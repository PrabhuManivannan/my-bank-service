pipeline {
  environment {
    registry = "prabhu25/my-bank-service"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/PrabhuManivannan/my-bank-service.git'
      }
    }
    stage('Building image') {
      steps{
        script {
        sh("sudo chmod 666 /var/run/docker.sock")
          dockerImage = docker.build("prabhu25/my-bank-service")
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push('latest')
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:latest"
      }
    }
  }
}