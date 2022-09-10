export class OrderModel {
    id!:string; // reference id
    stationId!:string;
    fuelType!:string;
    quantity!:string;
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