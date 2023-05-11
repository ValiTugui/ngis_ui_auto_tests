@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: ClinicalQuestions 13 - Page Validation

  @NTS-4672 @Z-LOGOUT
#    @E2EUI-1378
  Scenario Outline: NTS-4672 : Implement "repeating" units from dynamic content framework
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER | child |
    ##Patient Details
    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Patient details Stage
    Then the user is navigated to a page with title Add a requesting organisation
#    When the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
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

  @NTS-3346 @Z-LOGOUT
#    @E2EUI-995
  Scenario Outline: NTS-3346 - Clinical Questions - Page Layout - Verify enum values in dropdown
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Patient details Stage
    Then the user is navigated to a page with title Add a requesting organisation
#    When the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And the HPO phenotype drop-down is allowed to have values up to "<maximumAllowedValues>"
    And the OMIM and Oprhanet drop-down is allowed to have values up to "<maximumAllowedValues>"
    #Drop down values showed as 200
    Examples:
      | stage              | title                     | maximumAllowedValues |
      | Clinical questions | Answer clinical questions | 200                   |