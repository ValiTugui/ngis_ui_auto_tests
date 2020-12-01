pipeline {
    agent { label 'chrome' }
    tools { maven 'maven' }
    parameters {
            choice(name: 'TestEnvironment', choices: "e2elatest\ne2e\nuat\nbeta", description: 'Defines the environment on which the test will be run')
            string(name: 'tags', defaultValue: '@BVT_UI_SMOKE_TEST_PACK', description: 'tags of tests to execute - separate multiple tags with commas but NO SPACE')
    }
    
    stages {
        stage('Checkout Source Code') {
            steps {
                checkout([
                    $class: 'GitSCM', branches: [[name: 'develop']],
                    //userRemoteConfigs: [[url: 'ssh://git@github.com/genomicsengland/ngis_ui_auto_tests.git', credentialsId:'bd67b9d5-5ccb-4aae-9596-a37284f02405']]
                      userRemoteConfigs: [[url: 'https://github.com/genomicsengland/ngis_ui_auto_tests.git', credentialsId:'7f90912e-1e7e-48dd-81b4-3ff5da62ed7d']]

                ])
            }
        }
        stage('UI Test Execution') {
            steps {
                wrap([$class: 'Xvfb', autoDisplayName: true, displayNameOffset: 0, timeout: 0,screen: '1920x1080x24']) {
                   sh "mvn -f pom.xml -Dcucumber.options='--tags ${tags}' clean verify -DTestEnvironment=${TestEnvironment}"
                }
            }
        }
        stage('Zip and Archive') {
            steps {
                  echo "Archiving build ran at ${BUILD_TIMESTAMP}"
                  fileOperations([fileZipOperation('target'), fileRenameOperation(destination: 'NGIS_UI_NightlyRun_${BUILD_TIMESTAMP}.zip', source: 'target.zip')])
                  archiveArtifacts "NGIS_UI_NightlyRun_${BUILD_TIMESTAMP}.zip"

            }
        }
    }

}
