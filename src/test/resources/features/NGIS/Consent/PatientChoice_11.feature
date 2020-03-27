#@regression
#@patientChoice
@CONSENT
@SYSTEM_TEST
Feature: Patient Choice-11 - validations

  @NTS-3478 @LOGOUT
    #@E2EUI-2153 @E2EUI-1677
  Scenario Outline: NTS-3478: Patient choice option content has changed to Record of Discussion form not currently available
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    ##For E2EUI-1677
    Then the user should be able to see patient hospital number
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3436 @LOGOUT
    #@E2EUI-1704
  Scenario Outline: NTS-3436: No question is populated for the Not applicable case under child assent in patient choice questions.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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

  @NTS-4307 @LOGOUT
    #@E2EUI-880
  Scenario Outline: NTS-3436: No question is populated for the Not applicable case under child assent in patient choice questions.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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

  @NTS-3378 @LOGOUT
    #@E2EUI-1181 @E2EUI-1752
  Scenario Outline: NTS-3378: Navigate around the patient choice pages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button on the patient choice
    ##Below step is for E2EUI-1752
    And the user should able to see TO DO list even after clicking the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice
    Examples:
      | PatientChoiceStage | RecordedBy                            |
      | Patient choice     | ClinicianName=John:HospitalNumber=123 |

  @NTS-3444 @LOGOUT
    #@E2EUI-1727
  Scenario Outline: NTS-3444 : Validating Patient choice section must be completed to submit the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1993:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    And the user submits the referral

    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  |
      | Patient details | Requesting organisation | Maidstone            | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | Patient choice |

  @NTS-3385 @LOGOUT
    #@E2EUI-1474
  Scenario Outline: NTS-3385: Create referral navigation component - Patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user is navigated to a page with title Add patient choice information
    And the user sees a back button on Add patient choice information page
    When the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3387 @LOGOUT
    #@E2EUI-1464 @E2EUI-1141
  Scenario Outline: NTS-3387: patient signature is a mandatory field in Add patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2014:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
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
    And the user should see patient choice submit button as disabled
    ##Include the step for clicking on Continue without providing signature and validate the warning message.
    Examples:
      | Patient choice stage | RecordedBy         | ErrorMessage                                                                           |
      | Patient choice       | ClinicianName=John | Please complete the required field Clinician Name (Admin support user ID is optional): |

   @NTS-3457 @LOGOUT
     #@E2EUI-1667
  Scenario Outline: NTS-3457: Warn a user that they will lose their changes when navigating away from patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2014:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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
#    When the user attempts to navigate away by clicking "back"
#    Then the user sees a prompt alert "<UnsavedMessage>" after clicking "backbutton" button and "<Dismiss>" it
#    And the user is navigated to a page with title Add patient choice information

    Examples:
      | PatientChoice  | Panels | DiscardMessage                                              | Dismiss|UnsavedMessage                    |
      | Patient choice | Panels | This section contains unsaved information. Discard changes? | Dismiss|Changes you made may not be saved |
