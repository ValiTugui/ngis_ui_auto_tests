@regression
@TO_Common
@patientSearchNGIS
Feature: Patient search page_NGIS

  @NTS-2780 @E2EUI-2128 @E2EUI-1109 @v_1 @BVT_P0 @LOGOUT
  Scenario Outline: NTS-2780:patient search "<patient-search-type>" With NHS Number and Date of Birth
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | stage           | patient-search-type | patient-type |
      | Patient details | New-NGIS            | NGIS         |


  @NTS-3068 @E2EUI-1182 @v_1 @LOGOUT
  Scenario Outline: NTS-3068:Verifying the Patient Details page after successful search for "<patient-type>" patient
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | stage           | patient-type |
      | Patient details | NGIS         |

  @NTS-2796 @E2EUI-1472 @v_1 @BVT_P0 @LOGOUT
  Scenario Outline: NTS-2796:patient search - Patient Search Results Page validation
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"

    Examples:
      | stage           | result_message         |
      | Patient details | 1 patient record found |


  @NTS-2795 @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @v_1 @BVT_P0 @LOGOUT
  Scenario Outline: NTS-2795:patient search - "<patient-search-type>" Alternate Search - date of birth, first-name, last-name, and gender
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | stage           | patient-search-type | patient-type |
      | Patient details | New-NGIS            | NGIS         |

  @NTS-2795 @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @v_1 @BVT_P0 @LOGOUT
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
      | Patient details | New-NGIS            | NGIS         |


  @NTS-2822 @E2EUI-2140 @E2EUI-2132 @E2EUI-2131 @v_1 @LOGOUT
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
      | Patient details | New-NGIS            | NGIS         |


    @NTS-4501 @E2EUI-1130 @v_1 @LOGOUT
  Scenario Outline: Patient search - NHSNumber field - maximum length validation
      Given a web browser is at the patient search page
        | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
      When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
      Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field

    Examples:
      | NHSNumber        |
      | 9449310602111111 |