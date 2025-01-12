import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../../components/default-login-layout/default-login-layout.component';
import { PrimaryInputComponent } from '../../../components/primary-input/primary-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterService } from '../../../services/register.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

// definição da estrutura do formulário de registro
interface RegisterForm {
  nome: FormControl;
  login: FormControl;
  password: FormControl;
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    PrimaryInputComponent,
    ReactiveFormsModule
  ],
  providers: [
    RegisterService
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm!: FormGroup<RegisterForm>; // definição do formulário de registro

  constructor(
    private registerService: RegisterService,
    private toastService: ToastrService,
    private router: Router
  ) {
    // inicializando o formulário com os campos e suas validações
    this.registerForm = new FormGroup({
      nome: new FormControl('', [Validators.required, Validators.minLength(3)]),
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  // método para submeter o formulário de registro
  register() {
    // verifica se o formulário é válido
    if (this.registerForm.valid) {
      this.registerService.register(
        this.registerForm.value.nome,
        this.registerForm.value.login,
        this.registerForm.value.password
      ).subscribe({
        next: (response) => {
          this.toastService.success("Usuário registrado com sucesso!");
          this.router.navigate(['/login']);
        },
        error: (err) => {
          this.toastService.error("Erro ao registrar o usuário!");
          console.error(err);
        }
      });
    } else {
      this.toastService.error("Preencha todos os campos corretamente!");
    }
  }
}
