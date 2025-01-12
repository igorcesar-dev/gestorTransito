import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoOcorrencia } from '../models/TipoOcorrencia';

@Injectable({
  providedIn: 'root'// torna o serviço disponível globalmente na aplicação
})

export class TipoOcorrenciaService {

  // url da api
  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { } // injeta o HttpClient para fazer as requisições HTTP

    // método para listar todos os tipos de ocorrências
  getAll(): Observable<TipoOcorrencia[]> {
    return this.http.get<TipoOcorrencia[]>(`${this.apiUrl}/tipo-ocorrencia`);
  }
}
