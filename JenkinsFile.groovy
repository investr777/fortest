def env
def reg
def cred
def url


pipeline {

    agent any

    parameters {

        string(defaultValue: '', description: 'Suffix that will be added to resource', name: 'Environment_Id')
        string(defaultValue: '', description: 'WarmUp backend URL', name: 'warmup_backend_url')
        string(defaultValue: '', description: 'region', name: 'region')
        string(defaultValue: '', description: 'aws_credentials_id', name: 'aws_credentials_id')
    }

    stages {

        stage('Init') {
            steps {
                script {

                    if (!params.Environment_Id?.trim()) error("Region param is not specified")
                    if (!params.warmup_backend_url?.trim()) error("Region param is not specified")
                    if (!params.region?.trim()) error("Region param is not specified")
                    if (!params.aws_credentials_id?.trim()) error("Region param is not specified")

                    env = "${params.Environment_Id}"
                    cred = "${params.region}"
                    reg = "${params.warmup_backend_url}"
                    url = "${params.aws_credentials_id}"

                    sh "echo env: ${env}"
                    sh "echo cred: ${cred}"
                    sh "echo reg: ${reg}"
                    sh "echo url: ${url}"
                }
            }
        }
        
        stage('print') {
            steps {
                script {

                    

                    sh "echo may be losen"
                    sleep 2
                }
            }
        }

    }
    

    post {
        success {
            slackSend channel: '#bot',
                color: 'good',
                message: "env: ${env}, cred: ${cred}, reg: ${reg},  url: ${url} completed successfully."
        }
        failure {
            slackSend channel: '#bot',
                color: 'danger',
                message: "Error"
        }
    }
}
