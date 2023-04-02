FROM openjdk:17
EXPOSE 8080
ARG APP_NAME="ewallet-0.0.1-SNAPSHOT"
ARG APP_VERSION="0.0.1"
ARG JAR_FILE="/target/${APP_NAME}.jar"
COPY ${JAR_FILE} ewallet-0.0.1.jar
ENTRYPOINT ["java","-jar","/ewallet-0.0.1.jar"]