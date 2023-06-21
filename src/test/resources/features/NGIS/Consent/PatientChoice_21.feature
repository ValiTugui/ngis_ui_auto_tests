#@patientChoice
@05-CONSENT_NotST

Feature: Patient Choice- Document upload section should be open by default

  @E2EUI-2555
  Scenario Outline:E2EUI-2555:The User would like to have the 'Document upload' section opening by default.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2008:Gender=Male |
    And the "Patient details" stage is marked as Completed
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

  @NTS-6025 @Z-LOGOUT
  #@E2EUI-2553
  Scenario Outline: NTS-6025: Validating removal of incorrectly Uploaded Document functionality in Patient choice section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1992:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user is in the section Patient choice category
    When the user selects the option Child in patient choice category
    Then the Patient choice category option is marked as completed
    And the option Child displayed with edit option in Patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the Test type option is marked as completed
    And the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the user is in the section Recorded by
    And the user fills "<ClinicianNameWithFile>" details in recorded by
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user selects Record of Discussion Form from dropdown option in recorded by
    Then the Date of Signature fields are displayed as enabled
    When the user fills current date as Date of Signature
    Then the user clicks on Continue Button
    And the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option No for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    Then the user should be able to see patient choice in history tab
    And the user clicks on the current completed referral
    And the user clicks on the "Remove document" button
    And the user should not be able to see the remove document button
    And the user see that proper message "<expectedMessage>" is displayed after document is deleted

    Examples:
      | PatientChoice  | ClinicianNameWithFile                                                             | expectedMessage                                                                                                            |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileName=testfile.pdf | This document was recently deleted. If further assistance is needed,please contact the Service Desk for further assistance |

  @NTS-6027
  Scenario Outline:NTS-6027:Patient choice form does not have the research section completed
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2008:Gender=Male |
    And the "Patient details" stage is marked as Completed
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
    And the user fills "<RecordedByWithFormUpload>" details in recorded by
    And the user clicks on Continue Button
    And the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer

    Examples:
      | PatientChoice  | RecordedByWithFormUpload                                                                                             |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
