#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-11 - validations


  @NTS-3436 @NTS-4307 @Z-LOGOUT
   #@E2EUI-1704 @E2EUI-880
  Scenario Outline: NTS-3436: No question is populated for the Not applicable case under child assent in patient choice questions.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2006:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    ##Below step is for E2EUI-880
    And the user will see a notification warning message "<WarningMessage>"
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user is in the section Recorded by
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    Then the user should be able to see enabled continue button
    Examples:
      | PatientChoiceStage | RecordedBy                                                                                                     | WarningMessage                                                      |
      | Patient choice     | RecordingClinicianName=John Doe:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | A laboratory cannot start a test without patient choice information |

  @NTS-3378 @NTS-3385 @NTS-3387 @Z-LOGOUT
   #@E2EUI-1181 @E2EUI-1752 @E2EUI-1474 @E2EUI-1464 @E2EUI-1141
  Scenario Outline: NTS-3378: Navigate around the patient choice pages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    And the user is in the section Recorded by
    ##Below two step for E2EUI-1141
    And the user clicks on Continue Button
    Then the user will be able to see an error message as "<ErrorMessage>"
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    ##Below two step for NTS-3387
    And the user should see patient choice submit button as disabled
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button on the patient choice
    ##Below step is for E2EUI-1752
    And the user should able to see TO DO list even after clicking the Save and Continue button
    ##Steps for NTS-3385
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice
    Examples:
      | PatientChoiceStage | RecordedBy                            | ErrorMessage                                                                           |
      | Patient choice     | ClinicianName=John:HospitalNumber=123 | Please complete the required field Clinician Name (Admin support user ID is optional): |

  @NTS-3457 @Z-LOGOUT
     #@E2EUI-1667
  Scenario Outline: NTS-3457: Warn a user that they will lose their changes when navigating away from patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2014:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user is in the section Patient choice category
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    When the user navigates to the "<Panels>" stage
    Then the user sees a prompt alert "<DiscardMessage>" after clicking "<Panels>" button and "<Dismiss>" it
    And the user is navigated to a page with title Add patient choice information
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<UnsavedMessage>" after clicking "refresh" button and "<Dismiss>" it
    And the user is navigated to a page with title Add patient choice information
    When the user clicks the Log out button
    Then the user sees a prompt alert "<UnsavedMessage>" after clicking "logout" button and "<Dismiss>" it
    And the user is navigated to a page with title Add patient choice information

    Examples:
      | PatientChoice  | Panels | DiscardMessage                                              | Dismiss|UnsavedMessage                    |
      | Patient choice | Panels | This section contains unsaved information. Discard changes? | Dismiss|Changes you made may not be saved |