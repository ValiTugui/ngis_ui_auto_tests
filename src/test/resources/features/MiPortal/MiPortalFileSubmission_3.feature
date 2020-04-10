@MIPORTAL

Feature:  MIPORTAL:  fileSubmission_3

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-3390
  Scenario Outline: NTS-3390: Default selection in pagination entry of 10 is shown
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the user sees the search results pagination entry drop-down with default selection of "10"

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-3390
  Scenario Outline: NTS-3390: Pagination drop-down options shown in search result table
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
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
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders |
      | Error Msgs    |
      | Warning Msgs  |

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-3390
  Scenario Outline:NTS-3390: Drag and drop a column header from Show to Hide section (vice-versa)
    When the user navigates to the mi-portal "<mi_stage>" stage
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

#  @NTS-5048
#  Scenario Outline: NTS-5048: File Submissions - Hide unnecessary columns in File Submissions
#    When the user navigates to the mi-portal "<mi_stage>" stage
##    And the mi-portal "<mi_stage>" stage is selected
#    And the user sees a search box container section for "<mi_stage>" page
#    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
#    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
#    And the user enters a date "<date>" in the file-submission date field
#    And the user clicks on Add criteria button
#    Then file submission search criteria badge information is displayed below drop-down buttons
#    When the user click on the Search button
#    Then search results are displayed for the file-submission search
#    And the columns headers are displayed in the list of columns headers of the search result table
#      | columnHeaders     |
#      | ID                |
#      | Submitted By Code |
#      | Submitted By      |
#      | Field Errors      |
#      | Field Warnings    |
#      | Filename          |
#      | Created           |
#      | Status            |
#      | Error Msgs        |
#      | Warning Msgs      |
#    And the selected search option is reset after test
#
#    Examples:
#      | mi_stage         | column  | operator | date       |
#      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-5054
  Scenario Outline: NTS-5054:Sort file submissions results by created date descending
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the user see dates value in "Created" column of file-submission search result in descending order


    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |
