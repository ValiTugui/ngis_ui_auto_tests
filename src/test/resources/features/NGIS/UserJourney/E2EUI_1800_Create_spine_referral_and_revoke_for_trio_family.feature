@regression
@UserJourney
Feature: Create Spine Referral and Revoke for Trio Family - Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice Yes- Search Spine Patient

  @E2EUI-1800 @LOGOUT @BVT-P0 @v_1
  Scenario Outline: E2EUI-1800: User Journey by creating Spine Referral and Revoking for Trio Family - By Signature

    ##Referral creation  - provide nhs and dob of an existing patient
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | SPINE | Rare-Disease | NHSNumber=2000007945:DOB=07-06-1963 | GEL_SUPER_USER |
    ##Patient Details
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family - No of participants 3
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills the NotesPage with the "<NotesDetails>"
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=2000008461:DOB=14-05-1931 | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=2000007872:DOB=19-08-1939 | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    #Patient Choice - Family Details Provided below same as the Proband and Family Members (Signature option)
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                        | ChildAssent | ParentSignature                       |
      | NHSNumber=2000007945:DOB=07-06-1963 | Child                 | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |
      | NHSNumber=2000008461:DOB=14-05-1931 | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |
      | NHSNumber=2000007872:DOB=19-08-1939 | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |
#      | NHSNumber=9449310327:DOB=16-12-1970 | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |
    Then the "<PatientChoice>" stage is marked as Completed
    #Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user can see the selected panels listed
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above (proband and Family details)
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 |
      | NHSNumber=2000007945:DOB=07-06-1963 |
      | NHSNumber=2000008461:DOB=14-05-1931 |
      | NHSNumber=2000007872:DOB=19-08-1939 |
    ##Sobmitting Referral and Cancel Referral
    When the user submits the referral
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | NotesDetails                                              | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  | RevokeMessage                                                             |
      | Patient details | Requesting organisation | Maidstone            | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Urgent request because of deteriorating patient condition | Family members | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |
