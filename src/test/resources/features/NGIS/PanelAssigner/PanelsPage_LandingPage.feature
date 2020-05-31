#@regression
#@panelsPage_LandingPage
@06-PANEL_ASSIGNER
@SYSTEM_TEST_Deprecated
   ##This feature files have to be re-implemented based on new/modified tickets for Panel App.
  ##This changes are from Hanna Release
Feature: PanelAssigner: Panels Page Landing Page

  @NTS-3424 @Z-LOGOUT
#    @E2EUI-1469 @E2EUI-1322 @E2EUI-1278 @E2EUI-1258
  Scenario Outline: NTS-3424: Search and add panels to referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panel
    And the panels landing page displays the introduction message as "<IntroMessage>"
    And the user should be able to see a sub title Penetrance on panels page
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
      | ClinicalQuestion   | ClinicalQuestionDetails                                         | Panels | IntroMessage                                                                                                                                                                                                              | searchPanels                                  | penetranceIntro                                                                                               |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Panels | The selected tests require a panel and penetrance setting. Don't worry if you don't know what panels you need. Please submit your request and discuss panel selection with your lab once they have received your request. | Cardiac arrhythmias,Pigmentary skin disorders | If penetrance is marked 'unknown' on the request form, leave the default setting for the clinical indication. |