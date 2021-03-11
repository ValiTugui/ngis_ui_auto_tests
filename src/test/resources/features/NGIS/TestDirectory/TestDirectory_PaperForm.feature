#@regression
#@paperForm
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: TestDirectory: Paper Form


  @NTS-3288
    #@E2EUI-1022 @E2EUI-1257 @E2EUI-906
  Scenario Outline: NTS-3288 - Offline Order Page - Verify Offline Order Page is Displayed
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    And the user clicks the Start Test Order Referral button
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

  @NTS-3491
  #@E2EUI-2092
  Scenario:NTS-3491: Move the Online or Offline choice page
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    And the user should able to select online or offline order

  @NTS-3491
  #@E2EUI-2092  @E2EUI-1970 @E2EUI-2095
  Scenario Outline:NTS-3491: Move the Online or Offline choice page
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user checks for different screen width of "<ScreenWidth>"
    And the user checks the presence of  horizontal scrollbar "<ScrollBarPresent>"
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    And the user should able to select online or offline order
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    Then the user should be able to see NHS logo image

    Examples:
      | ScreenWidth | ScrollBarPresent |
      | 400         | Present          |
      | 1296        | Not Present      |
      | 1400        | Not Present      |