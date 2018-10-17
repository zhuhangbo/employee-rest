pipeline {
    agent none
    stages {
        stage('build') {
           sh 'mvnw clean compile'
        }
        stage('test') {
           sh 'mvnw clean test'
        }
    }

}
