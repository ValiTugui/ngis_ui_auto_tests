@SESSION_TEST_MB
Feature: NTS-5986-Implement temporary session/cookie storage - Multi browser

  @NTS-5986
  Scenario: NTS-5986: Test Order security while user is making a referral in a multi tab environment with multi (same) browser sessions

    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Add a requesting organisation
    And the user updates the file NTS-6467_Cancer with Mandatory Stages Completed by User1
    When the user close the browser tab and opens the referral details in a new tab
    Then the user is navigated to a page with title Add a requesting organisation