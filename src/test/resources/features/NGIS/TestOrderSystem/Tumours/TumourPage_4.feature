#@tumoursPage
@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: Tumours Page - 4

  #noinspection NonAsciiCharacters
  @NTS-3487 @Z-LOGOUT
#    @E2EUI-2144 @E2EUI-2097
  Scenario Outline: NTS-3487:E2EUI-2144,2097: Change 'Tumour Content' display value
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M185 | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    And the "<Tumours>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Blood (EDTA)" and filling SampleID
    And the user clicks the Save and Continue button
    And the user sees no error message on tumour page by selecting the sample type "Solid tumour sample", sample state "Blood (EDTA)" and filling SampleID
    Then the user is navigated to a page with title Add sample details
    And asterisk "<TumourContentPercentageOfMalignant>" star symbol is shown as mandatory next to the Tumour content - percentage of malignant field label for only Solid tumour sample

    Examples:
      | Tumours | tumour_type              | presentationType   | TumourContentPercentageOfMalignant                         |
      | Tumours | Solid tumour: metastatic | First presentation | Tumour content (percentage of malignant nuclei / blasts) ✱ |