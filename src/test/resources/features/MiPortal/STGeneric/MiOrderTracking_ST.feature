@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST - Order_Tracking

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    And the user selects is one of as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage       |
      | Order Tracking |


  @NTS-5052
    #@E2EUI-2398
  Scenario Outline: NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Test Type as the order tracking search column dropdown
    And the user selects is one of as the order tracking search operator dropdown
    And the user selects Cerebral malformations WGS,Hereditary ataxia - adult onset WGS as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Order Tracking |

  @NTS-5052 @MI-LOGOUT
    #@E2EUI-2398
  Scenario Outline: NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
      When the user selects Test Type as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects Cystic renal disease WGS as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Order Tracking |