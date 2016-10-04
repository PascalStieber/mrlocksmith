FROM ewolff/docker-java
ADD target/mrlocksmith-server-authorization-0.0.1-SNAPSHOT.jar .
ADD src/main/resources/keystore.jks
CMD /usr/bin/java -Xmx600m -Xms600m -jar mrlocksmith-server-authorization-0.0.1-SNAPSHOT.jar
EXPOSE 8080