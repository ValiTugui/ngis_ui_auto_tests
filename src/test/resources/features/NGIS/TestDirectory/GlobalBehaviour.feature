#@regression
#@globalBehaviour
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: TestDirectory: Global Behaviour Page

  Background:
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |

  @NTS-3169 @TD_VERSION_INFO
#  @E2EUI-1755
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

  @NTS-3501 @Z-LOGOUT
#    @E2EUI-1761
  Scenario Outline:NTS-3501:Validating NGIS Id and Referral Id and web element text
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |

    Then the user should verify the referral banner present at the top
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the user should verify the referral banner present at the top
    Then the "<PatientDetails>" stage is marked as Completed
    And the user verifies the Ngis Id and Referral Id from the referral banner
    Then user copies text from NgisId and verifies it with actual content

    Examples:
      | PatientDetails  |
      | Patient details |