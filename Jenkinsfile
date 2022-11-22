pipeline {
    agent any 

    options { timeout(time: 60)}

    environment {
        API_KEY = "PMAK-6377bf7cc5045926f4ac4321-60fe632662d26575df09461137ca560a27"
    }
    stages {
        stage('Launch Server and Test API'){
            failFast true
            parallel{
                stage('Launch Backend Server'){
                    steps {
                        bat 'java -jar .\\project-backend\\target\\project-backend-0.0.1-SNAPSHOT.war'
                    }
                }
                stage('Test API') {
                    steps {
                        // Espera 1 min para ejecutar la prueba
                        sh 'sleep 30' 
                        bat 'newman run https://api.getpostman.com/collections/0dfdc80a-5f57-408b-9124-63139de9dfb7?apikey=%API_KEY%' 
                    }
                }
            }
        }
    }
}