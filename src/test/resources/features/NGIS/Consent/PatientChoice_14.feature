@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice-14 - Different types of Form File upload and removal in recorded by section.

  @NTS-6024 @NTS-3483 @Z-LOGOUT
    #@E2EUI-2341 @E2EUI-1890 @E2EUI-1950 @E2EUI-1826
  Scenario Outline: NTS-6024:E2EUI-2341:Scenario-1:Only one of each form type can be uploaded in the recorded by section for Adult(With Capacity) patient .
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1998:Gender=Male |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<PatientChoice>" stage
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
    When the user fills "<ClinicianNameWithFile>" details in recorded by
    Then the file type dropdown options loaded with below details
      | Record of Discussion Form |
      | Deceased Form             |
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user selects Record of Discussion Form from dropdown option in recorded by
    And the Date of Signature fields are displayed as enabled
    Then the user fills current date as Date of Signature
     ###2nd form upload
    When the user fills "<FormUpload>" details in recorded by
    Then the file type dropdown options loaded with below details for "2nd" form type
      | Deceased Form |
    Then the user sees a success message for "2nd" form type upload in recorded by as "Successfully Uploaded"
    And the user selects "Deceased Form" from the "2nd" form type dropdown option in recorded by
    And the Date of Signature fields for the "2nd" form type are displayed as "enabled"
    Then the user fills current date as Date of Signature in the "2nd" form type
    And the user will see a warning message "The maximum number of form types has been reached"
    And the user clicks on Continue Button
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | ClinicianNameWithFile                                                               | FormUpload                                   |
      | Patient choice | ClinicianName=Arthur:HospitalNumber=123:Action=UploadDocument:FileName=testfile.pdf | Action=UploadDocument:FileName=testfile2.pdf |

  @NTS-6024 @Z-LOGOUT
    #@E2EUI-2341
  Scenario Outline: NTS-6024:E2EUI-2341:Scenario-2:Only one of each form type can be uploaded in the recorded by section for Adult (Without Capacity) patient.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user is in the section Patient choice category
    When the user selects the option Adult (Without Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    And the option Adult (Without Capacity) displayed with edit option in Patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the Test type option is marked as completed
    And the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the user is in the section Recorded by
    When the user fills "<ClinicianNameWithFile>" details in recorded by
    Then the file type dropdown options loaded with below details
      | Record of Discussion Form |
      | Deceased Form             |
      | Consultee Form            |
    And the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user selects Record of Discussion Form from dropdown option in recorded by
    And the Date of Signature fields are displayed as enabled
    Then the user fills current date as Date of Signature
     ###2nd form upload
    When the user fills "<FormUpload>" details in recorded by
    Then the file type dropdown options loaded with below details for "2nd" form type
      | Deceased Form  |
      | Consultee Form |
    Then the user sees a success message for "2nd" form type upload in recorded by as "Successfully Uploaded"
    And the user selects "Deceased Form" from the "2nd" form type dropdown option in recorded by
    And the Date of Signature fields for the "2nd" form type are displayed as "enabled"
    Then the user fills current date as Date of Signature in the "2nd" form type
     ###3rd form upload
    When the user fills "<FormUpload2>" details in recorded by
    Then the file type dropdown options loaded with below details for "3rd" form type
      | Consultee Form |
    Then the user sees a success message for "3rd" form type upload in recorded by as "Successfully Uploaded"
    And the user selects "Consultee Form" from the "3rd" form type dropdown option in recorded by
    And the Date of Signature fields for the "3rd" form type are displayed as "enabled"
    Then the user fills current date as Date of Signature in the "3rd" form type
    And the user will see a warning message "The maximum number of form types has been reached"
    When the user clicks on Continue Button
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | ClinicianNameWithFile                                                              | FormUpload                                      | FormUpload2                                      |
      | Patient choice | ClinicianName=Henry:HospitalNumber=123:Action=UploadDocument:FileName=testfile.pdf | Action=UploadDocument:FileName=deceasedform.pdf | Action=UploadDocument:FileName=consulteeform.pdf |

  @NTS-6024 @Z-LOGOUT
    #@E2EUI-2341
  Scenario Outline: NTS-6024:E2EUI-2341:Scenario-3:Only one of each form type can be uploaded in the recorded by section for Child patient.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2008:Gender=Male |
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
    When the user fills "<ClinicianNameWithFile>" details in recorded by
    Then the file type dropdown options loaded with below details
      | Record of Discussion Form |
      | Deceased Form             |
      | Assent Form               |
    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user selects Record of Discussion Form from dropdown option in recorded by
    And the Date of Signature fields are displayed as enabled
    Then the user fills current date as Date of Signature
     ###2nd form upload
    When the user fills "<FormUpload>" details in recorded by
    Then the file type dropdown options loaded with below details for "2nd" form type
      | Deceased Form |
      | Assent Form   |
    Then the user sees a success message for "2nd" form type upload in recorded by as "Successfully Uploaded"
    And the user selects "Deceased Form" from the "2nd" form type dropdown option in recorded by
    And the Date of Signature fields for the "2nd" form type are displayed as "enabled"
    Then the user fills current date as Date of Signature in the "2nd" form type
     ###3rd form upload
    When the user fills "<FormUpload2>" details in recorded by
    Then the file type dropdown options loaded with below details for "3rd" form type
      | Assent Form |
    Then the user sees a success message for "3rd" form type upload in recorded by as "Successfully Uploaded"
    And the user selects "Assent Form" from the "3rd" form type dropdown option in recorded by
    And the Date of Signature fields for the "3rd" form type are displayed as "enabled"
    Then the user fills current date as Date of Signature in the "3rd" form type
    And the user will see a warning message "The maximum number of form types has been reached"
    When the user clicks on Continue Button
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | ClinicianNameWithFile                                                             | FormUpload                                      | FormUpload2                                   |
      | Patient choice | ClinicianName=Hank:HospitalNumber=123:Action=UploadDocument:FileName=testfile.pdf | Action=UploadDocument:FileName=deceasedform.pdf | Action=UploadDocument:FileName=assentform.pdf |
