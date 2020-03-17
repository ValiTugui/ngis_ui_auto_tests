#@regression
#@FamilyMembersDetailsPage
@TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page - Field Validation_2

  @NTS-3296 @LOGOUT
#    @E2EUI-1038
  Scenario Outline: NTS-3296: Verify the mandatory input fields validations for non-NHS family member creation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1970:Gender=Male |
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

  @NTS-3342 @LOGOUT
#    @E2EUI-1790
  Scenario Outline: NTS-3342: Update FamilyMember card to use PatientIndentifiers in Test package and Patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
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

  @NTS-3475
#    @E2EUI-2090 @v_1 @P1
  Scenario Outline: NTS-3475: Verify Expected Family Members Error message
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
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

  @NTS-4413 @LOGOUT
#    @E2EUI-833 @E2EUI-1880 @LOGOUT @Scenario1
  Scenario Outline: NTS-4413 :  Change 'Trio Pedigree' icon as it is upside down
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Check your patient
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user should be able to see trio family icon in test package

    Examples:
      | TestPackage  |
      | Test package |

  @NTS-4413  @LOGOUT
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

  @NTS-4409 @LOGOUT
#    @E2EUI-1426
  Scenario Outline: NTS-4409: Remove diagnosis Age at Onset
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    Then The user should not see the rare disease diagnoses "<AgeOfOnset>" field

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | AgeOfOnset   |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | Age of onset |

  @NTS-4380 @LOGOUT
#    @E2EUI-859
  Scenario Outline:NTS-4380: Validate the Relationship to proband drop down values to check the order of the drop down is logical
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<FamilyMember>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message "No patient found" is displayed below the search button
    Then the user clicks on the create new patient record
    And the user is navigated to a page with title Add a new patient to the database
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks on RelationshipToProband drop down and sees the values of the drop down"<RelationshipToProband>" with recently used suggestion values
    Then the user clicks the Add new patient to referral button
    Examples:
      | FamilyMember   | reason_for_no_nhsNumber       | RelationshipToProband | relationShipType |
      | Family members | Patient is a foreign national | Father                | Father           |


  @NTS-4053 @LOGOUT
#    @E2EUI-2474 @scenario_3
  ##Note: Scenarios 1 and 2 for this ticket is covered in 1990
  Scenario Outline: NTS-4053 - To verify that Auto filled should not be enabled for search fields in family member page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    Then User clicks on a field "nhsNumber:dateDay:dateMonth:dateYear" and auto-complete is disabled
    When the user navigates to the "<Panels>" stage
    Then User clicks on a field "panelsSearchBox" and auto-complete is disabled
    Examples:
      | FamilyMembers  |Panels |
      | Family members |Panels |

  @NTS-4053 @LOGOUT
#    @E2EUI-2474 @scenario_5
  Scenario Outline: NTS-4053:  To verify that Auto filled should not be enabled for add tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    Then User clicks on a field "dateDay:dateMonth:dateYear" and auto-complete is disabled
    Examples:
      | stage   |
      | Tumours |

  @NTS-4019 @LOGOUT
#    @E2EUI-960
  Scenario Outline:NTS-4019: Assign family members to specific tests
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    When the user navigates to the "<FamilyMember>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the Test package page has Targeted genes section with the "<TargetedGenes>"
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    Examples:
      | FamilyMember   | FamilyMemberDetails                 | RelationshipToProband | TargetedGenes    |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Father                | Craniosynostosis |
