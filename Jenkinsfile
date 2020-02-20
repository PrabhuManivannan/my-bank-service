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
	  
     stage("foo") {
            steps {
                script {
                    env.RELEASE_SCOPE = input message: 'User input required', ok: 'Release!',
                            parameters: [choice(name: 'RELEASE_SCOPE', choices: 'patch\nminor\nmajor', description: 'What is the release scope?')]
                }
                echo "${env.RELEASE_SCOPE}"
            }
        }

    stage('Approval Request for K8 Deployment') {
                input {
                    message "Can we proceed to deploy the latest image into K8 ? If yes,please click OK to approve this request"
                    ok "Yes, we can proceed."
                    submitter "Ashok,Prabhu"
                    parameters {
                        string(name: 'Approver - Ashok', defaultValue: 'Text your comments', description: 'K8 Deployment for latest image')
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
