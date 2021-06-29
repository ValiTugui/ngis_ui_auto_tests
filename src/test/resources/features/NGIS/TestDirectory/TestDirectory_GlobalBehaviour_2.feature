#@regression
#@globalBehaviour
@01-TEST_DIRECTORY
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: Test Directory - Global Behaviour Page 2

#  @NTS-3194 @E2EUI-1502
#  @NTS-3195 @E2EUI-1506
#  @NTS-3193  @E2EUI-1756
#  @NTS-3200  @E2EUI-1470
#  @NTS-3237 @E2EUI-1541
#  @NTS-3169 @E2EUI-1755
#  @NTS-3236  @E2EUI-1658
  @NTS-3169  @NTS-3237   @NTS-3200  @NTS-3193 @NTS-3195 @NTS-3194
  @Z-LOGOUT
  Scenario: NTS-3169 - Home Page - User is able to see the version of NGIS at the bottom of the page
    Given the user gets the NGIS version
    And a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user clicks the Start Test Order Referral button
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    When the user clicks the PDF order form button
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    When the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the "Review test selection" page is properly opened and by default a test is selected
    And the user should be able to see text "Tests available to request online are listed. If other tests are required, they should be requested using standard genomic test request processes" under Review Test Selection heading
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the warning message is present on the Offline order page
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And The user should be able to see text "Consent" replaced with "Patient choice" Form
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link

