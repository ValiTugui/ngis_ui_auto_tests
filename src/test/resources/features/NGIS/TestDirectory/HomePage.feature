@regression
@testDirectory
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-1366 @NTS-3160 @v_1 @P0 @COMP1_TD_ClinicalIndications
Scenario: NTS-3160 - Home Page - Show number of results in a tab
    And the user types in the CI term  in the search field
      | 270 |
    Then the search results have been displayed
    And the number of results shown in each filters & total results should match

  @E2EUI-1793 @NTS-3168 @v_1 @P0 @COMP1_TD_Tests
  Scenario: NTS-3168 - Home Page - The tests from Tests tab are loaded properly
    And the user selects the Tests tab
    Then various test details are displayed