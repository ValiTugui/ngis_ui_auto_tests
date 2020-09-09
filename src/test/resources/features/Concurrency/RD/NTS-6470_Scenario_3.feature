@Concurrency
@Concurrency_RD
Feature: NTS-6470: Proband details updated and same proband patient verified in another referral.

  ###FLOW
  #User1 Login to an existing Referral - Proband
  #User2 Login to the another existing referral - Proband (In both referrals Proband patient is same)
  #User2 Updates patient details of proband
  #User1 Submit and verify the changes in patient details done by user2

  @NTS-6470 @NTS-6470_Scenario3 @Z-LOGOUT
  Scenario Outline:Login as User A,Complete all stages and do not submit referral,and validate the data updated,when B is updating a stage in different referral, upon referral submission by A.
    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Holoprosencephaly - NOT chromosomal | CONCURRENT_USER1_NAME |r20101658758| NRF1 |
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

  @NTS-6470 @NTS-6470_Scenario3 @Z-LOGOUT
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



