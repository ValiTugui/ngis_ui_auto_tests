@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL: SIT User Journey for Sequencer Samples and New Referrals(E2EUI-1489,1836)

  @NTS-5189
    #@E2EUI-1489
  Scenario Outline:NTS-5189:E2EUI-1489: MI Dashboard | Sequencer Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    ## Search Dropdown Validation
    Then the user sees the values in the search value "sequencer_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1009 Plate Barcode                 |
      | gel1010 Illumina QC Status            |
      | gel1010 Illumina Sample Concentration |
      | gel1009 Patient ID                    |
      | gel1009 Plate Date of Dispatch        |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    And the user selects a value "<filter_value1>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "sequencer_samples-search-value" value drop-down
    And the user sees the selected "<value1>" in the "sequencer_samples-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    And the user selects a value "<filter_value2>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<value2>" from the "sequencer_samples-search-value" value drop-down
    And the user sees the selected "<value2>" in the "sequencer_samples-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    And the user selects a value "<filter_value3>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "sequencer_samples-search-operator" operator drop-down
    And the user fills in a "<value3>" in the "sequencer_samples-search-value" search box
    Then the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    And the user selects a value "<filter_value4>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "sequencer_samples-search-operator" operator drop-down
    And the user fills in a "<value4>" in the "sequencer_samples-search-value" search box
    Then the selected search option is reset after test
    ###5 gel1009 Plate Barcode
    And the user selects a value "<filter_value5>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "sequencer_samples-search-operator" operator drop-down
    And the user fills in a "<value5>" in the "sequencer_samples-search-value" search box
    Then the selected search option is reset after test
      ###6  gel1004 Disease Area
    And the user selects a value "<filter_value6>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<fixed_operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<value6>" from the "sequencer_samples-search-value" column drop-down
    And the user sees the selected "<value6>" in the "sequencer_samples-search-value" value drop-down
    Then the selected search option is reset after test
    ###7   gel1004 GLH Sample Consignment Number
    And the user selects a value "<filter_value7>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<fixed_operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user fills in a "<value7>" in the "sequencer_samples-search-value" search box
    Then the selected search option is reset after test
    ####8  gel1004 Laboratory ID
    And the user selects a value "<filter_value8>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "sequencer_samples-search-operator" operator drop-down
    And the user fills in a "<value7>" in the "sequencer_samples-search-value" search box
    ####9  gel1005 Sample Received Datetime
    And the user selects a value "<filter_value9>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator9>" from the "sequencer_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    Then the selected search option is reset after test
    ### Value given to All Dropdowns for Table Data
    And the user selects a value "<filter_value1>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "sequencer_samples-search-value" column drop-down
    And the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
      ###The results are displayed
