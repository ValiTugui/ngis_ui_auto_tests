#@regression
#@clinicalQuestions
#@clinicalQuestionsFM
@TEST_ORDER
@SYSTEM_TEST
Feature: Clinical Question Page Validation

  @NTS-4438 @LOGOUT
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

  @NTS-4624 @LOGOUT
#    @E2EUI-1299
  Scenario Outline: NTS-4624 -To validate mandatory and non-mandatory input fields for Clinical question for Disease status section
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user is navigated to a page with title Check your patient's details
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader      |
      | Disease status ✱ |
    When the user selects "<DiseaseStatus>"
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                     |
      | Disease status ✱                |
      | Find an HPO phenotype or code ✱ |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader    |
      | Age of onset   |
      | Phenotypic sex |
      | Karyotypic sex |

    Examples:
      | stage              | title                     | DiseaseStatus |
      | Clinical questions | Answer clinical questions | Affected      |

  @NTS-4631 @LOGOUT
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


  @NTS-4679 @LOGOUT
#    @E2EUI-1479
  Scenario Outline: NTS-4679: UI | Recommended vs mandatory fields
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    ##Patient details Stage
    When the user is navigated to a page with title Check your patient's details
    ##Clinical questions Stage
    And the user navigates to the "<Clinical Questions Stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader      |
      | Disease status ✱ |
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
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                     |
      | Disease status ✱                |
      | Find an HPO phenotype or code ✱ |
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

  @NTS-4440 @LOGOUT
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
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader      |
      | Disease status ✱ |
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

  @NTS-4628 @LOGOUT
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

