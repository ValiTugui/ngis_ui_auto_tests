#Some History
#This test used Spine patient data as proband , and creates referrals for the same proband patient when we run this test multiple times (1 proband ->  many cancelled referrals)
#This causes a issue on DDF when referrals are submitted/cancelled as the payload for this patient becomes very bigger in size
#Hence removing it from the automation nightly runs by commenting out the tags - asked by Rex Thankaswamy
#Arthur release has been postponed because of this proband has bulk referrals
#Please run this Test cautiously especially if you are planning to run MORE THAN ONCE as DDF is not designed to handle this kind of situation
#@userJourneys
#@userJourneysRD
#@BVT-P0
@SYSTEM_INTEGRATION_TEST_SPINE
Feature: E2EUI-1800 - Create Spine Referral and Revoke for Trio Family - Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice Yes- Search Spine Patient

  @NTS-3377 @Z-LOGOUT
#    @E2EUI-1800  @v_1
  Scenario Outline: E2EUI-1800: User Journey by creating Spine Referral and Revoking for Trio Family - By Signature

    ###Referral creation  - provide nhs and dob of an existing patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=2000008178:DOB=29-12-1967:Gender=Male |
    ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms

      ###Patient Details
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - Trio family - No of participants 3
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=11-03-1942:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=12-02-1939:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ###patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes and uploading recording form
    And the user save patient choice form and continue
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-1942 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient has agreed to the test |             |                 |
      | NHSNumber=NA:DOB=12-02-1939 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient has agreed to the test |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    ###Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms - FamilyDetails -same as provided above Family details
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=11-03-1942 |
      | NHSNumber=NA:DOB=12-02-1939 |
    ###Submitting Referral and Cancel Referral
    When the user submits the referral
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name                    | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | NotesDetails                                              | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  | RevokeMessage                                                             |
      | Patient details | Requesting organisation | Maidstone And Tunbridge Wells NHS Trust | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Urgent request because of deteriorating patient condition | Family members | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |