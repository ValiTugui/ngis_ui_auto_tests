@Concurrency
@Concurrency_Cancer
Feature: NTS-7214:Cancer_new_referral_same_stage

  ###FLOW
  #User1 Login and create New Referral
  #User2 Login to the same referral as user1
  #User1 Update any field in each stage for the referral
  #User2 Update same stage as user1 and verify the alert message while saving the stage

  @NTS-7214 @Z-LOGOUT
  Scenario Outline: Login as User A and B,Access Same Referral, User A updates fields in each stages, User B also update the same stage and verify the alert message by saving the stage

#   Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Well Differentiated/Dedifferentiated Liposarcoma | CONCURRENT_USER1_NAME | New Referral | NTS-7214 |
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
#   Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
#   Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
#   Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
#   Tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the user clicks the Save and Continue button
#   Samples
    And the user navigates to the "<Samples>" stage
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
#   Samples
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
#   Notes
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#   Patient Choice
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
#   Print Forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NTS-7214 with Mandatory Stages Completed by User1
#   Patient Details - Update
    And the user waits max 20 minutes for the update User2 navigated to PatientDetails in the file NTS-7214
    When the user navigates to the "<PatientDetails>" stage
    Then the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with PatientDetails Updated by User1
#   Requesting Organization - Update
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NTS-7214
    And the user refresh the browser
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Requesting Organisation updated by User1
#   Test Package - Update
    And the user waits max 10 minutes for the update Requesting Organisation validated by User2 in the file NTS-7214
    And the user refresh the browser
    Then the user updates the stage "<TestPackage>" with "<TestPackageUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Test Package details updated by User1
#   Responsible Clinician - Update
    And the user waits max 8 minutes for the update Test Package details validated by User2 in the file NTS-7214
    And the user refresh the browser
    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Responsible Clinician details updated by User1
#   Edit a tumour page - Update
    And the user waits max 10 minutes for the update Responsible Clinician details validated by User2 in the file NTS-7214
    And the user refresh the browser
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    Then the user updates the stage "<Tumours>" with "<TumoursUpdatedByUser1>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user updates the file NTS-7214 with Edit a tumour page updated by User1
#    Answer questions about this tumour page - Update
    And the user waits max 10 minutes for the update Edit a tumour page validated by User2 in the file NTS-7214
    And the user refresh the browser
    And the user updates the page "<pageTitle1>" with "<TumoursQuestionnaireUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Answer questions about this tumour page updated by User1
#   Edit a sample page - Update
    And the user waits max 10 minutes for the update Answer questions about this tumour page validated by User2 in the file NTS-7214
    And the user refresh the browser
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user updates the stage "<Samples>" with "<SamplesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed
    And the user updates the file NTS-7214 with Edit a sample page updated by User1
#   Add sample details page - Update
    And the user waits max 10 minutes for the update Edit a sample page validated by User2 in the file NTS-7214
    And the user refresh the browser
    And the user updates the page "<pageTitle2>" with "<SamplesQuestionnaireUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Add sample details page updated by User1
#   Notes - Update
    And the user waits max 10 minutes for the update Add sample details page validated by User2 in the file NTS-7214
    And the user refresh the browser
    Then the user updates the stage "<Notes>" with "<NotesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Add clinical notes page updated by User1
#   PatientChoice- Update
    And the user waits max 15 minutes for the update Add clinical notes page validated by User2 in the file NTS-7214
    And the user refresh the browser
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceUpdatedByUser1>"
    Then the user updates the file NTS-7214 with Patient Choice details updated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdatedByUser1 | RequestingOrganisation  | RequestingOrganisationUpdatedByUser1              | TestPackage  | TestPackageUpdatedByUser1 | ResponsibleClinician  | ResponsibleClinicianDetailsUpdatedByUser1 | Tumours | TumoursUpdatedByUser1 | pageTitle1                         | TumoursQuestionnaireUpdatedByUser1 | Samples | SamplesUpdatedByUser1 | pageTitle2         | SamplesQuestionnaireUpdatedByUser1 | Notes | NotesUpdatedByUser1    | PatientChoice  | PatientChoiceUpdatedByUser1 | tumour_type                             | sampleType          | sampleState        | RecordedBy                            | presentationType   |
      | Patient details | FirstName=Jhonny             | Requesting organisation | Dorset Healthcare University NHS Foundation Trust | Test package | Priority=Urgent           | Responsible clinician | Department=Ireland,UK                     | Tumours | SIHMDSLabID=N7846509  | Answer questions about this tumour | FirstPresentation=Recurrence       | Samples | SampleID=J098078      | Add sample details | SampleComments=Sample Collected    | Notes | Notes updated by user1 | Patient choice | Authorised by clinician     | Haematological malignancy: solid sample | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 | First presentation |

  #User2
  @NTS-7214 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral and verify the alert message by saving the stage
    And the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-7214
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-7214 |
#   Patient Details - Update and Verify
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the file NTS-7214 with User2 navigated to PatientDetails
    Then the user waits max 20 minutes for the update PatientDetails Updated by User1 in the file NTS-7214
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Check your patient's details
    And the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Patient details validated by User2
