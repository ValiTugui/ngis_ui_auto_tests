@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

   @NTS-3433 @E2EUI-1546 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    Then the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Completed
    And the "<notes>" stage is selected
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                                           |  notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Unaffected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Uncertain:AgeOfOnset=10,02:HpoPhenoType=Lymphedema  |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Unknown:AgeOfOnset=10,02:HpoPhenoType=Lymphedema    |  Notes |

  @NTS-3433 @E2EUI-1894 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3433 - Clinical Questions - clear the value from Disease status field
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    When the user clears the value that is set on on the close icon  placed in the Disease status field by clicking the close icon
    Then the Disease status field is not set with the disease status value "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Mandatory To Do
    And the "<notes>" stage is selected
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                                           |  notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   |  Notes |

  @NTS-3433 @E2EUI-1625 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3433 - Clinical Questions - Allow HPO terms to be deleted
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    # user adds 2 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>"
    And the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm2>"
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    # user deletes 1 HPO term
    When the user clicks the delete icon which is displayed across the "<hpoTerm3>"
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<removeIcon>" button and "<acknowledgeMessage>" it
    # confirm total number of HPO terms are one less than previous HPO terms count
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTerm3          | partOfMessage                     | removeIcon | acknowledgeMessage |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | Juvenile cataract | Confirm removal of this HPO term. | remove     | Accept             |


  @NTS-3433 @E2EUI-1546 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory for the Family Members
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    Then the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    When the user fills the clinical questions with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field for the family member
    And the user clicks the Save and Continue button
    Then the user returns to family member landing page with the added family member details "<FamilyMemberDetails>"

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          |
