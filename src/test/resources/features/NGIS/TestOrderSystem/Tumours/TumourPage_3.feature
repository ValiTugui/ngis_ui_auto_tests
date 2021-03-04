#@tumoursPage
@03-TEST_ORDER
@SYSTEM_TEST
@Tumours
Feature: Tumours Page - 3

  @NTS-3255 @NTS-3176 @NTS-3154 @Z-LOGOUT
    # @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098 @E2EUI-1412 @E2EUI-1171 @E2EUI-894 @E2EUI-1549 @E2EUI-949
  Scenario Outline: NTS-3255:E2EUI-993,1325,1078,1098: Add a new tumour : "<tumour_type>" for a new patient with various tumour type "<tumour_type>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type1>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType1>"
    And the user answers the tumour dynamic questions "<tumour_type1>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #NTS-3176 NTS-3154
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<Tumours>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
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
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed
    And user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type2>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType2>"
    And the user answers the tumour dynamic questions "<tumour_type2>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    And user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type3>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType3>"
    And the user answers the tumour dynamic questions "<tumour_type3>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    And user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type4>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType4>"
    And the user answers the tumour dynamic questions "<tumour_type4>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    And user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type5>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType5>"
    And the user answers the tumour dynamic questions "<tumour_type5>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    And user clicks add a new tumour link
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type6>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType6>"
    And the user answers the tumour dynamic questions "<tumour_type6>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the "<Tumours>" stage is marked as Completed
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed

    Examples:
      | Tumours | tumour_type1 | tumour_type2             | tumour_type3          | tumour_type4          | tumour_type5                             | tumour_type6                            | presentationType1 | presentationType2  | presentationType3 | presentationType4 | presentationType5  | presentationType6 | searchTerm | notificationText |
      | Tumours | Brain tumour | Solid tumour: metastatic | Solid tumour: primary | Solid tumour: unknown | Haematological malignancy: liquid sample | Haematological malignancy: solid sample | Recurrence        | First presentation | Recurrence        | Unknown           | First presentation | Unknown           | test       | Tumour added     |


