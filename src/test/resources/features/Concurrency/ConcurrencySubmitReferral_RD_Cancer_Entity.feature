@Concurrency
@Concurrency_Referral_Cancer_entity
Feature: Submit New Referral for Cancer

  @newreferral_Cancer @Z-LOGOUT
    @NTS-6477_1
  Scenario Outline: User A is unable to submit a RD referral, where User B has updated the same referral, until it has been checked.

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
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##print Forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1

##Referral Submission by User1 after Tumour details updated by user2
    And the user waits max 5 minutes for the update Tumour details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Tumour details validated by User1


##Referral Submission by User1 after Tumour details updated by user2
    And the user waits max 3 minutes for the update Tumour question details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Tumour question details validated by User1

##Referral Submission by User1 after Sample details updated by user2
    And the user waits max 3 minutes for the update Sample details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Sample details validated by User1

##Referral Submission by User1 after Sample details updated by user2
    And the user waits max 3 minutes for the update Sample questions details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Sample questions details validated by User1


    Examples:
      | RequestingOrganisation  | testPackage  | tumour_type           | presentationType | sampleType          | sampleState         | RecordedBy                            | PatientChoiceStage |
      | Requesting organisation | Test package | Solid tumour: primary | Recurrence       | Solid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 | Patient choice     |


  @newreferral_Cancer @Z-LOGOUT

  Scenario Outline: Update every stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
##Tumour details updated by User2
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Select or edit a tumour
    When user clicks add a new tumour link
    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And the user clicks the Save and Continue button
    And the "<Tumours>" stage is marked as Completed
    And the user updates the file NRF1 with Tumour details Updated by User2

##Tumour questions details updated by User2
    And the user waits max 5 minutes for the update Tumour details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Select or edit a tumour
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And the user clicks the Save and Continue button
    And the "<Tumours>" stage is marked as Completed
    And the user updates the file NRF1 with Tumour question details Updated by User2

##Samples details updated by User2
    And the user waits max 3 minutes for the update Tumour question details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    And the "<Samples>" stage is marked as Completed
    And the user updates the file NRF1 with Sample details Updated by User2

##Samples questions details updated by User2
    And the user waits max 3 minutes for the update Sample details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Manage samples
    When the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the "<pageTitle4>" page is displayed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the Add a Sample Details displays the appropriate field elements for Sample Tumour type - Sample topography, morphology, Tumour content, number of slides, collection date and sample comments
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    And the "<Samples>" stage is marked as Completed
    And the user updates the file NRF1 with Sample questions details Updated by User2


    Examples:
      | stage   | PatientDetails  | updated_tumour_type      | Tumours | presentationType   | sampleType           | sampleState | Samples | pageTitle4    |
      | Tumours | Patient details | Solid tumour: metastatic | Tumours | First presentation | Liquid tumour sample | Fibroblasts | Samples | Edit a sample |
