#@regression
@02-DASHBOARD
@SYSTEM_TEST
Feature: Dashboard Page

#  Background:
#    Given a web browser is at the dashboard page

  ##No need to mention the background as single Given step in first scenario is applicable for both the scenarios


@NTS-3158
#@E2EUI-1974
  Scenario: NTS-3158 - Dashboard - To verify the Dashboard - Home Page Title displayed correctly
    Given a web browser is at the dashboard page
    And User should be able to see my Dashboard
    Then The user should see the Page title as "Welcome to the National Genomic Informatics System"

  @NTS-3158
#  @E2EUI-1977
  Scenario: NTS-3158 - verify the dashboard layout in IE11
#    Given a web browser is at the dashboard page
    When the user sees the NHS logo on top in left side
    Then the user should be able to see clickable tabs


