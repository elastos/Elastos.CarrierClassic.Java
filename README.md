Elastos Carrier Java SDK
===========================

## Introduction

The Elastos Carrier Java SDK allows access to the [Elastos Carrier Native](https://github.com/elastos/Elastos.NET.Carrier.Native.SDK) API (written in C programming language)
through Java Native Interface (JNI).

To explore the features of the Carrier and the Java SDK, please see the Elastos Messenger which is implemented
using Java and JavaFX for the Graphical User Interface (GUI).

The Elastos Carrier is a decentralized and encrypted peer-to-peer (P2P) communication framework that routes network traffic between virtual machines and Decentralized Applications (DApps).

The authentication process of peer nodes utilizes the Elastos Decentralized ID (DID) sidechain. （TODO）

Note: The Java SDK is a work in progress and is not intended for productive use. Currently, the Maven project can only be built on a Ubuntu / Debian / Linux host.

**An executable .jar file of the Messenger can be downloaded from the '/demo' folder which does not require any compilation.
After testing, it is recommended to build your own from the source code provided."**

**Please note, building and running of the messenger demo application is currently only possible on a Ubuntu / Debian / Linux hosts. Windows and MacOS will be added in the near future.**


## Build from source on Ubuntu / Debian / Linux host

#### 1. Install Pre-Requirements

To generate Makefiles by using **configure** or **cmake** and manage dependencies of the Carrier project, certain packages must be installed on the host before compilation.

Run the following commands to install the prerequisite utilities:

```shell
$ sudo apt-get update
$ sudo apt-get install -f default-jre openjdk-8-jdk openjfx mvn build-essential autoconf automake autopoint libtool flex bison libncurses5-dev cmake
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

## 3. Merge and package shared native libraries


Extract and open the distribution package (in visual file manager), and open the 'include' folder.

Copy the files ela_carrier.h and ela_session.h .


In the downloaded Java SDK, open c_jni_src/include folder
and paste the ela_carrier.h and ela_session.h header files into the 'include' folder.


Open the 'lib' folder from the distribution package and copy the files libcrystal.so, libelacarrier.so and libelasession.so .


In the downloaded Java SDK, open src/main/resources/lib and paste the files libcrystal.so, libelacarrier.so and libelasession.so
into the lib folder.


Open a new terminal window.

***
Note: Please make sure you have source $JAVA_HOME set. You can check with the command 'echo $JAVA_HOME' in the terminal.
If you configure JAVA_HOME now, please restart the computer to update the configuration.
***

Change directory to the Elastos.Carrier.Java.SDK folder where the MakeLists.txt is present.


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


## 4. Start the Messenger demo

Note: For easy access, it is recommended to move the previously generated messenger.jar file to a different location.
Create a new folder and place the .jar file inside. When running the application, it will create two additional folders.
**The messenger.jar file must be started from the terminal.**

Open a new terminal.

Execute the command where the messenger.jar file has been placed:
```
java -jar messenger.jar
```

The messenger application will start and a new address will be automatically created.
The loading indicator will disappear when the node has successfully joined the network.

Note: Please do not close the terminal in the background when the messenger is running.
It is technically possible to hide it, but currently it may be used for debugging.

***


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
