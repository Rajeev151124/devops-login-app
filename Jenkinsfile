pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
        DOCKERHUB_USER = "rajeevreddy1511"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Rajeev151124/devops-login-app.git'
            }
        }

        stage('Build Login Service') {
            steps {
                dir('login-service') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api-gateway') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Docker Login') {
            steps {
                sh """
                echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                """
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh """
                docker build -t $DOCKERHUB_USER/login-service:${BUILD_NUMBER} login-service
                docker build -t $DOCKERHUB_USER/api-gateway:${BUILD_NUMBER} api-gateway
                docker push $DOCKERHUB_USER/login-service:${BUILD_NUMBER}
                docker push $DOCKERHUB_USER/api-gateway:${BUILD_NUMBER}
                """
            }
        }

        stage('Deploy to K3s') {
            steps {
                sh """
                kubectl set image deployment/login-service login-service=$DOCKERHUB_USER/login-service:${BUILD_NUMBER}
                kubectl set image deployment/api-gateway api-gateway=$DOCKERHUB_USER/api-gateway:${BUILD_NUMBER}
                """
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}

