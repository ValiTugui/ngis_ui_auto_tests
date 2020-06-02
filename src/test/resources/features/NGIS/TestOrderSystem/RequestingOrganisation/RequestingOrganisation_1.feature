#@regression
@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Requesting Organisation page 1

  Background:
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed

  @NTS-3047 @Z-LOGOUT
#    @E2EUI-1413 @E2EUI-1360
  Scenario Outline: NTS-3047 - Find my ordering entity via name to order a test from the correct Lab/GLH
    #And the "Requesting organisation" stage is marked as Mandatory To Do
    When the user navigates to the "<stage>" stage
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | Maidstone            |

  @NTS-3069 @Z-LOGOUT
#    @E2EUI-916 @E2EUI-936 @E2EUI-1421 @E2EUI-1343 @E2EUI-980 @E2EUI-1290
  Scenario Outline: NTS-3069 - Feature: Find/Select Ordering Entity
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then there isn't any search results returned
    And  the Save and Continue button should be disabled
    And the message "<messageText>" displayed on the page
    Examples:
      | stage                   | ordering_entity_name | messageText                                           |
      | Requesting organisation | lllLondon            | Your search has returned 0 results. Please try again. |
      | Requesting organisation | xyxyx                | Your search has returned 0 results. Please try again. |
      | Requesting organisation | $@%','*%#$           | Your search has returned 0 results. Please try again. |
      | Requesting organisation | 11111                | Your search has returned 0 results. Please try again. |


  @NTS-3069 @Z-LOGOUT
#    @E2EUI-924 @E2EUI-1206
  Scenario Outline: NTS-3069 - Feature: page validation with a single character
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then  the Save and Continue button should be disabled
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | k                    |
     # entity name with two characters
      | Requesting organisation | lm                   |

  @NTS-3155 @Z-LOGOUT
#    @E2EUI-1361 @E2EUI-1542
  Scenario Outline: NTS-3155 - Requesting Organisation Page Layout
    When the user navigates to the "<stage>" stage
    And the user is navigated to a page with title <pageTitle>
    And the user sees the search label with "<expectedText>"
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    And  the Save and Continue button should be disabled
    Examples:
      | stage                   | pageTitle                     | expectedText                                                   | placeholderText                                                                         |
      | Requesting organisation | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |

  @NTS-3383 @Z-LOGOUT
#    @E2EUI-1415
  Scenario Outline: NTS-3383: Requesting Organisation landing page
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should be able to see an intro message "<introMessage>" on requesting organisation page
    Then the user should be able to see hint text in search box on requesting organisation page
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed

    Examples:
      | Requesting organisation | introMessage                                                   | ordering_entity_name |
      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | Maidstone            |