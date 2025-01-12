import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Ocorrencia } from '../../models/Ocorrencia';
import { OcorrenciaService } from '../../services/ocorrencia.service';
import { TipoOcorrenciaService } from '../../services/tipo-ocorrencia.service';
import { TipoOcorrencia } from '../../models/TipoOcorrencia';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  palavraChave = new FormControl(''); // control para palavra-chave de busca
  idTipo: number | null = null; // ID do tipo de ocorrência para filtro
  ocorrencias?: Ocorrencia[]; // lista de ocorrências
  tipoOcorrencias?: TipoOcorrencia[]; // lista de tipos de ocorrências

  constructor(
    private serviceOcorrencia: OcorrenciaService,
    private serviceTipoOcorrencia: TipoOcorrenciaService,
    private toastService: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.retrieveOcorrencias(); // recupera todas as ocorrências ao inicializar
    this.retrieveTipoOcorrencias(); // recupera todos os tipos de ocorrências ao inicializar
  }

  // função para recuperar todas as ocorrências
  retrieveOcorrencias(): void {
    this.serviceOcorrencia.getAll().subscribe({
      next: (data) => {
        this.ocorrencias = data; // armazena no estado
      },
      error: (e) => console.error(e),
    });
  }

  // função para recuperar todos os tipos de ocorrências
  retrieveTipoOcorrencias(): void {
    this.serviceTipoOcorrencia.getAll().subscribe({
      next: (data) => {
        this.tipoOcorrencias = data; // armazena no estado
      },
      error: (e) => console.error(e),
    });
  }

  // função para excluir uma ocorrência
  delete(id: number): void {
    const token = sessionStorage.getItem('auth-token');
    // verifica se o token de autenticação está presente
    if (token) {
      this.serviceOcorrencia.deleteOcorrencia(id, token).subscribe({
        next: () => {
          this.toastService.success('Ocorrência excluída com sucesso!');
          this.retrieveOcorrencias();
        },
        error: (err) => {
          console.error('Erro ao excluir ocorrência:', err);
          const errorMessage = err?.error?.message || 'Erro ao excluir ocorrência. Tente novamente.'; 
          this.toastService.error(errorMessage);
        },
      });
    } else {
      console.error('Token não encontrado. Usuário não autenticado.');
      this.toastService.error('Necessário autenticação do usuário que criou a ocorrência.');
    }
  }

  // função para buscar ocorrências por palavra-chave
  search(): void {
    const valor = this.palavraChave.value;
    if (valor) {
      this.serviceOcorrencia.searchOcorrenciasByPalavraChave(valor).subscribe({
        next: (data) => {
          this.ocorrencias = data; // Atualiza as ocorrências filtradas
        },
        error: (err) => {
          console.error('Erro ao buscar ocorrências: ', err); // Loga erro no console
        },
      });
    } else {
      this.retrieveOcorrencias(); // Caso não haja valor, recupera todas as ocorrências
    }
  }

  // Função para buscar ocorrências por tipo
  searchByTipo(): void {
    if (this.idTipo !== null && this.idTipo !== undefined) {
      this.serviceOcorrencia.filterOcorrenciasByTipo(this.idTipo).subscribe({
        next: (data) => {
          this.ocorrencias = data; // atualiza as ocorrências filtradas por tipo
        },
        error: (err) => {
          console.error('Erro ao filtrar ocorrências: ', err);
        },
      });
    } else {
      this.retrieveOcorrencias(); // caso não haja tipo selecionado, recupera todas as ocorrências
    }
  }

  // função para redirecionar para o formulário de cadastro de ocorrência
  cadastrarOcorrencia() {
    const token = sessionStorage.getItem('auth-token');
    if (token) {
      this.router.navigate(['/cadastrar-ocorrencia']);
    } else {
      console.error('Token não encontrado. Usuário não autenticado.');
      this.toastService.error('Necessário autenticação para criar uma nova ocorrência.');
    }
  }
}
