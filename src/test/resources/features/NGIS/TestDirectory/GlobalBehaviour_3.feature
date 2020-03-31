#@regression
#@globalBehaviour
@TEST_DIRECTORY
@SYSTEM_TEST
Feature: Global Behaviour Page

@NTS-3501 @LOGOUT
#    @E2EUI-1761
Scenario Outline:NTS-3501:Validating NGIS Id and Referral Id and web element text
  Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
    | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare-Disease | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
Then the user should verify the referral banner present at the top
When the user navigates to the "<PatientDetails>" stage
Then the user is navigated to a page with title Check your patient's details
And the user clicks the Save and Continue button
And the user should verify the referral banner present at the top
Then the "<PatientDetails>" stage is marked as Completed
And the user verifies the Ngis Id and Referral Id from the referral banner
Then user copies text from NgisId and verifies it with actual content

Examples:
| PatientDetails  |
| Patient details |


