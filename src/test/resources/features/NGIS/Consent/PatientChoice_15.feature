#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice-15 - Edit Paper Form - Adult Without Capacity

  @NTS-3417
    #@E2EUI-2040 @E2EUI-1060
  Scenario Outline: NTS-3417: Pre-Req:Editing Patient choice for an Adult (without capacity)
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    And the "Patient details" stage is marked as Completed
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
    And the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed

    Examples:
      | PatientChoiceStage | RecordedBy                            |
      | Patient choice     | ClinicianName=John:HospitalNumber=123 |

  @NTS-3417
    #@E2EUI-2040 @E2EUI-1060 @scenario_5
  Scenario Outline: NTS-3417: scenario 5 - Editing Patient choice for an Adult (without capacity)
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
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field      | field_type | symbol | symbol color |
      | Consultee first name | label      | ✱      | #dd2509      |
      | Consultee last name  | label      | ✱      | #dd2509      |
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    When the user should be able to clear the signature
    Then the user should see patient choice submit button as disabled
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    Then Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417
    #@E2EUI-2040 @E2EUI-1060 @scenario_5(a)
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
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field      | field_type | symbol | symbol color |
      | Consultee first name | label      | ✱      | #dd2509      |
      | Consultee last name  | label      | ✱      | #dd2509      |
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    When the user should be able to clear the signature
    Then the user should see patient choice submit button as disabled
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    Then Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |

  @NTS-3417 @Z-LOGOUT
    #@E2EUI-2040 @E2EUI-1060 @scenario_5(b)
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
    And the mandatory fields in patient choice shown with the symbol in red color
      | mandatory_field      | field_type | symbol | symbol color |
      | Consultee first name | label      | ✱      | #dd2509      |
      | Consultee last name  | label      | ✱      | #dd2509      |
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    When the user should be able to clear the signature
    Then the user should see patient choice submit button as disabled
    When the user fills "<Consultee signature>" details for signature
    And the user should see patient choice submit button as enabled
    Then Save and continue button is displayed as disabled

    Examples:
      | Consultee signature                |
      | FirstName=WILTON:LastName=BRITTAIN |