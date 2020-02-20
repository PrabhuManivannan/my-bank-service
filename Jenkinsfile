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
            dockerImage.push("$CURRENT_TIMESTAMP")
          }
        }
      }
    }
	  
     stage("Release Type") {
            steps {
                script {
                    env.RELEASE_SCOPE = input message: 'User input required', ok: 'OK!',
                            parameters: [choice(name: 'RELEASE_SCOPE', choices: 'Fresh\nCanary', description: 'What is the release Type?')]
                }
                echo "${env.RELEASE_SCOPE}"
            }
        }

    stage('K8s Deployment') {
	steps {
           script {
	if (env.RELEASE_SCOPE == 'Fresh') {
		try{	
	          sh "kubectl delete deployment ctsops"
		}catch(Exception e){
			
		}
	    sh "sed -i 's/@@{tag}/$CURRENT_TIMESTAMP/g' deploy.yaml" 
            sh "kubectl apply -f /var/lib/jenkins/workspace/my-bank-service_master/deploy.yaml"
        } else {
             sh "sed -i 's/@@{tag}/$CURRENT_TIMESTAMP/g' canarydeploy.yaml"
	     sh "kubectl apply -f /var/lib/jenkins/workspace/my-bank-service_master/canarydeploy.yaml"
        }
	env.DEPLOY_SUCCESS = input message: 'User input required', ok: 'OK!',
        parameters: [choice(name: 'DEPLOY_SUCCESS', choices: 'Yes\nNo', description: 'Is Deployment Success')]
	if(env.DEPLOY_SUCCESS == 'Yes'){
		sh 'kubectl set image deployment/ctsops ctsops=prabhu25/my-bank-service:$CURRENT_TIMESTAMP'		   
	}
      }
    }
   }
  }
}
