@MIPORTAL
@MIPORTAL_ST_FileSubmission
@SYSTEM_TEST

Feature: MIPORTAL ST - File Submission 1

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:E2EUI-1283:verify the defaults elements on File Submission search page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline:NTS-3390:E2EUI-1283,2513: verify the drop-down values of file-submission search column
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the user sees the below values in the file-submission search column drop-down menu
      | Created      |
      | Status       |
      | Submitted By |
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline:E2EUI-1283: When Search-column is "Created" - verify the drop-down values of file-submission search operator
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | equals       |
      | before or on |
      | on or after  |
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline:E2EUI-1283,E2EUI-2513: When Search-column is "<column>":verify the drop-down values of file-submission search operator
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <column> as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column       |
      | File Submissions | Status       |


  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline:E2EUI-1283,E2EUI-2513: When Search-column is "<column>":verify the drop-down values of file-submission search operator
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <column> as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column       |
      | File Submissions | Submitted By |


  @NTS-3390
    #@E2EUI-1283
  Scenario Outline:E2EUI-1283: When Search-column is "Status" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <column> as the search column dropdown
    And the user selects <operator> as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator  |
      | File Submissions | Status | is        |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline:E2EUI-1283: When Search-column is "Status" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <column> as the search column dropdown
    And the user selects <operator> as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator  |
      | File Submissions | Status | is one of |


  @NTS-3390
    #@E2EUI-1283
  Scenario Outline:E2EUI-1283: User is able to reset selected search criteria badge
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects equals as the search operator dropdown
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the reset button
    Then the search criteria badge disappears

    Examples:
      | mi_stage         | date  |
      | File Submissions | today |

  @NTS-33901
    #@E2EUI-1283
  Scenario Outline:NTS-3390:E2EUI-1283: When no result is found
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects equals as the search operator dropdown
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message "<noResultFound>" below the search container
    And the selected search option is reset after test

    Examples:
      | mi_stage         | date        | noResultFound                            |
      | File Submissions | future_date | No results found for these search terms. |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:Verify the main elements displayed in search result section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:Verify user is able to Download the CSV fileSubmission result
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv
    And the selected search option is reset after test

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:Verify the elements in the Column Ordering section of File-Submission Display Options
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:E2EUI-1283: Verify the default header values of 'Show' and 'Hide' in the Column Ordering section of File-Submission Display Options
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | ID                       |
      | Submitted By Code        |
      | Submitted By             |
      | Field Errors             |
      | Field Warnings           |
      | Filename                 |
      | Created                  |
      | Status                   |
      | Error Msgs               |
      | Warning Msgs             |
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | File Type                |
      | Path                     |
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline: E2EUI-1283,2513:File Submissions: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects <column> as the search column dropdown
    And the user selects <operator> as the search operator dropdown
    And the user selects <value> as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator | value   | columnHeader | fieldValue |
      | File Submissions | Status | is       | Valid   | Status       | valid      |

  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline: E2EUI-1283,2513:File Submissions: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects <column> as the search column dropdown
    And the user selects <operator> as the search operator dropdown
    And the user selects <value> as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator | value   | columnHeader | fieldValue |
      | File Submissions | Status | is       | Invalid | Status       | invalid    |