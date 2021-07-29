@SYSTEM_TEST
@NTS-6029
Feature: Create archived copy of files when user clicks on delete button in UI

  Scenario Outline: NTS-6029: As a user, I want to create a copy of the file before deleting the file uploaded.
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
    Then the user should able to see upload button is enable by default in Record by section
    And the user should see form to follow button as enabled
    When the user fills "<RecordedByWithFormUpload>" details in recorded by
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    Then the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    And the user should be able to see patient choice in history tab
    And the user has to click on latest record of discussion
    And the user read the filename from the UI
    And the user is able to connect to the S3 bucket and check the files presence in folder "<S3 folder>"
    And the user clicks on the "Remove document" button
    And the user should not be able to see the remove document button
    And the user see that proper message "<expectedMessage>" is displayed after document is deleted

    And the user is able to connect to the S3 bucket check files presence in folder "<ArchiveFolder>" and download the file
    And the user is able to upload the file to S3 bucket "<S3 folder>"
    And the user attempts to navigate away by clicking "refresh"
    When the user selects the History tab in patient choice page
    And the user should be able to see patient choice in history tab
    And the user has to click on latest record of discussion
    And the user should see the remove document button is displayed



    Examples:
      | S3 folder       | PatientChoice  | RecordedByWithFormUpload                                                                                             | expectedMessage                                                                                                            | ArchiveFolder |
      | patient-records | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | This document was recently deleted. If further assistance is needed,please contact the Service Desk for further assistance | archive       |