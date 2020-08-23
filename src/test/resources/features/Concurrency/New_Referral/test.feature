@Concurrency
@Concurrency_newReferral_RD


Feature: Submit Existing Referral for RD flow

  #User1
 @NTS-1 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | r20232207638 | NRF1 |
   When the user is navigated to a page with title Add a requesting organisation
    Then the user click on Reload referral button to validate the data

    Examples:
      | PatientDetails  |
    |                 Patient|