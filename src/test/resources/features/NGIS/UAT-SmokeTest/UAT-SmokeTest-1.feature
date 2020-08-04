@UAT_UI_SmokeTest_Pack

Feature: Navigation of Test Selection, Test Order, Mi Portal, Panel App and Interpretation Portal with Basic Checks from Dashboard

  @NTS-6426
  Scenario: Navigation to Test Selection, Test Order, Mi Portal, Panel App and Interpretation Portal with Basic Checks from Dashboard
  #Dashboard Page
    Given a web browser is at the dashboard page
    And User should be able to see my Dashboard
    And The user should see the Page title as "Welcome to the National Genomic Informatics System"
    When the user should be able to see clickable tabs
  #Test Order from Dashboard
    When the user clicks on "Order a genomic test" Tab
    And the user logs in to the "Test Order" system
    And the user is navigated to a page with title Find your patient
    When the user clicks the Log out button
    And User should be able to see my Dashboard
  #PanelApp from Dashboard
    And the user clicks on "Open PanelApp" Tab
    And the user is navigated to a page with title Genomics England PanelApp
    And User Navigates back to Dashboard
    And User should be able to see my Dashboard
  #Interpretation Portal from Dashboard
    And the user clicks on "Enter the Interpretation Portal" Tab
    And the user logs in to the Interpretation Portal system
    And the user is navigated to a page with title GMS: Interpretation Portal
    When the user clicks the Log out button