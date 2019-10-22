@patientDetails
Feature: Patient details page


  @patientDetails_01 @NTS-3068 @E2EUI-1182
  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with with NHS-Number
     Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |

  @patientDetails_02 @NTS-3068 @E2EUI-1182
  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with without NHS-Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | patient-search-type | SearchDetails                                                            |
      | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz:Gender=Female |
      | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male              |

  @patientDetails_03
  Scenario Outline: The user can return to the patient search page by clicking the Back link
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    When the user clicks the Back link
    Then the Patient Search page is displayed

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |

  @patientDetails_04 @NTS-3067 @E2EUI-1128
    Scenario Outline:NTS-3067:The user can not create a referral for a newly created patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
      When the user types in invalid details of a patient in the NHS number and DOB fields
      And the user clicks the Search button
      Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
      And the clinical indication ID missing banner is displayed
      And the Start Referral button is disabled

      Examples:
        | hyperlinkText               | reason_for_no_nhsNumber       |
        | create a new patient record | Patient is a foreign national |


  @patientDetails_05 @NTS-3067 @E2EUI-1128
  Scenario Outline:NTS-3067:The user can not create a referral for an existing patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    And a web browser is at the Patient Details page of a "<patient-search-type>" patient with NHS number "<NhsNumber>" and Date of Birth "<DOB>" without clinical indication test selected
    And the clinical indication ID missing banner is displayed
    And the Start Referral button is disabled

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |

    @patientDetails_06
  Scenario Outline: The user can navigate to Test Directory from the notification banner on patient details page when a clinical indication is not selected
      Given a web browser is at the patient search page
        | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
      And a web browser is at the Patient Details page of a "<patient-search-type>" patient with NHS number "<NhsNumber>" and Date of Birth "<DOB>" without clinical indication test selected
      And the clinical indication ID missing banner is displayed
      When the user clicks the Test Directory link from the notification banner
      Then the Test Directory homepage is displayed

      Examples:
        | patient-search-type | NhsNumber  | DOB        |
        | NHS Spine           | 9449310602 | 23-03-2011 |
        | NGIS                | 9449306680 | 14-06-2011 |


  @patientDetails_07 @E2EUI-2147
  Scenario Outline: A normal user cannot edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    Given a web browser is logged in as a "GEL-normal-user" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the NHS number field is disabled

    Examples:
      | patient-search-type |
      | NGIS                |
      | NHS Spine           |


  @patientDetails_07 @E2EUI-2146 @LOGOUT_BEFORE_TEST
  Scenario Outline: A super-user can edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-super-user|
    Given a web browser is logged in as a "GEL-ops" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the NHS number field is enabled
    And the GEL-ops user is able to edit and add an NHS Number

    Examples:
      | patient-search-type |
      | NGIS                |
