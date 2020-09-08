#@patientChoice
@05-CONSENT

Feature: Patient Choice- Document upload section should be open by default

  @E2EUI-2555
  Scenario Outline:E2EUI-2555:The User would like to have the 'Document upload' section opening by default.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2008:Gender=Male |
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
    Then the user should able to see upload button is enable by default in Record by section
    And the user should see form to follow button as enabled
    When the user fills "<RecordedByWithFormUpload>" details in recorded by
    And the user clicks on Continue Button

    Examples:
      | PatientChoice  | RecordedByWithFormUpload                                                                                             |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |