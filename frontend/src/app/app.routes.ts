import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/usuario/login/login.component';
import { RegisterComponent } from './pages/usuario/register/register.component';
import { CadastroOcorrenciaComponent } from './pages/ocorrencia/cadastro-ocorrencia/cadastro-ocorrencia.component';
import { DetalheOcorrenciaComponent } from './pages/ocorrencia/detalhe-ocorrencia/detalhe-ocorrencia.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registrar',
    component: RegisterComponent
  },
  {
    path: 'cadastrar-ocorrencia',
    component: CadastroOcorrenciaComponent
  },
  {
    path: 'detalhe-ocorrencia/:id',
    component: DetalheOcorrenciaComponent
  }
];
