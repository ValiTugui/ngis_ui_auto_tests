@regression
@COMP08_P0
@FamilyMemberStageNavigation
Feature: Family Members Navigation Stage Validation

  @COMP8_TO_Familymembers
    @familyMemberStageNavigation_01 @LOGOUT @NTS-3243 @E2EUI-1287 @v_1 @P0
  Scenario Outline: E2EUI-1287: Verify the Family Members stage Navigation Flow
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
#    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user can select the test to add to the family member
    And  clicks the Save and Continue button in family member details page
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the user returns to family member landing page with the added family member details

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |


  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_02 @LOGOUT @NTS-3299 @E2EUI-1698 @v_1 @P0
  Scenario Outline: E2EUI-1698: Verify the family members test package are selected by default
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    When the user deselects the test
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Add family member details
    When the user clicks on back button on family member details page
    Then the user sees test remains as deselected

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_03 @LOGOUT @NTS-3301 @E2EUI-1291 @v_1 @P0
  Scenario Outline: E2EUI-1291: Verify the current additional family member information
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And  clicks the Save and Continue button in family member details page
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should be able to see family member's details card
    And the editing referral color in Red

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_04 @LOGOUT @NTS-3292 @NTS-3293 @NTS-3293 @E2EUI-1331 @E2EUI-1485 @E2EUI-1639 @v_1 @P0
  Scenario Outline: E2EUI-1331-1485-1639: Remove a family member from a referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And  clicks the Save and Continue button in family member details page
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the "<ClinicalQuestions>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user can select the test to add to the family member
    And  clicks the Save and Continue button in family member details page
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the user returns to family member landing page with the added family member details
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
      | Family members | Test package | Clinical questions | 2                | NHSNumber=9449306680:DOB=14-06-2011 | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Full Sibling          | DiseaseStatus=Unknown | Family member removed from referral |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_05 @LOGOUT @NTS-3295 @E2EUI-1279 @v_1 @P0
  Scenario Outline: E2EUI-1279: Verify the family members page layout
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And  clicks the Save and Continue button in family member details page
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user can select the test to add to the family member
    And  clicks the Save and Continue button in family member details page
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the family member test package page is correctly displayed

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |

    ##November 26
  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_06 @LOGOUT @NTS-3291 @E2EUI-1604 @v_1 @P0
  Scenario Outline: Verify that Indicate family members with outstanding questions to answer
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks on the patient card
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<FamilyMembers>" stage
    Then the user will be able to see an error message as "<ErrorMessage>" in "<MessageColor>" for the family member
    And the user should be able to see incomplete family member in "<MessageColor>"
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Confirm family member details
    When clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And clicks the Save and Continue button in family member details page
    Then the family member landing page displayed without incomplete error message

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | ErrorMessage                                                    | MessageColor | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449310319:DOB=09-12-2010 | Full Sibling          | There is essential clinical information missing from this entry | #da291c      | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_07 @LOGOUT @NTS-3322 @E2EUI-1509 @v_1 @P0
  Scenario Outline: Verify family members has completed in to-do list
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    And the user types in valid details of a patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And clicks the Save and Continue button in family member details page
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  clicks the Save and Continue button in family member details page
    Then the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMember>" stage is marked as Completed

    Examples:
      | FamilyMember          | TestPackage  | NoOfParticipants | NhsNumber  | DOB        | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | 9449305307 | 14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |
