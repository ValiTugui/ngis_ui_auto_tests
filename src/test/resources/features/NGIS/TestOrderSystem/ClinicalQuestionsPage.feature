@regression
@regression_set1
@clinicalQuestions

Feature: Clinical Questions stage

  @E2EUI-2089 @NTS-3209 @E2EUI-1404 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalQuestions @BVT_P0
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

  @E2EUI-1972 @NTS-3240 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

  @E2EUI-1610 @NTS-3245 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions @BVT_P0
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

@E2EUI-1531 @E2EUI-992 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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


@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

@E2EUI-1531 @NTS-3246 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

  @NTS-3346 @E2EUI-995 @P0 @v_1
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

  @E2EUI-1546 @NTS-3433 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
  Scenario Outline: NTS-3433 - Clinical Questions - Rare Disease Diagnosis field is not mandatory
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
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
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
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
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    # user adds 2 HPO terms
    And the user adds a new HPO phenotype term "<hpoTerm1>"
    And the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
    And the user adds a new HPO phenotype term "<hpoTerm2>"
    And the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    Then the "<title>" page is displayed
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
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
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

  @E2EUI-1443 @E2EUI-918 @NTS-3439 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions @BVT_P0
  Scenario Outline: NTS-3439 - Clinical Questions -  scenario 1 - verify the 'Save and Continue' button on the Clinical Questions stage
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    Then the user navigates to the "<stage>" stage
    And the user sees the data such as "<hpoTerm1>" "<ClinicalQuestionDetails>" "<rareDiseaseValue>" phenotypic and karyotypic sex are saved
    Examples:
      | stage              | title                     | hpoTerm1                | termPresence | ClinicalQuestionDetails                | rareDiseaseValue          |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Present      | DiseaseStatus=Affected:AgeOfOnset=10,3 | BASAL CELL NEVUS SYNDROME |

  @E2EUI-881 @NTS-3453 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

  @E2EUI-1124 @NTS-3453 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions
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

  @E2EUI-1443 @E2EUI-918 @E2EUI-1351 @NTS-3439 @LOGOUT @v_1 @P0 @COMP6_TO_ClinicalQuestions @BVT_P0
  Scenario Outline: NTS-3439 - Clinical Questions -  scenario 2 - Return enum values for previous answers
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Test package" stage
    And the user selects the number of participants as "2"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    When the user navigates to the "Family members" stage
    And the user should be able to see the patient identifiers on family member landing page
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user navigates to the "<stage>" stage
    And the user sees the data such as "<hpoTerm1>" "<ClinicalQuestionDetails>" "<rareDiseaseValue>" phenotypic and karyotypic sex are saved
    Examples:
      | stage              | title                     | hpoTerm1                | termPresence | ClinicalQuestionDetails                | FamilyMemberDetails                 | RelationshipToProband | rareDiseaseValue          |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Present      | DiseaseStatus=Affected:AgeOfOnset=10,3 | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | BASAL CELL NEVUS SYNDROME |

