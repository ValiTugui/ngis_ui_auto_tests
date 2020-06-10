#@regression
#@panelsPage_LandingPage
@06-PANEL_ASSIGNER
@SYSTEM_TEST
   ##This feature files have to be re-implemented based on new/modified tickets for Panel App.
  ##This changes are from Hanna Release
Feature: PanelAssigner: Panels Page Landing Page

  @NTS-3424 @Z-LOGOUT
#    @E2EUI-1469 @E2EUI-1322 @E2EUI-1278 @E2EUI-1258
  Scenario Outline: NTS-3424: Search and add panels to referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the panels landing page displays the introduction message as shown below
      | The test package requires:         |
      | confirmation of disease penetrance |
      | addition of at least one panel     |
#    And the user should be able to see a sub title Penetrance on panels page
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user clicks on Complete button and button will show tick marked
    And the user should be able to see an additional line "<penetranceIntro>" underneath the penetrance title
    And the user should see the section with title Suggestions based on the clinical information
    And the user sees suggested panels under the section Suggestions based on the clinical information
    And the user sees link with title View On PanelApp attached to all the suggested panels
    And the user should be able to see Add another panel section with search field and search icon
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page
    And the user clicks the Save and Continue button

    Examples:
      | ClinicalQuestion   | ClinicalQuestionDetails                                         | Panels | searchPanels                                  | penetranceIntro                                                                                                                                                            |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Panels | Cardiac arrhythmias,Pigmentary skin disorders | Change suggested penetrance if: there is a referral form that confirms a different penetrance local decision-making processes indicate a different penetrance is preferred |