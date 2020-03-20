#@regression
#@samplesPage
#@samplesPage1
@Samples_Tumour
@TEST_ORDER
@SYSTEM_TEST
Feature: Samples Page

  @NTS-3272 @LOGOUT
#    @E2EUI-1946 @E2EUI-1239
  Scenario Outline: NTS-3272: Verifying the page titles and sub-title of Manage Samples, Add a Sample, Edit details and Add sample details pages
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user adds a new tumour
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 1                    |
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the Manage Samples page displays the page title and sub-titles text body
      | pageTitleHeader | subTitleHeader1                                       | subTitleHeader2                                |
      | Manage samples  | Add a sample here if your local processes require it. | You can also continue without adding a sample. |
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the Samples page displays the page title and sub-titles text body
      | pageTitleHeader | subTitleHeader1                                              |
      | Add a sample    | Enter sample information if your local processes require it. |
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the Add Samples details page displays the page title and sub-titles text body
      | pageTitleHeader    | subTitleHeader1                                           |
      | Add sample details | Give details of the sample being linked to this referral. |
    When the user clicks on the Back link
    Then the "<pageTitle4>" page is displayed
    And the Samples page displays the page title and sub-titles text body
      | pageTitleHeader | subTitleHeader1                                              |
      | Edit a sample   | Enter sample information if your local processes require it. |
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the Manage Samples page displays the page title and sub-titles text body
      | pageTitleHeader | subTitleHeader1                                       |
      | Manage samples  | Change existing sample details or add another sample. |
#    Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType          | sampleTopoMorphyGraphy |
      | Tumours | Manage samples | Add a sample | Add sample details | Edit a sample | Solid tumour sample | test                   |

  @NTS-3308 @LOGOUT
#    @E2EUI-943 @E2EUI-1050 @E2EUI-1186 @E2EUI-887
  Scenario Outline: NTS-3308: Add a sample page - sample state field validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user answers all sample questions on Add a Sample page without the "<sampleField>"
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<errorMessage>" in "<messageColor>" for the invalid field

    Examples:
      | stage   | pageTitle      | pageTitle2   | errorMessage                      | messageColor | sampleField |
      | Samples | Manage samples | Add a sample | Sample type is required.          | #dd2509      | sampleType  |
      | Samples | Manage samples | Add a sample | Sample state is required.         | #dd2509      | sampleState |
      | Samples | Manage samples | Add a sample | Local sample tube ID is required. | #dd2509      | sampleID    |

  @NTS-3308 @LOGOUT
#    @E2EUI-943 @E2EUI-2338 @E2EUI-1232
  Scenario Outline: NTS-3308: Add a sample page - verify the sample type drop down list
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the following drop-down values are displayed for Sample types on Add a sample page
      | sampleTypesHeader         |
      | Solid tumour sample       |
      | Liquid tumour sample      |
      | Normal or germline sample |
#      | Abnormal tissue sample - Descoped 24/02/2020 |
#      | Omics sample  - Descoped 24/02/2020            |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3312 @LOGOUT
#    @E2EUI-868 @@E2EUI-1261 @E2EUI-887
  Scenario Outline: NTS-3312: Add a sample page - Validate the mandatory input fields in add a Sample page without filling in the fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user clicks the Save and Continue button
    Then the error messages for the sample mandatory fields on Add a Sample page are displayed
      | labelHeader            | errorMessageHeader                |
      | Sample type ✱          | Sample type is required.          |
      | Sample state ✱         | Sample state is required.         |
      | Local sample tube ID ✱ | Local sample tube ID is required. |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3332 @LOGOUT
#    @E2EUI-1446 @E2EUI-1272
  Scenario Outline: NTS-3332 - Add a Sample page - Verify sample type, sample state and sampleID are display
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the Add a Sample page displays the appropriate field elements - sample type, sample state and sampleID

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3333 @LOGOUT
#    @E2EUI-1252
  Scenario Outline: NTS-3333 - Add a Sample page - verify the help hint-text on Local sample tube ID
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the labels and help hint texts are displayed on Add a Sample page
      | labelHeader            | HintTextHeader                                                                         |
      | Sample type ✱          | None                                                                                   |
      | Sample state ✱         | None                                                                                   |
      | Local sample tube ID ✱ | This could be the block ID, sample ID or nucleic acid ID given at the local laboratory |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |

  @NTS-3335 @LOGOUT
