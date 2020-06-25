#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-18 - File uploads

  @NTS-3480 @Z-LOGOUT
    #@E2EUI-2154
  Scenario Outline: NTS-3480: Verify patient signature section should not present after upload document
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1992:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user is in the section Patient choice category
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    And the option Adult (With Capacity) displayed with edit option in Patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the Test type option is marked as completed
    And the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the user is in the section Recorded by
    When the user fills "<RecordedByWithFormUpload>" details in recorded by
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient agrees that their data and samples may be used for research, separate to NHS care.
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Review and submit
    Examples:
      | PatientChoice  | RecordedByWithFormUpload                                                                                                           |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |