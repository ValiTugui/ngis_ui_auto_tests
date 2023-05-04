#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice -3 - Navigation

  @NTS-3418 @Z-LOGOUT
    #@E2EUI-1702
  Scenario Outline: NTS-3418:Research change messages displayed in Patient choice section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-10-2005:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
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
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage2>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
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
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage2>"

    Examples:
      | Patient choice stage | RecordedBy                            | WarningMessage1                                                                                                                                                     | WarningMessage3                                                                                                                                                            | WarningMessage2                                                                                                         |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | If you change this choice it will also apply to any genomic tests the patient has previously had. This will also apply to any future tests, unless they change their mind. | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. |

  @NTS-3446 @Z-LOGOUT
    #@E2EUI-2035
  Scenario Outline: NTS-3446: Editing Patient Choice ‘Test type’
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    And the user is in the section Recorded by
    When the user clicks on edit button in Test type
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    Then the option Cancer (paired tumour normal) – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    Then the user is in the section Recorded by

    Examples:
      | Patient choice stage |
      | Patient choice       |

   ###Commented the below test, as this is about checking the session expiry time. Manual team suggesetd they may take it separately.
#  @NTS-3473 @E2EUI-2037 @Z-LOGOUT @v_1 @P1
#  Scenario Outline: NTS-3473: Patient Choice critical error Should not be occurred when refresh token expires
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
#     #patient choice for the proband
#    When the user navigates to the "<PatientChoice>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    And the user should wait for session expiry time 740 seconds
#    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
#    And the user submits the patient choice with signature
#    And the user clicks the Save and Continue button on the patient choice
#    Then the "<PatientChoice>" page is displayed
#    Then the help text is displayed
#    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
#    Examples:
#      | PatientChoice  |
#      | Patient choice |