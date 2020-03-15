@regression
@panelsPage
@panelsPage_AddPanel

Feature: Panels Page Verification

  @NTS-3380 @E2EUI-1231 @E2EUI-1906 @E2EUI-1278 @E2EUI-976 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3380: Search and add panels to referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panel
    ##@E2EUI-1231
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user clicks on Complete button and button will show tick marked
    ##@E2EUI-1906
    And the user should be able to see a sub title Penetrance on panels page
    Then the user should be able to see an additional line "<textLine>" underneath the penetrance title
    ##@E2EUI-1278
    And the user should be able to see Add another panel section with search field and search icon
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    ##panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panel
    And the user sees the selected "<searchPanels>" panels under added panels
    Then the user should see the referral submit button as disabled

    Examples:
      | Panels | searchPanels                                  | textLine                                                                                                      |
      | Panels | Cardiac arrhythmias,Pigmentary skin disorders | If penetrance is marked 'unknown' on the request form, leave the default setting for the clinical indication. |

  @NTS-3381 @E2EUI-1045 @E2EUI-1484 @E2EUI-1158 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3381: Suggest and select panels on panels page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user should be able to see panels page is correctly displayed
    ##Below line for @E2EUI-1484
    And the user should see the section with title Suggestions based on the clinical information
    And the user sees suggested panels under the section Suggestions based on the clinical information
    When the user search and add the "<searchPanels>" panels
    Then the user should be able to deselect the selected panels
    And the user should be able to change the penetrance status
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page

    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | searchPanels |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | cardiac arr  |
