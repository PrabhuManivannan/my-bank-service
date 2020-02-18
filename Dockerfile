FROM frolvlad/alpine-java
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} my-bank-service.jar
RUN sh -c 'touch /my-bank-service.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /my-bank-service.jar" ]