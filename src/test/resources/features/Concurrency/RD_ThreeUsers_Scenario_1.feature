@Concurrency
@Concurrency_test
@Concurrency_newReferral_RD

Feature:Submit Existing Referral for three users

  @RD_existing_referral_three_users_scenario_1 @Z-LOGOUT
    @NTS-6466
  Scenario Outline:User A and User B are unable to submit referral, where User C  has updated the same referral, until it has been checked.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME |r20609782352 | NRF1 |
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    # Referral Submission by User1 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NRF1 with Patient details validated by User1
    And the user waits max 3 minutes for the update Patient details validated by User2 in the file NRF1
    # Finally User1 submit Referral Successfully
#    And the user submits the referral
#    Then the submission confirmation message "Your referral has been submitted" is displayed
#    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=20-10-2010        |


#Login as User B,

  @RD_existing_referral_three_users_scenario_1 @Z-LOGOUT

  Scenario Outline: Update the stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |r20609782352| NRF1 |
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
## Referral Submission by User2 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NRF1 with Patient details validated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=20-10-2010        |

#Login as User C,

  @RD_existing_referral_three_users_scenario_1 @Z-LOGOUT

  Scenario Outline: Update the stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER3_NAME |r20609782352| NRF1 |

    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    Then the user updates the file NRF1 with Patient Details Updated by User3
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NRF1
    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=20-10-2010        |

