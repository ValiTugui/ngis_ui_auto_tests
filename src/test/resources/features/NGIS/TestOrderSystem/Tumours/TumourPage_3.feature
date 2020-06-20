#@tumoursPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Tumours Page - 3

  @NTS-3255 @Z-LOGOUT
# @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098
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
      | Tumours | Solid tumour: metastatic                 | First presentation | test       | Tumour added     |
      | Tumours | Solid tumour: primary                    | Recurrence         | test       | Tumour added     |
      | Tumours | Solid tumour: unknown                    | Unknown            | test       | Tumour added     |

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

    #Split to separate based on jenkins run failure
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
      | Tumours | Haematological malignancy: liquid sample | First presentation | test       | Tumour added     |

  @NTS-3171 @Z-LOGOUT
#    @E2EUI-2145
  Scenario Outline:NTS:3171:E2EUI-2145:The user is stopped to navigate away from dynamic questions step from Tumours stage after making changes
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
   #  User click on refresh button
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    #     User click on back button - Defect - https://jira.extge.co.uk/browse/NTOS-4539
#     User click on back button
#    When the user attempts to navigate away by clicking "<browser_exit2>"
#    Then the user sees a prompt alert "<partOfMessage2>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl2>" page
   #  User click on logout button
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit3>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | acknowledgeMessage | partOfMessage1    | partialCurrentUrl1 | browser_exit1 | browser_exit3 |
      | Tumours | Solid tumour: metastatic | Dismiss            | may not be saved. | tumours/new     | refresh       | logout        |