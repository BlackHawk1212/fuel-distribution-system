import { FuelType } from "./fuelType";
import { Quantity } from "./quantity";

export class OrderModel {
    static values(order: any) {
      throw new Error('Method not implemented.');
    }
    id!:string; // reference id
    stationId!:string;
    fuelType!:FuelType;
    quantity!:Quantity;
    reserved!:boolean; // station created the order
    reservedTime!:string;
    qtyAllocated!:boolean; // quantity got allocated
    allocatedTime!:string;
    scheduled!:boolean; // delivery date scheduled
    scheduledTime!:string;
    dispatched!:boolean; // dispatched on the said date
    dispatchedTime!:string;
    delivered!:boolean; // delivered to the station
    deliveredTime!:string;
}