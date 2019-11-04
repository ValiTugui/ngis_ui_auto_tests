@responsibleClinicianOrg
Feature: Responsible Clinician

  @E2EUI-2133 @NTS-3166 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3166 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>"
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | browser_exit_option | warning_text                           |
      | Responsible clinician | refresh             | Changes that you made may not be saved |

  @E2EUI-2133 @NTS-3149 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3149 - Responsible Clinician Page - verify Warning Messages of unsaved changes - Moving to another To do list
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the user fills in all the clinician form fields
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message "<warning_text>" on the page

    Examples:
      | stage                 | new_stage      | warning_text                                                |
      | Responsible clinician | Patient choice | This section contains unsaved information. Discard changes? |

  @E2EUI-1216 @NTS-3167 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3167 - Responsible Clinician Page - Email and Phone Number field validation
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    When the user fills in "<invalid_email>" in clinician form fields
    And the user fills in invalid phone number value by providing "<invalid_number_of_digits>" digits in clinician form fields
    Then the user sees an "<error_info>" message on the page
    And The user should be restricted to enter more than "<total_number_of_digits_acceptable>" digits in the Phone number field.
    Examples:
      | stage                 | invalid_email | invalid_number_of_digits | total_number_of_digits_acceptable | error_info                         |
      | Responsible clinician | ab@ad.com     | 19                       | 15                                | Please enter a valid email address |

