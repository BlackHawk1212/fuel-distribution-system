import { Prop, Schema, SchemaFactory } from "@nestjs/mongoose";
import { Document } from 'mongoose';

export type DispatchDocument = Dispatch & Document;

@Schema()
export class Dispatch {

    @Prop()
    id: string;
    @Prop()
    stationId: string;
    @Prop()
    quantity: string;
    @Prop()
    scheduledDate: Date;
    @Prop()
    dispatchedDate: Date;

}

export const DispatchSchema = SchemaFactory.createForClass(Dispatch);