@GlobalFlow
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@GlobalConsistency
Feature: GlobalConsistency:Global Patient Flow 3 - End to end Tumour

  @NTS-4711 @Z-LOGOUT
# @E2EUI-964 @E2EUI-1587
  Scenario Outline:NTS-4711:E2EUI-964,1587:Verify Page titles for Cancer/Tumour on every stage
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M89 | GEL_NORMAL_USER |
    ##Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    And the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the message "No patient found" is displayed below the search button
    And the user clicks on the hyper link
    ##Create a New Patient Page Title
    And the user is navigated to a page with title Create a record for this patient
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "Patient not eligible for NHS number (e.g. foreign national)"
    And the user clicks the Start Referral button
    ##Patient Details Page Title
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page Title
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page Title
    Then the user is navigated to a page with title Confirm the test package
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page Title
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |
    And the user clicks the Save and Continue button
    ##Tumour Page Title
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |
    And the user clicks the Save and Continue button
    ##Tumour Dynamic Questions Page Title
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Save and Continue button
    ##Added a Tumour Page Title
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |
    And the user clicks the Save and Continue button
    ##Manage Samples Page Title
    Then the user is navigated to a page with title Manage samples
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Add sample button
    ##Add Samples Page Title
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Save and Continue button
    ##Samples Dynamic Questions Page Title
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    And the user clicks the Save and Continue button
    ##Manage Samples Page Title
    Then the user is navigated to a page with title Manage samples
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    When the user clicks the Save and Continue button
    ##Notes Page Title
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |
    And the user clicks the Save and Continue button
    ##Patient Choice Page Title
    Then the user is navigated to a page with title Patient choice
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |
    And the user edits the patient choice status
    ##Patient Choice Form Page Title
    Then the user is navigated to a page with title Add patient choice information
    And the user should see previous labels replaced as current labels
      | PreviousLabel | CurrentLabel |
      | test order    | Referral     |
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    Then the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    ##Patient Choice Landing Page Title
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ##print Forms Page Title
    When the user is navigated to a page with title Print sample forms
    And the user submits the referral
    ##Referral Submitted
    And the submission confirmation message "Your referral has been submitted" is displayed
    When the referral status is set to "Submitted"
    And the user should see previous labels replaced as current labels
      | PreviousLabel         | CurrentLabel      |
      | Test Order,test order | Referral,referral |

    Examples:
      | tumour_type           | presentationType | sampleType          | sampleState         | RecordedBy                                |
      | Solid tumour: primary | Recurrence       | Solid tumour sample | Fresh frozen tumour | ClinicianName=Herman:HospitalNumber=11203 |