@MIPORTAL
@SYSTEM_TEST

Feature: This is mi-portal fileSubmission

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS_todo
  Scenario Outline: verify the CSV filename submitted in CSV downstream is shown fileSubmission
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
#    And the user is able to see the field values - Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"
    And the user is able to see one or more of the Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"

    Examples:
      | mi_stage         | column   | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
      | File Submissions | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |

 @NTS-3390
  Scenario Outline: NTS-3390:verify the defaults elements on File Submission search page
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |


  @NTS-3390
  Scenario Outline:NTS-3390: verify the drop-down values of file-submission search column
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | Created                           |
      | Status                            |
      | Submitted By                      |
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |


  @NTS_todo
  Scenario Outline: When Search-column is "Created" - verify the drop-down values of file-submission search operator
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | equals                            |
      | before or on                      |
      | on or after                       |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  |
      | File Submissions | Created |


  @NTS_todo
  Scenario Outline: When Search-column is "<value>":verify the drop-down values of file-submission search operator
    When the user navigates to the mi-portal "<mi_stage>" stage
    #    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
      | is one of                           |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column        |
      | File Submissions | Status       |
      | File Submissions | Submitted By |


  @NTS_todo
  Scenario Outline: When Search-column is "Status" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator  |
      | File Submissions | Status | is        |
      | File Submissions | Status | is one of |


  @NTS-4865
  Scenario Outline: NTS-4865:When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex and West Midlands         |
      | Yorkshire and North East         |
      | ILMN                             |
      | UKB                              |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column        | operator |
      | File Submissions | Submitted By | is        |
      | File Submissions | Submitted By | is one of |


  @NTS_todo
  Scenario Outline: User is able to reset selected search criteria badge
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the reset button
    Then the search criteria badge disappears

    Examples:
      | mi_stage         | column   | operator | date  |
      | File Submissions | Created | equals   | today |


  @NTS-3390
  Scenario Outline:NTS-3390: When no result is found
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message "<noResultFound>" below the search container
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column   | operator | date        | noResultFound                            |
      | File Submissions | Created | equals   | future_date | No results found for these search terms. |


  @NTS-3390
  Scenario Outline: NTS-3390:Verify the main elements displayed in search result section
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390:Verify user is able to Download the CSV fileSubmission result
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390:Verify the elements in the Column Ordering section of File-Submission Display Options
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390:Verify the default header values of 'Show' abd 'Hide' in the Column Ordering section of File-Submission Display Options
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
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
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-4938
  Scenario Outline:NTS-4938:verify the drop-down values "GLH and "Ordering Entity" are not displayed in FileSubmission
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the values are not displayed in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-4968
  Scenario Outline:NTS-4968:Created Date equals filter displays only filtered date results in report table under File Submissions
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the column(s) field "Created" in the search result table displayed the only filtered "<date>"

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-4969
  Scenario Outline: NTS-4969:ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column       | operator | value        | columnHeader      | fieldValue   |
      | File Submissions | Submitted By | is       | London North | Submitted By Code | GLH002       |
      | File Submissions | Submitted By | is       | London North | Submitted By      | London North |


  @NTS_todo
  Scenario Outline: File Submissions: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column | operator | value   | columnHeader | fieldValue |
      | File Submissions | Status | is       | Valid   | Status       | valid      |
      | File Submissions | Status | is       | Invalid | Status       | invalid    |

  @NTS-4938
  Scenario Outline: NTS-4938: Remove glh_laboratory_id and ordering_entity_id from filesubmissions endpoint
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders              |
      | gel1001-glhlabID           |
      | gel1001-ordering entity ID |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390: Pagination drop-down options shown in search result table
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       | paginationValue |
      | File Submissions | Created | equals   | 09-03-2020 | 25              |
      | File Submissions | Created | equals   | 09-03-2020 | 50              |
      | File Submissions | Created | equals   | 09-03-2020 | 100             |
      | File Submissions | Created | equals   | 09-03-2020 | 10              |


  @NTS-4987
  Scenario Outline: NTS-4987:Verify "Show All" and "Hide All" buttons under Column Ordering in modal contents
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    And the user clicks on the button "Show all"
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
      | file_type                |
      | path                     |
    And the user clicks on the button "Hide all"
    And the user sees the displayed fields-columns under "Hide" section
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
      | file_type                |
      | path                     |
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-5020
  Scenario Outline:NTS-5020 File Submissions - field_errors and field_warnings in search result table
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
#    And the columns fields are displayed in the list of columns headers of the search result table - ToDo
      | columnHeaders |
      | Error Msgs    |
      | Warning Msgs  |

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline:NTS-3390: Drag and drop a column header from Show to Hide section (vice-versa)
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "id" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | id                       |
    When the user drag the column header "path" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | path                     |
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline:NTS-3390:Verify the drag and drop columnHeader in search result table
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "id" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | id                       |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders |
      | id            |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-3390
  Scenario Outline:NTS-3390: User select the "Compact grid" check box on the modal content page
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390:User select the "Truncate Columns" check box on the modal content page
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: NTS-3390:Verify the expand compact button on a search row result and click to expand
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-5048
  Scenario Outline: NTS-5048: File Submissions - Hide unnecessary columns in File Submissions
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the columns headers are displayed in the list of columns headers of the search result table
      | columnHeaders     |
      | ID                |
      | Submitted By Code |
      | Submitted By      |
      | Field Errors      |
      | Field Warnings    |
      | Filename          |
      | Created           |
      | Status            |
      | Error Msgs        |
      | Warning Msgs      |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |