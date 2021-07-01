@Concurrency
@Concurrency_Cancer
Feature: NTS-6469:Cancer_new_referral_all_stages_entity

  ###FLOW
  #User1 Login to New Referral
  #User2 Login to the same referral
  #User2 updated single entity in each stage for the referral
  #User1 Submit and verify the changes done by user2 in each stages

  @NTS-6469 @Z-LOGOUT
  Scenario Outline: Login as User A and B,Access Same Referral, User B updates entity in each stages, User A verify the message by submitting the referral

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Well Differentiated/Dedifferentiated Liposarcoma | CONCURRENT_USER1_NAME | New Referral | NTS-6469 |
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ##Responsible Clinician
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
    And the user navigates to the "<Samples>" stage
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
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Patient Choice
    And the user navigates to the "<PatientChoice>" stage
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
    Then the user updates the file NTS-6469 with Mandatory Stages Completed by User1
    #Patient Details - Verify
    And the user waits max 25 minutes for the update PatientDetails Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NTS-6469 with Patient details validated by User1
    ##Requesting Organization - Verify
    And the user waits max 8 minutes for the update Requesting Organisation Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user updates the file NTS-6469 with Requesting Organisation validated by User1
     #Test Package - Verify
    And the user waits max 8 minutes for the update Test Package details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<TestPackage>" stage
    Then the user verifies the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user updates the file NTS-6469 with Test Package details validated by User1
    ##Responsible Clinician - Verify
    And the user waits max 8 minutes for the update Responsible Clinician details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user updates the file NTS-6469 with Responsible Clinician details validated by User1
    # Tumours - Verify
    And the user waits max 8 minutes for the update Tumours details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user verifies the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user updates the file NTS-6469 with Tumours details validated by User1
    ## Samples - Verify
    And the user waits max 8 minutes for the update Samples details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Samples>" with "<SamplesUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed
    And the user verifies the page "<pageTitle2>" with "<SamplesQuestionnaireUpdated>"
    And the user updates the file NTS-6469 with Samples details validated by User1
    ##Notes - verify
    And the user waits max 8 minutes for the update Notes details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    Then the user updates the file NTS-6469 with Notes details validated by User1
    ##PatientChoice- Verify
    And the user waits max 15 minutes for the update Patient Choice details Updated by User2 in the file NTS-6469
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientChoice>" stage
    Then the user verifies the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NTS-6469 with Patient Choice details validated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                      | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | Tumours | TumoursUpdated       | pageTitle1                         | TumoursQuestionnaireUpdated          | Samples | SamplesUpdated   | pageTitle2         | SamplesQuestionnaireUpdated     | Notes | NotesUpdated           | PatientChoice  | PatientChoiceDetailsUpdated     | tumour_type                             | sampleType          | sampleState        | RecordedBy                            | presentationType   |
      | Patient details | FirstName=Jhonny      | Requesting organisation | Salford Royal NHS Foundation Trust | Test package | Priority=Urgent    | Responsible clinician | Department=Ireland,UK              | Tumours | SIHMDSLabID=N7846509 | Answer questions about this tumour | FirstPresentation=First presentation | Samples | SampleID=J098078 | Add sample details | SampleComments=Sample Collected | Notes | Notes updated by user2 | Patient choice | Proband=Authorised by clinician | Haematological malignancy: solid sample | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 | First presentation |

  #User2
  @NTS-6469 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    And the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6469
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6469 |
    # Patient Details - Update
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6469 with PatientDetails Updated by User2
     # Requesting Organisation - Update
    And the user waits max 8 minutes for the update Patient details validated by User1 in the file NTS-6469
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6469 with Requesting Organisation Updated by User2
    #Test Package - Update
    And the user waits max 8 minutes for the update Requesting Organisation validated by User1 in the file NTS-6469
    When the user navigates to the "<TestPackage>" stage
    Then the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6469 with Test Package details Updated by User2
    #Responsible Clinician- Update
    And the user waits max 8 minutes for the update Test Package details validated by User1 in the file NTS-6469
    When the user navigates to the "<ResponsibleClinician>" stage
    And the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6469 with Responsible Clinician details Updated by User2
#    # Tumours - Update
    And the user waits max 8 minutes for the update Responsible Clinician details validated by User1 in the file NTS-6469
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user updates the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6469 with Tumours details Updated by User2
    ## Samples - Update
    And the user waits max 8 minutes for the update Tumours details validated by User1 in the file NTS-6469
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Samples>" with "<SamplesUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed
    And the user updates the page "<pageTitle2>" with "<SamplesQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6469 with Samples details Updated by User2
    ##Notes - Update
    And the user waits max 8 minutes for the update Samples details validated by User1 in the file NTS-6469
    When the user navigates to the "<Notes>" stage
    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6469 with Notes details Updated by User2
    ##PatientChoice- Update
    And the user waits max 15 minutes for the update Notes details validated by User1 in the file NTS-6469
    When the user navigates to the "<PatientChoice>" stage
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NTS-6469 with Patient Choice details Updated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                      | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | Tumours | TumoursUpdated       | pageTitle1                         | TumoursQuestionnaireUpdated          | Samples | SamplesUpdated   | pageTitle2         | SamplesQuestionnaireUpdated     | Notes | NotesUpdated           | PatientChoice  | PatientChoiceDetailsUpdated |
      | Patient details | FirstName=Jhonny      | Requesting organisation | Salford Royal NHS Foundation Trust | Test package | Priority=Urgent    | Responsible clinician | Department=Ireland,UK              | Tumours | SIHMDSLabID=N7846509 | Answer questions about this tumour | FirstPresentation=First presentation | Samples | SampleID=J098078 | Add sample details | SampleComments=Sample Collected | Notes | Notes updated by user2 | Patient choice | Authorised by clinician     |
