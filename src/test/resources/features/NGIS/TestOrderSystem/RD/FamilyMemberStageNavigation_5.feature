@regression
@TO_RD
@FamilyMemberStageNavigation
@FamilyMemberStageNavigation_addFM
Feature: Family Members Navigation Stage - Relation ship tp Proband

  @NTS-3503 @E2EUI-1897 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3503: Verify Relationship to proband field in the family member's details page is cleared when submitting the Patient Details stage
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
     ###Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    ###Patient Details
    When the user navigates to the "<PatientDetails>" stage
    And the user clicks the Save and Continue button
    ###Family Members
    And the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Confirm family member details
    Then the user sees the relationship to proband which was previously selected is same as "<RelationshipToProband>"

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | PatientDetails  | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          | Patient details | DiseaseStatus=Unaffected |



  @NTS-3503 @E2EUI-1898 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3503: Verify the test selection page by creating a new patient from the family members stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER | child |
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father | Father                |
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And the user clicks on a test that is selected and the test is no longer selected

    Examples:
      | NoOfParticipants | FamilyMembers  |
      | 2                | Family members |

