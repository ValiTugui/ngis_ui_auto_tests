@MIPORTAL

Feature: This is mi-portal New Referrals

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

   @NTS-4865
  Scenario Outline: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user sees the values in the search value "new_referrals-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
#  defect https://jira.extge.co.uk/browse/NMIS-782
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage      | value | operator  |
      | New Referrals | GLH   | is        |
      | New Referrals | GLH   | is one of |

  @NTS-5066
    # @E2EUI-2408
  Scenario Outline: NTS-5017:For the New referrals Report filter the operator 'is one of' is not present
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<search_report>" from the "new_referrals-search-col" column drop-down
    Then the user sees the values in the search operator "new_referrals-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
    Then the selected search option is reset after test
    Examples:
      | mi_stage      | search_report |
      | New Referrals | Has Gel1001   |






