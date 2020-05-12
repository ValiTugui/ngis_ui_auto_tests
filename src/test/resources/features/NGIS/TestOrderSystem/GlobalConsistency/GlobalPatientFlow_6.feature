#@regression
#@referral
@03-TEST_ORDER
@SYSTEM_TEST
Feature: GlobalConsistency:Global Patent Flow 6 - Referral Header

  @NTS-4728 @Z-LOGOUT
#   @E2EUI-1250 @E2EUI-1368
  Scenario Outline: Referral: Date of Birth and Age format in the referral header bar
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is marked as Completed
    And the referral submit button is not enabled
    And the DOB and age in the referral header bar are displayed in the expected format

    Examples:
      | stage           |
      | Patient details |

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

  @NTS-4673 @Z-LOGOUT
#   @E2EUI-1492
  Scenario Outline: Patient Search - The correct elements are displayed in the header of Test Ordering
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the Genomic Medicine Service logo "<genomicsEnglandLogo>" is displayed in the header of Test Ordering
    And the username "<userType>" is displayed in the header of Test Ordering
    And the logout "<logoutText>" text is displayed in the header of Test Ordering
    Then the NHS logo is displayed in the header of Test Ordering

    Examples:
      | genomicsEnglandLogo      | logoutText | userType        |
      | Genomic Medicine Service | Log out    | GEL_NORMAL_USER |

   @NTS-4673 @Z-LOGOUT
#    @E2EUI-1492
  Scenario Outline: Referral header Page -  The correct elements are displayed in the header of Test Ordering
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "Patient details" stage
    Then the "Patient details" stage is marked as Completed
    And the Genomic Medicine Service logo "<genomicsEnglandLogo>" is displayed in the header of Test Ordering
    And the username "<userType>" is displayed in the header of Test Ordering
    And the logout "<logoutText>" text is displayed in the header of Test Ordering
    Then the NHS logo is displayed in the header of Test Ordering

    Examples:
      | genomicsEnglandLogo      | logoutText | userType        |
      | Genomic Medicine Service | Log out    | GEL_SUPER_USER |

   @NTS-4673 @Z-LOGOUT
#  @E2EUI-1492
  Scenario: The correct elements are displayed in the footer of Test Ordering
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user has scrolled down the page to the bottom (Footer)
    And the NHS logo is displayed in the footer of Test Ordering
    And the Genomics England logo is displayed in the footer of Test Ordering
    And the Report an issue or provide feedback text link is displayed in the footer of Test Ordering
    And the Privacy Policy text link is displayed in the footer of Test Ordering
    And the copyright text is displayed in the footer of Test Ordering

   @NTS-4673 @Z-LOGOUT
#  @E2EUI-1492
  Scenario: Referral footer Page -  The correct elements are displayed in the footer of Test Ordering
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "Patient details" stage
    Then the "Patient details" stage is marked as Completed
    And the user has scrolled down the page to the bottom (Footer)
    And the NHS logo is displayed in the footer of Test Ordering
    And the Genomics England logo is displayed in the footer of Test Ordering
    And the Report an issue or provide feedback text link is displayed in the footer of Test Ordering
    And the Privacy Policy text link is displayed in the footer of Test Ordering
    And the copyright text is displayed in the footer of Test Ordering


  @NTS-4689 @Z-LOGOUT
    #@E2EUI-1152
  Scenario Outline: Patient Search - Show user account information in Test Order Management System
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the username "<userType>" is displayed in the header of Test Ordering

    Examples:
      | userType        |
      | GEL_NORMAL_USER |

  @NTS-4689 @Z-LOGOUT
    #@E2EUI-1152
  Scenario Outline: Referral header Page -  The correct elements are displayed in the header of Test Ordering
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "Patient details" stage
    Then the "Patient details" stage is marked as Completed
    And the username "<userType>" is displayed in the header of Test Ordering

    Examples:
      | userType       |
      | GEL_SUPER_USER |

  @NTS-4793 @Z-LOGOUT
    #@E2EUI-1008
  Scenario Outline: NTS-4793:Re-order data in referral banner
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    And the "<patient-search-type>" patient details searched for are the same in the referral header bar

    Examples:
      | patient-search-type | stage1          |
      | NGIS                | Patient details |


  @NTS-4809 @Z-LOGOUT
    #@E2EUI-1324
  Scenario Outline: NTS-4809: Header bar for a referral
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the "<stage1>" stage is marked as Completed
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
    And the user sees the patient details on the referral header of each referral component page "<PageTitle>"
      | PatientName | PatientDOB | PatientGender | PatientNHSNo | PatientNgisId | ClinicalIndicationName | PatientReferralID | ReferralStatus | ReferralSubmitButton |

    Examples:
      | stage1          | PageTitle                    |
      | Patient details | Check your patient's details |