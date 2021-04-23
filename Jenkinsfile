node {
	def app
	def image = 'registry.hub.docker.com/abhi020290/spring-boot-openapi'
	def branch = 'develop'

	try {
		stage('Clone repository') {               
	    	git branch: branch,
	        	credentialsId: 'github-credentials',
	        	url: 'https://github.com/abhi020290/spring-boot-openapi.git'
	    } 

		stage('Build JAR') {
	    	docker.image('maven:3.6.3-jdk-8').inside('-v /root/.m2:/root/.m2') {
	        	sh 'mvn -B clean package'
	        	stash includes: '**/target/spring-boot-openapi.jar', name: 'jar'
	    	}
	    }

	    stage('Build Image') {
	    	unstash 'jar'
			app = docker.build image + ':$BUILD_NUMBER'
	    }

	    stage('Push') {
	    	docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {            
				app.push()
	        }    
	    }

	    stage('Cleanup') {
			sh 'docker rmi ' + image + ':$BUILD_NUMBER'
	    }
	} catch (e) {
		echo 'Error occurred during build process!'
		echo e.toString()
		currentBuild.result = 'FAILURE'
	} finally {
        junit '**/target/surefire-reports/TEST-*.xml'		
	}
}
