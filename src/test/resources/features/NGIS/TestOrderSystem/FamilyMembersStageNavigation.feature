@regression
@COMP08_P0
@FamilyMemberStageNavigation
Feature: Family Members Navigation Stage Validation

  @COMP8_TO_Familymembers
    @familyMemberStageNavigation_01 @LOGOUT @NTS-3243 @E2EUI-1287 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1287: Verify the Family Members stage Navigation Flow
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestions>" stage is marked as Completed
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
    And the user can select the test to add to the family member
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user returns to family member landing page with the added family member details

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | NHSNumber=9449305552:DOB=20-09-2008 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_02 @LOGOUT @NTS-3299 @E2EUI-1698 @v_1 @P0
  Scenario Outline: E2EUI-1698: Verify the family members test package are selected by default
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
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
    When the user deselects the test
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user clicks on back button on family member details page
    Then the user sees test remains as deselected

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_03 @LOGOUT @NTS-3301 @E2EUI-1291 @v_1 @P0
  Scenario Outline: E2EUI-1291: Verify the current additional family member information
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And reads the details of selected family member "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should be able to see family member's details card
    And the editing referral color in Red

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband |
      | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_04 @LOGOUT @NTS-3292 @NTS-3293 @NTS-3293 @E2EUI-1331 @E2EUI-1485 @E2EUI-1639 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1331-1485-1639: Remove a family member from a referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestions>" stage is marked as Completed
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
    And the user can select the test to add to the family member
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user returns to family member landing page with the added family member details
    ##The below one step is for E2EUI-1485 and following that for 1331
    And the family member details on family Member landing page is correctly displayed
    When the user removes the family member
    Then the user should be able to see "<SuccessDeleteMessage>" removal message on the family member landing page
    ##Below Steps for 1639
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    When the user navigates to the "<FamilyMembers>" stage
    Then the user should not see the removal message on the family member landing page

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | FamilyMemberDetails                 | ClinicalQuestionDetails                                         | RelationshipToProband | DiseaseStatusDetails  | SuccessDeleteMessage                |
      | Family members | Test package | Clinical questions | 2                | NHSNumber=9449306680:DOB=14-06-2011 | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Full Sibling          | DiseaseStatus=Unknown | Family member removed from referral |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_05 @LOGOUT @NTS-3295 @E2EUI-1279 @E2EUI-1362 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1279-1362: Verify the family members page layout
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
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
    And the user can select the test to add to the family member
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member test package page is correctly displayed

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |


  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_06 @LOGOUT @NTS-3291 @E2EUI-1604 @v_1 @P0
  Scenario Outline: E2EUI-1604: Verify that Indicate family members with outstanding questions to answer
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks on the patient card
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    When the user navigates to the "<FamilyMembers>" stage
    Then the user will be able to see an error message as "<ErrorMessage>" in "<MessageColor>" for the family member
    And the user should be able to see incomplete family member in "<MessageColor>"
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member landing page displayed without incomplete error message

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | ErrorMessage                                                    | MessageColor | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449310319:DOB=09-12-2010 | Full Sibling          | There is essential clinical information missing from this entry | #da291c      | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_07 @LOGOUT @NTS-3322 @E2EUI-1509 @v_1 @P0
  Scenario Outline: E2EUI-1509: Verify family members has completed in to-do list
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | FamilyMember   | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_08 @LOGOUT @NTS-3309 @E2EUI-2105 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-2105: Verify warning message if number of family members is less than number of participants
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    Then the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."
    When the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 3                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_09 @LOGOUT @NTS-3329 @E2EUI-1665 @v_1 @P0
  Scenario Outline: E2EUI-1665: Verify Global patient information bar component
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Requesting organisation>" stage
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the Save and Continue button should be clickable
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<Family members>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user should be able to see the patient details in family member landing page
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see the patient details in patient choice page
    Then the user should verify the data from family member landing page and patient choice page
    When the user navigates to the "<Print forms>" stage
    And the user should be able to see the patient details in print forms page
    Then the user should verify the data from family member landing page and print forms page

    Examples:
      | Requesting organisation | ordering_entity_name | Family members | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | Print forms |
      | Requesting organisation | Maidstone            | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected | Print forms |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_10 @LOGOUT @NTS-3309 @E2EUI-2104 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-2104: Validate the user is displayed with the warning message on Family members landing page by adding extra Family member more than the expected number of participants
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails1>"
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member test package page is correctly displayed
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails2>"
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants | FamilyMemberDetails1                | FamilyMemberDetails2                | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | NHSNumber=9449310343:DOB=02-03-2008 | Full Sibling          | DiseaseStatus=Unaffected |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_11 @LOGOUT @NTS-3330 @E2EUI-1202 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1202: User is completing a referral and wants to add a family member record to the referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the default family member details page is correctly displayed
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    And the user clicks on a test that is selected and the test is no longer selected
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then The user should be able to see details like name,relationship with proband,Date of birth,Gender,NHS No & Patient NGIS ID for all the family members added.
    And There is a message displayed on top of landing page stating "Tested family members you add here will be visible in the pedigree.You can add non-tested family members to the pedigree as well."
    And the family member status "<TestStatus>" Marked in "<color>"
    And The user should be able to view patient choice status for all the family members added.
    And The user should also see the separate edit or delete icon under every family member details provided.
    And The user also should see the Add Family Member button and continue button displayed

    Examples:
      | TestStatus       | color   | stage          | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Being tested     | #e5f6f5 | Family members | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |
      | Not being tested | #fdf3e5 | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Maternal Aunt         | DiseaseStatus=Unknown    |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_12 @LOGOUT @NTS-3337 @E2EUI-1326 @v_1 @P0
  Scenario Outline: Verify the family members test package are selected by default
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And  the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the deselected member status display as "<Status>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | Status           |
      | Family members | NHSNumber=9449310165:DOB=25-12-2000 | Full Sibling          | DiseaseStatus=Unaffected | Not being tested |

  @COMP8_TO_PatientSearch
    @familyMemberStageNavigation_13 @NTS-3339 @E2EUI-1791 @v_1 @P0
  Scenario Outline: Verify family members has completed in to-do list
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310327:DOB=16-12-1970 |
    When the user navigates to the "<Family Members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should be able to see the patient identifiers on family member landing page
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see the patient identifiers on patient choice page similar as in family member landing page

    Examples:
      | Family Members | Patient choice stage |
      | Family members | Patient choice       |

  @COMP8_TO_Familymembers
    @familyMemberDetailsPage_14 @NTS-3338 @E2EUI-1510 @BVT_P0 @v_1 @P0
  Scenario Outline: E2EUI-1510: To verify the error messages in family members test selection page by adding less and more number of expected participants to the referral.
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user should see an error message displayed as "One participant was quoted for this test" in "<color>" color
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on the link to amend the number of participants for test
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants2>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<Family member>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails2>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage with the "<RelationshipToProband2>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user should see a warning message displayed as "Four participants were quoted for this test" in "<color2>" color

    Examples:
      | TestPackage  | NoOfParticipants | Family member  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | color   | NoOfParticipants2 | FamilyMemberDetails2                | RelationshipToProband2 | color2  |
      | Test package | 1                | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Maternal Aunt         | DiseaseStatus=Unaffected | #dd2509 | 4                 | NHSNumber=9449310157:DOB=15-01-2000 | Full Sibling           | #425563 |

