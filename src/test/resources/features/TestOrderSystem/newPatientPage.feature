Feature: New Patient page


  @newPaitentPage_01 @NTS-3072 @E2EUI-981
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid NhsNo and DOB
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button


    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |


  @newPaitentPage_01 @NTS-3072 @E2EUI-981
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid Details in N) fields
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button

    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |