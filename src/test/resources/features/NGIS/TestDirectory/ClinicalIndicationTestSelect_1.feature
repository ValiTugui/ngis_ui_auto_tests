#@regression
#@clinicalIndicationTestSelect
@TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory : ClinicalIndicationTestSelect_1

  @NTS-3161
# @E2EUI-2093
  Scenario: NTS-3161:E2EUI-2093: - Clinical Indication Page - Loading wheel for Test Detail page/Clinical Indications tab
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user selects the Tests tab
    And the user types in the CI term  in the search field and selects the first result from the results list
      | 270 |
    Then the text "Please wait a moment - clinical indications are loading" is displayed
    And the text "This test cannot be ordered yet" is not displayed
    And the loading wheel is displayed
    And the list of clinical indications are loaded

  @NTS-3238 @NTS-3205
# @E2EUI-1530 @E2EUI-1500
  Scenario: NTS-3205 - NTS-3238:E2EUI-1530,1500 - Clinical Indication Page - Test order to be a confirmation of Eligibility Criteria and Clinical Indications.
  As a user when I start the test order I want that action to be my confirmation that I agree to the patient eligibility criteria, so that I don't have to re-read it in a modal
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    Then the user sees the "Eligibility Criteria" tab is selected by default
    And the user sees the button "Yes, start Referral" on Bottom right
    And the user selects the "Test Package" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    Then the user clicks the Start Test Order Referral button
    And the user selects the test in the test page and clicks on Continue button
    And the user should able to select online or offline order


  @NTS-3244
#  @E2EUI-1501 @E2EUI-1033
  Scenario: NTS-3244:E2EUI-1501,1033: - Clinical Indication Page -  View details for a Tumor Clinical Indication.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | M85 |
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "Clinical Indications" tab is selected by default
    And the user should be able to see all "4" tabs and are clickable
      | Clinical Indications | Test details | Labs | Order process |


  @NTS-3251
#  @E2EUI-1503 @E2EUI-1504 @E2EUI-1429 @E2EUI-1418 @E2EUI-1069
  Scenario: NTS-3251:E2EUI-1503,1504,1429,1418,1069: - Clinical Indication Page - View details for a Rare Disease Clinical Indication.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
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


  @NTS-3254
#    @E2EUI-1495 @E2EUI-986 @E2EUI-982 @E2EUI-1242  @E2EUI-1849
  Scenario Outline: NTS-3254:E2EUI-1495,986,982,1242,1849: - Clinical Indication Page - View details for Clinical Indication Test and Back to search.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    Then the user should be able to see all "4" tabs and are clickable
      | Eligibility Criteria | Test Package | Further Info | Order process |
    And the user selects the "<tabName1>" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "<tabName2>" tab is selected by default
    And the user should be able to see all "4" tabs and are clickable
      | Clinical Indications | Test details | Labs | Order process |
    Then the user should be able to see a link "<linkName>" at left side top of the page
    And the user clicks on Back to Search button
    And the browser navigates to the Private Test Selection Homepage with the user's last search and results automatically loaded in

    Examples:
      | tabName1     | tabName2             | linkName       |
      | Test Package | Clinical Indications | Back to search |


  @NTS-3260
#      @E2EUI-1003 @E2EUI-1174
  Scenario Outline: NTS-3260:E2EUI-1003,1174: - Clinical Indication Page - Display additional details on who can order card.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    Then the user should be able to see all "4" tabs and are clickable
      | Eligibility Criteria | Test Package | Further Info | Order process |
    And the user sees the "<tabName1>" tab is selected by default
    And the user selects the "Eligibility Criteria" tab
    And the user should be able to see the following under Eligibility Criteria tab
      | <tab1> | <tab2> | <tab3> | <tab4> |
    And the user should be able to see "<whoCanOrderContent>" according to the CI selected

    Examples:
      | searchTerm | tabName1             | tab1        | tab2          | tab3                | tab4          | whoCanOrderContent                                                    |
      | R100       | Eligibility Criteria | Who to test | When to test  | Clinical speciality | Who can order | Clinical Genetics                                                     |
      | M89        | Test Package         | Who to test | Who can order | null                | null          | Consultant Haematologist with access to suitable material for testing |


  @NTS-3262
#      @E2EUI-1419 @E2EUI-1497  @E2EUI-1197
  Scenario Outline: NTS-3262:E2EUI-1419,1497,1197: - Clinical Indication Page - View details for Clinical Indications Tab.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    And the user selects the "<tabName1>" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "<tabName2>" tab is selected by default
    Then the user clicks on first Clinical indications results displayed
    And the user sees Clinical indications modal with two sections and "Go to Clinical Indication" is present
      | <sectionName1> | <sectionName2> |
    And the user should be able to see Clinical indications list is displayed containing clickable cards for each clinical indication

    Examples:
      | searchTerm | tabName1     | tabName2             | sectionName1 | sectionName2             |
      | R100       | Test Package | Clinical Indications | Who to test  | Test package includes... |
      | 270        | Test Package | Clinical Indications | Who to test  | Test package includes... |
      | M85        | Test Package | Clinical Indications | Who to test  | Test package includes... |


  @NTS-3265
#      @E2EUI-1063
  Scenario Outline: NTS-3265:E2EUI-1063:Clinical Indication Page - View details for Further Info Tab..
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    Then the user should be able to see all "4" tabs and are clickable
      | Eligibility Criteria | Test Package | Further Info | Order process |
    And the user selects the "Further Info" tab
    And the user should be able to see the following under Further Info tab
      | References | Age group relevancy | Pseudonyms | Associated terms |

    Examples:
      | searchTerm |
      | R100       |
      | R237       |


  @NTS-3268
#      @E2EUI-948
  Scenario Outline: NTS-3268:E2EUI-948:Clinical Indication Page - View details for Clinical Indications Tab.
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    And the user selects the "<tabName1>" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    And the user sees the "<tabName2>" tab is selected by default
    Then the user clicks on first Clinical indications results displayed
    And the user sees Clinical indications modal with two sections and "Go to Clinical Indication" is present
      | Who to test | Test package includes... |
    And the user click on Go to Clinical Indication button
    And the user should be able to see all "4" tabs and are clickable
      | Eligibility Criteria | Test Package | Further Info | Order process |

    Examples:
      | searchTerm | tabName1     | tabName2             |
      | R100       | Test Package | Clinical Indications |
      | M89        | Test Package | Clinical Indications |