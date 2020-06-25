@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Tumours Page - 9

 # E2EUI-1440 E2EUI-1219
  @NTS-3154 @Z-LOGOUT
#    @E2EUI-894 @E2EUI-1549 @E2EUI-949
  Scenario Outline: NTS-3154:E2EUI-894,1549,949: Add a new tumour for a new patient
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the user see a tick mark next to the added tumour
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples:
      | stage   | tumour_type              | presentationType   | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | First presentation | test       | Tumour added     |