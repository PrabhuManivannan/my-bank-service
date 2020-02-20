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
	  
     stage("Release Type") {
            steps {
                script {
                    env.RELEASE_SCOPE = input message: 'User input required', ok: 'Type Of Release!',
                            parameters: [choice(name: 'RELEASE_SCOPE', choices: 'Fresh\nCanary', description: 'What is the release Type?')]
                }
                echo "${env.RELEASE_SCOPE}"
            }
        }

    stage('K8s Deployment') {
	if (env.RELEASE_SCOPE == 'Fresh') {
            echo 'I only execute on the master branch'
        } else {
            echo 'I execute elsewhere'
        }
   }
}
