def call(String agentLabel,body) {
    
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        agent none
        stages {
            stage("echo parameters") {
                agent { label "${agentLabel}" }
                steps {
                    sh "env | sort"
                    echo "${agentLabel}"              
                }
            }
            stage("Prepare Build Environment") {
                agent { label "${agentLabel}" }
                steps {
                    prepareBuildEnvironment()
                    echo 'Prepare Build Environment'
                }
            }
            stage("Source Code Checkout") {
                agent { label "${agentLabel}" }
                steps {
                    echo 'Source Code Checkout'
                }
            }
            stage("SonarQube Scan") {
                agent { label "${agentLabel}" }
                when {
                    branch 'master'
                }
                steps {
                    echo 'scan'
                }
            }
            stage("Build Application") {
                agent { label "${agentLabel}" }
                steps {
                    echo 'build'
                }
            }
            stage("Publish Artifacts") {
                agent { label "${agentLabel}" }
                steps {
                    echo 'Publish Artifacts'
                }
            }
            stage("Deploy Application") {
                agent { label "${agentLabel}" }
                steps {
                     echo 'Deploy Application'
                }
            }
  
        }
        post {
            always {
              echo 'Post action'
            }
        }
    }
}
