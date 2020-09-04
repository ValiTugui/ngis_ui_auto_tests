@Concurrency
@Concurrency_newReferral_RD
Feature: Submit New Referral to validate a stage

  #User1
  @RD_new_referral_verifyStage @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating a stage upon referral submission by A.

#Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NRF1 |
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
#    ##Test Package - proband only
    When the user navigates to the "<testPackage>" stage
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    And the "<testPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
#    ##Notes
    And the user clicks the Save and Continue button
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1

    #### verify patient details after changes done by B
    And the user waits max 4 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NRF1 with Patient details validated by User1
     # Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | PatientChoiceStage | ClinicianName      | PatientDetails  | PatientDetailsUpdated                                                                                       | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated                       | ClinicalQuestions  | ClinicalQuestionDetailsUpdated        | Notes | NotesUpdated        | FamilyMembers  | FamilyMemberDetailsUpdated                                             | FamilyMemberClinicalDetailsUpdated    | Panels | PanelsDetailsUpdated                                                               | PatientChoice  | PatientChoiceDetailsUpdated |
      | Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Patient choice     | ClinicianName=John | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | NoOfParticipants=2 | Responsible clinician | FirstName=edward:LastName=thomas:Department=woodspark,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=1,1 | Notes | NotesupdatedbyUser2 | Family members | LifeStatus=Alive:Ethnicity=B - White - Irish:RelationShipToProband=Son | DiseaseStatus=Affected:AgeOfOnset=1,2 | Panels | AdditionalPanels=Hereditary ataxia - adult onset:SuggestedPanels=Holoprosencephaly | Patient choice | Authorised by clinician     |


   #User2
  @RD_new_referral_verifyStage @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
    #Below step is for new referrals
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
#     Patient Details - Update By user2
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with PatientDetails Updated by User2


    Examples:
      | PatientDetails  | PatientDetailsUpdated                                                                                       |
      | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish |
