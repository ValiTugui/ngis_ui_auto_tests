@AuditHistory
@AuditHistory_Cancer_Cancel
Feature: NTS-5873:Verify Audit history for a cancelled referral.
  ###FLOW

  @NTS-5873 @Z-LOGOUT @Scenario1
  Scenario:Scenario 1: Verify audit history for a cancelled referral(cancer) for using patient Human readable Id..
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M89 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
#    Given The user is login to the Test Order Service and create a new referral
#      | Fibro-Osseous Tumour of Bone Differential |  | New Referral | NRF1 |
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation