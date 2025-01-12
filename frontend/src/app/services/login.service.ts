import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  apiUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  login(login: string, senha: string) {
    return this.httpClient.post<LoginResponse>(`${this.apiUrl}/login`, { login, senha }).pipe(
      tap((value) => {
        sessionStorage.setItem("auth-token", value.token)
        sessionStorage.setItem("login", value.login)
      }))
  }
}
