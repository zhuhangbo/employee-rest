pipeline {
	agent {
	    docker {
	        image 'maven:3.5.4-alpine'
	        args '-v $HOME/repo:/root/.m2'
	    }

	}

	stages {
	    stage('build') {
    	   steps {
				sh 'mvn clean compile'
   	       }

    	}

	}

}
