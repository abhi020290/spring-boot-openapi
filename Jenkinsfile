pipeline {
    agent any

    stages {
        
        stage ('Checkout') {
        git url: 'https://github.com/abhi020290/spring-boot-openapi.git'
        }
 
        
        stage ('Compile Stage') {
            steps {
                withMaven(maven : 'maven_3_6_3') {
                    sh 'mvn clean compile'
                }
            }
        }
        
            
        stage ('Build Stage') {
            steps {
                withMaven(maven : 'maven_3_6_3') {
                    sh 'mvn clean package'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_6_3') {
                    sh 'mvn test'
                }
            }
        }


        stage ('Deployment Stage') {
            steps {
                withMaven(maven : 'maven_3_6_3') {
                    sh 'mvn deploy'
                }
            }
        }
    }
}
