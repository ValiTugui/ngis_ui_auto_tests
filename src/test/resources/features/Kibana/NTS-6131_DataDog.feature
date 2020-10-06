Feature: Login to Datadog application

  @NTS-6131

  Scenario Outline: Login to datadog application
    Given a web browser is at the datadog home page
      | DATADOG_URL | Integration Monitor | DATADOG_USER |
    When the user selects the menu section Logs
    And the user moves to core section Service
    Then the user select the checkbox "<checkbox1>" in the services
    Then the user select the checkbox "<checkbox2>" in the services
    And the user provide the filters values in search box for e2e-latest
      | Index:main                           |
      | Host:ngis-test-toms-sftp-01.gel.zone |
      | source:authlog                       |
    Then the user can see the host details in the result table
    Then the user select the services as "<option>" only

    Examples:
      | checkbox1            | option               | checkbox2            |
      | e2e-latest-sftp-push | e2e-latest-sftp-push | e2e-latest-sftp-pull |