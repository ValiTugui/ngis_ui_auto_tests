#@regression
#@testPackageRD
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@TestPackage
Feature: TestOrder - Test Package 2 - RD

  @NTS-3177 @Z-LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177:E2EUI-1547,1585: To Do list should be Mandatory To Do - verify permissible Number of Participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
    ##Patient details Stage
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user clicks on the drop down box to see the values between "<minParticipants>" - "<maxParticipants>" displayed
    And the "<stage>" stage is marked as Mandatory To Do
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | title                    | minParticipants | maxParticipants | new_stage             | number_of |
      | Test package | Confirm the test package | 1               | 8               | Responsible clinician | 1         |

  @NTS-3177 @Z-LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177:E2EUI-1547,1585: To Do list should be Mandatory To Do
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
    ##Patient details Stage
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user does not select one of the values
    And the user sees an error message "<errorMessage>"
    And the "<stage>" stage is marked as Mandatory To Do
    And the user selects the number of participants: "<count>"
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    And the correct "<count>" of participants are saved to the referral in "<stage>"
    Examples:
      | stage        | title                    | errorMessage                                                     | count | new_stage             | number_of |
      | Test package | Confirm the test package | Select the total number of participants you expect for this test | 2     | Responsible clinician | 1         |
