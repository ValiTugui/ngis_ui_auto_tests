@MIPORTAL
@MIPORTAL_ST_1

Feature: MIPORTAL ST - File Submission 3

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5018
    # @E2EUI-2503
  Scenario Outline: NTS-5018:E2EUI-2503:Validate 'is one of' filter working and standardized date search to a single 'Created' filter
    When the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user selects Submitted By as the search column dropdown
    And the user selects is one of as the search operator dropdown
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And  the selected search option is reset after test
    ### Validating filter "Created"
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value                                |noOfDays |
      | File Submissions | London North,London South,North West | 2        |

  @NTS-5016
    #@E2EUI-2224
  Scenario Outline: NTS-5016:E2EUI-2224,2470: No warnings are displayed for files with status valid_with_warning
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user selects Status as the search column dropdown
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
    Then the table column Field Warnings is displayed with data non-empty-data
    And the selected search option is reset after test
    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-5031
    #@E2EUI-2513
  Scenario Outline:NTS-5031:E2EUI-2513 Search by "Status" in File Submissions
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the file-submission search column drop-down menu
      | Created                           |
      | Status                            |
      | Submitted By                      |
    When the user selects Status as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is                                  |
      | is one of                           |
    When the user selects is as the search operator dropdown
    And the user selects In Progress as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the table column Status is displayed with data in_progress
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-5177
    #@E2EUI-2578
  Scenario Outline:NTS-5177:@E2EUI-2578: MIS - Add "Status" search filter to File Submissions
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "file_submissions" page
    And the user selects Created as the search column dropdown
    And the user selects on or after as the search operator dropdown
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message "<noResultFound>" below the search container
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
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv
    And the selected search option is reset after test

    Examples:
      | mi_stage         | date        | noResultFound                            | NoOfSearchField |
      | File Submissions | future_date | No results found for these search terms. | 3               |