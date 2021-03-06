job('NodeJS Docker Example') {
    scm {
        git('git://github.com/wardviaene/docker-demo.git') {
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }

    triggers {
        scm('H/5 * * * *')
    }

    wrappers {
        nodejs('nodejs') // the name of Jenkins NodeJS Installation is
    }

    steps {
        dockerBuildAndPublish {
            repositoryName('wandirahman13/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}