@Concurrency
@Concurrency_RD
Feature: NTS-6462:RD_new_referral_all_stages

  ###FLOW
  #User1 Login to a New Referral
  #User2 Login to the same referral
  #User2 updated each stage for the referral
  #User1 Submit and verify the changes done by user2 in each stages

  @NTS-6462 @NTS-6465 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.

   ##Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-6462 |
    ##Patient Details
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Wye Valley NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -3
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=11-01-1971:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Unaffected:AgeOfOnset=02,02:HpoPhenoType=Dystonia |
    And the user clicks the Save and Continue button
    ##Patient Choice - Proband
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    #Patient Choice - Family Details Provided below should be same as above
    When the user edits patient choice for "<ThreeParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-01-1971 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NTS-6462 with Mandatory Stages Completed by User1
    And the user waits max 4 minutes for the update PatientDetails Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NTS-6462 with Patient details validated by User1
   ##Requesting Organization - Verify
    And the user waits max 8 minutes for the update Requesting Organisation Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user updates the file NTS-6462 with Requesting Organisation validated by User1
   ##Test Package - Verify
    And the user waits max 12 minutes for the update Test Package details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<TestPackage>" stage
    And the user verifies the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user updates the file NTS-6462 with Test Package details validated by User1
   ##Responsible Clinician - Verify
    And the user waits max 12 minutes for the update Responsible Clinician details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    Then the user updates the file NTS-6462 with Responsible Clinician details validated by User1
   ##ClinicalQuestions - Verify
    And the user waits max 8 minutes for the update Clinical Questions details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user verifies the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdated>"
    Then the user updates the file NTS-6462 with Clinical Questions details validated by User1
   ##Notes
    And the user waits max 8 minutes for the update Notes details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    Then the user updates the file NTS-6462 with Notes details validated by User1
   ##FamilyMembers - Verify
    And the user waits max 8 minutes for the update Family Member details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<FamilyMembers>" stage
    Then the user verifies the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdated>"
    Then the user updates the file NTS-6462 with Family Member details validated by User1
   ##FamilyMembersclinicalquestions - Verify
    And the user waits max 8 minutes for the update Family Member clinical details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    Then the user verify the page "Add family member details" with "<FamilyMemberClinicalDetailsUpdated>"
    Then the user updates the file NTS-6462 with Family Member clinical details validated by User1
   ##PatientChoice- Verify
    And the user waits max 15 minutes for the update Patient Choice details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientChoice>" stage
    Then the user verifies the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NTS-6462 with Patient Choice details validated by User1
   ##Panels-Verify
    And the user waits max 8 minutes for the update Panels details Updated by User2 in the file NTS-6462
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Panels>" stage
    Then the user verifies the stage "<Panels>" with "<PanelsDetailsUpdated>"
    Then the user updates the file NTS-6462 with Panels details validated by User1
   ## Finally User1 submit Referral Successfully
   ##And the user submits the referral
   ##Then the submission confirmation message "Your referral has been submitted" is displayed
   ##And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | PatientDetailsUpdated                                                                                       | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated                       | ClinicalQuestions  | ClinicalQuestionDetailsUpdated        | Notes | NotesUpdated        | FamilyMembers  | FamilyMemberDetailsUpdated                                             | FamilyMemberClinicalDetailsUpdated    | Panels | PanelsDetailsUpdated                                                               | PatientChoice  | PatientChoiceDetailsUpdated     | TwoParticipant | ResponsibleClinicianDetails                              | ClinicalQuestionDetails                                                     | RecordedBy         |
      | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | NoOfParticipants=2 | Responsible clinician | FirstName=edward:LastName=thomas:Department=woodspark,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=1,1 | Notes | NotesupdatedbyUser2 | Family members | LifeStatus=Alive:Ethnicity=B - White - Irish:RelationShipToProband=Son | DiseaseStatus=Affected:AgeOfOnset=1,2 | Panels | AdditionalPanels=Hereditary ataxia - adult onset:SuggestedPanels=Holoprosencephaly | Patient choice | Proband=Authorised by clinician | 2              | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | ClinicianName=John |

   #User2
  @NTS-6462 @NTS-6465 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    And the user waits max 30 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6462
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6462 |
    #Below step is for new referrals
    #Patient Details - Update
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with PatientDetails Updated by User2
    #Requesting Organisation - Update
    And the user waits max 8 minutes for the update Patient details validated by User1 in the file NTS-6462
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Requesting Organisation Updated by User2
    #Test Package - Update
    And the user waits max 8 minutes for the update Requesting Organisation validated by User1 in the file NTS-6462
    When the user navigates to the "<TestPackage>" stage
    And the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6462 with Test Package details Updated by User2
    #Responsible Clinician- Update
    And the user waits max 10 minutes for the update Test Package details validated by User1 in the file NTS-6462
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Responsible Clinician details Updated by User2
    #ClinicalQuestions - Update
    And the user waits max 8 minutes for the update Responsible Clinician details validated by User1 in the file NTS-6462
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user updates the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Clinical Questions details Updated by User2
    #Notes
    And the user waits max 8 minutes for the update Clinical Questions details validated by User1 in the file NTS-6462
    When the user navigates to the "<Notes>" stage
    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Notes details Updated by User2
    #FamilyMembers - Update
    And the user waits max 15 minutes for the update Notes details validated by User1 in the file NTS-6462
    When the user navigates to the "<FamilyMembers>" stage
    Then the user updates the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdate>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Family Member details Updated by User2
    #FamilyMembersclinicalquestions - Update
    And the user waits max 15 minutes for the update Family Member details validated by User1 in the file NTS-6462
    Then the user update the page "Add family member details" with "<FamilyMemberClinicalDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Family Member clinical details Updated by User2
    #PatientChoice- update
    And the user waits max 15 minutes for the update Family Member clinical details validated by User1 in the file NTS-6462
    When the user navigates to the "<PatientChoice>" stage
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    Then the user updates the file NTS-6462 with Patient Choice details Updated by User2
    #Panels- update
    And the user waits max 8 minutes for the update Patient Choice details validated by User1 in the file NTS-6462
    When the user navigates to the "<Panels>" stage
    Then the user updates the stage "<Panels>" with "<PanelsDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6462 with Panels details Updated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated                                                                                       | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated                       | ClinicalQuestions  | ClinicalQuestionDetailsUpdated        | FamilyMemberDetailsUpdate                                              | FamilyMemberClinicalDetailsUpdated      | Notes | NotesUpdated        | PatientChoiceDetailsUpdated | RequestingOrganisation  | TestPackage  | ResponsibleClinician  | NotesUpdated  | Panels | PanelsDetailsUpdated                             | PatientChoice  | FamilyMembers  |
      | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | NoOfParticipants=2 | Responsible clinician | FirstName=edward:LastName=thomas:Department=woodspark,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=1,1 | LifeStatus=Alive:Ethnicity=B - White - Irish:RelationShipToProband=Son | DiseaseStatus=Affected:AgeOfOnset=01,02 | Notes | NotesupdatedbyUser2 | Authorised by clinician     | Requesting organisation | Test package | Responsible clinician | Notes Updated | Panels | AdditionalPanels=Hereditary ataxia - adult onset | Patient choice | Family members |
