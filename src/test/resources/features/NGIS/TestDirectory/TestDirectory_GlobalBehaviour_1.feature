#@regression
#@globalBehaviour
@TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory - Global Behaviour Page 1

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

   @NTS-3236
#   @E2EUI-1658
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

