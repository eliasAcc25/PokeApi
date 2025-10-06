@echo off
echo === EXECUTANDO PUSH PARA GITHUB ===
cd /d "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

echo Verificando status...
git status

echo Adicionando arquivos...
git add .

echo Fazendo commit...
git commit -m "docs: adiciona diagrama arquitetural draw.io personalizado no inicio do README"

echo Sincronizando com remoto...
git fetch origin

echo Fazendo push...
git push origin main

echo === OPERACAO CONCLUIDA ===
echo Verifique em: https://github.com/eliasAcc25/PokeApi
pause
