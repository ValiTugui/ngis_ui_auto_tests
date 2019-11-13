@FamilyMembers
Feature: Navigation: Family Members stage

  @COMP8_TO_Familymembers
    @familyMemberStageNavigation_01 @NTS-3243 @E2EUI-1287 @v_1 @P0
  Scenario Outline: NTS-3243: Verify the family member relationship to proband field is mandatory
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks on save and continue in Clinical questions page
    Then the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user navigates to the family member search Page
    And the user search the family member with the specified details "<FamilyMemberSearchDetails>"
    And the user selects the patient search result tab
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    When the user clicks the Save and Continue button in family member details page
#    Then the "<RelationshipToProband>" and selected test are
    ##<To Continue, 4 more steps are pending>
    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                                     | FamilyMemberSearchDetails           | RelationshipToProband |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Intestinal malrotation | NHSNumber=9449310122:DOB=30-06-1974 | Full Sibling        |
