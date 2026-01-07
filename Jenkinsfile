pipeline {
    agent any

    environment {
        MVN_OPTS     = "-B"
        DOCKER_IMAGE = "todo-app"
        SERVICE_NAME = "todo-app"
        SONAR_SERVER = "sonarqube"
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
                bat '''
                    @echo on
                    call mvn %MVN_OPTS% clean package -DskipTests
                '''
            }
        }

        stage('Unit Tests') {
            steps {
                bat '''
                    @echo on
                    call mvn %MVN_OPTS% test
                '''
            }
        }

        stage('SonarQube Analysis') {
            when { expression { return env.CHANGE_ID == null } }
            steps {
                withSonarQubeEnv("${SONAR_SERVER}") {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        bat '''
                            @echo on
                            call mvn %MVN_OPTS% sonar:sonar -Dsonar.projectKey=%SERVICE_NAME% -Dsonar.projectName=%SERVICE_NAME% -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.token=%SONAR_TOKEN%
                        '''
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                bat '''
                    @echo on
                    docker build -t %DOCKER_IMAGE% .
                '''
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
