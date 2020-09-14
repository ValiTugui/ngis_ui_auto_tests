#@regression
@06-PANEL_ASSIGNER
@SYSTEM_TEST

Feature: PanelAssigner:  Validation for Panels heading and info texts

  @NTS-5818
#    @E2EUI-3015
  Scenario Outline:NTS-5818:E2EUI-3015 Dev - Panels -introduce panels heading and info text
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user should see the Add Panels section with the <message>


    Examples:
      | Panels | message                                      |
      | Panels | Only ticked panels will be sent for analysis |

  @NTS-5818
#  @E2EUI-2978
  Scenario Outline:E2EUI-2978 User visits the panel,change the selected panel,add additional panels and Save
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1982:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##  Clinical questions Page
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
          ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user sees suggested panels under the section Suggestions based on the clinical information
    Then the user should be able to deselect the selected panels
    And the user should be able to see Add another panel section with search field and search icon
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the user attempts to navigate away by clicking "back"
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    Examples:
      | ClinicalQuestions  | ClinicalQuestionDetails                                                    | Panels | searchPanels        |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,02:HpoPhenoType=Phenotypic abnormality | Panels | Cardiac arrhythmias |

  @NTS-5818
#  @E2EUI-2979
  Scenario Outline:E2EUI-2979 User visits the panel,navigates away without saving the changes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1978:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    Then the user should be able to deselect the selected panels
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage>" after clicking "<newStage>" button and "<acknowledgeMessage>" it

    Examples:
      | Panels | searchPanels     | warningMessage                                              | acknowledgeMessage | newStage       |
      | Panels | Optic neuropathy | This section contains unsaved information. Discard changes? | OK                 | Patient choice |