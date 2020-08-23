@Concurrency
@Concurrency_existing_referral
Feature: Submit Existing Referral

  @existing_referral @Z-LOGOUT
  Scenario Outline: User One Login and Updates Some Stages, Submits Referral after User Two changes Same Stages
    ##Create New Referral
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER1_NAME | r20652134582 |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    When the user updates the concurrency controller file with Mandatory Stages Completed by User1
    And the user waits max 2 minutes for the update Notes Updated by User2 in the concurrency controller file
    And the user submits the referral
#    Then the submission confirmation message "Your referral has been submitted" is displayed
#    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  |
      | Patient details |

  @existing_referral @Z-LOGOUT
  Scenario Outline: Update the Notes Stage after User One completes all mandatory stages

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME |r20652134582|
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Notes Section
    When the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the concurrency controller file
    And the user navigates to the "<notes>" stage
    Then the user is navigated to a page with title Add clinical notes
    And the "<notes>" stage is selected
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<notes>" stage is marked as Completed
    And the user updates the concurrency controller file with Notes Updated by User2

    Examples:
      | PatientDetails  | notes |
      | Patient details | Notes |