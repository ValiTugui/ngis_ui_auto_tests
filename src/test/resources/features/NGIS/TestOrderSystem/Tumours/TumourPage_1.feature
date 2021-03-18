#@regression
@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: TestOrder - Tumours Page - 1

  @NTS-3165 @NTS-3157 @NTS-3241 @NTS-3170 @NTS-3171 @NTS-4829 @NTS-4757 @NTS-3172 @Z-LOGOUT
#   @E2EUI-953 @E2EUI-823 @E2EUI-1120 @E2EUI-1026 @E2EUI-1515 @E2EUI-1020 @E2EUI-1576 @E2EUI-1410 @E2EUI-1356
#   @E2EUI-1699 @NTS-6342 @E2EUI-2018 @E2EUI-1840 @E2EUI-1350 @E2EUI-1486 @E2EUI-1459 @E2EUI-1846 @E2EUI-2145
#   @E2EUI-1758 @E2EUI-1339 @E2EUI-1465
  Scenario Outline: NTS-3165:E2EUI-953:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the user is navigated to a page with title Add a tumour
    And the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc
    #NTS-4757
    And the user clicks the Save and Continue button
    #noinspection NonAsciiCharacters
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                       | errorMessageHeader                           | messageColourHeader |
      | Date of diagnosis ✱               | Enter a year                                 | #dd2509             |
      | The tumour is... ✱                | Please select the tumour type                | #dd2509             |
     # | Histopathology laboratory ID or local sample ID ✱ | Histopathology laboratory ID or local sample ID is required. | #dd2509             |
      | Histopathology or SIHMDS Lab ID ✱ | Histopathology or SIHMDS Lab ID is required. | #dd2509             |
    And an information "<information>" is displayed that a test cannot start without a tumour
    #@NTS-3171 @NTS-3172
#    And the user navigates to the "<stage>" stage
    And the user is navigated to a page with title Add a tumour
    Then the user answers all tumour system questions without selecting any tumour type
    And the user clicks the Save and Continue button
    And the message will be displayed as "<error_message2>" in "#dd2509" color for the date of diagnosis field
    Then the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    #And the user clicks the Save and Continue button
    And the user answers all tumour system questions fields, select tumour type "<tumour_type>" and leaves date of diagnosis field blank
    #NTS-4829
    When the user clicks on the Back link
    Then the "<pageTitle>" page is displayed
    And the user enters "<Date_of_Diagnosis1>" in the date of diagnosis field
    Then the message will be displayed as "<error_message1>" in "#dd2509" color for the date of diagnosis field
    And the user enters "<Date_of_Diagnosis2>" in the date of diagnosis field
    Then the message will be displayed as "<error_message1>" in "#dd2509" color for the date of diagnosis field
    And the user enters "<Date_of_Diagnosis3>" in the date of diagnosis field
    Then the message will be displayed as "<error_message1>" in "#dd2509" color for the date of diagnosis field
    #NTS-3170
    #And the user clicks the Save and Continue button
    ##Add the step to verify there are NO existing tumours present (list of toumurs)
    And the DateOfDiagnosis field displays given messages in specific color for the wrong values
      | Date_of_Diagnosis | error_message                                 | color   |
      | 12-03-2150        | Please enter a date before today              | #dd2509 |
      | 32-03-2011        | Enter a day between 1 and 31 or leave blank   | #dd2509 |
      | 0-04-2011         | Enter a day between 1 and 31 or leave blank   | #dd2509 |
      | 10-28-2011        | Enter a month between 1 and 12 or leave blank | #dd2509 |
      | 10-0-2011         | Enter a month between 1 and 12 or leave blank | #dd2509 |
      | 14-11-1           | Enter a year in 4 figures e.g. 1983           | #dd2509 |
      | 14-11-19          | Enter a year in 4 figures e.g. 1983           | #dd2509 |
      | 14-11-190         | Enter a year in 4 figures e.g. 1983           | #dd2509 |
      | 14-11-null        | Enter a year                                  | #dd2509 |
      | 30-02-2012        | Check the day and month are valid             | #dd2509 |
      | 14-10-1899        | Enter a year after 1900                       | #dd2509 |
    #NTS-3241 NTS-3157
    #noinspection NonAsciiCharacters
    And the labels and help hint texts are displayed
      | labelHeader                       | HintTextHeader                                                                                                       |
      | Description                       | Describe in a way that distinguishes this tumour from others the patient may have                                    |
      | Date of diagnosis ✱               | Year is required. Enter day and month if known.                                                                      |
      #| Histopathology laboratory ID or local sample ID ✱ | For solid tumours, enter the "Histopathology laboratory ID". For haemato-oncology liquid tumours, enter the "Local Sample ID". |
      | Histopathology or SIHMDS Lab ID ✱ | For solid tumours, enter the "Histopathology Lab ID". For haemato-oncology liquid tumours, enter the "SIHMDS Lab ID" |
      | The tumour is... ✱                | None                                                                                                                 |
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field
    #NTS-3171
    #moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    #User click on refresh button
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    When the user clicks the Log out button
    And the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit3>" button and "<acknowledgeMessage>" it
    Then the web browser is still at the same "<partialCurrentUrl1>" page

    Examples:
      | stage   | information                                                                                              | tumour_type              | error_message | acknowledgeMessage | partOfMessage1    | partialCurrentUrl1 | browser_exit1 | browser_exit3 | pageTitle    | Date_of_Diagnosis1                               | error_message1                                    | Date_of_Diagnosis2                              | Date_of_Diagnosis3                              | new_stage | acknowledgeMessage | partOfMessage       | error_message2                |
      | Tumours | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. | Solid tumour: metastatic | Enter a year  | Dismiss            | may not be saved. | tumours/new        | refresh       | logout        | Add a tumour | Month_is_more_than_9_months_before_date_of_birth | Cannot be more than 9 months before date of birth | Date_is_more_than_9_months_before_date_of_birth | Year_is_more_than_9_months_before_date_of_birth | Samples   | Dismiss            | unsaved information | Please select the tumour type |