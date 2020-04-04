#@regression
#@clinicalQuestions
@TEST_ORDER4
@SYSTEM_TEST

Feature: ClinicalQuestions 2 - RD Questionnaire

  Background:
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3246 @LOGOUT
#    @E2EUI-1531 @E2EUI-992
  Scenario Outline: NTS-3246 - Clinical Questions - Age at Onset - Negative Tests
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

  @NTS-3246 @LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
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

  @NTS-3246 @LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  months only provided
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

  @NTS-3246 @LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  years only provided
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
