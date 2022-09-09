import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { MONGO_CONNECTION } from './app.properties';
import { DispatchModule } from './dispatch/dispatch.module';

@Module({
  imports: [DispatchModule, MongooseModule.forRoot(MONGO_CONNECTION)],
  controllers: [],
  providers: [],
})
export class AppModule {}
