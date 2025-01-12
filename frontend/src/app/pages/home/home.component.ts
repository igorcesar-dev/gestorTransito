import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Ocorrencia } from '../../models/Ocorrencia';
import { OcorrenciaService } from '../../services/ocorrencia.service';
import { TipoOcorrenciaService } from '../../services/tipo-ocorrencia.service';
import { TipoOcorrencia } from '../../models/TipoOcorrencia';
import { CommonModule } from '@angular/common'; // Importe CommonModule
import { ReactiveFormsModule } from '@angular/forms';  // Para usar formControl
import { FormsModule } from '@angular/forms';  // <-- Importando o FormsModule

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],  // Adicione o CommonModule aqui
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'], // Corrigido
})
export class HomeComponent implements OnInit {
  palavraChave = new FormControl('');
  idTipo: number | null = null;
  ocorrencias?: Ocorrencia[];
  tipoOcorrencias?: TipoOcorrencia[];

  constructor(
    private serviceOcorrencia: OcorrenciaService,
    private serviceTipoOcorrencia: TipoOcorrenciaService
  ) { }

  ngOnInit(): void {
    this.retrieveOcorrencias();
    this.retrieveTipoOcorrencias();
  }

  retrieveOcorrencias(): void {
    this.serviceOcorrencia.getAll().subscribe({
      next: (data) => {
        this.ocorrencias = data;
      },
      error: (e) => console.error(e),
    });
  }

  retrieveTipoOcorrencias(): void {
    this.serviceTipoOcorrencia.getAll().subscribe({
      next: (data) => {
        this.tipoOcorrencias = data;
      },
      error: (e) => console.error(e),
    });
  }

  search(): void {
    const valor = this.palavraChave.value;  // Pega o valor do FormControl
    if (valor) {
      this.serviceOcorrencia.searchOcorrenciasByPalavraChave(valor).subscribe({
        next: (data) => {
          this.ocorrencias = data;
        },
        error: (err) => {
          console.error('Erro ao buscar ocorrências: ', err);
        },
      });
    } else {
      this.retrieveOcorrencias();
    }
  }

  /* searchByTipo(): void {
    const valor = this.idTipo;
    if (valor != null) {
      this.serviceOcorrencia.filterOcorrenciasByTipo(valor).subscribe({
        next: (data) => {
          this.ocorrencias = data;
        },
        error: (err) => {
          console.error('Erro ao filtrar ocorrências: ', err);
        },
      });
    } else {
      this.retrieveOcorrencias();
    }
  } */

  searchByTipo(): void {
    if (this.idTipo !== null && this.idTipo !== undefined) {
      this.serviceOcorrencia.filterOcorrenciasByTipo(this.idTipo).subscribe({
        next: (data) => {
          this.ocorrencias = data;
        },
        error: (err) => {
          console.error('Erro ao filtrar ocorrências: ', err);
        },
      });
    } else {
      this.retrieveOcorrencias();
    }
  }

}
