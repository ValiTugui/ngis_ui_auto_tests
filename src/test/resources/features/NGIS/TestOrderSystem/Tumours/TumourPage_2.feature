#@tumoursPage
@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: TestOrder - Tumours Page - 2

  @NTS-3249 @NTS-3259 @Z-LOGOUT
#    @E2EUI-1459 @E2EUI-1075
  Scenario Outline: NTS-3249:E2EUI-1459: Fuzzy date "<Date_of_Diagnosis>" on Date of Diagnosis field
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user enters "<Date_of_Diagnosis1>" in the date of diagnosis field
    And the user answers the tumour system specific question fields - Description, Select a tumour type "<tumour_type>" and Pathology Sample ID
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType1>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
    And user clicks add a new tumour link
    And the "<pageTitle2>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle>" page is displayed
    And the user is navigated to a page with title Select or edit a tumour
    And user clicks add a new tumour link
    And the user enters "<Date_of_Diagnosis2>" in the date of diagnosis field
    And the user answers the tumour system specific question fields - Description, Select a tumour type "<tumour_type>" and Pathology Sample ID
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType2>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
    And user clicks add a new tumour link
    And the "<pageTitle2>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle>" page is displayed
    And the user is navigated to a page with title Select or edit a tumour
    And user clicks add a new tumour link
    And the user enters "<Date_of_Diagnosis3>" in the date of diagnosis field
    And the user answers the tumour system specific question fields - Description, Select a tumour type "<tumour_type>" and Pathology Sample ID
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType3>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    #Then the new tumour is displayed in the landing page
    #And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    #NTS-3259
    And the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    And user clicks add a new tumour link
    And the "<pageTitle2>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle>" page is displayed

    Examples: of filling out the year and leaving the month and day blank
      | stage   | Date_of_Diagnosis1 | Date_of_Diagnosis2 | Date_of_Diagnosis3 | tumour_type              | presentationType1 | presentationType2  | presentationType3 | searchTerm | notificationText | pageTitle               | pageTitle2   |
      | Tumours | null-null-2018     | null-11-2018       | 10-11-2018         | Solid tumour: metastatic | Recurrence        | First presentation | Unknown           | test       | Tumour added     | Select or edit a tumour | Add a tumour |

  @NTS-3252 @NTS-3174 @Z-LOGOUT
#    @E2EUI-1107 @E2EUI-1096 @E2EUI-1159 @E2EUI-1577 @E2EUI-1377
  Scenario Outline: NTS-3252:E2EUI-1107,1096: Tumour-list - Indicate any tumour list with incomplete or outstanding mandatory questions
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    #NTS-3174
    And the tumours stage is at Add a Tumour page
    When the user clicks the Save and Continue button
    Then the error messages for the tumour mandatory fields are displayed
      | errorMessageHeader                           |
      | Enter a year                                 |
      | Please select the tumour type                |
#      | Histopathology laboratory ID or local sample ID is required. |
      | Histopathology or SIHMDS Lab ID is required. |

    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list
    And the new tumour details added in the tumour list are indicated as in-complete with "<messageColor>" fonts colour
    Then the error message "<errorMessage>" is displayed in "<messageColor>" fonts colour in the page

    Examples:
      | stage   | pageTitle               | tumour_type              | notificationText | errorMessage                                           | messageColor |
      | Tumours | Select or edit a tumour | Solid tumour: metastatic | Tumour added     | There is essential information missing from this entry | #dd2509      |

