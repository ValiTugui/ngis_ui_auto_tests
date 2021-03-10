@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory: TestOrder
#fail
  @test-3493
  @NTS-3493 @Z-LOGOUT
# @E2EUI-2015 @scenario_01
  Scenario Outline: NTS-3493: Restricted access to navigate to cancelled referrals
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_SUPER_USER | NHSNumber=2000001327:DOB=05-12-1987 |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "<Reason>" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | Reason                                             | RevokeMessage                                                             |
      | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect |

