@TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Samples Page -5

  @NTS-5070 @LOGOUT
    #@E2EUI-1241
  Scenario Outline: NTS-5070  :  User is completing a referral and wants to add a new sample to a referral
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<TumourStage>" stage
    And the user adds a new tumour
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 1                    |
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    ###Adding the first sample which is of tumour type.
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-tumour>", sample state "<sampleState>" and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"<sampleTopoMorphyGraphy>"
    And the user clicks the Save and Continue button
    Then the success notification is displayed "<notificationText>"
    And the user is navigated to a page with title Manage samples
    And on the Manage Samples page, the new sample details are displayed in the sample table list
    ###Adding second sample which is of non-tumour type
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    Then the success notification is displayed "<notificationText>"
    And the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeade | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Sample ID             | Parent ID            | Tumour description      |
    And the "<SampleStage>" stage is marked as Completed
    And the user is navigated to a page with title Manage samples
    And the user sees the Add sample button to add additional samples

    Examples:
      | TumourStage | SampleStage | sampleType-tumour   | sampleState | sampleTopoMorphyGraphy | sampleType-non-tumour     | notificationText |
      | Tumours     | Samples     | Solid tumour sample | Saliva      | test                   | Normal or germline sample | Sample added     |