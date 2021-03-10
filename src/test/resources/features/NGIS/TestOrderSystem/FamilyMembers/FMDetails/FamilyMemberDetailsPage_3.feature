#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: Family Members Details Page 3- Field Validation_3

  @NTS-4409 @NTS-5916 @NTS-3297 @NTS-4019 @Z-LOGOUT
#    @E2EUI-1426 @E2EUI-1012 @E2EUI-960
  Scenario Outline: NTS-4409: Remove diagnosis Age at Onset
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    #Below line is for NTS-5916(@NTOS-4912 @NTOS-4911)
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    #NTS-4019
    And the Test package page has Targeted genes section with the "<TargetedGenes>"
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    #NTS-3297(E2EUI-1012)
    And the user clicks the Save and Continue button
    And the referral submit button is not enabled
    Then The user should not see the rare disease diagnoses "<AgeOfOnset>" field

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | AgeOfOnset   | TargetedGenes    |
      | Family members | NHSNumber=2000003869:DOB=18-09-2011 | Full Sibling          | Age of onset | Craniosynostosis |