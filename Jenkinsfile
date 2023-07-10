pipeline {
    agent any

    tools {
        maven 'Maven 3.6.2'
    }

    stages {
        stage('Build') {
            steps {
                // Checkout the source code from your GitHub repository
                git 'https://github.com/Sepay/Earnalyzer.git'

                // Build your Maven project
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                // Run the Spring Boot application
                sh 'mvn spring-boot:run'
            }
        }
    }
}
