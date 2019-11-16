@regression2
@FamilyMember
Feature: FamilyMember search page

  @COMP8_TO_Familymembers
    @familyMembersSearchPage_01 @NTS-3227 @E2EUI-1947 @v_1 @P0
  Scenario Outline: NTS-3227:Verify that re-adding a patient who is already included in referral via Yes option displays error message
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the YES button is selected by default on family member search
    When the user provides NHS and DOB of an already added patient and search
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string

    Examples:
      | stage          | ErrorMessage1                                      | ErrorMessage2                                       |
      | Family members | That person has already been added to the referral | Check that all details have been entered correctly. |

  @COMP8_TO_Familymembers
    @familyMembersSearchPage_02 @NTS-3227 @E2EUI-1947 @v_1 @P0
  Scenario Outline: NTS-3227:Verify that re-adding a patient who is already included in referral via No option displays error message
    When the user clicks the NO button in family member search page
    When the user provides DOB,FirstName,LastName and Gender of an already added patient and search
    Then the message should display as "<ErrorMessage1>" and "<ErrorMessage2>" along with search string

    Examples:
      | ErrorMessage1                                      | ErrorMessage2                                       |
      | That person has already been added to the referral | Check that all details have been entered correctly. |