@echo off
set MAVEN_WRAPPER_VERSION=0.5.6
set BASE_URL=https://repo.maven.apache.org/maven2/io/takari/maven-wrapper
set WRAPPER_JAR=.mvn/wrapper/maven-wrapper.jar
set WRAPPER_JAR_URL=%BASE_URL%/%MAVEN_WRAPPER_VERSION%/maven-wrapper-%MAVEN_WRAPPER_VERSION%.jar
set WRAPPER_PROPERTIES=.mvn/wrapper/maven-wrapper.properties

mkdir .mvn\wrapper

if not exist %WRAPPER_JAR% (
  echo Downloading Maven Wrapper...
  bitsadmin.exe /transfer "MavenWrapper" %WRAPPER_JAR_URL% %WRAPPER_JAR%
)

if not exist %WRAPPER_PROPERTIES% (
  echo Creating Maven Wrapper properties...
  echo distributionUrl=%BASE_URL%/maven-wrapper-%MAVEN_WRAPPER_VERSION%.tar.gz > %WRAPPER_PROPERTIES%
)

setlocal enabledelayedexpansion
set "arguments="
:loop
if "%~1"=="" goto execute
set arguments=!arguments! %1
shift
goto loop
:execute
endlocal & call .mvn\wrapper\maven-wrapper.jar %arguments%

