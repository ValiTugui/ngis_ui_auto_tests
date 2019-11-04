@tumoursPage
Feature: Tumours Page

  @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_01 @NTS-3165 @E2EUI-953 @P0 @v_1
  Scenario Outline: NTS-3165:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc

    Examples:
      | stage   |
      | Tumours |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_02 @NTS-3165 @E2EUI-823  @P0 @v_1
  Scenario Outline: NTS-3165: Text information for user on Tumour referral page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then an information "<information>" is displayed that a test cannot start without a tumour

    Examples:
      | stage   | information                      |
      | Tumours | A laboratory cannot start a test |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_03 @NTS-3152 @NTS-3170 @E2EUI-2018 @E2EUI-1840 @E2EUI-1350 @P0 @v_1
  Scenario Outline:NTS-3152 Future date can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of future date scenario
      | stage   | Date_of_Diagnosis | error_message                    |
      | Tumours | 12-03-2150        | Please enter a date before today |

    Examples: of invalid day
      | stage   | Date_of_Diagnosis | error_message                               |
      | Tumours | 32-03-2011        | Enter a day between 1 and 31 or leave blank |
      | Tumours | 0-04-2011         | Enter a day between 1 and 31 or leave blank |

    Examples: of invalid month
      | stage   | Date_of_Diagnosis | error_message                                 |
      | Tumours | 10-28-2011        | Enter a month between 1 and 12 or leave blank |
      | Tumours | 10-0-2011         | Enter a month between 1 and 12 or leave blank |

    Examples: of invalid year
      | stage   | Date_of_Diagnosis | error_message                       |
      | Tumours | 14-11-1           | Enter a year in 4 figures e.g. 1983 |
      | Tumours | 14-11-19          | Enter a year in 4 figures e.g. 1983 |

    Examples: diagnosis year comes before patient year birth
      | stage   | Date_of_Diagnosis | error_message                                     |
      | Tumours | 14-11-1899        | Cannot be more than 9 months before date of birth |
      | Tumours | 14-11-190         | Cannot be more than 9 months before date of birth |

    Examples: Enter year starting from 1900
      | stage   | Date_of_Diagnosis | error_message            |
      | Tumours | 14-11-0           | Enter a year beyond 1900 |

    Examples: of entering day and month without a year
      | stage   | Date_of_Diagnosis | error_message |
      | Tumours | 14-11-null        | Enter a year  |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_04 @NTS-3157 @E2EUI-1020 @P0 @v_1
  Scenario Outline: NTS-3157:Validate the mandatory input field 'Date of diagnosis' for the Tumour Section
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions by selecting tumour type "<tumour_type>" and leaves date of diagnosis field blank
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of leaving the date of diagnosis field blank
      | stage   | tumour_type              | error_message |
      | Tumours | Solid tumour: metastatic | Enter a year  |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_05 @NTS-3154 @E2EUI-1320 @E2EUI-894 @P0 @v_1
  Scenario Outline: NTS-3154: Add a new tumour for an existing patient
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_06 @NTS-3154 @E2EUI-894 @P0 @v_1
  Scenario Outline: NTS-3154: Add a new tumour for a new patient
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed

    Examples:
      | stage   | tumour_type              | presentationType   | searchTerm |
      | Tumours | Solid tumour: metastatic | First presentation | test       |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_07a  @P0 @v_1 @NTS:3171 @E2EUI-2145
  Scenario Outline:NTS:3171:Moving to other section:The user is stopped to navigate away from dynamic questions step from Tumours stage after editing
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
# moving to another Stage e.g Samples page
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page

#    User click on close button
#    When the user attempts to navigate away by clicking "<browser_exit1>"
#    Then the user sees a prompt alert "<partOfMessage>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
#    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | new_stage | acknowledgeMessage | partOfMessage       | partialCurrentUrl1 | browser_exit1 |
      | Tumours | Solid tumour: metastatic | Samples   | Dismiss            | unsaved information | tumours/create     | close         |


  @COMP6_TOC_Tumour  @LOGOUT
    @tumoursPage_07b  @P0 @v_1 @NTS:3171 @E2EUI-2145
  Scenario Outline:NTS:3171:The user is stopped to navigate away from dynamic questions step from Tumours stage after making changes
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
   #  User click on refresh button
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
#     User click on back button
    When the user attempts to navigate away by clicking "<browser_exit2>"
    Then the user sees a prompt alert "<partOfMessage2>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl2>" page
   #  User click on logout button
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit3>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save and Continue button

    Examples:
      | stage   | tumour_type              | acknowledgeMessage | partOfMessage1    | partOfMessage2      | partialCurrentUrl1 | browser_exit1 | browser_exit2 | browser_exit3 | partialCurrentUrl2 |
      | Tumours | Solid tumour: metastatic | Dismiss            | may not be saved. | unsaved information | tumours/create     | refresh       | back          | logout        | tumours            |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_08 @NTS-3172 @E2EUI-1465 @P0 @v_1
  Scenario Outline: NTS-3172:Validate the mandatory input field 'The tumour is' for the Tumour Section
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers all tumour system questions without selecting any tumour type
    And the user clicks the Save and Continue button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: Tumour type is not selected
      | stage   | error_message                 |
      | Tumours | Please select the tumour type |


  @COMP6_TOC_Tumour @LOGOUT
    @tumoursPage_09 @NTS-3174 @E2EUI-1159 @P0 @v_1
  Scenario Outline: NTS-3174:Verify Estimated Date of Diagnosis, Tumour Type and Specimen ID fields are mandatory fields
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    And the user navigates to the "<stage>" stage
    And the tumours stage is at Add a Tumour page
    When the user clicks the Save and Continue button
    Then the error messages for the tumour mandatory fields are displayed
      | errorMessageHeader                                           |
      | Enter a year                                                 |
      | Please select the tumour type                                |
      | Histopathology laboratory ID or local sample ID is required. |

    Examples: Tumour type is not selected
      | stage   |
      | Tumours |