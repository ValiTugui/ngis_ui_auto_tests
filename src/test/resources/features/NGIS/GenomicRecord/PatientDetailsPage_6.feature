@06-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 6

  @NTS-6155_1
  Scenario Outline: NTS-6155 - Rollout: Multi input date - Pre-referral patient detailsage
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
   And the user fills in the date of birth "<dateOfBirth2>"
    Then the message will be displayed as "<error_message2>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth3>"
    Then the message will be displayed as "<error_message3>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth4>"
    Then the message will be displayed as "<error_message4>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth5>"
    Then the message will be displayed as "<error_message5>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth6>"
    Then the message will be displayed as "<error_message6>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth7>"
    Then the message will be displayed as "<error_message7>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth8>"
    Then the message will be displayed as "<error_message8>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth9>"
    Then the message will be displayed as "<error_message9>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth10>"
    Then the message will be displayed as "<error_message10>" in "#dd2509" color for the DOB field
       And the user clears the date of birth field
    Then the message will be displayed as "<error_message11>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
      And the user fills in the birthday "<Birthday>"
    Then the birthday field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message12>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthday "<Birthday_1>"
    Then the birthday field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message13>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthyear "<Birthyear_3>"
    Then the birthday field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message15>" in "#dd2509" color for the DOB field

    Examples:

      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber       | patient-search-type |dateOfBirth2|error_message2|dateOfBirth3|error_message3|dateOfBirth4|error_message4|dateOfBirth5|error_message5|dateOfBirth6|error_message6|dateOfBirth7|error_message7|dateOfBirth8|error_message8|dateOfBirth9|error_message9|dateOfBirth10|error_message10|error_message11|Birthday|error_message12|Birthday_1|error_message13|Birthyear_3|error_message15|
      | create a new patient record | Find your patient | Other (please provide reason) | NGIS|a-01-2011|Enter a day|01-b-2011|Enter a month|01-01-c|Enter a year|a-b-c|Date of birth is required.|33-09-1990 |Enter a day between 1 and 31|12-15-2000|Enter a month between 1 and 12|29-02-2002|Check the day and month are valid|01-01-1899|Enter a year after 1900|10-10-2030|Please enter a date before today|Date of birth is required.|AASFGWE|Date of birth is required.|ABC12d34ef|Enter a month between 1 and 12|200074|Enter a day|







