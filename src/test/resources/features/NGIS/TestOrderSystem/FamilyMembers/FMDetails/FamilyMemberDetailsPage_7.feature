#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page 7- Post Code validation_1

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- update a family member by Postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1997:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks on RelationshipToProband drop down and sees the values of the drop down"<RelationshipToProband>" with recently used suggestion values
    And the user fills in the Postcode field box with "<Postcode>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat>" format

    Examples:
      | FamilyMember   | FamilyMemberDetails                 | Postcode | RelationshipToProband | PostcodeFormat |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | AB12CD   | Full Sibling          | AB1 2CD        |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | AB1 2CD  | Full Sibling          | AB1 2CD        |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | AB1  2CD | Full Sibling          | AB1 2CD        |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | ab12cd   | Full Sibling          | ab1 2cd        |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | ab1 2cd  | Full Sibling          | ab1 2cd        |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | ab1  2cd | Full Sibling          | ab1 2cd        |