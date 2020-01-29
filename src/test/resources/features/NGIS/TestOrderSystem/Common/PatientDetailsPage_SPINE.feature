@regression
@TO_Common
@patientDetails_SPINE


Feature: Patient details page_SPINE

@NTS-3541 @E2EUI-1289 @LOGOUT @PO @v_1
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
And the user selects the Relationship to proband as "<RelationshipToProband>"
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
And the Relationship to Proband from the patient referral card is "<RelationshipToProband>"

Examples:
| patient-search-type | stage1          |   stage          | FamilyMemberDetails                 | RelationshipToProband |
| NGIS                | Patient details |   Family members | NHSNumber=9449310122:DOB=30-06-1974 | Full Sibling          |