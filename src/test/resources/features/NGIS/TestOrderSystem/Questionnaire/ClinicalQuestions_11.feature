#@regression
@clinicalQuestions
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: ClinicalQuestions 11 - RD Questionnaire

  @NTS-3209 @Z-LOGOUT
#    @E2EUI-2089 @E2EUI-1404
  Scenario Outline: NTS-3209 - Clinical Questions - Display HPO terms newest to the oldest when added
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Test Order Forms
#    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
    ##Patient details Stage
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm1>"
    Then the new HPO term "<hpoTerm1>" appears at the top of the list of the HPO terms
    And the user selects the HPO phenotype questions such as Name, Term presence "<termPresence>" and corresponding modifier
    Then the "<title>" page is displayed
    When the user adds a new HPO phenotype term "<hpoTerm2>"
    Then the new HPO term "<hpoTerm2>" appears at the top of the list of the HPO terms
    And the Clinical Questions page is displayed with at least "<hpoTermsCount>" HPO terms in the HPO Phenotype section
    And the referral submit button is not enabled

    Examples:
      | stage              | title                     | hpoTerm1                | hpoTerm2  | hpoTermsCount | termPresence |
      | Clinical questions | Answer clinical questions | Sparse and thin eyebrow | Anonychia | 2             | Present      |