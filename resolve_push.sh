#!/usr/bin/env bash

# Script para resolver push rejeitado
echo "=== Resolvendo problema de push rejeitado ==="

# Navegue para o diretório do projeto
cd "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

# Primeiro, vamos verificar o que está acontecendo
echo "Status atual:"
git log --oneline -5

echo "=== Fazendo pull com rebase para sincronizar ==="
git pull origin main --rebase

echo "=== Adicionando todas as alterações ==="
git add .

echo "=== Verificando o que será commitado ==="
git status

echo "=== Fazendo commit ==="
git commit -m "docs: adiciona diagrama arquitetural draw.io e atualiza README"

echo "=== Tentando push novamente ==="
git push origin main

echo "=== Operação concluída ==="
