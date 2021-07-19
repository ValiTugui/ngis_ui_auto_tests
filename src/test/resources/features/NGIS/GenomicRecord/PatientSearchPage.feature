@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: Search Patient page

  @NTS-7032 @Z-LOGOUT
#    @NTS-7032 @NTOS-4986
  Scenario Outline:NTOS-4986:NTS-7032: Patient search page: Verify Postcode field error message with invalid format value.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user fills in the Postcode field box with "<Postcode1>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode2>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode3>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode4>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode5>"
    Then the user should be able to see "<errorMessage>"
    Examples:
      | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | errorMessage                           |
      | shdfkhs   | 982490    | AA99A 9AA | A99A 9AA  | rwrw2424  | This postcode is not in a valid format |


  @NTS-7032 @Z-LOGOUT
#    @NTS-7032 @NTOS-4986
  Scenario Outline:NTOS-4986:NTS-7032: Create new patient: Verify Postcode field error message with invalid format value.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    When the user clicks the Search button
    Then the message "<message>" is displayed below the search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    Then the "<pageTitle>" page is displayed
    And the user fills in the Postcode field box with "<Postcode1>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode2>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode3>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode4>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode5>"
    Then the user should be able to see "<errorMessage>"
    Examples:
      | message          | hyperlinkText               | pageTitle                        | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | errorMessage                           |
      | No patient found | create a new patient record | Create a record for this patient | shdfkhs   | 982490    | AA99A 9AA | A99A 9AA  | rwrw2424  | This postcode is not in a valid format |