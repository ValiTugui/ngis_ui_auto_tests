#@regression
#@tumoursPage2
#@tumoursPage
@TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Tumours Page - 2

  @NTS-3249 @LOGOUT
#    @E2EUI-1459
  Scenario Outline: NTS-3249: Fuzzy date "<Date_of_Diagnosis>" on Date of Diagnosis field
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    And the user answers the tumour system specific question fields - Description, Select a tumour type "<tumour_type>" and Pathology Sample ID
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples: of filling out the year and leaving the month and day blank
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | null-null-2018    | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |

    Examples: of filling out the month and year and leaving the day blank
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType   | searchTerm | notificationText |
      | Tumours | null-11-2018      | Solid tumour: metastatic | First presentation | test       | Tumour added     |

    Examples: of filling out day, month and year field
      | stage   | Date_of_Diagnosis | tumour_type              | presentationType | searchTerm | notificationText |
      | Tumours | 10-11-2018        | Solid tumour: metastatic | Unknown          | test       | Tumour added     |


  @NTS-3250 @LOGOUT
#    @E2EUI-1247
  Scenario Outline: NTS-3250: Verify the presence of pathology Sample Id and check long characters more than 20 can be entered.
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
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


  @NTS-3252 @LOGOUT
#    @E2EUI-1107 @E2EUI-1096
  Scenario Outline: NTS-3252: Tumour-list - Indicate any tumour list with incomplete or outstanding mandatory questions
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    And the new tumour is added as a list, with a checked radio button and a chevron right arrow icon
    And on the select or edit a tumour page, the new tumour details are displayed in the tumour table list
    And the new tumour details added in the tumour list are indicated as in-complete with "<messageColor>" fonts colour
    And the error message "<errorMessage>" is displayed in "<messageColor>" fonts colour in the page

    Examples:
      | stage   | pageTitle               | tumour_type              | notificationText | errorMessage                                           | messageColor |
      | Tumours | Select or edit a tumour | Solid tumour: metastatic | Tumour added     | There is essential information missing from this entry | #dd2509      |

  @NTS-3259 @LOGOUT
#    @E2EUI-1075
  Scenario Outline: NTS-3259:Back link button - Create referral navigation component - Tumours
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the "<pageTitle>" page is displayed
    And the success notification is displayed "<notificationText>"
    When user clicks add a new tumour link
    And the "<pageTitle2>" page is displayed
    When the user clicks on the Back link
    Then the "<pageTitle>" page is displayed

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | notificationText | pageTitle               | pageTitle2   |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     | Select or edit a tumour | Add a tumour |