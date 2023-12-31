@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: ClinicalQuestions 3 - RD Questionnaire

  @NTS-35061 @Z-LOGOUT
#    @E2EUI-999
  Scenario Outline: NTS-3506 - Clinical Questions -  scenario 2 - Present recommended answers to some questions and select if they apply
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
    ##Patient details Stage
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    When the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    When the user is navigated to a page with title Answer clinical questions
##    @NTS-3245 @E2EUI-1610
    And the phenotype label marked as mandatory based on the disease status selection
      | diseaseStatueValue             | phenotypeMandatory |
      | USER_DOES_NOT_SELECT_ANY_VALUE | true               |
      | Unaffected                     | false              |
      | Uncertain                      | false              |
      | Unknown                        | false              |
      | Affected                       | true               |
    And  the user selects "<diseaseStatueValue>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    When the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user searches for the karyotypic sex "<karyotypicSexValue>" by selecting from the result list
    Then the user clicks the Save and Continue button
    And the "Notes" stage is selected

    Examples:
      | stage              | hpoTerm1                | termPresence | ClinicalQuestionDetails                 | karyotypicSexValue | rareDiseaseValue | diseaseStatueValue |
      | Clinical questions | Sparse and thin eyebrow | Present      | AgeOfOnset=10,3:HpoPhenoType=Lymphedema | XXXY               | CEREBRAL SARCOMA | Affected           |
