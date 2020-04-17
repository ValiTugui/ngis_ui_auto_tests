@MIPORTAL

Feature:  MIPORTAL:  fileSubmission_3

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline: NTS-5190:E2EUI-2770:When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
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
      | mi_stage         | column       | operator  |
      | File Submissions | Submitted By | is        |
      | File Submissions | Submitted By | is one of |

  @NTS-4938
  Scenario Outline:NTS-4938:E2EUI-2703: Verify the drop-down values "GLH and "Ordering Entity" are not displayed in FileSubmission
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the values are not displayed in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-4938
  Scenario Outline: NTS-4938:E2EUI-2703: Remove glh_laboratory_id and ordering_entity_id from filesubmissions endpoint
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    Then search results are displayed in table format with display options button
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders              |
      | gel1001-glhlabID           |
      | gel1001-ordering entity ID |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-4968
  Scenario Outline:NTS-4968:E2EUI-2727:Created Date equals filter displays only filtered date results in report table under File Submissions
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    Then search results are displayed in table format with display options button
    And the column(s) field "Created" in the search result table displayed the only filtered "<date>"

    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |

  @NTS-4969
  Scenario Outline: NTS-4969:E2EUI-2699,2702: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    Then search results are displayed in table format with display options button
    And the column(s) field "<columnHeader>" in the search result table displayed the only filtered "<fieldValue>"
    And the selected search option is reset after test

    Examples:
      | mi_stage         | column       | operator | value        | columnHeader      | fieldValue   |
      | File Submissions | Submitted By | is       | London North | Submitted By Code | GLH002       |
      | File Submissions | Submitted By | is       | London North | Submitted By      | London North |

  @NTS-4987
  Scenario Outline: NTS-4987:E2EUI-2693:Verify "Show All" and "Hide All" buttons under Column Ordering in modal contents
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
#    Then search results are displayed for the file-submission search
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
  Scenario Outline:NTS-5020:E2EUI-2409: File Submissions - field_errors and field_warnings in search result table
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    Then search results are displayed in table format with display options button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders |
      | Error Msgs    |
      | Warning Msgs  |

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
  Scenario Outline: NTS-5054:E2EUI-2471:Sort file submissions results by created date descending
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    Then search results are displayed in table format with display options button
    And the user see dates value in "Created" column of file-submission search result in descending order


    Examples:
      | mi_stage         | column  | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |
