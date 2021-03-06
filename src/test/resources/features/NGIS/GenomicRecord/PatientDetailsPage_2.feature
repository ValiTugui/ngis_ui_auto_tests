@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 2

  @NTS-3101 @Z-LOGOUT
#    @E2EUI-2147
  Scenario Outline: NTS-3101:E2EUI-2147:A normal user cannot edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Given a web browser is logged in as a "GEL_NORMAL_USER" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    Then the NHS number field is disabled

    Examples:
      | patient-search-type |
      | NGIS                |
      | NHS Spine           |

  @NTS-3101 @Z-LOGOUT
#    @E2EUI-2146
  Scenario Outline: NTS-3101:E2EUI-2146:A super-user can edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Given a web browser is logged in as a "GEL-ops" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    Then the NHS number field is enabled

    Examples:
      | patient-search-type |
      | NGIS                |

  @NTS-31511 @Z-LOGOUT
#    @E2EUI-1047
  Scenario Outline: NTS-3151:E2EUI-1047:'Completed and ongoing referrals' should display the details only with respect to the concerned patient
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details

    ##Below two steps not valid for Gonzalo
#    And the user click on the referral card on patient details page to navigate to referral page
#    And the "<patient-search-type>" patient details searched for are the same in the referral header bar

    Examples:
      | patient-search-type | stage1          |
      | NGIS                | Patient details |

  @NTS-4052 @Z-LOGOUT
    #@E2EUI-1837
#    @scenario_1
  Scenario Outline: NTS-4052:E2EUI-1837: Multidate picker - Real dates validation in Find your Patient Page.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user is navigated to a page with title Find your patient
    And the YES button is selected by default on patient search
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    And the user clicks the NO button
    When the user click YES button for the question - Do you have the NHS no?
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB2>" fields
    Then the user does not see an error message on the page
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message                     | DOB2       |
      | NGIS                | 9449305935 | 30-02-2000 | Check the day and month are valid | 19-03-2000 |
