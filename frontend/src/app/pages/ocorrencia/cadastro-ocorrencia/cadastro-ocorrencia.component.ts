import { Component, OnInit } from '@angular/core';
import { DefaultRegisterLayoutComponent } from '../../../components/default-register-layout/default-register-layout.component';
import { PrimaryInputComponent } from '../../../components/primary-input/primary-input.component';
import { PrimarySelectComponent } from '../../../components/primary-select/primary-select.component';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { TipoOcorrenciaService } from '../../../services/tipo-ocorrencia.service';
import { OcorrenciaService } from '../../../services/ocorrencia.service';
import { ToastrService } from 'ngx-toastr';

// definição da estrutura do formulário
interface RegisterUsuarioForm {
  resumo: FormControl;
  descricao: FormControl;
  dataHora: FormControl;
  endereco: FormControl;
  tipoOcorrenciaId: FormControl;
  latitude: FormControl;
  longitude: FormControl;
}

@Component({
  selector: 'app-cadastro-ocorrencia',
  standalone: true,
  imports: [
    DefaultRegisterLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent,
    PrimarySelectComponent,
  ],
  templateUrl: './cadastro-ocorrencia.component.html',
  styleUrls: ['./cadastro-ocorrencia.component.css']
})
export class CadastroOcorrenciaComponent implements OnInit {
  registerUsuarioForm!: FormGroup<RegisterUsuarioForm>; // declaração do formulário com seus respectivos controles
  tiposOcorrencia: { label: string; value: string }[] = []; // lista de tipos de ocorrências a serem carregadas no select

  constructor(
    private tipoOcorrenciaService: TipoOcorrenciaService,
    private ocorrenciaService: OcorrenciaService,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    // inicializa o formulário com controles e seus valores iniciais
    this.registerUsuarioForm = new FormGroup<RegisterUsuarioForm>({
      resumo: new FormControl(''),
      descricao: new FormControl(''),
      dataHora: new FormControl(''),
      endereco: new FormControl(''),
      tipoOcorrenciaId: new FormControl(0),
      latitude: new FormControl(0),
      longitude: new FormControl(0)
    });

    // carrega os tipos de ocorrência do serviço
    this.tipoOcorrenciaService.getAll().subscribe({
      next: (data) => {
        // transforma os dados recebidos para o formato esperado pelo select
        this.tiposOcorrencia = data.map((tipo: any) => ({
          label: tipo.descricao,
          value: tipo.id
        }));
      },
      error: (err) => {
        console.error('Erro ao carregar os tipos de ocorrência:', err);
      }
    });
  }

  // método para enviar o formulário
  submit() {
    // verifica se o token de autenticação está presente no sessionStorage
    const token = sessionStorage.getItem('auth-token');
    if (token) {
      // se o token estiver presente, chama o serviço para criar a ocorrência
      this.ocorrenciaService.createOcorrencia(this.registerUsuarioForm.value, token).subscribe({
        next: () => {
          this.toastService.success('Ocorrência cadastrada com sucesso!'); // Exibe mensagem de sucesso
          this.registerUsuarioForm.reset(); // Limpa os campos do formulário
        },
        error: (err) => {
          // se ocorrer erro ao cadastrar, exibe mensagem de erro
          console.error('Erro ao cadastrar ocorrência:', err);
          const errorMessage = err?.error?.message || 'Erro ao cadastrar ocorrência. Tente novamente.';
          this.toastService.error(errorMessage);
        },
      });
    } else {
      this.toastService.error("Você precisa estar logado para criar uma ocorrência.");
    }
  }
}
