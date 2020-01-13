@regression
@testDirectory
@paperForm

Feature: Paper Form page functionalities

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-1502 @NTS-3194 @v_1 @P0 @COMP1_TD_TestType
  Scenario: NTS-3194 - Review Test Selection Page - Verify in Review Test Selection Page, by default a test is selected
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected

  @E2EUI-1506 @NTS-3195 @v_1 @P0 @COMP1_TD_OrderTests
  Scenario: NTS-3195 - Offline Order Page - Verify Offline Order Page is Displayed
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication

  @E2EUI-1756 @NTS-3193 @v_1 @P0 @COMP1_TD_OrderTests
  Scenario: NTS-3193 - Offline Order Page - Verify 'Consent' is replaced with 'Patient choice' on the Offline Order screen
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And The user should be able to see text "Consent" replaced with "Patient choice" Form

  @E2EUI-1470 @NTS-3200 @v_1 @P0 @COMP6_TD_OrderTests
  Scenario: NTS-3200 - Offline Order Page - Verify warning message for 2+ tumours on the Offline Order screen
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the warning message is present on the Offline order page
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section

  @E2EUI-1541 @NTS-3237 @v_1 @P0 @COMP1_TD_TestType
  Scenario: NTS-3237 - Review Test Selection Page - Verify in Review Test Selection Page, by default a test is selected
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user should be able to see text "Tests available to request online are listed. If other tests are required, they should be requested using standard genomic test request processes" under Review Test Selection heading

  @E2EUI-1022 @E2EUI-1257 @E2EUI-906 @NTS-3288 @v_1 @P0 @COMP1_TD_OrderTests
  Scenario Outline: NTS-3288 - Offline Order Page - Verify Offline Order Page is Displayed
    And the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<placeSearchTerm>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should be able to see two sections as follows and a "Print Page" button
      | Complete the forms with patient information | Send samples and completed forms to |
    And the Complete the forms with patient information section the following should be displayed
      | <sectionName1> | <sectionName2> | <sectionName3> |
    And the user should see the "Download" button next to each of the forms
    And the user should be see lab details for "<placeSearchTerm>" under the heading Send samples and completed forms without any warning message

    Examples:
      | searchTerm | placeSearchTerm | sectionName1 | sectionName2              | sectionName3   |
      | R100       | Manchester      | Referral     | Additional family members | Patient choice |
      | M89        | Leeds           | Referral     | Patient choice            | null           |
