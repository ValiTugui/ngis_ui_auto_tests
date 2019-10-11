@UserJourneys
Feature: Create Referral for Proband Only + Edit Data + Patient Choice Yes + Tumour + Sample - Search Spine Patient


  @UserJourneys-CANCER @UserJourneys-E2EUI-1636
  Scenario Outline: E2EUI-1636 - UseCase#21: NGIS Patient -> Create Referral for Proband only -> Patient Choice Yes -> Tumour -> Sample
    #Test Directory
    Given a web browser is at the Test Directory homepage
    And the user types in the CI term "<clinical-indication>" in the search field and selects the first result from the results list
    When the user clicks the Start referral button
    And the user clicks the "Sign in to the online service" hyperlink
     #Test Ordering
    Then the user logs in to the Test Order system successfully
    When  the user searches for a SPINE patient record with NHS number "<NhsNumber>" and Date of Birth "<DOB>"
    #When  the user searches for a "<patient-search-type>" patient with the NHS number "<NhsNumber>" and Date of Birth "<DOB>"
    And the user clicks the patient result card that is shown on the page
    And the Patient Details page is displayed
    When the user clicks the Start Referral button
    #Patient Details
    Then the "Patient details" stage is selected
    When the user does not modify the existing information on the "Patient details" form
    And the "Patient details" stage is marked as Completed
    #Requesting organisation
    Then the "Requesting organisation" stage is selected
    When the user provides the "<organisation>" detail
    Then the "Requesting organisation" stage is marked as Completed
    #Test package
    And the "Test package" stage is selected
    When the user sets the "<referral-priority>"
    #And the user confirms the tests by checking "<clinical-indication>" and "<referral-priority>"
    Then the "Test package" stage is marked as Completed
    #Responsible clinician
    And the "Responsible clinician" stage is selected
    When the user provides the details for the "Responsible clinician" stage
    Then the "Responsible clinician" stage is marked as Completed
    #Tumours
    And the "Tumours" stage is selected
    When the user adds "1" tumors info in the Add a tumour form
    And the user provides the details to the tumour questionnaire form
    Then the "Tumours" stage is marked as Completed
    #Samples - add two samples
    And the "Samples" stage is selected
    When the user adds "2" samples info to the tumour sample form
    Then the "Samples" stage is marked as Completed
    #Notes
    And the "Notes" stage is selected
    When the user provides the details to Add Notes form
    Then the "Notes" stage is marked as Completed
    #Patient Choice
    And the "Patient choice" stage is selected
    And the user answers the patient choice questions with  the "<patient-choice-info>"
    When the user submits the patient choice
    Then the "Patient choice" stage is marked as Completed
    #Print forms
    And the "Print forms" stage is selected
    When the user clicks the Submit button
    Then the submission confirmation message is displayed
    And the referral status is set to "Submitted"


    #patient details - test data needs to be updated with Spine patient data - currently this one is NGIS
    Examples:
      | clinical-indication     | patient-search-type | NhsNumber  | DOB        | organisation | referral-priority | patient-choice-info |
      | WGS Germline and Tumour | NHS Spine           | 9449309086 | 09-09-2008 | manchester   | Urgent            | Yes                  |
