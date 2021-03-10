@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: TestOrder - Family Members Search Page 2 - Field Validation_2

  @NTS-3328
#    @E2EUI-841
  Scenario Outline: NTS-3328: Find the family member page validation with NHS selected as YES: Entered incorrect date of birth field
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | message                        | color   |
      | Enter a day between 1 and 31   | #dd2509 |
      | Enter a month between 1 and 12 | #dd2509 |
      | Enter a year after 1900        | #dd2509 |
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                       | message                 | color   |
      | NHSNumber=2000000827:DOB=11-11-1111 | Enter a year after 1900 | #dd2509 |
    Examples:
      | stage          | SearchDetails                       |
      | Family members | NHSNumber=2000000827:DOB=00-00-0000 |

  @NTS-3328
#    @E2EUI-1016
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and providing a valid Last name and all other mandatory fields left blank
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Gender is required.        | #dd2509 |

    Examples:
      | stage          | SearchDetails               |
      | Family members | LastName=StaMbukdelifschitZ |

  @NTS-3328
#    @E2EUI-1401
  Scenario Outline: NTS-3328: Find the family member page validation with NHS selected YES : Provided valid DOB and NHS field left blank
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails  | message                 | color   |
      | DOB=23-03-2011 | NHS Number is required. | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3328
#    @E2EUI-1388 @E2EUI-1493
  Scenario Outline: NTS-3328: Search for a family member record - Yes Option
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | SearchDetails                       | ResultMessage          |
      | Family members | NHSNumber=2000000827:DOB=28-08-2011 | 1 patient record found |

  @NTS-3328
#    @E2EUI-1493
  Scenario Outline:NTS-3328: Search for a family member record - No Option
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | SearchDetails                                                                   | ResultMessage          |
      | Family members | DOB=02-09-2011:FirstName=CHELSEA:LastName=CRAM:Gender=Male:Postcode=ST20 6LH    | 1 patient record found |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-1011
  Scenario Outline: NTS-3328: Find a family member page validation with NHS selected as YES: Special characters entered in NHS number
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails        | message                                           | color   |
      | NHSNumber=123456789@ | Please enter your full NHS Number (10 characters) | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3343 @Z-LOGOUT
#    @E2EUI-1205
  Scenario Outline: NTS-3328: Find a family member Search Results Page validation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    And the search results have been displayed with Patient Name, dob, gender, NHS number and address

    Examples:
      | stage          | SearchDetails                       | ResultMessage          |
      | Family members | NHSNumber=2000000827:DOB=28-08-2011 | 1 patient record found |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-851
  Scenario Outline: NTS-3328: verify the text information present on the 'Find a family member ' page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the family member search page display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields

    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-1254
  Scenario Outline: NTS-3328: Search a family member record with NHS selected No, Last name field validation with incorrect data
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
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

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-983
  Scenario Outline: NTS-3328: Find a Family member page field validation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                       | message                                           | color   |
      | NHSNumber=12345                     | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=2000000827:DOB=ab-10-1975 | Enter a day                                       | #dd2509 |
      | NHSNumber=2000000827:DOB=10-bc-1975 | Enter a month                                     | #dd2509 |
      | NHSNumber=2000000827:DOB=12-10-abcd | Enter a year                                      | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-829
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and provided a valid gender and all other mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails | ErrorMessage                                                              | MessageColor |
      | Family members | Gender=Female | Date of birth is required.,First name is required.,Last name is required. | #dd2509      |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-830
  Scenario Outline: NTS-3328: Search a family member record with NHS selected No and providing a valid First name and leaving all other mandatory fields blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | Last name is required.     | #dd2509 |
      | Gender is required.        | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name | color   |
      | Last name  | #dd2509 |
      | Gender     | #dd2509 |

    Examples:
      | stage          | SearchDetails     |
      | Family members | FirstName=StaMbuk |

  @NTS-3328 @Z-LOGOUT
#    @E2EUI-1260 @E2EUI-1259
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and provided a valid Postcode and all other mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Last name is required.     | #dd2509 |
      | Gender is required.        | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name | color   |
      | First name | #dd2509 |
      | Last name  | #dd2509 |
      | Gender     | #dd2509 |

    Examples:
      | stage          | SearchDetails    |
      | Family members | Postcode=PC12345 |

  @NTS-3207
#    @E2EUI-950 @E2EUI-1116
  Scenario Outline: NTS-3207-3328: Find a family member page validation with NHS selected YES: All mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    When the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                    | color   |
      | NHS Number is required.    | #dd2509 |
      | Date of birth is required. | #dd2509 |

    Examples:
      | stage          |
      | Family members |

  @NTS-3207 @Z-LOGOUT
#  @E2EUI-1083
  Scenario:NTS-3207: Search a family member record with NHS selected No and All mandatory fields left blank
    When the user clicks the NO button in family member search page
    And the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                    | color   |
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Last name is required.     | #dd2509 |
      | Gender is required.        | #dd2509 |