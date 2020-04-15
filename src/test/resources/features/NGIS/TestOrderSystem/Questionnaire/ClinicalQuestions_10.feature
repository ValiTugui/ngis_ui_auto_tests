#@regression
#@clinicalQuestions
#@clinicalQuestionsFM
@03-TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 10 - Page Validation

  @NTS-4438 @Z-LOGOUT
#    @E2EUI-1273 @E2EUI-1198
  Scenario Outline: NTS-4438: Field headers on clinical questions page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<Stage>" stage
    And the user is navigated to a page with title Answer clinical questions
    And the user should be able to see the field headers on Clinical questions page
      | labelHeader                   |
      | Disease status details        |
      | HPO phenotype details         |
      | Rare disease diagnoses        |
      | Phenotypic and karyotypic Sex |
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
    Examples:
      | Stage              |
      | Clinical questions |

  @NTS-4631 @Z-LOGOUT
#    @E2EUI-1169
  Scenario Outline: Age of Onset field input validation with special characters under Disease status - Clinical questions
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R86 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    ##Patient details Stage
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##requesting organisation stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "ROYAL LIVERPOOL UNIVERSITY HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible ClinicianDetails stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
     ##clinical questions stage
    Then the user is navigated to a page with title Answer clinical questions
    When the user selects "<diseaseStatueValue>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    And the user sees an error "<errorMessage>" message on the page
    When the user provided the values "<specialChar1>" "<specialChar2>" for Age of onset fields
    Then the user should see that special characters are not allowed

    Examples:
      | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | diseaseStatueValue | year | month | errorMessage                        | specialChar1 | specialChar2 |
      | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Affected           | 2.4  | 0     | Please enter whole years and months | @@           | @#$          |

  @NTS-4679 @Z-LOGOUT
#    @E2EUI-1479
  Scenario Outline: NTS-4679: UI | Recommended vs mandatory fields
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    ##Patient details Stage
    When the user is navigated to a page with title Check your patient's details
    ##Clinical questions Stage
    And the user navigates to the "<Clinical Questions Stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the mandatory fields shown with the symbol in red color
      | mandatory_field | field_type | symbol | symbol color |
      | Disease status  | label      | ✱      | #dd2509      |
    Then the user should be able to see the hint text "<HintText>" for the "<DiseaseStatus>"
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader                   |
      | Age of onset                  |
      | Find an HPO phenotype or code |
      | Diagnosis                     |
      | Status                        |
      | Phenotypic sex                |
      | Karyotypic sex                |
    Then the user should be able to see the hint text "<HpoHintText>" for HPO phenotype details
    And the user should be able to see the field headers on Clinical questions page
      | labelHeader                   |
      | Disease status details        |
      | HPO phenotype details         |
      | Rare disease diagnoses        |
      | Phenotypic and karyotypic Sex |
    And the user should be able to see the hint text "<HintText>" for the "<PhenotypicSex>"
    And the user should be able to see the hint text "<HintText>" for the "<KaryotypicSex>"

    Examples:
      | Clinical Questions Stage | HintText | HpoHintText  | DiseaseStatus  | PhenotypicSex  | KaryotypicSex  |
      | Clinical questions       | Select   | Start typing | Disease status | Phenotypic sex | Karyotypic sex |

  @NTS-4440 @Z-LOGOUT
#    @E2EUI-1198
  Scenario Outline:NTS-4440:Form fields for the referral shown in sections (CI specific)
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=16-5-1987:Gender=Male |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user should be able to see the field headers on Clinical questions page
      | labelHeader                   |
      | Disease status details        |
      | HPO phenotype details         |
      | Rare disease diagnoses        |
      | Phenotypic and karyotypic Sex |
    And the mandatory fields shown with the symbol in red color
      | mandatory_field               | field_type | symbol | symbol color |
      | Disease status                | label      | ✱      | #dd2509      |
    And the user sees an bottom message "<Message1>" on the page
    And  the user selects "<diseaseStatueValue>"
    When the user provided the values "<year>" "<month>" for Age of onset fields
    And the user sees an bottom message "<Message2>" on the page
    And the user adds a new HPO phenotype term "<hpoTerm>" using the autosuggest terms
    And the user sees an bottom message "<Message3>" on the page
    When the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user answers the phenotypic and karyotypic sex questions
    Then the user clicks the Save and Continue button
    Examples:
      | stage              | diseaseStatueValue | Message1                                             | Message3                                             | year | month | Message2                                                            | hpoTerm | termPresence | diagnosisTypeValue | statusValue | rareDiseaseValue                                |
      | Clinical questions | Unaffected         | Choose the status of the condition being tested for. | For example, ventricular fibrillation or HP:0001663. | 2    | 3     | For prenatal patients, enter number of months before birth, e.g. -3 | Leuk    | Present      | Orphanet           | Suspected   | Lissencephaly with cerebellar hypoplasia type A |

  @NTS-4628 @Z-LOGOUT
#    @E2EUI-1338
  Scenario Outline: NTS-4628: Create the SNOMED custom dynamic unit
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user is navigated to a page with title Check your patient's details
    And  the user navigates to the "<TumourStage>" stage
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
    And the user fills the Topography of "<PrimaryTumour>" SnomedCT and Topography of this "<Tumour>" SnomedCT with result drop list
    And the user clicks on the Tumour Diagnosis add another link
    And the user fills the Topography of "<PrimaryTumour>" SnomedCT and Topography of this "<Tumour>" SnomedCT with result drop list
    And the user fills the Working diagnosis "<morphology>" SnomedCT with result drop list
    And the user clicks on the Working diagnosis morphology add another link
    And the user fills the Working diagnosis "<morphology>" SnomedCT with result drop list
    And the user clicks the Save and Continue button
    Then the success notification is displayed "<TumourAdded>"
    When the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    Then the user is navigated to a page with title Edit a tumour
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Answer questions about this tumour

    Examples:
      | TumourAdded  | TumourStage | PrimaryTumour | Tumour | morphology |
      | Tumour added | Tumours     | testis        | Liver  | solid      |

