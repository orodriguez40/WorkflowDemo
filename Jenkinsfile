pipeline {
  agent any

  environment {
    // change to your Docker Hub repo
    DOCKER_REPO = 'orodriguez40/workflow-demo'
    // tag your image with the Jenkins build number
    IMAGE_TAG   = "${DOCKER_REPO}:${BUILD_NUMBER}"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        // 1) compile & run tests
        bat 'mvn clean test'
      }
    }

    stage('Package') {
      steps {
        // 2) package JAR (skip tests since they already ran)
        bat 'mvn clean package -DskipTests'
      }
    }

    stage('Docker Build') {
      steps {
        // 3) build your Docker image
        bat """
          docker build ^
            -t %IMAGE_TAG% ^
            .
        """
      }
    }

    stage('Docker Info') {
      steps {
        // 4) just dump Docker info to confirm
        bat 'docker info'
      }
    }
  }

  post {
    always {
      // archive your JAR for inspection
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
