@regression
@TO_Cancer
@tumoursPage1
@tumoursPage
Feature: Tumours Page

  @NTS-3165 @E2EUI-953 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3165:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc

    Examples:
      | stage   |
      | Tumours |

  @NTS-3165 @E2EUI-823 @E2EUI-1120 @E2EUI-1026 @E2EUI-1515 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3165: Text information for user on Tumour referral page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then an information "<information>" is displayed that a test cannot start without a tumour

    Examples:
      | stage   | information                                                                                              |
      | Tumours | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |

  @NTS-3241 @E2EUI-1576 @E2EUI-1410 @E2EUI-1356 @E2EUI-1699 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3241: Labels and help hint texts are displayed in Add a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the labels and help hint texts are displayed
      | labelHeader                                       | HintTextHeader                                                                                                                 |
      | Description                                       | Describe in a way that distinguishes this tumour from others the patient may have                                              |
      | Date of diagnosis ✱                               | Year is required. Enter day and month if known.                                                                                |
      | Histopathology laboratory ID or local sample ID ✱ | For solid tumours, enter the "Histopathology laboratory ID". For haemato-oncology liquid tumours, enter the "Local Sample ID". |
      | The tumour is... ✱                                | None                                                                                                                           |

    Examples:
      | stage   |
      | Tumours |


 @NTS-3170 @E2EUI-2018 @E2EUI-1840 @E2EUI-1350 @E2EUI-1486 @E2EUI-1459 @LOGOUT @P0 @v_1
  Scenario Outline:NTS-3152 Future date "<Date_of_Diagnosis>" : "<error_message>" can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
#    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
#    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field
    Then the DateOfDiagnosis field displays given messages in specific color for the wrong values
      | Date_of_Diagnosis | error_message                                     | color   |
      | 12-03-2150        | Please enter a date before today                  | #dd2509 |
      | 32-03-2011        | Enter a day between 1 and 31 or leave blank       | #dd2509 |
      | 0-04-2011         | Enter a day between 1 and 31 or leave blank       | #dd2509 |
      | 10-28-2011        | Enter a month between 1 and 12 or leave blank     | #dd2509 |
      | 10-0-2011         | Enter a month between 1 and 12 or leave blank     | #dd2509 |
      | 14-11-1           | Enter a year in 4 figures e.g. 1983               | #dd2509 |
      | 14-11-19          | Enter a year in 4 figures e.g. 1983               | #dd2509 |
      | 14-11-1899        | Cannot be more than 9 months before date of birth | #dd2509 |
      | 14-11-190         | Cannot be more than 9 months before date of birth | #dd2509 |
      | 14-11-null        | Enter a year                                      | #dd2509 |
      | 30-02-2012        | Check the day and month are valid                 | #dd2509 |
#      | 14-10-1899        | Enter a year beyond 1900                          | #dd2509 |
    Examples: of future date scenario
      | stage   |
      | Tumours |

# Replaced SPINE data with NGIS Data creation
  @NTS-3157 @E2EUI-1020 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3157:Validate the mandatory input field 'Date of diagnosis' for the Tumour Section
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions fields, select tumour type "<tumour_type>" and leaves date of diagnosis field blank
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of leaving the date of diagnosis field blank
      | stage   | tumour_type              | error_message |
      | Tumours | Solid tumour: metastatic | Enter a year  |


