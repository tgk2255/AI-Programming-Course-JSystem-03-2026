@echo off
setlocal

set "ROOT=%~dp0"
set "BACKEND_DIR=%ROOT%backend"
set "JAR_PATH=%BACKEND_DIR%\target\JSystems-SilkyCodders-1-0.0.1-SNAPSHOT.jar"
set "RUN_DIR=%ROOT%.run"
set "PID_FILE=%RUN_DIR%\backend.pid"
set "LOG_FILE=%RUN_DIR%\backend.log"

if not exist "%RUN_DIR%" (
  mkdir "%RUN_DIR%"
)

where docker >nul 2>nul
if errorlevel 1 (
  echo Brak polecenia docker. Uruchom Docker Desktop i sprobuj ponownie.
  exit /b 1
)

where java >nul 2>nul
if errorlevel 1 (
  echo Brak polecenia java. Zainstaluj Java 21 i sprobuj ponownie.
  exit /b 1
)

if not exist "%JAR_PATH%" (
  echo Nie znaleziono pliku %JAR_PATH%
  echo Najpierw zbuduj aplikacje poleceniem: build-ai-course.cmd
  exit /b 1
)

pushd "%ROOT%"
if exist ".tools\docker-compose.exe" (
  call ".tools\docker-compose.exe" -f compose.yaml up -d
) else (
  call docker compose -f compose.yaml up -d
)
if errorlevel 1 (
  popd
  echo Nie udalo sie uruchomic PostgreSQL.
  exit /b 1
)
popd

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "try { $response = Invoke-WebRequest -UseBasicParsing http://localhost:8080 -TimeoutSec 3; if ($response.StatusCode -ge 200) { Write-Host 'Backend juz odpowiada na porcie 8080.'; exit 0 } } catch { exit 1 }"
if not errorlevel 1 (
  echo Frontend + backend: http://localhost:8080
  echo PostgreSQL dziala w Dockerze.
  endlocal
  exit /b 0
)

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$pidFile = '%PID_FILE%';" ^
  "$existingPid = $null;" ^
  "if (Test-Path $pidFile) { $existingPid = (Get-Content $pidFile -ErrorAction SilentlyContinue | Select-Object -First 1) };" ^
  "if ($existingPid) {" ^
  "  $existingProcess = Get-Process -Id $existingPid -ErrorAction SilentlyContinue;" ^
  "  if ($existingProcess) { Write-Host 'Backend juz dziala.'; exit 0 }" ^
  "};" ^
  "$process = Start-Process -FilePath 'java' -ArgumentList '-jar','%JAR_PATH%' -WorkingDirectory '%BACKEND_DIR%' -RedirectStandardOutput '%LOG_FILE%' -RedirectStandardError '%LOG_FILE%' -PassThru;" ^
  "Set-Content -Path $pidFile -Value $process.Id;" ^
  "Write-Host ('Backend uruchomiony. PID=' + $process.Id)"

if errorlevel 1 (
  echo Nie udalo sie uruchomic backendu.
  exit /b 1
)

echo Aplikacja startuje.
echo Frontend + backend: http://localhost:8080
echo Log backendu: %LOG_FILE%

endlocal
