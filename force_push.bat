@echo off
cd /d "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

echo === Verificando status atual ===
git status

echo === Fazendo backup das alteracoes locais ===
git stash push -m "backup antes do pull"

echo === Sincronizando com repositorio remoto ===
git pull origin main --rebase

echo === Restaurando alteracoes locais ===
git stash pop

echo === Adicionando arquivos ===
git add .

echo === Fazendo commit ===
git commit -m "docs: adiciona diagrama arquitetural draw.io e atualiza README"

echo === Enviando para GitHub ===
git push origin main

echo === Operacao concluida ===
pause
