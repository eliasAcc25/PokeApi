# PokeAPI - Sistema de Batalhas Pokémon

Uma API REST desenvolvida em Spring Boot que permite simular batalhas entre Pokémon.

## 🏗️ Arquitetura do Sistema

<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/><mxCell id="2" value="Frontend" style="swimlane;whiteSpace=wrap;html=1;fillColor=#e1f5fe;strokeColor=#01579b;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="30" y="110" width="240" height="100" as="geometry"/></mxCell><mxCell id="3" value="Interface Web - Thymeleaf" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#e1f5fe;strokeColor=#01579b;fontSize=11;" vertex="1" parent="2"><mxGeometry x="20" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="4" value="Navegador" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#e1f5fe;strokeColor=#01579b;fontSize=11;" vertex="1" parent="2"><mxGeometry x="130" y="40" width="80" height="40" as="geometry"/></mxCell><mxCell id="5" value="API Layer" style="swimlane;whiteSpace=wrap;html=1;fillColor=#f3e5f5;strokeColor=#7b1fa2;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="590" y="110" width="360" height="100" as="geometry"/></mxCell><mxCell id="6" value="WebController" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f3e5f5;strokeColor=#7b1fa2;fontSize=11;" vertex="1" parent="5"><mxGeometry x="20" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="7" value="BattleController" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f3e5f5;strokeColor=#7b1fa2;fontSize=11;" vertex="1" parent="5"><mxGeometry x="130" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="8" value="PokemonController" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f3e5f5;strokeColor=#7b1fa2;fontSize=11;" vertex="1" parent="5"><mxGeometry x="240" y="40" width="100" height="40" as="geometry"/></mxCell><mxCell id="9" value="Service Layer" style="swimlane;whiteSpace=wrap;html=1;fillColor=#e8f5e8;strokeColor=#2e7d32;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="590" y="390" width="360" height="100" as="geometry"/></mxCell><mxCell id="10" value="BattleService" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#e8f5e8;strokeColor=#2e7d32;fontSize=11;" vertex="1" parent="9"><mxGeometry x="20" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="11" value="PokemonService" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#e8f5e8;strokeColor=#2e7d32;fontSize=11;" vertex="1" parent="9"><mxGeometry x="130" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="12" value="RestTemplate" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#e8f5e8;strokeColor=#2e7d32;fontSize=11;" vertex="1" parent="9"><mxGeometry x="240" y="40" width="100" height="40" as="geometry"/></mxCell><mxCell id="13" value="Data Layer" style="swimlane;whiteSpace=wrap;html=1;fillColor=#fff3e0;strokeColor=#f57c00;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="245" y="770" width="360" height="100" as="geometry"/></mxCell><mxCell id="14" value="BattleRepository" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#fff3e0;strokeColor=#f57c00;fontSize=11;" vertex="1" parent="13"><mxGeometry x="20" y="40" width="90" height="40" as="geometry"/></mxCell><mxCell id="15" value="PokemonRepository" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#fff3e0;strokeColor=#f57c00;fontSize=11;" vertex="1" parent="13"><mxGeometry x="130" y="40" width="100" height="40" as="geometry"/></mxCell><mxCell id="16" value="H2 Database" style="shape=cylinder3;whiteSpace=wrap;html=1;boundedLbl=1;backgroundOutline=1;size=15;fillColor=#fff3e0;strokeColor=#f57c00;fontSize=11;" vertex="1" parent="13"><mxGeometry x="260" y="35" width="80" height="50" as="geometry"/></mxCell><mxCell id="17" value="External API" style="swimlane;whiteSpace=wrap;html=1;fillColor=#ffebee;strokeColor=#c62828;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="1130" y="380" width="180" height="100" as="geometry"/></mxCell><mxCell id="18" value="PokeAPI&#10;Externa" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#ffebee;strokeColor=#c62828;fontSize=11;" vertex="1" parent="17"><mxGeometry x="60" y="50" width="80" height="40" as="geometry"/></mxCell><mxCell id="19" value="DTOs &amp; Entities" style="swimlane;whiteSpace=wrap;html=1;fillColor=#f1f8e9;strokeColor=#689f38;fontStyle=1;fontSize=14;startSize=30;swimlaneLine=1;" vertex="1" parent="1"><mxGeometry x="640" y="770" width="280" height="100" as="geometry"/></mxCell><mxCell id="20" value="BattleDTO" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f1f8e9;strokeColor=#689f38;fontSize=10;" vertex="1" parent="19"><mxGeometry x="20" y="40" width="60" height="25" as="geometry"/></mxCell><mxCell id="21" value="PokemonDTO" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f1f8e9;strokeColor=#689f38;fontSize=10;" vertex="1" parent="19"><mxGeometry x="90" y="40" width="70" height="25" as="geometry"/></mxCell><mxCell id="22" value="Battle Entity" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f1f8e9;strokeColor=#689f38;fontSize=10;" vertex="1" parent="19"><mxGeometry x="20" y="65" width="70" height="25" as="geometry"/></mxCell><mxCell id="23" value="Pokemon Entity" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f1f8e9;strokeColor=#689f38;fontSize=10;" vertex="1" parent="19"><mxGeometry x="100" y="65" width="80" height="25" as="geometry"/></mxCell><mxCell id="24" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="4" target="3" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="25" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="3" target="6" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="26" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="4" target="7" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="380" y="160"/><mxPoint x="585" y="160"/></Array></mxGeometry></mxCell><mxCell id="27" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="4" target="8" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="380" y="150"/><mxPoint x="800" y="150"/></Array></mxGeometry></mxCell><mxCell id="28" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="6" target="10" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="29" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="7" target="10" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="585" y="290"/><mxPoint x="575" y="290"/></Array></mxGeometry></mxCell><mxCell id="30" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="8" target="11" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="800" y="290"/><mxPoint x="685" y="290"/></Array></mxGeometry></mxCell><mxCell id="31" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="10" target="14" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="575" y="450"/><mxPoint x="275" y="450"/></Array></mxGeometry></mxCell><mxCell id="32" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="10" target="15" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="575" y="460"/><mxPoint x="390" y="460"/></Array></mxGeometry></mxCell><mxCell id="33" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="11" target="15" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="685" y="450"/><mxPoint x="390" y="450"/></Array></mxGeometry></mxCell><mxCell id="34" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="11" target="12" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="35" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="12" target="18" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="36" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="14" target="16" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="37" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=2;strokeColor=#333333;endArrow=classic;" edge="1" source="15" target="16" parent="1"><mxGeometry relative="1" as="geometry"/></mxCell><mxCell id="38" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=1;strokeColor=#666666;endArrow=classic;dashed=1;" edge="1" source="10" target="20" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="575" y="470"/><mxPoint x="680" y="470"/></Array></mxGeometry></mxCell><mxCell id="39" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=1;strokeColor=#666666;endArrow=classic;dashed=1;" edge="1" source="11" target="21" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="685" y="470"/><mxPoint x="755" y="470"/></Array></mxGeometry></mxCell><mxCell id="40" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=1;strokeColor=#666666;endArrow=classic;dashed=1;" edge="1" source="14" target="22" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="685" y="630"/></Array></mxGeometry></mxCell><mxCell id="41" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;strokeWidth=1;strokeColor=#666666;endArrow=classic;dashed=1;" edge="1" source="15" target="23" parent="1"><mxGeometry relative="1" as="geometry"><Array as="points"><mxPoint x="390" y="610"/><mxPoint x="770" y="610"/></Array></mxGeometry></mxCell><mxCell id="42" value="Arquitetura do Sistema PokeAPI - Batalhas Pokémon" style="text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;rounded=0;fontSize=18;fontStyle=1;fontColor=#1a1a1a;" vertex="1" parent="1"><mxGeometry x="360" y="20" width="400" height="30" as="geometry"/></mxCell><mxCell id="43" value="" style="rounded=1;whiteSpace=wrap;html=1;fillColor=#f5f5f5;strokeColor=#666666;" vertex="1" parent="1"><mxGeometry x="995" y="770" width="180" height="100" as="geometry"/></mxCell><mxCell id="44" value="Legenda:" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=top;whiteSpace=wrap;rounded=0;fontSize=12;fontStyle=1;" vertex="1" parent="1"><mxGeometry x="995" y="770" width="60" height="20" as="geometry"/></mxCell><mxCell id="45" value="━━━ Fluxo de dados" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;whiteSpace=wrap;rounded=0;fontSize=11;" vertex="1" parent="1"><mxGeometry x="995" y="800" width="150" height="20" as="geometry"/></mxCell><mxCell id="46" value="┅┅┅ Uso de DTOs/Entities" style="text;html=1;strokeColor=none;fillColor=none;align=left;verticalAlign=middle;whiteSpace=wrap;rounded=0;fontSize=11;" vertex="1" parent="1"><mxGeometry x="990" y="830" width="150" height="20" as="geometry"/></mxCell></root></mxGraphModel>




### Descrição da Arquitetura

#### 🎯 **Camada de Apresentação**
- **Interface Web**: Desenvolvida com Thymeleaf para interação via navegador
- **Controllers REST**: Endpoints para integração via API

#### ⚙️ **Camada de Negócio**
- **BattleService**: Lógica de criação e gerenciamento de batalhas
- **PokemonService**: Gerenciamento de Pokémons e integração com API externa
- **RestTemplate**: Cliente HTTP para comunicação com PokeAPI

#### 💾 **Camada de Dados**
- **Repositories**: Acesso aos dados usando Spring Data JPA
- **H2 Database**: Banco de dados em memória para persistência
- **Entities**: Mapeamento objeto-relacional

#### 🌐 **Integração Externa**
- **PokeAPI**: API externa para buscar dados dos Pokémons

#### 📋 **Fluxo de Dados**
1. **Consulta Pokémon**: Interface/API → Controller → Service → Repository → Database
2. **Busca Externa**: Service → RestTemplate → PokeAPI → Cache local
3. **Batalha**: Dois Pokémons → Lógica de combate → Resultado persistido

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
