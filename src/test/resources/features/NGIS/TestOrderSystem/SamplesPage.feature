@regression
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
    @samplesPage_02 @NTS-3287 @E2EUI-1945 @P0 @v_1
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
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    Then the new sample is displayed in the landing page
    And the "<stage2>" stage is marked as Completed

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | sampleType          | sampleTopoMorphyGraphy | notificationText |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Solid tumour sample | test                   | Sample added     |