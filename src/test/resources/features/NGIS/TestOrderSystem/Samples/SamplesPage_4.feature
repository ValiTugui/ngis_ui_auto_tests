@03-TEST_ORDER
@SYSTEM_TEST
Feature: Samples Page -4

  @NTS-3412 @Z-LOGOUT
#    @E2EUI-2103
  Scenario Outline: NTS-3412:Add sample details - Sample non-tumour type "<sampleType-non-tumour>" - Sample stage is completed even if sample questionnaire is unattended
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user adds a new tumour
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 1                    |
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the user clicks the Save and Continue button
    And the "<stage2>" stage is marked as Completed
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test

    Examples:
      | stage   | stage2  | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Saliva         |

  @NTS-3416 @Z-LOGOUT
#    @E2EUI-2141 @E2EUI-2440
  Scenario Outline: NTS-3416: Moving to other stage: user is stopped if changes are not saved and try to navigate away from Sample stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    # moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    #   User is stopped from navigating away from Sample Details Page
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
     # moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "samples" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | new_stage | sampleType-non-tumour     | sampleState | acknowledgeMessage | partOfMessage       | partialCurrentUrl1 |
      | Samples | Manage samples | Add a sample | Notes     | Normal or germline sample | Saliva      | Dismiss            | unsaved information | samples/new        |


  @NTS-3416 @Z-LOGOUT
#    @E2EUI-2141
  Scenario Outline: NTS-3416: Refresh, back-button and logout - User is stopped if changes are not saved and try to navigate away from Sample stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID

     #  User click on refresh button
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page

  #     User click on back button - https://jira.extge.co.uk/browse/NTOS-4539
#    No prompt alert is currently being shown when the back button is clicked - 23/12/2019
#    When the user attempts to navigate away by clicking "back"
#    Then the user sees a prompt alert "<partOfMessage2>" after clicking "back" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl2>" page

   #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | sampleType-non-tumour     | sampleState | acknowledgeMessage | partOfMessage1    | partialCurrentUrl1 |
      | Samples | Manage samples | Add a sample | Normal or germline sample | Saliva      | Dismiss            | may not be saved. | samples/new       |


  @NTS-3416 @Z-LOGOUT
#    @E2EUI-2440
  Scenario Outline: NTS-3416: Refresh, back-button and logout - User is stopped if changes are not saved and try to navigate away from Add sample details
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page

     #  User click on refresh button
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page

  #     User click on back button - Defect - https://jira.extge.co.uk/browse/NTOS-4539
#    No prompt alert is currently being shown when the back button is clicked - 23/12/2019
#    When the user attempts to navigate away by clicking "back"
#    Then the user sees a prompt alert "<partOfMessage2>" after clicking "back" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl2>" page

   #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | pageTitle      | pageTitle2   | sampleType-non-tumour     | sampleState         | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | partialCurrentUrl2 |
      | Samples | Manage samples | Add a sample | Normal or germline sample | Fresh frozen tumour | Dismiss            | may not be saved. | unsaved information | samples            | samples            |


  @NTS-3432 @Z-LOGOUT
#    @E2EUI-1352
  Scenario Outline: NTS-3432: Add a Sample - 'Not the Right Tumour' in 'Add a Sample' page and Selecting a different tumour in 'Select or edit a tumour' page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    And the user adds new tumours
      | TumourTypeHeader         | PresentationTypeHeader | SnomedCTSearchHeader | NumberOfTumoursAdded |
      | Solid tumour: metastatic | First presentation     | test                 | 3                    |
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user selects a tumour sample type "Solid tumour sample" from the system questions page on Add a Sample page
    Then the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user sees a hyper-text link message below the linked tumour details "Not the right tumour?" on Add a Sample page
    When the user clicks the Not the right tumour link below the linked tumour details on Add a Sample page
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<notTheRightTumourLink>" button and "<acknowledgeMessage>" it
    And the "<pageTitle3>" page is displayed
#     selecting a different tumour in Select or edit a tumour page
    And the user select a different tumour list
    Then the user sees a prompt alert "Selecting a different tumour will remove any samples linked to the previously selected tumour." after clicking "<notTheRightTumourLink>" button and "<acknowledgeMessage>" it
    And the different tumour selected is shown with a checked radio button
    And the user clicks the Save and Continue button
#     user is back to Manage Samples Page
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Saliva" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle4>" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "Sample added"
    Then the new sample is displayed in the landing page
#  Tumour Description setter is reset after each Sample Scenario test that uses Tumour Description getter
    And the Tumour description value is reset after test


    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3              | pageTitle4         | partOfMessage                                  | notTheRightTumourLink | acknowledgeMessage |
      | Tumours | Manage samples | Add a sample | Select or edit a tumour | Add sample details | contains unsaved information. Discard changes? | Not the right tumour  | Accept             |

  @NTS-4531 @Z-LOGOUT
#    @E2EUI-1480
  Scenario Outline:NTS-4531:Samples stage (Post Edit Sample)
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle4>" page is displayed
    Then the new edited sample details are displayed in the edit sample page
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    When the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page
    And the user clicks the Save and Continue button
    And the success notification is displayed "<notificationText>"
    Then the "<pageTitle>" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage Samples page, the new sample details are displayed in the sample table list

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | pageTitle4    | sampleType-non-tumour     | notificationText |
      | Samples | Manage samples | Add a sample | Add sample details | Edit a sample | Normal or germline sample | Sample added     |


  @NTS-4709 @Z-LOGOUT
#     @E2EUI-1023
  Scenario Outline:NTS-4709:Verify Sample Collection date is displayed in Add sample details for non-Tumour Sample type
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the Add a Sample Details displays the appropriate field elements for Sample non-Tumour type - sample collection date and sample comments
    And the Sample Collection date field is displayed with label "Sample collection date"
    And the user is able to enter date in the Sample Collection date field

    Examples:
      | stage   | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     | sampleState |
      | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample | Fibroblasts |

