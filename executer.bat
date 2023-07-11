@echo off

echo Starting the application...
start "" /B cmd /C "mvn spring-boot:run"

echo Waiting for the application to start...
timeout /t 10 >nul

echo Opening the default browser...
start "" http://localhost:8080 >nul

exit
