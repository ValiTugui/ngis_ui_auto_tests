@SESSION_TEST
Feature: NTS-5675-Tumour with expired Cookie

  @NTS-5675
  Scenario Outline: NTS-5675: Wrong page is displayed when trying to add a tumour with authentication cookie expired

    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Add a tumour
    When the user inactive for 21 minutes
#    When the user clear all the session cookies in a new tab
    When the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a tumour

    Examples:
      | Tumours |
      | Tumours |