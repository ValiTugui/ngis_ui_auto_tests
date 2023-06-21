#@regression
@responsibleClinicianOrg
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: Responsible Clinician - 1

  @NTS-3166  @Z-LOGOUT
#    @NTS-3166_3167
#    @E2EUI-2133 @Z-LOGOUT
  Scenario Outline: NTS-3166 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>"
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
#NTS-3167
    When the user fills in "<invalid_email>" in clinician form fields
    And the user fills in invalid phone number value by providing "<invalid_number_of_digits>" digits in clinician form fields
    Then the user sees an "<error_info>" message on the page
    And The user should be restricted to enter more than "<total_number_of_digits_acceptable>" digits in the Phone number field.

    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | browser_exit_option | warning_text                           |invalid_email|invalid_number_of_digits|total_number_of_digits_acceptable|error_info|
      | Responsible clinician | refresh             | Changes that you made may not be saved |ab@ad.com |19|15|Please enter a valid email address|

  @NTS-3149  @Z-LOGOUT
#    @E2EUI-2133
  Scenario Outline: NTS-3149 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Moving to another To do list
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | new_stage      | warning_text                                                |
      | Responsible clinician | Patient choice | This section contains unsaved information. Discard changes? |


  @NTS-3175 @Z-LOGOUT
#    @NTS-3175_3895
#    @E2EUI-956 @Z-LOGOUT
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User selects 'Save and continue' button without providing inputs in mandatory fields
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#NTS-3895(scenario 3)
#    And the "Patient details" stage is marked as Completed
    And the user is navigated to a page with title Add a requesting organisation
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
    And the user should be able to close the pop up dialog box
#    When the user clicks outside of the modal
    And the user navigates to the "<stage>" stage
    When the user fills in all clinician form fields except the mandatory fields Last name , Department name and address
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | hyperlinkText | error_info            |red_color_hex_code |Title|
      | Responsible clinician | Add another   | Last name is required | #dd2509           |There is missing information|

  # E2EUI-1641
  @NTS-3175 @Z-LOGOUT
#    @E2EUI-956 @E2EUI-1355
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User select 'Save and continue' button without providing nullable Department address
  Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user sees the label Department name and address marked as mandatory
    And The user sees the text field Department name and address
    When the user fills in all clinician form fields except Department name and address
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    Examples:
      | stage                 |  new_stage   |
      | Responsible clinician |   Tumours    |

  @NTS-3311 @Z-LOGOUT
#    @E2EUI-972
  Scenario Outline: NTS-3311 - Responsible Clinician Page - Assign a responsible practitioner to a referral - Cancer flow
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
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

  @NTS-3321 @Z-LOGOUT
    @NTS-3321_3327_3336
#    @E2EUI-1137 @E2EUI-1295 @E2EUI-1238 @E2EUI-1629
  Scenario Outline: NTS-3321 - Responsible Clinician Page - Layout
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    And the user is navigated to a page with title <pageTitle>
    And the page shows the following help messages
      | helpMessageHeader                                                                                                                                                                                                                     |
      | Each referral requires a responsible clinician. They are ultimately accountable for referral accuracy and sample collection. A referral can also include additional clinicians. All linked clinicians will receive the test results. |
    And the user sees the section label as "<stage>"
#NTS-3327
    And The user sees the text field First name and it is blank
    And The user sees the text field Last name and it is blank
    And The user sees the text field Email address and it is blank

    And user sees the following help text under email address field
      | helpMessageHeader                                                                                               |
      | Laboratories will only return results to an address approved to receive patient information                     |
    And The user sees the text field Phone number and it is blank
    And The user sees the text field Department name and address and it is blank
    And The user sees the text field Professional registration number and it is blank
 #NTS-3336
    And the text field First name, Last name, Email address, Professional registration number, Phone number and Department name and address should not enabled with auto-fill feature
    And the user fills in all the clinician form fields
    And the user sees the another section name as "<sectionName>"
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
 #NTS-3327
    # add Additional clinician 1
    When the user clicks the Additional Clinician link
    And The user sees the text field First name in Additional Clinician 1 and it is blank
    And The user sees the text field Last name in Additional Clinician 1 and it is blank
    And The user sees the text field Email address in Additional Clinician 1 and it is blank
    And The user sees the text field Phone number in Additional Clinician 1 and it is blank
    And The user sees the text field Department name and address in Additional Clinician 1 and it is blank
    And The user sees the text field Professional registration number in Additional Clinician 1 and it is blank
    And the user creates additional clinician 1 by filling up all form fields
    Then the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user see the "<removeHyperLink>" displayed to remove the Additional clinician details

    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the "<stage2>" stage is selected
    And the "<stage>" stage is marked as Completed
    And both clinicians details are persisted when returning to the "<stage>" stage
    Examples:
      | stage                 | pageTitle                 | sectionName         |hyperlinkText |removeHyperLink | stage2  |
      | Responsible clinician | Add clinician information | Additional contacts |Add another   |Remove          | Tumours |

  @NTS-4696 @Z-LOGOUT
   # @E2EUI-1642
  Scenario Outline: NTS-4696 - Responsible Clinician Page - user returns back and complete the responsible clinician stage
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user sees the label Department name and address marked as mandatory
    And The user sees the text field Department name and address
    When the user fills in all clinician form fields except Department name and address
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    And the user navigates to the "<stage>" stage
    When the user fills in the Department name and address
    And the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    Then the "<new_stage>" stage is selected
    Examples:
      | stage                 |  new_stage   |
      | Responsible clinician |   Tumours    |