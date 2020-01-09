def env
def reg
def cred
def url


pipeline {

    agent any

    parameters {
        choice(choices: 'us-east-1\nus-east-2\nus-west-1\nus-west-2', description: 'Region to deploy.', name: 'region')
        string(defaultValue: '', description: 'Suffix that will be added to resource', name: 'Environment_Id')
        string(defaultValue: '', description: 'WarmUp backend URL', name: 'warmup_backend_url')
        choice(choices: '4b675045-3a80-4925-8e63-72871a219782', description: 'Jenkins AWS credentials id that is associated with AWS account', name: 'aws_credentials_id')
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
