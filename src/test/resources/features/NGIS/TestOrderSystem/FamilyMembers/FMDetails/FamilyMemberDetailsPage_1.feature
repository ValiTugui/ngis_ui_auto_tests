#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: Family Members Details Page 1- Field Validation_1

#  @NTS-3235 @NTS-4054_Scenario-01 @Z-LOGOUT
##    @E2EUI-908 @E2EUI-1882
#  Scenario Outline: NTS-3235:E2EUI-908: Verify addition of a family member to a referral without providing Relationship to Proband field.
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1971:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<stage>" stage
#    Then the user is navigated to a page with title Add a family member to this referral
#    And the user clicks on Add family member button
#    And the user search the family member with the specified details "<FamilyMemberDetails>"
#    Then the patient card displays with Born,Gender and NHS No details
#    When the user clicks on the patient card
#    Then the user is navigated to a page with title Add missing family member details
#    When the user clicks on edit patient details
#    Then the user is navigated to a page with title Edit patient details
#    And the mandatory fields shown with the symbol in red color
#      | mandatory_field         | field_type | symbol | symbol color |
#      | First name              | label      | ✱      | #dd2509      |
#      | Last name               | label      | ✱      | #dd2509      |
#      | Date of birth           | legend     | ✱      | #dd2509      |
#      | Gender                  | label      | ✱      | #dd2509      |
#      | Life status             | label      | ✱      | #dd2509      |
#      | Relationship to proband | label      | ✱      | #dd2509      |
#    When the user clicks the Save and Continue button
#    Then the user will see error messages highlighted in red colour
#      | message                              | color   |
#      | Relationship to proband is required. | #dd2509 |
#    And the blank mandatory field labels highlighted in red color
#      | field_name              | color   |
#      | Relationship to proband | #dd2509 |
#
#    Examples:
#      | stage          | FamilyMemberDetails                 |
#      | Family members | NHSNumber=2000003745:DOB=12-10-2011 |

  @NTS-3300 @NTS-3301 @NTS-3298 @NTS-3235 @NTS-4054_Scenario-01 @Z-LOGOUT
#    @E2EUI-1349 @E2EUI-1291 @E2EUI-1369 @E2EUI-908 @E2EUI-1882
  Scenario Outline: NTS-3300: Check family member Details validation:The family member details have to be verified on the 'Check family member Details' Page with respect to the 'Find a family member' Page
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1972:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    And verify the patient card displays the same NHS and DOB in "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the default family member details page is correctly displayed with the proper number of fields
    And the mandatory fields shown with the symbol in red color
      | mandatory_field         | field_type | symbol | symbol color |
      | First name              | label      | ✱      | #dd2509      |
      | Last name               | label      | ✱      | #dd2509      |
      | Date of birth           | legend     | ✱      | #dd2509      |
      | Gender                  | label      | ✱      | #dd2509      |
      | Life status             | label      | ✱      | #dd2509      |
      | Relationship to proband | label      | ✱      | #dd2509      |
      | Ethnicity               | label      | ✱      | #dd2509      |

    Then the user is navigated to a page with title Edit patient details
    When the user clicks the Save and Continue button
    Then the user will see error messages highlighted in red colour
      | message                              | color   |
      | Relationship to proband is required. | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name              | color   |
      | Relationship to proband | #dd2509 |

    And confirm family member details page populate with same details found in patient card for "<FamilyMemberDetails>"
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the color of referral name for "<FamilyMemberDetails>" displays as "<ReferralColor>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | ReferralColor |
      | Family members | NHSNumber=2000003796:DOB=12-05-2001 | Maternal Aunt         | #da291c       |

#  @NTS-3298 @Z-LOGOUT
##    @E2EUI-1369
#  Scenario Outline: NTS-3298: Verify "relationship to proband" field mandatory when adding a family member to referral
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1990:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<stage>" stage
#    Then the user is navigated to a page with title Add a family member to this referral
#    And the user clicks on Add family member button
#    Then the user is navigated to a page with title Find a family member
#    And the user search the family member with the specified details "<FamilyMemberDetails>"
#    Then the patient card displays with Born,Gender and NHS No details
#    And verify the patient card displays the same NHS and DOB in "<FamilyMemberDetails>"
#    When the user clicks on the patient card
#    Then the user is navigated to a page with title Add missing family member details
#    When the user clicks on edit patient details
#    Then the user is navigated to a page with title Edit patient details
#    And the default family member details page is correctly displayed with the proper number of fields
#    And the mandatory fields shown with the symbol in red color
#      | mandatory_field         | field_type | symbol | symbol color |
#      | First name              | label      | ✱      | #dd2509      |
#      | Last name               | label      | ✱      | #dd2509      |
#      | Date of birth           | legend     | ✱      | #dd2509      |
#      | Gender                  | label      | ✱      | #dd2509      |
#      | Life status             | label      | ✱      | #dd2509      |
#      | Relationship to proband | label      | ✱      | #dd2509      |
#      | Ethnicity               | label      | ✱      | #dd2509      |
#    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
#    And the user clicks the Save and Continue button
#
#    Examples:
#      | stage          | FamilyMemberDetails                 | RelationshipToProband |
#      | Family members | NHSNumber=2000003753:DOB=12-09-2011 | Maternal Aunt         |

#  @NTS-3297 @Z-LOGOUT
##    @E2EUI-1012
#  Scenario Outline: NTS-3297: To validate the flow when the user chooses to add a test for family members
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<stage>" stage
#    Then the user is navigated to a page with title Add a family member to this referral
#    And the user clicks on Add family member button
#    Then the user is navigated to a page with title Find a family member
#    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
#    And the user search the family member with the specified details "<FamilyMemberDetails>"
#    Then the patient card displays with Born,Gender and NHS No details
#    When the user clicks on the patient card
#    Then the user is navigated to a page with title Add missing family member details
#    When the user clicks on edit patient details
#    Then the user is navigated to a page with title Edit patient details
#    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Continue with this family member
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Select tests for
#    And the user selects the test to add to the family member "<FamilyMemberDetails>"
#    And the user clicks the Save and Continue button
#    And the referral submit button is not enabled
#    Examples:
#      | stage          | FamilyMemberDetails                 | RelationshipToProband |
#      | Family members | NHSNumber=2000000754:DOB=11-01-1975 | Full Sibling          |