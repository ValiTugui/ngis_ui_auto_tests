@regression
@TO_RD
@FamilyMemberSearchPage
@ReadyForReveiw
Feature: Family Members Search Validation

  @NTS-3207 @E2EUI-950 @E2EUI-1116 @v_1 @P0
  Scenario Outline: NTS-3207: Find a family member page validation with NHS selected YES: All mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
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
      | message                  | color   |
      | Enter a day              | #dd2509 |
      | Enter a month            | #dd2509 |
      | Enter a year             | #dd2509 |
      | First name is required.  | #dd2509 |
      | Last name is required.   | #dd2509 |
      | Gender name is required. | #dd2509 |

  @NTS-3234 @E2EUI-1249 @v_1 @P0
  Scenario Outline:NTS-3234: Search a family member record with NHS selected - No, First name field validation with incorrect data
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                                                            | message                 | color   |
      | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=P12345 | First name is required. | #dd2509 |
    Examples:
      | stage          |
      | Family members |

  @NTS-3233 @E2EUI-1394 @v_1 @P0
  Scenario Outline:NTS-3233: Find a family member page validation with NHS selected as YES: Invalid NHS number
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails  | message                                           | color   |
      | NHSNumber=1234 | Please enter your full NHS Number (10 characters) | #dd2509 |
    ##NHSNumber with 4 digits length
    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @E2EUI-841 @v_1 @P0
  Scenario Outline: NTS-3328: Find the family member page validation with NHS selected as YES: Entered incorrect date of birth field
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | message                        | color   |
      | Enter a day between 1 and 31   | #dd2509 |
      | Enter a month between 1 and 12 | #dd2509 |
      | Enter a year beyond 1900       | #dd2509 |
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                       | message                  | color   |
      | NHSNumber=9449310351:DOB=11-11-1111 | Enter a year beyond 1900 | #dd2509 |
    Examples:
      | stage          | SearchDetails                       |
      | Family members | NHSNumber=9449310351:DOB=00-00-0000 |


  @NTS-3328 @E2EUI-1016 @v_1 @P0
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and providing a valid Last name and all other mandatory fields left blank
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Enter a day              | #dd2509 |
      | Enter a month            | #dd2509 |
      | Enter a year             | #dd2509 |
      | Last name is required.   | #dd2509 |
      | Gender name is required. | #dd2509 |

    Examples:
      | stage          | SearchDetails               |
      | Family members | LastName=StaMbukdelifschitZ |

  @NTS-3328 @E2EUI-1401 @v_1 @P0
  Scenario Outline: NTS-3328: Find the family member page validation with NHS selected YES : Provided valid DOB and NHS field left blank
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails  | message                 | color   |
      | DOB=23-03-2011 | NHS Number is required. | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @E2UI-1388 @E2EUI-1493 @v_1 @P0
  Scenario Outline: NTS-3328: Search for a family member record - Yes Option
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | SearchDetails                       | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @NTS-3328 @E2EUI-1493 @BVT_P0 @v_1
  Scenario Outline:NTS-3328: Search for a family member record - No Option
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | SearchDetails                                                 | ResultMessage          |
      | Family members | DOB=14-02-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male | 1 patient record found |

  @NTS-3328 @E2EUI-1011 @v_1 @P0
  Scenario Outline: NTS-3328: Find a family member page validation with NHS selected as YES: Special characters entered in NHS number
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails        | message                                           | color   |
      | NHSNumber=123456789@ | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=$#%#*&^@%  | NHS Number is required.                           | #dd2509 |

    Examples:
      | stage          |
      | Family members |