#@regression
#@patientSearchNGIS
@GENOMIC_RECORD
@SYSTEM_TEST
Feature: Patient search page_NGIS


  @NTS-2780 @LOGOUT
#    @E2EUI-2128 @E2EUI-1109 @E2EUI-1363 @E2EUI-1025 @E2EUI-1217 @E2EUI-1125 @E2EUI-1268
  Scenario Outline: NTS-2780:patient search "<patient-search-type>" With NHS Number and Date of Birth
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    And the user see a tick mark next to the YES button
    Then the NHS number field is displayed
    When the user fills in all the fields with NHS number on the New Patient page
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | patient-type |
      | Add a new patient to the database | Find your patient | NGIS                | NGIS         |


  @NTS-3068 @LOGOUT
#    @E2EUI-1182
  Scenario Outline: NTS-3068: Verifying the Patient Details page after successful search for "<patient-type>" patient
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    Then the NHS number field is displayed
    When the user fills in all the fields with NHS number on the New Patient page
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | pageTitle                         | pageTitle2        | patient-type |
      | Add a new patient to the database | Find your patient | NGIS         |

  @NTS-2796 @LOGOUT
#    @E2EUI-1472 @E2EUI-2137
  Scenario Outline: NTS-2796:patient search - Patient Search Results Page validation
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    Then the NHS number field is displayed
    When the user fills in all the fields with NHS number on the New Patient page
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"

    Examples:
      | pageTitle                         | pageTitle2        | result_message         |
      | Add a new patient to the database | Find your patient | 1 patient record found |


  @NTS-2795 @LOGOUT
#    @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @E2EUI-1363 @E2EUI-1662 @E2EUI-865 @E2EUI-1217 @E2EUI-1125 @E2EUI-1268
  Scenario Outline: NTS-2795:patient search - "<patient-search-type>" Alternate Search - date of birth, first-name, last-name, and gender
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user see a tick mark next to the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | stage           | patient-search-type | patient-type |
      | Patient details | NGIS                | NGIS         |

  @NTS-2795 @LOGOUT
#    @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @E2EUI-1883
  Scenario Outline: NTS-2795:patient search - "<patient-search-type>" Alternate Search - date of birth, first-name, last-name, gender and post-code
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | stage           | patient-search-type | patient-type |
      | Patient details | NGIS                | NGIS         |


  @NTS-2822 @LOGOUT
#    @E2EUI-2140 @E2EUI-2132 @E2EUI-2131
  Scenario Outline: NTS-2822:patient search - "<patient-search-type>" Defuzzing, accented and special characters
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPECIAL_CHARACTERS | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | stage           | patient-search-type | patient-type |
      | Patient details | NGIS                | NGIS         |


    @NTS-4503 @LOGOUT
#      @E2EUI-1130
  Scenario Outline: NTS-4503-Patient search - NHSNumber field - maximum length validation
      Given a web browser is at the patient search page
        | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
      When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
      Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field

    Examples:
      | NHSNumber        |
      | 9449310602111111 |

  @NTS-3477 @LOGOUT
#    @E2EUI-1692
  Scenario Outline: NTS-3477: Display the role of the patient on the referral card
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the user is navigated to a page with title Check your patient's details
    And the user should verify the role and relationship of patient on referral card

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NGIS                | 9449306583 | 21-05-2008 |
