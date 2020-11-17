@03-TEST_ORDER
@SYSTEM_TEST
Feature: Samples Page -7

  @NTS-4734 @Z-LOGOUT
#    @E2EUI-1342 @E2EUI-1440
  Scenario Outline: NTS-3374: Adding a child sample to a parent sample
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
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
      | Tumours | Manage samples | Add a sample | Add sample details | Solid tumour sample | Normal or germline sample | Saliva      | test                   |

  @NTS-3376 @Z-LOGOUT
#    @E2EUI-1490
  Scenario Outline: NTS-3376: Add Sample Details - Sample non-Tumour type -  Verify the fields elements are displayed on Add Sample Details page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
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

  @NTS-3376 @Z-LOGOUT
#    @E2EUI-1490
  Scenario Outline:  NTS-3376: Add Sample Details - Sample Tumour type -  Verify the fields elements are displayed on Add Sample Details page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
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