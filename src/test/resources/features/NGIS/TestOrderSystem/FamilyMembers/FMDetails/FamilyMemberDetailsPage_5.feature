#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Family Members Details Page 5- Field Validation_5

  @NTS-4744 @Z-LOGOUT
#  @E2EUI-1694  @scenario1
  Scenario: NTS-4744: Referral create as a Proband
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | GEL_NORMAL_USER | NHSNumber=9449306206:DOB=06-05-2011 |
    Then the user is navigated to a page with title Check your patient

  @NTS-4744 @Z-LOGOUT
#    @E2EUI-1694 @scenario2
  Scenario Outline: NTS-4744: Referral create as a Family member
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral

    ##Note : Provide the FamilyMemberDetails as same as used in scenario 1 search
    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | NHSNumber=9449306206:DOB=06-05-2011 | Father                | DiseaseStatus=Unaffected |

  @NTS-4744 @Z-LOGOUT
#    @E2EUI-1694 @scenario3
  Scenario Outline: NTS-TODO: Verify the referrals relationship on patient page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user is navigated to a page with title Find your patient
    And the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NHSNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the user clicks the patient result card
    ##Referral Details Page
    When the user is navigated to a page with title Check your patient
    And the user should verify the role and relationship of patient on referral card
    Then the user should see the visible and clickable referral card

    Examples:
      | patient-search-type | NHSNumber  | DOB        |
      | NGIS                | 9449306206 | 06-05-2011 |


  @NTS-4052 @Z-LOGOUT
#    @E2EUI-1837 @scenario_2
  Scenario Outline: NTS-4052: Multidate picker - Real dates validation on Family Members Page.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R85 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    And the user clicks the NO button
    When the user click YES button for the question - Do you have the NHS no?
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB2>" fields
    Then the user does not see an error message on the page
    Examples:
      | patient-search-type | FamilyMembers  | NhsNumber  | DOB        | error_message                  | DOB2       |
      | NGIS                | Family members | 9449305935 | 20-13-2000 | Enter a month between 1 and 12 | 19-03-2000 |

  @NTS-4054 @Z-LOGOUT
#    @E2EUI-1882
  Scenario Outline: NTS-4054: As a user, I want to see the 'Relationship to proband' field highlighted with a validation error if left empty on the 'Confirm family member details' or 'Add a new family member' page when adding a new family member
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user clicks the Save and Continue button
    Then the user will see error messages highlighted in red colour
      | message                              | color   |
      | Relationship to proband is required. | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name              | color   |
      | Relationship to proband | #dd2509 |
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | FamilyMemberDetails                 |
      | Family members | Test package | 2                | NHSNumber=9449305919:DOB=24-07-2011 |

  @NTS-4054 @Z-LOGOUT
#    @E2EUI-1882 @scenario_2
  Scenario Outline: NTS-4054: As a user, I want to see the 'Relationship to proband' field highlighted with a validation error if left empty on the 'Confirm family member details' or 'Add a new family member' page when adding a new family member
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    And the user clicks on the hyper link
    Then the user is navigated to a page with title Add a new patient
    When the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the Add new patient to referral button
    Then the user will see error messages highlighted in red colour
      | message                              | color   |
      | Relationship to proband is required. | #dd2509 |
    And the blank mandatory field labels highlighted in red color
      | field_name              | color   |
      | Relationship to proband | #dd2509 |
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants | reason_for_no_nhsNumber       | SearchDetails                                                                  | patient-search-type |
      | Family members | Test package | 2                | Patient is a foreign national | DOB=13-03-2010:FirstName=NELLY:LastName=StschitZ:Gender=Female:Postcode=RE40BE | NGIS                |