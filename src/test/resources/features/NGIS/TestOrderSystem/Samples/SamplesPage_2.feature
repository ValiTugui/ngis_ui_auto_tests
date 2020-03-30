#@regression
#@samplesPage
#@samplesPage1
@TEST_ORDER
@SYSTEM_TEST
Feature: Samples Page -2

  @NTS-3272 @LOGOUT
#    @E2EUI-1946 @E2EUI-1239
  Scenario Outline: NTS-3272: Verifying the page titles and sub-title of Manage Samples, Add a Sample, Edit details and Add sample details pages
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
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
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user answers all sample questions on Add a Sample page without the "<sampleField>"
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<errorMessage>" in "<messageColor>" for the invalid field

    Examples:
      | stage   | pageTitle      | pageTitle2   | errorMessage              | messageColor | sampleField |
      | Samples | Manage samples | Add a sample | Sample type is required.  | #dd2509      | sampleType  |
      | Samples | Manage samples | Add a sample | Sample state is required. | #dd2509      | sampleState |
      | Samples | Manage samples | Add a sample | Sample ID is required.    | #dd2509      | sampleID    |

  @NTS-3308 @LOGOUT
#    @E2EUI-943 @E2EUI-2338 @E2EUI-1232
  Scenario Outline: NTS-3308: Add a sample page - verify the sample type drop down list
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
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
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user clicks the Save and Continue button
    Then the error messages for the sample mandatory fields on Add a Sample page are displayed
      | labelHeader    | errorMessageHeader        |
      | Sample type ✱  | Sample type is required.  |
      | Sample state ✱ | Sample state is required. |
      | Sample ID ✱    | Sample ID is required.    |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3332 @LOGOUT
#    @E2EUI-1446 @E2EUI-1272
  Scenario Outline: NTS-3332 - Add a Sample page - Verify sample type, sample state and sampleID are display
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
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
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the labels and help hint texts are displayed on Add a Sample page
      | labelHeader    | HintTextHeader                                                                         |
      | Sample type ✱  | None                                                                                   |
      | Sample state ✱ | None                                                                                   |
      | Sample ID ✱    | This could be the block ID, sample ID or nucleic acid ID given at the local laboratory |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |

  @NTS-3335 @LOGOUT
#    @E2EUI-1261 @E2EUI-1476
  Scenario Outline: NTS-3335 - Add a Sample page - page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the sub-page title "<subPageTitle>" is displayed on Add a Sample Page
    And a search icon is displayed inside the Sample state drop down field
    And fields and drops-downs are shown as mandatory with astericks star symbol
    And place-holder text is displayed for Sample type, Sample State and SampleID on Add a Sample page
      | labelHeader    | PlaceHolder Text |
      | Sample type ✱  | Select...        |
      | Sample state ✱ | Select...        |
      | Sample ID ✱    | e.g. A1 xxxxx    |

    Examples:
      | stage   | pageTitle      | pageTitle2   | subPageTitle                                                 |
      | Samples | Manage samples | Add a sample | Enter sample information if your local processes require it. |


  @NTS-3335 @LOGOUT
#   @E2EUI-1261 @E2EUI-1232 @E2EUI-1476
  Scenario Outline: NTS-3335 - Add a Sample page - page layout - verify sample-states drop-down values
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the expected sub-set of sample-state values are displayed in the Sample state drop-down
      | sampleStateHeader       |
      | Fibroblasts             |
      | Fresh frozen tumour     |
      | Tumour fresh fluid      |
      | Bone marrow             |
      | Blood (EDTA)            |
      | Amniotic fluid          |
      | Chorionic villus sample |
      | Fetal blood (EDTA)      |
      | Saliva                  |
      | Fresh frozen tissue     |
      | Skin biopsy             |


    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3345 @LOGOUT
#    @E2EUI-838 @E2EUI-857
  Scenario Outline: NTS-3345:Edit a non tumour sample type that has already been added to my referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
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
      | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Normal or germline sample | Fibroblasts | Sample added     | Normal or germline sample    | Bone marrow        | Sample updated           |


  @NTS-3345 @LOGOUT
#    @E2EUI-838 @E2EUI-857
  Scenario Outline: NTS-3345: Edit a tumour sample type that has already been added to my referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
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
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Solid tumour sample | Saliva      | test                   | Sample added     | Normal or germline sample | Bone marrow        | Sample updated           |