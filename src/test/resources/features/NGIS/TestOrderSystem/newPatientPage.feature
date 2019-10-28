@newPatientPage
Feature: New Patient page


  @newPatientPage_01 @NTS-3072 @E2EUI-981 @P1 @v_1
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid NhsNo and DOB
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL-normal-user |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button


    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |


  @newPatientPage_02 @NTS-3072 @E2EUI-981 @P1 @v_1
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid Details in N) fields
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL-normal-user |
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button

    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |

  @COMP2_TO_NewPatient
  @newPatientPage_03 @NTS-3150 @E2EUI-2122 @P0 @v_1
  Scenario Outline:NTS-3150:The user can not create a referral for a newly created patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL-normal-user |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       |
      | create a new patient record | Other - provide explanation   |
      | create a new patient record | Patient is a foreign national |