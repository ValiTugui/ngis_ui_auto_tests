@regression
@TO_RD
@FamilyMemberStageNavigation

Feature: Family Members Navigation Stage Validation

  @NTS-3292 @NTS-3293 @E2EUI-1331 @E2EUI-1485 @E2EUI-1639 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3292: Remove a family member from a referral
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user returns to family member landing page with the added family member details "<FamilyMemberDetails>"
    ##The below one step is for E2EUI-1485 and following that for 1331
    And the family member details on family Member landing page is correctly displayed
    When the user removes the family member
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page
    ##Below Steps for 1639
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should not see the removal message on the family member landing page

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | FamilyMemberDetails                 | ClinicalQuestionDetails                                         | RelationshipToProband | DiseaseStatusDetails  | SuccessDeleteMessage                |
      | Family members | Test package | Clinical questions | 2                | NHSNumber=9449305919:DOB=24-07-2011 | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Full Sibling          | DiseaseStatus=Unknown | Family member removed from referral |