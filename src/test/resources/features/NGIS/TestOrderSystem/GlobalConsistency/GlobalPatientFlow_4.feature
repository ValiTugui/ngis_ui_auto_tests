#@regression
#@noComponent
@03-TEST_ORDER
@SYSTEM_TEST
@GlobalConsistency
Feature: GlobalConsistency:Global Patient Flow 4 - Common validations
  @testABC
  @NTS-4692 @Z-LOGOUT
#    @E2EUI-1176
  Scenario Outline: NTS-4692: Copy & Design only - Create or update copy on Add a sample/Edit a sample
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1971:Gender=Male |
    When the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<Stage>" stage
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the success notification is displayed "Sample added"
    When the user edits the added sample
    Then the user is navigated to a page with title Edit a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType1>", sample state "<sampleState1>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    And the success notification is displayed "Sample updated"
    Then the user clicks the Save and Continue button
##examples changed below a/c to Gonzalo release
    Examples:
      | Stage   | sampleType                | sampleState        | sampleType1               | sampleState1   |
      | Samples | Normal or germline sample | Fetal blood (EDTA) | Normal or germline sample | Amniotic fluid |

  @NTS-4621 @Z-LOGOUT
#    @NTS-4673 @E2EUI-1492
#    @NTS-4689  @E2EUI-1152
#    @E2EUI-1191
  Scenario Outline:NTS-4621:To validate interface links and buttons for the NHS patient creation
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |

    ### @E2EUI-1492
    And the user has scrolled down the page to the bottom (Footer)
    And the NHS logo is displayed in the footer of Test Ordering
    And the Genomics England logo is displayed in the footer of Test Ordering
    And the Report an issue or provide feedback text link is displayed in the footer of Test Ordering
    And the Privacy Policy text link is displayed in the footer of Test Ordering
    And the copyright text is displayed in the footer of Test Ordering
#    @NTS-4673 @E2EUI-1492
    And the Genomic Medicine Service logo "<genomicsEnglandLogo>" is displayed in the header of Test Ordering
#    @NTS-4689  @E2EUI-1152
    And the username "<userType>" is displayed in the header of Test Ordering
    And the logout "<logoutText>" text is displayed in the header of Test Ordering
    Then the NHS logo is displayed in the header of Test Ordering

    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
#    When the user is navigated to a page with title Add a new patient to the database
#    Then the user sees the Save PatientDetails button highlighted with color as "<ButtonColor>"
    ## Changed a/c to Gonzalo release
    When the user is navigated to a page with title Create a record for this patient
    Then the user sees the Create Record button highlighted with color as "<ButtonColor>"

    Examples:
      | hyperlinkText               | ButtonColor | genomicsEnglandLogo      | logoutText | userType        |
      | create a new patient record | #005eb8     | Genomic Medicine Service | Log out    | GEL_NORMAL_USER |