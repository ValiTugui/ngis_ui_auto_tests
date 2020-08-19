@Concurrency
@Concurrency_newReferral_Cancer
Feature: Submit New Referral for Cancer

  @Newreferral_RD @Z-LOGOUT
    @NTS-6468
  Scenario Outline: Login as User A, Create a New Referral, Complete all stages and do not submit referral, and validate the data updated, when B is updating every stage upon referral submission by A.

#Login as User A, Complete all stages  and do not submit referral,
    Given The user is login to the Test Order Service and create a new referral
      | Well Differentiated/Dedifferentiated Liposarcoma | CONCURRENT_USER1_NAME | New Referral | NRF1 |
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Portsmouth Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
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
    Then the user is navigated to a page with title Add clinical notes
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
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
# Referral Submission by User1 after Patient Details updated by user 2
    And the user waits max 3 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the patient details data "<Patientdetailsupdated>"
    Then the user updates the file NRF1 with Patient details validated by User1
# Referral Submission by User1 after Requesting Organisation details updated by user 2
    And the user waits max 3 minutes for the update Requesting Organisation details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the requesting organisation value "Sandwell and West Birmingham Hospitals NHS Trust"
    Then the user updates the file NRF1 with Requesting organisation details validated by User1
#Referral Submission by User1 after Test package details updated by user2
    And the user waits max 3 minutes for the update Test Package details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the Test Package
    Then the user updates the file NRF1 with Test Package details validated by User1
  #Referral Submission by User1 after Responsible Clinician details updated by user2
    And the user waits max 3 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the Responsible Clinician details
    Then the user updates the file NRF1 with Responsible Clinician details validated by User1
#Referral Submission by User1 after Tumour details updated by user2
    And the user waits max 3 minutes for the update Tumour details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the Tumours stage with updated data "<updatedTumour>" on Edit a tumour page and "<updatedReccurance>"
    When the user updates the file NRF1 with Tumour details validated by User1
  # #Referral Submission by User1 after Sample details updated by user2
    And the user waits max 3 minutes for the update Sample details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the Sample stage with updated data "updatedSample" on Edit a sample page "updatedSampleDetails"
    And the user clicks the Save and Continue button
#    Then the user reads & validate the sample stage with updated data "updatedSampleDetails" on Add sample details page
     Then the user updates the file NRF1 with Sample details validated by User1
    ##Referral Submission by User1 after Notes updated by user2
    And the user waits max 2 minutes for the update Notes Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then the user reads & validate the notes details data "<Notesupdated>"
    Then the user updates the file NRF1 with Notes validated by User1
   ##Referral Submission by User1 after Patient choice details updated by user2
    And the user waits max 2 minutes for the update Patient Choice details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads and validate Patient Choice stage with updated data
    Then the user updates the file NRF1 with Patient Choice details validated by User1
## Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | RequestingOrganisation  | testPackage  | tumour_type           | presentationType | sampleType           | sampleState         | RecordedBy                            |  Patientdetailsupdated                       | updatedTumour            | updatedReccurance  |Notesupdated         |
      | Requesting organisation | Test package | Solid tumour: primary | Recurrence       | Liquid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 | 01-03-2010:Unknown:R - Chinese:Deceased | Solid tumour: metastatic | First presentation |User2updatedthenotes |
  @newreferral_Cancer @Z-LOGOUT

  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
       # Requesting Organisation updated by User2
    When the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the "<PatientDetails>" stage is marked as Completed
    Then the user is navigated to a page with title Check your patient's details
    And the user fill in the first name field
    And the user fill in the last name field
    And the user stores the first name & last name values
    And the user fills in the date of birth "<dateOfBirth>"
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with PatientDetails Updated by User2
##Requesting Organisation updated by User2
    When the user waits max 3 minutes for the update Patient details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    And the user updates the file NRF1 with Requesting Organisation details Updated by User2
##Test Package - change the priority
    And the user waits max 3 minutes for the update Requesting organisation details validated by User1 in the file NRF1
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the "Routine"
    When the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    And the user updates the file NRF1 with Test Package details Updated by User2
##Responsible Clinician updated by User2
    And the user waits max 3 minutes for the update Test Package details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails2>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    And the user updates the file NRF1 with Responsible Clinician details Updated by User2
##Tumour details updated by User2
    And the user waits max 3 minutes for the update Responsible Clinician details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Select or edit a tumour
    When user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    And the user stores the tumour details
    And the "<Tumours>" stage is marked as Completed
    And the user updates the file NRF1 with Tumour details Updated by User2
##Samples details updated by User2
    And the user waits max 3 minutes for the update Tumour details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user stores the Sample details
    When the user clicks the Save and Continue button
    And the "<Samples>" stage is marked as Completed
    And the user updates the file NRF1 with Sample details Updated by User2
##Notes updated by User2
    And the user waits max 2 minutes for the update Sample details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    And the user updates the file NRF1 with Notes Updated by user2
##Patient Choice updated by User2
    And the user waits max 2 minutes for the update Notes validated by User1 in the file NRF1
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (Without Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option No for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    And the user updates the file NRF1 with Patient choice details Updated by user2
    Examples:
      | PatientDetails  | dateOfBirth | gender  | lifeStatus | ethnicity   | TestPackage  | Tumours | RequestingOrganisation  | ResponsibleClinicianDetails2                             | ResponsibleClinician  | tumour_type              | presentationType   | sampleType                | sampleState | Samples | Notes | RecordedBy                             | PatientChoiceStage |
      | Patient details | 01-03-2010  | Unknown | Deceased   | R - Chinese | Test package | Tumours | Requesting organisation | FirstName=edward:LastName=thomas:Department=woodspark,uk | Responsible clinician | Solid tumour: metastatic | First presentation | Normal or germline sample | Fibroblasts | Samples |User2updatedthenotes | ClinicianName=Smith:HospitalNumber=123 | Patient choice     |


