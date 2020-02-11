@regression
@patientChoice
Feature: Patient Choice Page

  @NTS-3341 @E2EUI-1659 @LOGOUT @BVT-P0 @v_1
  Scenario Outline: NTS-3341: Verify the patient choice status in family member page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1999:Gender=Male |
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the "<PatientChoice>"
    Then the user is navigated to a page with title Patient choice
    And the user sees the patient choice status as "<Status2>"
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status2>"

    Examples:
      | Family members | Status1     | Patient choice stage | Status2           | PatientChoice                                        |
      | Family members | Not entered | Patient choice       | Agreed to testing | Parent(s) / carer / guardian have agreed to the test |

  @NTS-3382 @E2EUI-2110 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3382: Verify the upload revised patient choice documentation to form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1998:Gender=Male |
    When the user navigates to the "<Family members>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the Form library tab in patient choice page
    Then the user should see the supporting information links under the section Patient choice forms
      | FormName                                                     |
      | Record of Discussion Regarding WGS                           |
      | Agreement to Participate in Research                         |
      | National Genomic Research Library Young Person's Assent Form |
      | Consultee Declaration Regarding Whole Genome Sequencing      |
      | Withdrawal from the National Genomic Research Library        |
    And the user should see the supporting information links under the section Annotated patient choice forms
      | FormName                                                               |
      | Annotated Record of Discussion Regarding WGS                           |
      | Annotated Agreement to Participate in Research                         |
      | Annotated National Genomic Research Library Young Person's Assent Form |
      | Annotated Consultee Declaration Regarding Whole Genome Sequencing      |
      | Annotated Withdrawal from the National Genomic Research Library        |
    And the user should see the supporting information links under the section Supporting information
      | FormName                                             |
      | Clinician's Guide Cancer                             |
      | Clinician's Guide RD                                 |
      | Clinician's Guide Supplementary Information Clinical |
      | Clinician's Guide Supplementary Information NGRL     |
      | Patient's Information Research                       |
    Examples:
      | Family members | Status1     | Patient choice stage |
      | Family members | Not entered | Patient choice       |