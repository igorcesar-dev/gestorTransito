import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../../components/default-login-layout/default-login-layout.component';
import { PrimaryInputComponent } from '../../../components/primary-input/primary-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

// definição da estrutura do formulário de login
interface LoginForm {
  login: FormControl;
  password: FormControl;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    PrimaryInputComponent,
    ReactiveFormsModule
  ],
  providers: [
    LoginService
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup<LoginForm>; // declaração do formulário de login

  constructor(
    private loginService: LoginService,
    private toastService: ToastrService,
    private router: Router,
  ) {
    // inicializa o formulário com validações
    this.loginForm = new FormGroup({
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  // método para submeter o formulário
  submit() {
    // verifica se o formulário é valido
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value.login, this.loginForm.value.password).subscribe({
        next: () => {
          this.toastService.success("Login feito com sucesso!");
          this.router.navigate(['/']);
        },
        error: () => {
          this.toastService.error("Verifique seu usuário ou senha!");
        }
      });
    } else {
      this.toastService.error("Por favor, preencha todos os campos corretamente.");
    }
  }
}
