import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheOcorrenciaComponent } from './detalhe-ocorrencia.component';

describe('DetalheOcorrenciaComponent', () => {
  let component: DetalheOcorrenciaComponent;
  let fixture: ComponentFixture<DetalheOcorrenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalheOcorrenciaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetalheOcorrenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
