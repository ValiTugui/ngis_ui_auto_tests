@SYSTEM_TEST_DataDog

Feature: Login to Datadog application

  @NTS-6131
  Scenario: Login to datadog application
    Given the user logins to the data dog home page
    When the user selects the menu section Logs
    And the user moves to core section Service
    And the user search and select the service filters e2e-latest-sftp-push,e2e-latest-sftp-pull
    And the user provide the filters values in search box for e2e-latest
#      | Index:main                           |
      | host:ngis-test-toms-sftp-01.gel.zone |
#      | source:authlog                       |
    Then the user can see the host details in the result table