@Concurrency
@Concurrency_test
@Concurrency_newReferral_RD
Feature: Submit Existing Multiple Referrals to validate every stage

  @NTS-6470
  @RD_existing_referral_multipleReferral_scenario_2 @Z-LOGOUT
  Scenario Outline: Login as User A,Complete all stages and do not submit referral,and validate the data updated, when B is updating a stage in different referral, upon referral submission by A.
#Login as User A,
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | r20890331361 | NRF2 |
##Patient Details - Update
    And the user waits max 2 minutes for the update Mandatory Stages Completed by User2 in the file NRF2
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF2 with PatientDetails Updated by User1
    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | Gender=Male           |

  @RD_existing_referral_multipleReferral_scenario_2 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | r20307224383 | NRF2 |
    Then the user updates the file NRF2 with Mandatory Stages Completed by User2
##Family Member Details - Update
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

