@regression
@testDirectory
@requestingOrganisationTestDirectory

Feature: Requesting Organisation page

  Background:
    #Test Directory
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
      | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |

  @E2EUI-916 @E2EUI-1456 @E2EUI-978 @E2EUI-951 @NTS-3315 @v_1 @P0 @COMP1_TD_TestType @COMP1_TD_clinicaltests @COMP1_TD_OrderTests
  Scenario Outline: NTS-3315 - Find/Select Ordering Entity - With Valid Search Term
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the search results are displayed
    And  the Continue button should be clickable

    Examples:
      | ordering_entity_name |
      | Maidstone            |
      | Man                  |

  @E2EUI-916 @E2EUI-1019 @E2EUI-1442 @NTS-3315 @v_1 @P1 @COMP1_TD_TestType @COMP1_TD_OrderTests
  Scenario Outline: NTS-3315 - Find/Select Ordering Entity - With Invalid Search Term
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then there isn't any search results returned
    And  the user sees the tool tip with text as "<headerText>"
    When the user clicks on the tool tip
    Then a slide out panel is displayed with following header as "<headerText>",  body details as "<bodyText>" and a X for the user to select to close the panel
    And the user is able to close the panel

    Examples:
      | ordering_entity_name | headerText                                 | bodyText                                                                                                                                              |
      | lllLondon            | I can't find my requesting organisation... | NGIS does not yet support non-NHS (e.g. private, international) patients. If your patient is non-NHS, please follow your existing ordering processes. |

  @E2EUI-978 @NTS-3315 @v_1 @P0 @COMP1_TD_OrderTests
  Scenario: NTS-3315 - Find/Select Ordering Entity - With Valid Search Term
    And the user should be able to see a click able link "Cancel order" at top right side of the page
    When the user clicks the link Cancel Order
    Then the browser navigates to the previously selected Clinical Indication Details page while still saving the user's most recent search for further page navigation