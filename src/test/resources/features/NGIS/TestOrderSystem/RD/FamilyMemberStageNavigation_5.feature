@regression
@TO_RD
@FamilyMemberStageNavigation
@FamilyMemberStageNavigation_5
Feature: Family Members Navigation Stage 5

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

  @NTS-3339 @E2EUI-1791 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3339: Update PatientList component in family member section to use PatientIdentifiers
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1951:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Family Members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should be able to see the patient identifiers 1 patient
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see the same number patient identifiers in FM landing and Patient Choice Landing Page

    Examples:
      | Family Members | Patient choice stage |
      | Family members | Patient choice       |
