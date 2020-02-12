@regression
@TO_RD
@FamilyMemberSearchPage

Feature: Family Members Search Validation

  @NTS-3207 @E2EUI-950 @E2EUI-1116 @v_1 @P0
  Scenario Outline: NTS-3207-3328: Find a family member page validation with NHS selected YES: All mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    When the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                 | color   |
      | NHS Number is required. | #dd2509 |
      | Enter a day             | #dd2509 |
      | Enter a month           | #dd2509 |
      | Enter a year            | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3207 @E2EUI-1083 @v_1 @P0
  Scenario:NTS-3207: Search a family member record with NHS selected No and All mandatory fields left blank
    When the user clicks the NO button in family member search page
    And the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                 | color   |
      | Enter a day             | #dd2509 |
      | Enter a month           | #dd2509 |
      | Enter a year            | #dd2509 |
      | First name is required. | #dd2509 |
      | Last name is required.  | #dd2509 |
      | Gender is required.     | #dd2509 |
