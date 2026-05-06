pipeline {
    agent any

    tools {
        maven 'maven3'   
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Clone Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/Poornimaa-Shri/Zenvora-Testing.git'
            }
        }

        stage('Build & Test') {
            steps {
                dir('Automation/zenvora-automation') {
                    bat 'mvn clean test'
                }
            }
        }

        stage('Publish TestNG Report') {
            steps {
                publishTestNGResults testResultsPattern: 'Automation/zenvora-automation/test-output/testng-results.xml'
            }
        }
    }
}
