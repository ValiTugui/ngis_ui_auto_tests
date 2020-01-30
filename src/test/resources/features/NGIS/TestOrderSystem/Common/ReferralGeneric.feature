@regression
@TO_Common
@referral

Feature: This is a referral feature

  @NTS-3675 @LOGOUT @v_1 @E2EUI-1250
  Scenario Outline: Referral: Date of Birth and Age format in the referral header bar
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is marked as Completed
    And the DOB and age in the referral header bar are displayed in expected format

    Examples:
      | stage           |
      | Patient details |