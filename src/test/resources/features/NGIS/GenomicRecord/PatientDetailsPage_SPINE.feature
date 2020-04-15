#@regression
#@patientDetails_SPINE
@04-GENOMIC_RECORD
@SYSTEM_TEST_SPINE

Feature: Patient details page_SPINE

  @NTS-3541 @Z-LOGOUT
#    @E2EUI-1289
  Scenario Outline: NTS-3541:Show patient's referrals on patient detail page
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | RD | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
#    When the user clicks on the patient card
    And the user clicks the patient result card
    Then the user is navigated to a page with title Confirm family member details
    When the selects the ethnicity as "B - White - Irish"
#    And the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the patient's referrals are displayed at the bottom of the page
    And the referral status from the card is "Created"

    Examples:
      | patient-search-type | stage1          | stage          | FamilyMemberDetails                 | RelationshipToProband |
      | NGIS                | Patient details | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Full Sibling          |


  @NTS-4795 @Z-LOGOUT
#    @E2EUI-969
  Scenario Outline: NTS-4795:SPINE patient search "<patient-search-type>" With NHS Number and Date of Birth and add to NGIS
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    And the Patient Details page is displayed
    When the user fills in the Ethnicity field "B - White - Irish"
    And the Add To Patient Details "<addToPatientDetails>" button is displayed

    Examples:
      | patient-search-type | NhsNumber  | DOB        | addToPatientDetails |
      | NHS Spine           | 9449308691 | 23-05-2011 | Add details to NGIS |


  @NTS-4795 @Z-LOGOUT
#    @E2EUI-969
  Scenario Outline: NTS-4795:SPINE patient search "<patient-search-type>" With first name, last name, DOB, Gender and Post-code
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    And the Patient Details page is displayed
    When the user fills in the Ethnicity field "B - White - Irish"
    And the Add To Patient Details "<addToPatientDetails>" button is displayed

    Examples:
      | patient-search-type | addToPatientDetails | SearchDetails                                                                     |
      | NHS Spine           | Add details to NGIS | DOB=23-05-2011:FirstName=COLUMBINE:LastName=CRANE:Gender=Unknown:Postcode=RH3 7JP |
      | NHS Spine           | Add details to NGIS | DOB=23-05-2011:FirstName=COLUMBINE:LastName=CRANE:Gender=Unknown                  |

#  SPINE DATA replaced with NGIS DATA

#  @NTS-3068 @E2EUI-1182 @E2EUI-1463 @Z-LOGOUT
#  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with with NHS-Number
#    Given a web browser is at the patient search page
#      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
#    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
#    And the user clicks the Search button
#    Then a "<patient-search-type>" result is successfully returned
#    And the user clicks the patient result card
#    Then the Patient Details page is displayed
#    And the correct details of the "<patient-search-type>" are displayed in patient details
#
#    Examples:
#      | patient-search-type | NhsNumber  | DOB        |
#      | NHS Spine           | 9449310602 | 23-03-2011 |


#  @NTS-3068 @E2EUI-1182 @Z-LOGOUT
#  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with without NHS-Number
#    Given a web browser is at the patient search page
#      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
#    And the user clicks the NO button
#    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
#    And the user clicks the Search button
#    Then a "<patient-search-type>" result is successfully returned
#    And the user clicks the patient result card
#    Then the Patient Details page is displayed
#    And the correct details of the "<patient-search-type>" are displayed in patient details
#
#    Examples:
#      | patient-search-type | SearchDetails                                                            |
#      | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz:Gender=Female |


#  @NTS-3067 @E2EUI-1128 @Z-LOGOUT
#  Scenario Outline:NTS-3067:The user can not create a referral for an existing patient without a clinical indication test selected
#    Given a web browser is at the patient search page
#      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
#    And a web browser is at the Patient Details page of a "<patient-search-type>" patient with NHS number "<NhsNumber>" and Date of Birth "<DOB>" without clinical indication test selected
#    And the clinical indication ID missing banner is displayed
#    And the Start Referral button is disabled
#
#    Examples:
#      | patient-search-type | NhsNumber  | DOB        |
#      | NHS Spine           | 9449310602 | 23-03-2011 |