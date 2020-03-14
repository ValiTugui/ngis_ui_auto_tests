@regression
@TO_RD
@FamilyMemberStageNavigation
@FamilyMemberStageNavigation_patientIdentifier

Feature: Family Members Navigation Stage - Patient Identifiers

  @NTS-3243 @E2EUI-1287 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3243: Verify the Family Members stage Navigation Flow
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2007:Gender=Male |
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
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user clicks on back button
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user clicks on back button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                                               | DiseaseStatusDetails                                            |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=NA:DOB=11-02-2004:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

  @NTS-3299 @E2EUI-1698 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3299: Verify the family members test package are selected by default
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1959:Gender=Male |
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    When the user deselects the test
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user clicks on back button
    Then the user sees test remains as deselected

    Examples:
      | FamilyMembers  | FamilyMemberDetails                                               |
      | Family members | NHSNumber=NA:DOB=14-05-1960:Gender=Male:Relationship=Full Sibling |

  @NTS-3291 @E2EUI-1604 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3291: Verify that Indicate family members with outstanding questions to answer
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1952:Gender=Male |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should see an error message "<ErrorMessage>" in "<MessageColor>" for the family member
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member landing page displayed without incomplete error message

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                                               | ErrorMessage                                                    | MessageColor | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=NA:DOB=14-05-1953:Gender=Male:Relationship=Full Sibling | There is essential clinical information missing from this entry | #da291c      | DiseaseStatus=Unaffected |

