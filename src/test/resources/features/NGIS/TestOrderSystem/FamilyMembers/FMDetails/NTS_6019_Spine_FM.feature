#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: RD SPINE Family:NTS-6019:E2EUI-3133:- Find and select patient record (Family Member Spine / NGIS Record )

  @NTS-6019 @Z-LOGOUT
  Scenario Outline:NTS-6019:E2EUI-3133:Scenario-1: Find and select patient record (Family Member Spine Record )
      ###Check the patient NHS
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ##Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    And the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ## Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ##Referral creation for adding SPINE family member
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R109 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-05-2003:Gender=Male |
#    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family - No of participants 2
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Adding a family member - Father - SPINE Patient
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    Then a "<PatientType>" result is successfully returned
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    Then the user should "get" participant error message as "This record is missing essential information"
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add a family member to this referral
    Then the "<FamilyMembers>" stage is marked as Completed

    Examples:
      | FamilyMemberDetails                 | RelationshipToProband | PatientType | DiseaseStatusDetails  | NhsNumber  | DOB        | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | FamilyMembers  |
      | NHSNumber=2000003699:DOB=02-02-1984 | Father                | NHS Spine   | DiseaseStatus=Unknown | 2000003699 | 02-02-1984 | Patient details | Requesting organisation | Test package | 2              | Family members |

  @NTS-6019 @Z-LOGOUT
  Scenario Outline:NTS-6019:E2EUI-3133:Scenario-2:Find and select patient record (Family Member NGIS Record )
    ##Check the patient NHS
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ##Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    Then a "<PatientType>" result is successfully returned
    ##Referral creation for adding NGIS family member
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R109 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=15-05-2020:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    And the "<PatientDetails>" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family - No of participants 2
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Adding a family member - Father - NGIS Patient
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    Then a "<PatientType>" result is successfully returned
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    Then the user should "get" participant error message as "This record is missing essential information"
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add a family member to this referral
    Then the "<FamilyMembers>" stage is marked as Completed

    Examples:
      | FamilyMemberDetails                 | RelationshipToProband | PatientType | DiseaseStatusDetails  | NhsNumber  | DOB        | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | FamilyMembers  |
      | NHSNumber=2000003842:DOB=13-05-2001 | Father                | NGIS        | DiseaseStatus=Unknown | 2000003842 | 13-05-2001 | Patient details | Requesting organisation | Test package | 2              | Family members |
