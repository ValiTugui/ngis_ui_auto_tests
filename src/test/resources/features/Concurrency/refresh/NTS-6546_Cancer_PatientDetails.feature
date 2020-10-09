@Concurrency
@Refresh
@Refresh_Cancer
Feature: NTS-6546:Cancer_new_referral_PatientDetails: Navigate and verify the changes on Patient details stage done by another user
  ###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Patient details stage for the referral
  #User2 Navigate and verify the changes done by user1 in Patient details stage

   @NTS-6546 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral and update the Patient details stage ,Login as user B and navigates to Patient details and verify the changes done by User A.
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
    #Patient details stage updated by user1
    And the user waits max 10 minutes for the update Requsting Organisation details Updated by User2 in the file NRF1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Patient details Updated by User1
    And the user waits max 8 minutes for the update Patient details Validated by User2 in the file NRF1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_1>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Patient details Updated by User1
    And the user waits max 8 minutes for the update Patient details Validated by User2 in the file NRF1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_2>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Patient details Updated by User1
    And the user waits max 8 minutes for the update Patient details Validated by User2 in the file NRF1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_3>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Patient details Updated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated | PatientDetailsUpdated_1 | PatientDetailsUpdated_2 | PatientDetailsUpdated_3 | RequestingOrganisation  | testPackage  | tumour_type           | presentationType | sampleType           | sampleState         | RecordedBy                            |
      | Patient details | Title=Ms              | Title=Mr                | Title=Mrs               | Title=Mrss              | Requesting organisation | Test package | Solid tumour: primary | Recurrence       | Liquid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 |

  @NTS-6546 @Z-LOGOUT
  Scenario Outline: Verify Referral Banner by navigating to different stages when User A update Patient details

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    ##Patient Details verified by User2
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user updates the file NRF1 with Requsting Organisation details Updated by User2
    And the user waits max 8 minutes for the update Patient details Updated by User1 in the file NRF1
    And the user clicks the Save and Continue button
    When the user navigates to the "<TestPackage>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated>"
    Then the user updates the file NRF1 with Patient details Validated by User2
    And the user waits max 8 minutes for the update Patient details Updated by User1 in the file NRF1
    And the user clicks the Save and Continue button
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_1>"
    Then the user updates the file NRF1 with Patient details Validated by User2
    And the user waits max 8 minutes for the update Patient details Updated by User1 in the file NRF1
    And the user clicks the Save and Continue button
    When the user navigates to the "<Tumours>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_2>"
    Then the user updates the file NRF1 with Patient details Validated by User2
    And the user waits max 8 minutes for the update Patient details Updated by User1 in the file NRF1
    And the user clicks the Save and Continue button
    When the user navigates to the "<Samples>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_3>"
    Then the user updates the file NRF1 with Patient details Validated by User2

    Examples:
      | RequestingOrganisation  | ReferralBannerUpdated | ReferralBannerUpdated_1 | ReferralBannerUpdated_2 | ReferralBannerUpdated_3 | TestPackage  | ResponsibleClinician  | Tumours | Samples |
      | Requesting organisation | Title=Ms              | Title=Mr                | Title=Mrs               | Title=Mrss              | Test package | Responsible clinician | Tumours | Samples |

