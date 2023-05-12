@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: GenomicRecord: Patient details page 3

  @NTS-3513 @Z-LOGOUT
#    @E2EUI-849
#    @NTS-4538 @E2EUI-1054 @E2EUI-1507
#    @NTS-4565  @E2EUI-1582
#    @NTS-4752 @E2EUI-1184
  Scenario Outline:NTS-3513:E2EUI-849: User journey when Clinical Indication has not been selected
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
#    And the clinical indication ID missing banner is displayed
#    And the message displayed on the notification banner is "You must add a Clinical Indication from the Test Directory to start a new referral"
#    And the Start New Referral button is disabled
#    When the user clicks the "Test Directory" link on the notification banner
#    Then the Test Directory homepage is displayed
#    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    And the Start New Referral button is enabled
    And the user clicks the Start Referral button
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
#   @NTS-4538 @E2EUI-1054 @E2EUI-1507
    And the correct patient address is displayed on patient details page
    When the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "Patient details updated"
    And the user clicks the Start Referral button
    And the referral page is displayed
#   @NTS-4565  @E2EUI-1582
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
#    @NTS-4752 @E2EUI-1184
    When the user clears the date of birth field
    And the user fills in the date of birth "<dateOfBirth>"
    When the user attempts to navigate away by clicking "back"
    ##Two times click back is needed
    When the user attempts to navigate away by clicking "back"
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    ##refresh is needed spooler is not loading in click back
    And the user attempts to navigate away by clicking "refresh"
    Then the "<pageTitle1>" page is displayed

    Examples:
      | hyperlinkText               | pageTitle         | dateOfBirth | reason_for_no_nhsNumber       | patient-search-type | stage           | pageTitle1     | directoryPathPage  |
      | create a new patient record | Find your patient | 20-10-2010  | Other (please provide reason) | NGIS                | Patient details | Patient record | test-order/patient |

  @NTS-4500 @Z-LOGOUT
#    @E2EUI-2499
      # Ethnicity is now Mandatory
  Scenario Outline: NTS-4500:E2EUI-2499: Lookup an existing NGIS patient – NHSNo = Yes
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"
    And the user clicks the - "Back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    When the user deletes the content of the Ethnicity field
#    And the user clicks the Update NGIS record button
    Then the error messages for the mandatory fields on the "<pageTitle3>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | pageTitle                        | pageTitle2        | pageTitle3           | patient-search-type |
      | Create a record for this patient | Find your patient | Edit patient details | NGIS                |

  @NTS-4500 @Z-LOGOUT
#    @E2EUI-2499
     # Ethnicity is now Mandatory
  Scenario Outline: NTS-4500:E2EUI-2499:Referral Component Patient Detail Page - Lookup an existing NGIS patient – NHSNo = Yes
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user deletes the content of the Ethnicity field
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed

    Examples:
      | stage           | pageTitle                    | pageTitle2                    |
      | Patient details | Check your patient's details | Add a requesting organisation |


  @NTS-4503 @Z-LOGOUT
#    @E2EUI-1130
#    @NTS-4627 @E2EUI-1664
  Scenario Outline: NTS-4503:E2EUI-1130 NHSNumber and Hospital Number field - maximum length validation
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"
    And the user clicks the - "Back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    And the user deletes data in the NHS Number field
    When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
    Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field
    And the user deletes the data in the Hospital Number field
#   @NTS-4627 @E2EUI-1664
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | pageTitle                        | pageTitle2        | patient-search-type | NHSNumber        | HospitalNumber      |
      | Create a record for this patient | Find your patient | NGIS                | 9449310602111111 | 1234567890123456789 |

  @NTS-4565 @Z-LOGOUT
#    @E2EUI-1582
  Scenario Outline: NTS-4565:E2EUI-1582 The Patient Details page is loaded when clicking browser's Back button after starting a referral
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    When the user clicks the - "Back to patient search" - link
    And the page url address contains the directory-path web-page "<directoryPathPage>"
#    And the user attempts to navigate away by clicking "refresh"
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
#    And the clinical indication ID missing banner is displayed
#    And the message displayed on the notification banner is "You must add a Clinical Indication from the Test Directory to start a new referral"
#    And the Start New Referral button is disabled
#    When the user clicks the "Test Directory" link on the notification banner
#    Then the Test Directory homepage is displayed
#    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    When the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "Patient details updated"
    And the user clicks the Start Referral button
    And the referral page is displayed
    Then the user is navigated to a page with title Test Order Forms
#    And the "<stage>" stage is marked as Completed
    When the user attempts to navigate away by clicking "back"
    ##refresh is needed spooler is not loading in click back
    And the user attempts to navigate away by clicking "refresh"
    Then the "<pageTitle3>" page is displayed

    Examples:
      | stage           | pageTitle                        | pageTitle2        | pageTitle3     | reason_for_no_nhsNumber       | patient-search-type | directoryPathPage         |
      | Patient details | Create a record for this patient | Find your patient | Patient record | Other (please provide reason) | NGIS                | test-order/patient-search |