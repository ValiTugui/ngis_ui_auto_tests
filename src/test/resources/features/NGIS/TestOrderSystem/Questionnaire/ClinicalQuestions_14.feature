@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 14 - Validation of Dynamic Questions field in Questionnaire- Rare Disease/Cancer.

  @NTS-51831 @Z-LOGOUT
   #@E2EUI-1042
#    @NTS-4624  @E2EUI-1299
#    @NTS-4631  @E2EUI-1514
  Scenario Outline:NTS-5183:E2EUI-1042:Scenario-1: Validation of dynamic questions in Questionnaire- Rare Disease
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R88 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=16-5-1999:Gender=Male |
     ###Patient details
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user clicks the Save and Continue button
     ##Requesting Organisation
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the user clicks the Save and Continue button
     ###Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
     ### Responsible Clinician- Non Nullable Last Name
    When the user navigates to the "Responsible clinician" stage
    Then the user is navigated to a page with title Add clinician information
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "Responsible clinician" stage is marked as Mandatory To Do
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    Then the "Responsible clinician" stage is marked as Completed
     ### Clinical Questions-Mandatory Fields
    When the user is navigated to a page with title Answer clinical questions
    And the mandatory fields shown with the symbol in red color
      | mandatory_field | field_type | symbol | symbol color |
      | Disease status  | label      | ✱      | #dd2509      |
    And the user clicks the Save and Continue button
    Then the "Clinical questions" stage is marked as Mandatory To Do
    When the user navigates to the "Clinical questions" stage
    And the user selects "<DiseaseStatus>"
    Then the mandatory fields shown with the symbol in red color
      | mandatory_field               | field_type | symbol | symbol color |
      | Disease status                | label      | ✱      | #dd2509      |
      | Find an HPO phenotype or code | label      | ✱      | #dd2509      |
##    @NTS-4624  @E2EUI-1299
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader    |
      | Age of onset   |
      | Diagnosis      |
      | Status         |
      | Phenotypic sex |
      | Karyotypic sex |
     ### Min/Max validation
##   @NTS-4631  @E2EUI-1514
    And the user see error message when providing invalid age of onsets
      | year | month | errorMessage                                      |
      | -2.4 | 0     | Please enter whole years and months               |
      | 10   | 1.4   | Please enter whole years and months               |
      | -1   | 11    | Please enter prenatal age in negative months      |
      | 1    | 12    | Number of months can only exceed 11 if years is 0 |
      | 128  | 0     | Patient age cannot exceed 125 years               |
      | 24   | -1    | Only prenatal cases can have a negative number    |
      | 0    | -10   | Patient cannot be younger than -9 months          |
      | 0    | 1501  | Patient age cannot exceed 1500 months             |

    When the user provided the values "<yearvalue1>" "<monthvalue1>" for Age of onset fields
    Then the user sees an error "<errorMessage1>" message on the page
    And the user clicks the Save and Continue button
    Then the "Clinical questions" stage is marked as Mandatory To Do
    When the user provided the values "<yearvalue2>" "<monthvalue2>" for Age of onset fields
    And the user adds a new HPO phenotype term "<hpoTerm>" using the autosuggest terms
    And the user clicks the Save and Continue button

    Examples:
      | error_info            | red_color_hex_code | TestPackage  | ordering_entity_name | NoOfParticipants | DiseaseStatus | yearvalue1 | monthvalue1 | errorMessage1                       | yearvalue2 | monthvalue2 | hpoTerm                |rareDiseaseValue1         | diagnosisTypeValue1 | statusValue1 | TermPresence |
      | Last name is required | #dd2509            | Test package | Queen                | 1                | Affected      | 130        | 0           | Patient age cannot exceed 125 years | 5          | 8           | Phenotypic abnormality |BASAL CELL NEVUS SYNDROME | Omim                | Confirmed    | Present      |

  @NTS-5183 @Z-LOGOUT
  Scenario Outline:NTS-5183:E2EUI-1042:scenario-2: Validation of dynamic questions in Questionnaire- Cancer
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user clicks the Save and Continue button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                       | errorMessageHeader                           |
      | Date of diagnosis ✱               | Enter a year                                 |
      | The tumour is... ✱                | Please select the tumour type                |
      | Histopathology or SIHMDS Lab ID ✱ | Histopathology or SIHMDS Lab ID is required. |
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc
    ##We can fill any kind of data in Description field
    When the user attempts to fill in the Tumour Description "<TumourDescription>" with data that exceed the maximum data allowed 45
    Then the user is prevented from entering data that exceed that allowable maximum data 45 in the "TumourDescription" field

    Examples:
      | stage   | pageTitle    | TumourDescription                               |
      | Tumours | Add a tumour | 12345678@aAAjdlajldsa$*$*6()(*%^$^$^&%&&!(()190 |