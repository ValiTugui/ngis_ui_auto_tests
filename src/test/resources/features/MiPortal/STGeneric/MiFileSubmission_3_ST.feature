@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST - File Submission 3

  @NTS-5018
    # @E2EUI-2503
  Scenario Outline: NTS-5018:E2EUI-2503:Validate 'is one of' filter working and standardized date search to a single 'Created' filter
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Submitted By as the search column dropdown
    And the user selects is one of as the search operator dropdown
    And the user selects London South as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And  the selected search option is reset after test
    ### Validating filter "Created"
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-5016
    #@E2EUI-2224
  Scenario: NTS-5016:E2EUI-2224,2470: No warnings are displayed for files with status valid_with_warning
    When the selected search option is reset after test
    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |
    And the user selects Valid with Warnings as the search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column Status is displayed with data valid_with_warnings
    And the table column Field Warnings is displayed with data non-empty-data

  @NTS-5031
    #@E2EUI-2513
  Scenario: NTS-5031:E2EUI-2513 Search by "Status" in File Submissions
    When the selected search option is reset after test
    And the user sees the below values in the file-submission search column drop-down menu
      | Created                           |
      | Status                            |
      | Submitted By                      |
    When the user selects Status as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is                                  |
      | is one of                           |
    When the user selects is as the search operator dropdown
    And the user selects Duplicate as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the table column Status is displayed with data duplicate

  @NTS-5177
    #@E2EUI-2578
  Scenario: NTS-5177:@E2EUI-2578: MIS - Add "Status" search filter to File Submissions
    When the selected search option is reset after test
    When the user selects Created as the search column dropdown
    And the user selects on or after as the search operator dropdown
    And the user enters a date future_date in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message No results found for these search terms. below the search container
    And the selected search option is reset after test
    And the user selects Status as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is                                  |
      | is one of                           |
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |
    And the user selects Valid as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the selected search option is reset after test