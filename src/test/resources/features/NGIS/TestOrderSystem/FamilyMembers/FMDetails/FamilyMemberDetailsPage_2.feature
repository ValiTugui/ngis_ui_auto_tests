#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page 2- Field Validation_2

  @NTS-3296 @Z-LOGOUT
#    @E2EUI-1038
  Scenario Outline: NTS-3296: Verify the mandatory input fields validations for non-NHS family member creation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1970:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    And the user clicks on the hyper link
    Then the user is navigated to a page with title Create a record for this family member
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

  @NTS-3342 @Z-LOGOUT
#    @E2EUI-1790
  Scenario Outline: NTS-3342: Update FamilyMember card to use PatientIndentifiers in Test package and Patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    And the user clicks on edit icon to update patient choice status for family member
    Then the user is navigated to a page with title Add family member patient choice information
    When the user clicks on back button
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Family member  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | NHSNumber=9449310157:DOB=15-01-2000 | Full Sibling          | DiseaseStatus=Unaffected |

  @NTS-3475
#    @E2EUI-2090 @v_1 @P1
  Scenario Outline: NTS-3475: Verify Expected Family Members Error message
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should "get" participant error message as "<ErrorMessage>"

    Examples:
      | Requesting organisation | ordering_entity_name | FamilyMembers  | NoOfParticipants | ErrorMessage                                                                                                                                                                                |
      | Requesting organisation | Queen                | Family members | 3                | The number of participants you’ve selected for one or more tests does not match the number that was entered. |

  @NTS-4413 @Z-LOGOUT
#    @E2EUI-833 @E2EUI-1880 @Scenario1
  Scenario Outline: NTS-4413 :  Change 'Trio Pedigree' icon as it is upside down
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user should be able to see trio family icon in test package
    Examples:
      | TestPackage  |
      | Test package |

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