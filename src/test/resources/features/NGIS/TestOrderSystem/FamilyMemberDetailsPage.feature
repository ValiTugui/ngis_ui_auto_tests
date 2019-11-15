@regression
@FamilyMembers
Feature: Relationship to proband field validation

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_01 @NTS-3235 @E2EUI-908 @v_1 @P0
  Scenario Outline: NTS-3235: Verify the family member relationship to proband field is mandatory
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310645:DOB=16-02-2011 |
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    And the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user selects the patient search result tab
    When the user clicks the Save and Continue button in family member details page
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field in family member details page

    Examples:
      | stage          | NhsNumber  | DOB        | ErrorMessage                         | MessageColor |
      | Family members | 9449310602 | 23-03-2011 | Relationship to proband is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_02 @E2EUI-1012 @v_1 @P0
  Scenario Outline: Family member Details page field validation
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user selects the patient search result tab
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    When the user clicks the Save and Continue button in family member details page
    Then the family member details with the selected test are added to the referral

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband |
      | Family members | 9449310122 | 30-06-1974 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_03 @E2EUI-1349 @v_1 @P0
  Scenario Outline: Check family member Details validation
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user selects the patient search result tab
    Then the default family member details page is correctly displayed with the proper number of fields
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    When the user clicks the Save and Continue button in family member details page
    Then the family member details with the selected test are added to the referral

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband |
      | Family members | 9449310165 | 25-12-2000 | Maternal Aunt         |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_04 @E2EUI-1369 @v_1 @P0
  Scenario Outline: Verify "relationship to proband" field mandatory when adding a family member to referral
    When the user navigates to the "<stage>" stage
    And the user navigates to the family member search Page
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user selects the patient search result tab
    Then the default family member details page is correctly displayed with the proper number of fields
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user removes the data from all fields "<ClearFields>" in the family member details page
    When the user clicks the Save and Continue button in family member details page
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband | ClearFields                                          | ErrorMessage                                                                                                           | MessageColor |
      | Family members | 9449310602 | 23-03-2011 | Maternal Aunt         | Gender,Life status,Ethnicity,Relationship to proband | First name is required.,Last name is required.,Date of birth is required.,Gender is required.,Life status is required. | #dd2509      |


  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_5 @ @E2EUI-1038 @v_1 @P0
  Scenario Outline: Verify the family member search without providing last name displays correct error message
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then The message will be displayed as You’ve searched for "<SearchDetails>" "<ErrorMessage>" in "bold" font
    And There is a "<hyperlinkText>" link available to create a new patient
    And the user clicks the "<hyperlinkText>" to create a new patient
    ##This test is yet to complete - in progress
    Examples:
      | stage          | SearchDetails                                               | ErrorMessage     | hyperlinkText               |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found | create a new patient record |