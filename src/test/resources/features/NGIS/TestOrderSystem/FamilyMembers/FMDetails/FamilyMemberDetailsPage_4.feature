#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page 4- Field Validation_4

  @NTS-4413 @Z-LOGOUT
#    @E2EUI-833  @Scenario2
  Scenario Outline: NTS-4413 : Change 'Trio Pedigree' icon as it is upside down
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R105 |
    And the user clicks the Start Test Order Referral button
    And the user clicks the PDF order form button
    Then the user is navigated to a page with title Add a requesting organisation
    When the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks on Continue Button
    Then the user should be able to sees trio family icon in review test selection
    Examples:
      | ordering_entity_name |
      | Maidstone            |

  @NTS-4409 @Z-LOGOUT
#    @E2EUI-1426
  Scenario Outline: NTS-4409: Remove diagnosis Age at Onset
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    Then The user should not see the rare disease diagnoses "<AgeOfOnset>" field

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | AgeOfOnset   |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | Age of onset |

  @NTS-4380 @Z-LOGOUT
#    @E2EUI-859
  Scenario Outline:NTS-4380: Validate the Relationship to proband drop down values to check the order of the drop down is logical
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks on RelationshipToProband drop down and sees the values of the drop down"<RelationshipToProband>" with recently used suggestion values
    Then the user clicks the Add new patient to referral button
    Examples:
      | FamilyMember   | reason_for_no_nhsNumber       | RelationshipToProband |FamilyMemberDetails|
      | Family members | Patient is a foreign national | Father                | NHSNumber=9449305327:DOB=14-02-2012                  |


  @NTS-4053 @Z-LOGOUT
#    @E2EUI-2474 @scenario_3
  ##Note: Scenarios 1 and 2 for this ticket is covered in 1990
  Scenario Outline: NTS-4053 - To verify that Auto filled should not be enabled for search fields in family member page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | Rare-Disease | create a new patient record | Patient is a foreign national |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    Then User clicks on a field "nhsNumber:dateDay:dateMonth:dateYear" and auto-complete is disabled
    When the user navigates to the "<Panels>" stage
    Then User clicks on a field "panelsSearchBox" and auto-complete is disabled
    Examples:
      | FamilyMembers  |Panels |
      | Family members |Panels |

  @NTS-4053 @Z-LOGOUT
#    @E2EUI-2474 @scenario_5
  Scenario Outline: NTS-4053:  To verify that Auto filled should not be enabled for add tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And User clicks on a field "dateDay:dateMonth:dateYear" and auto-complete is disabled
    Examples:
      | stage   |
      | Tumours |

  @NTS-4019 @Z-LOGOUT
#    @E2EUI-960
  Scenario Outline:NTS-4019: Assign family members to specific tests
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMember>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the Test package page has Targeted genes section with the "<TargetedGenes>"
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    Examples:
      | FamilyMember   | FamilyMemberDetails                 | RelationshipToProband | TargetedGenes    |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Father                | Craniosynostosis |