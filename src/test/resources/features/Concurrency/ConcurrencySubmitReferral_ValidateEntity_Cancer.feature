@Concurrency
@Concurrency_newReferral_Cancer_entity
Feature: Submit New Referral for Cancer

  @newreferral_Cancer @Z-LOGOUT
  @NTS-6469

  Scenario Outline: Login as User A, Create a New Referral, Complete all stages and do not submit referral, and validate the data updated, when B is updating an entity on every stage upon referral submission by A.

#Login as User A, Complete all stages  and do not submit referral,

    Given The user is login to the Test Order Service and create a new referral
      | Well Differentiated/Dedifferentiated Liposarcoma | CONCURRENT_USER1_NAME | New Referral | NRF1 |
    ##Patient Details
#    When the user is navigated to a page with title Check your patient's details
#    And the user clicks the Save and Continue button
#    And the "<PatientDetails>" stage is marked as Completed
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


## Referral Submission by User1 after Patient Details updated by user 2
    And the user waits max 3 minutes for the update PatientDetails Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view patient details stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    Then the user updates the file NRF1 with Patient details validated by User1

## Referral Submission by User1 after Requesting Organisation details updated by user 2
    And the user waits max 3 minutes for the update Requesting Organisation details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view requesting organisation  section with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Requesting organisation details validated by User1

##Referral Submission by User1 after Test package details updated by user2
    And the user waits max 3 minutes for the update Test Package details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view requesting organisation  section with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Test Package details validated by User1

##Referral Submission by User1 after Responsible Clinician details updated by user2
    And the user waits max 3 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Responsible clinician stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Responsible Clinician details validated by User1


##Referral Submission by User1 after Tumour details updated by user2
    And the user waits max 3 minutes for the update Tumour details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Tumour details validated by User1


    ##Referral Submission by User1 after Sample details updated by user2
    And the user waits max 3 minutes for the update Sample details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Tumours stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Sample details validated by User1

    ##Referral Submission by User1 after Notes updated by user2
    And the user waits max 2 minutes for the update Notes Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Notes Stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Urgent"
    When the user updates the file NRF1 with Notes validated by User1


##Referral Submission by User1 after Patient choice details updated by user2
    And the user waits max 2 minutes for the update Patient Choice details Updated by User2 in the file NRF1
#    And the user submits the referral
#    Then the user should be able to view notification which has Reload & Review Popup with contact details of User2
#    Then the user should click on Reload & Review popup
#    Then the user should be able to view Patient Choice stage with updated data
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Patient Choice details validated by User1


## Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"


    Examples:
      | RequestingOrganisation  | testPackage  | tumour_type           | presentationType | sampleType           | sampleState         | RecordedBy                            | PatientChoiceStage |
      | Requesting organisation | Test package | Solid tumour: primary | Recurrence       | Liquid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 | Patient choice     |


  @newreferral_Cancer @Z-LOGOUT

  Scenario Outline: Update every stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
#    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
#    And the user modify the all the mandatory fields available in Check your "<update_patient's_details>" page
    And the user clicks the Save and Continue button
#    And the "<PatientDetails>" stage is marked as Completed
    And the user updates the file NRF1 with PatientDetails Updated by User2

##Requesting Organisation updated by User2
    When the user waits max 3 minutes for the update Patient details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
#    Then the details of the new organisation are displayed
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
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And the user clicks the Save and Continue button
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
    When the user clicks the Save and Continue button
    And the "<Samples>" stage is marked as Completed
    And the user updates the file NRF1 with Sample details Updated by User2

##Notes details updated by User2
    And the user waits max 2 minutes for the update Sample details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    And the user updates the file NRF1 with Notes Updated by user2

##Patient Choice details updated by User2
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
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ResponsibleClinicianDetails2 | ResponsibleClinician  | tumour_type              | Tumours | presentationType   | sampleType                | sampleState | Samples | RecordedBy          | Notes | PatientChoiceStage |
      | Patient details | Requesting organisation | Test package | LastName=thomas              | Responsible clinician | Solid tumour: metastatic | Tumours | First presentation | Normal or germline sample | Fibroblasts | Samples | ClinicianName=Smith | Notes | Patient choice     |

#    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
#    Examples:
#      | gender  | lifeStatus | ethnicity   |
#      | Unknown | Deceased   | R - Chinese |

#    And the user fills in the date of birth "01-03-2010"
#    And the user clicks the Save and Continue button on Patient details page
