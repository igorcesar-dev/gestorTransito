export interface Comentario {
  id: number;
  texto: string;
  dataHora: string;
  usuario: {
    id: number;
    nome: string;
    login: string;
  };
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
  comentarios?: Comentario[];
}
