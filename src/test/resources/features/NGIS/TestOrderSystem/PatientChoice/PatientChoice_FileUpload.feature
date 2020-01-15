@regression
@patientChoice
Feature: Patient Choice Page

  @NTS-3420 @E2EUI-2087 @LOGOUT  @v_1 @P0
  Scenario Outline: NTS-3420 : Verify upload of patient choice paper form with size less than 10Mb
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option <CategoryOption> in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    Then the user sees a success message after form upload in recorded by as "<FormSuccessMessage>"
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    Then the user should wait for the form to be uploaded by moving to the next section "Patient choice"
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder.
    Examples:
      | Patient choice stage | CategoryOption        | TestType                        | RecordedBy                                                                                                           | FormSuccessMessage    |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Successfully Uploaded |
#
#  @COMP9_TO_PatientChoiceAdd
#    @PatientChoice_page_31 @LOGOUT @NTS-3436 @E2EUI-1704 @v_1 @P0
#  Scenario Outline: NTS-3436: No question is populated for the Not applicable case under child assent in patient choice questions.
#    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
#
#    When the user navigates to the "<Patient choice stage>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    When the user selects the option Child in patient choice category
#    And the user fills "<TestType>" details in test type
#    Then the user should be able to see patient hospital number
#    When the user fills "<RecordedBy>" details in recorded by
#    Then the user sees a success message after form upload in recorded by as "<FormSuccessMessage>"
#    And the user clicks on Continue Button
#    And the Recorded by option is marked as completed
#    Then the user should wait for the form to be uploaded by moving to the next section "Patient choice"
#    And the user is navigated to a patient choice form option with title Patient choices
#    And the user selects the option "<PatientChoice>" as patient choices
#    And the user selects "<YesOption>" research participation option in patient choices
#    And the user selects "<YesOption>" data and sample option in patient choices
#    And the user clicks on Continue Button
#    Then the user is navigated to a patient choice form option with title Child assent
#    Then the user should see the section title as Does the child agree to participate in research?
#    And the child assent options as below
#      | Yes            |
#      | No             |
#      | Not applicable |
#    And the user should see continue button is not highlighted
#    And the user selects "<NotApplicable>" agree to participate in research for Child Assent
#    Then the user should be able to see highlighted continue button
#
#    Examples:
#      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                                                                                     | PatientChoice                                        | YesOption | NotApplicable  | FormSuccessMessage    |
#      | Patient choice       | Child                 | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form | Parent(s) / carer / guardian have agreed to the test | Yes       | Not applicable | Successfully Uploaded |
#
#  @COMP9_TO_PatientChoiceAdd
#    @PatientChoice_page_32 @LOGOUT @NTS-3448 @E2EUI-1147 @v_1 @P0
#  Scenario Outline: NTS-3448: Validating Upload Document functionality with invalid file formats in Patient choice section
#    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
#    When the user navigates to the "<Patient choice stage>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    When the user selects the option Adult (With Capacity) in patient choice category
#    And the user fills "<TestType>" details in test type
#    And the user fills "<RecordedBy>" details in recorded by
#    ##UploadFile mentioned should be present in the testData folder.
#    Then the user sees the specified error messages for unsupported file uploads
#      | UploadFile       | ErrorMessage     |
#      | invalidfile.txt  | Failed to upload |
#      | invalidfile.htm  | Failed to upload |
#      | invalidfile.xlsx | Failed to upload |
#
#    Examples:
#      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            |
#      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |
#
#  @COMP9_TO_PatientChoice
#    @patientChoice_Page02 @LOGOUT @NTS-3464 @E2EUI-1680 @v_1 @P0
#  Scenario Outline: NTS-3464: Validating the Date of signature field after uploading the document
#    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | NGIS | Rare-Disease | NHSNumber=9449303894:DOB=26-11-1986 |
#    When the user navigates to the "<Stage>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    When the user selects the option Adult (With Capacity) in patient choice category
#    And the user fills "<TestType>" details in test type
#    And the user fills "<RecordedBy>" details in recorded by
#    And the user clicks the Upload document button
#    And the user clicks the upload button to upload the file "<File>"
#    Then the user should verify the dropdown and dob field after the document successfully uploaded
#    And the Date of Signature fields are displayed as "disabled"
#    And the user selects "<SelectDropDown>" from dropdown option in recorded by
#    Then the Date of Signature fields are displayed as "enabled"
#    And the user fills the valid Date in "<Date of Signature>"
#
#    Examples:
#      | Stage          | PatientChoiceCategory    | TestType                        | RecordedBy                            | SelectDropDown            | File         | Date of Signature |
#      | Patient choice | Adult (Without Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Record of Discussion Form | testfile.pdf | 04/01/2020        |