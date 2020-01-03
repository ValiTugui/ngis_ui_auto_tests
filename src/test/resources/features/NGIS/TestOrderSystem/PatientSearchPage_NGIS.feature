@regression
@regression_set2
@patientSearchNGIS
Feature: Patient search page_NGIS

  @patientSearch_03b @NTS-2780 @E2EUI-2128 @E2EUI-1109 @v_1 @BVT_P0
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


  @patientSearch_13b @NTS-3068 @E2EUI-1182 @v_1
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

  @patientSearch_06b @NTS-2796 @E2EUI-1472 @v_1 @BVT_P0
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


  @COMP2_TO_PatientSearch
    @patientSearch_04 @NTS-2795 @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @v_1 @BVT_P0
  Scenario Outline: NTS-2795:patient search - "<ui-ticket-no>" - "<patient-search-type>" Alternate Search without NHS Number
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
#      | E2EUI-1762   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male                               |
#      | E2EUI-2136   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male:Postcode=KT21 2BE             |