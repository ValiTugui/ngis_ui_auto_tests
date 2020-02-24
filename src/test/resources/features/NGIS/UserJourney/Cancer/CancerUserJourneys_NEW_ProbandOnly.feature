@userJourneys
@userJourneysCancer
@userJourneysCancer_NEW_ProbandOnly

Feature: Create Cancer Referrals for NEW Patient - Proband Only

  @NTS-4687 @E2EUI-1637 @UseCase21 @LOGOUT
  Scenario Outline: NTS-4687: Use Case#21: Create Referral for Proband Only + Edit Data + Patient Choice Yes + Tumour + Sample - Search non spine/ngis patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M170 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=19-10-2001:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Portsmouth Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ##Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    ##Samples
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
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

    Examples:
      | tumour_type           | presentationType | sampleType           | sampleState         | RecordedBy                            |
      | Solid tumour: primary | Recurrence       | Liquid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 |

  @NTS-4550 @E2EUI-1802 @UseCase27 @LOGOUT
  Scenario Outline: Use Case#27: Create Referral for Proband Only + Edit Data + Patient Choice Yes + Tumour + Sample - Create New Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M80 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1998:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ##REsponsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ## Tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the user clicks the Save and Continue button
    ##Samples
    And the user navigates to the "<stage2>" stage
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    ##Samples
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage3>" stage
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
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
    ##Print Forms
    Then the user is navigated to a page with title Print sample forms
    ##Submit Referral
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | tumour_type                             | presentationType   | stage2  | stage3         | sampleType          | sampleState        | RecordedBy                            |
      | Haematological malignancy: solid sample | First presentation | Samples | Patient choice | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 |

