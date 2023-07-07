pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Checkout the source code from your GitHub repository
                git 'https://github.com/Sepay/Earnalyzer.git'

                // Build your Maven project
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                // Deploy your application to Tomcat
                sh 'cp target/FinalEarn.war'
            }
        }
    }
}
