# Gestor de Ocorrência de Trânsito

## Descrição

O **Gestor de Ocorrência de Trânsito** é um sistema projetado para gerenciar ocorrências de trânsito de forma eficiente e segura. Ele inclui funcionalidades como registro de ocorrências, gerenciamento de usuários com autenticação por login, e integração com banco de dados para armazenamento das informações.

O projeto utiliza tecnologias modernas tanto no back-end quanto no front-end, garantindo desempenho e escalabilidade.

---

## Tecnologias Utilizadas

### Back-end

- **Spring Boot 3**: Framework Java para desenvolvimento de aplicações web e APIs.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações do sistema.
- **Flyway**: Ferramenta para controle de versões do banco de dados.

### Front-end

- **Angular 17**: Framework JavaScript para criação de interfaces dinâmicas e responsivas.
- **Bootstrap**: Framework CSS para estilização e responsividade do layout.

---

## Funcionalidades Principais

- **Autenticação de Usuários:** Sistema de login para controle de acesso.
- **Gestão de Ocorrências:** Cadastro, consulta, edição e exclusão de ocorrências.
- **Banco de Dados Relacional:** Estruturação eficiente dos dados com integração ao PostgreSQL.

---

## Estrutura do Projeto

O projeto é dividido em duas partes principais:

### 1. API (Back-end)

- Local: Diretório `api`
- Tecnologias: Spring Boot 3, PostgreSQL
- Porta padrão: `8080`
- Recursos:
  - Controle de usuários
  - Cadastro e gestão de ocorrências

### 2. Front-end

- Local: Diretório `frontend`
- Tecnologias: Angular 17, Bootstrap
- Porta padrão: `4200`
- Recursos:
  - Interface responsiva e intuitiva
  - Consumo de APIs RESTful
---

## API Spring Boot - Gestor Trânsito

### **1. Introdução**
Este repositório contém o código-fonte da API do projeto "Gestor Trânsito". Esta API foi desenvolvida com **Spring Boot 3** e **Java 17+**. O objetivo é gerenciar ocorrências de trânsito, oferecendo funcionalidades de criação, visualização e exclusão de ocorrências, além de filtros e dashboards.

---

### **2. Tecnologias Utilizadas**
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Spring security**
- **JWT**

---

### **3. Instalação e Configuração**

#### **3.1. Clone o Repositório**
```bash
git clone https://github.com/igorcesar-dev/gestorTransito.git
cd gestorTransito/api
```

#### **3.2. Configure o Banco de Dados**
1. Crie um banco de dados no PostgreSQL:
   ```sql
   CREATE DATABASE gestor_transito;
   ```
2. Atualize o arquivo `application.properties` com suas credenciais:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gestor_transito
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.flyway.enabled=true
   ```

#### **3.3. Execute a Aplicação**
Compile e inicie a aplicação com o Maven Wrapper:
```bash
./mvnw spring-boot:run
```
A API estará acessível em `http://localhost:8080`.

---

### **4. Endpoints da api**
A documentação completa dos endpoints está disponível via Swagger em `https://documenter.getpostman.com/view/22348567/2sAYQWKtpd`

---

### **5. Estrutura do Projeto**
A estrutura do projeto segue os princípios da arquitetura hexagonal:

```plaintext
src/
├── main/
│   ├── java/com/igor/api/
│   │   ├── application/          # Camada de aplicação (serviços, DTOs, ports)
│   │   │   ├── dto/              # Transferência de dados (DTOs)
│   │   │   └── service/          # Lógica de aplicação
│   │   ├── domain/               # Camada de domínio (entidades e regras de negócio)
│   │   ├── infrastructure/       # Infraestrutura (configurações, persistência, segurança)
│   │   │   ├── config/           # Configurações gerais
│   │   │   ├── persistence/      # Repositórios e especificações
│   │   │   └── security/         # Configurações de segurança
│   │   ├── interfaceAdapters/    # Adaptadores para comunicação externa (controllers)
│   │   └── ApiApplication.java   # Classe principal
│   └── resources/
│       ├── application.properties # Configurações do ambiente
│       └── db/migration/          # Scripts de migração (Flyway)
└── test/                          # Testes unitários e de integração
```

