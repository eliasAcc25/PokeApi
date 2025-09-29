# PokeAPI - Sistema de Batalhas Pokémon

Uma API REST desenvolvida em Spring Boot que permite simular batalhas entre Pokémon.

## 🚀 Funcionalidades

- Consulta de Pokémons
- Criação de batalhas entre Pokémons
- Sistema de combate baseado em atributos dos Pokémons
- Interface web para visualização das batalhas
- Integração com a API externa de Pokémon

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database
- Maven
- Spring Data JPA
- Thymeleaf (para interface web)

## ⚙️ Pré-requisitos

- Java 17 ou superior
- Maven

## 📋 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/eliasAcc25/PokeApi.git
```

2. Entre no diretório do projeto:
```bash
cd PokeApi
```

3. Execute o projeto com Maven:
```bash
mvn spring-boot:run
```

O servidor iniciará em `http://localhost:8080`

## 🎮 Como Usar

### Via Navegador

Acesse `http://localhost:8080` para usar a interface web.

### Via Postman

Uma coleção do Postman está disponível no arquivo `PokeFight_Postman_Collection.json`.

## 📚 Documentação

- Para mais detalhes sobre como jogar via browser, consulte [COMO_JOGAR_BROWSER.md](COMO_JOGAR_BROWSER.md)
- Para mais detalhes sobre como usar via Postman, consulte [COMO_JOGAR_POSTMAN.md](COMO_JOGAR_POSTMAN.md)
- Para informações sobre os testes, consulte [GUIA_TESTES.md](GUIA_TESTES.md)

## 📝 Endpoints da API

### Pokémon
- GET `/api/pokemon` - Lista todos os Pokémons
- GET `/api/pokemon/{id}` - Obtém um Pokémon específico

### Batalhas
- POST `/api/battles` - Cria uma nova batalha
- GET `/api/battles` - Lista todas as batalhas
- GET `/api/battles/{id}` - Obtém detalhes de uma batalha específica

## 👥 Autor

- Elias Torres
