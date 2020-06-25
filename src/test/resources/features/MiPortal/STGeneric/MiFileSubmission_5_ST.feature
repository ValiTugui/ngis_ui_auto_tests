@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST - File Submission 5

  @NTS-3390
    #@E2EUI-1283
  Scenario Outline: NTS-3390:Verify the expand compact button on a search row result and click to expand
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

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