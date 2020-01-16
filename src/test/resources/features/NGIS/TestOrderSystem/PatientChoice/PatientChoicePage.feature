@regression
@PatientChoice
Feature: Patient Choice Page

  @NTS-3341 @E2EUI-1659 @LOGOUT @BVT-P0 @v_1
  Scenario Outline: NTS-3341: Verify the patient choice status in family member page
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status1>"
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
      | Family members | Not entered | Patient choice       | Agreed to testing | Child                 | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |

  @NTS-3382 @E2EUI-2110 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3382: Verify the upload revised patient choice documentation to form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Family members>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the "<LinkText>" link in patient choice page
    Then the patient choice form library page displays correctly
    ##To be updated with all links to forms and its existence as per the updated UI (Manual Review comment)

    Examples:
      | Family members | Status1     | Patient choice stage | LinkText     |
      | Family members | Not entered | Patient choice       | Form library |