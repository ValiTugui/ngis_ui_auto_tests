@MIPORTAL
@MIPORTAL_ST
@file_submission
Feature: MIPORTAL ST - File Submission 1

  @NTS-3390 @MI-LOGOUT
    #@E2EUI-1283
  Scenario Outline: E2EUI-1283: User is able to reset selected search criteria badge and verify the main elements displayed in search result section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
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
    #Merged the scenario to verify the main elements displayed in search result section
    When the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And search results are displayed in table format with display options button

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-5048 @MI-LOGOUT
    #@E2EUI-1634 ##To check as this involved drag and drop functionality
  Scenario Outline: NTS-5048 :As a user, I want to see the columns which are hidden by default in the File submissions section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders     |
      | Filename          |
      | Status            |
      | Errors            |
      | Warnings          |
      | Created           |
      | Submitted By Code |
      | ID                |

    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | ID                       |
      | Submitted By Code        |
      | Errors                   |
      | Warnings                 |
      | Filename                 |
      | Created                  |
      | Status                   |
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | File Type                |
      | Path                     |
    When the user clicks on the button "Show all"
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | ID                       |
      | Submitted By Code        |
      | Errors                   |
      | Warnings                 |
      | Filename                 |
      | Created                  |
      | Status                   |
      | File Type                |
      | Path                     |
    And the user sees the displayed fields-columns under "Hide" section as empty
    And the user clicks on the button "Hide all"
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | ID                       |
      | Submitted By Code        |
      | Errors                   |
      | Warnings                 |
      | Filename                 |
      | Created                  |
      | Status                   |
      | File Type                |
      | Path                     |
    And the user sees the displayed fields-columns under "Show" section as empty
    When the Save and Close button under Show All and Hide All button becomes disabled
    And the user closes the modal content by clicking on the reset-button
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    When the user drag the column header "ID" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | ID                       |
    When the user drag the column header "Path" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | Path                     |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders |
      | ID            |
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

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

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-5016
    #@E2EUI-2224
  Scenario Outline: NTS-5016:E2EUI-2224,2470: No warnings are displayed for files with status valid_with_warning
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the selected search option is reset after test
    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |
    And the user selects Valid with Warnings as the search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the table column Warnings is displayed with data non-empty-data
    Then the table column Status is displayed with data valid_with_warnings
    And  the selected search option is reset after test
    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    And the user selects Valid as the search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column Status is displayed with data valid
    And  the selected search option is reset after test
    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    And the user selects Invalid as the search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column Status is displayed with data invalid
    And  the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |
