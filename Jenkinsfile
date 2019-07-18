pipeline {
	agent {
	    docker {
	        image 'maven:3.6.1-jdk-8'
	        args '-v $HOME/repo:/root/.m2'
	    }

	}

	stages {
	    stage('check out') {
	        steps {
                checkout scm
	        }
	    }
	    stage('build') {
    	   steps {
				sh 'mvn clean compile'
   	       }
    	}

    	stage('test') {
    	    steps {
    	        sh 'mvn clean test'
    	    }
    	}

    	stage('package') {
    	    steps {
    	        sh 'mvn clean package'
    	    }
    	}

	}

}
