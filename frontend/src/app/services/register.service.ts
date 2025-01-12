import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { environment } from '../../environments/environment.development';

interface RegisterResponse {
  success: boolean;
  message: string;
}

@Injectable({
  providedIn: 'root' // torna o serviço disponível globalmente na aplicação
})
export class RegisterService {

  // url da api
  apiUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { } // injeta o HttpClient para fazer as requisições HTTP

  // método para registrar o usuário
  register(nome: string, login: string, senha: string) {
    return this.httpClient.post<RegisterResponse>(`${this.apiUrl}/register`, { nome, login, senha }).pipe(
      tap());
  }
}
