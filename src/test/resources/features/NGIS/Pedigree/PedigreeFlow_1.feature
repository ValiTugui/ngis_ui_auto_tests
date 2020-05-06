#@regression
#@pedigree_flow
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - Pedigree Flow 1

  @NTS-3458 @Z-LOGOUT
#    @E2EUI-1994 @E2EUI-1728 @E2EUI-2148 @E2EUI-1996
  Scenario Outline: NTS-3458 :E2EUI-1994,1728,2148,1996: Validating Pedigree section must be completed to submit the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2007:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Information
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    ##Family Member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                        | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1965:Gender=Male:Relationship=Other | Other                 | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    When the user navigates to the "<Patient Choice>" stage
    When the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    Then the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=17-07-1965 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    When the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Build a pedigree
    And the "<Pedigree>" stage is marked as Mandatory To Do
    And the user should see the referral submit button as disabled

    When the user navigates to the "<FamilyMembers>" stage
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<Pedigree>" stage
    Then the "<Pedigree>" stage is marked as Completed
    And the user should see the referral submit button as enabled

    Examples:
      | Requesting organisation | ordering_entity_name | NoOfParticipants | ResponsibleClinicianDetails               | ClinicalQuestionDetails                   | FamilyMembers  | Patient Choice | Pedigree | RecordedBy                                                                                                           | RelationshipToProband |
      | Requesting organisation | Maidstone            | 2                | LastName=Smith:Department=Victoria Street | DiseaseStatus=Unaffected:AgeOfOnset=03,02 | Family members | Patient choice | Pedigree | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Full Sibling          |