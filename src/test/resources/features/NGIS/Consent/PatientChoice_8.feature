#@patientChoice
@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice-8 - History - Preference

  @NTS-3437 @NTS-4603
    #@E2EUI-1878 @scenario_01 @E2EUI-1891
  Scenario Outline: NTS-3437 :scenario_01: Verify the Supporting information form section in form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    Then the user should be able to see patient choice in history tab
    ##Steps added for NTS-4603
    And the user selects the History tab in patient choice page
    And the user is navigated to a page of section with title Patient choice history
    Then the user verifies the referral id on history tab is same as on referral id on referral bar

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3437 @Z-LOGOUT
    #@E2EUI-1878  @scenario_02
  Scenario Outline: NTS-3437 :scenario_02: Verify the Supporting information form section in form library
    When the user selects the History tab in patient choice page
    And the user selects the New patient choice tab in patient choice page
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the amend patient choice button
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    Then the user should be able to see replaced patient choice in history tab

    Examples:
      | RecordedBy                            |
      | ClinicianName=John:HospitalNumber=123 |

  @NTS-3481 @NTS-5948 @Z-LOGOUT
    #@E2EUI-2151
  Scenario Outline: NTS-3481: Verify the updated warning message content in patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
     #patient choice for the proband
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    When the user selects the proband
    And the user selects the History tab in patient choice page
    And the user selects the New patient choice tab in patient choice page
    And the user clicks on the amend patient choice button
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ##Below steps for @NTS-5948
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user sees the search label with "<expectedText>"
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    Then  the Save and Continue button should be disabled

    Examples:
      | PatientChoice  | RequestingOrganisation  | expectedText                                                   | placeholderText                                                                         |
      | Patient choice | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |

  @NTS-3415 @Z-LOGOUT
    #@E2EUI-1678
  Scenario Outline: NTS-3415 Remove word consent from UI
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    And the "Patient details" stage is marked as Completed
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user is navigated to a page with title Add patient choice information
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage1>"
    And the user selects the New patient choice tab in patient choice page
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    ### this will fail since  Patient consent category: is present in rendered form
    When the user should be able to see the patient choice form with success message
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage2>"
    Then the user selects the New patient choice tab in patient choice page

    Examples:
      | PatientChoice  | WarningMessage2                                                                                                         | WarningMessage1                                                                                    |
      | Patient choice | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. | No patient choice data found. Please complete patient choice before modifying patient preferences. |