#    Test to be skipped till we sort out SPINE Data 20/02/2020
# @NTS-3154 @E2EUI-1320 @E2EUI-894 @E2EUI-1549 @E2EUI-1236 @LOGOUT @P0 @v_1
#  @ignore - this ignore tag is not picked up by Jenkins run. so commented out the entire ticket.
#  Scenario Outline: NTS-3154: Add a new tumour for an existing patient
#    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
#    When the user navigates to the "<stage>" stage
#    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
#    And the user clicks the Save and Continue button
#    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
#    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
#    And the user clicks the Save and Continue button
#    Then the new tumour is displayed in the landing page for the existing patient with tumour list
#    And the new tumour is not highlighted
#    And the "<stage>" stage is marked as Completed
#    And the success notification is displayed "<notificationText>"
#
#    Examples:
#      | stage   | tumour_type              | presentationType | searchTerm | notificationText |
#      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |

 # E2EUI-1440 E2EUI-1219
  @NTS-3154 @NTS-4734 @NTS-4761 @E2EUI-894 @E2EUI-1549 @E2EUI-949 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3154: Add a new tumour for a new patient
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
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

  @NTS-3255 @E2EUI-993 @E2EUI-1325 @E2EUI-1078 @E2EUI-1098 @LOGOUT @P0 @v_1 @BVT_P0
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
      | Tumours | Brain tumour                             | Recurrence         | test       | Tumour added     |
      | Tumours | Haematological malignancy: liquid sample | First presentation | test       | Tumour added     |
      | Tumours | Haematological malignancy: solid sample  | Unknown            | test       | Tumour added     |

    # Replaced SPINE data with NGIS Data creation
  @NTS-3171 @E2EUI-2145 @LOGOUT @P0 @v_1
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

 # Replaced SPINE data with NGIS Data creation
  @NTS-3171 @E2EUI-2145 @LOGOUT @P0 @v_1
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

  @NTS-3172 @E2EUI-1465 @LOGOUT @P0 @v_1
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


  @NTS-3174 @E2EUI-1159 @E2EUI-1577 @E2EUI-1377 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3174:Verify Estimated Date of Diagnosis, Tumour Type and Specimen ID fields are mandatory fields
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the tumours stage is at Add a Tumour page
    When the user clicks the Save and Continue button
    Then the error messages for the tumour mandatory fields are displayed
      | errorMessageHeader                                           |
      | Enter a year                                                 |
      | Please select the tumour type                                |
      | Histopathology laboratory ID or local sample ID is required. |

    Examples: Tumour type is not selected
      | stage   |
      | Tumours |


  @NTS-3176 @E2EUI-1171 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3176: Select or edit a tumour page - Add a new tumour and Tumour added notification is shown
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour added           | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And information text are displayed on the select or edit a tumour page
      | informationTextHeader                           |
      | Only one tumour can be tested in each referral. |
      | If the tumour to be tested is:                  |
      | not shown                                       |
      | a metastasis of one that is shown               |
      | you must add a new tumour then select it.       |
    And on the select or edit a tumour page, the tumour table list shows the column names
      | descriptionHeader | pathologySampleHeader                           | dateDiagnosedHeader | statusHeader |
      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       |

  @NTS-3190 @E2EUI-1513 @E2EUI-903 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3190: Select or edit a tumour page - Edit and save changes to a Tumour - functional and text rendering
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And information text are displayed on the select or edit a tumour page
      | informationTextHeader                           |
      | Only one tumour can be tested in each referral. |
      | If the tumour to be tested is:                  |
      | not shown                                       |
      | a metastasis of one that is shown               |
      | you must add a new tumour then select it.       |
    And on the select or edit a tumour page, the tumour table list shows the column names
      | descriptionHeader | pathologySampleHeader                           | dateDiagnosedHeader | statusHeader |
      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type   |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary |


  @NTS-3190 @E2EUI-1513 @E2EUI-903 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3190: Select or edit a tumour page - Edit and save changes to a Tumour and Tumour updated notification is shown
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type   |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary |


  @NTS-3204 @E2EUI-890 @E2EUI-1026 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3204:Edit a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the "<pageTitle>" page is displayed
    And an information "<information>" is displayed that a test cannot start without a tumour


    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | pageTitle     | information                                                                                              |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Edit a tumour | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |


  @NTS-3225 @E2EUI-2279 @E2EUI-1434 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: :NTS-3225: Edit a tumour page - The saved changes are displayed in the Edit a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the "<pageTitle>" page is displayed
    And the new tumour details are displayed in the Edit a Tumour page

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type   | pageTitle     |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary | Edit a tumour |

  @NTS-3176 @E2EUI-1412 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3176: Select or edit a tumour page - Added Tumour is displayed as a list on Select or edit a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
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
      | you must add a new tumour then select it.       |
    And on the select or edit a tumour page, the tumour table list shows the column names
      | descriptionHeader | pathologySampleHeader                           | dateDiagnosedHeader | statusHeader |
      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed
#
    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |


  @NTS-3431 @E2EUI-997 @LOGOUT @P0 @v_1
  Scenario Outline:NTS-3431:The Tumours stage is marked 'Mandatory ToDo' when not completed and marked 'Completed' when all tumour field completed
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Mandatory To Do
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


  @NTS-4503 @E2EUI-1130 @v_1 @LOGOUT
  Scenario Outline: Add Tumour Page - Description field - maximum length validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc
    When the user attempts to fill in the Tumour Description "<TumourDescription>" with data that exceed the maximum data allowed 45
    Then the user is prevented from entering data that exceed that allowable maximum data 45 in the "TumourDescription" field

    Examples:
      | stage   | TumourDescription                                  |
      | Tumours | 12345678901234567890123456789012345678901234567890 |


  @NTS-4757 @E2EUI-1339 @v_1 @LOGOUT
  Scenario Outline: NTS-4757: Add Tumour page error validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user clicks the Save and Continue button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                                       | errorMessageHeader                                           | messageColourHeader |
      | Date of diagnosis ✱                               | Enter a year                                                 | #dd2509             |
      | The tumour is... ✱                                | Please select the tumour type                                | #dd2509             |
      | Histopathology laboratory ID or local sample ID ✱ | Histopathology laboratory ID or local sample ID is required. | #dd2509             |

    Examples:
      | stage   | pageTitle    |
      | Tumours | Add a tumour |