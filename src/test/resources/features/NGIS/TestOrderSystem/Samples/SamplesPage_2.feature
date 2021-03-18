@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@Sample
Feature: Samples Page -2

  @NTS-3272 @Z-LOGOUT
#    @E2EUI-1946 @E2EUI-1239
  Scenario Outline: NTS-3272:E2EUI-1946,1239: Verifying the page titles and sub-title of Manage Samples, Add a Sample, Edit details and Add sample details pages
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
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


  @NTS-3308 @Z-LOGOUT
#    @E2EUI-943 @E2EUI-1050 @E2EUI-1186 @E2EUI-887
  Scenario Outline: NTS-3308:E2EUI-943,1050,1186,887: Add a sample page - sample state field validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user answers all sample questions on Add a Sample page without the "<sampleField1>"
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<errorMessage1>" in "<messageColor>" for the invalid field

    When the user navigates to the "<stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<stage>" button and "<acknowledgeMessage>" it
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user answers all sample questions on Add a Sample page without the "<sampleField2>"
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<errorMessage2>" in "<messageColor>" for the invalid field
#NTS-3308
    When the user navigates to the "<stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<stage>" button and "<acknowledgeMessage>" it
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user answers all sample questions on Add a Sample page without the "<sampleField3>"
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<errorMessage3>" in "<messageColor>" for the invalid field
    Examples:
      | stage   | pageTitle      |partOfMessage        |acknowledgeMessage| pageTitle2  | errorMessage1             |errorMessage2              |errorMessage3              | messageColor | sampleField1 |sampleField2 |sampleField3 |
      | Samples | Manage samples | unsaved information |Accept            |Add a sample | Sample type is required.  |Sample state is required. | Sample ID is required. |#dd2509      | sampleType  | sampleState |sampleID    |



  @NTS-3335 @Z-LOGOUT
#    @E2EUI-1261 @E2EUI-1232 @E2EUI-1476
  Scenario Outline: NTS-3335:E2EUI-1261,1476: Add a Sample page - page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the sub-page title "<subPageTitle>" is displayed on Add a Sample Page
 #NTS-3335
    And the expected sub-set of sample-state values are displayed in the Sample state drop-down
      | sampleStateHeader         |
      | Fresh frozen tumour       |
      | Bone marrow               |
      | Blood (EDTA)              |
      | Tumour fresh fluid        |
      | Saliva                    |
      | Fibroblasts               |
      | Skin biopsy               |
      | Amniotic fluid            |
      | Fetal blood (EDTA)        |
      | Chorionic villus sample   |
      | Fresh tissue (not tumour) |
    And a search icon is displayed inside the Sample state drop down field
    And fields and drops-downs are shown as mandatory with astericks star symbol
    #noinspection NonAsciiCharacters
    And place-holder text is displayed for Sample type, Sample State and SampleID on Add a Sample page
      | labelHeader    | PlaceHolder Text |
      | Sample type ✱  | Select...        |
      | Sample state ✱ | Select...        |
      | Sample ID ✱    | e.g. A1 xxxxx    |
    Examples:
      | stage   | pageTitle      | pageTitle2   | subPageTitle                                                 |
      | Samples | Manage samples | Add a sample | Enter sample information if your local processes require it. |
