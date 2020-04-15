#@regression
#@patientDetails
@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 3

  @NTS-3848 @Z-LOGOUT
#    @E2EUI-1609
  Scenario Outline: NTS-3848:E2EUI-1609: Verifying the sub-heading on patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the sub-heading title is displayed "Patient details entered here will be added to NGIS only. Contact the patient's GP to have their details updated to NHS Spine."
    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                |


  @NTS-3513 @Z-LOGOUT
#    @E2EUI-849
  Scenario Outline:NTS-3513:E2EUI-849: User journey when Clinical Indication has not been selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the clinical indication ID missing banner is displayed
    And the message displayed on the notification banner is "You need to add a Clinical Indication from the Test Directory before you can start a new referral."
    And the Start Referral button is disabled
    When the user clicks the "Test Directory" link on the notification banner
    Then the Test Directory homepage is displayed
#    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    And the referral page is displayed
    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                |

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
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    When the user deletes the content of the Ethnicity field
#    And the user clicks the Update NGIS record button
    Then the error messages for the mandatory fields on the "<pageTitle3>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"

    Examples:
      | pageTitle                         | pageTitle2        | pageTitle3                   | patient-search-type |
      | Add a new patient to the database | Find your patient | Check your patient's details | NGIS                |

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
  Scenario Outline: NTS-4503:E2EUI-1130 NHSNumber and Hospital Number field - maximum length validation
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user deletes data in the NHS Number field
    When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
    Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field
    And the user deletes the data in the Hospital Number field
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | NHSNumber        | HospitalNumber      |
      | Add a new patient to the database | Find your patient | NGIS                | 9449310602111111 | 1234567890123456789 |


  @NTS-4538 @Z-LOGOUT
#    @E2EUI-1054 @E2EUI-1507
  Scenario Outline: NTS-4538:(E2EUI-1054,1507) - Add patient contact(address) details to a patient
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct patient address is displayed on patient details page

    Examples:
      | pageTitle                         | pageTitle2        | patient-type | patient-search-type |
      | Add a new patient to the database | Find your patient | NGIS         | NGIS                |


  @NTS-4565 @Z-LOGOUT
#    @E2EUI-1582
  Scenario Outline: NTS-4565:E2EUI-1582:The Patient Details page is loaded when clicking browser's Back button after starting a referral
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user attempts to navigate away by clicking "back"
     #    Click the back button the second time due to user already navigated
    When the user attempts to navigate away by clicking "back"
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle>" page is displayed

    Examples:
      | stage           | pageTitle                         | directoryPathPage      |
      | Patient details | Add a new patient to the database | test-order/new-patient |


  @NTS-4565 @Z-LOGOUT
#    @E2EUI-1582
  Scenario Outline: NTS-4565:E2EUI-1582 The Patient Details page is loaded when clicking browser's Back button after starting a referral
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the clinical indication ID missing banner is displayed
    And the message displayed on the notification banner is "You need to add a Clinical Indication from the Test Directory before you can start a new referral."
    And the Start Referral button is disabled
    When the user clicks the "Test Directory" link on the notification banner
    Then the Test Directory homepage is displayed
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
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    And the referral page is displayed
    And the "<stage>" stage is marked as Completed
    When the user attempts to navigate away by clicking "back"
#    Click the back button the second time due to user already click save and continue in the test "And the referral page is displayed"
    When the user attempts to navigate away by clicking "back"
    Then the "<pageTitle3>" page is displayed

    Examples:
      | stage           | pageTitle                         | pageTitle2        | pageTitle3                   | reason_for_no_nhsNumber     | patient-search-type | directoryPathPage         |
      | Patient details | Add a new patient to the database | Find your patient | Check your patient's details | Other - provide explanation | NGIS                | test-order/patient-search |

  @NTS-4627 @Z-LOGOUT
#    @E2EUI-1664
  Scenario Outline:NTS-4627:E2EUI-1664 - Hospital Number field - Display an editable hospital number
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user deletes the data in the Hospital Number field
    And the Hospital number field displays the hint text "<hintText>"
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | HospitalNumber      | hintText |
      | Add a new patient to the database | Find your patient | NGIS                | 1234567890123456789 | B123456  |


  @NTS-4549 @Z-LOGOUT
#    @E2EUI-822
  Scenario Outline:NTS-4549:E2EUI-822: Mandatory input field validations for navigation from Patient Details to Patient details in ToDo List page
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the - "Go back to patient search" - link
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the patient detail page displays expected input-fields and drop-down fields
    And the user deletes data in the fields - First Name, Last Name, Date of Birth, Gender, Life Status and Ethnicity
    Then the error messages for the mandatory fields on the "Check your patient's details" page are displayed as follows
      | labelHeader     | errorMessageHeader         | messageColourHeader |
      | First name ✱    | First name is required.    | #dd2509             |
      | Last name ✱     | Last name is required.     | #dd2509             |
      | Date of birth ✱ | Date of birth is required. | #dd2509             |
      | Gender ✱        | Gender is required.        | #dd2509             |
      | Life status ✱   | Life status is required.   | #dd2509             |
      | Ethnicity ✱     | Ethnicity is required.     | #dd2509             |

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | reason_for_no_nhsNumber       | directoryPathPage         |
      | Add a new patient to the database | Find your patient | NGIS                | Patient is a foreign national | test-order/patient-search |

  @NTS-4752 @Z-LOGOUT
#    @E2EUI-1184
  Scenario Outline:NTS-4752:E2EUI-1184:Patient details stage is editable and it's not locked
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    And the "<stage>" stage is marked as Completed
    When the user clears the date of birth field
    And the user fills in the date of birth "<dateOfBirth>"
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage           | dateOfBirth |
      | Patient details | 20/10/2010  |