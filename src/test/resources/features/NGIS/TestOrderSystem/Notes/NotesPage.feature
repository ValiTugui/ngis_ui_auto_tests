#@regression
#@notes
@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Notes page

  @NTS-4503 @Z-LOGOUT
#   @E2EUI-1130 @E2EUI-1172 @E2EUI-1213 @E2EUI-834 @E2EUI-1488
  Scenario Outline: Responsible Clinician Page - Phone number field - maximum length validation
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user is navigated to a page with title Add clinical notes
    Then the "<stage>" stage is selected
    And the user should be able to see an info message 3000 characters remaining on the Notes page
    When the user attempts to fill in Referral Notes field with 3010 data that exceed the maximum data allowed 3000
    Then the user should see the message displayed Delete 10 characters before saving in the notes page

    Examples:
      | stage |
      | Notes |
