@UserJourneys
Feature: Create Referral for Proband Only + Edit Data + Patient Choice No + Tumour + Sample - Search Spine Patient


  @UserJourneys-CANCER @UserJourneys-E2EUI-1803
  Scenario Outline: E2EUI-1803 - UseCase#28: SPHINE Patient -> Create Referral for Proband only -> Patient Choice No -> Tumour -> Sample
    #Test Directory
    Given a web browser is at the Test Directory homepage
    And the search field is loaded
    And the user types the CI term "<clinical-indication>" in the search field
    When the user clicks on the search icon
    Then the user selects the first result from the search results list
    When the user clicks the Yes, Start referral button
    And the user clicks the "Sign in to the online service" hyperlink
    #Test Ordering
    Then the user logs in to the Test Order system
    And the patient search page is loaded
    And the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    When the user clicks the Search button
    #Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    When the user clicks the patient result card
    And the Patient Details page is displayed
    When the user clicks the Start Referral button
    #Patient Details
    Then the "Patient details" stage is selected
    When the user clicks the Save and Continue button
    And the "Patient details" stage is marked as Completed
    #Requesting organisation
    Then the "Requesting organisation" stage is selected
    And the user types in "<organisation>" name
    And the user selects the "<organisation>" from the dropdown list
    When the user clicks the Save and Continue button
    And the "Requesting organisation" stage is marked as Completed
    #Test package
    Then the "Test package" stage is selected
    And the user sets the "<referral-priority>"
    And the user confirms the tests by checking "<clinical-indication>" and "<referral-priority>"
    And the user clicks the Save and Continue button
    And the "Test package" stage is marked as Completed
    #Responsible clinician
    Then the "Responsible clinician" stage is selected
    And the user types in all mandatory fields in the "Responsible clinician" stage
    When the user clicks the Save and Continue button
    And the "Responsible clinician" stage is marked as Completed
    #Tumours
    Then the "Tumours" stage is selected
    When the user types in all mandatory fields in the Add a Tumour form
    And the user clicks the Save and Continue button
    And the user types in all the mandatory fields in the Answer Questions about this tumour form
    And the user clicks the Save and Continue button
    And the user is in the Select or edit a tumour form
    When the user clicks the Save and Continue button
    And the "Tumours" stage is marked as Completed
    #Samples - add two samples
    Then the "Samples" stage is selected
    When the user clicks the Add sample button
    And the user types in a tumour sample
    When the user clicks the Save and Continue button
    And the user types in sample details
    And the user clicks the Continue button
    And the "Samples" stage is marked as Completed
    #Notes
    Then the "Notes" stage is selected
    And the user types in the Add Notes field
    When the user clicks the Save and Continue button
    Then the "Notes" stage is marked as Completed
    #Patient Choice
    Then the "Patient choice" stage is selected
    And the user clicks the Continue button
    And the user answers the patient choice questions with  the "<patient-choice-info>"
    And the user submits the patient choice
    And the user clicks the Save and Continue button
    Then the "Patient choice" stage is marked as Completed
    #Print forms
    Then the "Print forms" stage is selected
    When the user clicks the Submit button
    Then the submission confirmation message is displayed
    And the referral status is set to "Submitted"


    #patient details - test data needs to be updated with Spine patient data - currently this one is NGIS


    Examples:
      | clinical-indication     | patient-search-type | NhsNumber  | DOB        |referral-priority |patient-choice-info |
      | WGS Germline and Tumour | NHS Spine           | 9449309086 | 09-09-2008 |Urgent            | Not Given          |
      | WGS Germline and Tumour | NHS Spine           | 9449309086 | 09-09-2008 |Routine           | No                 |

