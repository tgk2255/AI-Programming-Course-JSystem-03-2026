@echo off
setlocal EnableDelayedExpansion

if "%PGDATA%"=="" set "PGDATA=C:\pgsql\data"
if "%POSTGRES_USER%"=="" set "POSTGRES_USER=postgres"
if "%POSTGRES_DB%"=="" set "POSTGRES_DB=%POSTGRES_USER%"

if not exist "%PGDATA%" mkdir "%PGDATA%"
icacls "%PGDATA%" /grant "%USERNAME%":(OI)(CI)F >NUL

if not exist "%PGDATA%\PG_VERSION" (
    initdb -U "%POSTGRES_USER%" -E UTF8 --no-locale -D "%PGDATA%"

    >>"%PGDATA%\pg_hba.conf" echo.
    >>"%PGDATA%\pg_hba.conf" echo host all all all md5

    pg_ctl -U "%POSTGRES_USER%" -D "%PGDATA%" -w start

    if not "%POSTGRES_PASSWORD%"=="" (
        psql -v ON_ERROR_STOP=1 --username "%POSTGRES_USER%" --no-password --dbname postgres -c "ALTER USER \"%POSTGRES_USER%\" WITH PASSWORD '%POSTGRES_PASSWORD%';"
    )

    if /I not "%POSTGRES_DB%"=="postgres" (
        psql -v ON_ERROR_STOP=1 --username "%POSTGRES_USER%" --no-password --dbname postgres -c "CREATE DATABASE \"%POSTGRES_DB%\";"
    )

    for %%f in (C:\docker-entrypoint-initdb.d\*.sql) do (
        echo psql: running %%f
        psql -v ON_ERROR_STOP=1 --username "%POSTGRES_USER%" --no-password --dbname "%POSTGRES_DB%" -f "%%f"
    )

    pg_ctl -U "%POSTGRES_USER%" -D "%PGDATA%" -m fast -w stop
)

"C:\pgsql\bin\postgres.exe" -D "%PGDATA%"
