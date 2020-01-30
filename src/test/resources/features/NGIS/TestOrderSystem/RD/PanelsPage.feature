@regression
@TO_RD
@panelsPage

Feature: Panels Page Verification

  @NTS-3380 @E2EUI-1278 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3380: Search and add panels to referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1967:Gender=Male |
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panel
    And the user should be able to see Add another panel section with search field and search icon
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks on Save and Continue in Panels Page
    Then the user is navigated to a page with title Build a pedigree
    When the user navigates to "<Panels>" stage
    Then the user sees the selected "<searchPanels>" panels under added panels

    Examples:
      | Panels | searchPanels              |
      | Panels | Cardiac arrhythmias,CAKUT |

  @NTS-3379 @E2EUI-1231 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3379: User is making a referral, as a user I will see a clear tick on the icon when I select yes or no
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1968:Gender=Male |
    When the user navigates to "<Panels>" stage
    And the user is navigated to a page with title Panels
    Then the user should be able to see the button options present
    And the user clicks on "<Option1>" button and button will show tick marked
    And the user clicks on "<Option2>" button and button will show tick marked

    Examples:
      | Panels | Option1    | Option2  |
      | Panels | Incomplete | Complete |

  @NTS-3381 @E2EUI-1045 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3381: Suggest and select panels on panels page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks on Save and Continue in Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user should be able to see panels page is correctly displayed
    When the user search and add the "<searchPanels>" panels
    Then the user should be able to deselect the selected panels
    And the user should be able to change the penetrance status
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page

    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | searchPanels | PanelAppURL                                                |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | cardiac arr  | https://stage-panelapp-cloud.genomicsengland.co.uk/panels/ |

  @NTS-3413 @E2EUI-1906 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3413 : verify the text under penetrance title
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    When the user navigates to "<Panels>" stage
    And the user is navigated to a page with title Panels
    And the user should be able to see a sub title "<penetrance>" on panels page
    Then the user should be able to see an additional line "<textLine>" underneath the penetrance title

    Examples:
      | Panels | penetrance | textLine                                                                                                      |
      | Panels | Penetrance | If penetrance is marked 'unknown' on the request form, leave the default setting for the clinical indication. |

  @NTS-3424 @E2EUI-1484 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3424 : Suggest and select panels on panels page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks on Save and Continue in Panels Page
    When the user navigates to "<Panels>" stage
    Then the user is navigated to a page with title Panels
    Then the user should be able to see suggested panels under the "<Section>" section

    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                                    | Panels | Section                                       |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=02,02:HpoPhenoType=Agenesis of maxillary lateral incisor | Panels | Suggestions based on the clinical information |
