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
    Then the user is navigated to a page with title Add a requesting organisation
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

    Examples:
      | FamilyMember   | ClearFields                                          | FamilyMemberDetails                 |
      | Family members | Gender,Life status,Ethnicity,Relationship to proband | NHSNumber=9449305307:DOB=14-02-2011 |

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- update a family member by Postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
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

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field -Create a new family member, pass postcode
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
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
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user fills in the Postcode field box with "<Postcode>"
    Then the user clicks the Add new patient to referral button
    Then the user is navigated to a page with title Continue with this family member
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat>" format
    Examples:
      | FamilyMember   | reason_for_no_nhsNumber       | RelationshipToProband | FamilyMemberDetails | Postcode | PostcodeFormat |
      | Family members | Patient is a foreign national | Son                   | null                | AB1 2CD  | AB1 2CD        |
      | Family members | Patient is a foreign national | Father                | null                | AB1  2CD | AB1 2CD        |
      | Family members | Patient is a foreign national | Mother                | null                | AB12CD   | AB1 2CD        |
      | Family members | Patient is a foreign national | Daughter              | null                | ab12cd   | ab1 2cd        |
      | Family members | Patient is a foreign national | Full Sibling          | null                | ab1 2cd  | ab1 2cd        |
      | Family members | Patient is a foreign national | Other                 | null                | ab1  2cd | ab1 2cd        |
