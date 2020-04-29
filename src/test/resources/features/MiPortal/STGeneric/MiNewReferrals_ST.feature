@MIPORTAL
@SYSTEM_TEST

Feature:  MIPORTAL ST -  New Referrals ST

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770:When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <value> as the new referrals search column dropdown
    And the user selects <operator> as the new referrals search operator dropdown
    #  defect https://jira.extge.co.uk/browse/NMIS-782
    And the user sees the below values in the new referrals search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage      | value | operator  |
      | New Referrals | GLH   | is        |
      | New Referrals | GLH   | is one of |

  @NTS-5066
    # @E2EUI-2408
  Scenario Outline: NTS-5066:E2EUI-2408:For the New referrals Report filter the operator 'is one of' is not present
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Has Gel1001 as the new referrals search column dropdown
    Then the user sees the below values in the new referrals operator drop-down menu
      | is        |
    Then the selected search option is reset after test
    Examples:
      | mi_stage      |
      | New Referrals |
