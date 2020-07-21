@UAT_UI_SmokeTest_Pack

Feature: Start Test Selection - CI Details - Requesting Organisation - Test Package - PDF Forms - patient search page - log out - MI portal

  @UATSmokeTest_123
  Scenario Outline: Start Test selection and go to PDF Forms - Patient search page - logout - MI Portal
    Given a web browser is at the dashboard page
    And User should be able to see my Dashboard
    Then The user should see the Page title as "<Title>"
    And the user should be able to see clickable tabs
    When the user clicks on Find a genomic test
    Then the user should be directed to Test selection url
#Search CI
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the CI Search Start Referral button
    When the user clicks the PDF order form button
#Requesting Organisation
#    Then the user navigates to the "<requestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
#    And the details of the new organisation are displayed
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
#Offline forms
    Then the "Offline order" page is properly displayed for chosen clinical indication
    When the user clicks the link Cancel Order
#Authentication
    And the user clicks the Start Test Order Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    Then the user should be able to see NHS logo image

#MI portal
    When the user clicks the Log out button
    And User should be able to see my Dashboard
    And User clicks Manage Sample
#    When a web browser is at the mi-portal home page
#      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
#    Then user will view different sections on the MI portal


    Examples:
      | Title                                              |
      | Welcome to the National Genomic Informatics System |


