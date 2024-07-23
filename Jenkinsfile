pipeline {
    agent any

    environment {
        MAVEN_HOME = "/opt/maven"
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
        DOCKER_IMAGE = 'demo_tomcat'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        TARGET_SERVER = 'ubuntu@34.244.150.121'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://hub.docker.com/u/adeboyefrancis88', 'adeboyefrancis88') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }

        stage('Deploy to Tomcat Server') {
            steps {
                sshagent(credentials: ['docker-server-ssh-key']) {
                    sh """
                        ssh ${TARGET_SERVER} '
                        docker pull ${DOCKER_IMAGE}:${DOCKER_TAG}
                        docker stop demo_tomcat || true
                        docker rm demo_tomcat || true
                        docker run -d --name demo_tomcat -p 8080:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}
                        '
                    """
                }
            }
        }
    }
}