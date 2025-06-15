pipeline {
  agent any
  stages {
    stage('Checkout SCM') {
      steps { checkout scm }
    }
    stage('Build, Test & Package') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn clean package'
          } else {
            bat 'mvn clean package'
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
