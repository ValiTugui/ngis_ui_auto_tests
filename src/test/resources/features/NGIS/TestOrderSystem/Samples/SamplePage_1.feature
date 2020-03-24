#@regression
#@samplesPage
#@samplesPage2
@TEST_ORDER
@SYSTEM_TEST
Feature: Samples Page -1

  @NTS-32871 @LOGOUT
#    @E2EUI-1945 @E2EUI-1209 @E2EUI-850 @E2EUI-941 @E2EUI-1467 @E2EUI-962 @E2EUI-2782
  Scenario Outline: NTS-3287: Add a Sample - Without a tumour sample type
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      #| Sample type      | State             | Local sample tube ID   | Parent ID            | Tumour description      |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And the "<stage>" stage is marked as Completed

    Examples:

      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | notificationText |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Sample added     |

  @NTS-3287 @LOGOUT
#    @E2EUI-1945 @E2EUI-1209 @E2EUI-850 @E2EUI-962
  Scenario Outline: NTS-3287: Add a Sample - Without a tumour sample type - sample's details are displayed
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
#      | Sample type      | State             | Local sample tube ID   | Parent ID            | Tumour description      |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And on the Manage Samples page, the new sample details are displayed in the sample table list

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState | notificationText |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Saliva      | Sample added     |


  @NTS-3287 @LOGOUT
#    @E2EUI-2330 @E2EUI-870 @E2EUI-1209 @E2EUI-850 @E2EUI-1229 @E2EUI-1039 @E2EUI-941 @E2EUI-1467
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
#      | Sample type      | State             | Local sample tube ID   | Parent ID            | Tumour description      |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And the "<stage2>" stage is marked as Completed
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | sampleType           | sampleTopoMorphyGraphy | notificationText |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Solid tumour sample  | test                   | Sample added     |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Liquid tumour sample | test                   | Sample added     |


  @NTS-3287  @LOGOUT
#    @E2EUI-2330 @E2EUI-870 @E2EUI-1209 @E2EUI-850
  Scenario Outline: NTS-3287: Add a Sample of tumour type - tumour sample - sample's details are displayed
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
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
#      | Sample type      | State             | Local sample tube ID   | Parent ID            | Tumour description      |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
    And on the Manage Samples page, the new sample details are displayed in the sample table list
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-tumour   | sampleState | sampleTopoMorphyGraphy | notificationText |
      | Tumours | Manage samples | Add a sample | Add sample details | Solid tumour sample | Saliva      | test                   | Sample added     |
