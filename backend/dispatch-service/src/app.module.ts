import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { DispatchModule } from './dispatch/dispatch.module';

@Module({
  imports: [DispatchModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
