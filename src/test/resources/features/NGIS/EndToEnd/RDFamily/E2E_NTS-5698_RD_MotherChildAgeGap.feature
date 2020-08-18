Feature: RD Duo Family : NTS-5698: Create a RD referral for one family member, Age of mother is less than 12 when child was born and process GEL 1001

  @NTS-5698 @Z-LOGOUT
    #E2EUI-2746
  Scenario Outline: NTS-5698: User should see the valid message generated in the GEL 1001 file if Age of mother is less than 12 when child was born and process GEL 1001
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-2001:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
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
    ##Family Members - Adding one member - Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-1991:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    When the user is navigated to a page with title Patient choice
    And the user selects the proband
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
      ###Patient Choice - Family Details Provided below same as the Family Members
    And the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-1991 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient conversation happened; form to follow      |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                             | RecordedBy                            | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=James:LastName=Smith:Department=Minister Road | ClinicianName=John:HospitalNumber=123 | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,11:HpoPhenoType=Square face | Notes | Family members | Patient choice | Panels | Pedigree |
