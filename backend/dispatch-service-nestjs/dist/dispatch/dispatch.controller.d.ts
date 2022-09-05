import { DispatchService } from './service/dispatch.service';
import { Dispatch } from './schemas/Dispatch.schema';
import { ClientKafka } from '@nestjs/microservices';
export declare class DispatchController {
    private dispatchService;
    constructor(dispatchService: DispatchService);
    getAllDispatches(): Promise<Dispatch[]>;
    setOrderStatus(body: any): Promise<Dispatch>;
    scheduleListener(message: any): Promise<Dispatch>;
    client: ClientKafka;
    onModuleInit(): Promise<void>;
    updateOrderStatus(id: any): Promise<void>;
}
