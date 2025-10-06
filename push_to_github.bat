@echo off
cd /d "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"
echo Adicionando arquivos...
git add .
echo Fazendo commit...
git commit -m "docs: adiciona diagrama arquitetural draw.io e atualiza README"
echo Enviando para GitHub...
git push origin main
echo Operacao concluida!
pause

