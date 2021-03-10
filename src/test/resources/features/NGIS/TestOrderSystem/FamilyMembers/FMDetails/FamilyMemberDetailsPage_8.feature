#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: Family Members Details Page 8- Post Code validation_2

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field -Create a new family member, pass postcode
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    When the user is navigated to a page with title Add a requesting organisation
    Then the user navigates to the "<FamilyMember>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message "No patient found" is displayed below the search button
    And the user clicks on the hyper link
    Then the user is navigated to a page with title Create a record for this family member
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "Patient not eligible for NHS number (e.g. foreign national)"
    And the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user fills in the Postcode field box with "<Postcode1>"
    Then the user clicks the Add new patient to referral button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat1>" format

    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user fills in the Postcode field box with "<Postcode2>"
    Then the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat1>" format

    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user fills in the Postcode field box with "<Postcode3>"
    Then the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat1>" format

    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user fills in the Postcode field box with "<Postcode4>"
    Then the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat2>" format

    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user fills in the Postcode field box with "<Postcode5>"
    Then the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat2>" format

    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user fills in the Postcode field box with "<Postcode6>"
    Then the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat2>" format


    Examples:
      | FamilyMember   | RelationshipToProband | FamilyMemberDetails | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | Postcode6 | PostcodeFormat1 | PostcodeFormat2 |
      | Family members | Son                   | null                | AB1 2CD   | AB12CD    | AB1  2CD  | ab12cd    | ab1 2cd   | ab1  2cd  | AB1 2CD         | ab1 2cd         |