@regression
@COMP08_P0
@FamilyMembersDetailsPage
Feature: Family Members Details Validation

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_01 @NTS-3235 @E2EUI-908 @v_1 @P0
  Scenario Outline: E2EUI-908: Verify addition of a family member to a referral without providing Relationship to Proband field.
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    And the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage                         | MessageColor |
      | Family members | NHSNumber=9449310157:DOB=15-01-2000 | Relationship to proband is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_02 @NTS-3300 @E2EUI-1349 @v_1 @P0
  Scenario Outline: E2EUI-1349: Verify family member details page - Confirm family member details
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the default family member details page is correctly displayed with the proper number of fields
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for

    Examples:
      | stage          | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Maternal Aunt         |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_03 @NTS-3298 @E2EUI-1369 @v_1 @P0
  Scenario Outline: E2EUI-1369: Verify "relationship to proband" field mandatory when adding a family member to referral
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    Then the default family member details page is correctly displayed with the proper number of fields
    And the user clicks the Save and Continue button
    Then the message displays as "<ErrorMessage>" in color "<MessageColor>"

    Examples:
      | stage          | FamilyMemberDetails                 | ErrorMessage                         | MessageColor | RelationshipToProband |
      | Family members | NHSNumber=9449310602:DOB=23-03-2011 | Relationship to proband is required. | #dd2509      | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_04 @LOGOUT @NTS3297 @E2EUI-1012 @v_1 @P0
  Scenario Outline: E2EUI-1012: To validate the flow when the user chooses to add a test for family members
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user can select the test to add to the family member

    Examples:
      | stage          | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_05 @NTS3309 @E2EUI-1539 @v_1 @P0
  Scenario Outline: E2EUI-1539: Verify message when the number of participants in Test Package are less than family member selected
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should see mismatch message in selected and actual participant as "<ErrorMessage>"
    When the user clicks on participant amendment link to amend the number of participants
    Then the user is navigated to a page with title Confirm the test package
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | ErrorMessage                                                                                                |
      | Family members | Test package | 2                | The number of participants you’ve selected for one or more tests does not match the number that was entered |


  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_06 @NTS3309 @E2EUI-1539 @v_1 @P0
  Scenario Outline: E2EUI-1539: Verify message when the number of participants in Test Package are same as family member
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails  |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unknown |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_07 @NTS3309 @E2EUI-1539 @v_1 @P0
  Scenario Outline: E2EUI-1539:  Verify the message when number of participants in Test Package are less than family member
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user should see mismatch message in selected and actual participant as "<ErrorMessage>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails  | ErrorMessage                                                                                                |
      | Family members | NHSNumber=9449305536:DOB=16-07-2011 | Full Sibling          | DiseaseStatus=Unknown | The number of participants you’ve selected for one or more tests does not match the number that was entered |

  @COMP8_TO_PatientSearch
    @familyMemberDetailsPage_08 @LOGOUT @NTS-3296 @E2EUI-1038 @v_1 @P0
  Scenario Outline: E2EUI-1038: Verify the mandatory input fields validations for non-NHS family member creation
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    When the user clicks on the create new patient record
    Then the user is navigated to a page with title Add a new patient to the database
    And the new patient page is correctly displayed with expected fields
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Add new patient to referral button
    Then the message will be displayed as "<MandatoryFieldErrorMessage>" in "<MessageColor>" in new patient page
    Examples:
      | FamilyMember   | SearchDetails                                               | PatientSearchMessage | ClearFields | MessageColor | MandatoryFieldErrorMessage                                                                                                                                                                                                   |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     | Gender      | #dd2509      | First name is required.,Last name is required.,Date of birth is required.,Gender is required.,Life status is required.,Select the reason for no NHS Number,Hospital number is required.,Relationship to proband is required. |

