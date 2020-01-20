@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

  @NTS-3246 @E2EUI-1531 @E2EUI-992 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3246 - Clinical Questions - Age at Onset - Negative Tests
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    And the user sees an error "<errorMessage>" message on the page
    Examples:
      | stage              | title                     | diseaseStatueValue | year  | month | errorMessage                                      |
      | Clinical questions | Answer clinical questions | Affected           | -2.4  | 0     | Please enter whole years and months               |
      | Clinical questions | Answer clinical questions | Affected           | 128   | 0     | Patient age cannot exceed 125 years               |
      | Clinical questions | Answer clinical questions | Affected           | 10    | 1.4   | Please enter whole years and months               |
      | Clinical questions | Answer clinical questions | Affected           | -1    | 11    | Please enter prenatal age in negative months      |
      | Clinical questions | Answer clinical questions | Affected           | 1     | 12    | Number of months can only exceed 11 if years is 0 |
      | Clinical questions | Answer clinical questions | Affected           | 1000  | 0     | Patient age cannot exceed 125 years               |
      | Clinical questions | Answer clinical questions | Affected           | 24    | -1    | Only prenatal cases can have a negative number    |
      | Clinical questions | Answer clinical questions | Affected           | 0     | -10   | Patient cannot be younger than -9 months          |

  @NTS-3246 @E2EUI-1531 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    And the user does not see an error message on the page
    Examples:
      | stage              | title                     | diseaseStatueValue | year | month |
      | Clinical questions | Answer clinical questions | Affected           | 1    | 2     |
      | Clinical questions | Answer clinical questions | Affected           | 2    | 8     |
      | Clinical questions | Answer clinical questions | Affected           | 3    | 1     |
      | Clinical questions | Answer clinical questions | Affected           | 0    | 0     |

  @NTS-3246 @E2EUI-1531 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  months only provided
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user provided the values "<month>" for Age of onset fields
    And the user does not see an error message on the page
    Examples:
      | stage              | title                     | diseaseStatueValue | month |
      | Clinical questions | Answer clinical questions | Affected           | 10    |
      | Clinical questions | Answer clinical questions | Affected           | 15    |
      | Clinical questions | Answer clinical questions | Affected           | 20    |
      | Clinical questions | Answer clinical questions | Affected           | -2    |

  @NTS-3246 @E2EUI-1531 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  years only provided
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user provided the year values "<year>" for Age of onset fields
    And the user does not see an error message on the page
    Examples:
      | stage              | title                     | diseaseStatueValue | year |
      | Clinical questions | Answer clinical questions | Affected           | 1    |
      | Clinical questions | Answer clinical questions | Affected           | 15   |
      | Clinical questions | Answer clinical questions | Affected           | 125  |

  @NTS-3346 @E2EUI-995 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |