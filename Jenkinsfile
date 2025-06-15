pipeline {
  agent any

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
            sh "docker build -t orodriguez40/workflow-demo:${BUILD_NUMBER} ."
          } else {
            bat "docker build -t orodriguez40/workflow-demo:%BUILD_NUMBER% ."
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
