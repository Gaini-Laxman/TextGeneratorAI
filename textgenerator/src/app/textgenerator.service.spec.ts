import { TestBed } from '@angular/core/testing';

import { TextGenerationService } from './textgenerator.service';

describe('TextgeneratorService', () => {
  let service: TextGenerationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TextGenerationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
