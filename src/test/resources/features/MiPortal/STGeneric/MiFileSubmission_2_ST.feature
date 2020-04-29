@MIPORTAL
@SYSTEM_TEST

Feature: MIPORTAL ST - File Submission 2

  @NTS-5190
  Scenario Outline: NTS-5190:E2EUI-2770:When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <column> as the search column dropdown
    And the user selects <operator> as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
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
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the user should not sees the below values in the file-submission search column drop-down menu
      | GLH                               |
      | Ordering Entity                   |

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-4938
  Scenario Outline: NTS-4938:E2EUI-2703: Remove glh_laboratory_id and ordering_entity_id from filesubmissions endpoint
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
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders              |
      | gel1001-glhlabID           |
      | gel1001-ordering entity ID |
    And the selected search option is reset after test

    Examples:
      | mi_stage         | date      |
      | File Submissions | past_date |


  @NTS-4987
  Scenario Outline: NTS-4987:E2EUI-2693:Verify "Show All" and "Hide All" buttons under Column Ordering in modal contents
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
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the Re-Ordering Section of DisplayOptions sections displayed correctly
    When the user clicks on the button "Show all"
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
    And the user sees the displayed fields-columns under "Hide" section as empty
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
    And the user sees the displayed fields-columns under "Show" section as empty
    When the Save and Close button under Show All and Hide All button becomes disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 2        |

  @NTS-5020
  Scenario Outline:NTS-5020:E2EUI-2409: File Submissions - field_errors and field_warnings in search result table
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
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders |
      | Error Msgs    |
      | Warning Msgs  |

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 2        |

  @NTS-5048
    #@E2EUI-1634 ##To check as this involved drag and drop functionality
  Scenario Outline: NTS-5048 :As a user, I want to see the columns which are hidden by default in the File submissions section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the columns fields are  displayed in the list of columns headers of the search result table
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
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-5054
  Scenario Outline: NTS-5054:E2EUI-2471:Sort file submissions results by created date descending
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
    And the user see dates value in "Created" column of file-submission search result in descending order

    Examples:
      | mi_stage         | noOfDays |
      | File Submissions | 5        |

  @NTS-5027
    #@E2EUI-1156
  Scenario Outline:NTS-5027:E2EUI-1156: As a user I want to arrange the column order the results displayed, so that I can analyse the data accordingly
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
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
      | mi_stage         | noOfDays | field-column                   |
      | File Submissions | 5        | filename,field_warnings,status |