@clinicalIndicationTestSelect
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

@E2EUI-2093 @NTS-3161 @v_1 @P0
Scenario: NTS-3161 - Clinical Indication Page - Loading wheel for Test Detail page/Clinical Indications tab
  When the user has selected a test "270"
    Then the text "Please wait a moment - clinical indications are loading" is displayed
  And the text "This test cannot be ordered yet" is not displayed
  And the loading wheel is displayed
  And the list of clinical indications are loaded