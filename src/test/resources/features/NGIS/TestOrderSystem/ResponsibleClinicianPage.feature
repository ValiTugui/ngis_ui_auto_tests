@responsibleClinicianOrg
Feature: Responsible Clinician

  @E2EUI-2133 @NTS-3166 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3166 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>"
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message on the page

    Examples:
      | stage                 | browser_exit_option |
      | Responsible clinician | refresh             |

  @E2EUI-2133 @NTS-3149 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3149 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Moving to another To do list
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message on the page

    Examples:
      | stage                 | new_stage      |
      | Responsible clinician | Patient choice |
