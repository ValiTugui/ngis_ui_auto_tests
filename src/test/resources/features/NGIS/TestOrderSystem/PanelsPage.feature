@regression
@regression_set2
@panelsPage

Feature: Panels Page Verification

  @COMP9_TO_Panels
    @COMP9_TO_Panels_01 @LOGOUT @NTS-3380 @E2EUI-1278  @v_1 @P0
  Scenario Outline: NTS-3380: Search and add panels to referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panel
    And the user should be able to see add another panel search field and search icon
    And the user should  be able to search and add the "<searchPanels>" panels
    And the user should be able to see selected panels
    Then the user sees the selected panels under added panels
    And the user clicks on Save and Continue in Panels Page
    And the user is navigated to a page with title Build a pedigree
    When the user navigates to "<Panels>" stage
    Then the user sees the selected panels under added panels

    Examples:
      | Panels | searchPanels |
      | Panels | cardiac arr  |

  @COMP9_TO_Panels
    @COMP9_TO_Panels_02 @LOGOUT @NTS-3379 @E2EUI-1231 @v_1 @P0
  Scenario Outline: NTS-3379: User is making a referral, as a user I will see a clear tick on the icon when I select yes or no
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449303827:DOB=27-03-2011 |
    When the user navigates to "<Panels>" stage
    And the user is navigated to a page with title Panels
    Then the user should be able to see the button options present
    And the user clicks on "<Option1>" button and button will show tick marked
    And the user clicks on "<Option2>" button and button will show tick marked

    Examples:
      | Panels | Option1    | Option2  |
      | Panels | Incomplete | Complete |

  @COMP9_TO_Panels
    @COMP9_TO_Panels_03 @LOGOUT @NTS-3381 @E2EUI-1045  @v_1 @P0
  Scenario Outline: NTS-3381: Suggest and select panels on panels page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449303894:DOB=26-11-1986 |
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks on Save and Continue in Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user should be able to see panels page is correctly displayed
    When the user should  be able to search and add the "<searchPanels>" panels
    Then the user should able to deselect the selected panels
    And the user should be able to change the penetrance status
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page

    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | searchPanels |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | cardiac arr  |



  @COMP9_TO_Panels
    @COMP9_TO_Panels_04 @LOGOUT @NTS-3413 @E2EUI-1906  @v_1 @P0
  Scenario Outline: NTS-3413 : verify the text under penetrance title
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449303827:DOB=27-03-2011 |
    When the user navigates to "<Panels>" stage
    And the user is navigated to a page with title Panels
    And the user should be able to see a sub title "<penetrance>" on panels page
    Then the user should be able to see an additional line "<textLine>" underneath the penetrance title

    Examples:
      | Panels | penetrance | textLine                                                                                                      |
      | Panels | Penetrance | If penetrance is marked 'unknown' on the request form, leave the default setting for the clinical indication. |

  @COMP9_TO_Panels
    @COMP9_TO_Panels_05 @LOGOUT @E2EUI-1484 @NTS-3424 @v_1 @P0
  Scenario Outline: NTS-3424 : Suggest and select panels on panels page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | NGIS | Rare-Disease | NHSNumber=9449303894:DOB=26-11-1986 |
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks on Save and Continue in Panels Page
    When the user navigates to "<Panels>" stage
    Then the user is navigated to a page with title Panels
    Then the user should be able to see the list of the panels under the "<Section>" section
    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | Section                                       |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=02,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | Suggestions based on the clinical information |
