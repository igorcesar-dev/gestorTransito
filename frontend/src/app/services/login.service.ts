import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root' // torna o serviço disponível globalmente na aplicação
})
export class LoginService {

  // url da api
  apiUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  // método para fazer login
  login(login: string, senha: string) {
    return this.httpClient.post<LoginResponse>(`${this.apiUrl}/login`, { login, senha}).pipe(
      tap((value) => {
        sessionStorage.setItem("auth-token", value.token); // armazena o token de autenticação
        sessionStorage.setItem("login", value.login); // armazena o login do usuário
        sessionStorage.setItem("nome", value.nome); // armazena o nome do usuário
      })
    );
  }
}
