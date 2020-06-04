#@regression
#@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST

Feature: ClinicalQuestions 2 - RD Questionnaire

  @NTS-3246 @Z-LOGOUT
#    @E2EUI-1531 @E2EUI-992
  Scenario Outline: NTS-3246 - Clinical Questions - Age at Onset - Negative Tests
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    And the user see error message when providing invalid age of onsets
      | year | month | errorMessage                                      |
      | -2.4 | 0     | Please enter whole years and months               |
      | 128  | 0     | Patient age cannot exceed 125 years               |
      | 10   | 1.4   | Please enter whole years and months               |
      | -1   | 11    | Please enter prenatal age in negative months      |
      | 1    | 12    | Number of months can only exceed 11 if years is 0 |
      | 1000 | 0     | Patient age cannot exceed 125 years               |
      | 24   | -1    | Only prenatal cases can have a negative number    |
      | 0    | -10   | Patient cannot be younger than -9 months          |
    Examples:
      | stage              | title                     | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Affected           |

  @NTS-3246 @Z-LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    And the user should not see error message when providing valid age of onsets
      | year | month |
      | 1    | 2     |
      | 2    | 8     |
      | 3    | 1     |
      | 0    | 0     |
    Examples:
      | stage              | title                     | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Affected           |

  @NTS-3246 @Z-LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  months only provided
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    And the user should not see error message when providing valid age of onsets
      | year | month |
      |      | 10    |
      |      | 15    |
      |      | 20    |
      |      | -2    |
    Examples:
      | stage              | title                     | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Affected           |

  @NTS-3246 @Z-LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  years only provided
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user provided the year values "<year>" for Age of onset fields
    And the user should not see error message when providing valid age of onsets
      | year | month |
      | 1    |       |
      | 15   |       |
      | 125  |       |

    Examples:
      | stage              | title                     | diseaseStatueValue |
      | Clinical questions | Answer clinical questions | Affected           |