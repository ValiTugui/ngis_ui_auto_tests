@regression
@TO_RD
@FamilyMemberStageNavigation
@FamilyMemberStageNavigation_removeFM

Feature: Family Members Navigation Stage - Remove Family Member

  @NTS-3292 @E2EUI-1331 @E2EUI-1485 @E2EUI-1639 @LOGOUT @BVT_P0 @v_1
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

  @NTS-3330 @E2EUI-1202 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3330: User is completing a referral and wants to add a family member record to the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1980:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    Then the default family member details page is correctly displayed
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks on a test that is selected and the test is no longer selected
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then The user should be able to see details like name,relationship with proband,Date of birth,Gender,NHS No & Patient NGIS ID for all the family members added.
    And subtitle of the page displayed as Tested family members you add here will be visible in the pedigree.
    And subtitle links as add non-tested family members
    And the family member status "<TestStatus>" Marked in "<color>"
    And The user should be able to view patient choice status for all the family members added.
    And The user also should see the Add Family Member button and continue button displayed

    Examples:
      | TestStatus       | color   | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Being tested     | #e5f6f5 | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |
      | Not being tested | #fdf3e5 | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Maternal Aunt         | DiseaseStatus=Unknown    |
##Commented as some FIX required for this
#  @NTS-3339 @E2EUI-1791 @LOGOUT @v_1 @P0
#  Scenario Outline: NTS-3339: Update PatientList component in family member section to use PatientIdentifiers
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1951:Gender=Male |
#    Then the user is navigated to a page with title Check your patient's details
#    When the user navigates to the "<Family Members>" stage
#    Then the user is navigated to a page with title Add a family member to this referral
#    And the user should be able to see the patient identifiers 1 patient
#    When the user navigates to the "<Patient choice stage>" stage
#    Then the user is navigated to a page with title Patient choice
#    And the user should be able to see the patient identifiers on patient choice page similar as in family member landing page
#
#    Examples:
#      | Family Members | Patient choice stage |
#      | Family members | Patient choice       |

  @NTS-3338 @LOGOUT @E2EUI-1510 @BVT_P0 @v_1
  Scenario Outline: NTS-3338: To verify the error messages in family members test selection page by adding less and more number of expected participants to the referral.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1982:Gender=Male |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<One>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user should see an error message displayed as "One participant was quoted for this test" in "<color>" color
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on the link to amend the number of participants for test
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<Four>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user search the family member with the specified details "<FamilyMemberDetails2>"
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user should see a warning message displayed as "Four participants were quoted for this test" in "<color2>" color

    Examples:
      | TestPackage  | One | Family member  | FamilyMemberDetails                                                | DiseaseStatusDetails     | color   | Four | FamilyMemberDetails2                                               | color2  |
      | Test package | 1   | Family members | NHSNumber=NA:DOB=30-06-1974:Gender=Male:Relationship=Maternal Aunt | DiseaseStatus=Unaffected | #dd2509 | 4    | NHSNumber=NA:DOB=15-01-2000:Gender=Male:Relationship=Maternal Aunt | #425563 |