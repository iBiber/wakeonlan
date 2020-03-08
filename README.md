A wake on lan (WOL) micro service that can be called via HTTP GET request.

If it is started you can use your browser to send a "magic packet" (WOL message) into the network by opening this URL:
http://yourserver:8080/wakeonlan?broadcastIp=192.168.0.255&macAddress=00:80:41:ae:fd:7e

# Requirements
* At least Java 8
* Maven

# Build the project
Just enter into a console: 
``` mvn clean install ```

# Start the service
You can start the service after you build the project like this (from project root):
``` java -jar ./target/service-1.0.0-SNAPSHOT-runner.jar ```

If you want to listen the service on another port you can define it like:
``` java -jar -Dquarkus.http.port=8090 ./target/service-1.0.0-SNAPSHOT-runner.jar ```

# Develop the service
With this command you can start the service in a development mode:
``` mvn quarkus:dev ```

## With Eclipse
In eclipse you can add a "Maven Build" launch configuration and setting this goals to start the service:
``` compile quarkus:dev ```
