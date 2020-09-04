@Concurrency
@Concurrency_newReferral_Cancer
Feature: Create New Referral for Cancer flow
  #User1
  @NTS-6618
    @Cancer_new_referral_refresh_data_samples_Scenario1 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and updated Samples stage, when B accessed same referral then verified data updated by A.

    Given The user is login to the Test Order Service and create a new referral
      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | New Referral | NRF1 |
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
    ##REsponsible Clinician
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
    ## Samples
    And the user navigates to the "<Samples>" stage
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinical notes
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    #Samples - Updated by User1
    And the user waits max 10 minutes for the update Tumours details Updated by User2 in the file NRF1
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Samples>" with "<SamplesUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user updates the page "<pageTitle1>" with "<SamplesQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Samples details Updated by User1
    Examples:
      | Samples | SamplesUpdated                                                                  | tumour_type                             | presentationType   | pageTitle1         | SamplesQuestionnaireUpdated                                     | sampleType          | sampleState        |
      | Samples | SampleType=Liquid tumour sample:SampleState=Fresh frozen tumour:SampleID=SD6756 | Haematological malignancy: solid sample | First presentation | Add sample details | SampleCollectionDate=20-05-2020:SampleComments=Sample Collected | Solid tumour sample | Tumour fresh fluid |

  #User2
  #Login as User B, Verified Samples stage and do not submit referral
  @Cancer_new_referral_refresh_data_samples_Scenario1 @Z-LOGOUT
  Scenario Outline: Verified Samples stage of new referral updated by another user
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
   #Samples - Verified by User2
    And the user navigates to the "<Samples>" stage
    And the "<Samples>" stage is marked as Mandatory To Do
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Samples>" with "<SamplesUpdated>"
    And the user navigates to the "<Tumours>" stage
    And the user updates the file NRF1 with Tumours details Updated by User2
    And the user waits max 15 minutes for the update Samples details Updated by User1 in the file NRF1
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Samples>" with "<SamplesUpdated1>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user verifies the page "<pageTitle1>" with "<SamplesQuestionnaireUpdated>"
    And the user updates the file NRF1 with Samples details validated by User2

    Examples:
      | Tumours | Samples | SamplesUpdated                                                | SamplesUpdated1                                                                 | pageTitle1         | SamplesQuestionnaireUpdated                                     |
      | Tumours | Samples | SampleType=Solid tumour sample:SampleState=Tumour fresh fluid | SampleType=Liquid tumour sample:SampleState=Fresh frozen tumour:SampleID=SD6756 | Add sample details | SampleCollectionDate=20-05-2020:SampleComments=Sample Collected |