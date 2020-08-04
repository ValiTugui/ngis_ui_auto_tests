@SYSTEM_TEST
Feature: Patient Choice-19 - Remove file uploaded

  @NTS-6025 @Z-LOGOUT
    #@E2EUI-2553
  Scenario Outline: NTS-6025: Validating removal of incorrectly Uploaded Document functionality in Patient choice section
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
    And the user fills "<ClinicianNameWithFile>" details in recorded by
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user selects Record of Discussion Form from dropdown option in recorded by
    Then the Date of Signature fields are displayed as enabled
    When the user fills current date as Date of Signature
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    Then the user should be able to see patient choice in history tab
    And the user clicks on the current completed referral
    And the user clicks on the "Remove document" button

    Examples:
      | PatientChoice  | ClinicianNameWithFile                                                             |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileName=testfile.pdf |
