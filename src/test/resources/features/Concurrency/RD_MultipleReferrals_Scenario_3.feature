@Concurrency
@Concurrency_newReferral_RD
Feature: Submit Existing Multiple Referrals to validate every stage

@NTS-6470
   @RD_existing_referral_multipleReferral_scenario_3 @Z-LOGOUT
  Scenario Outline:Login as User A,Complete all stages and do not submit referral,and validate the data updated,when B is updating a stage in different referral, upon referral submission by A.
#Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME |r20706061692| NRF1 |
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
##Patient Details - Verify
    And the user waits max 10 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NRF1 with Patient details validated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated       |
      | Patient details | Ethnicity=B - White - Irish |

  @RD_existing_referral_multipleReferral_scenario_3 @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |r20473438456| NRF1 |
##Patient Details - Update
    And the user waits max 2 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with PatientDetails Updated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated       |
      | Patient details | Ethnicity=B - White - Irish |



