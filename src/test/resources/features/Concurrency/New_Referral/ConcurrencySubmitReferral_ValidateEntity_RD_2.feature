@Concurrency
@Concurrency_newReferral_RD_Entity

Feature: Submit Referral for RD

  @Newreferral_RD @Z-LOGOUT
    @NTS_6467_1
  Scenario Outline: Login as User A, Create a New Referral, Complete all stages and do not submit referral and validate the data updated, when B is updating every stage upon referral submission by A.

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
##Test Package - proband only
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
##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
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
##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
##Print forms
    And  the user is navigated to a page with title Print sample forms
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
##Referral Submission by User1 after Family Member details updated by user2
    And the user waits max 10 minutes for the update Family Members details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then  Then the user reads & validate an entity updated in FamilyMembers details
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Family Members details validated by User1
##Referral Submission by User1 after Responsible Clinician details updated by user2
    And the user waits max 4 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then Then the user reads & validate the Responsible Clinician details with an entity updated
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Responsible Clinician details validated by User1
##Referral Submission by User1 after Clinicalquestions details updated by User2
    And the user waits max 4 minutes for the update Clinicalquestions details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
#    Then  Then the user reads & validate the Clinical details for an entity updated
    When the user navigates to the "<testPackage>" stage
    And the user selects the "Routine"
    When the user updates the file NRF1 with Clinicalquestions details validated by User1
## Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | Notes | FamilyMembers  | PatientChoiceStage | ClinicianName      | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Notes | Family members | Patient choice     | ClinicianName=John | Panels | Pedigree |

#Login as User B

  @newreferral_RD @Z-LOGOUT

  Scenario Outline: Update the stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
##Test Package - Two participants updated by User2
    When the user navigates to the "<testPackage>" stage
    And the user selects the number of participants as "<TwoParticipants>"
    And the user clicks the Save and Continue button
    And the "<testPackage>" stage is marked as Completed
##Family Members - Adding two members - Father and Mother  updated by User2
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-1978:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Family Members details Updated by User2
##Responsible Clinician updated by User2
    And the user waits max 8 minutes for the update Family Members details validated by User1 in the file NRF1
    And the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    When the user clicks the Additional Clinician link
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails2>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    And the user updates the file NRF1 with Responsible Clinician details Updated by User2
##Clinical Question updated by User2
    And the user waits max 4 minutes for the update Responsible Clinician details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails2>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    And the user updates the file NRF1 with Clinicalquestions details Updated by User2



    Examples:
      | PatientDetails  | testPackage  | TwoParticipants | FamilyMembers  | hyperlinkText | ResponsibleClinician  | ResponsibleClinicianDetails2                             | ClinicalQuestionDetails2        | ClinicalQuestion   |
      | Patient details | Test package | 2               | Family members | Add another   | Responsible clinician | FirstName=edward:LastName=thomas:Department=woodspark,uk | HpoPhenoType=Scrotal hypoplasia | Clinical questions |
