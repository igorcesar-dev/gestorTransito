import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authTokenSubject = new BehaviorSubject<string | null>(this.getSessionStorageItem('auth-token'));
  private loginSubject = new BehaviorSubject<string>(this.getSessionStorageItem('login') ?? 'Usuário');
  private usernameSubject = new BehaviorSubject<string>(this.getSessionStorageItem('nome') ?? 'Nome');

  constructor() { }

  // Método para verificar se o código está sendo executado no navegador
  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof window.sessionStorage !== 'undefined';
  }

  // Método para obter item do sessionStorage com segurança
  private getSessionStorageItem(key: string): string | null {
    if (this.isBrowser()) {
      return sessionStorage.getItem(key);
    }
    return null; // Retorna null se estiver sendo executado no servidor
  }

  isAuthenticated(): boolean {
    return !!this.authTokenSubject.value; // Verifica se o token existe
  }

  // Retorna um observable para o token
  getAuthToken() {
    return this.authTokenSubject.asObservable(); // Permite que a interface reaja a mudanças
  }

  // Retorna um observable para o nome de usuário
  getUsername() {
    return this.loginSubject.asObservable(); // Permite que a interface reaja a mudanças
  }

  // Método para fazer login
  login(token: string, login: string, nome: string): void {
    if (this.isBrowser()) {
      sessionStorage.setItem('auth-token', token);
      sessionStorage.setItem('login', login);
      sessionStorage.setItem('nome', nome);
    }
    this.authTokenSubject.next(token);
    this.loginSubject.next(login);
    this.usernameSubject.next(nome);
  }

  // Método para fazer logout
  logout(): void {
    if (this.isBrowser()) {
      sessionStorage.removeItem('auth-token');
      sessionStorage.removeItem('login');
      sessionStorage.removeItem('nome');
    }
    this.authTokenSubject.next(null);
    this.loginSubject.next('Usuário');
    this.usernameSubject.next('Nome');
  }
}
