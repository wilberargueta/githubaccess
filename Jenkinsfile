pipeline {
    // agent any
    // tools {
    //     maven 'maven3.6.1'
    // }
    agent {
        docker {
            image 'maven:3.6.1'
        }
    }
    stages {
        // stage('Checkout'){
        //     steps {
        //         git branch: 'main',
        //         credentialsId:'Github',
        //         url: 'git@github.com:wilberargueta/githubaccess.git'
        //     }
        // }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }
    post {
        success {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            deleteDir()
        }
    }
}
