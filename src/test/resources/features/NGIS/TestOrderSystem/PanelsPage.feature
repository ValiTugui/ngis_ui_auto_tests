@regression
@COMP9_TO_Panels
@Panels

Feature: Panels

  @COMP9_TO_Panels
    @COMP9_TO_Panels01 @LOGOUT @NTS-3380 @E2EUI-1278 @v_1 @P0
  Scenario Outline: E2EUI-1278: Search and add panels to referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panel
    And the user should be able to see add another panel search field and search icon
    And the user should  be able to search and add the "<searchPanels>"panels
    And the user should be able to see selected panels
    Then the user sees the selected panels under added panels
##    As observed, below line is not able to click and perform here, so using twice
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Build a pedigree
##    As observed, below line is not able to click and perform here, so using twice
    When the user navigates to the "<Panels>" stage
    When the user navigates to the "<Panels>" stage
    Then the user sees the selected panels under added panels

    Examples:
      | Panels | searchPanels |
      | Panels | cardiac arr  |

  @COMP9_TO_Panels
    @COMP9_TO_Panels02 @LOGOUT @NTS-3381 @E2EUI-1045 @v_1 @P0
  Scenario Outline: E2EUI-1045: Suggest and select panels on panels page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
    And the user should  be able to search and add the "<searchPanels>"panels
    And the user should be able to see panels page is correctly displayed
    And the user should able to deselect the selected panels
    And the user should be able to change the penetrance status
    Then the user clicks on VisitPanelApp link and navigates to panelApp page

    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | searchPanels |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | cardiac arr  |

  @COMP9_TO_Panels
    @COMP9_TO_Panels03 @LOGOUT @NTS-3379 @E2EUI-1231 @v_1 @P0
  Scenario Outline: E2EUI-1231: User is making a referral, as a user I will see a clear tick on the icon when I select yes or no
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Stage>" stage
    And the user is navigated to a page with title Panels
    And the user should be able to see the button options present
    Then the user clicks on "<Option1>" button and button will show tick marked
    Then the user clicks on "<Option2>" button and button will show tick marked

    Examples:
      | Stage  | Option1    | Option2  |
      | Panels | Incomplete | Complete |
