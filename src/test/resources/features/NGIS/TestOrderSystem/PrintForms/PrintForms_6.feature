@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@PrintForms
Feature: TestOrder - Print Forms 6 - Family Members in Print Forms


  @NTS-4802 @Z-LOGOUT
#    @E2EUI-1789 @E2EUI-1262 @E2EUI-826
  Scenario Outline: NTS-4802:  As a user viewing the print forms section, I should be able to see all family member identifiers so that I can correctly identify they are the correct family members in the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R143 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-10-2001:Gender=Female |
     ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipants>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    Then the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1976:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=02,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1977:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    And the user reads the patient details in family member landing page
    And the user clicks the Save and Continue button
    ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
#    When the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<ThreeParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-1976 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
      | NHSNumber=NA:DOB=10-11-1977 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
   ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user reads the patient details in print forms page
    Then the user should see same data in family member landing page and print forms page
    And the user is able to download print form for the proband
    ###Covering E2EUI-826
    And the user should be able to click "Show address" link to verify the address of the lab in the downloaded file
    Then the user is able to download print forms for "<ThreeParticipants>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-1976 |
      | NHSNumber=NA:DOB=10-11-1977 |
    And the user should be able to click "Show address" link to verify the address of the lab in the downloaded file
    Examples:
      | ThreeParticipants | ClinicalQuestionDetails                   | ResponsibleClinicianDetails                | ClinicianName                           | PrintForms  |
      | 3                 | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | LastName=Barick:Department=Victoria Street | ClinicianName=Deepak:HospitalNumber=123 | Print forms |

  @NTS-6040 @Z-LOGOUT
  Scenario Outline: NTS-4802:  As a user viewing the print forms section, I should be able to see all family member identifiers so that I can correctly identify they are the correct family members in the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R85 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-10-2001:Gender=Female |
     ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<Requesting_Organisation_key>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipants>"
    And the user clicks the Save and Continue button

    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    ###Covering E2EUI-826
    And the user should be able to click "Show address" link to verify the address of the lab in the downloaded file
    Examples:
      | ThreeParticipants | Requesting_Organisation_key               | PrintForms  |
      | 1                 | Sheffield Children's NHS Foundation Trust | Print forms |
      | 1                 | Gateshead Health NHS Foundation Trust     | Print forms |
      | 1                 | Leeds Community Healthcare NHS Trust      | Print forms |
