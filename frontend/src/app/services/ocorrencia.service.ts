import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ocorrencia } from '../models/Ocorrencia';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root' // torna o serviço disponível globalmente na aplicação
})
export class OcorrenciaService {

  // url da api
  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private authService: AuthService) { } // injeta o HttpClient para fazer as requisições HTTP

  // método para listar todas as ocorrências
  getAll(): Observable<Ocorrencia[]> {
    return this.http.get<Ocorrencia[]>(`${this.apiUrl}/ocorrencia`);
  }

  // método para listar ocorrência pelo ID
  getOcorrenciaById(id: number): Observable<Ocorrencia> {
    return this.http.get<Ocorrencia>(`${this.apiUrl}/ocorrencia/${id}`);
  }

  // método para deletar uma ocorrência por ID, enviando o token no cabeçalho para autenticação
  deleteOcorrencia(id: number, token: string): Observable<void> {
    const headers = new HttpHeaders({
      Authorization: `${token}`
    });
    return this.http.delete<void>(`${this.apiUrl}/ocorrencia/${id}`, { headers });
  }

  // método para buscar ocorrências por palavra-chave
  searchOcorrenciasByPalavraChave(palavraChave: string): Observable<Ocorrencia[]> {
    const url = `${this.apiUrl}/ocorrencia/search?palavraChave=${encodeURIComponent(palavraChave)}`;
    return this.http.get<Ocorrencia[]>(url);
  }

  // método para cadastrar uma nova ocorrência
  createOcorrencia(ocorrencia: Ocorrencia, token: string): Observable<Ocorrencia> {
    const headers = new HttpHeaders({
      Authorization: `${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.post<Ocorrencia>(`${this.apiUrl}/ocorrencia`, ocorrencia, { headers });
  }

  // método para filtrar ocorrências por tipo
  filterOcorrenciasByTipo(idTipo: number): Observable<Ocorrencia[]> {
    const url = `${this.apiUrl}/ocorrencia/search?idTipo=${encodeURIComponent(idTipo)}`;
    return this.http.get<Ocorrencia[]>(url);
  }

  // método para adicionar comentário na ocorrência
  addComentario(ocorrenciaId: number, comentario: string): Observable<any> {
    const token = sessionStorage.getItem('auth-token'); // Lê o token do sessionStorage
    if (!token) {
      throw new Error('Usuário não autenticado.');
    }
  
    return this.http.post<any>(
      `${this.apiUrl}/comentario/ocorrencia/${ocorrenciaId}`,
      { comentario },
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }
  

}
