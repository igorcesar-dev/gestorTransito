import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OcorrenciaService } from '../../../services/ocorrencia.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs'; // Adicionado import para Subscription

@Component({
  selector: 'app-detalhe-ocorrencia',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  templateUrl: './detalhe-ocorrencia.component.html',
  styleUrls: ['./detalhe-ocorrencia.component.css']
})
export class DetalheOcorrenciaComponent implements OnInit, OnDestroy {
  ocorrencia: any;
  novoComentario: string = '';
  isAuthenticated: boolean = false; // Controle do estado de autenticação
  private authSubscription: Subscription | null = null; // Subscrição para autenticação

  constructor(
    private route: ActivatedRoute,
    private ocorrenciaService: OcorrenciaService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Observar mudanças no estado de autenticação
    this.authSubscription = this.authService.getAuthToken().subscribe((token) => {
      this.isAuthenticated = !!token; // Atualiza automaticamente quando o token muda
    });

    // Carregar os detalhes da ocorrência
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.ocorrenciaService.getOcorrenciaById(id).subscribe((data) => {
      this.ocorrencia = data;
    });
  }

  adicionarComentario(): void {
    if (!this.isAuthenticated) {
      alert('Você precisa estar autenticado para adicionar um comentário.');
      return;
    }

    if (!this.novoComentario.trim()) {
      alert('O comentário não pode estar vazio.');
      return;
    }

    const id = this.ocorrencia.id;
    this.ocorrenciaService.addComentario(id, this.novoComentario).subscribe({
      next: (comentario) => {
        this.ocorrencia.comentarios.push(comentario);
        this.novoComentario = '';
      },
      error: () => {
        alert('Erro ao adicionar o comentário.');
      }
    });
  }

  ngOnDestroy(): void {
    // Cancelar inscrição para evitar vazamentos de memória
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
