#@regression
#@tumoursPage1
#@tumoursPage
@TEST_ORDER
@SYSTEM_TEST
Feature: Tumours Page - 3

  @NTS-3255 @LOGOUT
#    @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098
  Scenario Outline: NTS-3255: Add a new tumour : "<tumour_type>" for a new patient with various tumour type "<tumour_type>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
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

  @NTS-3255 @LOGOUT
#    @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098
  Scenario Outline: NTS-3255: Add a new tumour : "<tumour_type>" for a new patient with various tumour type "<tumour_type>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
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
      | Tumours | Haematological malignancy: liquid sample | First presentation | test       | Tumour added     |
      | Tumours | Haematological malignancy: solid sample  | Unknown            | test       | Tumour added     |

  @NTS-3171 @LOGOUT
#    @E2EUI-2145
  Scenario Outline:NTS:3171:Moving to other section:The user is stopped to navigate away from dynamic questions step from Tumours stage after editing
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
# moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | new_stage | acknowledgeMessage | partOfMessage       | partialCurrentUrl1 |
      | Tumours | Solid tumour: metastatic | Samples   | Dismiss            | unsaved information | tumours/create     |

  @NTS-3171 @LOGOUT
#    @E2EUI-2145
  Scenario Outline:NTS:3171:The user is stopped to navigate away from dynamic questions step from Tumours stage after making changes
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
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
      | stage   | tumour_type              | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | browser_exit1 | browser_exit2 | browser_exit3 | partialCurrentUrl2 |
      | Tumours | Solid tumour: metastatic | Dismiss            | may not be saved. | unsaved information | tumours/create     | refresh       | back          | logout        | tumours            |

  @NTS-3172 @LOGOUT
#    @E2EUI-1465
  Scenario Outline: NTS-3172:Validate the mandatory input field 'The tumour is' for the Tumour Section
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions without selecting any tumour type
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: Tumour type is not selected
      | stage   | error_message                 |
      | Tumours | Please select the tumour type |
