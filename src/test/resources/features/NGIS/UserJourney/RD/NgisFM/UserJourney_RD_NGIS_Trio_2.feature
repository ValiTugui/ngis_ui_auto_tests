#@userJourneysRD
#@userJourneysRD_NgisFM
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NGIS_Trio_2 - UC10 - E2EUI-1009

  @NTS-4569 @Z-LOGOUT
#    @E2EUI-1009 @UseCase10
  Scenario Outline: NTS-4569: Use Case#10: Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice No - Search NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<ThreeParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Questions
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
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1965:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=19-12-1970:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice - Proband
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    ##Patient Choice - Family Members
    When the user edits patient choice for "<ThreeParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=17-07-1965 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |             |                 |
      | NHSNumber=NA:DOB=19-12-1970 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |             |                 |
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    And the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | Panels | Pedigree | FamilyMembers  | RecordedBy                            |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | Panels | Pedigree | Family members | ClinicianName=John:HospitalNumber=123 |