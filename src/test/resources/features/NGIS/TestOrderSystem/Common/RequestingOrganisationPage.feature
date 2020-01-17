@regression
@TO_Common
@requestingOrganisation
Feature: Requesting Organisation page

  Background:
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_SUPER_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3047 @E2EUI-1413 @E2EUI-1360 @LOGOUT @v_1 @BVT_P0
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

  @NTS-3069 @E2EUI-916 @E2EUI-936 @E2EUI-1421 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3069 - Feature: Find/Select Ordering Entity
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then there isn't any search results returned
    And  the Save and Continue button should be disabled
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | lllLondon            |


  @NTS-3069 @E2EUI-924 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3069 - Feature: page validation with a single character
    When the user navigates to the "<stage>" stage
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then  the Save and Continue button should be disabled
    Examples:
      | stage                   | ordering_entity_name |
      | Requesting organisation | k                    |

  @NTS-3155 @E2EUI-1361 @E2EUI-1542 @LOGOUT @v_1 @BVT_P0
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

  @NTS-3383 @E2EUI-1415 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3383: Requesting Organisation landing page
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Check your patient
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