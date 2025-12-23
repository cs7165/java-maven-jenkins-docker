# Java Maven Jenkins Docker CI/CD Project

## Tech Stack
- Java 17
- Maven
- Jenkins
- Docker

## Run Locally
mvn clean package
java -jar target/java-devops-app-1.0.jar

## Docker
docker build -t java-devops-app .
docker run java-devops-app

## Jenkins
Create a pipeline job and use Jenkinsfile
