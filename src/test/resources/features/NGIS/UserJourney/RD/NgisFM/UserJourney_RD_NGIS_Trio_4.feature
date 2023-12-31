#@userJourneysRD
#@userJourneysRD_NgisFM
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NGIS_Trio_4 - UC24 - E2EUI-1799

   @NTS-4595 @Z-LOGOUT
#     @E2EUI-1799 @UseCase24
  Scenario Outline: NTS-4595: Use Case#24: Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice No - Search NGIS Patient- Cancel referral(marked in error).
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R59 | GEL_SUPER_USER | NHSNumber=2000000266:DOB=23-05-2001 |
    ##Test Order Forms
#     Then the user is navigated to a page with title Test Order Forms
#     When the user clicks on Continue Button
    ##Patient Details
     Then the user is navigated to a page with title Check your patient's details
     And the user clicks the Save and Continue button
     And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
     When the user navigates to the "Requesting organisation" stage
     Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Great" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ### Test Package - Trio family - No of participants 3
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
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                               |
      | NHSNumber=NA:DOB=11-03-1980:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Early balding |
      | NHSNumber=NA:DOB=12-02-1980:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis     |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###patient choice for the proband
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the Patient Choice landing page is updated to "Declined testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members added
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user edits patient choice for "<ThreeParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-1980 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
      | NHSNumber=NA:DOB=12-02-1980 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms
    Then the user navigates to the "Print forms" stage
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
    ###Submitting Referral and Cancellation
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "An uneditable mistake was made in creation (“Mark in error”)" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | RevokeMessage                                                              | RecordedBy                            | searchPanels   |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Panels | Pedigree | This referral has been cancelled so further changes might not take effect. | ClinicianName=John:HospitalNumber=123 | Arthrogryposis |