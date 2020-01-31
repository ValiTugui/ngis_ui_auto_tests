@regression
@TO_RD
@FamilyMemberSearchPage

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

  @NTS-3304 @E2EUI-1301 @LOGOUT @v_1 @P0
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

  @NTS-3234 @E2EUI-1249 @v_1 @P0
  Scenario Outline:NTS-3234: Search a family member record with NHS selected - No, First name field validation with incorrect data
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                                                            | message                 | color   |
      | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=P12345 | First name is required. | #dd2509 |
    Examples:
      | stage          |
      | Family members |

  @NTS-3233 @E2EUI-1394 @LOGOUT @v_1 @P0
  Scenario Outline:NTS-3233: Find a family member page validation with NHS selected as YES: Invalid NHS number
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails  | message                                           | color   |
      | NHSNumber=1234 | Please enter your full NHS Number (10 characters) | #dd2509 |
    ##NHSNumber with 4 digits length
    Examples:
      | stage          |
      | Family members |


  @NTS-2801 @E2EUI-915 @E2EUI-1399 @LOGOUT @v_1
  Scenario Outline: NTS-2801-DOB field Validations - invalid day , month , year values
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | RD | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field

    Examples: of alphaNumeric
      | stage         | patient-search-type | NhsNumber  | DOB        | error_message |
      | Family members | NGIS                | 9449305099 | ab-02-2011 | Enter a day   |
      | Family members | NGIS                | 9449305099 | 22-!!-2011 | Enter a month |
      | Family members | NGIS                | 9449305099 | 01-02-abcd | Enter a year  |

    Examples: of invalid, day, month and year
      | stage          | patient-search-type | NhsNumber  | DOB        | error_message                  |
      | Family members | NGIS                | 9449305099 | 32-03-2011 | Enter a day between 1 and 31   |
      | Family members | NGIS                | 9449305099 | 10-28-2011 | Enter a month between 1 and 12 |
      | Family members | NGIS                | 9449305099 | 14-11-1800 | Enter a year beyond 1900       |


  @NTS-4501 @E2EUI-1130 @v_1 @LOGOUT
  Scenario Outline: Family members - NHSNumber field - maximum length validation
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | RD | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
    Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field

    Examples:
      | stage          | NHSNumber        |
      | Family members | 9449310602111111 |

