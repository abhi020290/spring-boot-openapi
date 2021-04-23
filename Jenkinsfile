node {
	def app
	def image = 'registry.hub.docker.com/abhi020290/spring-boot-openapi'
	def branch = 'master'
	
	try {
		stage('Clone repository') {               
	    	git branch: branch,
	        	credentialsId: '1d90220e-9ae0-45f2-811d-efd12bf84b09	',
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
	    	docker.withRegistry('https://registry.hub.docker.com', 'fd0a469f-0c02-4c3a-ac24-d41c1b0a2fbd') {            
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
