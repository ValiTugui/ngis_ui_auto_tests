@regression
@regression_stag
@FamilyMembersDetailsPage
Feature: Family Member Details Page

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_01 @NTS-3235 @E2EUI-908 @E2EUI-908 @v_1 @P0
  Scenario Outline: E2EUI-908: To verify the addition of a family member to a referral without providing Relationship to proband field.
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease |NHSNumber=9449310270:DOB=12-08-2007|
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And  clicks the Save and Continue button in family member details page
    Then the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | NhsNumber  | DOB        | ErrorMessage                         | MessageColor |
      | Family members | 9449310157 | 15-01-2000 | Relationship to proband is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_02 @E2EUI-1349 @v_1 @P0
  Scenario Outline: E2EUI-1349: Verify The family member details on the 'Check family member Details' Page with respect to the 'Find a family member' Page
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the default family member details page is correctly displayed with the proper number of fields
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband |
      | Family members | 9449310165 | 25-12-2000 | Maternal Aunt         |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_03 @E2EUI-1369 @v_1 @P0
  Scenario Outline: E2EUI-1369: Verify "relationship to proband" field mandatory when adding a family member to referral
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    Then the default family member details page is correctly displayed with the proper number of fields
    And  clicks the Save and Continue button in family member details page
    Then the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | NhsNumber  | DOB        | ErrorMessage                         | MessageColor |
      | Family members | 9449310602 | 23-03-2011 | Relationship to proband is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_04 @LOGOUT @E2EUI-1038 @v_1 @P0
  Scenario Outline: E2EUI-1038: Verify the mandatory input fields validations for non-NHS family member creation
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    When the user clicks on the create new patient record
    Then the user is navigated to a page with title Add a new patient to the database
    And the new patient page is correctly displayed with expected fields
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Add new patient to referral button
    Then the message will be displayed as "<MandatoryFieldErrorMessage>" in "<MessageColor>" in new patient page
    Examples:
      | stage          | SearchDetails                                               | PatientSearchMessage | ClearFields | MessageColor | MandatoryFieldErrorMessage                                                                                                                                                                                                   |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     |  Gender      | #dd2509      | First name is required.,Last name is required.,Date of birth is required.,Gender is required.,Life status is required.,Select the reason for no NHS Number,Hospital number is required.,Relationship to proband is required. |
#
#  @COMP8_TO_PatientSearch
#    @familyMemberDetailsPage_05 @E2EUI-1012 @v_1 @P0
#  Scenario Outline: E2EUI-1012: To validate the flow when the user chooses to add a test for family members
#    When the user navigates to the "<stage>" stage
#    And the user clicks on Add family member button
#    And the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
#    Then the patient card displays with Born,Gender and NHS No details
#    When the user clicks on the patient card
#    Then the user is navigated to a page with title Confirm family member details
#    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
#    And reads the details of selected family member "<RelationshipToProband>"
#    And  clicks the Save and Continue button in family member details page
#    Then the user is navigated to a page with title Select tests for
#    And the user can select the test to add to the family member
#
#    Examples:
#      | stage          | NhsNumber  | DOB        | RelationshipToProband |
#      | Family members | 9449310122 | 30-06-1974 | Full Sibling          |