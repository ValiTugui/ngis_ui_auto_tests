@MIPORTAL

Feature: MI Portal - File Submission 2

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |


  @NTS-5048
    #@E2EUI-1634
  Scenario Outline: NTS-5048 :As a user, I want to see the columns which are hidden by default in the File submissions section
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | id                       |
      | submitted_by_code        |
      | submitted_by             |
      | field_errors             |
      | field_warnings           |
      | filename                 |
      | created                  |
      | status                   |
      | error_msgs               |
      | warning_msgs             |
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | file_type                |
      | path                     |
    When the user drag the column header "warning_msgs" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | warning_msgs             |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders |
      | warning_msgs  |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | filter  | operator     | date       | ColumnHeader                                                                                                  |
      | File Submissions | Created | before or on | 01-04-2020 | ID,Submitted By Code,Submitted By,Field Errors,Field Warnings,Filename,Created,Status,Error Msgs,Warning Msgs |

  @NTS-5018
    # @E2EUI-2503
  Scenario Outline: NTS-5018:Validate 'is one of' filter working and standardized date search to a single 'Created' filter
    When the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And  the selected search option is reset after test
    ### Validating filter "Created"
    When the user selects a value "<column2>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator2>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column       | operator  | value                                | column2 | operator2    | date       |
      | File Submissions | Submitted By | is one of | London North,London South,North West | Created | before or on | 26-03-2020 |

  @NTS-5016
    #@E2EUI-2224
  Scenario Outline: NTS-5016: No warnings are displayed for files with status valid_with_warning
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "file_submissions-search-value" value drop-down
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |
    Then the user should be able to see the selected "<dropdownValue>" value in file submissions page
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the table column "<ColumnHeader>" is displayed with data
    Then the user should be able to see the explanation in the "<ColumnHeader2>" column's cell for the value "<CellValue>" in the "<ColumnHeader>" column
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator | dropdownValue       | ColumnHeader | ColumnHeader2  | CellValue           |
      | File Submissions | Status | is       | Valid with Warnings | Status       | Field Warnings | valid_with_warnings |

  @NTS-5050
    #@E2EUI-2470
  Scenario Outline: NTS-5050: Workaround - Suppress ambiguous "valid with warnings" statuses and warnings
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "file_submissions-search-value" value drop-down
    Then the user should be able to see the selected "<dropdownValue>" value in file submissions page
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the table column "<ColumnHeader>" is displayed with data
    Then the user should be able to see the explanation in the "<ColumnHeader2>" column's cell for the value "<CellValue>" in the "<ColumnHeader>" column
    Then the user should not be able to see the explanation in the "<ColumnHeader2>" column for the empty cell in the "<ColumnHeader>" column
    And the selected search option is reset after test

    Examples:
      | mi_stage         | section          | column | NoOfSearchField | operator | dropdownValue       | ColumnHeader | ColumnHeader2  | CellValue           |
      | File Submissions | file_submissions | Status | 3               | is       | Valid with Warnings | Status       | Field Warnings | valid_with_warnings |

  @NTS-5060
     #@E2EUI-1851 @Scenario1
  Scenario Outline: NTS-5060:Scenario1- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "new_referrals-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the user should be able to see the non-empty data cell in the "<ColumnName>" column
    And the selected search option is reset after test
#   Referral Last Submitted cell value is empty where Referral Status cell value is Draft

    Examples:
      | mi_stage      | section       | NoOfSearchField | column | operator | dropdownValue                 | ColumnName                             |
      | New Referrals | new_referrals | 3               | GLH    | is       | East Mids and East of England | Referral Creation Date,Referral Status |

  @NTS-5060
    #@E2EUI-1851 @Scenario2
  Scenario Outline: NTS-5060:Scenario2- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the user should be able to see the non-empty data cell in the "<ColumnName>" column
    And the selected search option is reset after test

    Examples:
      | mi_stage       | section        | NoOfSearchField | column | operator | dropdownValue                 | ColumnName                                                       |
      | Order Tracking | order_tracking | 3               | GLH    | is       | East Mids and East of England | gel1001 GLH Sample Dispatch Date,gel1001 Dispatched Sample State |

  @NTS-5027
    #@E2EUI-1156
  Scenario Outline:NTS-5027 : As a user I want to arrange the column order the results displayed, so that I can analyse the data accordingly
    When the user should be able to see sample processing menu is displayed
    Then the user should be able to see the below header sections in Sample Processing
      | HeaderSection     |
      | File Submissions  |
      | Order Tracking    |
      | GLH Samples       |
      | Plater Samples    |
      | Picklists         |
      | Sequencer Samples |
      | New Referrals     |
      | Search LSIDs      |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | id                       |
      | submitted_by_code        |
      | submitted_by             |
      | field_errors             |
      | field_warnings           |
      | filename                 |
      | created                  |
      | status                   |
      | error_msgs               |
      | warning_msgs             |
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | file_type                |
      | path                     |
    When the user adds "<field-column>" column to Hide section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | id                       |
      | submitted_by_code        |
      | submitted_by             |
      | field_errors             |
      | created                  |
      | error_msgs               |
      | warning_msgs             |
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | file_type                |
      | path                     |
      | filename                 |
      | field_warnings           |
      | status                   |
    When the user clicks "Hide all" button on the modal-content page
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user closes the modal content by clicking on the reset-button
    Then the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date       | field-column                   |
      | File Submissions | Created | equals   | 09-03-2020 | filename,field_warnings,status |

  @NTS-5031
    #@E2EUI-2513
  Scenario Outline:NTS-5031:E2EUI-2513 Search by "Status" in File Submissions
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value1>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | Created                           |
      | Status                            |
      | Submitted By                      |
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
      | is one of                           |
    And the user selects a value "<value2>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value1 | operator | value2      | columnHeader | fieldValue  |
      | File Submissions | Status | is       | In Progress | Status       | in_progress |

  @NTS-5177 @E2EUI-2578
  Scenario Outline:NTS-5177: MIS - Add "Status" search filter to File Submissions
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "file_submissions" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message "<noResultFound>" below the search container
    And the selected search option is reset after test
    And the user selects a value "<column1>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "file_submissions-search-operator" operator drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
      | is one of                           |
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date        | noResultFound                            | NoOfSearchField | column1 | operator1 |
      | File Submissions | Created | equals   | future_date | No results found for these search terms. | 3               | Status  | is        |