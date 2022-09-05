import { DispatchCreateDto } from './dto/DispatchCreate.dto';
import {
  Body,
  Controller,
  Get,
  Injectable,
  Param,
  Inject,
  Post,
  Query,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { DispatchService } from './service/dispatch.service';
import { Dispatch } from './schemas/Dispatch.schema';
import {
  Client,
  ClientKafka,
  MessagePattern,
  Payload,
  ClientProxy,
  Transport,
} from '@nestjs/microservices';

const kafkaHost = process.env.KAFKA_HOST || 'localhost';
const kafkaPort = process.env.KAFKA_PORT || '9092';


@Controller('dispatch')
export class DispatchController {
  constructor(private dispatchService: DispatchService) {}

  //(http://localhost:8194/dispatch)
  @Get()
  @UsePipes(ValidationPipe)
  async getAllDispatches(): Promise<Dispatch[]> {
    console.log('Fetch all Dispatches');
    return await this.dispatchService.getAll();
  }

  //(http://localhost:8194/dispatch) +  request body
  @Post()
  async setOrderStatus(@Body() body) {
    this.updateOrderStatus(body.orderId);
    return await this.dispatchService.setDispatchStatus(body.orderId);
  }

  //kafka consumer
  @MessagePattern('dispatch-topic') 
  scheduleListener(@Payload() message) {
    console.log("new message " +JSON.stringify(message))

    message.scheduledDate = this.dispatchService.setDateValues(
      message.scheduledDate,
    );
    console.log('Create a Dispatch for ' + JSON.stringify(message));
    return this.dispatchService.create(message);
  }

  //kafka producer
  @Client({
    transport: Transport.KAFKA,
    options: {
      client: {
        clientId: 'dispatch-service', 
        brokers: [`${kafkaHost}:${kafkaPort}`],
      },
      consumer: {
        groupId: 'dispatch-group',
      },
    },
  })
  client: ClientKafka;


  async onModuleInit() {
    this.client.subscribeToResponseOf('dispatch-complete-topic');
    await this.client.connect();
  }

  //send msg to the kafka
  async updateOrderStatus(id) {
    this.client.emit('dispatch-complete-topic', id);
  }
}
