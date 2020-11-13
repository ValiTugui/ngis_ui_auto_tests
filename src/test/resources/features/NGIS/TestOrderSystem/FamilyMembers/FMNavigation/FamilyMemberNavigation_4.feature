#@FamilyMemberStageNavigation
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Navigation Stage 4 - Member additions

  @NTS-3295 @Z-LOGOUT
#    @E2EUI-1279 @E2EUI-1362 @E2EUI-1581
  Scenario Outline: NTS-3295:E2EUI-1279,E2EUI-1362,E2EUI-1581: Verify the family members page layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1999:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And subtitle of the page displayed as Tested family members you add here will be visible in the pedigree.
    And subtitle links as add non-tested family members
    ##Added patient details on landing page covered in below step
    And the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-03-1942:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

    Examples:
      | FamilyMembers  | TestPackage  | Two |
      | Family members | Test package | 2   |

  @NTS-3301 @Z-LOGOUT
#    @E2EUI-1291
  Scenario Outline: NTS-3301:E2EUI-1291: Verify the current additional family member information
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-09-2011:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    And the color of referral name for "<FamilyMemberDetails>" displays as "<ReferralColor>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                                               | ReferralColor |
      | Family members | NHSNumber=NA:DOB=14-04-2011:Gender=Male:Relationship=Full Sibling | #da291c       |

  @NTS-3322 @Z-LOGOUT
#    @E2EUI-1509
  Scenario Outline: NTS-3322:E2EUI-1509: Verify family members has completed in to-do list
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1955:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-02-2011:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | FamilyMember   | TestPackage  | Two |
      | Family members | Test package | 2   |

  @NTS-4801 @Z-LOGOUT
#    @E2EUI-1106
  Scenario Outline: NTS-4801:E2EUI-1106 - Family members add page - Add non-nullable validation for system fields
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1999:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user navigate to Family Member - Add a new Patient to the database page "<pageTitle>"
      | APP_URL | family-members/new |
    And the user clicks the Add new patient to referral button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                   | messageColourHeader |
      | First name ✱                   | First name is required.              | #dd2509             |
      | Last name ✱                    | Last name is required.               | #dd2509             |
      | Date of birth ✱                | Date of birth is required.           | #dd2509             |
      | Gender ✱                       | Gender is required.                  | #dd2509             |
      | Life status ✱                  | Life status is required.             | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.               | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number  | #dd2509             |
      | Hospital number ✱              | Hospital number is required.         | #dd2509             |
      | Relationship to proband ✱      | Relationship to proband is required. | #dd2509             |

    Examples:
      | FamilyMembers  | pageTitle                              |
      | Family members | Create a record for this family member |

  @NTS-3503 @Z-LOGOUT
#    @E2EUI-1897
  Scenario Outline: NTS-3503: Verify Relationship to proband field in the family member's details page is cleared when submitting the Patient Details stage
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Add a requesting organisation
    ###Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    ###Patient Details
    When the user navigates to the "<PatientDetails>" stage
    And the user clicks the Save and Continue button
    ###Family Members
    And the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user edits to complete the highlighted family member
    Then the user is navigated to a page with title Continue with this family member
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    Then the user sees the relationship to proband which was previously selected is same as "<RelationshipToProband>"

    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | PatientDetails  | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=2000004768:DOB=02-08-1968 | Full Sibling          | Patient details | DiseaseStatus=Unaffected |

  @NTS-3503 @Z-LOGOUT
#    @E2EUI-1898
  Scenario Outline: NTS-3503: Verify the test selection page by creating a new patient from the family members stage
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER | child |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father | Father                |
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And the user is able to clicks on deselected test

    Examples:
      | NoOfParticipants | FamilyMembers  |
      | 2                | Family members |