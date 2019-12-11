@regression
@regression_set1
@questionnaire
Feature: Component name - Questionnaire


  @E2EUI-1602  @NTS-3266  @P0 @v_1 @COMP6_TO_ClinicalQuestions @LOGOUT @BVT_P0
  Scenario Outline: NTS-3266 - Tumour and Sample Questionnaire -  Dynamic questions label changes
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
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



