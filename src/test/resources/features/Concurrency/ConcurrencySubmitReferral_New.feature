@Concurrency
@Concurrency_new_referral
Feature: Submit the New Referral - User One create new referral:User Two Updates Notes:User One Submits Referral

  @new_referral @Z-LOGOUT
  Scenario Outline: User One Login and Updates all the Mandatory Stages, Submits Referral after User Two changes the Notes stage
    ##Create New Referral
    Given The user is login to the Test Order Service and create a new referral
      | Angiomatoid Fibrous Histiocytoma | CONCURRENT_USER1_NAME | New Referral |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<requestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<requestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    And the user clicks the Save and Continue button
    Then the "<testPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user navigates to the "<responsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    Then the "<responsibleClinician>" stage is marked as Completed

    When the user navigates to the "<tumours>" stage
    When the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page for the existing patient with tumour list
    And the new tumour is not highlighted
    And the "<tumours>" stage is marked as Completed
    And the success notification is displayed "Tumour added"
    #Samples 1 - Sample Type - Solid tumour sample and Sample State - tumour_freshFrozen
    When the user navigates to the "<samples>" stage
    And the "<samples>" stage is selected
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Fresh frozen tumour" and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    And the success notification is displayed "Sample added"
    And the user is navigated to a page with title Manage samples
    And the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And the "<samples>" stage is marked as Completed
    #Samples 2 - Sample Type - Add Normal or Germline Sample and Sample State - tissue_freshFrozen
    When the user navigates to the "<samples>" stage
    And the "<samples>" stage is selected
    Then the "Manage samples" page is displayed
    When the user clicks the Add sample button
    Then the "Add a sample" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "Normal or germline sample", sample state "Fresh tissue (not tumour)" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "Sample added"
    Then the "Manage samples" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And the "<samples>" stage is marked as Completed

    When the user navigates to the "<notes>" stage
    Then the user is navigated to a page with title Add clinical notes
    And the "<notes>" stage is selected
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<notes>" stage is marked as Completed

    When the user navigates to the "<patientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the user is navigated to a page with title Patient choice
    And the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband

    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms

    When the user updates the concurrency controller file with Mandatory Stages Completed by User1
    And the user waits for the update Notes Updated by User2 in the concurrency controller file
    And the user submits the referral
#    Then the submission confirmation message "Your referral has been submitted" is displayed
#    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | requestingOrganisation  | testPackage  | responsibleClinician  | tumours | samples | notes | patientChoice  | PrintForms  |
      | Patient details | Requesting organisation | Test package | Responsible clinician | Tumours | Samples | Notes | Patient choice | Print forms |

  @new_referral @Z-LOGOUT
  Scenario Outline: Update the Notes Stage after User One completes all mandatory stages

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |New Referral|
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Notes Section
    When the user waits for the update Mandatory Stages Completed by User1 in the concurrency controller file
    And the user navigates to the "<notes>" stage
    Then the user is navigated to a page with title Add clinical notes
    And the "<notes>" stage is selected
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<notes>" stage is marked as Completed
    And the user updates the concurrency controller file with Notes Updated by User2

    Examples:
      | PatientDetails  | notes |
      | Patient details | Notes |