#   Requesting Organisation - Update and verify
    And the user waits max 10 minutes for the update Requesting Organisation updated by User1 in the file NTS-7214
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add a requesting organisation
    And the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Requesting Organisation validated by User2
#   Test Package - Update and verify
    And the user waits max 10 minutes for the update Test Package details updated by User1 in the file NTS-7214
    And the user deselects the test
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Confirm the test package
    And the user verifies the stage "<TestPackage>" with "<TestPackageUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Test Package details validated by User2
#   Responsible Clinician- Update and verify
    And the user waits max 10 minutes for the update Responsible Clinician details updated by User1 in the file NTS-7214
    And the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add clinician information
    And the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    And the user updates the file NTS-7214 with Responsible Clinician details validated by User2
#   Edit a tumour page - Update and verify
    And the user waits max 10 minutes for the update Edit a tumour page updated by User1 in the file NTS-7214
    And the user updates the stage "<Tumours>" with "<TumoursUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Edit a tumour
    And the user verifies the stage "<Tumours>" with "<TumoursUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Edit a tumour page validated by User2
#   Answer questions about this tumour - Update and verify
    And the user waits max 10 minutes for the update Answer questions about this tumour page updated by User1 in the file NTS-7214
    And the user updates the page "<pageTitle1>" with "<TumoursQuestionnaireUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Answer questions about this tumour
    And the user verifies the page "<pageTitle1>" with "<TumoursQuestionnaireUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    And the user updates the file NTS-7214 with Answer questions about this tumour page validated by User2
#   Edit a sample page - Update and verify
    And the user waits max 10 minutes for the update Edit a sample page updated by User1 in the file NTS-7214
    And the user updates the stage "<Samples>" with "<SamplesUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Edit a sample
    And the user verifies the stage "<Samples>" with "<SamplesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Edit a sample page validated by User2
#   Add sample details page - Update and verify
    And the user waits max 10 minutes for the update Add sample details page updated by User1 in the file NTS-7214
    And the user updates the page "<pageTitle2>" with "<SamplesQuestionnaireUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add sample details
    And the user verifies the page "<pageTitle2>" with "<SamplesQuestionnaireUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7214 with Add sample details page validated by User2
#   Notes - Update and verify
    And the user waits max 10 minutes for the update Add clinical notes page updated by User1 in the file NTS-7214
    And the user updates the stage "<Notes>" with "<NotesUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add clinical notes
    And the user verifies the stage "<Notes>" with "<NotesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user edits the patient choice status
    And the user clicks on the amend patient choice button
    Then the user updates the file NTS-7214 with Add clinical notes page validated by User2
#   PatientChoice- Update and verify
    And the user waits max 15 minutes for the update Patient Choice details updated by User1 in the file NTS-7214
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
    And the user should be able to see concurrency alert message while submitting the patient choice
    And the user refresh the browser
    And the user navigates to the "<PatientChoice>" stage
    And the user verifies the stage "<PatientChoice>" with "<PatientChoiceUpdatedByUser1>"
    Then the user updates the file NTS-7214 with Patient Choice details validated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdatedByUser2 | PatientDetailsUpdatedByUser1 | RequestingOrganisation  | RequestingOrganisationUpdatedByUser2 | RequestingOrganisationUpdatedByUser1              | TestPackage  | TestPackageUpdatedByUser1 | ResponsibleClinician  | ResponsibleClinicianDetailsUpdatedByUser2 | ResponsibleClinicianDetailsUpdatedByUser1 | Tumours | TumoursUpdatedByUser2 | TumoursUpdatedByUser1 | pageTitle1                         | TumoursQuestionnaireUpdatedByUser2 | TumoursQuestionnaireUpdatedByUser1 | Samples | SamplesUpdatedByUser2 | SamplesUpdatedByUser1 | pageTitle2         | SamplesQuestionnaireUpdatedByUser2  | SamplesQuestionnaireUpdatedByUser1 | Notes | NotesUpdatedByUser2    | NotesUpdatedByUser1    | PatientChoice  | PatientChoiceUpdatedByUser1     | RecordedBy         |
      | Patient details | FirstName=Tim                | FirstName=Jhonny             | Requesting organisation | West London Mental Health NHS Trust  | Dorset Healthcare University NHS Foundation Trust | Test package | Priority=Urgent           | Responsible clinician | Department=London,UK                      | Department=Ireland,UK                     | Tumours | SIHMDSLabID=HJ76565   | SIHMDSLabID=N7846509  | Answer questions about this tumour | FirstPresentation=Unknown          | FirstPresentation=Recurrence       | Samples | SampleID=AD098765     | SampleID=J098078      | Add sample details | SampleComments=Sample Not Collected | SampleComments=Sample Collected    | Notes | Notes updated by user2 | Notes updated by user1 | Patient choice | Proband=Authorised by clinician | ClinicianName=John |
