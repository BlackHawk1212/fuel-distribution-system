import { IsNotEmpty } from "class-validator";

export class DispatchCreateDto {
  constructor() {

  }

  @IsNotEmpty()
  id: string;
  @IsNotEmpty()
  stationId: string;
  @IsNotEmpty()
  quantity: string;
  @IsNotEmpty()
  scheduledDate: Date;

}