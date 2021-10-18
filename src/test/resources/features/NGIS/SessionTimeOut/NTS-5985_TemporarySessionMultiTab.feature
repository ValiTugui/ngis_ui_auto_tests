@SESSION_TEST
Feature: NTS-5985/5983-Implement temporary session/cookie storage - Multi tab

  @NTS-5985 @NTS-5983
  Scenario: NTS-5985-5983: Test Order security while user is making a referral in a multi tab environment in a single known browser

    Given the user loads the browser with URL https://test-ordering.e2e-latest.ngis.io/test-order/
    And the user opens a new tab on the browser
    And a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user close the browser tab and opens the referral details in a new tab
    Then the user is navigated to a page with title Add a requesting organisation


  @NTS-7382
  Scenario: NTS-7382: Implement temporary session/cookie storage - New browser

    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Add a requesting organisation
    Then the user opens another browser instance
    Then the user is navigated to a page with title Add a requesting organisation