@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 5

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- update a patient record by passing postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode1>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode2>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode3>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode4>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode5>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode6>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    Examples:
      | PatientDetails  | PostcodeFormat | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | Postcode6 |
      | Patient details | AB1 2CD        | AB1 2CD   | AB1  2CD  | AB12CD    | ab12cd    | ab1 2cd   | ab1  2cd  |
