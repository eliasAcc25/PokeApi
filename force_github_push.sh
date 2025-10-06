#!/bin/bash
cd "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

echo "=== FORÇANDO PUSH PARA GITHUB ==="

# Adicionar todas as alterações
git add .

# Fazer commit
git commit -m "docs: adiciona diagrama arquitetural draw.io personalizado no início do README"

# Verificar diferenças com o remoto
git fetch origin

# Push com force-with-lease para resolver conflitos
git push origin main --force-with-lease

echo "=== PUSH CONCLUÍDO ==="
