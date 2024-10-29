pipeline{
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                sh "docker build -t=tempori/selenium:latest ."
            }
        }
        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials("dockerhub_creds")
            }
            steps {
                sh 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                sh "docker push tempori/selenium:latest"
                sh "docker tag tempori/selenium:latest tempori/selenium:${env.BUILD_NUMBER}"
                sh "docker push tempori/selenium:${env.BUILD_NUMBER}"
            }
        }

        stage('Run tests'){
            steps{
                build job: 'SELENIUM_DOCKER_RUNNER', parameters: [string(name: 'BROWSER', value: 'chrome')]
            }
        }
    }
    post {
        always {
            sh "docker logout"
        }
    }
}