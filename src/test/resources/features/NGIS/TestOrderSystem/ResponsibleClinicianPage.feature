@regression
@regression_set3
@responsibleClinicianOrg
Feature: Responsible Clinician

  @E2EUI-2133 @NTS-3166 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3166 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>"
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | browser_exit_option | warning_text                           |
      | Responsible clinician | refresh             | Changes that you made may not be saved |

  @E2EUI-2133 @NTS-3149 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3149 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Moving to another To do list
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | new_stage      | warning_text                                                |
      | Responsible clinician | Patient choice | This section contains unsaved information. Discard changes? |

  @E2EUI-1216 @NTS-3167 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3167 - Responsible Clinician Page - Email and Phone Number field validation
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    When the user fills in "<invalid_email>" in clinician form fields
    And the user fills in invalid phone number value by providing "<invalid_number_of_digits>" digits in clinician form fields
    Then the user sees an "<error_info>" message on the page
    And The user should be restricted to enter more than "<total_number_of_digits_acceptable>" digits in the Phone number field.
    Examples:
      | stage                 | invalid_email | invalid_number_of_digits | total_number_of_digits_acceptable | error_info                         |
      | Responsible clinician | ab@ad.com     | 19                       | 15                                | Please enter a valid email address |

  @E2EUI-956 @NTS-3175 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User selects 'Save and continue' button without providing inputs in mandatory fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    When the user fills in all clinician form fields except the mandatory fields Last name , Department name and address
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | hyperlinkText | error_info            |red_color_hex_code |
      | Responsible clinician | Add another   | Last name is required | #dd2509           |

@E2EUI-956 @NTS-3175 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User select 'Save and continue' button without providing nullable Department address
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    When the user fills in all clinician form fields except Department name and address
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    Examples:
      | stage                 |  new_stage   |
      | Responsible clinician |   Tumours    |

  @E2EUI-939 @NTS-3286 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3286 - Responsible Clinician Page - Save and continue the responsible section by leaving the Last name field empty
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the user sees the title text as "<pageTitle>"
    And the user sees First name, Last name, Phone number and email, Department name and address, Professional registration number
    When the user fills in all clinician form fields except Last name
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | pageTitle                 | hyperlinkText | error_info            | red_color_hex_code |
      | Responsible clinician | Add clinician information | Add another   | Last name is required | #dd2509            |


  @E2EUI-972 @NTS-3311 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3311 - Responsible Clinician Page - Assign a responsible practitioner to a referral - Cancer flow
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage1>" stage
    And the user is navigated to a page with title <pageTitle>
    And the user fills in all the clinician form fields
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    When the user clicks the Additional Clinician link
    And the user creates additional clinician 1 by filling up all form fields
    And the user clicks the Save and Continue button
    And the "<stage1>" stage is marked as Completed
    Then the "<stage2>" stage is selected
    And both clinicians details are persisted when returning to the "<stage1>" stage
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    Examples:
      | stage1                | stage2  | pageTitle                 | hyperlinkText |
      | Responsible clinician | Tumours | Add clinician information | Add another   |

  @E2EUI-1137 @NTS-3321 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3321 - Responsible Clinician Page - Layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    And the page shows the following help messages
      | helpMessageHeader                                                                                                                                                                                                                     |
      | Each referral requires a responsible clinician. They are ultimately accountable for referral accuracy and sample collection. A referral can also include additional clinicians. All linked clinicians will receive the test results. |
    And the user sees the section label as "<stage>"
    And The user sees the text field First name
    And The user sees the text field Last name
    And The user sees the text field Email address
    And The user sees the text field Phone number
    And The user sees the text field Department name and address
    And The user sees the text field Professional registration number
    And the user sees the another section name as "<sectionName>"
    And the page shows the following help messages under Additional contacts
      | helpMessageHeader                                                                                               |
      | A referral can include as many additional clinicians as needed. Each contact will receive a copy of the report. |
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user sees the Save and Continue button
    Examples:
      | stage                 | pageTitle                 | sectionName         |hyperlinkText |
      | Responsible clinician | Add clinician information | Additional contacts |Add another   |

  @E2EUI-1104 @E2EUI-1435 @NTS-3324 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3324 - Responsible Clinician Page - Verify the input fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage1>" stage
    And the "<pageTitle>" page is displayed
    And The user sees the text field First name
    And The user sees the text field Last name
    And The user sees the text field Email address
    And The user sees the text field Phone number
    And The user sees the text field Department name and address
    And The user sees the text field Professional registration number
    # add  Main clinician
    And the user fills in all the clinician form fields
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    # add Additional clinician 1
    When the user clicks the Additional Clinician link
    And the user creates additional clinician 1 by filling up all form fields
    Then the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user see the "<removeHyperLink>" displayed to remove the Additional clinician details
     # add Additional clinician 2
    When the user clicks the Additional Clinician link
    And the user creates additional clinician 2 by filling up all form fields
    Then the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user see the "<removeHyperLink>" displayed to remove the Additional clinician details

    And the user clicks the Save and Continue button
    Then the "<stage2>" stage is selected
    And the "<stage1>" stage is marked as Completed
    And three clinicians details are persisted when returning to the "<stage1>" stage
    Examples:
      | stage1                | pageTitle                 | hyperlinkText | removeHyperLink | stage2  |
      | Responsible clinician | Add clinician information | Add another   | Remove          | Tumours |

  @E2EUI-1014 @NTS-3325 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3324 - Responsible Clinician Page - Verify the mandatory fields validations under 'Add Another Clinician' section
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    And the user navigates to the "<stage>" stage
    And the "<pageTitle>" page is displayed
    # add  Main clinician
    And the user fills in all the clinician form fields
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    # add Additional clinician 1
    And the user clicks the Additional Clinician link
    When the user creates additional clinician 1 by filling up all form fields except the mandatory fields Last name
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark in additional clinician section
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | pageTitle                 | hyperlinkText | error_info            | red_color_hex_code |
      | Responsible clinician | Add clinician information | Add another   | Last name is required | #dd2509            |