#    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    ### CVS File download
    When the user clicks on the Download CSV button to download the CSV file as "sequencer_samples_filtered".csv
    ### Inside Display Options
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    Then the user sees the buttons - Reset, Save and close under Display Options
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "gel1008__gel1009__ethnicity" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList    |
      | gel1008__gel1009__ethnicity |
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders               |
      | gel1008__gel1009__ethnicity |
    When the user clicks on the Display Options button
    When the user clicks "Hide all" button on the modal-content page
    And the Save and Close button under Show All and Hide All button becomes disabled
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    ## Compact grid click
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the user verify the Compact Size of Screen
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    ## Truncate columns click
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the selected search option is reset after test
    Examples:
      | mi_stage          | paginationValue | filter_value1 | operator  | value1                    | filter_value2   | value2                                              | filter_value3 | value3      | filter_value4   | operator1  | value4                    | filter_value5         | fixed_operator | value5                      | filter_value6              | value6 | filter_value7                         | value7 | filter_value8      | filter_value9                  | operator9    | date       |
      | Sequencer Samples | 25              | GLH           | is        | London North              | Ordering Entity | Barts Health NHS Trust                              | Referral ID   | r1234       | Patient NGIS ID | is exactly | p08395405325              | gel1009 Plate Barcode | is             | r1234                       | gel1010 Illumina QC Status | Pass   | gel1010 Illumina Sample Concentration | r9876  | gel1009 Patient ID | gel1009 Plate Date of Dispatch | equals       | 11-03-2020 |
      | Sequencer Samples | 50              | GLH           | is one of | London North,London South | Ordering Entity | 2gether NHS Foundation Trust,Barts Health NHS Trust | Referral ID   | r1234,r5678 | Patient NGIS ID | is one of  | p08395405325,p67752570099 | gel1009 Plate Barcode | is             | LP6264316-DNA,LP8907987-DNA | gel1010 Illumina QC Status | Fail   | gel1010 Illumina Sample Concentration | r9888  | gel1009 Patient ID | gel1009 Plate Date of Dispatch | before or on | 14-03-2020 |

  @NTS-5017
   ## @E2EUI-1836 @E2EUI-2408
  Scenario Outline: NTS-5017:E2EUI-1836,E2EUI-2408:Mi dashboard New referrals section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "new_referrals-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Has Gel1001                      |
      | Priority                         |
      | Sample Processing GLH            |
      | Test Type Name                   |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    When the user selects a value "<filter_value1>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "new_referrals-search-value" value drop-down
    Then the user sees the selected "<value1>" in the "new_referrals-search-value" value drop-down
    And the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    When the user selects a value "<filter_value2>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value2>" from the "new_referrals-search-value" value drop-down
    Then the user sees the selected "<value2>" in the "new_referrals-search-value" value drop-down
    And the selected search option is reset after test
    ###For 3rd filter-Referral ID
    When the user selects a value "<filter_value3>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user fills in a "<value3>" in the "new_referrals-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the user sees the message "No results found for these search terms." below the search container
    And the selected search option is reset after test
    ###For 4th filter-Has Gel1001
    When the user selects a value "<filter_value4>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value4>" from the "new_referrals-search-value" value drop-down
    Then the user sees the selected "<value4>" in the "new_referrals-search-value" value drop-down
    And the selected search option is reset after test
    ###For 5th filter-Priority
    When the user selects a value "<filter_value5>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value5>" from the "new_referrals-search-value" value drop-down
    Then the user sees the selected "<value5>" in the "new_referrals-search-value" value drop-down
    And the selected search option is reset after test
    ###For 6th filter-Sample Processing GLH
    When the user selects a value "<filter_value6>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "new_referrals-search-value" value drop-down
    Then the user sees the selected "<value1>" in the "new_referrals-search-value" value drop-down
    And the selected search option is reset after test
    ###For 7th filter-Test Type Name
    When the user selects a value "<filter_value7>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value7>" from the "new_referrals-search-value" value drop-down
    And the user sees the selected "<value7>" in the "new_referrals-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ### Results Table
#    When the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    And the user selects a pagination drop-down value "<paginationValue>"
    Then the user sees the search result table updated with "<paginationValue>" results
    And the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    ### Download CSV
    And the user clicks on the Download CSV button to download the CSV file as "new_referrals_filtered".csv
    ### Display Button options
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user sees the buttons - Reset, Save and close under Display Options
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "priority" from the section "Hide" to "Show" section
    Then the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | priority                 |
    And the user save the changes on modal content by clicking Save and Close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user clicks on the button "Hide all"
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user clicks on the button "Show all"
    And the user save the changes on modal content by clicking Save and Close button
    ### Compact grid check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the user verify the Compact Size of Screen
    Then the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    ### Truncate columns check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    Then the selected search option is reset after test

    Examples:
      | mi_stage      | filter_value1 | operator  | value1                    | filter_value2   | value2                                              | filter_value3 | value3      | filter_value4 | operator1 | value4 | filter_value5 | value5         | filter_value6         | filter_value7  | value7                                 | paginationValue | ColumnHeader |
      | New Referrals | GLH           | is        | London North              | Ordering Entity | Barts Health NHS Trust                              | Referral ID   | r1234       | Has Gel1001   | is        | TRUE   | Priority      | Routine        | Sample Processing GLH | Test Type Name | Craniosynostosis WGS                   | 25              | Priority     |
      | New Referrals | GLH           | is one of | London North,London South | Ordering Entity | 2gether NHS Foundation Trust,Barts Health NHS Trust | Referral ID   | r1234,r5678 | Has Gel1001   | is        | FALSE  | Priority      | Routine,Urgent | Sample Processing GLH | Test Type Name | Craniosynostosis WGS,Hydrocephalus WGS | 50              | Priority     |

