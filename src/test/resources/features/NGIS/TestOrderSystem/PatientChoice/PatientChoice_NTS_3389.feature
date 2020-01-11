@regression
@COMP09_P0
@PatientChoice
Feature: Patient Choice Page

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_01 @NTS-3389 @E2EUI-2039 @v_1 @P0
  Scenario Outline: NTS-3389: Verify the relevant Patient choice for an Adult with capacity
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user should see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user should see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user should see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the Recorded by option is marked as completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_02 @NTS-3389 @E2EUI-2039 @v_1 @P0 @scenario_01
  Scenario Outline: NTS-3389: scenario_01 - Verify the relevant Patient choice for an Adult with capacity
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Patient has agreed to the test                     |
      | Record of Discussion form not currently available  |
      | Patient changed their mind about the clinical test |
    When the user selects the option "<PatientChoice>" as patient choices
    Then the user will see a "<WarningMessage1>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Yes |
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                                      | WarningMessage1                                                                                                                                               | WarningMessage2                                                                                                                                                                          |
      | Patient changed their mind about the clinical test | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_03 @NTS-3389 @E2EUI-2039 @v_1 @P0 @scenario_02
  Scenario Outline: NTS-3389: scenario_02 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    And the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Patient has agreed to the test                     |
      | Record of Discussion form not currently available  |
      | Patient changed their mind about the clinical test |
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice1>" as patient choices
    Then the user should see the section title as Reason for not capturing patient choice:
    And the patient choice reason options as below
      | Patient conversation happened; form to follow               |
      | Test does not require recording of patient choices          |
      | Patient currently lacks capacity and no consultee available |
      | Associated with another referral                            |
      | Other                                                       |
    When the user selects the option "<Reason5>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Yes |
      | Reason for not capturing patient choice: ::Other                                                                               |
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                    | Reason5 | WarningMessage                                                                                                                                                                           |
      | Record of Discussion form not currently available | Other   | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_04 @NTS-3389 @E2EUI-2039 @v_1 @P0 @scenario_03
  Scenario Outline: NTS-3389: scenario_03 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    And the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Patient has agreed to the test                     |
      | Record of Discussion form not currently available  |
      | Patient changed their mind about the clinical test |
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice1>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user should see continue button is not highlighted
    And the user selects "<NoOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice2>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Yes |
      | Has research participation been discussed?::No                                                                                 |
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                 | NoOption | Question1                                  | Question2                                          | WarningMessage                                                                                                         | PatientChoice2                                |
      | Patient has agreed to the test | No       | Has research participation been discussed? | Why has research participation not been discussed? | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | Patient would like to revisit at a later date |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_05 @NTS-3389 @E2EUI-2039 @v_1 @P0 @scenario_04
  Scenario Outline: NTS-3389: scenario_04 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Patient has agreed to the test                     |
      | Record of Discussion form not currently available  |
      | Patient changed their mind about the clinical test |
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should be able to see Yes and No answer options for the question
    And the user should see continue button is not highlighted
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Yes |
      | Has research participation been discussed?::Yes                                                                                |
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                  | YesOption | NoOption | Question1                                  | WarningMessage                                                                                                                                                      | Question2                                                                                      |
      | Patient has agreed to the test | Yes       | No       | Has research participation been discussed? | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | The patient agrees that their data and samples may be used for research, separate to NHS care. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3389_06 @LOGOUT @NTS-3389 @E2EUI-2039 @v_1 @P0 @scenario_05
  Scenario Outline: NTS-3389: scenario_05 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Patient has agreed to the test                     |
      | Record of Discussion form not currently available  |
      | Patient changed their mind about the clinical test |
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should be able to see Yes and No answer options for the question
    And the user should see continue button is not highlighted
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Yes |
      | Has research participation been discussed?::Yes                                                                                |
      | The patient agrees that their data and samples may be used for research, separate to NHS care.::Yes                            |
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                  | YesOption | Question1                                  | Question2                                                                                      |
      | Patient has agreed to the test | Yes       | Has research participation been discussed? | The patient agrees that their data and samples may be used for research, separate to NHS care. |
