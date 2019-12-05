def name


pipeline {

    agent any

    parameters {

        string(defaultValue: '', description: 'Suffix that will be added to resource', name: 'name_cat')
    }

    stages {

        stage('Init') {
            steps {
                script {

                    if (!params.name_cat?.trim()) error("Region param is not specified")

                    name = "${params.name_cat}"

                    sh "echo create new warmup ui stack: ${name}"
                }
            }
        }
        
        stage('print') {
            steps {
                script {

                    

                    sh "echo may be losen"
                    sle1ep 5
                }
            }
        }

    }
    

    post {
        success {
            slackSend channel: '#bot',
                color: 'good',
                message: "The name ${name} completed successfully."
        }
        failure {
            slackSend channel: '#bot',
                color: 'danger',
                message: "Error"
        }
    }
}
