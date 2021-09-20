@Concurrency
@Concurrency_RD
Feature: NTS-7321:Concurrency alert while deleting a family member

  @NTS-7321_Scenario3 @Z-LOGOUT
  Scenario Outline: As a user I should see the concurrency error when User A is on Select Tests page and User B removes the FM
    Given The user is login to the Test Order Service and create a new referral
      | R100 | CONCURRENT_USER1_NAME | New Referral | NTS-7321_Scenario3 |
    #   Family Members -
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=11-01-1971:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Unaffected:AgeOfOnset=02,02:HpoPhenoType=Dystonia |
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user updates the file NTS-7321_Scenario3 with Family member added by User1
    And the user waits max 20 minutes for the update User2 navigated to FamilyMembers in the file NTS-7321_Scenario3
    And the user clicks on edit button of Family member
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    Then the user updates the file NTS-7321_Scenario3 with User1 navigated to the Select Tests page
    And the user waits max 20 minutes for the update FamilyMember deleted by User2 in the file NTS-7321_Scenario3
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    And the user refresh the browser
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should not see the deleted family member on the landing page
    Then the user updates the file NTS-7321_Scenario2 with FamilyMember is not seen for User1

    Examples:
      | TwoParticipant | FamilyMembers  |
      | 2              | Family members |

  @NTS-7321_Scenario3 @Z-LOGOUT
  Scenario Outline: Navigate to the referral and User B removes the FM
    And the user waits max 30 minutes for the update Family member added by User1 in the file NTS-7321_Scenario3
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-7321_Scenario3 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user updates the file NTS-7321_Scenario3 with User2 navigated to FamilyMembers
    And the user waits max 20 minutes for the update User1 navigated to the Select Tests page in the file NTS-7321_Scenario3
    When the user removes the family member
    Then the user accept the alert with message Removing a family member can't be undone. Do you still want to remove them?
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page
    And the user updates the file NTS-7321_Scenario3 with FamilyMember deleted by User2
    Examples:
      | FamilyMembers  | SuccessDeleteMessage                |
      | Family members | Family member removed from referral |