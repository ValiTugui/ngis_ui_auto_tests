#@regression
#@FamilyMemberSearchPage
@03-TEST_ORDER
@SYSTEM_TEST

Feature: TestOrder - Family Members Search Page 3- Re-Adding existing members

  @NTS-3227
#    @E2EUI-1947
  Scenario Outline: NTS-3227: Verify that re-adding a patient who is already included in referral via Yes option displays error message
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449310327:DOB=16-12-1970:Gender=Male |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the YES button is selected by default on family member search
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData: NHSNumber and DOB same as the patient used for searching in Given step
    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage1                                      | ErrorMessage2                                       |
      | Family members | NHSNumber=9449310327:DOB=16-12-1970 | That person has already been added to the referral | Check that all details have been entered correctly. |

  @NTS-3227 @Z-LOGOUT
#    @E2EUI-1947
  Scenario Outline: NTS-3227: Verify that re-adding a patient who is already included in referral via No option displays error message
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData : Details of same patient used for searching in Given step
    Examples:
      | SearchDetails                                                      | ErrorMessage1                                      | ErrorMessage2                                       |
      | DOB=16-12-1970:FirstName=MARY:LastName=O'MY GOODNESS:Gender=Female | That person has already been added to the referral | Check that all details have been entered correctly. |

  @NTS-5810  @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field - Search family member with Postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-2005:Gender=Male |
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
      | stage          | SearchDetails                                                                      | ResultMessage          | PostcodeFormat |
      | Family members | DOB=20-09-2008:FirstName=OCTAVIA:LastName=CHISLETT:Gender=Female:Postcode=KT19 0ST | 1 patient record found | KT19 0ST       |
      | Family members | DOB=20-09-2008:FirstName=OCTAVIA:LastName=CHISLETT:Gender=Female:Postcode=kt19 0st | 1 patient record found | KT19 0ST       |
