@responsibleClinicianOrg
Feature: Responsible Clinician

  @E2EUI-1216 @NTS-3167 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3167 - Responsible Clinician Page - Email and Phone Number field validation
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    When the user fills in "<invalid_email>" in clinician form fields
    And the user fills in invalid phone number value exceeding fifteen digits in clinician form fields
    Then the user sees an "<error_info>" message on the page
    And The user should be restricted to enter more than fifteen digits in the Phone number field.
    Examples:
      | stage                 | invalid_email | error_info                         |
      | Responsible clinician | ab@ad.com     | Please enter a valid email address |

