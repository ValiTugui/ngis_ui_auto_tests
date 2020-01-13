@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

  @NTS-3209 @E2EUI-2089 @E2EUI-1404 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3209 - Clinical Questions - Display HPO terms newest to the oldest when added
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and modifier
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "<hpoTermsCount>" HPO terms in the HPO Phenotype section

    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTermsCount |termPresence |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | 2             |Present      |

  @NTS-3240 @E2EUI-1972 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3240 - Clinical Questions - clear the rare disease diagnosis field
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the user selects a value "<rareDiseaseDiagnosisValue>" from the Rare disease diagnosis
    When the user presses the backspace key on the Rare disease diagnosis field
    Then the value "<rareDiseaseDiagnosisValue>" should be cleared from the Rare disease diagnosis field

    Examples:
      | stage              | title                     | rareDiseaseDiagnosisValue |
      | Clinical questions | Answer clinical questions | rudimentary               |
      | Clinical questions | Answer clinical questions | TIETZ ALBINISM-DEAFNESS   |
      | Clinical questions | Answer clinical questions | BASAL CELL NEVUS          |

  @NTS-3245 @E2EUI-1610 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3245 - Clinical Questions - Check if HPO Phenotype is mandatory if Disease status is set to "<diseaseStatueValue>"
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    Examples:
      | stage              | title                     |   diseaseStatueValue                |mandatory |
      | Clinical questions | Answer clinical questions |  USER_DOES_NOT_SELECT_ANY_VALUE     |   false  |
      | Clinical questions | Answer clinical questions |  Affected                           |   true   |
      | Clinical questions | Answer clinical questions |  Unaffected                         |   false  |
      | Clinical questions | Answer clinical questions |  Uncertain                          |   false  |
      | Clinical questions | Answer clinical questions |  Unknown                            |   false  |

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

  @NTS-3453 @E2EUI-881 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3453 - Clinical Questions -  landing page is marked as mandatory
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the "<ClinicalQuestions>" stage is marked as Mandatory To Do
    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestions   |
      | Patient details | Requesting organisation | Maidstone            | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions  |

  @NTS-3453 @E2EUI-1124 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3453 - Clinical Questions -  mandatory field validations for Disease status field
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the disease status field
    And the user selects a value "BASAL CELL NEVUS" from the Rare disease diagnosis
    And the user clicks the Save and Continue button
    Then the "<notes>" stage is selected
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the Disease status field is not set with the disease status value "<ClinicalQuestionDetails>"
    And the "<ClinicalQuestions>" stage is marked as Mandatory To Do
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                  | notes |
      | Clinical questions | Answer clinical questions | AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes |
