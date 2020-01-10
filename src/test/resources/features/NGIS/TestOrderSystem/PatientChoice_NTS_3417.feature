@regression
@PatientChoice
Feature: Patient Choice Page

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_01 @NTS-3417 @E2EUI-2040 @v_1 @P0
  Scenario Outline: NTS-3417: Editing Patient choice for an Adult (without capacity)
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (Without Capacity) in patient choice category
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
      | PatientChoiceStage | PatientChoiceCategory    | TestType                        | RecordedBy                            |
      | Patient choice     | Adult (Without Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_02 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1
  Scenario Outline: NTS-3417: scenario 1 - Editing Patient choice for an Adult (without capacity)
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      | Consultee has agreed to the test                     |
      | Record of Discussion form not currently available    |
      | Consultee changed their mind about the clinical test |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoiceOption3>" as patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee changed their mind about the clinical test |
    When the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see the section title as I am willing to accept the role of consultee for this person.
    And the patient choice options as below
      | Yes |
      | No  |
    And the user selects "<YesOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoiceOption3                                 | YesOption | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                            |
      | Consultee changed their mind about the clinical test | Yes       | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_03 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(a)
  Scenario Outline: NTS-3417: scenario 1(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<NoOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<NoOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | NoOption | WarningMessage2                                                                                                                                                                            |
      | No       | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_04 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(b)
  Scenario Outline: NTS-3417: scenario 1(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption    | SecondOption   | WarningMessage2                                                                                                                                                                            |
      | Not applicable | Not applicable | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_05 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_2
  Scenario Outline: NTS-3417: scenario 2 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      | Consultee has agreed to the test                     |
      | Record of Discussion form not currently available    |
      | Consultee changed their mind about the clinical test |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice2>" as patient choices
    Then the user should see the section title as Reason for not capturing patient choice:
    And the patient choice reason options as below
      | Patient conversation happened; form to follow               |
      | Test does not require recording of patient choices          |
      | Patient currently lacks capacity and no consultee available |
      | Associated with another referral                            |
      | Other                                                       |
    ##Click on continue without selecting any option and validate warning message
    When the user selects the option "<Reason3>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Record of Discussion form not currently available |
      | Reason for not capturing patient choice: ::Patient currently lacks capacity and no consultee available                                                                                                   |
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice2                                    | Reason3                                                     | WarningMessage2                                                                                                                                                                            |
      | Record of Discussion form not currently available | Patient currently lacks capacity and no consultee available | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_06 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3
  Scenario Outline: NTS-3417: scenario 3 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      | Consultee has agreed to the test                     |
      | Record of Discussion form not currently available    |
      | Consultee changed their mind about the clinical test |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice1>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<NoOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    ##Question Why has research participation not been discussed? with options presence need to check
    ##And include the rrro option for not seleting and goign ahead
    And the user should see continue button is not highlighted
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice2>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::No                                                                                                                                         |
      | Why has research participation not been discussed?::Consultee would like to revisit at a later date                                                                                    |
    When the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see the section title as I am willing to accept the role of consultee for this person.
    And the patient choice options as below
      | Yes |
      | No  |
    And the user selects "<NoOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    ##For all disabled buttons, pleae click and verify for the warning message button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                   | PatientChoice2                                  | Consultee signature                | WarningMessage                                                                                                         | NoOption | YesOption | Question1                                  | Question2                                          |
      | Consultee has agreed to the test | Consultee would like to revisit at a later date | FirstName=WILTON:LastName=BRITTAIN | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | No       | Yes       | Has research participation been discussed? | Why has research participation not been discussed? |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_07 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(a)
  Scenario Outline: NTS-3417: scenario 3(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption | SecondOption   | Consultee signature                |
      | Yes         | Not applicable | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_08 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(b)
  Scenario Outline: NTS-3417: scenario 3(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption    | SecondOption | Consultee signature                |
      | Not applicable | No           | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_09 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4
  Scenario Outline: NTS-3417: scenario 4 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      | Consultee has agreed to the test                     |
      | Record of Discussion form not currently available    |
      | Consultee changed their mind about the clinical test |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                                                                      |
      | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.::No                                                                           |
    When the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see the section title as I am willing to accept the role of consultee for this person.
    And the patient choice options as below
      | Yes |
      | No  |
    And the user selects "<NoOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    Then the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                    | Consultee signature                | WarningMessage                                                                                                                                                      | NoOption | YesOption | Question1                                  | Question2                                                                                                |
      | Consultee has agreed to the test | FirstName=WILTON:LastName=BRITTAIN | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | No       | Yes       | Has research participation been discussed? | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_10 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4(a)
  Scenario Outline: NTS-3417: scenario 4(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption | SecondOption | Consultee signature                |
      | Yes         | No           | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_11 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4(b)
  Scenario Outline: NTS-3417: scenario 4(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption | SecondOption   | Consultee signature                |
      | No          | Not applicable | FirstName=WILTON:LastName=BRITTAIN |


  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_12 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5
  Scenario Outline: NTS-3417: scenario 5 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      | Consultee has agreed to the test                     |
      | Record of Discussion form not currently available    |
      | Consultee changed their mind about the clinical test |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                        |
      | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.::Yes                                                                          |
    When the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see the section title as I am willing to accept the role of consultee for this person.
    And the patient choice options as below
      | Yes |
      | No  |
    And the user selects "<YesOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                    | Consultee signature                | YesOption | Question1                                  | Question2                                                                                                |
      | Consultee has agreed to the test | FirstName=WILTON:LastName=BRITTAIN | Yes       | Has research participation been discussed? | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care. |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_13 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5(a)
  Scenario Outline: NTS-3417: scenario 5(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption | SecondOption   | Consultee signature                |
      | Yes         | Not applicable | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_NTS3417_14 @LOGOUT @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5(b)
  Scenario Outline: NTS-3417: scenario 5(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    Then the user should see the section title as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the section title as I have been consulted about this person's participation in the National Genomic Research Library.
    And the patient choice options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<FirstOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<SecondOption>" National Genomic Research Library for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | FirstOption    | SecondOption | Consultee signature                |
      | Not applicable | No           | FirstName=WILTON:LastName=BRITTAIN |