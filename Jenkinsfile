pipeline {
 agent any

	  environment {
	    registry = "prabhu25/my-bank-service"
	    registryCredential = 'dockerhub'
	    dockerImage = ''
            CURRENT_TIMESTAMP= "${BUILD_TIMESTAMP}"
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

    stage('Approval Request for K8 Deployment') {
                input {
                    message "Can we proceed to deploy into K8 ? If yes,please click OK to approve this request"
                    ok "Yes, we can proceed."
                    submitter "Ashok,Prabhu"
                    parameters {
                        string(name: 'Ashok', defaultValue: 'Admin', description: 'Deployment for latest image')
                    }
                }
                steps {
                  			
			sh "echo Deployment for SNAPSHOT-$CURRENT_TIMESTAMP is in progress..."
                }
       }
	  
	  stage('K8 Deployment'){
		steps {
                   	
			 sh "echo Deployment for SNAPSHOT-$CURRENT_TIMESTAMP is completed.."
                }  
	  }
   }
}
