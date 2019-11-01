@testDirectory
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-1366 @NTS-3160 @v_1 @P0 @COMP1_TD_ClinicalIndications
Scenario: NTS-3160 - Home Page - Show number of results in a tab
    When a user clicks on any search filter
    Then the search results have been displayed
    And the number of results shown in each filters & total results should match

  @E2EUI-1793 @NTS-3168 @v_1 @P0 @COMP1_TD_Tests
  Scenario: NTS-3168 - Home Page - The tests from Tests tab are loaded properly
    And The user selects the Tests tab
    Then Various test details are displayed

  @E2EUI-1755 @NTS-3169 @v_1 @P0 @COMP1_TD
  Scenario: NTS-3169 - Home Page - Add NGIS version number to TDS
    And The user has scrolled down the page to the bottom (Footer)
    Then The user can see the NGIS version number on the right side bottom of the page next to the privacy policy link