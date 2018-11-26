Elastos Carrier Java SDK
===========================

## Introduction

The Elastos Carrier Java SDK allows access to the [Elastos Carrier Native](https://github.com/elastos/Elastos.NET.Carrier.Native.SDK) API (written in C programming language)
through Java Native Interface (JNI).

The Elastos Carrier Java SDK functionalities are dependent on the Elastos Carrier Native SDK. Major changes
to the coding of the Carrier Native SDK most often result in the need to adjust the Carrier Java SDK.

Well tested, compatible versions of both the Carrier Native SDK and the Carrier Java SDK can be downloaded as [Releases](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/releases) .

If any issues are found, please [report it here](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/issues) .
In case you also know how to correct the coding or documentation, please submit a [Pull Request](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/pulls) .

To see the current status of the Elastos Carrier Java SDK, please see our [Project Roadmap](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/projects/1).

To read more about the Elastos Carrier Java SDK, please see the [Wiki](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/wiki) .

Note: The Java SDK is a work in progress and is not intended for productive use. Currently, the Maven project can only be built on a Ubuntu / Debian / Linux host.

#### Elastos Carrier Messenger Demo

To explore the features of the Carrier and the Java SDK, please see the [Elastos Messenger](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/tree/master/demo) which is implemented
using Java and JavaFX for the Graphical User Interface (GUI).
See [Screenshots](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/tree/master/demo/screenshots).

An executable .jar file of the Messenger can be downloaded from the '/demo' folder which does not require any compilation.
After testing, it is recommended to build your own from the source code provided."

**Please note, building and running of the messenger demo application is currently only possible on a Ubuntu / Debian / Linux hosts. Windows and MacOS will be added in the near future.**


## Build from source on Ubuntu / Debian / Linux host

#### 1. Install Pre-Requirements

To generate Makefiles by using **configure** or **cmake** and manage dependencies of the Carrier project, certain packages must be installed on the host before compilation.

Run the following commands to install the prerequisite utilities:

```shell
$ sudo apt-get update
$ sudo apt-get install -f default-jre openjdk-8-jdk openjfx maven build-essential autoconf automake autopoint libtool flex bison libncurses5-dev cmake
```

Download the Elastos.NET.Carrier.Native.SDK (not Elastos.Carrier.Java.SDK) repository using Git:
```shell
$ git clone https://github.com/elastos/Elastos.NET.Carrier.Native.SDK
```

#### 2. Create shared native libraries on host (Ubuntu / Debian / Linux)

To compile the project from source code for the target to run on Ubuntu / Debian / Linux, carry out the following steps:

Open a new terminal window.

Navigate to the previously downloaded folder that contains the source code of the Carrier project.

```shell
$ cd YOUR-PATH/Elastos.NET.Carrier.Native.SDK
```

Enter the 'build' folder.
```shell
$ cd build
```

Create a new folder with the target platform name, then change directory.
```shell
$ mkdir linux
$ cd linux
```

Generate the Makefile in the current directory:<br/>
Note: Please see custom options below.
```shell
$ cmake ../..
```
***
Optional (Generate the Makefile): To be able to build a distribution with a specific build type **Debug/Release**, as well as with customized install location of distributions, run the following commands:
```shell
$ cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=YOUR-INSTALL-PATH ../..
```
***

Build the program: <br/>
Note: If "make" fails due to missing permissions, use "sudo make" instead.
```shell
$ make
```


Install the program: <br/>
Note: If "make install" fails due to missing permissions, use "sudo make install" instead.
```shell
$ make install
```

Create distribution package: <br/>
Note: If "make dist" fails due to missing permissions, use "sudo make dist" instead.
```
$ make dist
```


*****************

#### 3. Merge and package shared native libraries

##### 3.1 Copy the Header files
Extract and open the distribution package of the **Carrier Native SDK** (not the Java SDK) previously created in a visual file manager, and open the 'include' folder.

Copy the files **ela_carrier.h** and **ela_session.h** .


In the downloaded **Carrier Java SDK**, navigate to the 'c_jni_src/linux/include' folder
and paste the ela_carrier.h and ela_session.h header files into the **c_jni_src/linux/include** folder.

##### 3.2 Copy the Shared Native Libraries
Open the 'lib' folder of the distribution package of the **Carrier Native SDK** (not the Java SDK) and copy the files **libcrystal.so**, **libelacarrier.so** and **libelasession.so** .


In the downloaded **Carrier Java SDK**, open src/main/resources/lib/linux and paste the files **libcrystal.so**, **libelacarrier.so** and **libelasession.so** into the lib folder.


##### 3.3 Build the program
Open a new terminal window.

***
Note: To make sure all Java related packages are installed as described in the prerequisites section, run the following commands:

```
$ sudo apt-get update
$ sudo apt-get install default-jre openjdk-8-jdk openjfx maven
```

Please make sure you have source $JAVA_HOME set. You can check with the command 'echo $JAVA_HOME' in the terminal.
If you configure JAVA_HOME now, please restart the computer (or virtual machine) to update the configuration.

***

Change directory to the **Elastos.Carrier.Java.SDK/c_jni_src/linux** folder where the CMakeLists.txt is present.
```
$ cd YOUR-PATH/Elastos.NET.Carrier.Java.SDK/c_jni_src/linux
```

Generate the required Makefile in the current directory:
```
$ sudo cmake .
```

Build the program:
```
$ sudo make
```

Package the project with Maven:
```
$ mvn clean package assembly:single
```

The output will be a .jar file (messenger.jar) which is placed in the folder '/target'.


#### 4. Start the Messenger demo

Note: For easy access, it is recommended to move the previously generated messenger.jar file to a different location.
Create a new folder and place the .jar file inside. When running the application, it will create two additional folders.
**The messenger.jar file must be started from the terminal.**

Open a new terminal.

Execute the command where the messenger.jar file has been placed:
```
$ java -jar messenger.jar
```

![Welcome](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/welcome.png)

![Chat](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/chatwindow.png)

The messenger application will start and a new address will be automatically created.
The loading indicator will disappear when the node has successfully joined the network.

Note: Please do not close the terminal in the background when the messenger is running.
It is technically possible to hide it, but currently it may be used for debugging.

***


### Windows

#### 1. Brief introduction

With CMake, Elastos Carrier can be cross-compiled to run only on Windows as target platform, while compilation is carried out on a Windows host. Both 32-bit and 64-bit target versions are supported.


#### 2. Set up Environment

**Prerequisites**:
- Visual Studio IDE is required. The Community version can be downloaded at https://visualstudio.microsoft.com/downloads/ for free.
- Download and install "Visual Studio Command Prompt (devCmd)" from https://marketplace.visualstudio.com/items?itemName=ShemeerNS.VisualStudioCommandPromptdevCmd .
- Install 'Desktop development with C++' Workload


Start the program 'Visual Studio Installer'.
***
Alternative:
Start Visual Studio IDE.
In the menu, go to "Tools >> Get Tools and Features", it will open the Visual Studio Installer.
***

Make sure 'Desktop development with C++' Workload is installed.

On the right side, make sure in the 'Installation details' all of the following are installed:
"Windows 8.1 SDK and UCRT SDK" <- might have to be selected additionally
"Windows 10 SDK (10.0.17134.0)" <- might have to be selected additionally
"VC++ 2017 version 15.9 ... tools"
"C++ Profiling tools"
"Visual C++ tools for CMake"
"Visual C++ ATL for x86 and x64"
Additional tools are optional, some additional ones are installed by default with the Workload.

After modifications, restarting of Visual Studio might be required.

To build for a 32-bit target , select `x86 Native Tools Command Console` to run building commands, otherwise, select `x64 Native Tools Command Console` for a 64-bit target.

#### 3. Build from source on Windows host

To compile the project from source code for the target to run on Windows, carry out the following steps:

In Visual Studio, open Visual Studio Command Prompt from the menu "Tools >> Visual Studio Command Prompt"
It will open a new terminal window.

Navigate to the previously downloaded folder that contains the source code of the Carrier project.

```shell
$ cd YOUR-PATH/Elastos.NET.Carrier.Native.SDK
```

Enter the 'build' folder.
```shell
$ cd build
```

Create a new folder with the target platform name, then change directory.
```shell
$ mkdir win
$ cd win
```

Generate the Makefile in the current directory:
```shell
$ cmake -G "NMake Makefiles" -DCMAKE_INSTALL_PREFIX=outputs ..\..
```


Build the program: <br/>
Note: If "make" fails due to missing permissions, use "sudo make" instead.
```shell
$ nmake
```



Install the program: <br/>
Note: If "make install" fails due to missing permissions, use "sudo make install" instead.
```shell
$ nmake install
```


Create distribution package: <br/>
Note: If "make dist" fails due to missing permissions, use "sudo make dist" instead.
```
$ nmake dist
```

***********

#### 4. Merge and package shared native libraries

##### 4.1 Copy the Header files
Extract and open the distribution package of the **Carrier Native SDK** (not the Java SDK) previously created in a visual file manager, and open the 'include' folder.

Copy the files **ela_carrier.h** and **ela_session.h** .


In the downloaded **Carrier Java SDK**, navigate to the 'c_jni_src/windows/include' folder
and paste the ela_carrier.h and ela_session.h header files into the **c_jni_src/windows/include** folder.

##### 4.2 Copy the Shared Native Libraries
Open the '/bin' folder of the distribution package of the **Carrier Native SDK** (not the Java SDK) and copy the files **crystal.dll**, **elacarrier.dll**, **elasession.dll** and **pthreadVC2.dll**.


In the downloaded **Carrier Java SDK**, open src/main/resources/lib/windows and paste the files **crystal.dll**, **elacarrier.dll**, **elasession.dll** and **pthreadVC2.dll** into the lib folder.


##### 4.3 Build the program

**Prerequisites**
- Java JDK (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- Maven (https://maven.apache.org/download.cgi)
- CMake (https://cmake.org/download/)
- C compiler (https://mingw-w64.org/doku.php/start -> 'Downloads' -> 'Sources' section (Download hosted on SourceForge) )

***
Note: Please make sure you have source $JAVA_HOME set. You can check with the command 'echo %JAVA_HOME%' in the terminal.
If you configure JAVA_HOME now, please restart the computer (or virtual machine) to update the configuration.

***


Open a new Command Line.

Change directory to the **Elastos.Carrier.Java.SDK/c_jni_src/windows** folder where the CMakeLists.txt is present.
```
$ cd YOUR-PATH/Elastos.NET.Carrier.Java.SDK/c_jni_src/windows
```

Generate the required Makefile in the current directory:
```
$ cmake .
```

Build the program:
```
$ mingw32-make
```
Note: The mingw32-make command will build both the 32 bit and the 64 bit versions.

Package the project with Maven:
```
$ mvn clean package assembly:single
```

The output will be a .jar file (messenger.jar) which is placed in the folder '/target'.


#### 5. Start the Messenger demo

Note: For easy access, it is recommended to move the previously generated messenger.jar file to a different location.
Create a new folder and place the .jar file inside. When running the application, it will create two additional folders.
**The messenger.jar file must be started from the terminal.**

Open a new terminal.

Execute the command where the messenger.jar file has been placed:
```
$ java -jar messenger.jar
```

![Welcome](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/welcome.png)

![Chat](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/chatwindow.png)

The messenger application will start and a new address will be automatically created.
The loading indicator will disappear when the node has successfully joined the network.

Note: Please do not close the terminal in the background when the messenger is running.
It is technically possible to hide it, but currently it may be used for debugging.

***

***********



## Current limitations

**Not yet accepted incoming friend requests cannot be listed by command**
In case a friend request is sent by someone to our address while us being offline,
the pending request cannot be listed by command to force an instant check. However, pending requests are
updated by the network with some latency and are then displayed when us being online for a while.
The Carrier Native library does not (yet) support this function.

**Sent, but not yet accepted friend requests cannot be sorted as 'Pending'**
When an invitation is sent, the user is added to the list of friends instantly and their status is kept as 'offline'
until they accept the request (and are online).
The Carrier Native library does not (yet) support this function.

**Messages are not stored offline**
When a conversation window is closed, the content of the conversation is not stored anywhere.
The Carrier Native library does not (yet) support this function.


## Contribution

We welcome contributions to the Elastos Carrier Java SDK Project.

## Acknowledgments

A sincere thank you to all teams and projects that we rely on directly or indirectly.

## License
This project is licensed under the terms of the [MIT license](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/LICENSE).
