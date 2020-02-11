@regression
@PatientChoice

Feature: Patient Choice Page - File uploads

  @NTS-3440 @E2EUI-2038 @E2EUI-1823 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3440: Editing Patient Choice Recorded by when uploading a paper form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1992:Gender=Male |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user is in the section Recorded by
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field          | field_type | symbol | symbol color |
      | Recording clinician name | label      | ✱      | #dd2509      |
    And the following optional fields should be displayed in patient choice section
      | optional_field                   |
      | Patient Hospital Number          |
      | Responsible Clinician Name       |
      | Document scanned and checked by: |
    When the user fills "<RecordedBy>" details in recorded by
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | RecordedBy                                                                                                     | WarningMessage                                                                                                                  |
      | Patient choice | RecordingClinicianName=John Doe:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Please make sure you have uploaded all required forms (child assent, consultee, etc.), you currently have uploaded the files... |

  @NTS-3464 @E2EUI-1680 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3464: Validating the Date of signature field after uploading the document
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1993:Gender=Male |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user is in the section Recorded by
    And the user fills "<RecordedByWithoutFormSelection>" details in recorded by
    And the Date of Signature fields are displayed as "disabled"
    And the user selects Record of Discussion Form from dropdown option in recorded by
    Then the Date of Signature fields are displayed as "enabled"
    And the user fills current date as Date of Signature
    ##Note: Not passing fileType in RecordedBy Argument
    Examples:
      | PatientChoice  | RecordedByWithoutFormSelection                                              |
      | Patient choice | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile.pdf |

  @NTS-3448 @E2EUI-1147 @E2EUI-2087 @E2EUI-825 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3448: Validating Upload Document functionality with invalid file formats in Patient choice section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    Then the option Child displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user is in the section Recorded by
    And the user fills "<RecordedBy>" details in recorded by
    ##UploadFile mentioned should be present in the testData folder.
    Then the user sees the specified error messages for unsupported file uploads
      | UploadFile          | ErrorMessage                                                |
      | testfile_text.txt   | Failed to upload testfile_text.txt, unsupported file type   |
      | testfile_html.html  | testfile_html.html, unsupported file type                   |
      | testfile_excel.xlsx | Failed to upload testfile_excel.xlsx, unsupported file type |
      | testfile_doc.docx   | Failed to upload testfile_doc.docx, unsupported file type   |
      | testfile_zip.zip    | Failed to upload testfile_zip.zip, unsupported file type    |
       ###E2EUI-825
      | testfile_11MB.jpg    | Failed to upload testfile_11MB.jpg. Maximum file size is 10 MB.   |

    Examples:
      | PatientChoice  | RecordedBy                            |
      | Patient choice | ClinicianName=John:HospitalNumber=123 |


  @E2EUI-1890 @E2EUI-1950 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-TODO: Remove the option for consultee from the document upload enumeration - Child flow
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    Then the option Child displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user is in the section Recorded by
    And the user fills "<RecordedByWithoutFormSelection>" details in recorded by
    And the file type dropdown options loaded with below details
      | Record of Discussion Form |
      |Deceased Form |
     Examples:
      | PatientChoice  | RecordedByWithoutFormSelection                                              |
      | Patient choice | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile.pdf |

  @NTS-3480 @E2EUI-2154 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3480: Verify patient signature section should not present after upload document
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1992:Gender=Male |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    And the user is in the section Recorded by
    When the user fills "<RecordedBy>" details in recorded by
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
      |PatientChoice| RecordedBy                                                                                                           |
      |Patient choice| ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
