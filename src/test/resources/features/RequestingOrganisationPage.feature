Feature: Requesting Organisation page


  Background:
    #Test Directory
    Given a web browser is at the Private Test Selection homepage
    And the user types in the CI term "Angiomatoid Fibrous Histiocytoma" in the search field and selects the first result from the results list
    And the user clicks the Start referral button
    And the user clicks the "Sign in to the online service" hyperlink
     #Test Ordering
    And the user logs in to the Test Order system successfully
    And the default patient search page is correctly displayed with the NHS number and Date of Birth fields
    And the user types in valid details of a "NGIS" patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the patient result card
    And the user clicks the Start Referral button
    And the referral page is displayed

  @E2EUI-1413 @NTS-3047 @LOGOUT
  Scenario: NTS-3047 - Find my ordering entity via name to order a test from the correct Lab/GLH
    #And the "Requesting organisation" stage is marked as Mandatory To Do
    When the user navigates to the "Requesting organisation" stage
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the Save and Continue button should be clickable



  @E2EUI-916 @NTS-3069 @LOGOUT
  Scenario: NTS-3069 - Feature: Find/Select Ordering Entity
    When the user navigates to the "Requesting organisation" stage
    And the user enters the invalid keyword "lllLondon" in the search field
    Then there isn't any search results returned
    And  the Save and Continue button should be disabled






