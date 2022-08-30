import { Test, TestingModule } from '@nestjs/testing';
import { DispatchService } from './service/dispatch.service';

describe('DispatchService', () => {
  let service: DispatchService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [DispatchService],
    }).compile();

    service = module.get<DispatchService>(DispatchService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
