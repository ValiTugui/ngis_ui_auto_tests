#@regression
#@panelsPage_LandingPage
@06-PANEL_ASSIGNER
@SYSTEM_TEST
@SYSTEM_TEST_2
  ##This feature files have to be re-implemented based on new/modified tickets for Panel App.
  ##This changes are from Hanna Release
Feature: PanelAssigner: Panels Page Landing Page

  @NTS-3380 @NTS-3381 @NTS-3424 @NTS-5818 @NTS-5947-Scenario-4 @NTS-5818 @Z-LOGOUT
#    @E2EUI-1231 @E2EUI-1906 @E2EUI-1278 @E2EUI-976 @E2EUI-1045 @E2EUI-1484 @E2EUI-1158
#    @E2EUI-3015 @E2EUI-2978 @E2EUI-2979 @E2EUI-1469 @E2EUI-1322 @E2EUI-1278 @E2EUI-1258
  Scenario Outline: NTS-3380: Search and add panels to referral
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
    ##NTS-5947 @Scenario4
    And the panels landing page displays the introduction message as shown below
      | The test package requires:         |
      | confirmation of disease penetrance |
      | addition of at least one panel     |
    ##NTS-3381
    And the user should be able to see panels page is correctly displayed
    And the user should see the section with title Default Panel based on the clinical information
    And the user sees suggested panels under the section Default Panel based on the clinical information
    ##@E2EUI-1906
    And the user should be able to see a sub title Confirm disease penetrance on panels page
    Then the user should be able to see an additional line "<textLine>" underneath the penetrance title
    ##@E2EUI-1278
    And the user should be able to see Add another panel section with search field and search icon
    #Below steps for NTS-5818
    And the user should see the Add Panels section with the Only ticked panels will be sent for analysis
    ##@E2EUI-1231
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user clicks on Complete button and button will show tick marked
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    Then the user attempts to navigate away by clicking "back"
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user sees the selected "<searchPanels>" panels under added panels
    Then the user should see the referral submit button as disabled
    ##NTS-3381
    Then the user should be able to deselect the selected panels
    And the user should be able to change the penetrance status
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page
    ##NTS-5818
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user sees a prompt alert "This section contains unsaved information. Discard changes?" after clicking "<ClinicalQuestion>" button and "OK" it


    Examples:
      | Panels | ClinicalQuestion   | ClinicalQuestionDetails                                         | searchPanels                                  | textLine                                                                                                                                                                   |
      | Panels | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Cardiac arrhythmias,Pigmentary skin disorders | Change suggested penetrance if: there is a referral form that confirms a different penetrance local decision-making processes indicate a different penetrance is preferred |
