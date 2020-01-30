@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

  @NTS-3439 @E2EUI-1443 @E2EUI-918 @v_1 @BVT_P0
  Scenario Outline: NTS-3439 - Clinical Questions -  scenario 1 - verify the 'Save and Continue' button on the Clinical Questions stage
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    Then the user navigates to the "<stage>" stage
    And the user sees the data such as "<diseaseStatueValue>" "<hpoTerm1>" "<ClinicalQuestionDetails>" "<rareDiseaseValue>" phenotypic and karyotypic sex are saved
    Examples:
      | stage              | title                     | hpoTerm1                | termPresence | ClinicalQuestionDetails                 | rareDiseaseValue | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Present      | AgeOfOnset=10,3:HpoPhenoType=Lymphedema | CEREBRAL SARCOMA | Affected           |

  @NTS-3439 @E2EUI-1443 @E2EUI-918 @E2EUI-1351 @E2EUI-902 @v_1 @BVT_P0
  Scenario Outline: NTS-3439 - Clinical Questions -  scenario 2 - Return enum values for previous answers
    When the user navigates to the "Test package" stage
    And the user selects the number of participants as "2"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    When the user navigates to the "Family members" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the default family member details page is correctly displayed
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user navigates to the "<stage>" stage
    And the user sees the data such as "<diseaseStatueValue>" "<hpoTerm1>" "<ClinicalQuestionDetails>" "<rareDiseaseValue>" phenotypic and karyotypic sex are saved
    Examples:
      | stage              | title                     | hpoTerm1                | termPresence | ClinicalQuestionDetails                 | FamilyMemberDetails                 | RelationshipToProband | rareDiseaseValue | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Present      | AgeOfOnset=10,3:HpoPhenoType=Lymphedema | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | CEREBRAL SARCOMA | Affected           |

