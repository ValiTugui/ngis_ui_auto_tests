@FamilyMemberSearch
Feature: Relationship to proband field validation

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_01 @LOGOUT @NTS-3235 @E2EUI-908 @v_1 @P0
  Scenario Outline: NTS-3235: Verify the family member relationship to proband field is mandatory
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user selects the patient search result tab
    When the user clicks the Save and Continue button in family member details page
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field in family member details page

    Examples:
      | stage          |NhsNumber   |DOB         |ErrorMessage                          |MessageColor |
      | Family members | 9449310602 | 23-03-2011 |Relationship to proband is required.  |#dd2509      |
