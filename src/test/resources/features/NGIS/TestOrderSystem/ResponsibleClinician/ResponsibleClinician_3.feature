#@regression
#@responsibleClinicianOrg
@TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Responsible Clinician 3

  @NTS-3324 @LOGOUT
#    @E2EUI-1014
  Scenario Outline: NTS-3324 - Responsible Clinician Page - Verify the mandatory fields validations under 'Add Another Clinician' section
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
    And the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user is navigated to a page with title <pageTitle>
    # add  Main clinician
    And the user fills in all the clinician form fields
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    # add Additional clinician 1
    And the user clicks the Additional Clinician link
    When the user creates additional clinician 1 by filling up all form fields except the mandatory fields Last name
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark in additional clinician section
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | pageTitle                 | hyperlinkText | error_info            | red_color_hex_code |
      | Responsible clinician | Add clinician information | Add another   | Last name is required | #dd2509            |
