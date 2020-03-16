@regression
@patientChoice
@patientChoice_formLibrary
Feature: Patient Choice Page - Form Library

  @NTS-3435 @E2EUI-2180 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3435: Upload the latest 'Opt-in' form to the form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the Form library tab in patient choice page
    Then the user should see the supporting information links under the section Patient choice forms
      | FormName                             |
      | Agreement to Participate in Research |

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3382 @E2EUI-2110 @E2EUI-1889 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3382: Verify the upload revised patient choice documentation to form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1990:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the Form library tab in patient choice page
    And the user sees the new patient choice tab selected by default with subtitle Form library
    Then the user should see the supporting information links under the section Patient choice forms
      | FormName                                                     |
      | Record of Discussion Regarding WGS                           |
      | Agreement to Participate in Research                         |
      | National Genomic Research Library Young Person's Assent Form |
      | Consultee Declaration Regarding Whole Genome Sequencing      |
      | Withdrawal from the National Genomic Research Library        |
    And the user should see the supporting information links under the section Annotated patient choice forms
      | FormName                       |
      | Annotated Patient Choice Forms |
    ##Below options commented out as per the latest release - March 12,2020
#      | Annotated Record of Discussion Regarding WGS                           |
#      | Annotated Agreement to Participate in Research                         |
#      | Annotated National Genomic Research Library Young Person's Assent Form |
#      | Annotated Consultee Declaration Regarding Whole Genome Sequencing      |
#      | Annotated Withdrawal from the National Genomic Research Library        |
    And the user should see the supporting information links under the section Supporting information
      | FormName                                             |
      | Clinician's Guide Cancer                             |
      | Clinician's Guide RD                                 |
      | Clinician's Guide Supplementary Information Clinical |
      | Clinician's Guide Supplementary Information NGRL     |
      | Patient's Information Research                       |
      ##Below options added as per the latest release  - March 12,2020
      | Patient Information for Cancer                       |
      | Patient Information for Cancer - Easy Read           |
      | Patient Information for Rare Disease                 |
      | Patient Information for Rare Disease - Easy Read     |
    Examples:
      | Patient choice stage |
      | Patient choice       |
