@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: TestOrder - Family Members Search Page 2 - Field Validation_2

  @NTS-3304 @NTS-3234 @NTS-3328 @Z-LOGOUT
#   @E2EUI-1301 @E2EUI-1249 @E2EUI-1254
  Scenario Outline: NTS-3304: Search a family member record with NHS selected No, Date of birth Field validation with incorrect date
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
      | SearchDetails                                                                   | error_message                          | color   |
      | DOB=32-03-2011:FirstName=NICKY:LastName=MCCLEMENS:Gender=Male:Postcode=ST20 6LH | Enter a day between 1 and 31           | #dd2509 |
      | DOB=0-04-2011                                                                   | Enter a day between 1 and 31           | #dd2509 |
      | DOB=10-28-2011                                                                  | Enter a month between 1 and 12         | #dd2509 |
      | DOB=10-0-2011                                                                   | Enter a month between 1 and 12         | #dd2509 |
      | DOB=14-11-1                                                                     | Enter a year in 4 figures e.g. 1983    | #dd2509 |
      | DOB=14-11-1800                                                                  | Enter a year after 1900                | #dd2509 |
      | DOB=29-02-2001                                                                  | Check the day and month are valid      | #dd2509 |
      | DOB=28-02-2001:Postcode=ST20926LH                                               | This postcode is not in a valid format | #dd2509 |
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
    ##@NTS-3234
      | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=ST20 6LH | First name is required. | #dd2509 |
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    Then the user will see error messages highlighted in red colour when search with the given details
    ##@NTS-3328 @E2EUI-1254
      | DOB=23-03-2011:FirstName=Smith:Gender=Female | Last name is required. | #dd2509 |
    #@NTS-3328 @E2EUI-1493
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<validSearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    Examples:
      | FamilyMember   | validSearchDetails                                                           | ResultMessage          |
      | Family members | DOB=02-09-2011:FirstName=CHELSEA:LastName=CRAM:Gender=Male:Postcode=ST20 6LH | 1 patient record found |

  @NTS-3328 @NTS-3207 @Z-LOGOUT
#    @E2EUI-1260 @E2EUI-1259 @E2EUI-1083 E2EUI-830 @E2EUI-829 @E2EUI-1016
  Scenario Outline: NTS-3328: Search for a family member record with NHS selected No and provided a valid Postcode and all other mandatory fields left blank
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on Add family member button
    #NTS-3207
    ##By all fields blank
    When the user clicks the NO button in family member search page
    And the user clicks the Search button in family member search page
    Then the user will see error messages highlighted in red colour
      | message                    | color   |
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Last name is required.     | #dd2509 |
      | Gender is required.        | #dd2509 |

    ##By Providing PostCode only
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<searchWithPostcode>"
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

    ##By Providing firstName only
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    And the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<searchWithFirstName>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | Last name is required.     | #dd2509 |
      | Gender is required.        | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name | color   |
      | Last name  | #dd2509 |
      | Gender     | #dd2509 |

    ##By Providing lastname only
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<searchWithLastName>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Gender is required.        | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name | color   |
      | First name | #dd2509 |
      | Gender     | #dd2509 |

    ##By Providing gender only
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<searchWithGender>"
    Then the user will see error messages highlighted in red colour
      | Date of birth is required. | #dd2509 |
      | First name is required.    | #dd2509 |
      | Last name is required.     | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name | color   |
      | First name | #dd2509 |
      | Last name  | #dd2509 |

    Examples:
      | FamilyMember   | searchWithPostcode | searchWithFirstName | searchWithLastName          | searchWithGender |
      | Family members | Postcode=N16 7TY   | FirstName=StaMbuk   | LastName=StaMbukdelifschitZ | Gender=Female    |