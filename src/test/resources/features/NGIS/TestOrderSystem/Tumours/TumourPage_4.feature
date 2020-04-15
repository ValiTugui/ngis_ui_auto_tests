#@regression
#@tumoursPage1
#@tumoursPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Tumours Page - 4

 @NTS-3204 @Z-LOGOUT
#    @E2EUI-890 @E2EUI-1026
  Scenario Outline: NTS-3204:E2EUI-890,1026:Edit a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
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
    When the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    Then the user is navigated to a page with title Edit a tumour
    And an information "<information>" is displayed that a test cannot start without a tumour

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | information                                                                                              |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |

  @NTS-3225 @Z-LOGOUT
#    @E2EUI-2279 @E2EUI-1434
  Scenario Outline: :NTS-3225:E2EUI-2279,1434: Edit a tumour page - The saved changes are displayed in the Edit a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
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

  @NTS-3176 @Z-LOGOUT
#    @E2EUI-1412
  Scenario Outline: NTS-3176:E2EUI-1412: Select or edit a tumour page - Added Tumour is displayed as a list on Select or edit a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
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
      | you must add a new tumour then select it.       |
    And on the select or edit a tumour page, the tumour table list shows the column names
      | descriptionHeader | pathologySampleHeader           | dateDiagnosedHeader | statusHeader |
#      | Description       | Histopathology laboratory ID or local sample ID | Date diagnosed      | Status       |
      | Description       | Histopathology or SIHMDS Lab ID | Date diagnosed      | Status       |
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And Save and Continue button is displayed
#
    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |


  @NTS-3431 @Z-LOGOUT
#    @E2EUI-997
  Scenario Outline:NTS-3431:E2EUI-997:The Tumours stage is marked 'Mandatory ToDo' when not completed and marked 'Completed' when all tumour field completed
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
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
      | stage   | tumour_type              | presentationType   | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | First presentation | test       | Tumour added     |


  @NTS-4503 @Z-LOGOUT
#    @E2EUI-1130
  Scenario Outline:NTS-4503:E2EUI-1130: Add Tumour Page - Description field - maximum length validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc
    When the user attempts to fill in the Tumour Description "<TumourDescription>" with data that exceed the maximum data allowed 45
    Then the user is prevented from entering data that exceed that allowable maximum data 45 in the "TumourDescription" field

    Examples:
      | stage   | TumourDescription                                  |
      | Tumours | 12345678901234567890123456789012345678901234567890 |


  @NTS-4757 @Z-LOGOUT
#    @E2EUI-1339
  Scenario Outline: NTS-4757:E2EUI-1339: Add Tumour page error validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user clicks the Save and Continue button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                       | errorMessageHeader                           | messageColourHeader |
      | Date of diagnosis ✱               | Enter a year                                 | #dd2509             |
      | The tumour is... ✱                | Please select the tumour type                | #dd2509             |
#      | Histopathology laboratory ID or local sample ID ✱ | Histopathology laboratory ID or local sample ID is required. | #dd2509             |
      | Histopathology or SIHMDS Lab ID ✱ | Histopathology or SIHMDS Lab ID is required. | #dd2509             |

    Examples:
      | stage   | pageTitle    |
      | Tumours | Add a tumour |


  @NTS-4829 @Z-LOGOUT
#    @E2EUI-1758
  Scenario Outline:NTS-4829:E2EUI-1758:Update validation in Estimated Date of Diagnosis to account for birth date on Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples:
      | stage   | Date_of_Diagnosis                                | error_message                                     |
      | Tumours | Month_is_more_than_9_months_before_date_of_birth | Cannot be more than 9 months before date of birth |
      | Tumours | Date_is_more_than_9_months_before_date_of_birth  | Cannot be more than 9 months before date of birth |
      | Tumours | Year_is_more_than_9_months_before_date_of_birth  | Cannot be more than 9 months before date of birth |

  @NTS-3487 @Z-LOGOUT
#    @E2EUI-2144 @E2EUI-2097
  Scenario Outline: NTS-3487:E2EUI-2144,2097: Change 'Tumour Content' display value
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M185 | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Blood (EDTA)" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    Then asterisk "<TumourContentPercentageOfMalignant>" star symbol is shown as mandatory next to the Tumour content - percentage of malignant field label for only Solid tumour sample

    Examples:
      | stage   | tumour_type              | presentationType   | TumourContentPercentageOfMalignant                         |
      | Tumours | Solid tumour: metastatic | First presentation | Tumour content (percentage of malignant nuclei / blasts) ✱ |
