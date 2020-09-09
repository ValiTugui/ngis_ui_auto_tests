@Concurrency
@Concurrency_RD
Feature: NTS-6470: Family member details updated and same Family member patient verified in another referral.

  ###FLOW
  #User1 Login to an existing Referral - Proband and family member
  #User2 Login to the another existing referral - Proband and family member (In both referrals family member patient is same)
  #User2 Updates patient details of Family member
  #User1 Submit and verify the changes in Family member patient details done by user2

  @NTS-6470 @NTS-6470_Scenario4 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.
   #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME |r20997615184| NRF1 |
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    ##FamilyMembers - Verify
    And the user waits max 10 minutes for the update Family Member details Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<FamilyMembers>" stage
    Then the user verifies the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdated>"
    Then the user updates the file NRF1 with Family Member details validated by User1

    Examples:
      | FamilyMemberDetailsUpdated | FamilyMembers  |
      | Gender=Male                | Family members |

  @NTS-6470 @NTS-6470_Scenario4 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |r20764991948| NRF1 |
    And the user waits max 2 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    ##FamilyMembers - Update
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user updates the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdate>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    And the "<FamilyMembers>" stage is marked as Completed
    Then the user updates the file NRF1 with Family Member details Updated by User2

    Examples:
      | FamilyMembers  | FamilyMemberDetailsUpdate | DiseaseStatusDetails   |
      | Family members | Gender=Male               | DiseaseStatus=Affected |