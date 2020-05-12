#@regression
#@globalBehaviour
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory - Global Behaviour Page 2

  @NTS-3169
#  @E2EUI-1755
  Scenario: NTS-3169 - Home Page - User is able to see the version of NGIS at the bottom of the page
    Given the user gets the NGIS version
    And a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
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

