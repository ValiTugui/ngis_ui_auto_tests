@MIPORTAL

Feature: MI Portal - This is mi-portal Order_Tracking

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-4865
  Scenario Outline: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user sees the values in the search value "order_tracking-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage       | value | operator  |
      | Order Tracking | GLH   | is        |
      | Order Tracking | GLH   | is one of |






