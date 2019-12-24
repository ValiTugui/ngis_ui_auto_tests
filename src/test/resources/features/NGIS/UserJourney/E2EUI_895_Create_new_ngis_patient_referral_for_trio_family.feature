@BVT_UI_SMOKE_TEST_PACK
@userJourneysRD
Feature: NTS-3407 - RD flow - Create New NGIS Patient Referral for Trio Family - Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Not Given - Search Non Spine/NGIS Patient

  @NTS-3407 @LOGOUT
  Scenario Outline: NTS-3407: User Journey by creating new NGIS Referral for Trio Family - By Signature

    ##Create referral with new patient without providing NHS number
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
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
    ##Test Package - Trio family - No of participants -3
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
    ##Family Members - Family member details to be added - giving existing patient details
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=2000008461:DOB=14-05-1931 | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Trigonocephaly |
      | NHSNumber=2000008127:DOB=11-03-1942 | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Trigonocephaly |
    Then the "<FamilyMembers>" stage is marked as Completed
    #patient choice for the proband
    And the user clicks the Save and Continue button
    Then the "<PatientChoice>" stage is selected
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the "<PatientChoice>"
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above (Signature option)
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=2000008461:DOB=14-05-1931 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             |                 |
      | NHSNumber=2000008127:DOB=11-03-1942 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             |                 |
    #Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user can see the selected panels listed
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigress
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 |
      | NHSNumber=2000008461:DOB=14-05-1931 |
      | NHSNumber=2000008127:DOB=11-03-1942 |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | NotesDetails                                              | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  | RevokeMessage                                                             |
      | Patient details | Requesting organisation | Maidstone            | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Trigonocephaly | Notes | Urgent request because of deteriorating patient condition | Family members | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |
