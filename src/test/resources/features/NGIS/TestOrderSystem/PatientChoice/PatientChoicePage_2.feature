@regression
@patientChoice
@patientChoice_page2
Feature: Patient Choice Page Verification

  @NTS-3384 @E2EUI-1677 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3384: Verify the hospital no field on patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2016:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    And the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see patient hospital number

    Examples:
      | Patient choice stage | RecordedBy         |
      | Patient choice       | ClinicianName=John |

  @NTS-3385 @E2EUI-1474 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3385: Create referral navigation component - Patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    When the user is navigated to a page with title Add patient choice information
    And the user sees a back button on Add patient choice information page
    When the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3386 @E2EUI-1141 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3386: Recorded By field is mandatory field in Add patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2015:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    And the user clicks on Continue Button
    Then the user will be able to see an error message as "<ErrorMessage>"
    When the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see enabled continue button
    Examples:
      | Patient choice stage | ErrorMessage                                                                           | RecordedBy         |
      | Patient choice       | Please complete the required field Clinician Name (Admin support user ID is optional): | ClinicianName=John |

  @NTS-3387 @E2EUI-1464 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3387: patient signature is a mandatory field in Add patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2014:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user should see patient choice submit button as disabled
    ##Include the step for clicking on Continue without providing signature and validate the warning message.
    Examples:
      | Patient choice stage | RecordedBy         |
      | Patient choice       | ClinicianName=John |
      ##Include other combination of PatientChoiceCategory and other patient choice section combinations.
      ##OR include this validation of clicking without providing signature ijnbig scenario where we cover all the combinations.

  @NTS-3388 @E2EUI-1112 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3388: Verify add patient choice form is an embedded app
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    And the user is navigated to a page with title Add patient choice information

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3377 @E2EUI-2034 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3377: Editing Patient Choice in Patient Choice category
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2013:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user clicks on edit button in Patient choice category
    When the user selects the option Child in patient choice category
    Then the option Child displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed

    Examples:
      | Patient choice stage |
      | Patient choice       |


