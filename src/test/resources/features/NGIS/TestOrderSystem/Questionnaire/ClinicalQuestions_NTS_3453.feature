#@regression
#@clinicalQuestions
@TEST_ORDER
@SYSTEM_TEST
Feature: RD Questionnaire

  Background:
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3453 @LOGOUT
#    @E2EUI-881
  Scenario Outline: NTS-3453 - Clinical Questions -  landing page is marked as mandatory
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
      | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestions  |
      | Requesting organisation | Maidstone            | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions |

  @NTS-3453 @LOGOUT
#  @E2EUI-1124
  Scenario Outline: NTS-3453 - Clinical Questions -  mandatory field validations for Disease status field
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the "<title>" page is displayed
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>" except to the disease status field
    And the user selects a value "CEREBRAL SARCOMA" from the Rare disease diagnosis
    And the user clicks the Save and Continue button
    Then the "<notes>" stage is selected
    And the user navigates to the "<ClinicalQuestions>" stage
    Then the Disease status field is not set with the disease status value "<ClinicalQuestionDetails>"
    And the "<ClinicalQuestions>" stage is marked as Mandatory To Do
    Examples:
      | ClinicalQuestions  | title                     | ClinicalQuestionDetails                  | notes |
      | Clinical questions | Answer clinical questions | AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes |
