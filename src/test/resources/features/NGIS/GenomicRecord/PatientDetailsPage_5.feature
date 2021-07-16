@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: GenomicRecord: Patient details page 5

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
    #@NTS-4762 @E2EUI-1192
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- update a patient record by passing postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
  #@NTS-4762 @E2EUI-1192
    When the user navigates to the "<PatientDetails>" stage
    Then the "<PatientDetails>" stage is selected
    And the "<PatientDetails>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode1>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat1>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode2>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat2>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode3>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat3>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode4>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat4>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode5>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat5>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode6>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat6>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode7>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat7>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode8>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat8>" format

    Examples:
      | PatientDetails  | PostcodeFormat1 | Postcode1 | PostcodeFormat2 | Postcode2 | PostcodeFormat3 | Postcode3 | PostcodeFormat4 | Postcode4 | PostcodeFormat5 | Postcode5 | PostcodeFormat6 | Postcode6 | PostcodeFormat7 | Postcode7 | PostcodeFormat8 | Postcode8 |
      | Patient details | AA9A 9AA        | AA9A 9AA  | AB1 2CD         | AB12CD    | ab1 2cd         | ab1 2cd   | A9A 9AA         | A9A 9AA   | A9 9AA          | A9 9AA    | A99 9AA         | A99 9AA   | AA9 9AA         | AA9 9AA   | AA99 9AA        | AA99 9AA  |

  @NTS-7032 @Z-LOGOUT
#    @NTS-7032 @NTOS-4986
  Scenario Outline:NTOS-4986:NTS-7032: Verify Postcode field error message with invalid format value.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode1>"
    Then the user should be able to see "<errorMessage>"
    When the user fills in the Postcode field box with "<Postcode2>"
    Then the user should be able to see "<errorMessage>"
    When the user fills in the Postcode field box with "<Postcode3>"
    Then the user should be able to see "<errorMessage>"
    When the user fills in the Postcode field box with "<Postcode4>"
    Then the user should be able to see "<errorMessage>"
    When the user fills in the Postcode field box with "<Postcode4>"
    Then the user should be able to see "<errorMessage>"
    When the user fills in the Postcode field box with "<Postcode5>"
    Then the user should be able to see "<errorMessage>"
    Examples:
      | PatientDetails  | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | errorMessage                           |
      | Patient details | 982490    | shdfkhs   | AA99A 9AA | A99A 9AA  | rwrw2424  | This postcode is not in a valid format |
