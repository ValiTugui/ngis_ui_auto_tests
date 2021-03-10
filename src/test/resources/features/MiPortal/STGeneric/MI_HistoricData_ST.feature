@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL ST - Historic Data

  @NTS-5743 @MI-LOGOUT
  Scenario Outline: User is able to verify historic data
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user verifies the "File Submissions" data from MI table against "<FileName>"
    And the user verifies the "Order Tracking" data from MI table against "<FileName>"
    And the user verifies the "GLH Samples" data from MI table against "<FileName>"
    And the user verifies the "Plater Samples" data from MI table against "<FileName>"
    And the user verifies the "Picklists" data from MI table against "<FileName>"
    And the user verifies the "Sequencer Samples" data from MI table against "<FileName>"
    And the user verifies the "New Referrals" data from MI table against "<FileName>"

    Examples:
      | FileName                        |
      | MI_HistoricData_Validation.xlsx |