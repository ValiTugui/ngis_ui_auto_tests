@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: TestOrder - Global Consistency

    @NTS-5069
    #@E2EUI-875
  Scenario Outline: NTS-5069:E2EUI-875: Microsoft Login / Authentication
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1997:Gender=Male |
      And the "Patient details" stage is marked as Completed
#      And the "Patient details" stage is marked as Completed
#      And the user clicks the Save and Continue button
    ##Patient details Stage
      When the user navigates to the "Requesting organisation" stage
      Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Log out button
    Then the user should be navigated to Microsoft login "<MicrosoftLoginUrl>" page
    When the user login to Test Order with invalid credential
    Then the user should be able to see an error message "<errorMessage>"
    When the user login to Test Order with valid credential
    Then the user is navigated to a page with title Welcome to the National Genomic Informatics System



    Examples:
      | MicrosoftLoginUrl           | dialogTitle                  | stage2                  | ordering_entity_name | stage3       | errorMessage                           |
      | /login.microsoftonline.com/ | There is missing information | Requesting organisation | Maidstone            | Test package | Your account or password is incorrect. |
