@regression
@TO_Common
@referral

Feature: This is a referral feature

  @NTS-4502 @LOGOUT @v_1 @E2EUI-1250
  Scenario Outline: Referral: Date of Birth and Age format in the referral header bar
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is marked as Completed
    And the DOB and age in the referral header bar are displayed in the expected format

    Examples:
      | stage           |
      | Patient details |

# Awaiting review from Manual Testers
  @LOGOUT @PO @v_1  @ignore
  Scenario Outline: NTS-4543 - Displaying the current state for each stage
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    And the number of mandatory stages to be completed is "6"
#---------------------------------------------------------------#
   Then the Test package stage is marked as Mandatory To Do
    And the Responsible clinician stage is marked as Mandatory To Do
    And the Tumours stage is marked as Mandatory To Do
    And the Patient choice stage is marked as Mandatory To Do

    Examples:
      | stage           |
      | Patient details |


# Awaiting review from Manual Testers
  @LOGOUT @PO @v_1 @ignore
  Scenario Outline: NTS-4545 - Cancer referral - Navigation through the journey
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    And the user navigates to the "Requesting organisation" stage
    Then the "Requesting organisation" stage is selected
    And the user navigates to the "Test package" stage
    Then the "Test package" stage is selected
    And the user navigates to the "Responsible clinician" stage
    Then the "Responsible clinician" stage is selected
    And the user navigates to the "Tumours" stage
    Then the "Tumours" stage is selected
    And the user navigates to the "Samples" stage
    Then the "Samples" stage is selected
    And the user navigates to the "Notes" stage
    Then the "Notes" stage is selected
    And the user navigates to the "Patient choice" stage
    Then the "Patient choice" stage is selected

    Examples:
      | stage           |
      | Patient details |

  @LOGOUT @NTS-4562 @PO @v_1 @E2EUI-1088
  Scenario Outline: NTS-4562-The user is able to logout from Referral Header - Test Ordering system
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the user clicks the Log out button
    Then the user is successfully logged out

    Examples:
      | stage           |
      | Patient details |


  @LOGOUT @NTS-4673 @PO @v_1 @E2EUI-1492
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


  @LOGOUT @NTS-4673 @PO @v_1 @E2EUI-1492
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


  @LOGOUT @NTS-4673 @PO @v_1 @E2EUI-1492
  Scenario: The correct elements are displayed in the footer of Test Ordering
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user has scrolled down the page to the bottom (Footer)
    And the NHS logo is displayed in the footer of Test Ordering
    And the Genomics England logo is displayed in the footer of Test Ordering
    And the Report an issue or provide feedback text link is displayed in the footer of Test Ordering
    And the Privacy Policy text link is displayed in the footer of Test Ordering
    And the copyright text is displayed in the footer of Test Ordering


  @LOGOUT @NTS-4673 @PO @v_1 @E2EUI-1492
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



