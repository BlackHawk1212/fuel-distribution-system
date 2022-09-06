import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { DispatchController } from './dispatch.controller';
import { DispatchRepository } from './repository/Dispatch.repository';
import { Dispatch, DispatchSchema } from './schema/Dispatch.schema';
import { DispatchService } from './service/dispatch.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: Dispatch.name, schema: DispatchSchema },
    ]),
  ],
  controllers: [DispatchController],
  providers: [DispatchService, DispatchRepository],
})
export class DispatchModule { }
