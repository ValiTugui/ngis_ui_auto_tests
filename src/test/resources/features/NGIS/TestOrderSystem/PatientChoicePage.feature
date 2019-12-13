@regression
@COMP08_P0
@PatientChoice
Feature: Patient Choice Page

  @COMP8_TO_PatientSearch
    @patientChoice_Page01 @LOGOUT @NTS-3341 @E2EUI-1659 @BVT-P0 @v_1 @P0
  Scenario Outline: E2EUI-1659: Verify the patient choice status in family member page

    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status in family member page as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills new patient choice form with below details
      | <PatientChoiceCategory> |
      | <TestType>              |
      | <RecordedBy>            |
      | <PatientChoice>         |
      | <ChildAssent>           |
      | <ParentSignature>       |
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user sees the patient choice status as "<Status2>"
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status2>"

    Examples:
      | Family members | Status1     | Patient choice stage | Status2           | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                        | ChildAssent | ParentSignature                       |
      | Family members | Not entered | Patient choice       | Agreed to testing | Child                 | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |
