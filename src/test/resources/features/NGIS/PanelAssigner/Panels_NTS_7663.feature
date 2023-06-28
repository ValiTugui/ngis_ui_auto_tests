@06-PANEL_ASSIGNER
@SYSTEM_TEST
@SYSTEM_TEST_2

Feature: PanelAssigner: Default Panel to not be de-selectable

  @NTS-7663 @Z-LOGOUT
  Scenario Outline: NTS-7661: Default Panel to not be de-selectable
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    And the "Patient details" stage is marked as Completed
    When the user is navigated to a page with title Check your patient's details
   ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user should see the section with title Default Panel based on the clinical information
    And the user sees suggested panels under the section Default Panel based on the clinical information
    Then the user should not be able to deselect the suggested panel


    Examples:
      | Panels |
      | Panels |
