@regression
@PatientChoice
Feature: Patient Choice Page

  @NTS-3428 @E2EUI-2041 @v_1 @P0
  Scenario Outline: NTS-3428: Editing Patient choice for a Child in person
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national | child |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    And the user should see the chosen "Child" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user should see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user should see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the Recorded by option is marked as completed

    Examples:
      | Patient choice stage | TestType                        | RecordedBy                            |
      | Patient choice       | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_1
  Scenario Outline: NTS-3428: scenario 1 - Editing Patient choice for a Child in person
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Parent(s) / carer / guardian have agreed to the test                    |
      | Record of Discussion form not currently available                       |
      | Parent(s) / carer / guardian changed their mind about the clinical test |
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<PatientChoice3>" as patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / carer / guardian changed their mind about the clinical test |
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see continue button is not highlighted
    And the user selects "<OptionNo>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice3                                                          | OptionNo | WarningMessage                                                                                                                                                | WarningMessage2                                                                                                                                                                          |
      | Parent(s) / carer / guardian changed their mind about the clinical test | No       | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_2
  Scenario Outline: NTS-3428: scenario 2 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    Then the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Parent(s) / carer / guardian have agreed to the test                    |
      | Record of Discussion form not currently available                       |
      | Parent(s) / carer / guardian changed their mind about the clinical test |
    When the user selects the option "<PatientChoice2>" as patient choices
    Then the user should see the section title as Reason for not capturing parent(s) / carer / guardian choice:
    And the patient choice reason options as below
      | Patient conversation happened; form to follow               |
      | Test does not require recording of patient choices          |
      | Patient currently lacks capacity and no consultee available |
      | Associated with another referral                            |
      | Other                                                       |
    And the user should see continue button is not highlighted
    And the user clicks on Continue Button
    Then the user should see a error message box with border color #dd2509 and message as Please select an answer
    When the user selects the option "<Reason5>" as patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Record of Discussion form not currently available |
      | Reason for not capturing parent(s) / carer / guardian choice: ::Other                                                                                                                              |
    When the user is navigated to a patient choice form option with title Review and submit
    And the user will see a "<WarningMessage>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice2                                    | Reason5 | WarningMessage                                                                                                                                                                           |
      | Record of Discussion form not currently available | Other   | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @NTS-3428 @E2EUI-2041 @LOGOUT @v_1 @P0 @scenario_3
  Scenario Outline: NTS-3428: scenario 3 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Parent(s) / carer / guardian have agreed to the test                    |
      | Record of Discussion form not currently available                       |
      | Parent(s) / carer / guardian changed their mind about the clinical test |
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
      | Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / carer / guardian have agreed to the test |
      | Has research participation been discussed?::No                                                                                                                                                        |
      | Why has research participation not been discussed?::Parent(s) / carer / guardian would like to revisit at a later date                                                                                |
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see continue button is not highlighted
    And the user selects "<Option3>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    ###Ensure this field is marked as mandatory and clear button is working or not.
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | PatientChoice2                                                     | NoOption | Option3        | Question1                                  | Question2                                          | WarningMessage                                                                                                         | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | Parent(s) / carer / guardian would like to revisit at a later date | No       | Not applicable | Has research participation been discussed? | Why has research participation not been discussed? | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_3a
  Scenario Outline: NTS-3428:scenario 3a -  Editing Patient choice for a Child in person
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national | child |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
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

  @NTS-3428 @E2EUI-2041 @v_1 @P0 @scenario_4
  Scenario Outline: NTS-3428: scenario 4 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Parent(s) / carer / guardian have agreed to the test                    |
      | Record of Discussion form not currently available                       |
      | Parent(s) / carer / guardian changed their mind about the clinical test |
    When the user selects the option "<PatientChoice1>" as patient choices
    Then the question will be displayed as "<Question1>"
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should see continue button is not highlighted
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    ###Click on show additional warnings link and ensure the additional warning message displayed
    ###Verify for the link Add Partner Details (trio analysis only)
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
      | Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / carer / guardian have agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                      |
      | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care.::No            |
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see continue button is not highlighted
    And the user selects "<NAOption>" agree to participate in research for Child Assent
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    ##Check for mandatory fields presence and the clear functionality to be included.
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | NoOption | YesOption | NAOption       | Question1                                  | Question2                                                                                                                             | WarningMessage                                                                                                                                                      | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | No       | Yes       | Not applicable | Has research participation been discussed? | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care. | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3428 @E2EUI-2041 @LOGOUT @v_1 @P0 @scenario_5
  Scenario Outline: NTS-3428: scenario 5 - Editing Patient choice for a Child in person
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    Then the user should see the section title as Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the patient choice options as below
      | Parent(s) / carer / guardian have agreed to the test                    |
      | Record of Discussion form not currently available                       |
      | Parent(s) / carer / guardian changed their mind about the clinical test |
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
      | Have the parent(s) / carer / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / carer / guardian have agreed to the test |
      | Has research participation been discussed?::Yes                                                                                                      |
      | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care.::Yes           |
    When the user is navigated to a patient choice form option with title Child assent
    Then the user should see the section title as Does the child agree to participate in research?
    And the child assent options as below
      | Yes            |
      | No             |
      | Not applicable |
    And the user should see continue button is not highlighted
    And the user selects "<YesOption>" agree to participate in research for Child Assent
    And the user fills signature details in Child signature
    And the user clicks on Continue Button
    Then the Child assent option is marked as completed
    And the user is navigated to a patient choice form option with title Parent/Guardian signature
    ##Include the mandatory and clear field validations
    When the user fills "<Parent/Guardian signature>" details for signature
    Then the user should be able to see the highlighted Submit patient choice button
    And Save and continue button is displayed as "disabled"

    Examples:
      | PatientChoice1                                       | YesOption | Question1                                  | Question2                                                                                                                             | Parent/Guardian signature          |
      | Parent(s) / carer / guardian have agreed to the test | Yes       | Has research participation been discussed? | The patient's parent(s) / carer / guardian agrees that their child's data and samples may be used for research, separate to NHS care. | FirstName=WILTON:LastName=BRITTAIN |
