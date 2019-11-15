@regression1
@FamilyMemberStage
Feature: Navigation: Family Members stage

  @COMP8_TO_Familymembers
    @familyMemberStageNavigation_01 @LOGOUT @NTS-3243 @E2EUI-1287 @v_1 @P0
  Scenario Outline: NTS-3243: Verify the Family Members stage Navigation Flow
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user navigates to the family member search Page
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user selects the patient search result tab
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    When the user clicks the Save and Continue button in family member details page
    Then the family member details with the selected test are added to the referral
    When the user clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    When the user clicks the Save and Continue button in family member details page
    Then the user returns to family member landing page with the added family member details

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
