@regression
@regression_set1
@clinicalQuestions

Feature: Clinical Questions stage

  @E2EUI-2089 @NTS-3209 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalQuestions @BVT_P0
  Scenario Outline: NTS-3209 - Clinical Questions - Display HPO terms newest to the oldest when added
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the Clinical Questions page header is shown as "<title>"
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "<hpoTermsCount>" HPO terms in the HPO Phenotype section

    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTermsCount |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | 2             |

  @E2EUI-1972 @NTS-3240 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3240 - Clinical Questions - clear the rare disease diagnosis field
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    And the user selects a value "<rareDiseaseDiagnosisValue>" from the Rare disease diagnosis
    When the user presses the backspace key on the Rare disease diagnosis field
    Then the value "<rareDiseaseDiagnosisValue>" should be cleared from the Rare disease diagnosis field

    Examples:
      | stage              | title                     | rareDiseaseDiagnosisValue |
      | Clinical questions | Answer clinical questions | rudimentary               |
      | Clinical questions | Answer clinical questions | TIETZ ALBINISM-DEAFNESS   |
      | Clinical questions | Answer clinical questions | BASAL CELL NEVUS          |

  @E2EUI-1610 @NTS-3245 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions @BVT_P0
  Scenario Outline: NTS-3245 - Clinical Questions - Check if HPO Phenotype is mandatory if Disease status is set to "<diseaseStatueValue>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    When the user selects "<diseaseStatueValue>"
    Then the HPO phenotype details mandatory state is "<mandatory>"
    Examples:
      | stage              | title                     |   diseaseStatueValue                |mandatory |
      | Clinical questions | Answer clinical questions |  USER_DOES_NOT_SELECT_ANY_VALUE     |   false  |
      | Clinical questions | Answer clinical questions |  Affected                           |   true   |
      | Clinical questions | Answer clinical questions |  Unaffected                         |   false  |
      | Clinical questions | Answer clinical questions |  Uncertain                          |   false  |
      | Clinical questions | Answer clinical questions |  Unknown                            |   false  |

@E2EUI-1531 @E2EUI-992 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Age at Onset - Negative Tests
  Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
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


@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset to be stored in months
  Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the values "<year>" "<month>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | year | month |
    | Clinical questions | Answer clinical questions | Affected           | 1    | 2     |
    | Clinical questions | Answer clinical questions | Affected           | 2    | 8     |
    | Clinical questions | Answer clinical questions | Affected           | 3    | 1     |
    | Clinical questions | Answer clinical questions | Affected           | 0    | 0     |

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  months only provided
  Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the values "<month>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | month |
    | Clinical questions | Answer clinical questions | Affected           | 10    |
    | Clinical questions | Answer clinical questions | Affected           | 15    |
    | Clinical questions | Answer clinical questions | Affected           | 20    |
    | Clinical questions | Answer clinical questions | Affected           | -2    |

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3246 - Clinical Questions - Convert Disease status Age at Onset -  years only provided
  Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
  And the user navigates to the "<stage>" stage
  And the Clinical Questions page header is shown as "<title>"
  And  the user selects "<diseaseStatueValue>"
  When the user provided the year values "<year>" for Age of onset fields
  And the user does not see an error message on the page
  Examples:
    | stage              | title                     | diseaseStatueValue | year |
    | Clinical questions | Answer clinical questions | Affected           | 1    |
    | Clinical questions | Answer clinical questions | Affected           | 15   |
    | Clinical questions | Answer clinical questions | Affected           | 125  |

  @NTS-3346 @E2EUI-995 @P0 @v_1
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the Clinical Questions page header is shown as "<title>"
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 50                   |

  @E2EUI-1546 @NTS-3433 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<ClinicalQuestions>" stage
    And the Clinical Questions page header is shown as "<title>"
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    Then the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Completed
    And the "<notes>" stage is selected
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                                           |  notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Unaffected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Uncertain:AgeOfOnset=10,02:HpoPhenoType=Lymphedema  |  Notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Unknown:AgeOfOnset=10,02:HpoPhenoType=Lymphedema    |  Notes |

  @E2EUI-1894 @NTS-3433 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3433 - Clinical Questions - clear the value from Disease status field
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<ClinicalQuestions>" stage
    And the Clinical Questions page header is shown as "<title>"
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    When the user clears the value that is set on on the close icon  placed in the Disease status field by clicking the close icon
    Then the Disease status field is not set with the disease status value "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Mandatory To Do
    And the "<notes>" stage is selected
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                                           |  notes |
      | Clinical questions | Answer clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   |  Notes |

  @E2EUI-1625 @NTS-3433 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3433 - Clinical Questions - Allow HPO terms to be deleted
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the Clinical Questions page header is shown as "<title>"
    # user adds 2 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>"
    And the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the Clinical Questions page header is shown as "<title>"
    And the user adds a new HPO phenotype term "<hpoTerm2>"
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page header is shown as "<title>"
    And the user adds a new HPO phenotype term "<hpoTerm3>"
    And the new HPO term "<hpoTerm3>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    # user deletes 1 HPO term
    When the user clicks the delete icon which is displayed across the "<hpoTerm3>"
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<removeIcon>" button and "<acknowledgeMessage>" it
    # confirm total number of HPO terms are one less than previous HPO terms count
    And the Clinical Questions page is displayed with at least "2" HPO terms in the HPO Phenotype section
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTerm3          | partOfMessage                     | removeIcon | acknowledgeMessage |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | Juvenile cataract | Confirm removal of this HPO term. | remove     | Accept             |


  @E2EUI-1546 @NTS-3433 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory for the Family Members
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    When the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field
    Then the user clicks the Save and Continue button
    And the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    When the user fills the clinical questions with the "<ClinicalQuestionDetails>" except to the Rare disease diagnosis field for the family member
    And the user clicks the Save and Continue button
    Then the user returns to family member landing page with the added family member details "<FamilyMemberDetails>"

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          |
