#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: Family Members Details Page 6- Field Validation_6

  @NTS-4503 @Z-LOGOUT
#    @E2EUI-1130
  Scenario Outline: NTS-4503:E2EUI-1130:Family members Detail Page - Hospital Number field - maximum length validation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user deletes the data in the Hospital Number field
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | stage          | FamilyMemberDetails                 | HospitalNumber      |
      | Family members | NHSNumber=2000004326:DOB=08-10-2011 | 1234567890123456789 |

  @NTS-3474 @NTS-4053 @Z-LOGOUT
#    @E2EUI-1876 @E2EUI-2474_scenario_3
  Scenario Outline:NTS-3474:E2EUI-1867 Validating family member section must be completed to submit the referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Add a requesting organisation
     ###Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Member addition with test deselected
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    When the user deselects the test
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    And the user clicks the Save and Continue button
    ####Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails                 | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=2000004261:DOB=10-10-2011 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient has agreed to the test |             |                 |
    When the user is navigated to a page with title Patient choice
    Then the "<Patient Choice>" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Message>"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Family members            |
    And the user should be able to click on incomplete "<FamilyMembers>" section
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    #NTS-4053
    When the user navigates to the "<Panels>" stage
    Then User clicks on a field "panelsSearchBox" and auto-complete is disabled

    Examples:
      | Requesting organisation | ordering_entity_name | NoOfParticipants | FamilyMembers  | Patient Choice | Message                      | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                      | ClinicalQuestionDetails                | TestType                        | RecordedBy                            | PatientChoice                  | YesOption | IncompleteSection | Panels |
      | Requesting organisation | Queen                | 2                | Family members | Patient choice | There is missing information | NHSNumber=2000004261:DOB=10-10-2011 | Full Sibling          | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | DiseaseStatus=Unknown:AgeOfOnset=01,02 | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       | Family members    | Panels |