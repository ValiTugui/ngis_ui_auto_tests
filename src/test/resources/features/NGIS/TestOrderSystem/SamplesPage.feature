@regression
@regression_set2
@samplesPage
Feature: Samples Page

  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_01 @NTS-3272 @E2EUI-1946 @P0 @v_1
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

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType          | sampleTopoMorphyGraphy |
      | Tumours | Manage samples | Add a sample | Add sample details | Edit a sample | Solid tumour sample | test                   |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_02 @NTS-3287 @E2EUI-1945 @E2EUI-1209 @E2EUI-850 @P0 @v_1
  Scenario Outline: NTS-3287: Add a Sample - Without a tumour sample type
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Local lab ID           | Parent ID            | Tumour description      |
    And the "<stage>" stage is marked as Completed

    Examples:

      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType                | notificationText |
      | Samples | Manage samples | Add a sample | Add sample details | Omics sample              | Sample added     |
      | Samples | Manage samples | Add a sample | Add sample details | Abnormal tissue sample    | Sample added     |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Sample added     |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_03 @NTS-3287 @E2EUI-2330 @E2EUI-870 @E2EUI-1209 @E2EUI-850 @P0 @v_1
  Scenario Outline: NTS-3287: Add a Sample of tumour type - tumour sample
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
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Local lab ID           | Parent ID            | Tumour description      |
    And the "<stage2>" stage is marked as Completed

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | sampleType           | sampleTopoMorphyGraphy | notificationText |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Solid tumour sample  | test                   | Sample added     |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Liquid tumour sample | test                   | Sample added     |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_04  @NTS-3308 @E2EUI-943 @E2EUI-1050 @E2EUI-1186 @P0 @v_1
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
      | stage   | pageTitle      | pageTitle2   | errorMessage                                 | messageColor | sampleField |
      | Samples | Manage samples | Add a sample | Sample type is required.                     | #dd2509      | sampleType  |
      | Samples | Manage samples | Add a sample | Sample state is required.                    | #dd2509      | sampleState |
      | Samples | Manage samples | Add a sample | Sample ID from local laboratory is required. | #dd2509      | sampleID    |

  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_05  @NTS-3308 @E2EUI-943 @E2EUI-2338 @E2EUI-1232 @P0 @v_1
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
      | Abnormal tissue sample    |
      | Omics sample              |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_06 @NTS-3312  @E2EUI-868 @@E2EUI-1261 @P0 @v_1
  Scenario Outline: NTS-3312: Add a sample page - Validate the mandatory input fields in add a Sample page without filling in the fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user clicks the Save and Continue button
    Then the error messages for the sample mandatory fields on Add a Sample page are displayed
      | labelHeader                       | errorMessageHeader                           |
      | Sample type ✱                     | Sample type is required.                     |
      | Sample state ✱                    | Sample state is required.                    |
      | Sample ID from local laboratory ✱ | Sample ID from local laboratory is required. |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_07  @P0 @v_1 @NTS-3332 @E2EUI-1446 @E2EUI-1272
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


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_08  @P0 @v_1 @NTS-3333 @E2EUI-1252
  Scenario Outline: NTS-3333 - Add a Sample page - verify the help hint-text on Sample ID from local laboratory
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the labels and help hint texts are displayed on Add a Sample page
      | labelHeader                       | HintTextHeader                                                                         |
      | Sample type ✱                     | None                                                                                   |
      | Sample state ✱                    | None                                                                                   |
      | Sample ID from local laboratory ✱ | This could be the block ID, sample ID or nucleic acid ID given at the local laboratory |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |

  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_09 @NTS-3335 @P0 @v_1 @E2EUI-1261 @E2EUI-1476
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
      | labelHeader                       | PlaceHolder Text |
      | Sample type ✱                     | Select...        |
      | Sample state ✱                    | Select...        |
      | Sample ID from local laboratory ✱ | e.g. A1 xxxxx    |

    Examples:
      | stage   | pageTitle      | pageTitle2   | subPageTitle                                                 |
      | Samples | Manage samples | Add a sample | Enter sample information if your local processes require it. |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_10 @NTS-3335 @P0 @v_1 @E2EUI-1261 @E2EUI-1232 @E2EUI-1476
  Scenario Outline: NTS-3335 - Add a Sample page - page layout - verify sample-states drop-down values
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the expected sub-set of sample-state values are displayed in the Sample state drop-down
      | sampleStateHeader   |
      | Urine               |
      | DNA                 |
      | Buccal swab         |
      | Fresh frozen tumour |
      | Fresh frozen tissue |


    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_11 @NTS-3345 @P0 @v_1 @E2EUI-838 @E2EUI-857
  Scenario Outline: NTS-3345:Edit a non tumour sample type that has already been added to my referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
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
    And the user edits the fields on Edit a Sample page by selecting the sample type "<sampleType-edited>", sample state "<sampleState-edited>" and SampleID
    And the user clicks the Save and Continue button
    When the user clicks on the Back link
    And the user navigates to the "<stage>" stage
    And the success notification is displayed "<notificationText-updated>"
    And the user selects the existing sample from the landing page by clicking on the chevron right arrow icon
    Then the new edited sample details are displayed in the edit sample page

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType   | sampleState | notificationText | sampleType-edited      | sampleState-edited | notificationText-updated |
      | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Omics sample | Urine       | Sample added     | Abnormal tissue sample | Buccal swab        | Sample updated           |


  @COMP7_TOC_Samples @LOGOUT
    @samplesPage_12 @NTS-3345 @P0 @v_1 @E2EUI-838 @E2EUI-857
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

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType          | sampleState | sampleTopoMorphyGraphy | notificationText | sampleType-edited      | sampleState-edited | notificationText-updated |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Solid tumour sample | Urine       | test                   | Sample added     | Abnormal tissue sample | Buccal swab        | Sample updated           |

