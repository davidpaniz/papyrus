# Instalation #
## Downloading a release ##
* download any version from http://github.com/davidpaniz/papyrus/downloads
* extract the file in a directory
* inside the extracted firectory, configure papyrus through the following comand
        java -jar setup.jar
* after answer all configurations, you can start up the server
        java -jar start

## Compiling source ##
* first of all clone the project
* Configure the build.properties with you flex SDK directory
* Copy files from /src/main/resources to /src/production/resources and modify then with your configurations
* run ant task called war <br />
        ant war
* deploy the file /target/artifacts/papyrus.war in your favorite servlet container, just like tomcat, jetty and other.