node {

    stage('git source Pull') {
        checkout scm
    }

    stage("Docker Image build") {
        sh(script: "docker build -t ${IMAGE_NAME}:latest .")
    }

    stage("Docker Image tag") {
        sh(script: "docker tag ${IMAGE_NAME}:latest ${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME}:latest")
    }

    stage("Docker Image Push") {
        withDockerRegistry(credentialsId: 'harbor_docker_repository', url: 'https://cozubu.cf') {
            // some block
            sh "docker push ${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME}:latest"
        }
    }

    stage("SSH Docker Image Pull") {
        def dockerRun = 'sudo docker run -p 9090:9090 ${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME}:latest'
        def harboLogin = 'sudo docker login https://${HARBOR_URL} -u ${HARBOR_USER} -p ${HARBOR_PWD}'
        sshagent(['dev-server']) {
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${harboLogin}"
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${dockerRun}"
        }
    }


}