@regression
@PatientChoicePage_4
@PatientChoice
Feature: Patient Choice Page

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_01 @NTS-3417 @E2EUI-2040 @v_1 @P0
  Scenario Outline: NTS-3417: Editing Patient choice for an Adult (without capacity)
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
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
      | Patient choice stage | PatientChoiceCategory    | TestType                        | RecordedBy                            |
      | Patient choice       | Adult (Without Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_02 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1
  Scenario Outline: NTS-3417: scenario 1 - Editing Patient choice for an Adult (without capacity)
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
    |Consultee has agreed to the test|
    |Record of Discussion form not currently available|
    |Consultee changed their mind about the clinical test|
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoiceOption1>" as patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
    And the user clicks on Continue Button
    Then Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see a new question and having Yes and No answer options in Consultee Attestation
    And the user selects "<YesOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoiceOption1                                        | YesOption | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                            |
      | Consultee changed their mind about the clinical test | Yes       | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_03 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(a)
  Scenario Outline: NTS-3417: scenario 1(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
      | YesOption | NoOption | WarningMessage2                                                                                                                                                                            |
      | Yes       | No       | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_04 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(b)
  Scenario Outline: NTS-3417: scenario 1(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_05 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_2
  Scenario Outline: NTS-3417: scenario 2 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      |Consultee has agreed to the test|
      |Record of Discussion form not currently available|
      |Consultee changed their mind about the clinical test|
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice>" as patient choices
#    Then the user should be able to see all the details of patient choices reasons
    Then the user should see the section title as Reason for not capturing parent(s) / carer / guardian choice:
    And the patient choice reason options as below
      |Patient conversation happened; form to follow|
      |Test does not require recording of patient choices|
      |Patient currently lacks capacity and no consultee available|
      |Associated with another referral|
      |Other|
    When the user selects the option "<Reasons>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                                     | Reasons                                                     | WarningMessage2                                                                                                                                                                            |
      | Record of Discussion form not currently available | Patient currently lacks capacity and no consultee available | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_06 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3
  Scenario Outline: NTS-3417: scenario 3 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      |Consultee has agreed to the test|
      |Record of Discussion form not currently available|
      |Consultee changed their mind about the clinical test|
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<NoOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user should see continue button is not highlighted
    When the user selects the option "<PatientChoice2>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see a new question and having Yes and No answer options in Consultee Attestation
    And the user selects "<NoOption>" willing to accept the role of consultee for the person in Consultee Attestation
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    And the user is navigated to a patient choice form option with title Consultee signature
    When the user fills "<Consultee signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                    | PatientChoice2                                  | Consultee signature                | WarningMessage                                                                                                         | NoOption | YesOption | Question1                                  | Question2                                          |
      | Consultee has agreed to the test | Consultee would like to revisit at a later date | FirstName=WILTON:LastName=BRITTAIN | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | No       | Yes       | Has research participation been discussed? | Why has research participation not been discussed? |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_07 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(a)
  Scenario Outline: NTS-3417: scenario 3(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_08 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(b)
  Scenario Outline: NTS-3417: scenario 3(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_09 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4
  Scenario Outline: NTS-3417: scenario 4 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      |Consultee has agreed to the test|
      |Record of Discussion form not currently available|
      |Consultee changed their mind about the clinical test|
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
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see a new question and having Yes and No answer options in Consultee Attestation
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
    @patientChoice_Page4_10 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4(a)
  Scenario Outline: NTS-3417: scenario 4(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_11 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4(b)
  Scenario Outline: NTS-3417: scenario 4(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_12 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5
  Scenario Outline: NTS-3417: scenario 5 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the patient choice options as below
      |Consultee has agreed to the test|
      |Record of Discussion form not currently available|
      |Consultee changed their mind about the clinical test|
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
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    And the user selects "<YesOption>" lacks capacity of consultee for the person in Consultee Attestation
    And the user selects "<YesOption>" National Genomic Research Library for the person in Consultee Attestation
    Then the user should see a new question and having Yes and No answer options in Consultee Attestation
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
    @patientChoice_Page4_13 @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5(a)
  Scenario Outline: NTS-3417: scenario 5(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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
    @patientChoice_Page4_14 @LOGOUT @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5(b)
  Scenario Outline: NTS-3417: scenario 5(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user is navigated to a patient choice form option with title Consultee attestation
    And the user should verify the questions and options in consultee attestation
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


  @COMP9_TO_PatientChoice
    @patientChoice_Page4_15 @NTS-3428 @E2EUI-2041 @v_1 @P0
  Scenario Outline: NTS-3428: Editing Patient choice for a Child in person
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
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
      | Patient choice       | Child                 | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_16 @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_1
  Scenario Outline: NTS-3428: scenario 1 - Editing Patient choice for a Child in person
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      |Parent(s) / carer / guardian have agreed to the test|
      |Record of Discussion form not currently available|
      |Parent(s) / carer / guardian changed their mind about the clinical test|
     And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice>" as patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      |Yes|
      |No|
      |Not applicable|
    And the user should see continue button is not highlighted
    And the user selects "<Option>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice                                                           | Option | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                          |
      | Parent(s) / carer / guardian changed their mind about the clinical test | No     | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_17 @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_2
  Scenario Outline: NTS-3428: scenario 2 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      |Parent(s) / carer / guardian have agreed to the test|
      |Record of Discussion form not currently available|
      |Parent(s) / carer / guardian changed their mind about the clinical test|
    When the user selects the option "<PatientChoice2>" as patient choices
    Then the user should see the section title as Reason for not capturing parent(s) / carer / guardian choice:
    And the patient choice reason options as below
      |Patient conversation happened; form to follow|
      |Test does not require recording of patient choices|
      |Patient currently lacks capacity and no consultee available|
      |Associated with another referral|
      |Other|
    And the user should see continue button is not highlighted
    When the user selects the option "<Reason5>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice2                                     | Reason5 | WarningMessage                                                                                                                                                                           |
      | Record of Discussion form not currently available | Other   | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_18 @LOGOUT @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_3
  Scenario Outline: NTS-3428: scenario 3 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      |Parent(s) / carer / guardian have agreed to the test|
      |Record of Discussion form not currently available|
      |Parent(s) / carer / guardian changed their mind about the clinical test|
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
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
      |Has research participation been discussed?|
      |Why has research participation not been discussed?|
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      |Yes|
      |No|
      |Not applicable|
    And the user should see continue button is not highlighted
    And the user selects "<Option>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | PatientChoice2                                                     | NoOption | Option         | Question1                                  | Question2                                          | WarningMessage                                                                                                         | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | Parent(s) / carer / guardian would like to revisit at a later date | No       | Not applicable | Has research participation been discussed? | Why has research participation not been discussed? | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_15 @NTS-3428 @E2EUI-2041 @v_1 @P0
  Scenario Outline: NTS-3428:scenario 3a -  Editing Patient choice for a Child in person
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
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
      | Patient choice       | Child                 | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_19 @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_4
  Scenario Outline: NTS-3428: scenario 4 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      |Parent(s) / carer / guardian have agreed to the test|
      |Record of Discussion form not currently available|
      |Parent(s) / carer / guardian changed their mind about the clinical test|
    When the user selects the option "<PatientChoice1>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
      |Has research participation been discussed?|
      |The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care.|
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      |Yes|
      |No|
      |Not applicable|
    And the user should see continue button is not highlighted
    And the user selects "<NAOption>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | NoOption | YesOption | NAOption       | Question1                                  | Question2                                                                                                                             | WarningMessage                                                                                                                                                      | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | No       | Yes       | Not applicable | Has research participation been discussed? | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care. | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | FirstName=WILTON:LastName=BRITTAIN |

  @COMP9_TO_PatientChoice
    @patientChoice_Page4_20 @LOGOUT @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_5
  Scenario Outline: NTS-3428: scenario 5 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      |Parent(s) / carer / guardian have agreed to the test|
      |Record of Discussion form not currently available|
      |Parent(s) / carer / guardian changed their mind about the clinical test|
    When the user selects the option "<PatientChoice1>" as patient choices
    And the question will be displayed as "<Question1>"
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      |Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?|
      |Has research participation been discussed?|
      |The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care.|
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      |Yes|
      |No|
      |Not applicable|
    And the user should see continue button is not highlighted
    And the user selects "<YesOption>" agree to participate in research for Child Assent
    And the user fills signature details in Child signature
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | YesOption | Question1                                  | Question2                                                                                                                             | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | Yes       | Has research participation been discussed? | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care. | FirstName=WILTON:LastName=BRITTAIN |
