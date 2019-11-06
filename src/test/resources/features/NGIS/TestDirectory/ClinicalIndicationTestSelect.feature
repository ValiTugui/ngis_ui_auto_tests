@clinicalIndicationTestSelect
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-2093 @NTS-3161 @v_1 @P0
  Scenario: NTS-3161 - Clinical Indication Page - Loading wheel for Test Detail page/Clinical Indications tab
    When the user selects the Tests tab
    And the user types in the CI term  in the search field and selects the first result from the results list
      | 270 |
    Then the text "Please wait a moment - clinical indications are loading" is displayed
    And the text "This test cannot be ordered yet" is not displayed
    And the loading wheel is displayed
    And the list of clinical indications are loaded

  @E2EUI-1530 @NTS-**** @v_1 @P0
  Scenario: NTS-**** - Clinical Indication Page - Loading wheel for Test Detail page/Clinical Indications tab
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user sees the "Eligibility Criteria" tab is selected by default
    And the user sees the button "Yes, start Referral" on Bottom right
    Then the user clicks the Start referral button
    And the user should able to select online or offline order