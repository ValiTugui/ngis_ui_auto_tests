@regression
@PatientChoice
Feature: Patient Choice Page

  @NTS-3417 @E2EUI-2040 @v_1 @P0
  Scenario Outline: NTS-3417: Editing Patient choice for an Adult (without capacity)
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Adult (Without Capacity) in patient choice category
    Then the option Adult (Without Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed

    Examples:
      | PatientChoiceStage | RecordedBy                            |
      | Patient choice     | ClinicianName=John:HospitalNumber=123 |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1
  Scenario Outline: NTS-3417: scenario 1 - Editing Patient choice for an Adult (without capacity)
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the options displayed as below for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
      | Consultee has agreed to the test                                   |
      | Consultee conversation happened; form to follow                    |
      | Consultee changed their mind about the clinical test               |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee changed their mind about the clinical test for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee changed their mind about the clinical test |
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Yes for the question I have been consulted about this person's participation in the National Genomic Research Library.
    Then the user should see the question displayed as I am willing to accept the role of consultee for this person.
    And the options displayed as below for the question I am willing to accept the role of consultee for this person.
      | Yes |
      | No  |
    When the user selects the option Yes for the question I am willing to accept the role of consultee for this person.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage2>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                                                                                                                                          |
      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(a)
  Scenario Outline: NTS-3417: scenario 1(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option No for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option No for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                             |
      | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_1(b)
  Scenario Outline: NTS-3417: scenario 1(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    When the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Not applicable for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Not applicable for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                             |
      | By hitting submit you are confirming that the consultee has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_2
  Scenario Outline: NTS-3417: scenario 2 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    Then the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the options displayed as below for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
      | Consultee has agreed to the test                                   |
      | Consultee conversation happened; form to follow                    |
      | Consultee changed their mind about the clinical test               |
      | Clinician has agreed to the test (in the Patient's best interests) |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee conversation happened; form to follow for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee conversation happened; form to follow |
    When the user is in the section Review and submit
    Then the user will see a warning message "<WarningMessage>"
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | WarningMessage                                                                                                                                                                                                                                                                                           |
      | By hitting submit you are confirming that either you have uploaded a valid record of discussion form and transcribed it correctly, or the clinical team has indicated that the patient has agreed to the test, but you are still awaiting a record of discussion form and will upload it when available. |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3
  Scenario Outline: NTS-3417: scenario 3 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the options displayed as below for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
      | Consultee has agreed to the test                                   |
      | Consultee conversation happened; form to follow                    |
      | Consultee changed their mind about the clinical test               |
      | Clinician has agreed to the test (in the Patient's best interests) |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee has agreed to the test for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                  |
      | Consultee would like to revisit at a later date   |
      | Patient lacks capacity and no consultee available |
      | Other                                             |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    Then the user should see selected details displayed under the section Patient choices
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::No                                                                                                                                         |
      | Why has research participation not been discussed?::Consultee would like to revisit at a later date                                                                                    |
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Yes for the question I have been consulted about this person's participation in the National Genomic Research Library.
    Then the user should see the question displayed as I am willing to accept the role of consultee for this person.
    And the options displayed as below for the question I am willing to accept the role of consultee for this person.
      | Yes |
      | No  |
    When the user selects the option No for the question I am willing to accept the role of consultee for this person.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(a)
  Scenario Outline: NTS-3417: scenario 3(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Not applicable for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_3(b)
  Scenario Outline: NTS-3417: scenario 3(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Not applicable for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option No for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4
  Scenario Outline: NTS-3417: scenario 4 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the options displayed as below for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
      | Consultee has agreed to the test                                   |
      | Consultee conversation happened; form to follow                    |
      | Consultee changed their mind about the clinical test               |
      | Clinician has agreed to the test (in the Patient's best interests) |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee has agreed to the test for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    When the user selects the option No for the question The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                        |
      | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.::No                                                                           |

    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Yes for the question I have been consulted about this person's participation in the National Genomic Research Library.
    Then the user should see the question displayed as I am willing to accept the role of consultee for this person.
    And the options displayed as below for the question I am willing to accept the role of consultee for this person.
      | Yes |
      | No  |
    When the user selects the option No for the question I am willing to accept the role of consultee for this person.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                | WarningMessage                                                                                                                                                      |
      | FirstName=WILTON:LastName=BRITTAIN | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_4(a)
  Scenario Outline: NTS-3417: scenario 4(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option No for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @LOGOUT @v_1 @P0 @scenario_4(b)
  Scenario Outline: NTS-3417: scenario 4(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option No for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Not applicable for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |


  @NTS-3417 @E2EUI-2040 @v_1 @P0
  Scenario Outline: NTS-3417: Pre-Req:Editing Patient choice for an Adult (without capacity)
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Adult (Without Capacity) in patient choice category
    Then the option Adult (Without Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoiceStage | RecordedBy                            |
      | Patient choice     | ClinicianName=John:HospitalNumber=123 |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5
  Scenario Outline: NTS-3417: scenario 5 - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    And the options displayed as below for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
      | Consultee has agreed to the test                                   |
      | Consultee conversation happened; form to follow                    |
      | Consultee changed their mind about the clinical test               |
      | Clinician has agreed to the test (in the Patient's best interests) |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Consultee has agreed to the test for the question Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
    And the options displayed as below for the question The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
      | Yes |
      | No  |
    When the user selects the option Yes for the question The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed

    And the user should see selected details displayed under the section Patient choices
      | Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?::Consultee has agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                                                        |
      | The consultee agrees that the patient's data and samples may be used for research, separate to NHS care.::Yes                                                                          |
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Yes for the question I have been consulted about this person's participation in the National Genomic Research Library.
    Then the user should see the question displayed as I am willing to accept the role of consultee for this person.
    And the options displayed as below for the question I am willing to accept the role of consultee for this person.
      | Yes |
      | No  |
    When the user selects the option Yes for the question I am willing to accept the role of consultee for this person.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @v_1 @P0 @scenario_5(a)
  Scenario Outline: NTS-3417: scenario 5(a) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Yes for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option Not applicable for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @E2EUI-2040 @LOGOUT @v_1 @P0 @scenario_5(b)
  Scenario Outline: NTS-3417: scenario 5(b) - Editing Patient choice for an Adult (without capacity)
    When the user clicks on edit button in Consultee attestation
    When the user is in the section Consultee attestation
    Then the user should see the question displayed as I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    And the options displayed as below for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
      | Yes            |
      | No             |
      | Not applicable |
    Then the user should see the question displayed as I have been consulted about this person's participation in the National Genomic Research Library.
    And the options displayed as below for the question I have been consulted about this person's participation in the National Genomic Research Library.
      | Yes            |
      | No             |
      | Not applicable |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option Not applicable for the question I have had the opportunity to read and discuss information about being a consultee for the person who lacks capacity
    When the user selects the option No for the question I have been consulted about this person's participation in the National Genomic Research Library.
    And the user clicks on Continue Button
    Then the Consultee attestation option is marked as completed
    When the user is in the section Consultee signature
    ##Check for the mandatory field and clear.......
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |