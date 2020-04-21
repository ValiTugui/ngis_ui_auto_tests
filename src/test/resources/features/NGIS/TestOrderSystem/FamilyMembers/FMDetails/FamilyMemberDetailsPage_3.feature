#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page 3- Field Validation_3

  @NTS-4612 @Z-LOGOUT
#    @E2EUI-1772
  Scenario Outline: Make family member details editable
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the mandatory fields shown with the symbol in red color
      | mandatory_field         | field_type | symbol | symbol color |
      | First name              | label      | ✱      | #dd2509      |
      | Last name               | label      | ✱      | #dd2509      |
      | Date of birth           | label      | ✱      | #dd2509      |
      | Gender                  | label      | ✱      | #dd2509      |
      | Life status             | label      | ✱      | #dd2509      |
      | Ethnicity               | label      | ✱      | #dd2509      |
      | Relationship to proband | label      | ✱      | #dd2509      |
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Save and Continue button
    Then the blank mandatory field labels highlighted in red color
      | field_name              | color   |
      | First name              | #dd2509 |
      | Last name               | #dd2509 |
      | Date of birth           | #dd2509 |
      | Gender                  | #dd2509 |
      | Life status             | #dd2509 |
      | Ethnicity               | #dd2509 |
      | Relationship to proband | #dd2509 |
    And the NHS number field is disabled

    Examples:
      | FamilyMember   | ClearFields                                          | FamilyMemberDetails                 |
      | Family members | Gender,Life status,Ethnicity,Relationship to proband | NHSNumber=9449305307:DOB=14-02-2011 |
