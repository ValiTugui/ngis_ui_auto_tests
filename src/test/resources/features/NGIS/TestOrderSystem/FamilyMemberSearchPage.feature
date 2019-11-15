@regression1
@FamilyMembers
Feature: FamilyMember search page

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_01 @NTS-3207 @E2EUI-1116 @v_1 @P0
  Scenario Outline: NTS-3207: Verify the family member search page with Yes option displayed properly
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    Examples:
      | stage          |
      | Family members |

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_02 @NTS-3207 @E2EUI-1116 @v_1 @P0
  Scenario: NTS-3207:Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with Yes option selected

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_03 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario:NTS-3207: Verify the family member search page with No option displayed properly
    When the user clicks the NO button in family member search page
    Then the family member search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_04 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario: NTS-3207:Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with No option

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_05 @NTS-3234 @E2EUI-1249 @v_1 @P0
  Scenario Outline:NTS-3234: Verify the family member search without providing first name displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide first name in the search details
    Examples:
      | stage          | SearchDetails                                            | ErrorMessage            | MessageColor |
      | Family members | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female | First name is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_06 @NTS-3233 @E2EUI-1394 @v_1 @P0
  Scenario Outline:NTS-3233: Verify the family member search with invalid NHS Number displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails  | ErrorMessage                                      | MessageColor |
      | Family members | NHSNumber=1234 | Please enter your full NHS Number (10 characters) | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_07 @E2EUI-841 @v_1 @P0
  Scenario Outline: Verify the family member search with invalid DOB displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails                       | ErrorMessage                                                                         | MessageColor |
      | Family members | NHSNumber=9449310351:DOB=00-00-0000 | Enter a day between 1 and 31,Enter a month between 1 and 12,Enter a year beyond 1900 | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_08 @E2EUI-1016 @v_1 @P0
  Scenario Outline: Verify the family member search without providing first name displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails               | ErrorMessage                                                                       | MessageColor |
      | Family members | LastName=StaMbukdelifschitZ | Enter a day,Enter a month,Enter a year,First name is required.,Gender is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_09 @E2EUI-1401 @v_1 @P0
  Scenario Outline: Verify the family member search with invalid NHS Number displays correct error message
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails  | ErrorMessage            | MessageColor |
      | Family members | DOB=23-03-2011 | NHS Number is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_10 @E2EUI-1493 @v_1 @P0
  Scenario Outline: Verify the family member search with valid NHS Number and DOB displays result message
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    And the user search the family member with the specified details "<YesSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | YesSearchDetails                    | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_11 @E2EUI-1493 @v_1 @P0
  Scenario Outline:NTS-Todo: Verify the family member search with valid DOB, First name, Last name and gender displays result message
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<NoSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found

    Examples:
      | stage          | NoSearchDetails                                               | ResultMessage          |
      | Family members | DOB=14-02-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male | 1 patient record found |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_12 @E2EUI-1011 @v_1 @P0
  Scenario Outline: Verify the family member search with special characters in NHS Number field displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    Examples:
      | stage          | SearchDetails       | ErrorMessage                                      | MessageColor |
      | Family members | NHSNumber=456@%     | Please enter your full NHS Number (10 characters) | #dd2509      |
      | Family members | NHSNumber=$#%#*&^@% | NHS Number is required.                           | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_13 @E2EUI-1301 @v_1 @P0
  Scenario Outline: Verify the family member search with invalid DOB displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
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

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_14 @E2EUI-1205 @v_1 @P0
  Scenario Outline: Verify the family member search results Page validation with valid NHS Number and DOB
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    And the user search the family member with the specified details "<YesSearchDetails>"
    And the message will be displayed as "<ResultMessage>" result found
    Then the search results have been displayed with Patient Name, dob, gender, NHS number and address

    Examples:
      | stage          | YesSearchDetails                    | ResultMessage          |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | 1 patient record found |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_15 @E2EUI-851 @v_1 @P0
  Scenario Outline: Verify the family member search landing page with displayed properly
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the display title of the family member search page is "Find a family member"
    And the family member search page display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
    And the display question for NHS Number of the family member search page is "Do you have the family member’s NHS Number?"
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields

    Examples:
      | stage          |
      | Family members |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_16 @ @E2EUI-1254 @v_1 @P0
  Scenario Outline: Verify the family member search without providing last name displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide last name in the search details
    Examples:
      | stage          | SearchDetails                                | ErrorMessage           | MessageColor |
      | Family members | DOB=23-03-2011:FirstName=Smith:Gender=Female | Last name is required. | #dd2509      |

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_17 @E2EUI-965 @v_1 @P0
  Scenario Outline: Verify the family member search with valid DOB displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<MandatoryFieldsMessage>" in "<MandatoryFieldsColor>" for the invalid field

    Examples:
      | stage          | SearchDetails            | MandatoryFieldsMessage                                                                                                                 | MandatoryFieldsColor |
      | Family members | NHSNumber=1234:DOB=0-0-0 | Please enter your full NHS Number (10 characters),Enter a day between 1 and 31,Enter a month between 1 and 12,Enter a year beyond 1900 | #dd2509              |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_21 @E2EUI-983 @v_1 @P0
  Scenario Outline: Verify the family member search with invalid nhs no and blank DOB displays correct error message
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    And the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the user search the family member with the specified details "<SearchDetails>"
    And the user clicks the Search button in family member search page
    Then the message will be displayed as "<MandatoryFieldsMessage>" in "<MandatoryFieldsColor>" for the invalid field

    Examples:
      | stage          | SearchDetails   | MandatoryFieldsMessage                                                                   | MandatoryFieldsColor |
      | Family members | NHSNumber=12345 | Please enter your full NHS Number (10 characters),Enter a day,Enter a month,Enter a year | #dd2509              |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_22 @E2EUI-829 @v_1 @P0
  Scenario Outline: Verify the family member search without providing Dob, first name and last name  displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | SearchDetails | ErrorMessage                                                                           | MessageColor |
      | Family members | Gender=Female | Enter a day,Enter a month,Enter a year,First name is required.,Last name is required. | #dd2509      |
