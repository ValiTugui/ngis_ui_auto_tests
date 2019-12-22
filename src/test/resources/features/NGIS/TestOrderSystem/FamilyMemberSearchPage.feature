@regression
@regression_set1
@FamilyMemberSearchPage

Feature: Family Members Search Validation

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_01 @NTS-3207 @E2EUI-1116 @v_1 @P0
  Scenario Outline: E2EUI-1116: Verify the family member search page with Yes option displayed properly
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    Examples:
      | stage          |
      | Family members |

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_02 @NTS-3207 @E2EUI-1116 @E2EUI-950 @v_1 @P0
  Scenario: E2EUI-1116-950: Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with Yes option selected

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_03 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario:E2EUI-1116-1083: Verify the family member search page with No option displayed properly
    When the user clicks the NO button in family member search page
    Then the family member search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_04 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario: E2EUI-1116-1083:Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with No option

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_05 @NTS-3234 @E2EUI-1249 @v_1 @P0
  Scenario Outline:E2EUI-1249: Verify the family member search without providing first name displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide first name in the search details
    Examples:
      | stage          | SearchDetails                                            | ErrorMessage            | MessageColor |
      | Family members | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female | First name is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_06 @NTS-3233 @E2EUI-1394 @v_1 @P0
  Scenario Outline:E2EUI-1394: Verify the family member search with invalid NHS Number displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails  | ErrorMessage                                      | MessageColor |
      | Family members | NHSNumber=1234 | Please enter your full NHS Number (10 characters) | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_07 @NTS-3328 @E2EUI-841 @v_1 @P0
  Scenario Outline: E2EUI-841: Verify the family member search with invalid DOB displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails                       | ErrorMessage                                                                         | MessageColor |
      | Family members | NHSNumber=9449310351:DOB=00-00-0000 | Enter a day between 1 and 31,Enter a month between 1 and 12,Enter a year beyond 1900 | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_08 @NTS-3328 @E2EUI-1016 @v_1 @P0
  Scenario Outline: E2EUI-1016: Verify the family member search without providing first name displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails               | ErrorMessage                                                                       | MessageColor |
      | Family members | LastName=StaMbukdelifschitZ | Enter a day,Enter a month,Enter a year,First name is required.,Gender is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_09 @NTS-3328 @E2EUI-1401 @v_1 @P0
  Scenario Outline: E2EUI-1401: Verify the family member search with invalid NHS Number displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails  | ErrorMessage            | MessageColor |
      | Family members | DOB=23-03-2011 | NHS Number is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_10 @NTS-3328 @E2UI-1388 @E2EUI-1493 @v_1 @P0
  Scenario Outline: E2EUI-1493-1388: Verify the family member search with valid NHS Number and DOB displays result message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<YesSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | YesSearchDetails                    | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_11 @NTS-3328 @E2EUI-1493 @BVT_P0 @v_1 @P0
  Scenario Outline:E2EUI-1493: Verify the family member search with valid DOB, First name, Last name and gender displays result message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<NoSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | NoSearchDetails                                               | ResultMessage          |
      | Family members | DOB=14-02-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male | 1 patient record found |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_12 @NTS-3328 @E2EUI-1011 @v_1 @P0
  Scenario Outline: E2EUI-1011: Verify the family member search with special characters in NHS Number field displays correct error message
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails       | ErrorMessage                                      | MessageColor |
      | Family members | NHSNumber=456@%     | Please enter your full NHS Number (10 characters) | #dd2509      |
      | Family members | NHSNumber=$#%#*&^@% | NHS Number is required.                           | #dd2509      |
