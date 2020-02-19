FROM java:8
VOLUME /tmp
ADD /target/my-bank-service.jar app.jar
cmd java -jar app.jar
