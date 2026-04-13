<!-- Área do Banner -->
<div align="center" style="background-color: white; max-width: 100%;">
  <img alt="BANNER com título: BOOTCAMP 1 - 2026" title="Banner_BOOTCAMP_1_2026" src=".github/img/Banner_BOOTCAMP_1_2026.png" width="100%" />
</div>

<!-- Título e descrição -->
<div align="center">
  <h1>MedAlerta</h1>
  <p><b>Projeto base para desenvolvimento de um sistema de gerenciamento de medicamentos, preparado com arquitetura em camadas, containers Docker e boas práticas de engenharia de software.</b></p>
</div>

<!-- Tecnologias -->
<p align="center">
  <a href="https://www.java.com/" title="Java">
    <img src="https://github.com/get-icon/geticon/raw/master/icons/java.svg" alt="Java" height="21px">
  </a>
  +
  <a href="https://spring.io/projects/spring-boot" title="Spring Boot">
    <img src="https://img.icons8.com/color/48/spring-logo.png" alt="Spring Boot" height="21px">
  </a>
  +
  <a href="https://www.mysql.com/" title="MySQL">
    <img src="https://github.com/get-icon/geticon/raw/master/icons/mysql.svg" alt="MySQL" height="21px">
  </a>
  +
  <a href="https://www.docker.com/" title="Docker">
    <img src="https://github.com/get-icon/geticon/raw/master/icons/docker-icon.svg" alt="Docker" height="21px">
  </a>
  +
  <a href="https://code.visualstudio.com/" title="VSCode">
    <img src="https://github.com/get-icon/geticon/raw/master/icons/visual-studio-code.svg" alt="VSCode" height="21px">
  </a>
</p>

---

# 👥 Professores

| [<img src="https://avatars.githubusercontent.com/u/8054061?v=4" width="75px;"/>](https://github.com/nascimentoRodrigo) | [<img src="https://avatars3.githubusercontent.com/u/60905310?s=460&v=4" width="75px;"/>](https://github.com/guipatriota) | [<img src="https://media.licdn.com/dms/image/v2/C4D03AQEZ0ucDyC-I3Q/profile-displayphoto-shrink_400_400/profile-displayphoto-shrink_400_400/0/1656806969197?e=1777507200&v=beta&t=s6pQJeDpx0wPD0s16Efp6SX4-CDDFdq9ucW1nEtoucY" width="75px;"/>](https://github.com/neusagrando) | [<img src="https://avatars.githubusercontent.com/u/155771429?v=4" width="75px;"/>](https://github.com/leonam-oliveira) | [<img src="https://media.licdn.com/dms/image/v2/D4D03AQFsHo8soDbGOw/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1689000005086?e=1777507200&v=beta&t=TnbkVxnkGM2ICqeMjFDwuMv-43Y4vJU3tGzEY_vqwYg" width="75px;"/>](https://github.com/eros-kohler-uninter) |
| :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| [Prof. Me. Rodrigo da Silva do Nascimento](https://github.com/nascimentoRodrigo) | [Prof. Guilherme Patriota](https://github.com/guipatriota) | [Prof. Neusa Grando](https://github.com/neusagrando) | [Prof. Leonam Cordeiro de Oliveira](https://github.com/leonam-oliveira) | [Prof. Eros Leon Kohler](https://github.com/eros-kohler-uninter) |

---

# 📌 Descrição

Este repositório contém a **infraestrutura base e estrutura arquitetural do projeto MedAlerta**, desenvolvida para o Bootcamp de Engenharia de Software.

O objetivo é preparar um ambiente profissional para desenvolvimento, incluindo:

- Arquitetura em camadas
- Containerização da aplicação
- Banco de dados MySQL integrado
- Configuração por variáveis de ambiente
- Inicialização automática do banco
- Base para CI/CD com GitHub Actions

Esta base será utilizada nas aulas seguintes para implementação de:

- CRUD
- Integração com banco de dados
- Regras de negócio
- Autenticação e segurança

---

# 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**:

Service → Repository → Banco de Dados

- **Service**: regras de negócio  
- **Repository (CRUD)**: acesso aos dados  
- **Banco (MySQL)**: persistência  

A aplicação poderá ser exposta via API (ex: REST), mas isso não é foco inicial.

---

# 🧱 Infraestrutura Base

O projeto utiliza Docker para padronizar o ambiente.

## Containers

- **app** → ambiente Java (desenvolvimento)
- **db** → MySQL

## Características

- Comunicação entre containers via rede interna
- Banco acessível externamente (Workbench)
- Aplicação acessível via navegador
- Ambiente reproduzível em qualquer máquina

---

# ⚙️ Pré-requisitos

- Docker Desktop
- VSCode
- Extensão Dev Containers (recomendado)

---

# 🚀 Como executar o projeto

## 1. Configurar variáveis de ambiente

```bash
cp .env.example .env
```
## 2. Subir os containers

```bash
docker compose up --build
```

## 3. Acessos

- Aplicação: http://localhost:8080  
- Health (se implementado): http://localhost:8080/actuator/health  
- MySQL: localhost:3306  

---

# 🗄️ Banco de Dados

O banco MySQL é inicializado automaticamente através de scripts em:

docker/mysql/init/

⚠️ Importante:

- Scripts `.sql` são executados apenas na primeira inicialização
- Para reinicializar o banco:

```bash
docker compose down -v  
docker compose up --build  
```
---

# 🧪 GitHub Actions (CI)

O projeto inclui um pipeline de CI que:

- Sobe um MySQL temporário
- Executa testes automatizados
- Gera a imagem Docker de produção

---

# 🔐 Segurança (base)

A estrutura já considera boas práticas iniciais:

- Uso de variáveis de ambiente para credenciais
- Separação entre configuração e código
- Preparação para autenticação
- Proteção contra SQL Injection (uso de ORM nas próximas etapas)

---

# ⚠️ Observações importantes para trabalhos fora deste BOOTCAMP

- Este projeto representa uma **infraestrutura de desenvolvimento**
- Em produção real:
  - o banco não deve rodar em container local
  - credenciais devem ser armazenadas em cofres seguros
  - devem existir mecanismos de backup e redundância

---

# 🎯 Objetivo do Bootcamp

Construir um sistema completo a partir de uma base profissional, abordando:

- Arquitetura de software
- Infraestrutura
- Segurança
- Banco de dados
- Desenvolvimento backend
