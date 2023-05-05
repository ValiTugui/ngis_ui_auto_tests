#@FamilyMemberStageNavigation
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: Family Members Navigation Stage 3 - Patient Identifiers

  @NTS-3291 @NTS-3243 @NTS-3295 @Z-LOGOUT
#    @E2EUI-1604 @E2EUI-1287 @E2EUI-1279 @E2EUI-1362 @E2EUI-1581
  Scenario Outline: NTS-3291: Verify that Indicate family members with outstanding questions to answer
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1952:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And subtitle of the page displayed as Tested family members you add here will be visible in the pedigree.
    And subtitle links as add non-tested family members
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should see an error message "<ErrorMessage>" in "<MessageColor>" for the family member
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user clicks on back button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member landing page displayed without incomplete error message

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                                               | ErrorMessage                                                    | MessageColor | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=NA:DOB=14-05-1953:Gender=Male:Relationship=Full Sibling | There is essential clinical information missing from this entry | #da291c      | DiseaseStatus=Unaffected |

  @NTS-3330 @NTS-3337 @Z-LOGOUT
#    @E2EUI-1202 @E2EUI-1326 @E2EUI-1770
  Scenario Outline: NTS-3330: User is completing a referral and wants to add a family member record to the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1980:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    Then the default family member details page is correctly displayed
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user is able to clicks on deselected test
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then The user should be able to see details like name,relationship with proband,Date of birth,Gender,NHS No & Patient NGIS ID for "<FamilyMemberDetails>"
    And subtitle of the page displayed as Tested family members you add here will be visible in the pedigree.
    And subtitle links as add non-tested family members
    And the test status "<TestStatus>" Marked in "<color>"
    And The user should be able to view patient choice status for all the family members added.
    And The user also should see the Add Family Member button and continue button displayed

    Examples:
      | TestStatus       | color   | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Being tested     | #e5f6f5 | Family members | NHSNumber=2000004296:DOB=24-09-2011 | Full Sibling          | DiseaseStatus=Unaffected |
      | Not being tested | #fdf3e5 | Family members | NHSNumber=2000004768:DOB=02-08-1968 | Maternal Aunt         | DiseaseStatus=Unknown    |


##Commented as some FIX required for this
#  @NTS-3339 @E2EUI-1791 @Z-LOGOUT
#  Scenario Outline: NTS-3339: Update PatientList component in family member section to use PatientIdentifiers
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1951:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
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
