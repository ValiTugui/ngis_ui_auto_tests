@Concurrency
@Concurrency_test
@Concurrency_existingReferral_Cancer
Feature: Submit Existing Referral for Cancer flow
  #User1
  @NTS-6469
    @Cancer_existing_referral_all_stages_validate_entity @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Fibro-Osseous Tumour of Bone Differential | CONCURRENT_USER1_NAME | r20279927085 | NRF1 |
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    #Patient Details - Verify
    And the user waits max 20 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NRF1 with Patient details validated by User1
    ##Requesting Organization - Verify
    And the user waits max 8 minutes for the update Requesting Organisation Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user updates the file NRF1 with Requesting Organisation validated by User1
     #Test Package - Verify
    And the user waits max 8 minutes for the update Test Package details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<TestPackage>" stage
    Then the user verifies the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user updates the file NRF1 with Test Package details validated by User1
    ##Responsible Clinician - Verify
    And the user waits max 8 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user updates the file NRF1 with Responsible Clinician details validated by User1
    ## Tumours - Verify
    And the user waits max 8 minutes for the update Tumours details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user verifies the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user updates the file NRF1 with Tumours details validated by User1
    ## Samples - Verify
    And the user waits max 8 minutes for the update Samples details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user verifies the stage "<Samples>" with "<SamplesUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed
    And the user verifies the page "<pageTitle2>" with "<SamplesQuestionnaireUpdated>"
    And the user updates the file NRF1 with Samples details validated by User1
    ##Notes - verify
    And the user waits max 8 minutes for the update Notes details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    Then the user updates the file NRF1 with Notes details validated by User1
    ##PatientChoice- Verify
    And the user waits max 15 minutes for the update Patient Choice details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientChoice>" stage
    Then the user verifies the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NRF1 with Patient Choice details validated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                      | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | Tumours | TumoursUpdated       | pageTitle1                         | TumoursQuestionnaireUpdated | Samples | SamplesUpdated   | pageTitle2         | SamplesQuestionnaireUpdated     | Notes | NotesUpdated           | PatientChoice  | PatientChoiceDetailsUpdated |
      | Patient details | FirstName=Jhonny      | Requesting organisation | Epsom and St Helier University Hospitals NHS Trust | Test package | Priority=Urgent    | Responsible clinician | Department=Ireland,UK              | Tumours | SIHMDSLabID=N7846509 | Answer questions about this tumour | FirstPresentation=Unknown   | Samples | SampleID=J098078 | Add sample details | SampleComments=Sample Collected | Notes | Notes updated by user2 | Patient choice | Authorised by clinician     |

  #User2
  @Cancer_existing_referral_all_stages_validate_entity @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | r20279927085 | NRF1 |
    #Below step is for existing referrals
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    # Patient Details - Update
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with PatientDetails Updated by User2
     # Requesting Organisation - Update
    And the user waits max 8 minutes for the update Patient details validated by User1 in the file NRF1
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Requesting Organisation Updated by User2
    #Test Package - Update
    And the user waits max 8 minutes for the update Requesting Organisation validated by User1 in the file NRF1
    When the user navigates to the "<TestPackage>" stage
    Then the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Test Package details Updated by User2
    #Responsible Clinician- Update
    And the user waits max 8 minutes for the update Test Package details validated by User1 in the file NRF1
    When the user navigates to the "<ResponsibleClinician>" stage
    And the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Responsible Clinician details Updated by User2
#    # Tumours - Update
    And the user waits max 8 minutes for the update Responsible Clinician details validated by User1 in the file NRF1
    When the user navigates to the "<Tumours>" stage
    And the user selects the existing tumour on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Tumours>" with "<TumoursUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle1>" page is displayed
    And the user updates the page "<pageTitle1>" with "<TumoursQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Tumours details Updated by User2
    ## Samples - Update
    And the user waits max 8 minutes for the update Tumours details validated by User1 in the file NRF1
    When the user navigates to the "<Samples>" stage
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    And the user updates the stage "<Samples>" with "<SamplesUpdated>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed
    And the user updates the page "<pageTitle2>" with "<SamplesQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Samples details Updated by User2
    ##Notes - Update
    And the user waits max 8 minutes for the update Samples details validated by User1 in the file NRF1
    When the user navigates to the "<Notes>" stage
    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Notes details Updated by User2
    ##PatientChoice- Update
    And the user waits max 15 minutes for the update Notes details validated by User1 in the file NRF1
    When the user navigates to the "<PatientChoice>" stage
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NRF1 with Patient Choice details Updated by User2
    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                      | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | Tumours | TumoursUpdated       | pageTitle1                         | TumoursQuestionnaireUpdated | Samples | SamplesUpdated   | pageTitle2         | SamplesQuestionnaireUpdated     | Notes | NotesUpdated           | PatientChoice  | PatientChoiceDetailsUpdated |
      | Patient details | FirstName=Jhonny      | Requesting organisation | Epsom and St Helier University Hospitals NHS Trust | Test package | Priority=Urgent    | Responsible clinician | Department=Ireland,UK              | Tumours | SIHMDSLabID=N7846509 | Answer questions about this tumour | FirstPresentation=Unknown   | Samples | SampleID=J098078 | Add sample details | SampleComments=Sample Collected | Notes | Notes updated by user2 | Patient choice | Authorised by clinician     |
