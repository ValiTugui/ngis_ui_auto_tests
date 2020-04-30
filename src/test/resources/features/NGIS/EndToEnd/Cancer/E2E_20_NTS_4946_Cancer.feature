@E2E_TEST

Feature: NTS-4946:E2E20:Create Cancer Referrals for NEW Patient - Proband Only

  @NTS-4946 @Z-LOGOUT
    #@E2EUI-2684
  Scenario Outline: NTS-4946: E2E#20 : Submit a Cancel referral with patient choice Yes and check ddf payload
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=23-01-2007:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "WALTON HOSPITAL RICE LANE" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    #Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: primary"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "First presentation"
    And the user clicks the Save and Continue button
    ##Samples
    Then the user navigates to the "Samples" stage
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType1>", sample state "<sampleState1>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType2>", sample state "<sampleState2>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    ### Editing the samples
    Then the user is navigated to a page with title Manage samples
    When the user edits the added sample 1
    Then the user is navigated to a page with title Edit a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Normal or Germline sample", sample state "Bone marrow (EDTA - unsorted)" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    When the user edits the added sample 2
    Then the user is navigated to a page with title Edit a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Fresh frozen tumour" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user clicks on Continue Button
      ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ##print Forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

     ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE

    Examples:
      | sampleType1               | sampleState1                  | RecordedBy                              | sampleType2         | sampleState2        |
      | Normal or germline sample | Bone marrow (EDTA - unsorted) | ClinicianName=Seaun:HospitalNumber=5607 | Solid tumour sample | Fresh frozen tumour |
