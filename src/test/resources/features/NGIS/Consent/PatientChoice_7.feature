@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice-7 - Form Library

  @NTS-3382 @NTS-3435 @Z-LOGOUT
    #@E2EUI-2110 @E2EUI-1889 @E2EUI-2180
  Scenario Outline: NTS-3382: Verify the upload revised patient choice documentation to form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1990:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
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
    Then the user should see the supporting information links under the section Supporting information
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