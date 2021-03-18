#@regression
@responsibleClinicianOrg
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: TestOrder - Responsible Clinician 3

  @NTS-3324 @Z-LOGOUT
#    @NTS-3324_3895_3895_3286_4503_4696
#    @E2EUI-1014
  Scenario Outline: NTS-3324 - Responsible Clinician Page - Verify the mandatory fields validations under 'Add Another Clinician' section
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
#NTS-3895
    And the "Patient details" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
#NTS-3895
    And the user should be able to close the pop up dialog box
    Then the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the user is navigated to a page with title <pageTitle>

#NTS-3286
    And the user sees First name, Last name, Phone number and email, Department name and address, Professional registration number
    When the user fills in all clinician form fields except Last name
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "<stage>" stage is marked as Mandatory To Do
    # add  Main clinician
    And the user fills in all the clinician form fields

#NTS-4503
    And the user fills in all the clinician form fields
    And the user deletes the data in the Clinician Phone Number field
    When the user attempts to fill in Clinician Phone Number field "<ClinicianPhoneNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "ClinicianPhoneNumber" field
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    #And the user deletes the data in the Clinician Phone Number field

    # add Additional clinician 1
    And the user clicks the Additional Clinician link
    When the user creates additional clinician 1 by filling up all form fields except the mandatory fields Last name
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark in additional clinician section
    And the "<stage>" stage is marked as Mandatory To Do
    And the user creates additional clinician 1 by filling up all form fields

 #NTS-4696
    And the user is navigated to a page with title <pageTitle>
    And The user sees the text field First name
    And The user sees the text field Last name
    And The user sees the text field Email address
    And The user sees the text field Phone number
    And The user sees the text field Department name and address
    And The user sees the text field Professional registration number
    And the user fills in all the clinician form fields
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user clicks the Additional Clinician link
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button

    Examples:
      | stage                 |stage1| pageTitle                 |ClinicianPhoneNumber| hyperlinkText | error_info            | red_color_hex_code |Title|
      | Responsible clinician |Tumours| Add clinician information |1234567890123456789| Add another   |Last name is required.| #dd2509            |There is missing information|