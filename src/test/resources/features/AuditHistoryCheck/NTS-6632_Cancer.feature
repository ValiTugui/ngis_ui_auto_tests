@AuditHistory
@AuditHistory_Cancer
Feature: NTS-6632:Audit history for a cancer referral
  ###FLOW
  ###First scenario - Crete a new referrral for cancxer/RD - Use from BVT smoke test
  ###Secons scenrio -  continuation of step- Update

  @NTS-6632 @Z-LOGOUT
  Scenario Outline: NTS-3362 - Create Referral for Proband Only - Standard user - patient choice Yes
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    ##Patient Details
    And the "<patientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<requestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user enters the keyword "Maidstone" in the search field
#    And the user selects a random entity from the suggestions list
#    And the details of the new organisation are displayed
#    And the user clicks the Save and Continue button
#    Then the "<requestingOrganisation>" stage is marked as Completed
#    ##Test Package
#    When the user navigates to the "<testPackage>" stage
#    And the user selects the "Routine"
#    And the user clicks the Save and Continue button
#    Then the "<testPackage>" stage is marked as Completed
#    And the "<responsibleClinician>" stage is selected
#    And the correct "1" tests are saved to the referral in  "<testPackage>"
#    ##Responsible Clinician
#    When the user navigates to the "<responsibleClinician>" stage
#    Then the user is navigated to a page with title Add clinician information
#    And the user fills in all the clinician form fields
#    And the user clicks the Save and Continue button
#    Then the "<responsibleClinician>" stage is marked as Completed
#    And the "<tumours>" stage is selected
#    When the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
#    And the user clicks the Save and Continue button
#    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
#    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
#    And the user clicks the Save and Continue button
#    Then the new tumour is displayed in the landing page for the existing patient with tumour list
#    And the new tumour is not highlighted
#    And the "<tumours>" stage is marked as Completed
#    And the success notification is displayed "Tumour added"
#    #Samples 1 - Sample Type - Solid tumour sample and Sample State - tumour_freshFrozen
#    When the user navigates to the "<samples>" stage
#    And the "<samples>" stage is selected
#    Then the user is navigated to a page with title Manage samples
#    When the user clicks the Add sample button
#    Then the user is navigated to a page with title Add a sample
#    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Fresh frozen tumour" and filling SampleID
#    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Add sample details
#    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
#    And the user clicks the Save and Continue button
#    And the success notification is displayed "Sample added"
#    And the user is navigated to a page with title Manage samples
#    And the new sample is displayed in the landing page
#    And on the Manage samples page, the sample table list shows the column header names
#      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
#      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
#    And the "<samples>" stage is marked as Completed
#    #Samples 2 - Sample Type - Add Normal or Germline Sample and Sample State - tissue_freshFrozen
#    When the user navigates to the "<samples>" stage
#    And the "<samples>" stage is selected
#    Then the "Manage samples" page is displayed
#    When the user clicks the Add sample button
#    Then the "Add a sample" page is displayed
#    When the user answers the questions on Add a Sample page by selecting the sample type "Normal or germline sample", sample state "Fresh tissue (not tumour)" and filling SampleID
#    And the user clicks the Save and Continue button
#    Then the "Add sample details" page is displayed
#    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
#    And the user clicks the Save and Continue button
#    And the success notification is displayed "Sample added"
#    Then the "Manage samples" page is displayed
#    Then the new sample is displayed in the landing page
#    And on the Manage samples page, the sample table list shows the column header names
#      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
#      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
#    And the "<samples>" stage is marked as Completed
#    When the user navigates to the "<notes>" stage
#    Then the user is navigated to a page with title Add clinical notes
#    And the "<notes>" stage is selected
#    When the user fills in the Add Notes field
#    And the user clicks the Save and Continue button
#    Then the "<notes>" stage is marked as Completed
#    When the user navigates to the "<patientChoice>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
#    And the user submits the patient choice with signature
#    And the user clicks the Save and Continue button on the patient choice
#    Then the user is navigated to a page with title Patient choice
#    And the help text is displayed
#    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
#    When the user navigates to the "<PrintForms>" stage
#    And the user is navigated to a page with title Print sample forms
#    And the user is able to download print form for the proband
#    And the user submits the referral
#    And the submission confirmation message "Your referral has been submitted" is displayed
#    And the referral status is set to "Submitted"
##      Requesting Organisation - Update
#    When the user navigates to the "<requestingOrganisation>" stage
#    And the user updates the stage "<requestingOrganisation>" with "<RequestingOrganisationUpdated>"
#    And the user clicks the Save and Continue button
#   #Responsible Clinician - Verified by User2
#    When the user navigates to the "<responsibleClinician>" stage
#    Then the user updates the stage "<responsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
#    And the user clicks the Save and Continue button
##
##  Scenario Outline: Verify audit history for an existing cancer referral for using patient ID.
##    Given The user is login to the Test Order Service and create a new referral
##      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | New Referral | NRF1 |
##    # Referral created and completed all stages and submitted by user1
##    When the user is navigated to a page with title Add a requesting organisation
##    And the user clicks the Save and Continue button
##    ##Requesting Organisation
##    Then the user is navigated to a page with title Add a requesting organisation
##    And the user enters the keyword "East London NHS Foundation Trust" in the search field
##    And the user selects a random entity from the suggestions list
##    And the user clicks the Save and Continue button
##    ##Test Package
##    Then the user is navigated to a page with title Confirm the test package
##    When the user clicks the Save and Continue button
##    ##REsponsible Clinician
##    Then the user is navigated to a page with title Add clinician information
##    When the user fills in all the clinician form fields
##    And the user clicks the Save and Continue button
##    ## Tumour
##    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
##    And the user clicks the Save and Continue button
##    Then the user is navigated to a page with title Answer questions about this tumour
##    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
##    And the user clicks the Save and Continue button
##    Then the user is navigated to a page with title Select or edit a tumour
##    And the success notification is displayed "Tumour added"
##    And the user clicks the Save and Continue button
##    ##Samples
##    And the user navigates to the "<stage2>" stage
##    Then the user is navigated to a page with title Manage samples
##    And the user clicks the Add sample button
##    Then the user is navigated to a page with title Add a sample
##    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
##    And the user clicks the Save and Continue button
##    Then the user is navigated to a page with title Add sample details
##    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
##    And the user clicks the Save and Continue button
##    ##Samples
##    Then the user is navigated to a page with title Manage samples
##    When the user clicks the Save and Continue button
##    ##Notes
##    Then the user is navigated to a page with title Add clinical notes
##    When the user fills in the Add Notes field
##    And the user clicks the Save and Continue button
##    ##Patient Choice
##    And the user navigates to the "<stage3>" stage
##    Then the user is navigated to a page with title Patient choice
##    And the user edits the patient choice status
##    When the user selects the option Adult (With Capacity) in patient choice category
##    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
##    And the user fills "<RecordedBy>" details in recorded by
##    And the user clicks on Continue Button
##    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
##    When the user selects the option Yes for the question Has research participation been discussed?
##    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
##    And the user clicks on Continue Button
##    When the user is in the section Patient signature
##    When the user fills PatientSignature details in patient signature
##    And the user clicks on submit patient choice Button
##    And the user should be able to see the patient choice form with success message
##    And the user clicks the Save and Continue button
##    Then the user is navigated to a page with title Patient choice
##    When the user clicks on Continue Button
##    ##Print Forms
##    Then the user is navigated to a page with title Print sample forms
##      ###Submitting Referral
##    When the user submits the referral
##    And the submission confirmation message "Your referral has been submitted" is displayed
##    And the referral status is set to "Submitted"
##    ## Capture the referralId somewhere in a variable

    Examples:
      | patientDetails  | requestingOrganisation  | testPackage  | responsibleClinician  | tumours | samples | notes | patientChoice  | PrintForms  | RequestingOrganisationUpdated                  | ResponsibleClinicianDetailsUpdated                                         |
      | Patient details | Requesting organisation | Test package | Responsible clinician | Tumours | Samples | Notes | Patient choice | Print forms | King's College Hospital NHS Foundation Trust | departmental_address=viotialtmlk:surname=smith:email_address=msith@test.om |

#      | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | tumour_type                             | presentationType   | stage2  | stage3         | sampleType          | sampleState        | RecordedBy                            |
#      | Responsible clinician | EmailAddress=john.smith@nhs.net    | Haematological malignancy: solid sample | First presentation | Samples | Patient choice | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 |


     #User2
  #Login as User B,then made the following changes in the referral:
#  @NTS-6632 @Z-LOGOUT
#  Scenario Outline: Verified Responsible Clinician stage of new referral updated by another user
#    And the user waits max 20 minutes for the update Referral Submitted by User1 in the file NRF1
#    Given The user is login to the Test Order Service and access the given referral
#      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
#         # Requesting Organisation - Update
#    When the user navigates to the "<RequestingOrganisation>" stage
#    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
#    And the user clicks the Save and Continue button
#   #Responsible Clinician - Verified by User2
#    When the user navigates to the "<ResponsibleClinician>" stage
#    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
#    And the user clicks the Save and Continue button
#
#    Examples:
#      | RequestingOrganisation  | RequestingOrganisationUpdated                  | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated |
#      | Requesting organisation | South London and Maudsley NHS Foundation Trust | Responsible clinician | EmailAddress=john.smith@nhs.net    |