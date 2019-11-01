@responsibleClinicianOrg
Feature: Responsible Clinician

  @E2EUI-956 @NTS-3175 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User selects 'Save and continue' button without providing inputs in mandatory fields
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    When the user fills in all clinician form fields except the mandatory fields Last name , Department name and address
    And the user see the "<hyperlinkText>" displayed to add Additional clinician details
    And the user clicks the Save and Continue button
    Then The Last name field should display an error message "<error_info>"
    And The mandatory field Last name should be highlighted with a "<red_color_hex_code>" red mark
    And the "<stage>" stage is marked as Mandatory To Do
    Examples:
      | stage                 | hyperlinkText | error_info            |red_color_hex_code |
      | Responsible clinician | Add another   | Last name is required | #dd2509           |

@E2EUI-956 @NTS-3175 @LOGOUT @v_1 @P0 @COMP5_TO_ClinicalDetails
  Scenario Outline: NTS-3175 - Responsible Clinician Page - User select 'Save and continue' button without providing nullable Department address
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Chondrosarcoma Conventional Central | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    When the user fills in all clinician form fields except Department name and address
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    Examples:
      | stage                 |  new_stage   |
      | Responsible clinician |   Tumours    |

