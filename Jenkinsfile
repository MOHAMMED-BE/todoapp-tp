// Pipeline declarative avec les stages :
//  1. Checkout
//  2. Build Maven
//  3. Tests unitaires 
// 4. Analyse SonarQube
// 5. Build Docker

pipeline {
    agent any

    environment {
        MAVEN_CLI_OPTS = '-B -DskipTests=true'
        SONARQUBE_SERVER = 'SonarQubeServer' 
        DOCKER_IMAGE = 'todo-app'
        SERVICE_NAME = "todo-app"
    }

    stages {
         stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/MOHAMMED-BE/todoapp-tp.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh "mvn ${MAVEN_CLI_OPTS} clean package"
            }
        }

        stage('Unit Tests') {
            steps {
                sh "mvn test"
            }
        }

         stage('SonarQube Analysis') {
            when { expression { return env.CHANGE_ID == null } }
            steps {
                bat """
                @echo on
                cd todo-app

                mvn sonar:sonar ^
                    -Dsonar.projectKey=%SERVICE_NAME% ^
                    -Dsonar.projectName=%SERVICE_NAME% ^
                    -Dsonar.host.url=http://localhost:9000 ^
                    -Dsonar.token=%SONAR_TOKEN%
                """
            }
        }


        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}