@regression
@TO_Common

Feature: Notes page

 @NTS-4503 @E2EUI-1130 @E2EUI-1172 @v_1 @LOGOUT
  Scenario Outline: Responsible Clinician Page - Phone number field - maximum length validation
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    When the user attempts to fill in Referral Notes field with data that exceed the maximum data allowed 3000
    Then the user is prevented from entering data that exceed that allowable maximum data 3000 in the "referralNotes" field

    Examples:
      | stage |
      | Notes |