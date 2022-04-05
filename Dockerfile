FROM openjdk:17.0.2-jdk-slim
ARG JAR_FILE=build/libs/*jar
WORKDIR /app
COPY users.csv .
COPY rentals.csv .
COPY Movies.csv .
COPY British_Movies.csv .
COPY US_Movies.csv .
COPY ${JAR_FILE} app/app.jar
EXPOSE 7070
CMD ["java","-jar","app/app.jar"]