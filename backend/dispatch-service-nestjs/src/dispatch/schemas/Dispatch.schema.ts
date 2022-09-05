import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type DispatchDocument = Dispatch & Document;

@Schema()
export class Dispatch {

  @Prop()
  orderId: string;
  @Prop()
  station: string;
  @Prop()
  passport: string;

  @Prop()
  octane92: boolean;
  @Prop()
  quantityOctane92: number;

  @Prop()
  octane95: boolean;
  @Prop()
  quantityOctane95: number;

  @Prop()
  autoDiesel: boolean;
  @Prop()
  quantityAutoDiesel: number;

  @Prop()
  superDiesel: boolean;
  @Prop()
  quantitySuperDiesel: number; 

  @Prop()
  scheduledDate: Date;
  @Prop()
  dispatchedDate: Date;
}

export const DispatchSchema = SchemaFactory.createForClass(Dispatch);
