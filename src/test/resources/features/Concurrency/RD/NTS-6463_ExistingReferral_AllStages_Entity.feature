@Concurrency
@Concurrency_RD
Feature: NTS-6463:RD_existing_referral_all_stages_entity

  ###FLOW
  #User1 Login to an existing Referral
  #User2 Login to the same referral
  #User2 updated each stage for the referral
  #User1 Submit and verify the changes done by user2 in each stages

  @NTS-6463 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | r20204704011 | NRF1 |

    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    And the user waits max 4 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NRF1 with Patient details validated by User1
   #Requesting Organization - Verify
    And the user waits max 8 minutes for the update Requesting Organisation Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user updates the file NRF1 with Requesting Organisation validated by User1
   #Test Package - Verify
    And the user waits max 12 minutes for the update Test Package details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<TestPackage>" stage
    And the user verifies the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user updates the file NRF1 with Test Package details validated by User1
   #Responsible Clinician - Verify
    And the user waits max 12 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    Then the user updates the file NRF1 with Responsible Clinician details validated by User1
   #ClinicalQuestions - Verify
    And the user waits max 8 minutes for the update Clinical Questions details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user verifies the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdated>"
    Then the user updates the file NRF1 with Clinical Questions details validated by User1
   #Notes - verify
    And the user waits max 8 minutes for the update Notes details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    Then the user updates the file NRF1 with Notes details validated by User1
   #FamilyMembers - Verify
    And the user waits max 8 minutes for the update Family Member details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<FamilyMembers>" stage
    Then the user verifies the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdated>"
    Then the user updates the file NRF1 with Family Member details validated by User1
   #FamilyMembersclinicalquestions - Verify
    And the user waits max 8 minutes for the update Family Member clinical details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    Then the user verify the page "Add family member details" with "<FamilyMemberClinicalDetailsUpdated>"
    Then the user updates the file NRF1 with Family Member clinical details validated by User1
   #PatientChoice- Verifyt Details - Verify
    And the user waits max 15 minutes for the update Patient Choice details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientChoice>" stage
    Then the user verifies the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NRF1 with Patient Choice details validated by User1
   #Panels-Verify
    And the user waits max 8 minutes for the update Panels details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Panels>" stage
    Then the user verifies the stage "<Panels>" with "<PanelsDetailsUpdated>"
    Then the user updates the file NRF1 with Panels details validated by User1
   ## Finally User1 submit Referral Successfully
   ##And the user submits the referral
   ##Then the submission confirmation message "Your referral has been submitted" is displayed
   ##And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | ClinicalQuestions  | ClinicalQuestionDetailsUpdated | Notes | NotesUpdated        | FamilyMembers  | FamilyMemberDetailsUpdated | FamilyMemberClinicalDetailsUpdated | Panels | PanelsDetailsUpdated                                                               | PatientChoice  | PatientChoiceDetailsUpdated     |
      | Patient details | FirstName=Jhon12      | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | NoOfParticipants=2 | Responsible clinician | FirstName=edward                   | Clinical questions | AgeOfOnset=1,1                 | Notes | NotesupdatedbyUser2 | Family members | LifeStatus=Alive           | DiseaseStatus=Affected             | Panels | AdditionalPanels=Hereditary ataxia - adult onset:SuggestedPanels=Holoprosencephaly | Patient choice | Proband=Authorised by clinician |

   #User2
  @NTS-6463 @Z-LOGOUT
  Scenario Outline: Update entity in every stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | r20204704011 | NRF1 |
    #Below step is for new referrals
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
   ##Patient Details - Update
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with PatientDetails Updated by User2
   ##Requesting Organisation - Update
    And the user waits max 8 minutes for the update Patient details validated by User1 in the file NRF1
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Requesting Organisation Updated by User2
   ##Test Package - Update
    And the user waits max 8 minutes for the update Requesting Organisation validated by User1 in the file NRF1
    When the user navigates to the "<TestPackage>" stage
    And the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Test Package details Updated by User2
   ##Responsible Clinician- Update
    And the user waits max 10 minutes for the update Test Package details validated by User1 in the file NRF1
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Responsible Clinician details Updated by User2
   ##ClinicalQuestions - Update
    And the user waits max 8 minutes for the update Responsible Clinician details validated by User1 in the file NRF1
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user updates the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Clinical Questions details Updated by User2
   ##Notes
    And the user waits max 8 minutes for the update Clinical Questions details validated by User1 in the file NRF1
    When the user navigates to the "<Notes>" stage
    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Notes details Updated by User2
   ##FamilyMembers - Update
    And the user waits max 15 minutes for the update Notes details validated by User1 in the file NRF1
    When the user navigates to the "<FamilyMembers>" stage
    Then the user updates the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdate>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Family Member details Updated by User2
   ##FamilyMembersclinicalquestions - Update
    And the user waits max 15 minutes for the update Family Member details validated by User1 in the file NRF1
    Then the user update the page "Add family member details" with "<FamilyMemberClinicalDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Family Member clinical details Updated by User2
   ##PatientChoice- update
    And the user waits max 15 minutes for the update Family Member clinical details validated by User1 in the file NRF1
    When the user navigates to the "<PatientChoice>" stage
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NRF1 with Patient Choice details Updated by User2
   ##Panels- update
    And the user waits max 8 minutes for the update Patient Choice details validated by User1 in the file NRF1
    When the user navigates to the "<Panels>" stage
    Then the user updates the stage "<Panels>" with "<PanelsDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Panels details Updated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | ClinicalQuestions  | ClinicalQuestionDetailsUpdated | FamilyMemberDetailsUpdate | FamilyMemberClinicalDetailsUpdated | Notes | NotesUpdated        | PatientChoiceDetailsUpdated | RequestingOrganisation  | ResponsibleClinician  | NotesUpdated  | Panels | PanelsDetailsUpdated                             | PatientChoice  | FamilyMembers  |
      | Patient details | FirstName=Jhon12      | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | NoOfParticipants=2 | Responsible clinician | FirstName=edward                   | Clinical questions | AgeOfOnset=1,1                 | LifeStatus=Alive          | DiseaseStatus=Affected             | Notes | NotesupdatedbyUser2 | Authorised by clinician     | Requesting organisation | Responsible clinician | Notes Updated | Panels | AdditionalPanels=Hereditary ataxia - adult onset | Patient choice | Family members |