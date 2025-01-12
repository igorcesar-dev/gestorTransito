import { Component } from '@angular/core';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { ToastrService } from 'ngx-toastr';

interface RegisterForm {
  nome: FormControl,
  login: FormControl,
  password: FormControl
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
  registerForm!: FormGroup<RegisterForm>;

  constructor(
    private registerService: RegisterService,
    private toastService: ToastrService
  ) {
    this.registerForm = new FormGroup({
      nome: new FormControl('', [Validators.required, Validators.minLength(3)]),
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  register() {
    if (this.registerForm.valid) {
      this.registerService.register(
        this.registerForm.value.nome,
        this.registerForm.value.login,
        this.registerForm.value.password
      ).subscribe({
        next: (response) => {
          this.toastService.success("Usuário registrado com sucesso!");
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
