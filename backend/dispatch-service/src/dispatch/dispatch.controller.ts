import { Body, Controller, Get, Post, UsePipes, ValidationPipe } from '@nestjs/common';
import { Client, ClientKafka, MessagePattern, Payload, Transport } from '@nestjs/microservices';
import { Dispatch } from './schema/Dispatch.schema';
import { DispatchService } from './service/dispatch.service';

const kafkaHost = process.env.KAFKA_HOST || 'localhost';
const kafkaPort = process.env.KAFKA_PORT || '9092';

@Controller('dispatch')
export class DispatchController {

  constructor(private dispatchService: DispatchService) {}

  @Get()
  @UsePipes(ValidationPipe)
  async getAllDispatches(): Promise<Dispatch[]> {
    console.log('Fetching all Dispatches');
    return await this.dispatchService.getAll();
  }

  @Post()
  async setOrderStatus(@Body() body) {
    this.updateOrderStatus(body.id)
  }

  @MessagePattern('dispatchCreateTopic')
  scheduleListner(@Payload() message) {
    message.scheduledDate = this.dispatchService.setDateValues(
      message.scheduledDate,
    );
    console.log('Creating a Dispatch for ' + JSON.stringify(message));
    return this.dispatchService.create(message);
  }

  @Client({
    transport: Transport.KAFKA,
    options: {
      client: {
        clientId: 'dispatch-service',
        brokers: [`${kafkaHost}:${kafkaPort}`],
      },
      consumer: {
        groupId: 'ceypetco',
      },
    },
  })
  client: ClientKafka;

  async onModuleInit() {
    this.client.subscribeToResponseOf('dispatchSubmitTopic');
    await this.client.connect();
  }

  async updateOrderStatus(id) {
    this.client.emit('dispatchSubmitTopic', id);
  }

}