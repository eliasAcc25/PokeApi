@echo off
echo === FORÇANDO PUSH PARA GITHUB ===
cd /d "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

echo Sincronizando com repositorio remoto...
git pull origin main --rebase

echo Adicionando arquivos...
git add .

echo Fazendo commit...
git commit -m "docs: adiciona diagrama Mermaid arquitetural no inicio do README"

echo Enviando para GitHub...
git push origin main --force-with-lease

echo === PUSH CONCLUIDO ===
echo Verifique em: https://github.com/eliasAcc25/PokeApi
echo Pressione qualquer tecla para fechar...
pause
