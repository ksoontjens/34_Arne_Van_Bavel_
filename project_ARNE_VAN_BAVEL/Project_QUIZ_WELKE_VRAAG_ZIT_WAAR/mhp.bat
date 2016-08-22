@echo off
REM parameter description:
REM   mhp.bat [-debug][-release] [RIRoot [options]]
REM   -debug: forces the jar files located in %RUNTIME%classes to be used
REM   -release: forces the jar files located in %RUNTIME%obfuscated to be used
REM   RIRoot: the path to the IRT MHP RI root directory (default = ..)
REM   -select locator: selects a service with the given locator
REM   -app application: adds the given application to the app.database.
REM         (The format of application is described in cfg/Applications.cfg)
REM   -run AppID: autostarts the application with the given AppID
REM         (The format of AppID is hex with the format: orgId.appId)
REM   -fullscreen turns the presentation to full screen mode automatically
REM         (If not set the RI is presented in window mode)
REM example:
REM   mhp.bat .. -select dvb://1.44d.6dca -addapp "HelloDVB;2=Hello World;dvb://0.0.1" -runapp 0.2
REM   (set RIroot to parent directory, select ARD service,
REM    add HelloDVB to the application database and autostart it)

REM setup RI jar file location
IF "%1"=="-debug" SET RIJARS=%2/runtime/classes/
IF "%1"=="-debug" IF "%2"=="" SET RIJARS=classes/
IF "%1"=="-debug" %0 %2 %3 %4 %5 %6 %7 %8 %9
IF "%1"=="-release" SET RIJARS=%2/runtime/obfuscated/
IF "%1"=="-release" IF "%2"=="" SET RIJARS=obfuscated/
IF "%1"=="-release" %0 %2 %3 %4 %5 %6 %7 %8 %9

REM setting current path, default is current directory
SET RIROOT=%1/
SET RUNTIME=%RIROOT%TT-MHP-Browser/
if "%1"=="" SET RIROOT=../
if "%1"=="" SET RUNTIME=

