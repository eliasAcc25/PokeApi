# 🎮 COMO JOGAR POKÉFIGHT NO POSTMAN - PASSO A PASSO

## 📥 **PASSO 1: IMPORTAR A COLLECTION**

1. **Abra o Postman**
2. **Clique em "Import"** (botão laranja no canto superior esquerdo)
3. **Selecione "Upload Files"**
4. **Navegue até:** `C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi\`
5. **Selecione:** `PokeFight_Postman_Collection.json`
6. **Clique "Import"**

✅ **Resultado:** Você verá "PokéFight API - Batalhas Pokémon" na barra lateral

---

## 🎯 **PASSO 2: PRIMEIRA BATALHA - PIKACHU VS CHARMANDER**

### **2.1 - Configurar a Batalha:**
1. **Expanda** "🥊 BATALHAS" na barra lateral
2. **Clique** em "1. Criar Batalha - Pikachu vs Charmander"
3. **Verifique** se está configurado:
   - Método: **POST**
   - URL: `http://localhost:8080/api/batalhas`
   - Headers: `Content-Type: application/json`
   - Body (JSON):
   ```json
   {
     "pokemon1Id": 25,
     "pokemon2Id": 4
   }
   ```

### **2.2 - Executar a Batalha:**
4. **Clique no botão azul "Send"**
5. **Aguarde 2-3 segundos** (busca na PokeAPI pela primeira vez)

### **2.3 - Ver o Resultado:**
```json
{
  "id": 1,
  "pokemon1": {
    "name": "pikachu",
    "baseExperience": 112
  },
  "pokemon2": {
    "name": "charmander", 
    "baseExperience": 62
  },
  "winner": {
    "name": "pikachu" ← VENCEDOR!
  },
  "battleDate": "2025-01-26 14:45:30"
}
```

---

## 🔍 **PASSO 3: VER RESULTADO DA BATALHA**

1. **Clique** em "4. Ver Resultado de uma Batalha"
2. **URL será:** `http://localhost:8080/api/batalhas/1`
3. **Clique "Send"**
4. **Veja os detalhes** da batalha que você acabou de criar

---

## 📋 **PASSO 4: VER HISTÓRICO COMPLETO**

1. **Clique** em "5. Listar Todas as Batalhas"
2. **URL será:** `http://localhost:8080/api/batalhas`
3. **Clique "Send"**
4. **Veja todas** as batalhas já realizadas

---

## 🆚 **PASSO 5: MAIS BATALHAS ÉPICAS**

### **Batalha 2 - Bulbasaur vs Squirtle:**
- **Clique:** "2. Criar Batalha - Bulbasaur vs Squirtle"
- **Send:** Batalha dos starters originais!

### **Batalha 3 - Mewtwo vs Mew (Lendários):**
- **Clique:** "3. Criar Batalha - Mewtwo vs Mew (Legendários)"  
- **Send:** Batalha épica entre lendários!

### **Batalha Personalizada:**
- **Clique:** "9. Batalha Personalizada - Seu Teste"
- **Edite o Body** com seus IDs favoritos:
```json
{
  "pokemon1Id": 6,    ← Charizard
  "pokemon2Id": 9     ← Blastoise
}
```
- **Send:** Sua batalha customizada!

---

## 🎮 **POKÉMONS POPULARES PARA TESTAR**

| ID  | Nome       | Experiência | Descrição           |
|-----|------------|-------------|---------------------|
| 1   | Bulbasaur  | 64          | Starter Grama       |
| 4   | Charmander | 62          | Starter Fogo        |
| 7   | Squirtle   | 63          | Starter Água        |
| 25  | Pikachu    | 112         | Mascote Pokémon     |
| 6   | Charizard  | 267         | Dragão de Fogo      |
| 9   | Blastoise  | 239         | Tartaruga d'Água    |
| 150 | Mewtwo     | 340         | Lendário Psychic    |
| 151 | Mew        | 300         | Lendário Original   |

---

## 🏆 **COMO FUNCIONA A LÓGICA DE BATALHA**

### **Formula do Vencedor:**
- **70% Experiência** (baseExperience do Pokémon)  
- **30% Sorte** (fator aleatório)
- **Pokémon com maior pontuação final vence!**

### **Exemplo:**
- **Pikachu:** 112 exp × 0.7 + sorte × 0.3 = 78.4 + 30 = **108.4**
- **Charmander:** 62 exp × 0.7 + sorte × 0.3 = 43.4 + 15 = **58.4**
- **🏆 Pikachu vence!**

---

## 🔧 **FUNCIONALIDADES EXTRAS**

### **Ver Pokémons Salvos:**
- **Clique:** "6. Listar Pokémons Salvos"
- **Veja** todos os Pokémons que já foram buscados na PokeAPI

### **Buscar Pokémon por Nome:**
- **Clique:** "7. Buscar Pokémon por Nome"  
- **Mude** "pikachu" por outro nome na URL
- **Exemplo:** `.../search/charizard`

### **Teste de Erro:**
- **Clique:** "10. Teste de Erro - Mesmo Pokémon"
- **Veja** como o sistema previne um Pokémon lutar contra si mesmo

---

## 🎯 **DICAS PARA JOGAR**

### ✅ **Boas Práticas:**
1. **Primeira batalha demora mais** (busca na PokeAPI)
2. **Batalhas seguintes são instantâneas** (dados salvos)
3. **Use IDs entre 1-1010** (Pokémons válidos)
4. **Experimente diferentes combinações!**

### ❌ **Evite:**
- IDs iguais (mesmo Pokémon)
- IDs inválidos (maiores que 1010)  
- IDs negativos ou zero

---

## 🎊 **EXEMPLOS DE BATALHAS DIVERTIDAS**

```json
// Batalha dos Starters Finais
{"pokemon1Id": 3, "pokemon2Id": 6}    // Venusaur vs Charizard
{"pokemon1Id": 6, "pokemon2Id": 9}    // Charizard vs Blastoise  
{"pokemon1Id": 9, "pokemon2Id": 3}    // Blastoise vs Venusaur

// Batalha de Lendários
{"pokemon1Id": 150, "pokemon2Id": 144}  // Mewtwo vs Articuno
{"pokemon1Id": 145, "pokemon2Id": 146}  // Zapdos vs Moltres

// Batalha Clássica
{"pokemon1Id": 25, "pokemon2Id": 26}   // Pikachu vs Raichu
{"pokemon1Id": 39, "pokemon2Id": 40}   // Jigglypuff vs Wigglytuff
```

---

🎮 **AGORA É SÓ IMPORTAR A COLLECTION E COMEÇAR A JOGAR!**

**Lembre-se:** O sistema precisa estar rodando em `http://localhost:8080` para funcionar!
