@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: Tumours Page Navigation

  @NTS-7158 @NTOS-5483 @Z-LOGOUT
  Scenario Outline: NTS-7158: Validate Save and continue / Continue button through all the pages of Tumour stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
       ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "University College London Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ##Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    When the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    Then the user is navigated to a page with title Edit a tumour
    And the user should be able to see "Continue" button
    When the user updates the "Tumours" stage with "<tumour_type1>"
    Then the user should be able to see "Save and continue" button
    When the user updates the "Tumours" stage with "<tumour_type2>"
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    And the user should be able to see "Continue" button
    When the user updates the page "<pageTitle>" with "<TumoursQuestionnaireUpdated>"
    Then the user should be able to see "Save and continue" button
    When the user updates the page "<pageTitle>" with "<TumoursQuestionnaireUpdated1>"
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    Then the user should be able to see "Save and continue" button
    Examples:
      | tumour_type              | presentationType | tumour_type1 | tumour_type2             | pageTitle                          | TumoursQuestionnaireUpdated          | TumoursQuestionnaireUpdated1 |
      | Solid tumour: metastatic | Recurrence       | Brain tumour | Solid tumour: metastatic | Answer questions about this tumour | FirstPresentation=First presentation | FirstPresentation=Recurrence |