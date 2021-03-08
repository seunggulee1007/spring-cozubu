node {

    stage('git source Pull') {
        git(
           url: "${GIT_URL}",
           credentialsId: "${CREDIT_ID}",
           branch: "${BRANCH}"
        )
    }

    stage("Docker Image build") {
        sh(script: "docker build -t springboot-cozubu:latest .")
    }

    stage("Docker Image tag") {
        sh(script: "docker tag springboot-cozubu:latest cozubu.cf/cozubu/springboot-cozubu:latest")
    }

    stage("Docker Image Push") {
        withDockerRegistry(credentialsId: 'harbor_docker_repository', url: 'https://cozubu.cf') {
            // some block
            sh "docker push cozubu.cf/cozubu/springboot-cozubu:latest"
        }
    }

    withCredentials([sshUserPrivateKey(credentialsId: 'dev-server', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'ubuntu')]) {
            def remote = [:]
            remote.name = "springboot-cozubu"
            remote.host = "13.209.86.32"
            remote.allowAnyHosts = true
            remote.user = ubuntu
            remote.identityFile = identity //remote.passphrase = passphrase
            stage("SSH Docker Image Pull") {
                sshCommand remote: remote, command: "docker stop cozubu.cf/cozubu/springboot-cozubu:latest || true && docker rm cozubu.cf/cozubu/springboot-cozubu:latest || true"
                sshCommand remote: remote, command: "docker rmi cozubu.cf/cozubu/springboot-cozubu:latest || true"
            }
            stage("Docker run") {
                sshCommand remote: remote, command: "docker run -p 9090:9090 cozubu.cf/cozubu/springboot-cozubu:latest"
            }
        }

    }
}