@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: Family Members Search Page - Field Validation_1

  @NTS-3302 @NTS-3207 @NTS-3343 @NTS-3328 @Z-LOGOUT
#    @E2EUI-1205 @E2EUI-1388 @E2EUI-1493
#    @E2EUI-950 @E2EUI-1116 @E2EUI-841
#    @E2EUI-965 @E2EUI-1395
  Scenario Outline: NTS-3302: Find a Family Member page layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    And the mandatory fields shown with the symbol in red color
      | mandatory_field | field_type | symbol | symbol color |
      | NHS Number      | label      | ✱      | #dd2509      |
      | Date of birth   | legend     | ✱      | #dd2509      |
    And the NHS number entry fields should be of length 10
    And the DOB entry fields should have the format dd-mm-yyyy displayed
    And the Search button should be displayed with search symbol and click-able
    ##Below step for E2EUI-1395
    And the user verifies the svg icon for tick mark
    #Below step for NTS-3207
    When the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                    | color   |
      | NHS Number is required.    | #dd2509 |
      | Date of birth is required. | #dd2509 |
    #@NTS-3328 @E2EUI-841
    And the user search the family member with the specified details "<invalidSearchDetails>"
    Then the user will see error messages highlighted in red colour
      | message                        | color   |
      | Enter a day between 1 and 31   | #dd2509 |
      | Enter a month between 1 and 12 | #dd2509 |
      | Enter a year after 1900        | #dd2509 |
    #@NTS-3343
    And the user search the family member with the specified details "<validSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    And the search results have been displayed with Patient Name, dob, gender, NHS number and address

    Examples:
      | FamilyMember   | invalidSearchDetails                | validSearchDetails                  | ResultMessage          |
      | Family members | NHSNumber=2000000827:DOB=00-00-0000 | NHSNumber=2000000827:DOB=28-08-2011 | 1 patient record found |

  @NTS-3328 @NTS-3233 @NTS-28011 @NTS-4722 @Z-LOGOUT
#    @E2EUI-983 @E2EUI-1399 @E2EUI-1406 @E2EUI-1885 @E2EUI-1614
#    @E2EUI-1401 @E2EUI-1394 @E2EUI-851 @E2EUI-1011 @E2EUI-835
  Scenario Outline: NTS-3328: Find a Family member page field validation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the YES button is selected by default on family member search
    And the family member search page display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                       | message                                           | color   |
      | DOB=23-03-2011                      | NHS Number is required.                           | #dd2509 |
      | NHSNumber=12345                     | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=1234                      | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=123456789@                | Please enter your full NHS Number (10 characters) | #dd2509 |
      | NHSNumber=abcdefghh                 | NHS Number is required.                           | #dd2509 |

      | NHSNumber=2000000827:DOB=ab-10-1975 | Enter a day                                       | #dd2509 |
      | NHSNumber=2000000827:DOB=10-bc-1975 | Enter a month                                     | #dd2509 |
      | NHSNumber=2000000827:DOB=12-10-abcd | Enter a year                                      | #dd2509 |

      | NHSNumber=2000000827:DOB=22-!!-2011 | Enter a month                                     | #dd2509 |
      | NHSNumber=2000000827:DOB=32-03-2011 | Enter a day between 1 and 31                      | #dd2509 |
      | NHSNumber=2000000827:DOB=10-28-2011 | Enter a month between 1 and 12                    | #dd2509 |
      | NHSNumber=2000000827:DOB=14-11-1800 | Enter a year after 1900                           | #dd2509 |
      | NHSNumber=2000000827:DOB=11-11-1111 | Enter a year after 1900                           | #dd2509 |

    Examples:
      | FamilyMember   |
      | Family members |