#@regression
@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Tumours Page - 1

  @NTS-3165 @Z-LOGOUT
#   @E2EUI-953
  Scenario Outline: NTS-3165:E2EUI-953:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc

    Examples:
      | stage   |
      | Tumours |

  @NTS-3165 @Z-LOGOUT
#    @E2EUI-823 @E2EUI-1120 @E2EUI-1026 @E2EUI-1515 @NTS-6342
  Scenario Outline: NTS-3165:E2EUI-823,1120,1026,1515: Text information for user on Tumour referral page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then an information "<information>" is displayed that a test cannot start without a tumour
    ##Add the step to verify there are NO existing tumours present (list of toumours)
    Examples:
      | stage   | information                                                                                              |
      | Tumours | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |

  @NTS-3241 @Z-LOGOUT
#    @E2EUI-1576 @E2EUI-1410 @E2EUI-1356 @E2EUI-1699
  Scenario Outline: NTS-3241:E2EUI-1576,1410,1356,1699: Labels and help hint texts are displayed in Add a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the labels and help hint texts are displayed
      | labelHeader                       | HintTextHeader                                                                                                                 |
      | Description                       | Describe in a way that distinguishes this tumour from others the patient may have                                              |
      | Date of diagnosis ✱               | Year is required. Enter day and month if known.                                                                                |
      #| Histopathology laboratory ID or local sample ID ✱ | For solid tumours, enter the "Histopathology laboratory ID". For haemato-oncology liquid tumours, enter the "Local Sample ID". |
      | Histopathology or SIHMDS Lab ID ✱ | For solid tumours, enter the "Histopathology Lab ID". For haemato-oncology liquid tumours, enter the "SIHMDS Lab ID" |
      | The tumour is... ✱                | None                                                                                                                           |

    Examples:
      | stage   |
      | Tumours |

  @NTS-3170 @Z-LOGOUT
#   @E2EUI-2018 @E2EUI-1840 @E2EUI-1350 @E2EUI-1486 @E2EUI-1459 @E2EUI-1846
  Scenario Outline:NTS-3152:E2EUI-2018,1840,1350,1486,1459,1846: Future date  can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
#    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
#    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field
    Then the DateOfDiagnosis field displays given messages in specific color for the wrong values
      | Date_of_Diagnosis | error_message                       | color   |
      | 12-03-2150        | Please enter a date before today                  | #dd2509 |
      | 32-03-2011        | Enter a day between 1 and 31 or leave blank       | #dd2509 |
      | 0-04-2011         | Enter a day between 1 and 31 or leave blank       | #dd2509 |
      | 10-28-2011        | Enter a month between 1 and 12 or leave blank     | #dd2509 |
      | 10-0-2011         | Enter a month between 1 and 12 or leave blank     | #dd2509 |
      | 14-11-1           | Enter a year in 4 figures e.g. 1983               | #dd2509 |
      | 14-11-19          | Enter a year in 4 figures e.g. 1983               | #dd2509 |
      | 14-11-190         | Enter a year in 4 figures e.g. 1983 | #dd2509 |
      | 14-11-null        | Enter a year                        | #dd2509 |
      | 30-02-2012        | Check the day and month are valid   | #dd2509 |
      | 14-10-1899        | Enter a year after 1900             | #dd2509 |
    Examples: of future date scenario
      | stage   |
      | Tumours |

  @NTS-3157 @Z-LOGOUT
#    @E2EUI-1020
    # Replaced SPINE data with NGIS Data creation
  Scenario Outline: NTS-3157:E2EUI-1020:Validate the mandatory input field 'Date of diagnosis' for the Tumour Section
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions fields, select tumour type "<tumour_type>" and leaves date of diagnosis field blank
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of leaving the date of diagnosis field blank
      | stage   | tumour_type              | error_message |
      | Tumours | Solid tumour: metastatic | Enter a year  |

#    Test to be skipped till we sort out SPINE Data 20/02/2020
# @NTS-3154 @E2EUI-1320 @E2EUI-894 @E2EUI-1549 @E2EUI-1236 @Z-LOGOUT
#  @ignore - this ignore tag is not picked up by Jenkins run. so commented out the entire ticket.
#  Scenario Outline: NTS-3154: Add a new tumour for an existing patient
#    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
#    When the user navigates to the "<stage>" stage
#    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
#    And the user clicks the Save and Continue button
#    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
#    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
#    And the user clicks the Save and Continue button
#    Then the new tumour is displayed in the landing page for the existing patient with tumour list
#    And the new tumour is not highlighted
#    And the "<stage>" stage is marked as Completed
#    And the success notification is displayed "<notificationText>"
#
#    Examples:
#      | stage   | tumour_type              | presentationType | searchTerm | notificationText |
#      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Tumour added     |


