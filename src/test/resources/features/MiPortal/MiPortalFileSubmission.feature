@MIPORTAL
@SYSTEM_TEST

Feature: This is mi-portal fileSubmission

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS_todo
  Scenario Outline: verify the CSV filename submitted in CSV downstream is shown fileSubmission
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
#    And the user is able to see the field values - Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"
    And the user is able to see one or more of the Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"

    Examples:
      | mi_stage         | value   | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
      | File Submissions | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |

 @NTS-3390
  Scenario Outline: verify the defaults elements on File Submission search page
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |


  @NTS_todo
  Scenario Outline: verify the drop-down values of file-submission search column
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
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | equals                            |
      | before or on                      |
      | on or after                       |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   |
      | File Submissions | Created |


  @NTS_todo
  Scenario Outline: When Search-column is "<value>":verify the drop-down values of file-submission search operator
    When the user navigates to the mi-portal "<mi_stage>" stage
    #    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
      | is one of                           |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value        |
      | File Submissions | Status       |
      | File Submissions | Submitted By |


  @NTS_todo
  Scenario Outline: When Search-column is "Status" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
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
      | mi_stage         | value  | operator  |
      | File Submissions | Status | is        |
      | File Submissions | Status | is one of |


  @NTS-4865
  Scenario Outline: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
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
      | mi_stage         | value        | operator |
      | File Submissions | Submitted By | is        |
      | File Submissions | Submitted By | is one of |


  @NTS_todo
  Scenario Outline: User is able to reset selected search criteria badge
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the reset button
    Then the search criteria badge disappears

    Examples:
      | mi_stage         | value   | operator | date  |
      | File Submissions | Created | equals   | today |


  @NTS-3390
  Scenario Outline: When no result is found
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the message "<noResultFound>" below the search container
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date        | noResultFound                            |
      | File Submissions | Created | equals   | future_date | No results found for these search terms. |


  @NTS-3390
  Scenario Outline: Verify the main elements displayed in search result section
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: Verify user is able to Download the CSV fileSubmission result
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


  @NTS-3390
  Scenario Outline: Verify the elements in the Column Ordering section of File-Submission Display Options
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
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
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |


   @NTS-4938
  Scenario Outline: verify the drop-down values "GLH and "Ordering Entity" are not displayed in FileSubmission
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