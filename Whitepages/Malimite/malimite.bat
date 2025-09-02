@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Malimite startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set "JAVAEXE=java.exe"
%JAVAEXE% -version 2>&1 | findstr /i "version" > tmp.txt
for /f "tokens=3" %%g in ('type tmp.txt') do (
    set "JAVA_VERSION=%%g"
)

@rem Extract just the major version number
for /f "delims=._ tokens=1" %%v in ("%JAVA_VERSION%") do set "JAVA_MAJOR_VERSION=%%v"

@rem Clean up temporary file
del tmp.txt

@rem Convert "1.8" style to "8", or use directly for versions 9 and above
set "JAVA_MAJOR_VERSION=%JAVA_MAJOR_VERSION:"=%"
if "%JAVA_MAJOR_VERSION:~0,2%"=="1." set "JAVA_MAJOR_VERSION=%JAVA_MAJOR_VERSION:~2,1%"
if %JAVA_MAJOR_VERSION% LSS 11 (
    echo This application requires Java 11 or higher.
    echo Current version: Java %JAVA_MAJOR_VERSION%
    pause
    exit /b
)

@rem Get the directory where this batch file is located
set "SCRIPT_DIR=%~dp0"

@rem Change to the script directory so the application can find DecompilerBridge  
cd /d "%SCRIPT_DIR%"

:: Run the JAR file
echo Starting Malimite...
"%JAVAEXE%" -jar Malimite-1-2.jar
