@miportal

Feature: This is mi-portal fileSubmission

  @miportal1 @mi_smokeTest
  Scenario Outline: verify the CSV filename submitted in CSV downstream is shown fileSubmission
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user selects a search operator "<operator>" from the file-submission search operator drop-down
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

  @miportal2
  Scenario Outline: verify the defaults elements on File Submission search page
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons

    Examples:
      | mi_stage         |
      | File Submissions |


  @miportal3
  Scenario Outline: verify the drop-down values of file-submission search column
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | Created                           |
      | Status                            |
      | Submitted By                      |

    Examples:
      | mi_stage         |
      | File Submissions |


  @miportal4
  Scenario Outline: When Search-column is "Created" - verify the drop-down values of file-submission search operator
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | equals                            |
      | before or on                      |
      | on or after                       |

    Examples:
      | mi_stage         | value   |
      | File Submissions | Created |


  @miportal5
  Scenario Outline: When Search-column is "<value>":verify the drop-down values of file-submission search operator
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    #    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user sees the values in the search operator "file_submissions-search-operator" drop-down menu
      | fileSubmissionsSearchOperatorHeader |
      | is                                  |
      | is one of                           |

    Examples:
      | mi_stage         | value        |
      | File Submissions | Status       |
      | File Submissions | Submitted By |


  @miportal6
  Scenario Outline: When Search-column is "Status" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user selects a search operator "<operator>" from the file-submission search operator drop-down
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | Duplicate                        |
      | In Progress                      |
      | Invalid                          |
      | Valid                            |
      | Valid with Warnings              |

    Examples:
      | mi_stage         | value  | operator  |
      | File Submissions | Status | is        |
#      | File Submissions | Status | is one of |


  @miportal7
  Scenario Outline: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user selects a search operator "<operator>" from the file-submission search operator drop-down
    And the user sees the values in the search value "file_submissions-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader  |
      | East Midlands and East of England |
      | London North                      |
      | London South                      |
      | North West                        |
      | South West                        |
      | Wessex and West Midlands          |
      | Yorkshire and North East          |
      | ILMN                              |
      | UKB                               |

    Examples:
      | mi_stage         | value        | operator |
      | File Submissions | Submitted By | is       |
#      | File Submissions | Status | is one of |


  @miportal8
  Scenario Outline: User is able to reset selected search criteria badge
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user selects a search operator "<operator>" from the file-submission search operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the reset button
    Then the search criteria badge disappears

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 09-03-2020 |