@Concurrency
@Concurrency_RD
Feature: NTS-6470: Family member details updated and same Family member verified as Proband in another referral.

  ###FLOW
  #User1 Login to an existing Referral - Proband
  #User2 Login to the another existing referral - Proband in first referral is the Family member on the current referral
  #User1 Updates patient details of proband
  #User2 Submit and verify the changes in family member (same patient who is proband in another referral) done by user1

  @NTS-6470 @NTS-6470_Scenario2 @Z-LOGOUT
  Scenario Outline: Login as User A,Complete all stages and do not submit referral,and validate the data updated, when B is updating a stage in different referral, upon referral submission by A.
    #Login as User A,
    Given The user is login to the Test Order Service and create a new referral
      | Intellectual disability | CONCURRENT_USER1_NAME | r20017193982 | NRF2 |
    ##Patient Details - Update
    And the user waits max 10 minutes for the update Mandatory Stages Completed by User2 in the file NRF2
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF2 with Patient details Updated by User1
    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | Gender=Male           |

  @NTS-6470 @NTS-6470_Scenario2 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | r20781045072 | NRF2 |
    Then the user updates the file NRF2 with Mandatory Stages Completed by User2
    ##Family Member Details - Verify
    And the user waits max 10 minutes for the update PatientDetails Updated by User1 in the file NRF2
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<FamilyMembers>" stage
    And the user verifies the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdated>"
    Then the user updates the file NRF2 with Patient details validated by User2

    Examples:
      | FamilyMembers  | FamilyMemberDetailsUpdated |
      | Family members | Gender=Male                |

    ################################

