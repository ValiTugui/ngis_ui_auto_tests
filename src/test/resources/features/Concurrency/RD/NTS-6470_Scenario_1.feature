@Concurrency
@Concurrency_RD
Feature: NTS-6470: Proband details updated as family member in another referral, submit and verify the changes

  ###FLOW
  #User1 Login to an existing Referral - Proband
  #User2 Login to the another referral - Proband in first referral is the Family member on the current referral
  #User2 Updates patient details of family member (proband of first referral)
  #User1 Submit and verify the changes done by user2

  @NTS-6470 @NTS-6470_Scenario1 @Z-LOGOUT
  Scenario Outline: Login as User A,Complete all stages and do not submit referral,and validate the data updated, when B is updating a stage in different referral, upon referral submission by A.
    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Holoprosencephaly - NOT chromosomal | CONCURRENT_USER1_NAME | r21767325992 | NTS-6470_Scenario1 |
    Then the user updates the file NTS-6470_Scenario1 with Mandatory Stages Completed by User1
    ##FamilyMembers - Verify
    And the user waits max 10 minutes for the update Family Member details Updated by User2 in the file NTS-6470_Scenario1
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NTS-6470_Scenario1 with Family Member details validated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=18-05-2006        |

  @NTS-6470 @NTS-6470_Scenario1 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | r21531177197 | NTS-6470_Scenario1 |
    And the user waits max 2 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6470_Scenario1
    ##FamilyMembers - Update
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user updates the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdate>"
    And the user clicks the Save and Continue button
    And the "<FamilyMembers>" stage is marked as Completed
    Then the user updates the file NTS-6470_Scenario1 with Family Member details Updated by User2

    Examples:
      | FamilyMemberDetailsUpdate | FamilyMembers  |
      | DOB=18-05-2006            | Family members |