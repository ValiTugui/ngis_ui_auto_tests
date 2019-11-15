@regression
@clinicalIndicationTestSelect
Feature: Home Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-2093 @NTS-3161 @v_1 @P0 @COMP1_TD_ClinicalIndications
  Scenario: NTS-3161 - Clinical Indication Page - Loading wheel for Test Detail page/Clinical Indications tab
    When the user selects the Tests tab
    And the user types in the CI term  in the search field and selects the first result from the results list
      | 270 |
    Then the text "Please wait a moment - clinical indications are loading" is displayed
    And the text "This test cannot be ordered yet" is not displayed
    And the loading wheel is displayed
    And the list of clinical indications are loaded

  @E2EUI-1530 @E2EUI-1500 @NTS-3238 @NTS-3205 @v_1 @P0 @COMP1_TD_EligibilityCriteria @COMP1_TD_ClinicalTests
  Scenario: NTS-3205 - NTS-3238 - Clinical Indication Page - Test order to be a confirmation of Eligibility Criteria and Clinical Indications.
  As a user when I start the test order I want that action to be my confirmation that I agree to the patient eligibility criteria, so that I don't have to re-read it in a modal
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    Then the user sees the "Eligibility Criteria" tab is selected by default
    And the user sees the button "Yes, start Referral" on Bottom right
    And the user selects the "Test Package" tab
    And the user clicks on view more icon
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    Then the user clicks the Start referral button
    And the user selects the test in the test page and clicks on Continue button
    And the user should able to select online or offline order

  @E2EUI-1501 @NTS-3244 @v_1 @P0 @COMP1_TD_ClinicalTests
  Scenario: NTS-3244 - Clinical Indication Page -  View details for a Tumor Clinical Indication.
    When the user types in the CI term  in the search field and selects the first result from the results list
      | M85 |
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    And the user should be able to see all "4" tabs and are clickable
      | Clinical Indications | Test details | Labs | Order process |

  @E2EUI-1503 @E2EUI-1504 @E2EUI-1429 @E2EUI-1418 @NTS-3251 @v_1 @P0 @COMP1_TD_ClinicalTests @COMP1_TD_TestPackage @COMP1_TD_TestDetail
  Scenario: NTS-3251 - Clinical Indication Page - View details for a Rare Disease Clinical Indication.
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user selects the "Test Package" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    And the user should be able to see all "4" tabs and are clickable
      | Clinical Indications | Test details | Labs | Order process |
    And the user selects the "Test details" tab
    And the user should be able to see sections are displayed based on Clinical Indications type
      | Targeted regions | Required samples | Optimal family structure |
    And the user selects the "Labs" tab
    And the user should be able to see "Lab details" section is displayed
    And the user selects the "Order process" tab
    And the user should be able to see "5" sections of Order process are displayed
      | Find your patient’s Clinical Indication | Enter your patient’s information | Send your test order to a laboratory | Get your patient’s test results | Order more genomic tests |

  @E2EUI-1495 @E2EUI-986 @E2EUI-982 @NTS-3254 @v_1 @P0 @COMP1_TD_ClinicalTests @COMP1_TD_TestPackage @COMP1_TD_TestDetail
  Scenario: NTS-3254 - Clinical Indication Page - View details for Clinical Indication Test and Back to search.
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    Then the user should be able to see all "4" tabs and are clickable
      | Eligibility Criteria | Test Package | Further Info | Order process |
    And the user selects the "Test Package" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    And the user should be able to see all "4" tabs and are clickable
      | Clinical Indications | Test details | Labs | Order process |
    Then the user should be able to see a link "Back to search" at left side top of the page
    And the user clicks on Back to Search button
    And the browser navigates to the Private Test Selection Homepage with the user's last search and results automatically loaded in