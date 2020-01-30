@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

  Background:
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3506 @E2EUI-999 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3506 - Clinical Questions -  scenario 2 - Present recommended answers to some questions and select if they apply
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user searches for the karyotypic sex "<karyotypicSexValue>" by selecting from the result list
    Then the user clicks the Save and Continue button
    And the "Notes" stage is selected

    Examples:
      | stage              | title                     | hpoTerm1                | termPresence | ClinicalQuestionDetails                 | karyotypicSexValue | rareDiseaseValue | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Present      | AgeOfOnset=10,3:HpoPhenoType=Lymphedema | XXXY               | CEREBRAL SARCOMA | Affected           |

  @NTS-3245 @E2EUI-1610 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3245 - Clinical Questions - Check if HPO Phenotype is mandatory if Disease status is set to "<diseaseStatueValue>"
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    Examples:
      | stage              | title                     | diseaseStatueValue             | mandatory |
      | Clinical questions | Answer clinical questions | USER_DOES_NOT_SELECT_ANY_VALUE | false     |
      | Clinical questions | Answer clinical questions | Affected                       | true      |
      | Clinical questions | Answer clinical questions | Unaffected                     | false     |
      | Clinical questions | Answer clinical questions | Uncertain                      | false     |
      | Clinical questions | Answer clinical questions | Unknown                        | false     |

