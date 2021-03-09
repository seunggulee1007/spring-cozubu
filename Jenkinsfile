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

    stage("SSH Docker Container Run") {
        def dockerRun = 'sudo docker run -d -p 9090:8080 --name ${IMAGE_NAME} ${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME}:latest'
        def harboLogin = 'sudo docker login https://${HARBOR_URL} -u ${HARBOR_USER} -p ${HARBOR_PWD}'
        def dockerStop = 'sudo docker stop ${IMAGE_NAME} || true'
        def dockerRm = 'sudo docker rm ${IMAGE_NAME} || true'
        def dockerRmi = 'sudo docker rmi ${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME} || true'
        sshagent(['dev-server']) {
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${harboLogin}"
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${dockerStop}"
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${dockerRm}"
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${dockerRmi}"
            sh "ssh -o StrictHostKeyChecking=no ${REMOTE_URL} ${dockerRun}"
        }
    }


}