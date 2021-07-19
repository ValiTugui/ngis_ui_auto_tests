#@FamilyMemberSearchPage
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: TestOrder - Family Members Search Page 3- Re-Adding existing members

  @NTS-3227
#    @E2EUI-1947
  Scenario Outline: NTS-3227: Verify that re-adding a patient who is already included in referral via Yes option displays error message
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449304890:DOB=03-09-1996:Gender=Unknown |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData: NHSNumber and DOB same as the patient used for searching in Given step
    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage1                                      | ErrorMessage2                                       |
      | Family members | NHSNumber=9449304890:DOB=03-09-1996 | That person has already been added to the referral | Check that all details have been entered correctly. |

  @NTS-3227 @Z-LOGOUT
#    @E2EUI-1947
  Scenario Outline: NTS-3227: Verify that re-adding a patient who is already included in referral via No option displays error message
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData : Details of same patient used for searching in Given step
    Examples:
      | SearchDetails                                                            | ErrorMessage1                                      | ErrorMessage2                                       |
      | DOB=03-09-1996:FirstName=BROOK:LastName=COMLEY:Gender=Unknown | That person has already been added to the referral | Check that all details have been entered correctly. |

  @NTS-5810
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field - Search family member with Postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-2005:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat>" format

    Examples:
      | stage          | SearchDetails                                                                   | ResultMessage          | PostcodeFormat |
      | Family members | DOB=01-10-2011:FirstName=AMBERA:LastName=ZHOU ZHU:Gender=Male:Postcode=LE17 2RJ | 1 patient record found | LE17 2RJ       |

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field - Search family member with Postcode.
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat>" format

    Examples:
      | stage          | SearchDetails                                                                   | ResultMessage          | PostcodeFormat |
      | Family members | DOB=01-10-2011:FirstName=AMBERA:LastName=ZHOU ZHU:Gender=Male:Postcode=le17 2rj | 1 patient record found | LE17 2RJ       |

  @NTS-7032 @Z-LOGOUT
  #NTS-7032 #NTOS-4986
  Scenario Outline:NTS-7032:NTOS-4986: Search Family member : Verify Postcode field error message with invalid format value.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-2005:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    When the user clicks the NO button in family member search page
    And the user fills in the Postcode field box with "<Postcode1>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode2>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode3>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode4>"
    Then the user should be able to see "<errorMessage>"
    And the user fills in the Postcode field box with "<Postcode5>"
    Then the user should be able to see "<errorMessage>"
    Examples:
      | stage          | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | errorMessage                           |
      | Family members | shdfkhs   | 982490    | AA99A 9AA | A99A 9AA  | rwrw2424  | This postcode is not in a valid format |