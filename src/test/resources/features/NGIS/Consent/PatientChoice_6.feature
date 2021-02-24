#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
@Test01
Feature: Patient Choice-6 - File uploads

  @NTS-3440 @Z-LOGOUT
    #@E2EUI-2038 @E2EUI-2036 @E2EUI-1823 @E2EUI-1680 @E2EUI-1827
  Scenario Outline: NTS-3440: Editing Patient Choice Recorded by when uploading a paper form
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
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field          | field_type | symbol | symbol color |
      | Recording clinician name | label      | ✱      | #dd2509      |
    And the following optional fields should be displayed in patient choice section
      | optional_field                   |
      | Patient Hospital Number          |
      | Responsible Clinician Name       |
      | Document scanned and checked by: |
      | Attach All scanned forms         |
    And the user will see a warning message "<WarningMessage1>"
    And the user will see a warning message "<WarningMessage2>"
    And the user should see form to follow button as enabled
    When the user clicks on Form To Follow Button
    Then the user will see a warning message "<ErrorMessage>"
    When the user fills "<RecordedByWithoutFormSelection>" details in recorded by
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the Date of Signature fields are displayed as disabled
    And the user will see a warning message "<WarningMessage3>"
    And Save and continue button is displayed as disabled
    And the user selects Record of Discussion Form from dropdown option in recorded by
    Then the Date of Signature fields are displayed as enabled
    When the user fills current date as Date of Signature
    Then the user should see Continue button as enabled
    When the user Cancel the uploaded forms
    Then the user should not see any uploaded files

    Examples:
      | PatientChoice  | WarningMessage1                                                                                                                                                                                                                                          | WarningMessage2                                                                                                                                                                      | ErrorMessage                                                                           | WarningMessage3                                                                                                                 | RecordedByWithoutFormSelection                                              |
      | Patient choice | You are the appointed administrator for ensuring that the Patient's Genomic Test decisions are accurately reproduced in this digital form. Please ensure that you take care to enter the answers as described on the paper record of decisions attached. | Your file may take a minute to upload, depending on its size. You can continue to fill out this form, but will not be able to complete and submit it until the upload has completed. | Please complete the required field Clinician Name (Admin support user ID is optional): | Please make sure you have uploaded all required forms (child assent, consultee, etc.), you currently have uploaded the files... | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile.pdf |

  @NTS-3448 @Z-LOGOUT
    #@E2EUI-1147 @E2EUI-2087 @E2EUI-825
  Scenario Outline: NTS-3448: Validating Upload Document functionality with invalid file formats in Patient choice section
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
    And the user fills "<ClinicianName>" details in recorded by
    ##Note:UploadFile mentioned should be present in the testData folder.
    Then the user sees the specified error messages for unsupported file uploads
      | UploadFile          | ErrorMessage                                                    |
      | testfile_text.txt   | Failed to upload testfile_text.txt, unsupported file type       |
      | testfile_html.html  | testfile_html.html, unsupported file type                       |
      | testfile_excel.xlsx | Failed to upload testfile_excel.xlsx, unsupported file type     |
      | testfile_doc.docx   | Failed to upload testfile_doc.docx, unsupported file type       |
      | testfile_zip.zip    | Failed to upload testfile_zip.zip, unsupported file type        |
       ###E2EUI-825
      | testfile_11MB.jpg   | Failed to upload testfile_11MB.jpg. Maximum file size is 10 MB. |

    Examples:
      | PatientChoice  | ClinicianName                         |
      | Patient choice | ClinicianName=John:HospitalNumber=123 |

  @NTS-4603 @Z-LOGOUT
    #@E2EUI-1856
  Scenario Outline: NTS-4603: Verify that the old file uploaded names remain after all files have been deleted.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2012:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedByWithoutFormSelection1>" details in recorded by
    Then the user should be able to see the uploaded file name "<FileName1>"
    And the user selects the History tab in patient choice page
    And the user selects the New patient choice tab in patient choice page
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedByWithoutFormSelection2>" details in recorded by
    Then the user should be able to see the uploaded file name "<FileName2>"

    Examples:
      | Stage          | RecordedByWithoutFormSelection1                                             | FileName1    | RecordedByWithoutFormSelection2                                              | FileName2     |
      | Patient choice | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile.pdf | testfile.pdf | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile2.pdf | testfile2.pdf |