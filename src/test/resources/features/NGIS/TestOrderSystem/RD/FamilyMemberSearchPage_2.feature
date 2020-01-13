@regression
@TO_RD
@FamilyMemberSearchPage

Feature: Family Members Search Validation

  @NTS-3304 @E2EUI-1301 @v_1 @P0
  Scenario Outline: NTS-3304: Verify the family member search with invalid DOB displays correct error message
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetailsDOB>"
    Then the message will be displayed as "<error_message>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetailsDOB | error_message                       | MessageColor |
      | Family members | DOB=32-03-2011   | Enter a day between 1 and 31        | #dd2509      |
      | Family members | DOB=0-04-2011    | Enter a day between 1 and 31        | #dd2509      |
      | Family members | DOB=10-28-2011   | Enter a month between 1 and 12      | #dd2509      |
      | Family members | DOB=10-0-2011    | Enter a month between 1 and 12      | #dd2509      |
      | Family members | DOB=14-11-1      | Enter a year in 4 figures e.g. 1983 | #dd2509      |
      | Family members | DOB=14-11-1800   | Enter a year beyond 1900            | #dd2509      |
      | Family members | DOB=29-02-2001   | Check the day and month are valid   | #dd2509      |

  @NTS-3328 @E2EUI-1205 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Verify the family member search results Page validation with valid NHS Number and DOB
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<YesSearchDetails>"
    And the message will be displayed as "<ResultMessage>" result found
    Then the search results have been displayed with Patient Name, dob, gender, NHS number and address

    Examples:
      | stage          | YesSearchDetails                    | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @NTS-3328 @E2EUI-851 @v_1 @P0
  Scenario Outline: NTS-3328: Verify the family member search landing page with displayed properly
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the display title of the family member search page is "Find a family member"
    And the family member search page display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
    And the display question for NHS Number of the family member search page is "Do you have the family member’s NHS Number?"
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields

    Examples:
      | stage          |
      | Family members |

  @NTS-3328  @E2EUI-1254 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Verify the family member search without providing last name displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide last name in the search details
    Examples:
      | stage          | SearchDetails                                | ErrorMessage           | MessageColor |
      | Family members | DOB=23-03-2011:FirstName=Smith:Gender=Female | Last name is required. | #dd2509      |

  @NTS-3302 @E2EUI-965 @v_1 @P0
  Scenario Outline: NTS-3302: Verify the family member search with valid DOB displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<MandatoryFieldsMessage>" in "<MandatoryFieldsColor>" for the invalid field

    Examples:
      | stage          | SearchDetails            | MandatoryFieldsMessage                                                                                                                 | MandatoryFieldsColor |
      | Family members | NHSNumber=1234:DOB=0-0-0 | Please enter your full NHS Number (10 characters),Enter a day between 1 and 31,Enter a month between 1 and 12,Enter a year beyond 1900 | #dd2509              |

  @NTS-3328 @E2EUI-983 @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Verify the family member search with invalid nhs no and blank DOB displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the user search the family member with the specified details "<SearchDetails>"
    And the user clicks the Search button in family member search page
    Then the message will be displayed as "<MandatoryFieldsMessage>" in "<MandatoryFieldsColor>" for the invalid field

    Examples:
      | stage          | SearchDetails   | MandatoryFieldsMessage                                                                   | MandatoryFieldsColor |
      | Family members | NHSNumber=12345 | Please enter your full NHS Number (10 characters),Enter a day,Enter a month,Enter a year | #dd2509              |

  @NTS-3328 @E2EUI-829 @v_1 @P0
  Scenario Outline: NTS-3328: Verify the family member search without providing Dob, first name and last name  displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails | ErrorMessage                                                                          | MessageColor |
      | Family members | Gender=Female | Enter a day,Enter a month,Enter a year,First name is required.,Last name is required. | #dd2509      |

  @NTS-3328 @E2EUI-830 @v_1 @P0
  Scenario Outline: NTS-3328: Verify the family member search without providing Dob, last name and gender  displays correct error message
    And the user navigates to the "<stage>" stage
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails    | ErrorMessage                                                                      | MessageColor |
      | Family members | FirstName=MADHAV | Enter a day,Enter a month,Enter a year,Last name is required.,Gender is required. | #dd2509      |

  @NTS-3328 @E2EUI-1260 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3328: Verify the family member search with NHS selected No and provided a valid Postcode and all other mandatory fields left blank
    And the user navigates to the "<stage>" stage
    When the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails    | ErrorMessage                                                                      | MessageColor |
      | Family members | FirstName=MADHAV | Enter a day,Enter a month,Enter a year,Last name is required.,Gender is required. | #dd2509      |
