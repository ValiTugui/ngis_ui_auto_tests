#@regression
@06-PANEL_ASSIGNER
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: PanelAssigner: NTOS-5043: Panels are mandatory if there are no suggested panels and no selected panels

  @NTOS-5043 @Z-LOGOUT
  Scenario Outline: NTOS-5043: panels stage has no status indicator
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    Then the user sees the "<Panels>" stage has no status indicator
    Then the user should see the section with title Suggestions based on the clinical information
    And the user sees suggested panels under the section Suggestions based on the clinical information
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed

    Examples:
      | Panels | searchPanels |
      | Panels | cardiac arr  |

  @NTOS-5043 @Z-LOGOUT
    #@Scenario2
  Scenario Outline: NTOS-5043: panels stage has no suggested panels
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R89 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    And the user sees the No suggested panels found message on the page
    Then the "<Panels>" stage is marked as Mandatory To Do
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Message>"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
      | Panels                    |

    And the user should be able to click on incomplete "<Panels>" section
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    When the "<Panels>" stage is marked as Mandatory To Do
    Then the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Message>"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
      | Panels                    |

    And the user should be able to click on incomplete "<Panels>" section
    Then the user is navigated to a page with title Manage panels

    Examples:
      | Panels | Message                      |
      | Panels | There is missing information |
