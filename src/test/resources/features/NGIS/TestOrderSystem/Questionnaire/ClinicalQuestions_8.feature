#@regression
#@clinicalQuestions
@TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 8 - RD Questionnaire

  @NTS-3511 @LOGOUT
#    @E2EUI-876 @E2EUI-1180
  Scenario Outline: NTS-3511 - Clinical Questions - Term presence value is 'Present' for atleast one HPO phenotype
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence1>" and corresponding modifier
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence2>" and corresponding modifier
    And the user sees the error message "At least 1 phenotype must be marked Present" in the HPO phenotype section
    And the count of HPO terms table is 2
    Then the "<title>" page is displayed
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    Then the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the user sees the data in Disease status details such as "<diseaseStatueValue>" AgeOnset values "<year>" "<month>"
    And the user sees the data in HPO phenotype details such as "<hpoTerm1>" Term presence "<termPresence1>"
    And the user sees the data in HPO phenotype details such as "<hpoTerm2>" Term presence "<termPresence2>"
    And the count of HPO terms table is 2
    And the user sees the data in Rare disease diagnoses such as "<rareDiseaseValue>" "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user sees the data in Phenotypic and karyotypic Sex
    Examples:
      | stage              | title                     | diseaseStatueValue | mandatory | year | month | hpoTerm1  | termPresence1 | hpoTerm2                | termPresence2 | rareDiseaseValue | diagnosisTypeValue | statusValue |
      | Clinical questions | Answer clinical questions | Affected           | true      | 1    | 2     | Anonychia | Absent        | Sparse and thin eyebrow | Unknown       | Fibrosarcoma     | Orphanet           | Suspected   |


  @NTS-3511 @LOGOUT
#    @E2EUI-876 @E2EUI-944 - specify single omim value
  Scenario Outline: NTS-3511 - Clinical Questions - Show dynamic Clinical Questions about the proband
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
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
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    Then the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the user sees the data in Disease status details such as "<diseaseStatueValue>" AgeOnset values "<year>" "<month>"
    And the user sees the data in HPO phenotype details such as "<hpoTerm>" Term presence "<termPresence>"
    And the user sees the data in Rare disease diagnoses such as "<rareDiseaseValue>" "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user sees the data in Phenotypic and karyotypic Sex

    Examples:
      | stage              | title                     | diseaseStatueValue | mandatory | year | month | hpoTerm                 | termPresence | rareDiseaseValue | diagnosisTypeValue | statusValue |
      | Clinical questions | Answer clinical questions | Affected           | true      | 1    | 2     | Sparse and thin eyebrow | Present      | CEREBRAL SARCOMA | Omim               | Confirmed   |


  @NTS-3511 @LOGOUT
#    @E2EUI-1068  @E2EUI-944 - multiple HPO terms added in the test
  Scenario Outline: NTS-3511 - Clinical Questions -  Search for HPO terms in Questionnaire
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    # user adds 3 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>" using the autosuggest terms
    And the count of HPO terms table is 1
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm2>" using the autosuggest terms
    And the count of HPO terms table is 2
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>" using the autosuggest terms
    And the count of HPO terms table is 3
     # example of providing 1 letter / partial words for the HPO term - E2EUI-1068
    Examples:
      | stage              | title                     | hpoTerm1 | hpoTerm2 | hpoTerm3 |
      | Clinical questions | Answer clinical questions | Sparse   | Anonych  | J        |
