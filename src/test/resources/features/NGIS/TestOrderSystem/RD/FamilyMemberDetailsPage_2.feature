@regression
@TO_RD
@FamilyMembersDetailsPage

Feature: Family Members Details Validation

  @NTS-3309 @E2EUI-1539 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3309: Verify message when the number of participants in Test Package are less than family member selected
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<FamilyMembers>" stage
    Then the user should "get" participant error message as "<ErrorMessage>"
    When the user clicks on participant amendment link to amend the number of participants
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants1>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should "not get" participant error message as "<ErrorMessage>"
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    And the user should "get" participant error message as "<ErrorMessage>"
    When the user clicks on participant amendment link to amend the number of participants
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<FamilyMembers>" stage
    When the user is navigated to a page with title Add a family member to this referral
    And the "<FamilyMembers>" stage is marked as Completed
    Then the user should "not get" participant error message as "<ErrorMessage>"
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | NoOfParticipants1 | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails  | ErrorMessage                                                                                                |
      | Family members | Test package | 2                | 1                 | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unknown | The number of participants you’ve selected for one or more tests does not match the number that was entered |

  @NTS-3296 @LOGOUT @E2EUI-1038 @v_1 @P0
  Scenario Outline: NTS-3296: Verify the mandatory input fields validations for non-NHS family member creation
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    When the user clicks on the create new patient record
    Then the user is navigated to a page with title Add a new patient to the database
    And the new patient page is correctly displayed with expected fields
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Add new patient to referral button
    Then the message will be displayed as "<MandatoryFieldErrorMessage>" in "<MessageColor>" in new patient page
    Examples:
      | FamilyMember   | SearchDetails                                               | PatientSearchMessage | ClearFields | MessageColor | MandatoryFieldErrorMessage                                                                                                                                                                                                   |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     | Gender      | #dd2509      | First name is required.,Last name is required.,Date of birth is required.,Gender is required.,Life status is required.,Select the reason for no NHS Number,Hospital number is required.,Relationship to proband is required. |

  @NTS-3342 @E2EUI-1790 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3342: As a user editing a family member's details or patient choice, I should know which family member I am focusing on so that I only make the changes relevant to that family member
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    Then the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks on edit icon to update patient choice status for family member
    Then the user is navigated to a page with title Add family member patient choice information
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    When the user moves back to previous page
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Family member  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | NHSNumber=9449310157:DOB=15-01-2000 | Full Sibling          | DiseaseStatus=Unaffected |


