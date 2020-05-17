@MIPORTAL
@MIPORTAL_ST_OrderTracking
@SYSTEM_TEST

Feature: MIPORTAL ST - Order_Tracking

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <value> as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage       | value | operator  |
      | Order Tracking | GLH   | is        |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <value> as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage       | value | operator  |
      | Order Tracking | GLH   | is one of |

  @NTS-5052
    #@E2EUI-2398
  Scenario Outline:NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Test Type as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects <value> as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       | operator  | value                                                          |
      | Order Tracking | is one of | Cerebral malformations WGS,Hereditary ataxia - adult onset WGS |

  @NTS-5052
    #@E2EUI-2398
  Scenario Outline:NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Test Type as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects <value> as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       | operator  | value                                                          |
      | Order Tracking | is        | Cystic renal disease WGS                                       |