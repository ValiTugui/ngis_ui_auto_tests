@regression
@patientChoice
@patientChoice_page3
Feature: Patient Choice Page

  @NTS-3409 @E2EUI-1822 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3409: Navigate around the patient choice pages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
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
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3411 @E2EUI-1960 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3411: Adult (With Capacity): Verify the info message when user declined for a test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2011:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    And the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<InfoMessage>"

    Examples:
      | Patient choice stage | RecordedBy                            | InfoMessage                                                                                                                                                   |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @NTS-3411 @E2EUI-1960 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3411: Adult (Without Capacity): Verify the info message when user declined for a test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2010:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<InfoMessage>"

    Examples:
      | Patient choice stage | RecordedBy                            | InfoMessage                                                                                                                                                   |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @NTS-3411 @E2EUI-1960 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3411: Child: Verify the info message when user declined for a test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2009:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    Then the option Cancer (paired tumour normal) – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<InfoMessage>"

    Examples:
      | Patient choice stage | RecordedBy                            | InfoMessage                                                                                                                                                   |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @NTS-3410 @E2EUI-1127 @E2EUI-1934 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3410: Verify the patient Choice stage marked as completed
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-34141 @E2EUI-1889 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3414: Verify the Supporting information form section in form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2007:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the Form library tab in patient choice page
    Then the user sees the new patient choice tab selected by default with subtitle Form library

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3418 @E2EUI-1702 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3418: Validation of change in research message inside patient choices section if I change my choice to participate in research.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2006:Gender=Male |
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
    When the user selects the option No for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage1>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    When the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user clicks on "Preferences" link
    And the user will see a warning message "<WarningMessage2>"
    When the user clicks on "New patient choice" link
    Then the user is navigated to a page with title Add patient choice information
    When the user clicks on the amend patient choice button
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage3>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks on "Preferences" link
    And the user will see a warning message "<WarningMessage2>"

    Examples:
      | Patient choice stage | RecordedBy                            | WarningMessage1                                                                                                                                                     | WarningMessage3                                                                                                                                                            | WarningMessage2                                                                                                         |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | If you change this choice it will also apply to any genomic tests the patient has previously had. This will also apply to any future tests, unless they change their mind. | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. |

  @NTS-3437 @E2EUI-1878 @v_1 @P0 @scenario_01
  Scenario Outline: NTS-3437 :scenario_01: Verify the Supporting information form section in form library
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
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
    And the user clicks on "History" link
    And the user should be able to see patient choice in history tab

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3437 @E2EUI-1878 @LOGOUT @v_1 @P0 @scenario_02
  Scenario Outline: NTS-3437 :scenario_02: Verify the Supporting information form section in form library
    When the user clicks on "History" link
    And the user clicks on "New patient choice" link
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
    And the user clicks on "History" link
    Then the user should be able to see replaced patient choice in history tab

    Examples:
      | RecordedBy                            |
      | ClinicianName=John:HospitalNumber=123 |
