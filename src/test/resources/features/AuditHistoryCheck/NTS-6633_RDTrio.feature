@AuditHistory
@AuditHistory_RD
Feature: NTS-6633:Audit history for a RD referral having 3 family members (Trio) or more participant
  ###FLOW
  # submit the new referral and entered all mandatory stages
  # Login to the same referral
  # updated or change in the referral like :- Changed Responsible Clinician
#   download the updated referral form the print form stage
#   get the Patient ID from the Patient Search and extract the Genomic Record data via patient ID using SQL query
#  Then user can extract the sample processing details from Gel1001 and Gel1005 via patient ID using SQL query
  @NTS-6633 @Z-LOGOUT
  Scenario Outline: NTS-6633:Audit history for a RD referral having 3 family members (Trio) or more participant
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=27-07-1987:Gender=Male |
     ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - Trio family - No of participants 3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members - Adding two members - Full Sibling and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-2017:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
      | NHSNumber=NA:DOB=12-02-1940:Gender=Female:Relationship=Mother     | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###patient choice for the proband
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=11-03-2017 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=12-02-1940 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
   ##Requesting Organisation - Update
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
     ##Test Package - Update
    When the user navigates to the "<TestPackage>" stage
    And the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
    And the user clicks the Save and Continue button
   ##Responsible Clinician- Update
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
    And the user clicks the Save and Continue button
    ##Notes
    When the user navigates to the "<Notes>" stage
    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | ResponsibleClinicianDetailsUpdated | PatientDetailsUpdated          | TestPackageUpdated | RequestingOrganisationUpdated                  | NotesUpdated        |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Panels | Pedigree | FirstName=edward                   | FirstName=Jhon12:Gender=Female | Priority=Urgent    | South London and Maudsley NHS Foundation Trust | Updated notes value |

     #User2
  #Login as User B, update the stage and do not submit referral
##  @NTS-6633 @Z-LOGOUT
#  Scenario Outline: Verified Responsible Clinician stage of new referral updated by another user
#    And the user waits max 20 minutes for the update Referral Submitted by User1 in the file NRF1
#    Given The user is login to the Test Order Service and access the given referral
#      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
#   #Responsible Clinician - Verified by User2
##    When the user is navigated to a page with title Add a requesting organisation
#   ##Patient Details - Update
#    When the user navigates to the "<PatientDetails>" stage
#    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
#    And the user clicks the Save and Continue button
#   ##Requesting Organisation - Update
#    When the user navigates to the "<RequestingOrganisation>" stage
#    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
#    And the user clicks the Save and Continue button
#   ##Test Package - Update
#    When the user navigates to the "<TestPackage>" stage
#    And the user updates the stage "<TestPackage>" with "<TestPackageUpdated>"
#    And the user clicks the Save and Continue button
#   ##Responsible Clinician- Update
#    When the user navigates to the "<ResponsibleClinician>" stage
#    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdated>"
#    And the user clicks the Save and Continue button
#    ##Notes
#    When the user navigates to the "<Notes>" stage
#    Then the user updates the stage "<Notes>" with "<NotesUpdated>"
#    And the user clicks the Save and Continue button
#
#
#    Examples:
#      | PatientDetails  | PatientDetailsUpdated          | RequestingOrganisation  | RequestingOrganisationUpdated                  | TestPackage  | TestPackageUpdated | ResponsibleClinician  | ResponsibleClinicianDetailsUpdated | ClinicalQuestions  | ClinicalQuestionDetailsUpdated | FamilyMemberDetailsUpdate | FamilyMemberClinicalDetailsUpdated | Notes | NotesUpdated        |
#      | Patient details | FirstName=Jhon12:Gender=Female | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | Priority=Urgent    | Responsible clinician | FirstName=edward:LastName=Nort     | Clinical questions | AgeOfOnset=1,1                 | LifeStatus=Alive          | DiseaseStatus=Affected             | Notes | NotesupdatedbyUser2 |
