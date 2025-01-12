export interface ComentarioListar {
  id?: number;
  texto?: string;
  // Outros campos, se necessário
}

export interface Ocorrencia {
  id?: any;
  resumo?: string;
  descricao?: string;
  dataHora?: Date;
  endereco?: string;
  latitude?: number;
  longitude?: number;
  tipoOcorrencia?: string;
  comentarios?: ComentarioListar[];
}
