def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {
                    //git branch: pipelineParams.branch, credentialsId: 'GitCredentials', url: pipelineParams.scmUrl
                  sh "echo checkout git" 
                }
            }

            stage('build') {
                steps {
                    //sh 'mvn clean package -DskipTests=true'
                    sh "echo build" 
                }
            }

            stage ('test') {
                steps {
                    parallel (
                        sh "echo test" 
                        //"unit tests": { sh 'mvn test' },
                        //"integration tests": { sh 'mvn integration-test' }
                    )
                }
            }

            stage('deploy developmentServer'){
                steps {
                    //deploy(pipelineParams.developmentServer, pipelineParams.serverPort)
                    sh "echo deploy developmentServer" 
                }
            }

            stage('deploy staging'){
                steps {
                    //deploy(pipelineParams.stagingServer, pipelineParams.serverPort)
                    sh "echo staging" 
                }
            }

            stage('deploy production'){
                steps {
                   // deploy(pipelineParams.productionServer, pipelineParams.serverPort)
                   sh "echo deploy production" 
                }
            }
        }
        post {
            failure {
                mail to: pipelineParams.email, subject: 'Pipeline failed', body: "${env.BUILD_URL}"
            }
        }
    }
}
