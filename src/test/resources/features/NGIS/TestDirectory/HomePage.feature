@testDirectory
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-1366 @NTS-3160 @v_1 @P0
Scenario: NTS-3160 - Home Page - Show number of results in a tab
    When a user clicks on any search filter
    Then the search results have been displayed
    And the number of results shown in each filters & total results should match