@MIPORTAL

Feature: MIPORTAL: Glh Samples (E2EUI-2336,2486,1162,2579,2771)

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

 @NTS-4865
  Scenario Outline:NTS-4865: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user sees the values in the search value "glh_samples-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage    | value | operator  |
      | GLH Samples | GLH   | is        |
      | GLH Samples | GLH   | is one of |

  @NTS-5036
   # @E2EUI-2336
  Scenario Outline: NTS-5036:E2EUI-2336: New data columns in the result table of GLH Samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value>" from the "glh_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage    | filter | operator | value        | ColumnHeader                                                                             |
      | GLH Samples | GLH    | is       | London North | gel1001 Referral ID,gel1001 Dispatched Sample Type,gel1001 GLH Sample Consignment Number |
 ###One filter remaining to include in the example,as from E2EUI-2336 The filter "gel1001_dispatched_sample_id" is not present in the search result table.

  @NTS-5036
   # @E2EUI-2486
  Scenario Outline:NTS-5036:E2EUI-2486: The GLH Samples section is having a new Filename filter
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the user sees the values in the search value "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | Batch Import Filename                 |
      | Dispatched Sample Type                |
      | gel1004 GLH Sample Consignment Number |
    And the selected search option is reset after test
    When the user selects a value "<filter>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user fills in a "<value>" in the "glh_samples-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage    | filter                | operator | value |
      | GLH Samples | Batch Import Filename | matches  | gel   |

  @NTS-5049
    #@E2EUI-1162
  Scenario Outline: NTS-5049:E2EUI-1162: MI Dashboard | Filter
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
    And the user selects a value "<filter1>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "glh_samples -search-value" value drop-down
    And the user clicks on Add criteria button
    And the user click on the reset button
    And the user selects a value "<filter2>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator2>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value2>" from the "glh_samples -search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the table column "<ColumnHeader>" is displayed with data
    Then the selected search option is reset after test

    Examples:
      | mi_stage    | filter2 | operator2 | value2       | filter1         | operator1 | value1                      | ColumnHeader                                                                             |
      | GLH Samples | GLH     | is        | London North | Ordering Entity | is one of | Bolton NHS Foundation Trust | gel1001 Referral ID,gel1001 Dispatched Sample Type,gel1001 GLH Sample Consignment Number |

  @NTS-5033
    #@E2EUI-2579
  Scenario Outline:NTS-5033:E2EUI-2579: MIS - Remove "is one of" option for Gel 1004 GLH Sample Consignment Number Filter in GLH Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user enters a sample consignment Number "<gel1004_GLHSample_Consignment_Number>" in the search box field
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then search results are displayed for the file-submission search
    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv in GLH samples page
    And the selected search option is reset after test

    Examples:
      | mi_stage    | value                                 | operator | gel1004_GLHSample_Consignment_Number |
      | GLH Samples | gel1004 GLH Sample Consignment Number | is       | sow-2020-04-04-1                     |

  @NTS-5178
    #@E2EUI-2771
  Scenario Outline:NTS-5178:E2EUI-2771: In GLH Samples section the Batch Import Filename now correctly filters results
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Patient NGIS ID                  |
      | Batch Import Filename            |
    And the selected search option is reset after test
    And the user selects a value "<filter_value>" from the "glh_samples-search-col" column drop-down
    And the user sees the values in the search value "glh_samples-search-operator" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | matches                          |
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user fills in a "<value>" in the "glh_samples-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    Then the table column "<ColumnHeader>" is displayed with data

    Examples:
      | mi_stage    | filter_value          | operator | value | ColumnHeader     |
      | GLH Samples | Batch Import Filename | matches  | gel   | gel1001 Filename |
