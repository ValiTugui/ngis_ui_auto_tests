@regression
@patientChoice
@patientChoice_navigation
Feature: Patient Choice Navigation

  @NTS-3409 @E2EUI-1822 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3409: Navigate around the patient choice pages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
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
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3411 @E2EUI-1960 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3411: Adult (With Capacity): Verify the info message when user declined for a test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2010:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<InfoMessage>"

    Examples:
      | Patient choice stage | RecordedBy                            | InfoMessage                                                                                                                                                   |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @NTS-3411 @E2EUI-1960 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3411: Child: Verify the info message when user declined for a test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2009:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    Then the option Cancer (paired tumour normal) – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<InfoMessage>"

    Examples:
      | Patient choice stage | RecordedBy                            | InfoMessage                                                                                                                                                   |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @NTS-3410 @E2EUI-1127 @E2EUI-1934 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3410: Verify the patient Choice stage marked as completed
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3418 @E2EUI-1702 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3418: Validation of change in research message inside patient choices section if I change my choice to participate in research.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2006:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option No for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage1>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    When the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage2>"
    And the user selects the New patient choice tab in patient choice page
    Then the user is navigated to a page with title Add patient choice information
    When the user clicks on the amend patient choice button
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage3>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user selects the Preferences tab in patient choice page
    And the user will see a warning message "<WarningMessage2>"

    Examples:
      | Patient choice stage | RecordedBy                            | WarningMessage1                                                                                                                                                     | WarningMessage3                                                                                                                                                            | WarningMessage2                                                                                                         |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | If you change this choice it will also apply to any genomic tests the patient has previously had. This will also apply to any future tests, unless they change their mind. | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. |

  @NTS-3446 @E2EUI-2035 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3446: As a user, I should be able to edit test type for a family member in the patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    And the user is in the section Recorded by
    When the user clicks on edit button in Test type
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    Then the option Cancer (paired tumour normal) – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    And the user is in the section Recorded by
    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3472 @E2EUI-2149 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3472: Remove Child Assent section where paper form is not present
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian conversation happened; form to follow for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian conversation happened; form to follow |
    When the user is in the section Review and submit

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3471 @E2EUI-2155 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3471: Verify the child assent question content
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    When the user selects the option Parent(s) / guardian would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |

    Examples:
      | Patient choice stage | RecordedBy                                                                                                           |
      | Patient choice       | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |

  @NTS-3415 @E2EUI-1627 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3415: Verify the patient Choice 'Save & Continue' button is disabled until the patient choice has been submitted
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1991:Gender=Male |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user selects the Relationship to proband as "Full Sibling"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the user clicks on Continue Button
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
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                  |
      | Patient would like to revisit at a later date     |
      | Patient lacks capacity and no consultee available |
      | Other                                             |
    And the user will see a warning message "<WarningMessage>"
    And the user should see Continue button as disabled
    When the user selects the option Patient would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    Then the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient has agreed to the test |
      | Has research participation been discussed?::No                                                                                                            |
      | Why has research participation not been discussed?::Patient would like to revisit at a later date                                                         |
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | ClinicalQuestionDetails                 | WarningMessage                                                                                                         | RecordedBy                            |
      | Family members | NHSNumber=9449305919:DOB=24-07-2011 | DiseaseStatus=Affected:AgeOfOnset=02,02 | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | ClinicianName=John:HospitalNumber=123 |

  @NTS-4603 @E2EUI-1892 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4603: Moving the warn message to research section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Child in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user is in the section Recorded by
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    When the user selects the option No for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    ###Again editing the Proband
    And the user selects the proband
    And the user is navigated to a page with title Add patient choice information
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on the amend patient choice button
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button

    Examples:
      | Patient choice stage | RecordedBy                              | WarningMessage                                                                                                                                                                              |
      | Patient choice       | ClinicianName=Johny:HospitalNumber=1234 | A patient choice record currently exists for this test referral ID. You may create another to override the current record by clicking the 'Amend patient choice for referral' button below. |

        ###Commented the below test, as this is about checking the session expiry time. Manual team suggesetd they may take it separately.
#  @NTS-3473 @E2EUI-2037 @LOGOUT @v_1 @P1
#  Scenario Outline: NTS-3473: Patient Choice critical error Should not be occurred when refresh token expires
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
#     #patient choice for the proband
#    When the user navigates to the "<PatientChoice>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    And the user should wait for session expiry time 740 seconds
#    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
#    And the user submits the patient choice with signature
#    And the user clicks the Save and Continue button on the patient choice
#    Then the "<PatientChoice>" page is displayed
#    Then the help text is displayed
#    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
#    Examples:
#      | PatientChoice  |
#      | Patient choice |
#
