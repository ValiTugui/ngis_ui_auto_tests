@regression
@TO_RD
@clinicalQuestions

Feature: Clinical Questions stage

  @NTS-3209 @E2EUI-2089 @E2EUI-1404 @v_1 @BVT_P0
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
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTermsCount | termPresence |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | 2             | Present      |

  @NTS-3246 @E2EUI-1531 @v_1 @P0
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

  @NTS-3346 @E2EUI-995 @P0 @v_1
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |

  @NTS-3240 @E2EUI-1972 @v_1 @P0
  Scenario Outline: NTS-3240 - Clinical Questions - clear the rare disease diagnosis field
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the user selects a value "<rareDiseaseDiagnosisValue>" from the Rare disease diagnosis
    When the user presses the backspace key on the Rare disease diagnosis field
    Then the value "<rareDiseaseDiagnosisValue>" should be cleared from the Rare disease diagnosis field

    Examples:
      | stage              | title                     | rareDiseaseDiagnosisValue |
      | Clinical questions | Answer clinical questions | ACHONDROPLASIA            |

  @NTS-3433 @E2EUI-1625 @E2EUI-1068 @v_1 @P0
  Scenario Outline: NTS-3433 - Clinical Questions - Allow HPO terms to be deleted
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    # user adds 3 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>"
    And the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm2>"
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "3" HPO terms in the HPO Phenotype section
    # user deletes 1 HPO term
    When the user clicks the delete icon which is displayed across the "<hpoTerm3>"
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<removeIcon>" button and "<acknowledgeMessage>" it
    # confirm total number of HPO terms are one less than previous HPO terms count
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    # user adds the HPO term again
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "3" HPO terms in the HPO Phenotype section
    Examples:
      | stage              | title                     | hpoTerm1               | hpoTerm2 | hpoTerm3          | partOfMessage                     | removeIcon | acknowledgeMessage |
      | Clinical questions | Answer clinical questions | Aspartylglucosaminuria | Nocturia | Juvenile cataract | Confirm removal of this HPO term. | remove     | Accept             |

  @NTS-3511 @E2EUI-1068 @v_1 @P0
  Scenario Outline: NTS-3511 - Clinical Questions -  Search for HPO terms in Questionnaire
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

  @NTS-3346 @E2EUI-995 @P0 @v_1
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |
