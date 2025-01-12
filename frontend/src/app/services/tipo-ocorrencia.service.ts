import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoOcorrencia } from '../models/TipoOcorrencia';
@Injectable({
  providedIn: 'root'
})
export class TipoOcorrenciaService {

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAll(): Observable<TipoOcorrencia[]> {
    return this.http.get<TipoOcorrencia[]>(`${this.apiUrl}/tipo-ocorrencia`);
  }
}
