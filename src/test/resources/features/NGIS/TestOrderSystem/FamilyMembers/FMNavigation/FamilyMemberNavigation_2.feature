#@regression
#@FamilyMemberStageNavigation
#@FamilyMemberStageNavigation_mismatchFM
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: TestOrder - Family Members Navigation Stage 2 - Varying Members addition and removal

  @NTS-3309 @NTS-3475 @Z-LOGOUT
#    @E2EUI-1539 @E2EUI-2105 @E2EUI-1149 @E2EUI-2104 @E2EUI-1149 @E2EUI-2090 @v_1 @P1
  Scenario Outline: NTS-3309: Verify message when the number of participants in Test Package are less than family member selected
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1969:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user should "get" participant error message as "<ErrorMessage>"
    When the user clicks on participant amendment link to amend the number of participants
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants1>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should "not get" participant error message as "<ErrorMessage>"
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    And the user should "get" participant error message as "<ErrorMessage>"
    When the user clicks on participant amendment link to amend the number of participants
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the user navigates to the "<FamilyMembers>" stage
    When the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMembers>" stage is marked as Completed
    Then the user should "not get" participant error message as "<ErrorMessage>"
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | NoOfParticipants1 | FamilyMemberDetails                                               | DiseaseStatusDetails  | ErrorMessage                                                                                                |
      | Family members | Test package | 2                | 1                 | NHSNumber=NA:DOB=14-02-2011:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Unknown | The number of participants you’ve selected for one or more tests does not match the number that was entered |