@MIPORTAL_DATA
@MIPORTAL_SIT

Feature: MIPORTAL SIT - File Submission 1

  @NTS-4969
  Scenario Outline: NTS-4969:E2EUI-2699,2702: ColumnHeader "<columnHeader>" displays only filtered "<fieldValue>" results in report table
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects Submitted By as the search column dropdown
    And the user selects is as the search operator dropdown
    And the user selects GLHName as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user should see the below columns populated in search result table based on the selected GLHName
      | ColumnHeader      |
      | Submitted By Code |
      | Submitted By      |
    And the selected search option is reset after test
    ##Note: Ensure that the test data file MIPortalTestData_SIT.csv present in the testdata folder
    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-4968
  Scenario Outline:NTS-4968:E2EUI-2727:Created Date equals filter displays only filtered date results in report table under File Submissions
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects Created as the search column dropdown
    And the user selects equals as the search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the column "<column>" in the search result table displayed the only filtered date "<noOfDays>" days before today

    Examples:
      | mi_stage         | column  | noOfDays |
      | File Submissions | Created | 1        |
#
#  @NTS-5059
#    #@E2EUI-2779
#  Scenario Outline:NTS-5059:E2EUI-2779: Fix CSV Download fails to truncate cells
#    When the user should be able to see sample processing menu is displayed
#    Then the user should be able to see the below header sections in Sample Processing
#      | HeaderSection     |
#      | File Submissions  |
#      | Order Tracking    |
#      | GLH Samples       |
#      | Plater Samples    |
#      | Picklists         |
#      | Sequencer Samples |
#      | New Referrals     |
#      | Search LSIDs      |
#    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the user sees a search box container section for "<mi_stage>" page
#    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
#    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
#    And the user enters a date "<date>" in the file-submission date field
#    And the user clicks on Add criteria button
#    Then file submission search criteria badge information is displayed below drop-down buttons
#    When the user click on the Search button
#    And search results are displayed in table format with display options button
#    When the user clicks on the Download CSV button to download the CSV file as "fileSubmission".csv
#    ### Incomplete : file content matching in UI and downloaded CSV file are yet to be done.
#
#    Examples:
#      | mi_stage         | value   | operator | date       |
#      | File Submissions | Created | equals   | 02-04-2020 |
#

    #  @NTS_todo
#  Scenario Outline: verify the CSV filename submitted in CSV downstream is shown fileSubmission
#    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
#    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
#    And the user enters a date "<date>" in the file-submission date field
#    And the user clicks on Add criteria button
#    Then file submission search criteria badge information is displayed below drop-down buttons
#    When the user click on the Search button
#    Then search results are displayed in table format with display options button
##    Then search results are displayed for the file-submission search
##    And the user is able to see the field values - Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"
#    And the user is able to see one or more of the Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"
#
#    Examples:
#      | mi_stage         | column  | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
#      | File Submissions | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |


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
