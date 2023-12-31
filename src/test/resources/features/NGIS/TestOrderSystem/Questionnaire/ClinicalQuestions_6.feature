@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: ClinicalQuestions 6 - RD Questionnaire

  @NTS-3439 @Z-LOGOUT
#    @E2EUI-1443 @E2EUI-918 @E2EUI-1351 @E2EUI-902
  Scenario Outline: NTS-3439 - Clinical Questions -  scenario 2 - Return enum values for previous answers
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#
    And the "Patient details" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Test Order Forms
    And the user clicks the Continue button
    ##Patient details Stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Test package" stage
    And the user selects the number of participants as "2"
    And the user clicks the Save and Continue button
   ##@NTS-4735 @E2EUI-1884
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    And  the user selects "<diseaseStatueValue>"
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user selects a value "<rareDiseaseValue>" from the Rare disease diagnosis
    And the user selects the Rare disease diagnosis questions such as "<diagnosisTypeValue>" and corresponding status "<statusValue>"
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    And the "Notes" stage is selected
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-02-2011:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=00,02:HpoPhenoType=Lymphedema |
    And the family member details on family Member landing page is correctly displayed
    And the user navigates to the "<stage>" stage
    And the user sees the data such as "<diseaseStatueValue>" "<hpoTerm1>" "<ClinicalQuestionDetails>" "<rareDiseaseValue>" phenotypic and karyotypic sex are saved
    Examples:
      | stage              | title                     | hpoTerm1                | diagnosisTypeValue | statusValue | termPresence | ClinicalQuestionDetails                 | rareDiseaseValue | diseaseStatueValue | NoOfParticipants |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Omim               | Suspected   | Present      | AgeOfOnset=10,3:HpoPhenoType=Lymphedema | ACHONDROPLASIA   | Affected           | 2                |
