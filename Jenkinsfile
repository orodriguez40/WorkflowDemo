pipeline {
  agent any

  tools {
    // If you’ve named a Maven installation “M3” under Manage Jenkins → Global Tool Configuration
    maven 'M3'
  }

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn clean test'
          } else {
            bat 'mvn clean test'
          }
        }
      }
    }

    stage('Docker Build') {
      steps {
        script {
          if (isUnix()) {
            sh "docker build -t orodriguez40/workflowdemo:${BUILD_NUMBER} ."
          } else {
            // Use %BUILD_NUMBER% in Windows batch
            bat "docker build -t orodriguez40/workflowdemo:%BUILD_NUMBER% ."
          }
        }
      }
    }

    stage('Docker Info') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker version && docker images'
          } else {
            bat 'docker version && docker images'
          }
        }
      }
    }
  }
}
