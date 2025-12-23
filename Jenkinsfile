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
