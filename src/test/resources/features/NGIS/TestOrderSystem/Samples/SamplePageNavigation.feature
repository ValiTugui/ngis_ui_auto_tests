@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@Sample
Feature: Samples Page Navigation

  @NTS-7158 @NTOS-5483 @Z-LOGOUT
  Scenario Outline: NTS-7158: Validate Save and continue / Continue button through all the pages of Samples stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    ##Tumour
    When the user navigates to the "Tumours" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user updates the page "<pageTitle>" with "<SamplesQuestionnaireUpdated>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user selects the existing sample on the landing page by clicking on the chevron right arrow icon
    Then the user is navigated to a page with title Edit a sample
    And the user should be able to see "Continue" button
    When the user updates the stage "Samples" with "<SamplesUpdated>"
    Then the user should be able to see "Save and continue" button
    When the user updates the stage "Samples" with "<SamplesUpdated1>"
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user should be able to see "Save and continue" button
    When the user updates the page "<pageTitle>" with "<SamplesQuestionnaireUpdated1>"
    Then the user should be able to see "Save and continue" button
    When the user updates the page "<pageTitle>" with "<SamplesQuestionnaireUpdated2>"
    Then the user should be able to see "Save and continue" button
    # Steps are same for clicking on continue / Save and continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the user should be able to see "Continue" button
    Examples:
      | tumour_type              | presentationType | sampleType          | sampleState        | SamplesUpdated          | SamplesUpdated1                | pageTitle          | SamplesQuestionnaireUpdated | SamplesQuestionnaireUpdated1 | SamplesQuestionnaireUpdated2 |
      | Solid tumour: metastatic | Recurrence       | Solid tumour sample | Tumour fresh fluid | SampleState=Bone marrow | SampleState=Tumour fresh fluid | Add sample details | SampleComments=Test         | SampleComments=Test1         | SampleComments=Test          |