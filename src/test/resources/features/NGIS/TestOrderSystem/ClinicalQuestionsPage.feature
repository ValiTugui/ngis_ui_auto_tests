@clinicalQuestions
Feature: Clinical Questions stage

  @E2EUI-2089 @NTS-3209 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalQuestions
  Scenario Outline: NTS-3209 - Clinical Questions - Display HPO terms newest to the oldest when added
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the Clinical Questions page header is shown as "<title>"
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "<hpoTermsCount>" HPO terms in the HPO Phenotype section

    Examples:
      | stage              | title                     |   hpoTerm1                | hpoTerm2               |hpoTermsCount |
      | Clinical questions | Answer clinical questions |  Sparse and thin eyebrow  | Intestinal malrotation | 2            |
