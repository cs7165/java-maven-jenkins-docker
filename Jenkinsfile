pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk17'
    }

    environment {
        DOCKER_IMAGE = "sagarbarve/java-devops-app:1.0"
        EC2_USER = "ubuntu"
        EC2_HOST = "<EC2_PUBLIC_IP>"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Code checked out from SCM'
            }
        }

        stage('Maven Build & Test') {
            steps {
                sh 'mvn clean test package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Push Image to DockerHub') {
            steps {
                sh 'docker push $DOCKER_IMAGE'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(credentials: ['ec2-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no $EC2_USER@$EC2_HOST << EOF
                          docker pull $DOCKER_IMAGE
                          docker rm -f java-app || true
                          docker run -d --name java-app $DOCKER_IMAGE
                        EOF
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ App deployed successfully on EC2"
        }
        failure {
            echo "❌ Deployment Failed"
        }
    }
}
