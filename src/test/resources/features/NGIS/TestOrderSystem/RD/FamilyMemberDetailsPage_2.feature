@regression
@TO_RD
@FamilyMembersDetailsPage

Feature: Family Members Details Page - Field Validation_2

  @NTS-3296 @LOGOUT @E2EUI-1038 @v_1 @P0
  Scenario Outline: NTS-3296: Verify the mandatory input fields validations for non-NHS family member creation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1970:Gender=Male |
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    When the user clicks on the create new patient record
    Then the user is navigated to a page with title Add a new patient to the database
    And the mandatory fields shown with the symbol in red color
      | mandatory_field              | field_type | symbol | symbol color |
      | First name                   | label      | ✱      | #dd2509      |
      | Last name                    | label      | ✱      | #dd2509      |
      | Date of birth                | label      | ✱      | #dd2509      |
      | Gender                       | label      | ✱      | #dd2509      |
      | Life status                  | label      | ✱      | #dd2509      |
      | Reason NHS Number is missing | label      | ✱      | #dd2509      |
      | Hospital number              | label      | ✱      | #dd2509      |
      | Relationship to proband      | label      | ✱      | #dd2509      |
      | Ethnicity                    | label      | ✱      | #dd2509      |
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Add new patient to referral button
    Then the blank mandatory field labels highlighted in red color
      | field_name                   | color   |
      | First name                   | #dd2509 |
      | Last name                    | #dd2509 |
      | Date of birth                | #dd2509 |
      | Gender                       | #dd2509 |
      | Life status                  | #dd2509 |
      | Reason NHS Number is missing | #dd2509 |
      | Hospital number              | #dd2509 |
      | Relationship to proband      | #dd2509 |
      | Ethnicity                    | #dd2509 |
    Examples:
      | FamilyMember   | SearchDetails                                               | PatientSearchMessage | ClearFields |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     | Gender      |

  @NTS-3342 @E2EUI-1790 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3342: Update FamilyMember card to use PatientIndentifiers in Test package and Patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
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
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    Then the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    And the user clicks on edit icon to update patient choice status for family member
    Then the user is navigated to a page with title Add family member patient choice information
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    When the user clicks on back button
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Family member  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | Patient Choice |
      | Family members | NHSNumber=9449310157:DOB=15-01-2000 | Full Sibling          | DiseaseStatus=Unaffected | Patient choice |


