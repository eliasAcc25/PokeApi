# 🎮 GUIA DE TESTES - POKÉFIGHT API

## 🚀 Sistema está rodando em: http://localhost:8080

---

## 📋 ENDPOINTS DISPONÍVEIS

### 1️⃣ **CRIAR BATALHA** (Principal funcionalidade)
```http
POST http://localhost:8080/api/batalhas
Content-Type: application/json

{
  "pokemon1Id": 1,
  "pokemon2Id": 4
}
```

### 2️⃣ **VER RESULTADO DE UMA BATALHA**
```http
GET http://localhost:8080/api/batalhas/1
```

### 3️⃣ **LISTAR TODAS AS BATALHAS**
```http
GET http://localhost:8080/api/batalhas
```

---

## 🧪 TESTES PRÁTICOS

### **TESTE 1: Batalha Pikachu vs Charmander**
```json
POST http://localhost:8080/api/batalhas
{
  "pokemon1Id": 25,
  "pokemon2Id": 4
}
```

### **TESTE 2: Batalha Bulbasaur vs Squirtle**
```json
POST http://localhost:8080/api/batalhas
{
  "pokemon1Id": 1,
  "pokemon2Id": 7
}
```

### **TESTE 3: Batalha Legendary - Mewtwo vs Mew**
```json
POST http://localhost:8080/api/batalhas
{
  "pokemon1Id": 150,
  "pokemon2Id": 151
}
```

---

## 🛠️ COMO TESTAR

### **OPÇÃO 1: Postman**
1. Abra o Postman
2. Crie uma nova requisição POST
3. URL: `http://localhost:8080/api/batalhas`
4. Headers: `Content-Type: application/json`
5. Body (raw/JSON): Cole um dos exemplos acima
6. Clique em "Send"

### **OPÇÃO 2: cURL (Terminal)**
```bash
curl -X POST http://localhost:8080/api/batalhas \
  -H "Content-Type: application/json" \
  -d '{"pokemon1Id": 25, "pokemon2Id": 4}'
```

### **OPÇÃO 3: Navegador (apenas GET)**
- Ver todas batalhas: http://localhost:8080/api/batalhas
- Ver batalha específica: http://localhost:8080/api/batalhas/1

---

## 🎯 O QUE ESPERAR

### **Resposta da Batalha:**
```json
{
  "id": 1,
  "pokemon1": {
    "id": 1,
    "name": "pikachu",
    "sprite": "",
    "weight": 60,
    "height": 4,
    "baseExperience": 112
  },
  "pokemon2": {
    "id": 2,
    "name": "charmander",
    "sprite": "",
    "weight": 85,
    "height": 6,
    "baseExperience": 62
  },
  "winner": {
    "id": 1,
    "name": "pikachu",
    "sprite": "",
    "weight": 60,
    "height": 4,
    "baseExperience": 112
  },
  "battleDate": "2025-01-26 14:30:45"
}
```

---

## 🎮 POKÉMONS POPULARES PARA TESTAR

| ID  | Nome          | Experiência | Tipo        |
|-----|---------------|-------------|-------------|
| 1   | Bulbasaur     | 64          | Grass       |
| 4   | Charmander    | 62          | Fire        |
| 7   | Squirtle      | 63          | Water       |
| 25  | Pikachu       | 112         | Electric    |
| 39  | Jigglypuff    | 95          | Normal      |
| 150 | Mewtwo        | 340         | Psychic     |
| 151 | Mew           | 300         | Psychic     |

---

## 🔧 FUNCIONALIDADES EXTRAS

### **Ver Pokémons salvos:**
```http
GET http://localhost:8080/api/pokemon
```

### **Buscar Pokémon por nome:**
```http
GET http://localhost:8080/api/pokemon/search/pikachu
```

### **Console do Banco H2:**
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:pokefight`
- Username: `sa`
- Password: (deixe vazio)

---

## ⚡ DICAS IMPORTANTES

1. **Primeira batalha demora mais**: Busca dados na PokeAPI
2. **Batalhas seguintes são rápidas**: Dados já estão salvos localmente
3. **Lógica da batalha**: 70% experiência + 30% sorte
4. **Pokémons inexistentes**: Retorna erro 400
5. **Mesmo Pokémon**: Não pode lutar contra si mesmo

---

## 🐛 RESOLUÇÃO DE PROBLEMAS

### **Erro 400 - Bad Request**
- Verifique se os IDs são números válidos
- Certifique-se que não são o mesmo Pokémon

### **Erro 404 - Not Found**
- Pokémon não existe na PokeAPI
- Use IDs entre 1-1010 (Pokémons válidos)

### **Erro 500 - Internal Server Error**
- Problema na conexão com PokeAPI
- Verifique sua conexão com internet

---

🎉 **DIVIRTA-SE TESTANDO AS BATALHAS POKÉMON!**
