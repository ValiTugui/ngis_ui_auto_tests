@regression
@patientChoice

Feature: Patient Choice Page

  @NTS-3434 @E2EUI-1447 @v_1 @P0
  Scenario Outline: NTS-3434: Verify the relevant Patient choice for an Adult with capacity paper form
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
    When the user fills "<RecordedBy>" details in recorded by
    Then the user will see a warning message "<WarningMessage>"
    Then the user sees a success message after form upload in recorded by as "<FormSuccessMessage>"
    And the user will see a "<Message>" message on upload section
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    Examples:
      | PatientChoice  | RecordedBy                                                                                                           | FormSuccessMessage    | WarningMessage                                                                                                                                                                                                                                           | Message                                                                                                                                                                              |
      | Patient choice | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Successfully Uploaded | You are the appointed administrator for ensuring that the Patient's Genomic Test decisions are accurately reproduced in this digital form. Please ensure that you take care to enter the answers as described on the paper record of decisions attached. | Your file may take a minute to upload, depending on its size. You can continue to fill out this form, but will not be able to complete and submit it until the upload has completed. |

  @NTS-3434 @E2EUI-1447 @v_1 @P0 @scenario_01
  Scenario Outline: NTS-3434: scenario_01 - Verify the relevant Patient choice for an Adult with capacity
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient changed their mind about the clinical test |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3434 @E2EUI-1447 @v_1 @P0 @scenario_02
  Scenario Outline: NTS-3434: scenario_02 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient conversation happened; form to follow |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                                                                                                                                           |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3434 @E2EUI-1447 @v_1 @P0 @scenario_03
  Scenario Outline: NTS-3434: scenario_03 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                  |
      | Patient would like to revisit at a later date     |
      | Patient lacks capacity and no consultee available |
      | Other                                             |
    And the user will see a warning message "<WarningMessage>"
    When the user selects the option Patient would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    Then the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient has agreed to the test |
      | Has research participation been discussed?::No                                                                                                            |
      | Why has research participation not been discussed?::Patient would like to revisit at a later date                                                         |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                         | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3434 @E2EUI-1447 @v_1 @P0 @scenario_04
  Scenario Outline: NTS-3434: scenario_04 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    When the user selects the option No for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    Then the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                           |
      | The patient agrees that their data and samples may be used for research, separate to NHS care.::No                                                        |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled
    Examples:
      | WarningMessage                                                                                                                                                      | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @LOGOUT @NTS-3434 @E2EUI-1447 @v_1 @P0 @scenario_05
  Scenario Outline: NTS-3434: scenario_05 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Patient has agreed to the test                                     |
      | Patient conversation happened; form to follow                      |
      | Patient changed their mind about the clinical test                 |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient agrees that their data and samples may be used for research, separate to NHS care.
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    Then the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                           |
      | The patient agrees that their data and samples may be used for research, separate to NHS care.::Yes                                                       |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled
    Examples:
      | WarningMessage                                                                                                                                                                                                                                                                                           |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |