#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-4 Edit Paper Form - Child

  @NTS-3428
    #@E2EUI-2041 @E2EUI-1392
  Scenario Outline: NTS-3428: Editing Patient choice for a Child in person
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | ClinicianName                         |
      | Patient choice | ClinicianName=John:HospitalNumber=123 |

  @NTS-3428
    #@E2EUI-2041 @scenario_1
  Scenario Outline: NTS-3428: scenario 1 - Editing Patient choice for a Child in person
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    ##Above options has changed due to CRO11 - Just an information
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<WarningMessage1>"
    When the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian changed their mind about the clinical test |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see Continue button as disabled
    When the user selects the option No for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user should see selected details displayed under the section Child assent
      | Does the child agree to participate in research?::No |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage1                                                                                                                                               | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @NTS-3428
    #@E2EUI-2041 @scenario_2
  Scenario Outline: NTS-3428: scenario 2 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    Then the user should be able to see previous section re-opened
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
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                                                                                                                                           |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3428
    #@E2EUI-2041 @scenario_3
  Scenario Outline: NTS-3428: scenario 3 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                           |
      | Parent(s) / guardian would like to revisit at a later date |
      | Patient lacks capacity and no consultee available          |
      | Other                                                      |
    And the user will see a warning message "<WarningMessage>"
    When the user selects the option Parent(s) / guardian would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    Then the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian have agreed to the test |
      | Has research participation been discussed?::No                                                                                                                                        |
      | Why has research participation not been discussed?::Parent(s) / guardian would like to revisit at a later date                                                                        |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see Continue button as disabled
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user should see selected details displayed under the section Child assent
      | Does the child agree to participate in research?::Not applicable |
    When the user is in the section Parent/Guardian signature
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field            | field_type | symbol | symbol color |
      | Parent/Guardian first name | label      | ✱      | #dd2509      |
      | Parent/Guardian last name  | label      | ✱      | #dd2509      |
    And the user fills "<Parent/Guardian signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                         | Parent/Guardian signature          |
      | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | FirstName=WILTON:LastName=BRITTAIN |

    ### Parent(s) / guardian have agreed to the test  - Yes - No
    ### Child Assent - Not applicable
  @NTS-3428
    #@E2EUI-2041 @scenario_4
  Scenario Outline: NTS-3428: scenario 4 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option No for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage>"
    ###Click on show additional warnings link and ensure the additional warning message displayed
    ###Verify for the link Add Partner Details (trio analysis only)
    And the user clicks on Continue Button
    Then the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian have agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                       |
      | The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care::No                                                      |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see Continue button as disabled
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user should see selected details displayed under the section Child assent
      | Does the child agree to participate in research?::Not applicable |
    When the user is in the section Parent/Guardian signature
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field            | field_type | symbol | symbol color |
      | Parent/Guardian first name | label      | ✱      | #dd2509      |
      | Parent/Guardian last name  | label      | ✱      | #dd2509      |
    And the user fills "<Parent/Guardian signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled
    Examples:
      | WarningMessage                                                                                                                                                      | Parent/Guardian signature          |
      | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | FirstName=WILTON:LastName=BRITTAIN |

    ### Parent(s) / guardian have agreed to the test  - Yes - Yes
    ### Child Assent - Yes
  @NTS-3428 @Z-LOGOUT
    #@E2EUI-2041 @scenario_5
  Scenario Outline: NTS-3428: scenario 5 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    And the user should see Continue button as disabled
    When the user selects the option Yes for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
     ###Click on show additional warnings link and ensure the additional warning message displayed
    ###Verify for the link Add Partner Details (trio analysis only)
    Then the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian have agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                       |
      | The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care::Yes                                                     |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see Continue button as disabled
    When the user selects the option Yes for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user fills PatientSignature details in patient signature
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user should see selected details displayed under the section Child assent
      | Does the child agree to participate in research?::Yes |
    When the user is in the section Parent/Guardian signature
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field            | field_type | symbol | symbol color |
      | Parent/Guardian first name | label      | ✱      | #dd2509      |
      | Parent/Guardian last name  | label      | ✱      | #dd2509      |
    And the user fills "<Parent/Guardian signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Parent/Guardian signature          |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-4603 @Z-LOGOUT
    #@E2EUI-1892
  Scenario Outline: NTS-4603: Moving the warn message to research section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
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