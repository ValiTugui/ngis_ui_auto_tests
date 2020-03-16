#@regression
#@patientChoice
#@patientChoice_consentChild
@CONSENT
@SYSTEM_TEST
Feature: Patient Choice ConsentScenario - Child

  @NTS-3441
#    @E2EUI-1215
  Scenario Outline: NTS-3441: Verify the relevant Patient choice for a Child paper form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
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
    Then the user will see a warning message "<WarningMessage>"
    When the user fills "<RecordedBy>" details in recorded by
    And the user sees a success message after form upload in recorded by as Successfully Uploaded
    And the user will see a warning message "<Message>"
    And the user clicks on Continue Button
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoice  | RecordedBy                                                                                                           | FormSuccessMessage    | WarningMessage                                                                                                                                                                                                                                           | Message                                                                                                                         |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Successfully Uploaded | You are the appointed administrator for ensuring that the Patient's Genomic Test decisions are accurately reproduced in this digital form. Please ensure that you take care to enter the answers as described on the paper record of decisions attached. | Please make sure you have uploaded all required forms (child assent, consultee, etc.), you currently have uploaded the files... |

  @NTS-3441
#    @E2EUI-1215 @scenario_1
  Scenario Outline: NTS-3441: scenario 1 - Verify the relevant Patient choice for a Child paper form
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<WarningMessage>"
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
      | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3441
#    @E2EUI-1215 @scenario_2
  Scenario Outline: NTS-3441: scenario 2 - Verify the relevant Patient choice for a Child paper form
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
    Then the Child assent option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                                                                                                                                           |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3441
#    @E2EUI-1215 @scenario_3
  Scenario Outline: NTS-3441: scenario 3 - Verify the relevant Patient choice for a Child paper form
    When the user clicks on edit button in Patient choices
    Then the user should be able to see previous section re-opened
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
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion |
      | Parent(s) / guardian would like to revisit at a later date  |
      | Patient lacks capacity and no consultee available  |
      | Other  |
    When the user selects the option Parent(s) / guardian would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user will see a warning message "<WarningMessage1>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian have agreed to the test |
      | Has research participation been discussed?::No                                                                                                                                        |
      | Why has research participation not been discussed?::Parent(s) / guardian would like to revisit at a later date                                                                        |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage1                                                                                                        | WarningMessage2    |
      | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3441
#    @E2EUI-1215 @scenario_4
  Scenario Outline: NTS-3441: scenario 4 - Verify the relevant Patient choice for a Child paper form
    When the user clicks on edit button in Patient choices
    Then the user should be able to see previous section re-opened
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
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    When the user selects the option No for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    Then the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian have agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                       |
      | The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.::No                                                     |
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage2    | WarningMessage                                                                                                                                                      |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. |

  @NTS-3441 @LOGOUT
#    @E2EUI-1215 @LOGOUT  @scenario_5
  Scenario Outline: NTS-3441: scenario 5 - Verify the relevant Patient choice for a Child paper form
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
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    When the user selects the option Yes for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
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
    When the user selects the option Yes for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user should see selected details displayed under the section Child assent
      | Does the child agree to participate in research?::Yes |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage    |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |
