@SESSION_TEST
Feature: NTS-5986-Implement temporary session/cookie storage - Multi browser

  @NTS-5986 @NTS-5983 @Browser1
  Scenario: NTS-5986: Login to Test Order and create a referral and close the browser

    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Add a requesting organisation
    And the user writes the referralURL to the file NTS-5986 and close the window

  @NTS-5986 @NTS-5983 @Browser2
  Scenario: NTS-5986: Open the referral created by first scenario in new browser instance
    Given the user loads the browser with URL https://test-ordering.int.ngis.io/test-order/
    When the user access the referral created by first scenario from file NTS-5986
    Then the user is navigated to a page with title Add a requesting organisation