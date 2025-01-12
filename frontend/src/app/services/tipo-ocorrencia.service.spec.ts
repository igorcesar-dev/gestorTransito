import { TestBed } from '@angular/core/testing';

import { TipoOcorrenciaService } from './tipo-ocorrencia.service';

describe('TipoOcorrenciaService', () => {
  let service: TipoOcorrenciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoOcorrenciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
