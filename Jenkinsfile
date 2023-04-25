pipeline {
    agent any
    tools {
        maven 'maven3.6.1'
    }
    // agent {
    //     docker {
    //         image 'maven:3.6.1'
    //     }
    // }
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
        // stage('Sonarqube') {
        //     steps {
        //         withCredentials([file(credentialsId: 'sonarqube-settings', variable: 'M2_SETTINGS')]) {
        //             sh "mvn sonar:sonar -B -ntp -s ${M2_SETTINGS}"
        //         }
        //     }

        stage('Artifactory') {
            steps {

                    script {
                        def releases = 'githubaccess-service-release'
                        def snapshots = 'githubaccess-service-snapshot'
                        def server = Artifactory.server 'artifactory'

                        def rtMaven = Artifactory.newMavenBuild()

                        rtMaven.deployer server: server, releaseRepo: releases, snapshotRepo: snapshots

                        def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean package -DskipTests -B -ntp -Dartifactory.publish.artifacts=true -Dartifactory.publish.buildInfo=true'

                        server.publishBuildInfo  buildInfo
                    }
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
