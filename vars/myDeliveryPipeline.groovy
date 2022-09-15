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
                    sh "mvn clean package -DskipTests=true"
                }
            }

            stage('build') {
                steps {
                    sh "mvn clean package -DskipTests=true"
                }
            }

            stage ('test') {
                steps {
                    parallel (
                        sh "mvn clean package -DskipTests=true"
                        sh "mvn clean package -DskipTests=true"
                    )
                }
            }

            stage('deploy developmentServer'){
                steps {
                    sh "mvn clean package -DskipTests=true"
                }
            }

            stage('deploy staging'){
                steps {
                    sh "mvn clean package -DskipTests=true"
                }
            }

            stage('deploy production'){
                steps {
                    sh "mvn clean package -DskipTests=true"
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
