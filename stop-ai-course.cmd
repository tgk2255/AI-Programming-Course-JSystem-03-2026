@echo off
setlocal

set "ROOT=%~dp0"
set "RUN_DIR=%ROOT%.run"
set "PID_FILE=%RUN_DIR%\backend.pid"

if exist "%PID_FILE%" (
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "$pidFile = '%PID_FILE%';" ^
    "$pid = Get-Content $pidFile -ErrorAction SilentlyContinue | Select-Object -First 1;" ^
    "if ($pid) { Stop-Process -Id $pid -Force -ErrorAction SilentlyContinue };" ^
    "Remove-Item $pidFile -ErrorAction SilentlyContinue"
)

pushd "%ROOT%"
if exist ".tools\docker-compose.exe" (
  call ".tools\docker-compose.exe" -f compose.yaml down
) else (
  call docker compose -f compose.yaml down
)
popd

echo Aplikacja i baza zostaly zatrzymane.

endlocal
