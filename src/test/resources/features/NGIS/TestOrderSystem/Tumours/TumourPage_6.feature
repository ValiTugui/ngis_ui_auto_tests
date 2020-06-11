@03-TEST_ORDER1
@SYSTEM_TEST
Feature: Tumours Page - 6

  @NTS-3250 @Z-LOGOUT
#    @E2EUI-1247
  Scenario Outline: NTS-3250:E2EUI-1247: Verify the presence of pathology Sample Id and check long characters more than 20 can be entered.
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user enters "<pathologySampleId>" in the Pathology Sample ID field
    And the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples: of filling out the year and leaving the month and day blank
      | stage   | pathologySampleId                 | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | A12345678912345667890-ABCDEFGHIJK | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |

  @NTS-3171 @Z-LOGOUT
#    @E2EUI-2145
  Scenario Outline:NTS:3171:E2EUI-2145:Moving to other section:The user is stopped to navigate away from dynamic questions step from Tumours stage after editing
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
   # moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | new_stage | acknowledgeMessage | partOfMessage       | partialCurrentUrl1 |
      | Tumours | Solid tumour: metastatic | Samples   | Dismiss            | unsaved information | tumours/new        |

  @NTS-3172 @Z-LOGOUT
#    @E2EUI-1465
  Scenario Outline: NTS-3172:E2EUI-1465:Validate the mandatory input field 'The tumour is' for the Tumour Section
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions without selecting any tumour type
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: Tumour type is not selected
      | stage   | error_message                 |
      | Tumours | Please select the tumour type |