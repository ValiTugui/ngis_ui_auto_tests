@regression
@requestingOrganisation
Feature: Requesting Organisation page

  Background:
    #Test Directory
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
     #Test Ordering
    And the user logs in to the Test Order system successfully
    And the user searches for a patient by providing valid details of NHS number and DOB fields in the patient search page
      | NGIS |
    And the user clicks the patient result card
    And the user clicks the Start Referral button
    And the referral page is displayed

  @E2EUI-1413 @E2EUI-1360 @NTS-3047 @LOGOUT @v_1 @P0 @COMP3_TO_OrderingEntity
  Scenario Outline: NTS-3047 - Find my ordering entity via name to order a test from the correct Lab/GLH
    #And the "Requesting organisation" stage is marked as Mandatory To Do
    When the user navigates to the "<stage>" stage
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the Save and Continue button should be clickable
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | Maidstone            |

  @E2EUI-916 @E2EUI-936 @E2EUI-1421 @NTS-3069 @LOGOUT @v_1 @P1 @COMP3_TO_OrderingEntity
  Scenario Outline: NTS-3069 - Feature: Find/Select Ordering Entity
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then there isn't any search results returned
    And  the Save and Continue button should be disabled
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | lllLondon            |


  @E2EUI-924 @NTS-3069 @LOGOUT @v_1 @P1 @COMP3_TO_OrderingEntity
  Scenario Outline: NTS-3069 - Feature: page validation with a single character
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then  the Save and Continue button should be disabled
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | k                    |

  @E2EUI-1361 @E2EUI-1542 @NTS-3155 @LOGOUT @v_1 @P0 @COMP3_TO_OrderingEntity
  Scenario Outline: NTS-3155 - Requesting Organisation Page Layout
    When the user navigates to the "<stage>" stage
    Then the requesting organisation page has the "<title>"
    And the requesting organisation has search label displayed
    And the user sees the search label with "<expectedText>"
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    And  the Save and Continue button should be disabled
    Examples:
      | stage                   | title                         | expectedText                                                   | placeholderText                                                                         |
      | Requesting organisation | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |







