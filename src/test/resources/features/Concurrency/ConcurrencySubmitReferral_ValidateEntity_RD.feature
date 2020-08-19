@Concurrency
@Concurrency_newReferral_RD_Entity
Feature: Submit New Referral for RD

  @newreferral_RD @Z-LOGOUT
    @NTS-6463
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating an entity in every stage upon referral submission by A.

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
    And the user selects the number of participants as "<TwoParticipants>"
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
    And the user clicks the Save and Continue button
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-1978:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    And the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    Then the user navigates to the "<PatientChoice>" stage
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<TwoParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-1978 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient conversation happened; form to follow |             |                 |
    Then the user is navigated to a page with title Patient choice
    Then the "<PatientChoice>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    # Referral Submission by User1 after Patient Details updated by user 2
    And the user waits max 10 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the patient details data for an entity"<Patientdetailsupdated>" updated
    Then the user updates the file NRF1 with Patient details validated by User1
    # Referral Submission by User1 after Requesting Organisation details updated by user 2
    And the user waits max 5 minutes for the update Requesting Organisation details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the requesting organisation value "East London NHS Foundation Trust"
    Then the user updates the file NRF1 with Requesting organisation details validated by User1
  #Referral Submission by User1 after Test package details updated by user2
    And the user waits max 3 minutes for the update Test Package details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the Test Package with "OneParticipant"
    When the user updates the file NRF1 with Test Package details validated by User1
     ###Referral Submission by User1 after Responsible Clinician details updated by user2
    And the user waits max 3 minutes for the update Responsible Clinician details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the Responsible Clinician details with an entity updated
    When the user updates the file NRF1 with Responsible Clinician details validated by User1
    ###Referral Submission by User1 after Clinicalquestions details updated by User2
    And the user waits max 3 minutes for the update Clinicalquestions details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the Clinical details for an entity updated
    When the user updates the file NRF1 with Clinicalquestions details validated by User1
    ####Referral Submission by User1 after Notes updated by user2
    And the user waits max 5 minutes for the update Notes Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the notes details data "<Notesupdated>"
    Then the user updates the file NRF1 with Notes validated by User1
    ####Referral Submission by User1 after Family Member details updated by user2
    And the user waits max 8 minutes for the update Family Members details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate an entity updated in FamilyMembers details
    Then the user updates the file NRF1 with Family Members details validated by User1
    ####Referral Submission by User1 after Patient choice details updated by user2
    And the user waits max 10 minutes for the update Patient Choice details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads and validate an entity updated in Patient Choice stage
    Then the user updates the file NRF1 with Patient Choice details validated by User1
    ####Referral Submission by User1 after Panels details updated by user2
    And the user waits max 8 minutes for the update Panels details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads and validate an entity updated in Panels stage
    Then the user updates the file NRF1 with Panels details validated by User1
    ####Referral Submission by User1 after Pedigree details updated by user2
    And the user waits max 8 minutes for the update Pedigree details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
  Then the user reads and validate an entity updated in Pedigree
    Then the user updates the file NRF1 with Pedigree details validated by User1
    ## Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | RequestingOrganisation  | testPackage  | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | Notes | PatientChoiceStage | ClinicianName      | Panels | Pedigree | PedigreeStage | ProbandDetails              | TwoParticipants |Notesupdated|
      | Requesting organisation | Test package | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree | Pedigree      | NHSNumber=NA:DOB=25-11-1987 | 2               |User2UpdatedNotes|

  @newreferral_RD @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
      # Patient Detials
    When the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    Then the user is navigated to a page with title Check your patient's details
    When the user clears the date of birth field
    And the user fills in the date of birth "<dateOfBirth>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with PatientDetails Updated by User2
   # Requesting Organisation updated by User2
    When the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    And the user navigates to the "<RequestingOrganisation>" stage
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    And the user updates the file NRF1 with Requesting Organisation details Updated by User2
    #Test Package - Two participants updated by User2
    And the user waits max 5 minutes for the update Requesting organisation details validated by User1 in the file NRF1
    When the user navigates to the "<TestPackage>" stage
    And the user selects the "Routine"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    And the user updates the file NRF1 with Test Package details Updated by User2
    ##Responsible Clinician updated by User2
    And the user waits max 3 minutes for the update Test Package details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails2>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    And the user updates the file NRF1 with Responsible Clinician details Updated by User2
    ##Clinical Question updated by User2
    And the user waits max 3 minutes for the update Responsible Clinician details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails2>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    And the user updates the file NRF1 with Clinicalquestions details Updated by User2
    ##Notes updated by User2
    And the user waits max 2 minutes for the update Clinicalquestions details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    And the user updates the file NRF1 with Notes Updated by User2
    ##Family Members - Adding two members - Father and Mother  updated by User2
    And the user waits max 5 minutes for the update Notes validated by User1 in the file NRF1
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user added additional phenotypes "<Phenotypes>" to the family member
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user updates the file NRF1 with Family Members details Updated by User2
   ##Patient Choice updated by User2
    And the user waits max 5 minutes for the update Family Members details validated by User1 in the file NRF1
    Then the user navigates to the "<PatientChoice>" stage
    When the user selects the proband
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option No for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    Then the user is navigated to a page with title Patient choice
    Then the "<PatientChoice>" stage is marked as Completed
    And the user updates the file NRF1 with Patient Choice details Updated by User2
     ##Panels updated by User2
    And the user waits max 8 minutes for the update Patient Choice details validated by User1 in the file NRF1
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks on Complete button and button will show tick marked
    Then the "<Panels>" stage is marked as Completed
    And the user updates the file NRF1 with Panels details Updated by User2
  #Pedigree updated by User2
    And the user waits max 8 minutes for the update Panels details validated by User1 in the file NRF1
    Then the user is navigated to a page with title Build a pedigree
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Clinical
    Then the user should be able to update the Age of Onset with "<AgeOfOnset>"
    And the user is able to close the popup by clicking on the close icon
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    And the user updates the file NRF1 with Pedigree details Updated by User2
    Examples:
      | ResponsibleClinicianDetails2 | ClinicalQuestionDetails2        | ClinicianName2      | searchPanels     | Notes | PatientDetails  | RequestingOrganisation  | TestPackage  | ResponsibleClinician  | ClinicalQuestion   | Notes | Panels | Pedigree | PatientChoice  | FamilyMembers  | gender | lifeStatus | AgeOfOnset | dateOfBirth |
      | Department=Greenwood,uk      | HpoPhenoType=Scrotal hypoplasia | ClinicianName=Smith | Optic neuropathy | User2UpdatedNotes| Patient details | Requesting organisation | Test package | Responsible clinician | Clinical questions | Notes | Panels | Pedigree | Patient choice | Family members | Other  | Deceased   | 01,02      | 20-10-2010  |

