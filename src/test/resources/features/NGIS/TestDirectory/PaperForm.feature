Feature: Paper Form page functionalities

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    When the user clicks the PDF order form button

  @E2EUI-1502 @NTS-3194 @v_1 @P0 @COMP1_TD_TestType
  Scenario: Verify in Review Test Selection Page, by default a test is selected
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the Review test selection page is properly opened and by default a test is selected