pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Code already checked out from SCM'
            }
        }

        stage('Maven Build & Test') {
            steps {
                sh 'mvn clean test package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-devops-app:1.0 .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker rm -f java-app || true
                docker run -d --name java-app java-devops-app:1.0
                '''
            }
        }

     stage('Push Image to DockerHub') {
    steps {
        echo "Pushing Docker image to DockerHub"

        withCredentials([usernamePassword(
            credentialsId: 'dockerhub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )]) {
            sh '''
                echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                docker push sagarbarve/java-devops-app:1.0
            '''
        }
    }
}

    }

    post {
        success {
            echo "Deployment Successful"
        }
        failure {
            echo "Build Failed"
        }
    }
}
