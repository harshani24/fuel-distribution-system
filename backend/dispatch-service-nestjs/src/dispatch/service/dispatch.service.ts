import { Dispatch } from './../schemas/Dispatch.schema';
import { DispatchCreateDto } from './../dto/DispatchCreate.dto';
import { DispatchRepository } from './../repository/Dispatch.repository';
import { Injectable } from '@nestjs/common';

@Injectable()
export class DispatchService {
  constructor(private dispatchRepository: DispatchRepository) {}

  async getAll(): Promise<Dispatch[]> {
    return await this.dispatchRepository.findAll();
  }

  async create(dispatchCreateDto: DispatchCreateDto): Promise<Dispatch> {
    const dispatch = new Dispatch();
    dispatch.orderId = dispatchCreateDto.orderId;
    dispatch.station = dispatchCreateDto.station;
    dispatch.passport = dispatchCreateDto.passport;
   
    dispatch.octane92 = dispatchCreateDto.octane92;
    dispatch.quantityOctane92 = dispatchCreateDto.quantityOctane92;

    dispatch.octane95 = dispatchCreateDto.octane95;
    dispatch.quantityOctane95 = dispatchCreateDto.quantityOctane95;

    dispatch.autoDiesel = dispatchCreateDto.autoDiesel;
    dispatch.quantityAutoDiesel = dispatchCreateDto.quantityAutoDiesel;

    dispatch.superDiesel = dispatchCreateDto.superDiesel;
    dispatch.quantitySuperDiesel = dispatchCreateDto.quantitySuperDiesel;

    dispatch.scheduledDate = dispatchCreateDto.scheduledDate;

    return await this.dispatchRepository.create(dispatch);
  }

  async setDispatchStatus(id: string): Promise<Dispatch> {
    return await this.dispatchRepository.setDispatchStatus(id);
  }

  setDateValues(d: number[]): Date {
    //[ 2022, 8, 4, 19, 25, 21, 764327200 ]
    //[2022,9,5,22,39,13]
    let year: number= d[0];
    let month: number= d[1];
    let date: number= d[2];
    let hour: number= d[3];
    let min: number= d[4];
    let sec: number= d[5];

    //let scheduleDate: Date = new Date(+year, month - 1, +date , +hour, +min, +sec );
    let scheduleDate: Date = new Date(+year, month - 1, +date);
    console.log(scheduleDate);

    return scheduleDate;
  }
}
