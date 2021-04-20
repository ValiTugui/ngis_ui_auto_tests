@Concurrency
@Refresh
@Refresh_Cancer
Feature: NTS-6555:Cancer_new_referral_RequestingOrganisation: Navigate and verify the changes on Requesting organisation and print forms stage done by another user
###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Requesting organisation stage for the referral
  #User2 Navigated and verify the changes done by user1 in Requesting organisation and print forms stage

  @NTS-6555 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral and update the Requesting organisation stage ,Login as user B and navigates to Requesting organisation and Print forms and verify the changes done by User A.
    Given The user is login to the Test Order Service and create a new referral
      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | New Referral| NTS-6555_Cancer |
    # Referral created and completed all stages but not submitted by user1
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
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Patient Choice
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
    Then the user updates the file NTS-6555_Cancer with Mandatory Stages Completed by User1
    #Requesting Organisation - Updated by User1
    And the user waits max 10 minutes for the update Patient details Updated by User2 in the file NTS-6555_Cancer
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6555_Cancer with Requesting Organisation Updated by User1
    Examples:
      | RequestingOrganisation  | RequestingOrganisationUpdated                  | tumour_type                             | presentationType   | stage2  | stage3         | sampleType          | sampleState        | RecordedBy                            |
      | Requesting organisation | Leeds Teaching Hospitals NHS Trust | Haematological malignancy: solid sample | First presentation | Samples | Patient choice | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 |

  #User2
  #Login as User B, Verified Requesting organisation and Print forms stage and do not submit referral
  @NTS-6555 @Z-LOGOUT
  Scenario Outline: Verified Requesting Organisation stage of new referral updated by another user
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6555_Cancer
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |New Referral| NTS-6555_Cancer |
   #Requesting Organisation - Verified by User2
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the file NTS-6555_Cancer with Patient details Updated by User2
    And the user waits max 20 minutes for the update Requesting Organisation Updated by User1 in the file NTS-6555_Cancer
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user updates the file NTS-6555_Cancer with Requesting Organisation validated by User2
    #Print - Verified by User2
    And the user navigates to the "Print forms" stage
    And the user verifies the lab name "<LabName>" is updated in Print forms stage
    And the user updates the file NTS-6555_Cancer with Print forms details validated by User2

    Examples:
      | PatientDetails  | RequestingOrganisation  | RequestingOrganisationUpdated                  | LabName        |
      | Patient details | Requesting organisation | Leeds Teaching Hospitals NHS Trust | NE&Y GLH Leeds |
