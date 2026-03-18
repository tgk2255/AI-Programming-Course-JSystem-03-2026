@echo off
setlocal

set "ROOT=%~dp0"
set "BACKEND_DIR=%ROOT%backend"
set "MAVEN_REPO=%ROOT%.m2-temp"

if not exist "%MAVEN_REPO%" (
  mkdir "%MAVEN_REPO%"
)

set "MAVEN_CMD="
where mvn >nul 2>nul
if not errorlevel 1 (
  set "MAVEN_CMD=mvn"
)

if not defined MAVEN_CMD (
  if exist "C:\Users\labuser\AppData\Local\Temp\apache-maven-3.9.9\bin\mvn.cmd" (
    set "MAVEN_CMD=C:\Users\labuser\AppData\Local\Temp\apache-maven-3.9.9\bin\mvn.cmd"
  )
)

if not defined MAVEN_CMD (
  echo Nie znaleziono Mavena. Zainstaluj Maven albo przywroc lokalny pakiet w AppData\Local\Temp.
  exit /b 1
)

pushd "%BACKEND_DIR%"
call "%MAVEN_CMD%" "-Dmaven.repo.local=%MAVEN_REPO%" "-DskipTests=true" package
set "BUILD_EXIT=%ERRORLEVEL%"
popd

if not "%BUILD_EXIT%"=="0" (
  echo Build nie powiodl sie.
  exit /b %BUILD_EXIT%
)

echo Build zakonczony sukcesem.
echo Plik jar: %BACKEND_DIR%\target\JSystems-SilkyCodders-1-0.0.1-SNAPSHOT.jar

endlocal
