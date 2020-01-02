@regression
@PatientChoicePage_3
@patientChoice
Feature: Patient Choice Page

  @COMP9_TO_PatientChoiceAdd
    @patientChoice_Page3_01 @LOGOUT @NTS-3409 @E2EUI-1822 @v_1 @P0
  Scenario Outline: NTS-3409: Navigate around the patient choice pages
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                            | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Cancer (paired tumour normal) – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |


  @COMP9_TO_PatientChoiceAdd
    @patientChoice_Page3_02 @LOGOUT @E2EUI-1960 @NTS-3411 @v_1 @P0
  Scenario Outline: NTS-3411: Verify the info message when user declined for a test
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    Then the user will see a "<InfoMessage>" warning message on the patient choice information option

    Examples:
      | Patient choice stage | PatientChoiceCategory    | TestType                            | RecordedBy                            | PatientChoice                                                           | InfoMessage                                                                                                                                                   |
      | Patient choice       | Adult (With Capacity)    | Rare & inherited diseases – WGS     | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test                      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |
      | Patient choice       | Adult (Without Capacity) | Rare & inherited diseases – WGS     | ClinicianName=John:HospitalNumber=123 | Consultee changed their mind about the clinical test                    | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |
      | Patient choice       | Child                    | Cancer (paired tumour normal) – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian changed their mind about the clinical test | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @COMP9_TO_PatientChoiceAdd
    @patientChoice_Page3_03 @LOGOUT @E2EUI-1127 @E2EUI-1934 @NTS-3410 @v_1 @P0
  Scenario Outline: NTS-3410: Verify the patient Choice stage marked as completed
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
    Then the "<Patient choice stage>" stage is marked as Completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |


  @COMP9_TO_PatientChoice
    @patientChoice_Page3_04 @LOGOUT @NTS-3414 @E2EUI-1889 @v_1 @P0
  Scenario Outline: NTS-3414: Verify the Supporting information form section in form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user clicks on the "<forms>" link in patient choice page
    And the user should be able to see a sub title "<forms>" on add patient choice information page
    Then the should be able to see an additional section "<additionalForms>" under the Form Library

    Examples:
      | Patient choice stage | forms        | additionalForms        |
      | Patient choice       | Form library | Supporting information |

  @COMP9_TO_PatientChoiceAdd
    @patientChoice_Page3_05 @LOGOUT @E2EUI-1627 @NTS-3415 @v_1 @P0
  Scenario Outline: NTS-3415: Verify the patient Choice 'Save & Continue' button is disabled until the patient choice has been submitted
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see highlighted continue button
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then Save and continue button is displayed as "disabled"
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    Then Save and continue button is displayed as "enabled"
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see highlighted continue button
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on "New patient choice" link
    And the user clicks on the amend patient choice button
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then Save and continue button is displayed as "disabled"
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    Then Save and continue button is displayed as "enabled"
    When the user clicks the Save and Continue button
    And the user should be able to see highlighted continue button
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status for family member with "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then Save and continue button is displayed as "disabled"
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    Then Save and continue button is displayed as "enabled"
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see highlighted continue button
    Then the "<Patient choice stage>" stage is marked as Completed

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | ClinicalQuestionDetails                 | RelationshipToProband |  Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption |
      | Family members | NHSNumber=9449305919:DOB=24-07-2011 | DiseaseStatus=Affected:AgeOfOnset=02,02 | Full Sibling          |  Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |

  @COMP9_TO_PatientChoiceAdd
    @patientChoice_Page3_06 @LOGOUT @NTS-3418 @E2EUI-1702 @v_1 @P0
  Scenario Outline: NTS-3418: Validation of change in research message inside patient choices section if I change my choice to participate in research.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER | When the user navigates to the "<Patient choice stage>" stage

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage1>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    When the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user clicks on "Preferences" link
    Then the user will see a "<WarningMessage2>" warning message on the patient choice information option
    When the user clicks on "New patient choice" link
    Then the user is navigated to a page with title Add patient choice information
    When the user clicks on the amend patient choice button
    And the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage3>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills signature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks on "Preferences" link
    Then the user will see a "<WarningMessage2>" warning message on the patient choice information option

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption | NoOption | WarningMessage1                                                                                                                                                     | WarningMessage3                                                                                                                                                            | WarningMessage2                                                                                                         |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       | No       | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | If you change this choice it will also apply to any genomic tests the patient has previously had. This will also apply to any future tests, unless they change their mind. | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. |


  @COMP9_TO_PatientChoice
    @patientChoice_Page3_07 @NTS-3437 @E2EUI-1878 @v_1 @P0 @scenario_01
  Scenario Outline: NTS-3437 :scenario_01: Verify the Supporting information form section in form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks on "History" link
    And the user should be able to see patient choice in history tab

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |

  @COMP9_TO_PatientChoice
    @patientChoice_Page3_08 @LOGOUT @NTS-3437 @E2EUI-1878 @v_1 @P0 @scenario_02
  Scenario Outline: NTS-3437 :scenario_02: Verify the Supporting information form section in form library
    When the user clicks on "History" link
    And the user clicks on "New patient choice" link
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the amend patient choice button
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks on "History" link
    Then the user should be able to see replaced patient choice in history tab

    Examples:
      | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                      |
      | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |