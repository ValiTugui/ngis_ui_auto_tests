@regression
@TO_RD
@FamilyMemberStageNavigation

Feature: Family Members Navigation Stage Validation

  @NTS-3309 @E2EUI-2105 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3309: Verify warning message if number of family members is less than number of participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    Then the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."
    When the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
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

  @NTS-3329 @E2EUI-1665 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3329: Verify Global patient information bar component
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
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
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user reads the patient details in family member landing page
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user reads the patient details in patient choice page
    And the user should see same set of family member identifiers in family member landing page and patient choice page
    When the user navigates to the "<Print forms>" stage
    And the user reads the patient details in print forms page
    Then the user should see same data in family member landing page and print forms page

    Examples:
      | Requesting organisation | ordering_entity_name | Family members | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | Print forms |
      | Requesting organisation | Maidstone            | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected | Print forms |

  @NTS-3309 @E2EUI-2104 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3309: Validate the user is displayed with the warning message on Family members landing page by adding extra Family member more than the expected number of participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails1>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails1>" with the "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member test package page is correctly displayed
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails2>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails2>" with the "<RelationshipToProband>"
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

  @NTS-3330 @E2EUI-1202 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3330: User is completing a referral and wants to add a family member record to the referral
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the default family member details page is correctly displayed
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
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

  @NTS-3337 @E2EUI-1326 @LOGOUT @v_1 @P0
  Scenario Outline:NTS-3337: Verify the family members test package are selected by default
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
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

  @NTS-3339 @E2EUI-1791 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3339: Update PatientList component in family member section to use PatientIdentifiers
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Family Members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should be able to see the patient identifiers on family member landing page
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see the patient identifiers on patient choice page similar as in family member landing page

    Examples:
      | Family Members | Patient choice stage |
      | Family members | Patient choice       |

  @NTS-3338 @LOGOUT @E2EUI-1510 @BVT_P0 @v_1
  Scenario Outline: NTS-3338: To verify the error messages in family members test selection page by adding less and more number of expected participants to the referral.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
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
    When the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband>"
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
    And the user fills the FamilyMemberDetailsPage for "<FamilyMemberDetails>" with the "<RelationshipToProband2>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member is selected by default
    And the user should see a warning message displayed as "Four participants were quoted for this test" in "<color2>" color

    Examples:
      | TestPackage  | NoOfParticipants | Family member  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | color   | NoOfParticipants2 | FamilyMemberDetails2                | RelationshipToProband2 | color2  |
      | Test package | 1                | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Maternal Aunt         | DiseaseStatus=Unaffected | #dd2509 | 4                 | NHSNumber=9449310157:DOB=15-01-2000 | Full Sibling           | #425563 |

