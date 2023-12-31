@Concurrency
@Refresh
@Refresh_Cancer
Feature: NTS-6617:Cancer_new_referral_Tumours_edit: Navigate and verify the changes on Tumours stage done by another user
  ###FLOW
  #This is for editing the added tumour
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Tumours and its sub stages for the referral
  #User2 Navigate and verify the changes done by user1 in Tumours and its sub stages

  @NTS-6617 @NTS-6617_Scenario2 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and updated Tumours stage, when B accessed same referral then verified data updated by A.

    Given The user is login to the Test Order Service and create a new referral
      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | New Referral | NTS-6617_Scenario2 |
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
    Then the user updates the file NTS-6617_Scenario2 with Mandatory Stages Completed by User1
    #Tumours - Updated by User1
    And the user waits max 10 minutes for the update Responsible clinician details Updated by User2 in the file NTS-6617_Scenario2
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user updates the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6617_Scenario2 with Tumours details Updated by User1
    Examples:
      | Tumours | TumoursUpdated                               | tumour_type                             | presentationType   | pageTitle1                         | TumoursQuestionnaireUpdated  |
      | Tumours | TumourType=Brain tumour:SIHMDSLabID=VBN89756 | Haematological malignancy: solid sample | First presentation | Answer questions about this tumour | FirstPresentation=Recurrence |

  #User2
  #Login as User B, Verified Tumours stage and do not submit referral
  @NTS-6617 @NTS-6617_Scenario2 @Z-LOGOUT
  Scenario Outline: Verified Tumours stage of new referral updated by another user
   And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6617_Scenario2
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6617_Scenario2 |
   #Tumours - Verified by User2
    And the user navigates to the "<ResponsibleClinican>" stage
    And the user updates the file NTS-6617_Scenario2 with Responsible clinician details Updated by User2
    And the user waits max 15 minutes for the update Tumours details Updated by User1 in the file NTS-6617_Scenario2
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user verifies the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user updates the file NTS-6617_Scenario2 with Tumours details validated by User2

    Examples:
      | ResponsibleClinican   | Tumours | TumoursUpdated                               | pageTitle1                         | TumoursQuestionnaireUpdated  |
      | Responsible clinician | Tumours | TumourType=Brain tumour:SIHMDSLabID=VBN89756 | Answer questions about this tumour | FirstPresentation=Recurrence |
