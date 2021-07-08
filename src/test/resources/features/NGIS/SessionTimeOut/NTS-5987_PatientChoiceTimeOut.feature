@SESSION_TEST
Feature: NTS-5987-Patient Choice Timeout

  @NTS-5987
  Scenario Outline: NTS-5987: User is completing a referral and adds details to Patient choice section, then user should time out in 20 minutes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-10-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
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
#    When the user inactive for 21 minutes
    When the user clear all the session cookies in a new tab
    And the user clicks on submit patient choice Button
    And the user save patient choice form and continue
    Then user should redirect to login page
    Examples:
      | Patient choice stage | RecordedBy                            | WarningMessage1                                                                                                                                                     | WarningMessage3                                                                                                                                                            | WarningMessage2                                                                                                         |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | If you change this choice it will also apply to any genomic tests the patient has previously had. This will also apply to any future tests, unless they change their mind. | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. |