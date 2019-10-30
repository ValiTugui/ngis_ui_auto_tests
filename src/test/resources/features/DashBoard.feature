@dashBoard
Feature: Dashboard Page

  Background:
    Given a web browser is at the dashboard page


@E2EUI-1974 @NTS-3158 @v_1 @P0 @COMP1_TOC_Dashboard

Scenario: NTS-3158 - Dashboard - To verify the Dashboard - Home Page Title displayed correctly
    And User should be able to see my Dashboard
    Then The user should see the Page title as "Welcome to the National Genomic Informatics System"

