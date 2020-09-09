@Concurrency
@Concurrency_RD
Feature: NTS-6466: Different stages updated by three users and verified by another two users.

  ###FLOW
  #User1 Login to an existing Referral
  #User2 Login to the same Referral as User1
  #User3 Login to the same referral as User1 and User2
  #User1 Updates the Requesting organisation stage
  #User2 Updates the Notes stage
  #User3 Updates Patient details stage
  #User1 and User2 verifies the changes done by User3 in Patient details stage
  #User1 and user3 verifies the changes done by User2 in Notes stage
  #User2 and User3 verifies the changes done by User1 in Requesting organisation

  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Different stages updated by three users and verified by another two users upon referral submissions.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME |r20609782352| NRF1 |
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    ## Referral Submission by User1 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NRF1 with Patient details validated by User1
    ## Referral Submission by User1 after Notes updated by user2
    And the user waits max 10 minutes for the update Notes Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    When the user updates the file NRF1 with Notes validated by User1
    ##########################################################
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with RequestingOrganisation Updated by User1
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User2 in the file NRF1
    And the user waits max 10 minutes for the update Notes validated by User3 in the file NRF1
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User3 in the file NRF1
    ## Finally User1 submit Referral Successfully
    #And the user submits the referral
    #Then the submission confirmation message "Your referral has been submitted" is displayed
    #And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | PatientDetailsUpdated | NotesUpdated        |Notes|RequestingOrganisation|RequestingOrganisationUpdated|
      | Patient details | Postcode=AB1 2CD      | NotesUpdatedByUser2 |Notes|Requesting organisation |South London and Maudsley NHS Foundation Trust |


     #Login as User B

  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |  r20609782352 | NRF1 |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Notes Section
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    When the user navigates to the "<Notes>" stage
    And the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with Notes Updated by User2
    And the user waits max 10 minutes for the update Notes validated by User1 in the file NRF1
    ## Referral Submission by User2 after Patient Details updated by user 3
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    When the user updates the file NRF1 with Patient details validated by User2
  ###############################################################
    And the user waits max 10 minutes for the update RequestingOrganisation Updated by User1 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    When the user updates the file NRF1 with RequestingOrganisation validated by User2
    And the user waits max 10 minutes for the update Notes validated by User3 in the file NRF1
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User3 in the file NRF1

    Examples:
      | PatientDetails  | Notes | NotesUpdated        | PatientDetailsUpdated |Notes|RequestingOrganisation|RequestingOrganisationUpdated|
      | Patient details | Notes | NotesUpdatedByUser2 | Postcode=AB1 2CD      |Notes|Requesting organisation | South London and Maudsley NHS Foundation Trust |

  #Login as User C,

  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER3_NAME | r20609782352| NRF1 |
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NRF1 with Patient Details Updated by User3
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    ## Referral Submission by User1 after Notes updated by user2
    And the user waits max 10 minutes for the update Notes Updated by User2 in the file NRF1
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NRF1
    And the user waits max 10 minutes for the update RequestingOrganisation Updated by User1 in the file NRF1
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User2 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    When the user updates the file NRF1 with Notes validated by User3
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    When the user updates the file NRF1 with RequestingOrganisation validated by User3

    Examples:
      | PatientDetails  | Notes | NotesUpdated        | PatientDetailsUpdated |Notes|RequestingOrganisation|RequestingOrganisationUpdated|
      | Patient details | Notes | NotesUpdatedByUser2 | Postcode=AB1 2CD      |Notes|Requesting organisation | South London and Maudsley NHS Foundation Trust |