---

## **Frontend Angular - Documentação**

### **1. Pré-requisitos**
Certifique-se de ter os seguintes itens instalados no seu ambiente:
- **Node.js** (versão mínima: 18)
- **Angular CLI** (versão mínima: 17)
- Gerenciador de pacotes: **npm** ou **yarn**

---

### **2. Instalação**

1. Clone este repositório:
   ```bash
   git clone https://github.com/igorcesar-dev/gestorTransito.git
   
   ```

2. Instale as dependências:
   ```bash
   npm install
   ```

3. Execute o projeto localmente:
   ```bash
   ng serve ou npm run start
   ```
   O projeto estará acessível em `http://localhost:4200`.

---
### **3. Estrutura do Projeto**
Abaixo está a estrutura principal das pastas do projeto:

```plaintext
src/
├── app/
│   ├── components/             # Componentes reutilizáveis
│   │   ├── default-login-layout/
│   │   ├── default-register-layout/
│   │   ├── navbar/
│   │   ├── primary-input/
│   │   └── primary-select/
│   ├── pages/                  # Páginas principais
│   │   ├── home/
│   │   ├── ocorrencia/
│   │   │   ├── cadastro-ocorrencia/
│   │   │   └── detalhe-ocorrencia/
│   │   └── usuario/
│   │       ├── login/
│   │       └── register/
│   ├── services/               # Serviços para chamadas de API
│   ├── models/                 # Interfaces e modelos de dados
│   ├── types/                  # Tipos e definições
│   ├── app.component.*         # Arquivos do componente principal
│   ├── app.config.*            # Configurações gerais
│   └── app.routes.ts           # Configuração de rotas
├── assets/                     # Recursos estáticos (imagens, ícones, etc.)
├── environments/               # Configurações de ambiente (dev, prod)
└── main.ts                     # Ponto de entrada do aplicativo
```

---

### **4. Configurações de Ambiente**
As configurações de ambiente estão localizadas na pasta `src/environments`.

- `environment.ts`: Configurações para desenvolvimento.
- `environment.prod.ts`: Configurações para produção.

---

### **5. Componentes Principais**

- **DefaultLoginLayoutComponent**
  - **Local:** `src/app/components/default-login-layout/`
  - **Descrição:** Layout padrão para a página de login.

- **DefaultRegisterLayoutComponent**
  - **Local:** `src/app/components/default-register-layout/`
  - **Descrição:** Layout padrão para a página de registro.

- **NavbarComponent**
  - **Local:** `src/app/components/navbar/`
  - **Descrição:** Exibe a barra de navegação principal.

---

### **6. Serviços**

- **AuthService**
  - **Local:** `src/app/services/auth.service.ts`
  - **Descrição:** Gerencia autenticação de usuários.

- **LoginService**
  - **Local:** `src/app/services/login.service.ts`
  - **Descrição:** Gerencia o login de usuários.

- **RegisterService**
  - **Local:** `src/app/services/register.service.ts`
  - **Descrição:** Gerencia o registro de usuários.

- **OcorrenciaService**
  - **Local:** `src/app/services/ocorrencia.service.ts`
  - **Descrição:** Gerencia as chamadas relacionadas às ocorrências.

- **TipoOcorrenciaService**
  - **Local:** `src/app/services/tipo-ocorrencia.service.ts`
  - **Descrição:** Gerencia os tipos de ocorrências disponíveis.

---

### **7. Rotas**
As rotas do aplicativo estão configuradas no arquivo `app.routes.ts`.

### **Exemplo de Configuração de Rotas**
```typescript
export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registrar',
    component: RegisterComponent
  },
  {
    path: 'cadastrar-ocorrencia',
    component: CadastroOcorrenciaComponent
  },
  {
    path: 'detalhe-ocorrencia/:id',
    component: DetalheOcorrenciaComponent
  }
];
```

---

### **8. Scripts Disponíveis**
Os scripts disponíveis estão configurados no arquivo `package.json`.

### **Principais Scripts**
- **Iniciar o Servidor Local:**
  ```bash
  npm start
  ```
- **Build para Produção:**
  ```bash
  npm run build
  ```
- **Testes:**
  ```bash
  npm test
  ```