#    @E2EUI-1261 @E2EUI-1476
  Scenario Outline: NTS-3335 - Add a Sample page - page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the sub-page title "<subPageTitle>" is displayed on Add a Sample Page
    And a search icon is displayed inside the Sample state drop down field
    And fields and drops-downs are shown as mandatory with astericks star symbol
    And place-holder text is displayed for Sample type, Sample State and SampleID on Add a Sample page
      | labelHeader            | PlaceHolder Text |
      | Sample type ✱          | Select...        |
      | Sample state ✱         | Select...        |
      | Local sample tube ID ✱ | e.g. A1 xxxxx    |

    Examples:
      | stage   | pageTitle      | pageTitle2   | subPageTitle                                                 |
      | Samples | Manage samples | Add a sample | Enter sample information if your local processes require it. |


  @NTS-3335 @LOGOUT
#   @E2EUI-1261 @E2EUI-1232 @E2EUI-1476
  Scenario Outline: NTS-3335 - Add a Sample page - page layout - verify sample-states drop-down values
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the expected sub-set of sample-state values are displayed in the Sample state drop-down
      | sampleStateHeader   |
      | Fibroblasts         |
      | DNA                 |
      | Saliva              |
      | Fresh frozen tumour |
      | Fresh frozen tissue |


    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3345 @LOGOUT
#    @E2EUI-838 @E2EUI-857
  Scenario Outline: NTS-3345:Edit a non tumour sample type that has already been added to my referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<non-tumour-SampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    Then the new sample is displayed in the landing page
#    Editing sample details
    When the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the "<pageTitle4>" page is displayed
    And the user edits the fields on Edit a Sample page by selecting the sample type "<non-tumour-sampleType-edited>", sample state "<sampleState-edited>" and SampleID
    And the user clicks the Save and Continue button
    When the user clicks on the Back link
    And the user navigates to the "<stage>" stage
    And the success notification is displayed "<notificationText-updated>"
    And the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the new edited sample details are displayed in the edit sample page

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | non-tumour-SampleType     | sampleState | notificationText | non-tumour-sampleType-edited | sampleState-edited | notificationText-updated |
      | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Normal or germline sample | Fibroblasts | Sample added     | Normal or germline sample    | DNA                | Sample updated           |


  @NTS-3345 @LOGOUT
#    @E2EUI-838 @E2EUI-857
  Scenario Outline: NTS-3345: Edit a tumour sample type that has already been added to my referral
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
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    Then the new sample is displayed in the landing page
#   Editing sample details
    When the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the "<pageTitle4>" page is displayed
    And the user edits the fields on Edit a Sample page by selecting the sample type "<sampleType-edited>", sample state "<sampleState-edited>" and SampleID
    And the user clicks the Save and Continue button
    When the user clicks on the Back link
    And the user navigates to the "<stage2>" stage
    And the success notification is displayed "<notificationText-updated>"
    And the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the new edited sample details are displayed in the edit sample page
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType          | sampleState | sampleTopoMorphyGraphy | notificationText | sampleType-edited         | sampleState-edited | notificationText-updated |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Solid tumour sample | Saliva      | test                   | Sample added     | Normal or germline sample | DNA                | Sample updated           |


  @NTS-4734 @NTS-3347 @LOGOUT
#    @E2EUI-1342 @E2EUI-1440
  Scenario Outline: NTS-3374: Adding a child sample to a parent sample
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
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
#  <--- User add child sample - non-sample tumour type---------->
    When the user clicks the Add sample button
    Then the user sees a text below the the Sample-ID on Add a Sample page "Is this sample derived from another sample?"
    And the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user adds a sample as Child sample by selecting a sample row as a Parent Sample on Add a Sample page
    And the user see a tick mark next to the selected parent Sample
    And the user clicks the Save and Continue button
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    Then the new sample is displayed in the landing page
    And on the Manage Samples page, the child sample's details are properly displayed in the sample table list
    #    Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-tumour   | sampleType-non-tumour     | sampleState | sampleTopoMorphyGraphy |
      | Tumours | Manage samples | Add a sample | Add sample details | Solid tumour sample | Normal or germline sample | DNA         | test                   |


  @NTS-3365 @LOGOUT
