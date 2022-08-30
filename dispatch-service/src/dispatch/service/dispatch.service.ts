import { Injectable } from '@nestjs/common';
import { DispatchCreateDto } from '../dto/DispatchCreate.dto';
import { DispatchRepository } from '../repository/Dispatch.repository';
import { Dispatch } from '../schema/Dispatch.schema';

@Injectable()
export class DispatchService {

    constructor(private dispatchRepository: DispatchRepository) {

    }

    async getAll(): Promise<Dispatch[]> {
        return await this.dispatchRepository.findAll();
    }

    async create(dispatchCreateDto: DispatchCreateDto): Promise<Dispatch> {
        const dispatch = new Dispatch();
        dispatch.id = dispatchCreateDto.id;
        dispatch.stationId = dispatchCreateDto.stationId;
        dispatch.quantity = dispatchCreateDto.quantity;
        dispatch.scheduledDate = dispatchCreateDto.scheduledDate;

        return await this.dispatchRepository.create(dispatch);
    }

    async setDispatchStatus(id: string): Promise<Dispatch> {
        return await this.dispatchRepository.setDispatchStatus(id);
    }

    setDateValues(digit: number[]): Date {
        let date: Date = new Date();
        date.setFullYear(digit[0]);
        date.setMonth(digit[1]);
        date.setDate(digit[2]);
        date.setHours(digit[3]);
        date.setMinutes(digit[4] + 1);
        date.setSeconds(digit[5]);
        date.setMilliseconds(digit[6]);
        return date;
    }

}