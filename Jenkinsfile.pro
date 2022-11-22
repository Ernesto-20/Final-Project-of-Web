pipeline {
    agent dockerfile {
        filename 'Dockerfile'
        dir 'build'
        label 'my-defined-label'
        additionalBuildArgs  '--build-arg version=1.0.2'
    } 
    tools {
        maven 'apache-maven-3.8.1' 
        tomcat 'apache-tomcat-9.0.45' 
    }
    // "timeout" Si se demora más de 1 hora que se detenga el build
    options { timeout(time: 60) newContainerPerStage()}
    environment {
        PROJECT = "D:\\Apps\\workspace\\Final-Proyect-of-Web"
        API_KEY = "PMAK-6377bf7cc5045926f4ac4321-60fe632662d26575df09461137ca560a27"
    }
    
    stages {
        stage('MVN Builds') {
            steps {
                bat 'mvn -pl .\\project-backend\\,.\\project-frontend\\ clean install'
            }
        }
        stage('Launch Servers'){
            failFast true
            parallel{
                stage('Launch Backend Server') {
                    steps {
                        bat 'java -jar .\\project-backend\\target\\project-backend-0.0.1-SNAPSHOT.war'
                    }
                }
                stage('Launch Frontend Server') {
                    steps {
                        bat '.\\tomcat_launcher\\startup'
                    }
                }
            }
        }
        stage('Test API') {
            steps {
                sh 'sleep 60'
                bat 'newman run https://api.getpostman.com/collections/0dfdc80a-5f57-408b-9124-63139de9dfb7?apikey=%API_KEY%'            }
        }
    }
    post{always{echo 'Implementar notificacion por Slack'}}
}