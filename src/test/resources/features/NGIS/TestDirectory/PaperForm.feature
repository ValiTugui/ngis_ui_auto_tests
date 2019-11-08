Feature: Paper Form page functionalities

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button

  @E2EUI-1756 @E2EUI-1506 @NTS-3195 @v_1 @P0 @COMP1_TD_OrderTests
  Scenario: Verify Offline Order Page is Displayed
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the Review test selection page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the Offline order page is properly displayed for chosen clinical indication
    And The user should be able to see text "Consent" replaced with "Patient choice" Form