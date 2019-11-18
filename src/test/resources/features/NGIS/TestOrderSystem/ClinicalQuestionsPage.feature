@regression
@regression_set1
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

  @E2EUI-1972 @NTS-3240 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3240 - Clinical Questions - clear the rare disease diagnosis field
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    And the user selects a value "<rareDiseaseDiagnosisValue>" from the Rare disease diagnosis
    When the user presses the backspace key on the Rare disease diagnosis field
    Then the value "<rareDiseaseDiagnosisValue>" should be cleared from the Rare disease diagnosis field

    Examples:
      | stage              | title                     | rareDiseaseDiagnosisValue |
      | Clinical questions | Answer clinical questions | rudimentary               |
      | Clinical questions | Answer clinical questions | ABDUCENS                  |
      | Clinical questions | Answer clinical questions | TRANSCOBALAMIN            |

  @E2EUI-1610 @NTS-3245 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3245 - Clinical Questions - Check if HPO Phenotype is mandatory if Disease status is set to "<diseaseStatueValue>"
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    Examples:
      | stage              | title                     |   diseaseStatueValue                |mandatory |
      | Clinical questions | Answer clinical questions |  USER_DOES_NOT_SELECT_ANY_VALUE     |   false  |
      | Clinical questions | Answer clinical questions |  Affected                           |   true   |
      | Clinical questions | Answer clinical questions |  Unaffected                         |   false  |
      | Clinical questions | Answer clinical questions |  Uncertain                          |   false  |
      | Clinical questions | Answer clinical questions |  Unknown                            |   false  |

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Age at Onset - Negative Tests
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the values "<year>" "<month>" for Age of onset fields
  And the user sees an error "<errorMessage>" message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | year | month | errorMessage                                      |
    | Clinical questions | Answer clinical questions | Affected           | 10   | 1.4   | Please enter whole years and months               |
    | Clinical questions | Answer clinical questions | Affected           | -1   | 11    | Please enter prenatal age in negative months      |
    | Clinical questions | Answer clinical questions | Affected           | 1    | 12    | Number of months can only exceed 11 if years is 0 |
    | Clinical questions | Answer clinical questions | Affected           | 1000 | 0     | Patient age cannot exceed 125 years               |
    | Clinical questions | Answer clinical questions | Affected           | 24   | -1    | Only prenatal cases can have a negative number    |
    | Clinical questions | Answer clinical questions | Affected           | 0    | -10   | Patient cannot be younger than -9 months          |


@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the values "<year>" "<month>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | year | month |
    | Clinical questions | Answer clinical questions | Affected           | 1    | 2     |
    | Clinical questions | Answer clinical questions | Affected           | 2    | 8     |
    | Clinical questions | Answer clinical questions | Affected           | 3    | 1     |
    | Clinical questions | Answer clinical questions | Affected           | 0    | 0     |

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  months only provided
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the values "<month>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | month |
    | Clinical questions | Answer clinical questions | Affected           | 10    |
    | Clinical questions | Answer clinical questions | Affected           | 15    |
    | Clinical questions | Answer clinical questions | Affected           | 20    |
    | Clinical questions | Answer clinical questions | Affected           | -2    |

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  years only provided
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the year values "<year>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | year |
    | Clinical questions | Answer clinical questions | Affected           | 1    |
    | Clinical questions | Answer clinical questions | Affected           | 15   |
    | Clinical questions | Answer clinical questions | Affected           | 125  |
