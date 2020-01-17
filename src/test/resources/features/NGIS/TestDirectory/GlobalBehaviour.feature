@regression
@testDirectory
@globalBehaviour

Feature: Global Behaviour Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @E2EUI-1755 @NTS-3169 @TD_VERSION_INFO @v_1 @P0 @COMP1_TD
  Scenario: NTS-3169 - Home Page - User is able to see the version of NGIS at the bottom of the page
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user clicks the Start Test Order Referral button
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    When the user clicks the PDF order form button
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the "Review test selection" page is properly opened and by default a test is selected
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link

  @E2EUI-1658 @NTS-3236 @v_1 @P0 @COMP1_TD_ClinicalTests
  Scenario: NTS-3236 - Home Page - User is able to see the privacy policy link at the bottom of the page
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    When the user clicks the privacy policy link
    Then the "Your privacy and data" page should be opened in the next tab
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user clicks the Start Test Order Referral button
    Then the user can see the "Privacy Policy" link at bottom of the page
    When the user clicks the PDF order form button
    Then the user can see the "Privacy Policy" link at bottom of the page
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the "Review test selection" page is properly opened and by default a test is selected
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page