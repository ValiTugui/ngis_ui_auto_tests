@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST -  Glh Samples

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the user sees the below values in the glh samples search column drop-down menu
      | GLH                           |
      | Ordering Entity               |
      | Referral ID                   |
      | Patient NGIS ID               |
      | Batch Import Filename         |
      | Dispatched Sample Type        |
      | GLH Sample Consignment Number |

    When the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    Then the user sees the below values in the glh samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    When the user selects is one of as the glh search operator dropdown
    Then the user sees the below values in the glh samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |
    And the selected search option is reset after test

    Examples:
      | mi_stage    |
      | GLH Samples |

  @NTS-5036
   # @E2EUI-2486
  Scenario Outline: NTS-5036:E2EUI-2486: The GLH Samples section is having a new Filename filter
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Batch Import Filename as the glh search column dropdown
    And the user selects matches as the glh search operator dropdown
    And the user enters gel in the glh search value box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage    |
      | GLH Samples |
  @NTS-5178
    #@E2EUI-2771
  Scenario Outline: NTS-5178:E2EUI-2771: In GLH Samples section the Batch Import Filename now correctly filters results
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Batch Import Filename as the glh search column dropdown
    And the user selects matches as the glh search operator dropdown
    And the user enters gel in the glh search value box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the glh search result table column GEL1001 Filename is displayed with data gel

    Examples:
      | mi_stage    |
      | GLH Samples |