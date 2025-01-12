import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ocorrencia } from '../models/Ocorrencia';
@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Ocorrencia[]> {
    return this.http.get<Ocorrencia[]>(`${this.apiUrl}/ocorrencia`);
  }

  searchOcorrenciasByPalavraChave(palavraChave: string): Observable<Ocorrencia[]> {
    const url = `${this.apiUrl}/ocorrencia/search?palavraChave=${encodeURIComponent(palavraChave)}`;
    return this.http.get<Ocorrencia[]>(url);
  }


  filterOcorrenciasByTipo(idTipo: number): Observable<Ocorrencia[]> {
    const url = `${this.apiUrl}/ocorrencia/search?idTipo=${encodeURIComponent(idTipo)}`;
    return this.http.get<Ocorrencia[]>(url);
  }
}
