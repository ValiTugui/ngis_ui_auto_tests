@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: Tumours Page - 5

  @NTS-3176 @NTS-3190 @Z-LOGOUT
#    @E2EUI-1171 @E2EUI-1513 @E2EUI-903
  Scenario Outline: NTS-3176:E2EUI-1171: Select or edit a tumour page - Add a new tumour and Tumour added notification is shown
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Add a tumour
    And the tumours stage is at Add a Tumour page
    When the user clicks the Save and Continue button
    Then the error messages for the tumour mandatory fields are displayed
      | errorMessageHeader                           |
      | Enter a year                                 |
      | Please select the tumour type                |
#      | Histopathology laboratory ID or local sample ID is required. |
      | Histopathology or SIHMDS Lab ID is required. |
    And the user navigates to the "<Tumours>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<Tumours>" stage is marked as Completed
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour added           | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And information text are displayed on the select or edit a tumour page
      | informationTextHeader                           |
      | Only one tumour can be tested in each referral. |
      | If the tumour to be tested is:                  |
      | not shown                                       |
      | a metastasis of one that is shown               |
      | you must                                        |
      | add a new tumour                                |
      | then select it.                                 |
    And on the select or edit a tumour page, the tumour table list shows the column names
      | descriptionHeader | pathologySampleHeader           | dateDiagnosedHeader | statusHeader |
#      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon

    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon

    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<Tumours>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    Then on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | Tumours | tumour_type              | presentationType | searchTerm | updated_tumour_type   |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary |


