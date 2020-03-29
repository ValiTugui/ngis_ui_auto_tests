#@regression
#@clinicalQuestions
@TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 11 - RD Questionnaire

  Background:
#    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    And the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed

  @NTS-3209 @LOGOUT
#    @E2EUI-2089 @E2EUI-1404
  Scenario Outline: NTS-3209 - Clinical Questions - Display HPO terms newest to the oldest when added
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "<hpoTermsCount>" HPO terms in the HPO Phenotype section
    And the referral submit button is not enabled

    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTermsCount | termPresence |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | 2             | Present      |

  @NTS-3246 @LOGOUT
#    @E2EUI-1531
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
    And the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And  the user selects "<diseaseStatueValue>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    And the user does not see an error message on the page
    Examples:
      | stage              | diseaseStatueValue | year | month |
      | Clinical questions | Affected           | 1    | 2     |
      | Clinical questions | Affected           | 2    | 8     |
      | Clinical questions | Affected           | 3    | 1     |
      | Clinical questions | Affected           | 0    | 0     |

  @NTS-3346 @LOGOUT
#    @E2EUI-995
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |
