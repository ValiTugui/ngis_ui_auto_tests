#@regression
#@FamilyMemberStageNavigation
#@FamilyMemberStageNavigation_removeFM
@TEST_ORDER_FM
@SYSTEM_TEST

Feature: Family Members Navigation Stage 1 - Remove Family Member

  @NTS-3292 @LOGOUT
#    @E2EUI-1331 @E2EUI-1485 @E2EUI-1639
  Scenario Outline: NTS-3292: Remove a family member from a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1973:Gender=Male |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
    Then the "<ClinicalQuestions>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1928:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    ##Step for E2EUI-1485
    And the family member details on family Member landing page is correctly displayed
    ##Step for 1331 and 1639
    When the user removes the family member
    Then the user accept the alert with message Removing a family member can't be undone. Do you still want to remove them?
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should not see the removal message on the family member landing page

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | SuccessDeleteMessage                |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Family member removed from referral |