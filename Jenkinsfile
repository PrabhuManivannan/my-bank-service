pipeline {
 agent any

	  environment {
	    registry = "prabhu25/my-bank-service"
	    registryCredential = 'dockerhub'
	    dockerImage = ''
            CURRENT_TIMESTAMP= ${BUILD_TIMESTAMP}
	  }
 
  stages {
    stage('Bank Service Clone') {
      steps {
        git 'https://github.com/PrabhuManivannan/my-bank-service.git'
      }
    }
    stage('Build') {
       steps {
           sh 'mvn clean install -DskipTests=true'

         }
    }
    stage('Bank Service Build Image') {
      steps{
        script {
      
          dockerImage = docker.build("prabhu25/my-bank-service")
        }
      }
    }
    stage('Bank Service Push Image') {
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("SNAPSHOT-$CURRENT_TIMESTAMP")
          }
        }
      }
    }

   
    
   }
}
