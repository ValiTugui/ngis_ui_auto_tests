#@regression
#@CancerQuestionnaire
@03-TEST_ORDER
@SYSTEM_TEST
Feature: ClinicalQuestions 12 - Tumour Questionnaire

  @NTS-3266 @Z-LOGOUT
#    @E2EUI-1602
  Scenario Outline: NTS-3266 - Tumour and Sample Questionnaire -  Dynamic questions label changes
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage1>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the "<pageTitle>" page is displayed
    Then the Tumour page has the label text displayed as "<dynamicQuestionsLabel>"
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions "<tumour_type>" for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    And the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage1>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
    And the user clicks the Save and Continue button
    When the user clicks the Add sample button
    And the user adds a tumour sample by providing sample type "<sampleType>"
    And the "<sampleDynamicQuestionsPageTitle>" page is displayed
    And the Sample page has the label text is shown as "<sampleDynamicQuestionsLabel>"
    And the user answers the sample dynamic questions by providing topography "<topography>" morphology "<morphology>"
    Then the "<stage2>" stage is marked as Completed

    Examples:
      | stage1  | stage2  | tumour_type              | pageTitle                          | dynamicQuestionsLabel                   | presentationType   | searchTerm | notificationText | sampleDynamicQuestionsPageTitle | sampleDynamicQuestionsLabel                              | sampleType           | topography | morphology |
      | Tumours | Samples | Solid tumour: metastatic | Answer questions about this tumour | Working diagnosis/morphology (SnomedCT) | First presentation | test       | Tumour added     | Add sample details              | Tumour content (percentage of malignant nuclei / blasts) | Liquid tumour sample | test       | test       |

  @NTS-4728 @Z-LOGOUT
#    @E2EUI-970
  Scenario Outline: NTS-4728 -  Add Sample Details - Sample morphology
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user adds a new tumour
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 1                    |
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-tumour>", sample state "<sampleState>" and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the Add a Sample Details displays the appropriate field elements for Sample Tumour type - Sample topography, morphology, Tumour content, number of slides, collection date and sample comments
    And the Sample page has the label text is shown as "<sampleDynamicQuestionsLabel>"
    And the user answers the sample dynamic questions by providing topography "<topography>" morphology "<morphology>"
    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-tumour   | sampleState | sampleDynamicQuestionsLabel                                | topography                               | morphology     |
      | Tumours | Manage samples | Add a sample | Add sample details | Solid tumour sample | Fibroblasts | Tumour content (percentage of malignant nuclei / blasts) ✱ | All teeth, gums and supporting structures | Eccrine poroma |

  @NTS-4728 @Z-LOGOUT
#    @E2EUI-1971
  Scenario Outline: NTS-4728 - Selecting / deselecting a tumour will  not prompt the 'Tumour updated' message
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    Then the "<patientDetails>" stage is marked as Completed
    When the user navigates to the "<tumours>" stage
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    Then the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page for the existing patient with tumour list
    And user clicks add a new tumour link
    And the user adds a new tumour
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 2                    |
    Then the new tumour is displayed in the landing page for the existing patient with tumour list
    And the user selects the existing tumour from the tumour landing page
    Then the user should not see any error message after selecting the tumour

    Examples:
      | patientDetails  | tumours |
      | Patient details | Tumours |

  @NTS-3499 @Z-LOGOUT
#    @E2EUI-1949
  Scenario Outline: NTS-3499: Update guidance on Tumours stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M89 | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
    And the user clicks the Save and Continue button
    And information text are displayed on the select or edit a tumour page
      | informationTextHeader                           |
      | Only one tumour can be tested in each referral. |
      | If the tumour to be tested is:                  |
      | not shown                                       |
      | a metastasis of one that is shown               |
      | you must add a new tumour then select it.       |
    And the user sees the Save and Continue button
    Then the user should be able to see that the "<text>" is not present

    Examples:
      | stage   | text                                                                                                  |
      | Tumours | If the tumour to be tested is shown but metastatic or not shown, add a new tumour to this test order. |
