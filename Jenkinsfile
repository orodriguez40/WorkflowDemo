pipeline {
  agent any

  environment {
    // your Docker Hub repo
    DOCKER_REPO = 'orodriguez40/workflowdemo'
    // Maven Docker image
    MVN_IMAGE   = 'maven:3.9.3-eclipse-temurin-17'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        script {
          // use the Maven image, mounting your local ~/.m2 cache
          docker.image(env.MVN_IMAGE)
                .inside("-v ${env.HOME}/.m2:/root/.m2") {
            // inside a Linux container, use sh
            sh 'mvn clean test'
          }
        }
      }
    }

    stage('Package & Docker Build') {
      steps {
        script {
          // package your app
          sh 'mvn clean package -DskipTests'

          // build and tag your Docker image
          sh """
            docker build \\
              -t ${env.DOCKER_REPO}:${env.BUILD_NUMBER} \\
              .
          """
        }
      }
    }

    stage('Docker Info') {
      steps {
        sh 'docker info'
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
