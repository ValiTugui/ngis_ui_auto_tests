#@regression
@02-DASHBOARD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: DashBoard - Dashboard Page

  @NTS-3158
#@E2EUI-1974  @E2EUI-1977
  Scenario: NTS-3158 - Dashboard - To verify the Dashboard - Home Page after login as Normal user
    Given a web browser is at the dashboard page
    And User should be able to see my Dashboard
    Then The user should see the Page title as "Welcome to the National Genomic Informatics System"
# Added @E2EUI-1977
    When the user sees the NHS logo on top in left side
    Then the user should be able to see clickable tabs