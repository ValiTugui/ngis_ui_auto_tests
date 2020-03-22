#@regression
#@clinicalQuestions
@TEST_ORDER_QN
@SYSTEM_TEST
Feature: RD Questionnaire

  Background:
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed


  @NTS-4708  @LOGOUT
  # @E2EUI-1323
  Scenario Outline: NTS-4708 - Clinical Questions - drop-downs in the Phenotypic and Karyotypic Sex section
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user clicks the phenotypic sex dropdown
    And the user sees the following values in phenotypic sex dropdown
    |Male | Female | Unknown | Indeterminate|
    And the user clicks the karyotypic sex dropdown
    And the user sees the following values in karyotypic sex dropdown
    |XX | XY | XO | XXY | XYY | XXX |  XXXY | XXXX | XXYY | other | unknown|

    Examples:
      | stage              | title                     |
      | Clinical questions | Answer clinical questions |

  @NTS-4708 @LOGOUT
#    @E2EUI-876 @E2EUI-944 - add multiple diagnosisTypeValue
  Scenario Outline: NTS-3511 - Clinical Questions - verify switchable static enum component
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm>"
    Then the new HPO term "<hpoTerm>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue1>" and corresponding status "<statusValue1>"
    And the user selects a value "<rareDiseaseValue1>" from the Rare disease diagnosis
    And the user clicks on add another link
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue2>" and corresponding status "<statusValue2>" in the second table
    And the user selects a value "<rareDiseaseValue2>" from the Rare disease diagnosis in the second table
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected

    Examples:
      | stage              | title                     | diseaseStatueValue | mandatory | year | month | hpoTerm                 | termPresence | rareDiseaseValue1 | diagnosisTypeValue1 | statusValue1 |rareDiseaseValue2      | diagnosisTypeValue2 | statusValue2 |
      | Clinical questions | Answer clinical questions | Affected           | true      | 1    | 2     | Sparse and thin eyebrow | Present      | CEREBRAL SARCOMA  | Omim                | Confirmed    |Ataxia-telangiectasia  | Orphanet            | Suspected    |