#    @E2EUI-2359 @E2EUI-1302 @E2EUI-842
  Scenario Outline: NTS-3365: Add a Sample - User can navigate to the Add a tumour page from the tumour sample error message
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user selects a tumour sample type "<sampleType>" from the system questions page on Add a Sample page
    Then the Add tumour error message is displayed below Sample type field "To select this sample type, add a tumour"
    When the user clicks the Add a tumour link from the error message
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<addATumourLink>" button and "<acknowledgeMessage>" it
    Then the "<pageTitle3>" page is displayed

    Examples:

      | stage   | pageTitle      | pageTitle2   | pageTitle3   | sampleType          | partOfMessage                                  | addATumourLink | acknowledgeMessage |
      | Samples | Manage samples | Add a sample | Add a tumour | Solid tumour sample | contains unsaved information. Discard changes? | add a Tumour   | Accept             |

  @NTS-3364 @LOGOUT
#    @E2EUI-2360
  Scenario Outline: NTS-3364: Add a Sample - Verify the link 'Not the Right Tumour' in 'Add a Sample' page.
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
    When the user selects a tumour sample type "<sampleType>" from the system questions page on Add a Sample page
    Then the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user sees a hyper-text link message below the linked tumour details "Not the right tumour?" on Add a Sample page
    When the user clicks the Not the right tumour link below the linked tumour details on Add a Sample page
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<notTheRightTumourLink>" button and "<acknowledgeMessage>" it
    And the "<pageTitle3>" page is displayed
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3              | sampleType          | partOfMessage                                  | notTheRightTumourLink | acknowledgeMessage |
      | Tumours | Manage samples | Add a sample | Select or edit a tumour | Solid tumour sample | contains unsaved information. Discard changes? | Not the right tumour  | Accept             |


  @NTS-3376 @LOGOUT
#    @E2EUI-1490
  Scenario Outline: NTS-3376: Add Sample Details - Sample non-Tumour type -  Verify the fields elements are displayed on Add Sample Details page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the Add a Sample Details displays the appropriate field elements for Sample non-Tumour type - sample collection date and sample comments

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Saliva      |

  @NTS-3376 @LOGOUT
#    @E2EUI-1490
  Scenario Outline:  NTS-3376: Add Sample Details - Sample Tumour type -  Verify the fields elements are displayed on Add Sample Details page
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
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-tumour   | sampleState |
      | Tumours | Manage samples | Add a sample | Add sample details | Solid tumour sample | Saliva      |


  @NTS-3408 @LOGOUT
#    @E2EUI-2143 @E2EUI-2108 @E2EUI-2106 @E2EUI-2098
  Scenario Outline: NTS-3408: Add sample details - Sample Type Tumour "<sampleType-tumour>" - Verify Tumour content value field is mandatory for Only Solid tumour sample
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
    And asterisk "<asterisk>" star symbol is shown as mandatory next to the Tumour content - percentage of malignant field label for only Solid tumour sample
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>" and leaves Tumour content percentage field blank
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "Sample added"
    When the user clicks the Save and Continue button
    Then the "Add notes to this referral" page is displayed
    And the "Notes" stage is selected
    But the "Samples" stage is marked "<stageStatus>"
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test


    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | stageStatus   | sampleType-tumour    | sampleState | asterisk                                                   | sampleTopoMorphyGraphy |
      | Tumours | Manage samples | Add a sample | Add sample details | MandatoryToDo | Solid tumour sample  | Saliva      | Tumour content (percentage of malignant nuclei / blasts) ✱ | test                   |
      | Tumours | Manage samples | Add a sample | Add sample details | Completed     | Liquid tumour sample | DNA         | Tumour content (percentage of malignant nuclei / blasts)   | test                   |


  @NTS-3412 @LOGOUT
#    @E2EUI-2103
  Scenario Outline: NTS-3412:Add sample details - Sample non-tumour type "<sampleType-non-tumour>" - Sample stage is completed even if sample questionnaire is unattended
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
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the user clicks the Save and Continue button
    And the "<stage2>" stage is marked as Completed
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | DNA         |


  @NTS-3416 @LOGOUT
#    @E2EUI-2141 @E2EUI-2440
  Scenario Outline: NTS-3416: Moving to other stage: user is stopped if changes are not saved and try to navigate away from Sample stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    # moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    #   User is stopped from navigating away from Sample Details Page
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
     # moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "samples" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | new_stage | sampleType-non-tumour     | sampleState | acknowledgeMessage | partOfMessage       | partialCurrentUrl1 |
      | Samples | Manage samples | Add a sample | Notes     | Normal or germline sample | Saliva      | Dismiss            | unsaved information | samples/add        |


  @NTS-3416 @LOGOUT
