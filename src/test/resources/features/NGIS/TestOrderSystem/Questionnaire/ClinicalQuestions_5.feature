#@regression
#@clinicalQuestions
@TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 5 - RD Questionnaire

  @NTS-3433 @LOGOUT
#    @E2EUI-1546
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    When the user is navigated to a page with title Answer clinical questions
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    Then the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Completed
    And the "<notes>" stage is selected
    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                           | notes |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   | Notes |
      | Clinical questions | DiseaseStatus=Unaffected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes |
      | Clinical questions | DiseaseStatus=Uncertain:AgeOfOnset=10,02:HpoPhenoType=Lymphedema  | Notes |
      | Clinical questions | DiseaseStatus=Unknown:AgeOfOnset=10,02:HpoPhenoType=Lymphedema    | Notes |

  @NTS-3433 @LOGOUT
#    @E2EUI-1894
  Scenario Outline: NTS-3433 - Clinical Questions - clear the value from Disease status field
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
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
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                                         | notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes |

  @NTS-3433 @LOGOUT
#    @E2EUI-1546
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory for the Family Members
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
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
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father         | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

  @NTS-3433 @LOGOUT
#    @E2EUI-1625 @E2EUI-1068 @E2EUI-842
  Scenario Outline: NTS-3433 - Clinical Questions - Allow HPO terms to be deleted
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    # user adds 3 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>"
    And the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm2>"
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "3" HPO terms in the HPO Phenotype section
    # user deletes 1 HPO term
    When the user clicks the delete icon which is displayed across the "<hpoTerm3>"
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<removeIcon>" button and "<acknowledgeMessage>" it
    # confirm total number of HPO terms are one less than previous HPO terms count
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    # user adds the HPO term again
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "3" HPO terms in the HPO Phenotype section
    Examples:
      | stage              | title                     | hpoTerm1               | hpoTerm2 | hpoTerm3          | partOfMessage                     | removeIcon | acknowledgeMessage |
      | Clinical questions | Answer clinical questions | Aspartylglucosaminuria | Nocturia | Juvenile cataract | Confirm removal of this HPO term. | remove     | Accept             |
