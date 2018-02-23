import java.text.SimpleDateFormat;

// This pipeline expects a paramater newcolor which will set the newcolor of the newly deployed application.

node {
  // Blue/Green Deployment into Production
  // -------------------------------------
  def project  = "cicd-dev"
  def project1 = "spring-v1"
  def project2 = "spring-v3"
  def dest     = project2
  def active   = ""
  def route    = project1

  stage('Determine Deployment route') {  
    // Determine currently active Service
    sh "oc get route ${route} -n ${project} -o jsonpath='{ .spec.to.name }' > activesvc.txt"   
    active = readFile('activesvc.txt').trim()
    if (active == project2) {
      dest = project1
    }
  }
  stage('Deploy new Version') {
    echo "oc start-build ${dest}"
	  // sh "oc start-build ${dest}"
    openshiftBuild bldCfg: dest, namespace: project, showBuildLogs: 'true'
    // openshiftVerifyBuild bldCfg: dest, namespace: project, verbose: 'false', waitTime: '780', waitUnit: 'sec'
    openshiftDeploy depCfg: dest, namespace: project, verbose: 'false', waitTime: '780', waitUnit: 'sec'
    openshiftVerifyDeployment depCfg: dest, namespace: project, replicaCount: '1', verbose: 'false', verifyReplicaCount: 'true', waitTime: '780', waitUnit: 'sec'
    openshiftVerifyService namespace: project, svcName: dest, verbose: 'false'
  }
  stage('Switch over to new Version') {
    // input "Switch Production?"
    sh 'oc patch route ' + ${project1} + ' -p \'{"spec":{"to":{"name":"' + dest + '"}}}\''
    sh 'oc get route ' + ${project1} + ' > oc_out.txt'
    oc_out = readFile('oc_out.txt')
    echo "Current route configuration: " + oc_out
  }
}