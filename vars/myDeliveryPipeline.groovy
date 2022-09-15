def call(body) {

    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    
    pipeline {
        agent any
        stages {
            stage('checkout git') {
                steps {
                    echo "The build number is even"
                   //sh 'echo checkout git' 
                }
            }

            stage('build') {
                steps {
                    echo "The build number is even"
                    //sh 'echo build'
                }
            }

            stage ('test') {
                steps {
                    parallel (
                        echo "The build number is even"
                        echo "The build number is even"
                        //sh 'echo test 1'
                        //sh 'echo test 2'
                    )
                }
            }

            stage('deploy developmentServer'){
                steps {
                    echo "The build number is even"
                    //sh 'echo deploy developmentServer' 
                }
            }

            stage('deploy staging'){
                steps {
                    echo "The build number is even"
                    //sh 'echo staging'
                }
            }

            stage('deploy production'){
                steps {
                    echo "The build number is even"
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
