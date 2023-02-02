#@regression
@02-DASHBOARD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: DashBoard - Dashboard Page

  @NTS-3158
 Scenario Outline: NTS-3158 - Dashboard - To verify the Dashboard - Home Page after login as Super user
#    Given the user clears all the current session cookies
    Given a web browser is at the dashboard page with super user
    And User should be able to see my Dashboard
    Then The user should see the Page title as "Welcome to the National Genomic Informatics System"
# Added @E2EUI-1977
    When the user sees the NHS logo on top in left side
    Then the user should be able to see clickable tabs
    And the user should be able to clicks on "<Tabs>"
    Examples:
      | Tabs                                                                                                                                |
      | Find a genomic test,Order a genomic test,Manage samples,Enter the Interpretation Portal,Open PanelApp,Access the service admin tool |