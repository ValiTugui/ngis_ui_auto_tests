@UAT_UI_SmokeTest_Pack

Feature: Navigation of Test Selection, Test Order and Panel App with Basic Checks from Dashboard

  @NTS-6426 @inprogress
  Scenario: Navigation of Test Selection, Test Order and Panel App with Basic Checks from Dashboard
    ## GET NGIS Version from NGIS Status page
    Given the user gets the NGIS version
    #Validate Banner elements on Test Selection
    And a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    Then the user should see the banner elements based on the current environment
    ##Validate NGIS Version
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    ##Dashboard Page
    Given a web browser is at the dashboard page
    ##Validate Banner elements on Dashboard
    Then the user should see the banner elements based on the current environment
    And User should be able to see my Dashboard
    And The user should see the Page title as "Welcome to the National Genomic Informatics System"
    When the user should be able to see clickable tabs
    And the user clicks on "Find a genomic test" Tab
    Then the user should be directed to Test selection url
    ##Search CI
    When the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the CI Search Start Referral button
    And the user clicks the PDF order form button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    When the user enters the keyword "Derbyshire Healthcare" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    When the user clicks the Continue button again
    ##Offline forms
    Then the "Offline order" page is properly displayed for chosen clinical indication
    When the user clicks the link Cancel Order
    ##Authentication
    And the user clicks the Start Test Order Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    Then the user should be able to see NHS logo image
    #Validate Banner elements on Test Order
    Then the user should see the banner elements based on the current environment
  ##MI portal
    When the user clicks the Log out button
    And User should be able to see my Dashboard
    And the user clicks on "Manage samples" Tab
    And the user logs in to the "MI Portal" system
    #And the user click on "File Submissions" section select the filters "Valid" and click on Add and Search buttons and verify the table loaded
#    And the user click on "Order Tracking" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    And the user click on "GLH Samples" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    And the user click on "Plater Samples" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    And the user click on "Picklists" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    And the user click on "Sequencer Samples" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    And the user click on "New Referrals" section select the filters "Yorkshire & North East" and click on Add and Search buttons and verify the table loaded
#    When the user should be able to see data quality menu is displayed
#    Then the user click on Clinical Data Quality section select the filters Yorkshire & North East and click on Add Filters button and verify the table loaded
    When the user clicks the MIPortal Log out button
    When a web browser is at the dashboard page
    And User should be able to see my Dashboard
    ##Test Order from Dashboard
    When the user clicks on "Order a genomic test" Tab
    And the user logs in to the "Test Order" system
    And the user is navigated to a page with title Find your patient
    When the user clicks the Log out button
    And User should be able to see my Dashboard
    ##PanelApp from Dashboard
    And the user clicks on "Open PanelApp" Tab
    And the user is navigated to a page with title Genomics England PanelApp
    And User Navigates back to Dashboard
    Then User should be able to see my Dashboard
    ##Interpretation Portal from Dashboard
#    Then the user clicks on "Enter the Interpretation Portal" Tab
#    And the user logs in to the Interpretation Portal system
#
#    And the user is navigated to a page with title GMS: Interpretation Portal
#    When the user clicks the Log out buttons