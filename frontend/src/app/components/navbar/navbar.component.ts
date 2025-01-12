import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service'; // Importando o serviço de autenticação
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(
    private authService: AuthService, // Injetando o AuthService
    private router: Router
  ) {}

  // Método para verificar se o usuário está logado
  getLoginUser(): boolean {
    let loggedIn: boolean = false;
    this.authService.getAuthToken().subscribe(token => {
      loggedIn = !!token; // Se token existir, o usuário está logado
    });
    return loggedIn;
  }

  // Método para obter o nome de usuário
  getUsernameLogin(): string {
    let username: string = 'Usuário';
    this.authService.getUsername().subscribe(user => {
      username = user; // Atualiza o nome de usuário a partir do serviço
    });
    return username;
  }

  // Método de logout
  logout(): void {
    this.authService.logout(); // Chama o serviço de logout
    this.router.navigate(['/login']); // Redireciona para a página de login
  }
}
