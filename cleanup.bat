@echo off
echo Cerrando procesos de Android Studio...
taskkill /F /IM java.exe 2>nul
taskkill /F /IM adb.exe 2>nul
taskkill /F /IM gradle.exe 2>nul

echo Esperando 2 segundos...
timeout /t 2 /nobreak >nul

echo Eliminando carpetas build...
rmdir /s /q app\build 2>nul
rmdir /s /q build 2>nul
rmdir /s /q .gradle 2>nul

echo Limpieza completada!
pause
