#@regression
#@clinicalQuestions
@TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 4 - RD Questionnaire

  Background:
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3240 @LOGOUT
#  @E2EUI-1972
  Scenario Outline: NTS-3240 - Clinical Questions - clear the rare disease diagnosis field
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the user selects a value "<rareDiseaseDiagnosisValue>" from the Rare disease diagnosis
    When the user presses the backspace key on the Rare disease diagnosis field
    Then the value "<rareDiseaseDiagnosisValue>" should be cleared from the Rare disease diagnosis field

    Examples:
      | stage              | title                     | rareDiseaseDiagnosisValue |
      | Clinical questions | Answer clinical questions | ACHONDROPLASIA            |
