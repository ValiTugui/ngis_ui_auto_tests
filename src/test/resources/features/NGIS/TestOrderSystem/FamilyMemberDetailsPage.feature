@regression
@COMP08_P0
@FamilyMembersDetailsPage
Feature: Family Member Details Page

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_01 @NTS-3235 @E2EUI-908 @v_1 @P0
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
    @familyMemberDetailsPage_03 @NTS-3298 @E2EUI-1369 @v_1 @P0
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
      | stage          | NhsNumber  | DOB        | ErrorMessage                         | MessageColor |RelationshipToProband|
      | Family members | 9449310602 | 23-03-2011 | Relationship to proband is required. | #dd2509      | Full Sibling                    |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_04 @NTS3297 @E2EUI-1012 @v_1 @P0
  Scenario Outline: E2EUI-1012: To validate the flow when the user chooses to add a test for family members

    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search a patient with valid NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user can select the test to add to the family member

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband |
      | Family members | 9449310122 | 30-06-1974 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_06 @NTS-3299 @E2EUI-1698 @v_1 @P0
  Scenario Outline: E2EUI-1698: Verify the family members test package are selected by default
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    When the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    When the user deselect the test
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Add family member details
    When the user clicks on back button on family member details page
    Then the user sees test remains deselect

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband |
      | Family members | 9449310165 | 25-12-2000 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_07 @E2EUI-1291 @v_1 @P0
  Scenario Outline: E2EUI-1291: Verify the current additional family member information
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user should be able to see test package for family member is selected by default
    When clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    When the user navigates to the "<stage>" stage
#
#    And the user should be able to see which patient in my referral currently editing

    Examples:
      | stage          | NhsNumber  | DOB        | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | 9449305307 | 14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_12 @E2EUI-1331 @v_1 @P0
  Scenario Outline: Remove a family member from family member stage
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user should be able to see test package for family member is selected by default
    When clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    When the user removes the family member
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page

    Examples:
      | stage          | TestPackage  | NoOfParticipants | NhsNumber  | DOB        | RelationshipToProband | DiseaseStatusDetails  | SuccessDeleteMessage                |
      | Family members | Test package | 2                | 9449306680 | 14-06-2011 | Full Sibling          | DiseaseStatus=Unknown | Family member removed from referral |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_11 @E2EUI-1485 @v_1 @P0
  Scenario Outline: Verify the family members details on family member landing page
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user should be able to see test package for family member is selected by default
    When clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the family member details on family Member landing page is correctly displayed

    Examples:
      | stage          | TestPackage  | NoOfParticipants | NhsNumber  | DOB        | RelationshipToProband | DiseaseStatusDetails  |
      | Family members | Test package | 2               | 9449306680 | 14-06-2011 | Full Sibling          | DiseaseStatus=Unknown |


  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_09 @E2EUI-1639 @v_1 @P0
  Scenario Outline: Verify the family member has been removed from the referral message
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user should be able to see test package for family member is selected by default
    When clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    When the user removes the family member
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page
    And the user clicks on Continue Button
    Then the Patient Choice page is displayed
    When the user navigates to the "<stage>" stage
    Then the user should not see the removal message on the family member landing page

    Examples:
      | stage          | TestPackage  | NoOfParticipants | NhsNumber  | DOB        | RelationshipToProband | DiseaseStatusDetails     | SuccessDeleteMessage                |
      | Family members | Test package | 2                | 9449305307 | 14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected | Family member removed from referral |
