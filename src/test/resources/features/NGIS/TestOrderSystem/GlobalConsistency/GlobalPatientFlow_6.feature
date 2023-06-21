@GlobalFlow
#@regression
#@referral
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@GlobalConsistency
Feature: GlobalConsistency:Global Patent Flow 6 - Referral Header


# Awaiting review from Manual Testers
#  @Z-LOGOUT  @ignore @NTS_todo
#  Scenario Outline: NTS-4543 - Displaying the current state for each stage
#    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
#    And the user navigates to the "<stage>" stage
#    And the "<stage>" stage is marked as Completed
#    And the number of mandatory stages to be completed is "6"
##---------------------------------------------------------------#
#   Then the Test package stage is marked as Mandatory To Do
#    And the Responsible clinician stage is marked as Mandatory To Do
#    And the Tumours stage is marked as Mandatory To Do
#    And the Patient choice stage is marked as Mandatory To Do
#
#    Examples:
#      | stage           |
#      | Patient details |


# Awaiting review from Manual Testers
#  @Z-LOGOUT @NTS_todo @ignore
#  Scenario Outline: NTS-4545 - Cancer referral - Navigation through the journey
#    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
#    And the user navigates to the "<stage>" stage
#    Then the "<stage>" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Requesting organisation" stage
#    Then the "Requesting organisation" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Test package" stage
#    Then the "Test package" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Responsible clinician" stage
#    Then the "Responsible clinician" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Tumours" stage
#    Then the "Tumours" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Samples" stage
#    Then the "Samples" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Notes" stage
#    Then the "Notes" stage is selected
#    And the referral submit button is not enabled
#    And the user navigates to the "Patient choice" stage
#    Then the "Patient choice" stage is selected
#    And the referral submit button is not enabled
#
#    Examples:
#      | stage           |
#      | Patient details |

  @NTS-4728  @NTS-4673 @Z-LOGOUT
#    @E2EUI-1492
  Scenario Outline: Referral header Page -  The correct elements are displayed in the header of Test Ordering
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "Patient details" stage
    Then the "Patient details" stage is marked as Completed
#    @NTS-4793 @E2EUI-1008
    And the "<patient-search-type>" patient details searched for are the same in the referral header bar
    ### @NTS-4728
    And the referral submit button is not enabled
    And the DOB and age in the referral header bar are displayed in the expected format
###  @E2EUI-1492
    And the user has scrolled down the page to the bottom (Footer)
    And the NHS logo is displayed in the footer of Test Ordering
    And the Genomics England logo is displayed in the footer of Test Ordering
    And the Report an issue or provide feedback text link is displayed in the footer of Test Ordering
    And the Privacy Policy text link is displayed in the footer of Test Ordering
    And the copyright text is displayed in the footer of Test Ordering
    And the Genomic Medicine Service logo "<genomicsEnglandLogo>" is displayed in the header of Test Ordering
#    @NTS-4813 @Z-LOGOUT @E2EUI-1005
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Tumours                   |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage2>" in the dialog box
    And the "<stage2>" stage is selected
    Then the "<pageTitle>" page is displayed
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the user clicks the Save and Continue button
    And the "<stage3>" stage is selected
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Test package              |
      | Responsible clinician     |
      | Tumours                   |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage3>" in the dialog box
    And the "<stage3>" stage is selected
#    @NTS-4689 @E2EUI-1152
    And the username "<userType>" is displayed in the header of Test Ordering
    And the logout "<logoutText>" text is displayed in the header of Test Ordering
    Then the NHS logo is displayed in the header of Test Ordering
#    @NTS-4809 @E2EUI-1324
    And the user retrieve the referral HumanReadable-ID from the referral page url
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user retrieve the patient HumanReadable-ID from the patient detail url
    And the user click on the referral card on patient details page to navigate to referral page
#    Then the user is navigated to a page with title Test Order Forms
#    And the "Patient details" stage is marked as Completed
    When the user navigates to the "Patient details" stage
    Then the user sees the patient details on the referral header of each referral component page "<PageTitle>"
      | PatientName | PatientDOB | PatientGender | PatientNHSNo | PatientNgisId | ClinicalIndicationName | PatientReferralID | ReferralStatus | ReferralSubmitButton |
#    @NTS-4562 @E2EUI-1088
    And the user clicks the Log out button
    Then the user is successfully logged out

    Examples:
      | genomicsEnglandLogo      | stage2                  | pageTitle                     | ordering_entity_name | stage3       | logoutText | userType       | dialogTitle                  | patient-search-type | PageTitle                    |
      | Genomic Medicine Service | Requesting organisation | Add a requesting organisation | Maidstone            | Test package | Log out    | GEL_SUPER_USER | There is missing information | NGIS                | Check your patient's details |
