@Concurrency
@Refresh
@Refresh_Cancer
Feature: NTS-6556:Cancer_new_referral_TestPackage: Navigate and verify the changes on Test Package stage done by another user
###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Test Package stage for the referral
  #User2 Navigate and verify the changes done by user1 in Test Package stage

  @NTS-6556 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and updated Test package stage, when B accessed same referral then verified data updated by A.

    Given The user is login to the Test Order Service and create a new referral
      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | New Referral | NTS-6556_Cancer |
    # Referral created and completed all stages but not submitted by user1
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ## Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
#    When the user fills in all the clinician form fields
#    And the user clicks the Save and Continue button
#    ## Tumour
#    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Answer questions about this tumour
#    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Select or edit a tumour
#    And the user clicks the Save and Continue button
#    ##Samples
#    And the user navigates to the "<stage2>" stage
#    Then the user is navigated to a page with title Manage samples
#    And the user clicks the Add sample button
#    Then the user is navigated to a page with title Add a sample
#    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Add sample details
#    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
#    And the user clicks the Save and Continue button
#    ##Samples
#    Then the user is navigated to a page with title Manage samples
#    When the user clicks the Save and Continue button
#    ##Notes
#    Then the user is navigated to a page with title Add clinical notes
#    When the user fills in the Add Notes field
#    And the user clicks the Save and Continue button
#    ##Patient Choice
#    And the user navigates to the "<stage3>" stage
#    Then the user is navigated to a page with title Patient choice
#    And the user edits the patient choice status
#    When the user selects the option Adult (With Capacity) in patient choice category
#    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
#    And the user fills "<RecordedBy>" details in recorded by
#    And the user clicks on Continue Button
#    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
#    When the user selects the option Yes for the question Has research participation been discussed?
#    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
#    And the user clicks on Continue Button
#    When the user is in the section Patient signature
#    When the user fills PatientSignature details in patient signature
#    And the user clicks on submit patient choice Button
#    And the user should be able to see the patient choice form with success message
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Patient choice
#    When the user clicks on Continue Button
#    ##Print Forms
#    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NTS-6556_Cancer with Mandatory Stages Completed by User1
    #Test package - Updated by User1
    And the user waits max 15 minutes for the update Patient details Updated by User2 in the file NTS-6556_Cancer
    When the user navigates to the "<TestPackage>" stage
    Then the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6556_Cancer with Test Package details Updated by User1
    Examples:
      | TestPackage  | TestPackageUpdated | tumour_type                             | presentationType   | stage2  | stage3         | sampleType          | sampleState        | RecordedBy                            |
      | Test package | Priority=Urgent    | Haematological malignancy: solid sample | First presentation | Samples | Patient choice | Solid tumour sample | Tumour fresh fluid | ClinicianName=John:HospitalNumber=123 |

  #User2
  #Login as User B, Verified Test Package stage and do not submit referral
  @NTS-6556 @Z-LOGOUT
  Scenario Outline: Verified Test package stage of new referral updated by another user
    And the user waits max 15 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6556_Cancer
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6556_Cancer |
   #Test Package - Verified by User2
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the file NTS-6556_Cancer with Patient details Updated by User2
    And the user waits max 15 minutes for the update Test Package details Updated by User1 in the file NTS-6556_Cancer
    When the user navigates to the "<TestPackage>" stage
    Then the user verifies the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user updates the file NTS-6556_Cancer with Test Package details validated by User2

    Examples:
      | PatientDetails  | TestPackage  | TestPackageUpdated |
      | Patient details | Test package | Priority=Urgent    |

