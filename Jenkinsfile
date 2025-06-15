pipeline {
  agent any

  environment {
    // Tell Maven to use the Jenkins user’s .m2 directory so dependencies stick around between builds
    MAVEN_OPTS = '-Dmaven.repo.local=$HOME/.m2/repository'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      // Run Maven *inside* a Docker container that already has Maven + JDK,
      // and mount ~/.m2 so Central downloads only happen once.
      steps {
        docker.image('maven:3.9.3-eclipse-temurin-17').inside('-v $HOME/.m2:/root/.m2') {
          sh 'mvn clean package -B'
        }
      }
    }

    stage('Docker Build') {
      steps {
        sh "docker build -t orodriguez40/workflow-demo:${BUILD_NUMBER} ."
      }
    }
  }
}
