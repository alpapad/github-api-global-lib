def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {
                   //sh 'echo checkout git' 
                }
            }

            stage('build') {
                steps {
                    //sh 'echo build'
                }
            }

            stage ('test') {
                steps {
                    parallel (
                        //sh 'echo test 1'
                        //sh 'echo test 2'
                    )
                }
            }

            stage('deploy developmentServer'){
                steps {
                    //sh 'echo deploy developmentServer' 
                }
            }

            stage('deploy staging'){
                steps {
                    //sh 'echo staging'
                }
            }

            stage('deploy production'){
                steps {
                   //sh 'echo deploy production' 
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
