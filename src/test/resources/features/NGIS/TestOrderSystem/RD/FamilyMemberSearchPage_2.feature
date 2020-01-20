@regression
@TO_RD
@FamilyMemberSearchPage
@ReadyForReveiw
Feature: Family Members Search Validation

  @NTS-3302 @E2EUI-965 @v_1 @P0
  Scenario Outline: NTS-3302: Find a Family Member page layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the mandatory fields shown with the symbol in red color
      | mandatory_field | field_type | symbol | symbol color |
      | NHS Number      | label      | ✱      | #dd2509      |
      | Date of birth   | legend     | ✱      | #dd2509      |
    And the NHS number entry fields should be of length 10
    And the DOB entry fields should have the format dd-mm-yyyy displayed
    And the Search button should be displayed with search symbol and click-able

    Examples:
      | stage          |
      | Family members |

  @NTS-3304 @E2EUI-1301 @v_1 @P0
  Scenario Outline: NTS-3304: Search a family member record with NHS selected No, Date of birth Field validation with incorrect date
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                                                                 | error_message                       | color   |
      | DOB=32-03-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234 | Enter a day between 1 and 31        | #dd2509 |
      | DOB=0-04-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234  | Enter a day between 1 and 31        | #dd2509 |
      | DOB=10-28-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234 | Enter a month between 1 and 12      | #dd2509 |
      | DOB=10-0-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234  | Enter a month between 1 and 12      | #dd2509 |
      | DOB=14-11-1:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234    | Enter a year in 4 figures e.g. 1983 | #dd2509 |
      | DOB=14-11-1800:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234 | Enter a year beyond 1900            | #dd2509 |
      | DOB=29-02-2001:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=PC1234 | Check the day and month are valid   | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @E2EUI-1205 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Find a family member Search Results Page validation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    And the search results have been displayed with Patient Name, dob, gender, NHS number and address

    Examples:
      | stage          | SearchDetails                       | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @NTS-3328 @E2EUI-851 @v_1 @P0
  Scenario Outline: NTS-3328: verify the text information present on the 'Find a family member ' page
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the family member search page display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
    And the family member search page display description title contains the phrase "Full name, gender and postcode are required if NHS Number is not available."
    And the display question for NHS Number of the family member search page is "Do you have the family member’s NHS Number?"
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields

    Examples:
      | stage          |
      | Family members |

  @NTS-3328  @E2EUI-1254 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Search a family member record with NHS selected No, Last name field validation with incorrect data
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                                | message                | color   |
      | DOB=23-03-2011:FirstName=Smith:Gender=Female | Last name is required. | #dd2509 |
    ##Do not provide last name in the search details
    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @E2EUI-983 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Find a Family member page field validation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                       | message                                           | color   |
      | NHSNumber=12345                     | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=9449305307:DOB=ab-10-1975 | Enter a day                                       | #dd2509 |
      | NHSNumber=9449305307:DOB=10-bc-1975 | Enter a month                                     | #dd2509 |
      | NHSNumber=9449305307:DOB=12-10-abcd | Enter a year                                      | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @E2EUI-829 @v_1 @P0
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and provided a valid gender and all other mandatory fields left blank
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails | ErrorMessage                                                                          | MessageColor |
      | Family members | Gender=Female | Enter a day,Enter a month,Enter a year,First name is required.,Last name is required. | #dd2509      |

  @NTS-3328 @E2EUI-830 @v_1 @P0
  Scenario Outline: NTS-3328: Search a family member record with NHS selected No and providing a valid First name and leaving all other mandatory fields blank
    And the user navigates to the "<stage>" stage
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Enter a day            | #dd2509 |
      | Enter a month          | #dd2509 |
      | Enter a year           | #dd2509 |
      | Last name is required. | #dd2509 |
      | Gender is required.    | #dd2509 |

    Examples:
      | stage          | SearchDetails     |
      | Family members | FirstName=StaMbuk |

  @NTS-3328 @E2EUI-1260 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and provided a valid Postcode and all other mandatory fields left blank
    And the user navigates to the "<stage>" stage
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Enter a day             | #dd2509 |
      | Enter a month           | #dd2509 |
      | Enter a year            | #dd2509 |
      | First name is required. | #dd2509 |
      | Last name is required.  | #dd2509 |
      | Gender is required.     | #dd2509 |

    Examples:
      | stage          | SearchDetails    |
      | Family members | Postcode=PC12345 |
