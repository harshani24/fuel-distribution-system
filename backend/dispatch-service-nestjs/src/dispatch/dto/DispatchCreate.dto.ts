import { IsNotEmpty } from 'class-validator';

export class DispatchCreateDto {
  constructor() {}

  @IsNotEmpty()
  orderId: string;
  @IsNotEmpty()
  station: string;
  @IsNotEmpty()
  passport: string;

  octane92: boolean;
  quantityOctane92: number;

  octane95: boolean;
  quantityOctane95: number;

  autoDiesel: boolean;
  quantityAutoDiesel: number;

  superDiesel: boolean;
  quantitySuperDiesel: number; 

  @IsNotEmpty()
  scheduledDate: Date;

  dispatchedDate: Date;
}
