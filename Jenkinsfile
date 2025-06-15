pipeline {
  agent any
  stages {
    stage('Checkout')    { steps { checkout scm } }
    stage('Build & Test'){ steps { sh 'mvn clean test' } }
    stage('Docker Build'){ steps { sh 'docker build -t yourname/workflowdemo:${BUILD_NUMBER} .' } }
  }
}