REM setup RI jar file location (if not yet set)
REM IF "%RIJARS%"=="" IF EXIST %RUNTIME%obfuscated/*.jar SET RIJARS=%RUNTIME%obfuscated/
REM IF "%RIJARS%"=="" IF EXIST %RUNTIME%classes/*.jar SET RIJARS=%RUNTIME%classes/
SET RIJARS=%RUNTIME%obfuscated/

echo Using %RIJARS% archives

REM setting system pathes...
IF EXIST %JAVA_HOME% SET JAVAHOME=%JAVA_HOME%
IF EXIST %RIROOT%jre_130 SET JAVAHOME=%RIROOT%jre_130
IF EXIST %RUNTIME%jre_130 SET JAVAHOME=%RUNTIME%jre_130
IF EXIST %RIROOT%jre_131 SET JAVAHOME=%RIROOT%jre_131
IF EXIST %RUNTIME%jre_131 SET JAVAHOME=%RUNTIME%jre_131
IF EXIST %RIROOT%jre_140 SET JAVAHOME=%RIROOT%jre_140
IF EXIST %RUNTIME%jre_140 SET JAVAHOME=%RUNTIME%jre_140
IF EXIST %RIROOT%jre_141 SET JAVAHOME=%RIROOT%jre_141
IF EXIST %RUNTIME%jre_141 SET JAVAHOME=%RUNTIME%jre_141
IF EXIST %RIROOT%jre_142 SET JAVAHOME=%RIROOT%jre_142
IF EXIST %RUNTIME%jre_142 SET JAVAHOME=%RUNTIME%jre_142
IF EXIST %RIROOT%jre_150 SET JAVAHOME=%RIROOT%jre_150
IF EXIST %RUNTIME%jre_150 SET JAVAHOME=%RUNTIME%jre_150
IF EXIST %RIROOT%jre_151 SET JAVAHOME=%RIROOT%jre_151
IF EXIST %RUNTIME%jre_151 SET JAVAHOME=%RUNTIME%jre_151
IF EXIST %JAVAHOME% SET JAVABIN=%JAVAHOME%/bin/

REM setting RI pathes...
SET DLLJAVA=%JAVABIN%
SET DLLHOME=%RUNTIME%dll
SET PREPENDHOME=%RIJARS%boot.jar
SET POLICY=%RUNTIME%cfg/RIBoot.pol
SET RICLASSPATH=%RIJARS%platform.jar;%RIJARS%mhpri.jar;%RIJARS%optional.jar;%RIJARS%navigator.jar;%RUNTIME%lib/Security/iaik_jce.jar;%RUNTIME%lib/Security/jsse.jar;%RUNTIME%lib/Security/jnet.jar;%RUNTIME%lib/Security/jcert.jar;%RUNTIME%lib/XML/crimson.jar
SET RIADDONS= -addon de.irt.mhp.optional.AddOn

REM Quicktime Add-On
SET DLLJAVA=%DLLJAVA%;%windir%/system32;%RUNTIME%dll/quicktime
SET PREPENDHOME=%RIJARS%quicktime/quicktime.system.jar;%windir%/system32/QTJava.zip;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%quicktime/quicktime.addon.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.quicktime.AddOn

REM Windows Media Player Add-On
SET DLLJAVA=%DLLJAVA%;%windir%/system32;%RUNTIME%dll/wmp
SET PREPENDHOME=%RIJARS%wmp/wmp.system.jar;%windir%/system32/QTJava.zip;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%wmp/wmp.addon.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.wmp.AddOn

REM CASSIC QoS Add-On
SET PREPENDHOME=%RIJARS%cassic/cassic.system.jar;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%cassic/cassic.addon.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.cassic.AddOn
SET DLLHOME=%RUNTIME%dll/cassic;%DLLHOME%

REM IP-Tuner Add-On
SET PREPENDHOME=%RIJARS%iptuner/iptuner.system.jar;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%iptuner/iptuner.addon.jar;%RUNTIME%lib/iptuner/xml-apis.jar;%RUNTIME%lib/iptuner/xercesImpl.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.iptuner.AddOn
SET DLLHOME=%RUNTIME%dll/iptuner;%DLLHOME%

REM Personal data Recorder (PDR) Add-On
SET PREPENDHOME=%RIJARS%pdr/pdr.system.jar;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%pdr/pdr.addon.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.pdr.AddOn
SET DLLHOME=%RUNTIME%dll/pdr;%DLLHOME%

REM TV-Anytime Add-On
SET PREPENDHOME=%RIJARS%tva/tva.system.jar;%PREPENDHOME%
SET RICLASSPATH=%RIJARS%tva/tva.addon.jar;%RUNTIME%lib/tva/TVAnytimeBBC.jar;%RUNTIME%lib/tva/EwSchemaCompiler.jar;%RUNTIME%lib/tva/EwDVBBaseDecoder.jar;%RUNTIME%lib/tva/EwDVBSAXLikeDecoder.jar;%RUNTIME%lib/tva/EwDVBHelpers.jar;%RICLASSPATH%
SET RIADDONS=%RIADDONS% -addon de.irt.mhp.tva.AddOn
SET DLLHOME=%RUNTIME%dll/tva;%DLLHOME%

REM setting path
SET PATH=%DLLHOME%;%PATH%

REM finish the installation process on the first run
if not exist %0.JVMParams.txt call install

REM create JVM parameters for MHP-RI debugging
echo            -Xmx128M "-Dsun.boot.library.path=%DLLJAVA%" "-Djava.library.path=%DLLHOME%" "-DMHP.root=%RUNTIME%" "-Xbootclasspath/p:%PREPENDHOME%" "-Djava.security.policy=%POLICY%" -classpath "%RICLASSPATH%" de.irt.mhp.boot.MhpRI%RIADDONS%>%0.JVMParams.txt
REM execute MHP-RI
@echo on
REM "%JAVABIN%java" -classpath %RUNTIME%obfuscated\extern MHPTesterXlet
"%JAVABIN%java" -Xmx128M "-Dsun.boot.library.path=%DLLJAVA%" "-Djava.library.path=%DLLHOME%" "-DMHP.root=%RUNTIME%" "-Xbootclasspath/p:%PREPENDHOME%" "-Djava.security.policy=%POLICY%" -classpath "%RICLASSPATH%" de.irt.mhp.boot.MhpRI%RIADDONS% %2 %3 %4 %5 %6 %7 %8 %9

REM if "%1"=="" pause
