#@userJourneysRD
#@userJourneysRD_NewFM
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NEW_Trio_1 - UC08 - E2EUI-844

  @NTS-4564 @Z-LOGOUT
#    @E2EUI-844 @UseCase08
  Scenario Outline: NTS-4564: Use Case#08: Create Referral for Trio Family with Default Data and Add Family Members to Test with Patient Choice Yes - Search NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R27 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-1970:Gender=Male |
    ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
#    When the user clicks on Continue Button
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipant>"
    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
#    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
#    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding two members - Son and Aunt
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                  | RelationshipToProband | DiseaseStatusDetails                                               |
      | NHSNumber=NA:DOB=21-05-2005:Gender=Male:Relationship=Son             | Son                   | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis |
      | NHSNumber=NA:DOB=13-03-1965:Gender=Female:Relationship=Maternal Aunt | Maternal Aunt         | DiseaseStatus=Affected:AgeOfOnset=12,05:HpoPhenoType=Epistaxis     |
    And the user clicks the Save and Continue button
#    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient choice for the proband
    When the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    And the help text is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    And the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=21-05-2005 | Child                 | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=13-03-1965 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
#    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
#    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
#    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    And the below stages marked as completed
      | <PatientDetails>         |
      | <RequestingOrganisation> |
      | <TestPackage>            |
      | <ResponsibleClinician>   |
      | <ClinicalQuestion>       |
      | <Notes>                  |
      | <FamilyMembers>          |
      | <PatientChoice>          |
      | <Panels>                 |
      | <Pedigree>               |
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                             | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=James:LastName=Smith:Department=Minister Road | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,11:HpoPhenoType=Square face | Notes | Family members | Patient choice | Panels | Pedigree |