node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/pandeyarpit88/jenkinsfile.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
   stage('OC Setup') {
        if (isUnix()) {
            sh 'oc login https://10.0.75.2:8443 --token=vgQwR6O5KxT-kVnuT8QpRSlAqM8pzrNmH8TkAAOT6AA'
            sh 'oc projects | grep spring-jenkins'
            
        } else {
            sh 'echo "windows machine "'
        }
   }
}