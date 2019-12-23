@regression
@COMP08_P0
@FamilyMemberStageNavigation
Feature: Family Members Navigation Stage Validation

  @COMP8_TO_Familymembers
    @familyMemberStageNavigation_01 @LOGOUT @NTS-3243 @E2EUI-1287 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1287: Verify the Family Members stage Navigation Flow
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
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

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_02 @LOGOUT @NTS-3299 @E2EUI-1698 @v_1 @P0
  Scenario Outline: E2EUI-1698: Verify the family members test package are selected by default
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    When the user deselects the test
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user clicks on back button
    Then the user sees test remains as deselected

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_03 @LOGOUT @NTS-3301 @E2EUI-1291 @v_1 @P0
  Scenario Outline: E2EUI-1291: Verify the current additional family member information
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    And the color of referral name for "<FamilyMemberDetails>" displays as "<ReferralColor>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | ReferralColor |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | #da291c       |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_05 @LOGOUT @NTS-3295 @E2EUI-1279 @E2EUI-1362 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1279-1362: Verify the family members page layout
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
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
    Then the family member test package page is correctly displayed

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |


  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_06 @LOGOUT @NTS-3291 @E2EUI-1604 @v_1 @P0
  Scenario Outline: E2EUI-1604: Verify that Indicate family members with outstanding questions to answer
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should see an error message "<ErrorMessage>" in "<MessageColor>" for the family member
    When the user edits to complete the highlighted family member "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member landing page displayed without incomplete error message

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | ErrorMessage                                                    | MessageColor | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449310319:DOB=09-12-2010 | Full Sibling          | There is essential clinical information missing from this entry | #da291c      | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_07 @LOGOUT @NTS-3322 @E2EUI-1509 @v_1 @P0
  Scenario Outline: E2EUI-1509: Verify family members has completed in to-do list
    Given a referral is created for a nwe patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | FamilyMember   | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |
