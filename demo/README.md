#### Introduction:
The Messenger Demo is a precompiled version and can be started with a single command on Windows and Ubuntu / Debian / Linux hosts for evaluation purposes.


#### Prerequisites:

**On Windows:**
Java must be installed.
In case Java is already installed, please check the version with the 'java -version' command, Java version 8 is recommended.
Java 11 does not contain the JavaFX SDK and must be additionally installed. (https://www.oracle.com/technetwork/java/javafx2-archive-download-1939373.html).

**On Ubuntu / Debian / Linux:**
Java and JavaFX must be installed.
In case Java is already installed, please check the version with the 'java -version' command, Java version 8 is recommended.
Java 11 does not contain the JavaFX SDK and must be additionally installed.

```
$ sudo apt-get install -f default-jre openjdk-8-jdk openjfx
```

#### Start the Messenger demo

Note: For easy access, it is recommended to move the previously generated messenger.jar file to a different location.
Create a new folder and place the .jar file inside. When running the application, it will create two additional folders.
**The messenger.jar file must be started from the terminal.**


Open a new terminal.

Execute the command where the messenger.jar file has been placed:
```
$ java -jar messenger.jar
```

![Welcome](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/Windows/welcome.png)

![Chat](https://github.com/elastos/Elastos.NET.Carrier.Java.SDK/blob/master/demo/screenshots/Windows/chatwindow.png)

The messenger application will start and a new address will be automatically created.
The loading indicator will disappear when the node has successfully joined the network.

Note: Please do not close the terminal in the background when the messenger is running.
It is technically possible to hide it, but currently it may be used for debugging.
