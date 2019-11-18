@regression
@regression_set3
@tumoursPage
Feature: Tumours Page

  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_01 @NTS-3165 @E2EUI-953 @P0 @v_1
  Scenario Outline: NTS-3165:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc

    Examples:
      | stage   |
      | Tumours |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_02 @NTS-3165 @E2EUI-823 @E2EUI-1120 @P0 @v_1
  Scenario Outline: NTS-3165: Text information for user on Tumour referral page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then an information "<information>" is displayed that a test cannot start without a tumour

    Examples:
      | stage   | information                                                                                              |
      | Tumours | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_02b @NTS-3241 @E2EUI-1576 @E2EUI-1410 @E2EUI-1356 @P0 @v_1
  Scenario Outline: NTS-3241: Labels and help hint texts are displayed in Add a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the labels and help hint texts are displayed
      | labelHeader                                       | HintTextHeader                                                                                             |
      | Description                                       | Describe in a way that distinguishes this tumour from others the patient may have                          |
      | Date of diagnosis ✱                               | Year is required. Enter day and month if known.                                                            |
      | Histopathology laboratory ID or local sample ID ✱ | Add the pathology or haemotology identifier from the local report. Use the subspecimen ID if there is one. |
      | The tumour is... ✱                                | None                                                                                                       |

    Examples:
      | stage   |
      | Tumours |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_03 @NTS-3152 @NTS-3170 @E2EUI-2018 @E2EUI-1840 @E2EUI-1350 @E2EUI-1486 @E2EUI-1459 @P0 @v_1
  Scenario Outline:NTS-3152 Future date can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of future date scenario
      | stage   | Date_of_Diagnosis | error_message                    |
      | Tumours | 12-03-2150        | Please enter a date before today |

    Examples: of invalid day
      | stage   | Date_of_Diagnosis | error_message                               |
      | Tumours | 32-03-2011        | Enter a day between 1 and 31 or leave blank |
      | Tumours | 0-04-2011         | Enter a day between 1 and 31 or leave blank |

    Examples: of invalid month
      | stage   | Date_of_Diagnosis | error_message                                 |
      | Tumours | 10-28-2011        | Enter a month between 1 and 12 or leave blank |
      | Tumours | 10-0-2011         | Enter a month between 1 and 12 or leave blank |

    Examples: of invalid year
      | stage   | Date_of_Diagnosis | error_message                       |
      | Tumours | 14-11-1           | Enter a year in 4 figures e.g. 1983 |
      | Tumours | 14-11-19          | Enter a year in 4 figures e.g. 1983 |

    Examples: diagnosis year comes before patient year birth
      | stage   | Date_of_Diagnosis | error_message                                     |
      | Tumours | 14-11-1899        | Cannot be more than 9 months before date of birth |
      | Tumours | 14-11-190         | Cannot be more than 9 months before date of birth |

    Examples: Enter year starting from 1900
      | stage   | Date_of_Diagnosis | error_message            |
      | Tumours | 14-11-0           | Enter a year beyond 1900 |

    Examples: of entering day and month without a year
      | stage   | Date_of_Diagnosis | error_message |
      | Tumours | 14-11-null        | Enter a year  |

    Examples: of entering invalid date for February month
      | stage   | Date_of_Diagnosis | error_message |
      | Tumours | 30-02-2012        | Check the day and month are valid  |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_04 @NTS-3157 @E2EUI-1020 @P0 @v_1
  Scenario Outline: NTS-3157:Validate the mandatory input field 'Date of diagnosis' for the Tumour Section
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions fields, select tumour type "<tumour_type>" and leaves date of diagnosis field blank
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of leaving the date of diagnosis field blank
      | stage   | tumour_type              | error_message |
      | Tumours | Solid tumour: metastatic | Enter a year  |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_05 @NTS-3154 @E2EUI-1320 @E2EUI-894 @E2EUI-1549 @E2EUI-1236 @P0 @v_1
  Scenario Outline: NTS-3154: Add a new tumour for an existing patient
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page for the existing patient with tumour list
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | notificationText|
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added    |



  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_06 @NTS-3154 @E2EUI-894 @E2EUI-1549 @P0 @v_1
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
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples:
      | stage   | tumour_type              | presentationType   | searchTerm | notificationText|
      | Tumours | Solid tumour: metastatic | First presentation | test       | Tumour added    |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_07a  @P0 @v_1 @NTS:3171 @E2EUI-2145
  Scenario Outline:NTS:3171:Moving to other section:The user is stopped to navigate away from dynamic questions step from Tumours stage after editing
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
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


  @COMP6_TOC_Tumour  @LOGOUT
    @tumoursPage_07b  @P0 @v_1 @NTS:3171 @E2EUI-2145
  Scenario Outline:NTS:3171:The user is stopped to navigate away from dynamic questions step from Tumours stage after making changes
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
   #  User click on refresh button
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
#     User click on back button
    When the user attempts to navigate away by clicking "<browser_exit2>"
    Then the user sees a prompt alert "<partOfMessage2>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl2>" page
   #  User click on logout button
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit3>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | browser_exit1 | browser_exit2 | browser_exit3 | partialCurrentUrl2 |
      | Tumours | Solid tumour: metastatic | Dismiss            | may not be saved. | unsaved information | tumours/create     | refresh       | back          | logout        | tumours            |

  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_08 @NTS-3172 @E2EUI-1465 @P0 @v_1
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


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_09 @NTS-3174 @E2EUI-1159  @E2EUI-1577 @P0 @v_1
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


  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_10 @NTS-3176 @E2EUI-1171 @P0 @v_1
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


  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_11 @NTS-3190 @E2EUI-1513 @E2EUI-903 @P0 @v_1
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
      | stage   | tumour_type              | presentationType | searchTerm |  updated_tumour_type  |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary |


  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_12 @NTS-3190 @E2EUI-1513 @E2EUI-903 @P0 @v_1
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
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type  |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary|


  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_13 @NTS-3204 @E2EUI-890 @P0 @v_1
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



  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_14 @NTS-3225 @E2EUI-2279 @E2EUI-1434 @P0 @v_1
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
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type  | pageTitle |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary| Edit a tumour|


  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_15 @NTS-3176 @E2EUI-1412 @P0 @v_1
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


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_16 @NTS-3249 @E2EUI-1459 @P0 @v_1
  Scenario Outline: NTS-3249: Fuzzy date on Date of Diagnosis field
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    And the user answers the tumour system specific question fields - Description, Select a tumour type "<tumour_type>" and Pathology Sample ID
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"


    Examples: of filling out the year and leaving the month and day blank
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | null-null-2018    | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |

    Examples: of filling out the month and year and leaving the day blank
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType   | searchTerm | notificationText |
      | Tumours | null-11-2018      | Solid tumour: metastatic | First presentation | test       | Tumour added     |

    Examples: of filling out day, month and year field
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | 10-11-2018        | Solid tumour: metastatic | Unknown          | test       | Tumour added     |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_17 @NTS-3250 @E2EUI-1247 @P0 @v_1
  Scenario Outline: NTS-3250: Verify the presence of pathology Sample Id and check long characters more than 20 can be entered.
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<pathologySampleId>" in the Pathology Sample ID field
    And the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples: of filling out the year and leaving the month and day blank
      | stage   | pathologySampleId                 | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | A12345678912345667890-ABCDEFGHIJK | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_18 @NTS-3252 @E2EUI-1107 @P0 @v_1
  Scenario Outline: NTS-3252: Tumour-list - Indicate any tumour list with incomplete or outstanding mandatory questions
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list
    And the new tumour details added in the tumour list are indicated as in-complete with "<messageColor>" fonts colour
    And the error message "<errorMessage>" is displayed in "<messageColor>" fonts colour in the page

    Examples:
      | stage   | pageTitle               | tumour_type              | notificationText | errorMessage                                           | messageColor |
      | Tumours | Select or edit a tumour | Solid tumour: metastatic | Tumour added     | There is essential information missing from this entry | #dd2509      |