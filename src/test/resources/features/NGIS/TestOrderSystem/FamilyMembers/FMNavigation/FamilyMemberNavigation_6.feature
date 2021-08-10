#@FamilyMemberStageNavigation
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: All Pages Navigation FM Stage

  @NTS-7158 @NTOS-5483 @Z-LOGOUT
  Scenario Outline: NTS-7158: Validate Save and continue / Continue button through all the pages of FM stage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1973:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user should be able to see trio family icon in test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1928:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    And the family member details on family Member landing page is correctly displayed
    When the user clicks on edit button of Family member
    Then the user is navigated to a page with title Continue with this family member
    And the user should be able to see "Continue" button
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user should be able to see "Continue" button
    When the user updates with "<FamilyMemberDetailsUpdate>"
    Then the user should be able to see "Save and continue" button
    When the user updates with "<FamilyMemberDetailsUpdate1>"
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see "Continue" button
    When the user deselects the test
    Then the user should be able to see "Save and continue" button
    When the user is able to clicks on deselected test
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user should be able to see "Continue" button
    When the user updates the "Add family member details" page with "<FamilyMemberClinicalDetailsUpdated>"
    Then the user should be able to see "Save and continue" button
    When the user updates the "Add family member details" page with "<FamilyMemberClinicalDetailsUpdated1>"
    Then the user should be able to see "Continue" button
    # Steps are same for clicking on continue / Save and continue button
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should be able to see "Continue" button
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetailsUpdate | FamilyMemberDetailsUpdate1 | FamilyMemberClinicalDetailsUpdated | FamilyMemberClinicalDetailsUpdated1 |
      | Family members | Test package | 2                | Gender=Female             | Gender=Male                | DiseaseStatus=Unaffected           | DiseaseStatus=Affected              |