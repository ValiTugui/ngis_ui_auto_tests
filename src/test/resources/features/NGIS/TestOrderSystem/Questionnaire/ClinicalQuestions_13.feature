#@regression
#@clinicalQuestions
#@clinicalQuestionsFM
@TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 13 - Page Validation

  @NTS-4624 @LOGOUT
#    @E2EUI-1299
  Scenario Outline: NTS-4624 -To validate mandatory and non-mandatory input fields for Clinical question for Disease status section
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the mandatory fields shown with the symbol in red color
      | mandatory_field | field_type | symbol | symbol color |
      | Disease status  | label      | ✱      | #dd2509      |
    When the user selects "<DiseaseStatus>"
    And the mandatory fields shown with the symbol in red color
      | mandatory_field               | field_type | symbol | symbol color |
      | Disease status                | label      | ✱      | #dd2509      |
      | Find an HPO phenotype or code | label      | ✱      | #dd2509      |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader    |
      | Age of onset   |
      | Phenotypic sex |
      | Karyotypic sex |

    Examples:
      | stage              | title                     | DiseaseStatus |
      | Clinical questions | Answer clinical questions | Affected      |

  @NTS-4631 @LOGOUT
#    @E2EUI-1514
  Scenario Outline: NTS-4631: Updating age of onset in Clinical Questions
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R88 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=16-5-1999:Gender=Male |
    ##Clinical questions Stage
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user selects "<diseaseStatueValue>"
    When the user provided the values "<yearvalue1>" "<monthvalue1>" for Age of onset fields
    Then the user sees an error "<errorMessage1>" message on the page
    When the user provided the values "<yearvalue2>" "<monthvalue2>" for Age of onset fields
    Then the user sees an error "<errorMessage2>" message on the page
    When the user provided the values "<yearvalue3>" "<monthvalue3>" for Age of onset fields
    Then the user sees an error "<errorMessage3>" message on the page
    When the user provided the values "<yearvalue4>" "<monthvalue4>" for Age of onset fields
    Then the user sees an error "<errorMessage4>" message on the page
    When the user provided the values "<yearvalue5>" "<monthvalue5>" for Age of onset fields
    Then the user sees an error "<errorMessage5>" message on the page

    Examples:
      | stage              | title                     | diseaseStatueValue | yearvalue1 | monthvalue1 | errorMessage1                       | yearvalue2 | monthvalue2 | errorMessage2                         | yearvalue3 | monthvalue3 | errorMessage3                                     | yearvalue4 | monthvalue4 | errorMessage4                                | yearvalue5 | monthvalue5 | errorMessage5                            |
      | Clinical questions | Answer clinical questions | Affected           | 130        | 0           | Patient age cannot exceed 125 years | 0          | 1501        | Patient age cannot exceed 1500 months | 1          | 12          | Number of months can only exceed 11 if years is 0 | -1         | 0           | Please enter prenatal age in negative months | 0          | -10         | Patient cannot be younger than -9 months |

  @NTS-4672 @LOGOUT
#    @E2EUI-1378
  Scenario Outline: NTS-4672 : Implement "repeating" units from dynamic content framework
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER | child |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the mandatory fields shown with the symbol in red color
      | mandatory_field               | field_type | symbol | symbol color |
      | Disease status                | label      | ✱      | #dd2509      |
      | Find an HPO phenotype or code | label      | ✱      | #dd2509      |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader    |
      | Age of onset   |
      | Diagnosis      |
      | Status         |
      | Phenotypic sex |
      | Karyotypic sex |
    And the user should be able to see the field headers on Clinical questions page
      | subtitles                     |
      | Disease status details        |
      | HPO phenotype details         |
      | Rare disease diagnoses        |
      | Phenotypic and karyotypic Sex |
    And the user should see selected radio button in "<TermPresence>" temp plate
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue1>" and corresponding status "<statusValue1>"
    When the user selects a value "<rareDiseaseValue1>" from the Rare disease diagnosis
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed

    Examples:
      | ClinicalQuestion   | ClinicalQuestionDetails                                                                              | rareDiseaseValue1         | diagnosisTypeValue1 | statusValue1 | TermPresence |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XYY | BASAL CELL NEVUS SYNDROME | Omim                | Confirmed    | Present      |


  @NTS-3346 @LOGOUT
#    @E2EUI-995
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |