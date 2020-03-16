@regression
@clinicalQuestions
@clinicalQuestionsFM

Feature: Clinical Question Page - Family Members

  @NTS-4735 @E2EUI-1271 @LOGOUT
  Scenario Outline: NTS-4735: Show dynamic Clinical Questions about additional family members
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=12-03-1999:Gender=Female |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    ##Family Member
    And the user navigates to the "<FamilyMembers>" stage
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
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user fills the RareDiseaseDiagnoses Status as "<RareDiseaseValue>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the test package page has Selected family members with the "<FamilyMemberDetails>"
    And the user clicks on a test that is selected and the test is no longer selected
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Add family member details
    And the user should able to see the same family member DiseaseStatusDetails "<DiseaseStatusDetails>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                                                                            | RareDiseaseValue | diagnosisTypeValue | statusValue |
      | Family members | NHSNumber=9449303673:DOB=16-07-2010 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality:PhenotypicSex=Male:KaryotypicSex=XY | WEAVER SYNDROME  | Omim               | Confirmed   |

  @NTS-4735 @E2EUI-1884 @LOGOUT
  Scenario Outline: NTS-4735 :  The update to the diagnosis type for the Rare Disease question will not be saved
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R80 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
   ##Patient details Stage
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    ##Clinical questions Stage
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And  the user selects "<DiseaseStatueValue>"
    And the user selects a value "<RareDiseaseValue>" from the Rare disease diagnosis
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user clicks the Save and Continue button

    Examples:
      | ClinicalQuestions  | RareDiseaseValue | DiseaseStatueValue | diagnosisTypeValue | statusValue |
      | Clinical questions | ABDUCENS PALSY   | Unaffected         | Omim               | Suspected   |
