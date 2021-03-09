#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: Family Members Details Page 4- Field Validation_4

  @NTS-4380 @NTS-4054 @Z-LOGOUT
#    @E2EUI-859 @E2EUI-1882_scenario_2
  Scenario Outline:NTS-4380: Validate the Relationship to proband drop down values to check the order of the drop down is logical
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
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
    #NTS-4054 (E2EUI-1882 @scenario_2)
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the Add new patient to referral button
    Then the user will see error messages highlighted in red colour
      | message                              | color   |
      | Relationship to proband is required. | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name              | color   |
      | Relationship to proband | #dd2509 |
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks on RelationshipToProband drop down and sees the values of the drop down"<RelationshipToProband>" with recently used suggestion values
    Then the user clicks the Add new patient to referral button
    Examples:
      | FamilyMember   | reason_for_no_nhsNumber                                     | RelationshipToProband | FamilyMemberDetails                 |
      | Family members | Patient not eligible for NHS number (e.g. foreign national) | Father                | NHSNumber=9449305327:DOB=14-02-2012 |

#  @NTS-4053 @Z-LOGOUT
##    @E2EUI-2474 @scenario_3
#  ##Note: Scenarios 1 and 2 for this ticket is covered in 1990
#  Scenario Outline: NTS-4053 - To verify that Auto filled should not be enabled for search fields in family member page
#    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<FamilyMembers>" stage
#    And the user clicks on Add family member button
#    ###No more valid for Kyle Increment
#    #Then User clicks on a field "nhsNumber:dateDay:dateMonth:dateYear" and auto-complete is disabled
#    When the user navigates to the "<Panels>" stage
#    Then User clicks on a field "panelsSearchBox" and auto-complete is disabled
#    Examples:
#      | FamilyMembers  | Panels |
#      | Family members | Panels |

#  @NTS-4053 @Z-LOGOUT
##    @E2EUI-2474 @scenario_5
#  Scenario Outline: NTS-4053:  To verify that Auto filled should not be enabled for add tumour page
#    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<stage>" stage
#    Then the user is navigated to a page with title Add a tumour
#    ###No more valid for Kyle Increment
##    And User clicks on a field "dateDay:dateMonth:dateYear" and auto-complete is disabled
#    Examples:
#      | stage   |
#      | Tumours |

#  @NTS-4019 @Z-LOGOUT
##    @E2EUI-960
#  Scenario Outline:NTS-4019: Assign family members to specific tests
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
#    When the user navigates to the "<FamilyMember>" stage
#    And the user is navigated to a page with title Add a family member to this referral
#    And the user clicks on Add family member button
#    And the user is navigated to a page with title Find a family member
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
#    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
#    And the Test package page has Targeted genes section with the "<TargetedGenes>"
#    And the user selects the test to add to the family member "<FamilyMemberDetails>"
#    Examples:
#      | FamilyMember   | FamilyMemberDetails                 | RelationshipToProband | TargetedGenes    |
#      | Family members | NHSNumber=2000003850:DOB=28-09-2011 | Father                | Craniosynostosis |