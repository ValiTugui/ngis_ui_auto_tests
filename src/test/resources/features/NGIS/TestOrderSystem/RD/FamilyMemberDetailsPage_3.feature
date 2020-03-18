@regression
@TO_RD
@FamilyMembersDetailsPage

Feature: Family Members Details Page - Flow validation

  @NTS-3474 @E2EUI-1876 @LOGOUT @v_1 @P1
  Scenario Outline:NTS-3474: Validating family member section must be completed to submit the referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
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
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Member addition with test deselected
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
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
      | NHSNumber=9449305307:DOB=14-02-2011 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient has agreed to the test |             |                 |
    When the user is navigated to a page with title Patient choice
    Then the "<Patient Choice>" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Message>"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Family members            |
    And the user should be able to click on incomplete "<FamilyMembers>" section
    And the "<FamilyMembers>" stage is marked as Mandatory To Do

    Examples:
      | Requesting organisation | ordering_entity_name | NoOfParticipants | FamilyMembers  | Patient Choice | Message                      | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                      | ClinicalQuestionDetails                |
      | Requesting organisation | Queen                | 2                | Family members | Patient choice | There is missing information | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | DiseaseStatus=Unknown:AgeOfOnset=01,02 |

  @NTS-4413 @E2EUI-833 @E2EUI-1880 @LOGOUT @Scenario1
  Scenario Outline: NTS-4413 :  Change 'Trio Pedigree' icon as it is upside down
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Check your patient
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user should be able to see trio family icon in test package

    Examples:
      | TestPackage  |
      | Test package |

  @NTS-4413 @E2EUI-833 @LOGOUT @Scenario2
  Scenario Outline: NTS-4413 : Change 'Trio Pedigree' icon as it is upside down
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R105 |
    And the user clicks the Start Test Order Referral button
    And the user clicks the PDF order form button
    Then the user is navigated to a page with title Add a requesting organisation
    When the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks on Continue Button
    Then the user should be able to sees trio family icon in review test selection
    Examples:
      | ordering_entity_name |
      | Maidstone            |
