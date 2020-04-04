#@regression
#@tumoursPage1
#@tumoursPage
@TEST_ORDER6
@SYSTEM_TEST
Feature: Tumours Page - 5

  @NTS-3174 @LOGOUT
#    @E2EUI-1159 @E2EUI-1577 @E2EUI-1377
  Scenario Outline: NTS-3174:Verify Estimated Date of Diagnosis, Tumour Type and Specimen ID fields are mandatory fields
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the tumours stage is at Add a Tumour page
    When the user clicks the Save and Continue button
    Then the error messages for the tumour mandatory fields are displayed
      | errorMessageHeader                           |
      | Enter a year                                 |
      | Please select the tumour type                |
#      | Histopathology laboratory ID or local sample ID is required. |
      | Histopathology or SIHMDS Lab ID is required. |

    Examples: Tumour type is not selected
      | stage   |
      | Tumours |

  @NTS-3176 @LOGOUT
#    @E2EUI-1171
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
      | descriptionHeader | pathologySampleHeader           | dateDiagnosedHeader | statusHeader |
#      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       |

  @NTS-3190 @LOGOUT
#    @E2EUI-1513 @E2EUI-903
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
      | descriptionHeader | pathologySampleHeader           | dateDiagnosedHeader | statusHeader |
#      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |

    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type   |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary |

  @NTS-3190 @LOGOUT
#    @E2EUI-1513 @E2EUI-903
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