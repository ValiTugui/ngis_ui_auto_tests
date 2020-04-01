#@regression
#@CancerQuestionnaire
@TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - ClinicalQuestions 13 - Tumour Questionnaire

  @NTS-4633 @LOGOUT
    #@E2EUI-1108 @scenario1
  Scenario Outline: NTS-4633: As a user I when the form is submitted, we must submit data for units even if the answers are blank - this allows the user to erase/correct previously submitted answers.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=12-03-1986:Gender=Female |
     ##Patient Details stage
    When the user is navigated to a page with title Check your patient's details
    ## Clinical questions stage
    And the user navigates to the "<Stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user should be able to see all the fields left blank in clinical questions page
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
    ## Clinical questions stage
    When the user navigates to the "<Stage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user should be able to see all the fields left blank in clinical questions page

    Examples:
      | Stage              |
      | Clinical questions |

  @NTS-4633 @LOGOUT
    #@E2EUI-1108 @scenario2 @LOGOUT
  Scenario Outline: NTS-4633: As a user I when the form is submitted, we must submit data for units even if the answers are blank - this allows the user to erase/correct previously submitted answers.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=12-03-1986:Gender=Female |
     ##Patient Details stage
    When the user is navigated to a page with title Check your patient's details
    ###Tumour stage
    And  the user navigates to the "<TumourStage>" stage
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the "Tumours" stage is marked "MandatoryToDo"

    Examples:
      | TumourStage |
      | Tumours     |