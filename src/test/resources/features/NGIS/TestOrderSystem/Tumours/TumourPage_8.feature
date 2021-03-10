#@tumoursPage
@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
Feature: Tumours Page - 8

  @NTS-3255 @Z-LOGOUT
#    @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098
  Scenario Outline: NTS-3255:E2EUI-993,1325,1078,1098: Add a new tumour : "<tumour_type>" for a new patient with various tumour type "<tumour_type>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions "<tumour_type>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples:
      | stage   | tumour_type                              | presentationType   | searchTerm | notificationText |
      | Tumours | Brain tumour                             | Recurrence         | test       | Tumour added     |
      | Tumours | Haematological malignancy: solid sample  | Unknown            | test       | Tumour added     |

  @NTS-3176 @Z-LOGOUT
#    @E2EUI-1412
  Scenario Outline: NTS-3176:E2EUI-1412: Select or edit a tumour page - Added Tumour is displayed as a list on Select or edit a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
    When the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | None                   | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
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
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |
