@regression2
@FamilyMember
Feature: FamilyMember search page

  @COMP8_TO_Familymembers
    @familyMembersSearchPage_01 @NTS-3227 @E2EUI-1947 @v_1 @P0
  Scenario Outline: NTS-3227:Verify that re-adding a patient who is already included in referral via Yes option displays error message
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the YES button is selected by default on family member search
    When the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData: NHSNumber and DOB of searched patient used for searching in Given step
    Examples:
      | stage          | NhsNumber  | DOB        | ErrorMessage1                                      | ErrorMessage2                                       |
      | Family members | 9449310270 | 12-08-2007 | That person has already been added to the referral | Check that all details have been entered correctly. |

  @COMP8_TO_Familymembers
    @familyMembersSearchPage_02 @NTS-3227 @E2EUI-1947 @v_1 @P0
  Scenario Outline: NTS-3227:Verify that re-adding a patient who is already included in referral via No option displays error message
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string
    ##TestData : Details of same patient used for searching in Given step
    Examples:
      | SearchDetails                                               | ErrorMessage1                                      | ErrorMessage2                                       |
      | DOB=12-08-2007:FirstName=ALEX:LastName=CRESSEY:Gender=Male | That person has already been added to the referral | Check that all details have been entered correctly. |