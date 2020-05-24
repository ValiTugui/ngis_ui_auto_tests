@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST_MI

Feature: MIPORTAL ST - File Submission 1

  @NTS-3390
    #@E2EUI-1283,2513
  Scenario Outline: NTS-3390:E2EUI-1283,2513:verify the defaults elements on File Submission search page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons
    Then the user sees the below values in the file-submission search column drop-down menu
      | Created      |
      | Status       |
      | Submitted By |

    When the user selects Created as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | equals       |
      | before or on |
      | on or after  |

    When the user selects Status as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |

    When the user selects Submitted By as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |

    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |

    When the user selects Status as the search column dropdown
    And the user selects is one of as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |

    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |


  @NTS-3390
    #@E2EUI-1283
  Scenario: E2EUI-1283: User is able to reset selected search criteria badge
    When the user selects Created as the search column dropdown
    And the user selects equals as the search operator dropdown
    And the user enters a date today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the reset button
    Then the search criteria badge disappears

    When the user enters a date future_date in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message No results found for these search terms. below the search container
    And the selected search option is reset after test

  @NTS-3390
    #@E2EUI-1283
  Scenario: NTS-3390:Verify the main elements displayed in search result section
    When the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And search results are displayed in table format with display options button

  @NTS-3390
    #@E2EUI-1283
  Scenario: NTS-3390:Verify the elements in the Column Ordering section of File-Submission Display Options
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user closes the modal content by clicking on the reset-button

  @NTS-3390
    #@E2EUI-1283
  Scenario: NTS-3390:E2EUI-1283: Verify the default header values of 'Show' and 'Hide' in the Column Ordering section of File-Submission Display Options
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

  @NTS-3390
    #@E2EUI-1283 @E2EUI-2513
  Scenario Outline: E2EUI-1283,2513:File Submissions: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    And the user selects <value> as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | value   | columnHeader | fieldValue |
      | Valid   | Status       | valid      |
      | Invalid | Status       | invalid    |