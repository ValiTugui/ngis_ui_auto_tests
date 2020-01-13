@regression
@TO_RD
@FamilyMembersDetailsPage

Feature: Family Members Details Validation

  @NTS-3235 @E2EUI-908 @v_1 @P0
  Scenario Outline: NTS-3235: Verify addition of a family member to a referral without providing Relationship to Proband field.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    And the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage                         | MessageColor |
      | Family members | NHSNumber=9449305552:DOB=20-09-2008 | Relationship to proband is required. | #dd2509      |

  @NTS-3300 @E2EUI-1349 @BVT_P0 @v_1
  Scenario Outline: NTS-3300: Verify family member details page - Confirm family member details
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the default family member details page is correctly displayed with the proper number of fields
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for

    Examples:
      | stage          | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Maternal Aunt         |

  @NTS-3298 @E2EUI-1369 @v_1 @P0
  Scenario Outline: NTS-3298: Verify "relationship to proband" field mandatory when adding a family member to referral
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    Then the default family member details page is correctly displayed with the proper number of fields
    And the user clicks the Save and Continue button
    Then the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage                         | MessageColor | RelationshipToProband |
      | Family members | NHSNumber=9449310602:DOB=23-03-2011 | Relationship to proband is required. | #dd2509      | Full Sibling          |

  @NTS-3297 @E2EUI-1012 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3297: To validate the flow when the user chooses to add a test for family members
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"

    Examples:
      | stage          | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Full Sibling          |