#    @E2EUI-2141
  Scenario Outline: NTS-3416: Refresh, back-button and logout - User is stopped if changes are not saved and try to navigate away from Sample stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID

     #  User click on refresh button
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page

  #     User click on back button - https://jira.extge.co.uk/browse/NTOS-4539
#    No prompt alert is currently being shown when the back button is clicked - 23/12/2019
#    When the user attempts to navigate away by clicking "back"
#    Then the user sees a prompt alert "<partOfMessage2>" after clicking "back" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl2>" page

   #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | sampleType-non-tumour     | sampleState | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | partialCurrentUrl2 |
      | Samples | Manage samples | Add a sample | Normal or germline sample | Saliva      | Dismiss            | may not be saved. | unsaved information | samples/add        | samples            |


  @NTS-3416 @LOGOUT
#    @E2EUI-2440
  Scenario Outline: NTS-3416: Refresh, back-button and logout - User is stopped if changes are not saved and try to navigate away from Add sample details
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page

     #  User click on refresh button
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page

  #     User click on back button - Defect - https://jira.extge.co.uk/browse/NTOS-4539
#    No prompt alert is currently being shown when the back button is clicked - 23/12/2019
#    When the user attempts to navigate away by clicking "back"
#    Then the user sees a prompt alert "<partOfMessage2>" after clicking "back" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl2>" page

   #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | sampleType-non-tumour     | sampleState | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | partialCurrentUrl2 |
      | Samples | Manage samples | Add a sample | Normal or germline sample | DNA         | Dismiss            | may not be saved. | unsaved information | samples            | samples            |


  @NTS-3432 @LOGOUT
#    @E2EUI-1352
  Scenario Outline: NTS-3432: Add a Sample - 'Not the Right Tumour' in 'Add a Sample' page and Selecting a different tumour in 'Select or edit a tumour' page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user adds new tumours
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 |    3                 |
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user selects a tumour sample type "Solid tumour sample" from the system questions page on Add a Sample page
    Then the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user sees a hyper-text link message below the linked tumour details "Not the right tumour?" on Add a Sample page
    When the user clicks the Not the right tumour link below the linked tumour details on Add a Sample page
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<notTheRightTumourLink>" button and "<acknowledgeMessage>" it
    And the "<pageTitle3>" page is displayed
#     selecting a different tumour in Select or edit a tumour page
    And the user select a different tumour list
    Then the user sees a prompt alert "Selecting a different tumour will remove any samples linked to the previously selected tumour." after clicking "<notTheRightTumourLink>" button and "<acknowledgeMessage>" it
    And the different tumour selected is shown with a checked radio button
    And the user clicks the Save and Continue button
#     user is back to Manage Samples Page
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Saliva" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle4>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "Sample added"
    Then the new sample is displayed in the landing page
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test


    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3              | pageTitle4         | partOfMessage                                  | notTheRightTumourLink | acknowledgeMessage |
      | Tumours | Manage samples | Add a sample | Select or edit a tumour | Add sample details | contains unsaved information. Discard changes? | Not the right tumour  | Accept             |


  @NTS-4531 @LOGOUT
#    @E2EUI-1480
  Scenario Outline:NTS-4531:Samples stage (Post Edit Sample)
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle4>" page is displayed
    Then the new edited sample details are displayed in the edit sample page
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage Samples page, the new sample details are displayed in the sample table list

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType-non-tumour     | notificationText |
      | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Normal or germline sample | Sample added     |


   @NTS-4709 @LOGOUT
#     @E2EUI-1023
  Scenario Outline:NTS-4709:Verify Sample Collection date is displayed in Add sample details for non-Tumour Sample type
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
     When the user navigates to the "<stage>" stage
     Then the "<pageTitle>" page is displayed
     When the user clicks the Add sample button
     Then the "<pageTitle2>" page is displayed
     When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
     And the user clicks the Save and Continue button
     Then the "<pageTitle3>" page is displayed
     And the Add a Sample Details displays the appropriate field elements for Sample non-Tumour type - sample collection date and sample comments
     And the Sample Collection date field is displayed with label "Sample collection date"
     And the user is able to enter date in the Sample Collection date field

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Fibroblasts |

