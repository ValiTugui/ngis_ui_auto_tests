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