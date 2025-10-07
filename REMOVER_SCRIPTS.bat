cd "C:\Users\elias.andre.torres\Downloads\pokeApi\pokeApi"

REM Remove arquivos localmente
del /F /Q force_push.bat 2>nul
del /F /Q force_github_push.sh 2>nul
del /F /Q git_push.sh 2>nul
del /F /Q push_agora.bat 2>nul
del /F /Q push_final.bat 2>nul
del /F /Q push_to_github.bat 2>nul
del /F /Q resolve_push.sh 2>nul

REM Remove do Git e faz commit
git rm -f force_push.bat force_github_push.sh git_push.sh push_agora.bat push_final.bat push_to_github.bat resolve_push.sh 2>nul
git add -A
git commit -m "chore: remove todos os scripts temporarios"
git push origin main

echo Arquivos removidos com sucesso!
pause
