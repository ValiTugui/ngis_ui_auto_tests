@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@Sample

Feature: Samples Page -3

  @NTS-3365 @Z-LOGOUT
#    @E2EUI-2359 @E2EUI-1302 @E2EUI-842
  Scenario Outline: NTS-3365: Add a Sample - User can navigate to the Add a tumour page from the tumour sample error message
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
     ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user selects a tumour sample type "<sampleType>" from the system questions page on Add a Sample page
    And the user clicks the Save and Continue button
    Then the Add tumour error message is displayed below Sample type field "To select this sample type, add a tumour"
    When the user clicks the Add a tumour link from the error message
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<addATumourLink>" button and "<acknowledgeMessage>" it
    Then the "<pageTitle3>" page is displayed
    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3   | sampleType          | partOfMessage                                  | addATumourLink | acknowledgeMessage |
      | Samples | Manage samples | Add a sample | Add a tumour | Solid tumour sample | contains unsaved information. Discard changes? | add a Tumour   | Accept             |


  @NTS-3408 @Z-LOGOUT
#    @E2EUI-2143 @E2EUI-2108 @E2EUI-2106 @E2EUI-2098
  Scenario Outline: NTS-3408: Add sample details - Sample Type Tumour "<sampleType-tumour>" - Verify Tumour content value field is mandatory for Only Solid tumour sample
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user is navigated to a page with title Add a requesting organisation
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
    #Then the "Add notes to this referral" page is displayed
    Then the "Add clinical notes" page is displayed
    And the "Notes" stage is selected
    But the "Samples" stage is marked "<stageStatus>"
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test
    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | stageStatus   | sampleType-tumour    | sampleState        | asterisk                                                   | sampleTopoMorphyGraphy |
      | Tumours | Manage samples | Add a sample | Add sample details | MandatoryToDo | Solid tumour sample  | Saliva             | Tumour content (percentage of malignant nuclei / blasts) ✱ | test                   |
      | Tumours | Manage samples | Add a sample | Add sample details | Completed     | Liquid tumour sample | Tumour fresh fluid | Tumour content (percentage of malignant nuclei / blasts)   | test                   |