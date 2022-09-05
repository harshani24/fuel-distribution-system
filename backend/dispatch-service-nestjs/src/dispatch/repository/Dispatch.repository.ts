import { Dispatch, DispatchDocument } from './../schemas/Dispatch.schema';
import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';

@Injectable()
export class DispatchRepository {
  constructor(
    @InjectModel(Dispatch.name) private dispatchModel: Model<DispatchDocument>,
  ) {}


  async create(dispatch: Dispatch): Promise<Dispatch> {
    let newDispatch = new this.dispatchModel(dispatch);
    return await newDispatch.save();
  }

  async findAll(): Promise<Dispatch[]> {
    let list = await this.dispatchModel.find({ dispatchedDate: null });
    console.log('Fetching the available dispatch list');
    return list;
  }

  async setDispatchStatus(id: string): Promise<Dispatch> {
    console.log('Setting order dispatched status ' + id);

    const filter = { orderId: id };
    const update = { dispatchedDate: new Date() };

    const record = await this.dispatchModel.findOneAndUpdate(filter, update, {
      new: true,
    });
    return record;
  }
}
