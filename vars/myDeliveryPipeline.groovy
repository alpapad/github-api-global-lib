def call(Closure body) {
    
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        agent { label "linux" }
        stages {
            stage("echo parameters") {
                steps {
                    sh "env | sort"
                    echo "${agentLabel}"              
                }
            }
            stage("Prepare Build Environment") {
                steps {
                    prepareBuildEnvironment()
                    echo "Prepare Build Environment ${pipelineParams.email}"
                }
            }
            stage("Source Code Checkout") {
                steps {
                    echo 'Source Code Checkout'
                }
            }
            stage("SonarQube Scan") {
                when {
                    branch 'master'
                }
                steps {
                    echo 'scan'
                }
            }
            stage("Build Application") {
                steps {
                    echo 'build'
                }
            }
            stage("Publish Artifacts") {
                steps {
                    echo 'Publish Artifacts'
                }
            }
            stage("Deploy Application") {
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
