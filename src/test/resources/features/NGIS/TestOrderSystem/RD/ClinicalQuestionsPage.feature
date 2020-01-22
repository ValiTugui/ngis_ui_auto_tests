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
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
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
      | Clinical questions | Answer clinical questions | ACHONDROPLASIA            |

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
