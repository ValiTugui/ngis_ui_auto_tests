@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - User Journey - Plater Samples and Picklists

  @NTS-5188
     #@E2EUI-1424  @E2EUI-2580
  Scenario Outline:NTS-5188:E2EUI-1424,2580: MI Dashboard | Plater Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    ## Search Dropdown Validation
    And the user sees the below values in the plater samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | GEL1004 Clinic Sample Type            |
      | GEL1004 Disease Area                  |
      | GEL1004 GLH Sample Consignment Number |
      | GEL1004 Laboratory ID                 |
      | GEL1005 Sample Received Datetime      |
    Then the selected search option is reset after test
     ##For 1st filter-GLH
    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects GLHName as the plater samples search value dropdown
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    And the user selects Ordering Entity as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects OrderingEntity as the plater samples search value dropdown
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    When the user selects Referral ID as the plater samples search column dropdown
    And the user selects is exactly as the plater samples search operator dropdown
    And the user selects Referral ID as the plater samples search input value
    Then the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    When the user selects Patient NGIS ID as the plater samples search column dropdown
    And the user selects is exactly as the plater samples search operator dropdown
    And the user selects Patient NGIS ID as the plater samples search input value
    Then the selected search option is reset after test
    ###5 gel1004 Clinic Sample Type
    When the user selects <filter_value5> as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects <value5> as the plater samples search value dropdown
    Then the selected search option is reset after test
    ####6  gel1004 Disease Area
    And the user selects a value "<filter_value6>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user selects a value "<value6>" from the "plater_samples-search-value" column drop-down
    And the user sees the selected "<value6>" in the "plater_samples-search-value" value drop-down
    Then the selected search option is reset after test
    ###7   gel1004 GLH Sample Consignment Number
    And the user selects a value "<filter_value7>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user fills in a "<value7>" in the "plater_samples-search-value" search box
    Then the selected search option is reset after test
    ####8  gel1004 Laboratory ID
    And the user selects a value "<filter_value8>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user selects a value "<value8>" from the "plater_samples-search-value" column drop-down
    And the user sees the selected "<value8>" in the "plater_samples-search-value" value drop-down
    Then the selected search option is reset after test
    ####9  gel1005 Sample Received Datetime
    And the user selects a value "<filter_value9>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator9>" from the "plater_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    Then the selected search option is reset after test
    ## Value given to All Dropdown for Table Data
#    And the user selects a value "<filter_value1>" from the "plater_samples-search-col" column drop-down
#    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
#    And the user selects a value "<value1>" from the "plater_samples-search-value" column drop-down
    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects GLHName as the plater samples search value dropdown
    And the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
      ## Table Data Fetched
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    ## CSV File download
    When the user clicks on the Download CSV button to download the CSV file as "plater_samples_filtered".csv
    ## Inside Diplay Options
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    Then the user sees the buttons - Reset, Save and close under Display Options
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "GEL1001 Referral ID" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | GEL1001 Referral ID      |
    When the user drag the column header "GEL1005 Filename" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | GEL1005 Filename         |
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders     |
      | GEL1005 Filename |
    When the user clicks on the Display Options button
    When the user clicks "Hide all" button on the modal-content page
    And the Save and Close button under Show All and Hide All button becomes disabled
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    ## Compact grid click
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the user verify the Compact Size of Screen
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    ## Truncate columns click
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the selected search option is reset after test

    Examples:
      | mi_stage       | paginationValue | filter_value5              | operator | value5     | filter_value6        | value6       | filter_value7                         | value7           | filter_value8         | value8     | filter_value9                    | operator9 | date       |
      | Plater Samples | 25              | GEL1004 Clinic Sample Type | is       | dna_saliva | GEL1004 Disease Area | Rare Disease | GEL1004 GLH Sample Consignment Number | lns-2020-04-08-1 | GEL1004 Laboratory ID | North West | GEL1005 Sample Received Datetime | equals    | 14-03-2020 |

  @NTS-5063
   ## @E2EUI-1305
  Scenario Outline:NTS-5063:E2EUI-1305 :Mi dashboard Picklists section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "picklists-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader               |
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | GEL1008 Plate ID                               |
      | GEL1008 Normalised Biorepository Sample Volume |
      | GEL1008 Plate Date of Dispatch                 |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    When the user selects a value "<filter_value1>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "picklists-search-value" value drop-down
    And the user sees the selected "<value1>" in the "picklists-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    When the user selects a value "<filter_value2>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<value2>" from the "picklists-search-value" value drop-down
    And the user sees the selected "<value2>" in the "picklists-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    When the user selects a value "<filter_value3>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value3>" in the "picklists-search-value" search box
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the user sees the message "No results found for these search terms." below the search container
    And the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    When the user selects a value "<filter_value4>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value4>" in the "picklists-search-value" search box
    And the selected search option is reset after test
    ###For 5th filter-gel1008 Plate ID
    And the user selects a value "<filter_value5>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value5>" in the "picklists-search-value" search box
    Then the selected search option is reset after test
     ###For 6th filter-gel1008 Normalised Biorepository Sample Volume
    When the user selects a value "<filter_value6>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator2>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value6>" in the "picklists-search-value" search box
    And the user selects a search operator "<operator3>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value6>" in the "picklists-search-value" search box
    Then the selected search option is reset after test
     ###For 7th filter-gel1008 Plate Date of Dispatch
    When the user selects a value "<filter_value7>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator4>" from the "picklists-search-operator" operator drop-down
    And the user enters a date "<date1>" in the file-submission date field
    And the user selects a search operator "<operator5>" from the "picklists-search-operator" operator drop-down
    And the user enters a date "<date2>" in the file-submission date field
    Then the selected search option is reset after test
     ### Search for Results
    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user selects GLHName as the pick lists search value dropdown
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ### Results Table Page
#    When the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    And the user selects a pagination drop-down value "<paginationValue>"
    Then the user sees the search result table updated with "<paginationValue>" results
    #### Download CSV
    When the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    When the user clicks on the Download CSV button to download the CSV file as "picklists_filtered".csv
    ### Display Options Button
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the buttons - Reset, Save and close under Display Options
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    When the user drag the column header "GEL1008 Participant ID" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | GEL1008 Participant ID  |
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders          |
      | GEL1008 Participant ID |
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user clicks on the button "Hide all"
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user clicks on the button "Show all"
    Then the user save the changes on modal content by clicking Save and Close button
    ### Compact grid check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    And the user verify the Compact Size of Screen
    Then the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    ### Truncate columns check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<mi_stage>" page
    Then the selected search option is reset after test

    Examples:
      | mi_stage  | filter_value1 | operator  | value1                    | filter_value2   | value2                                              | filter_value3 | value3      | filter_value4   | operator1  | value4                                 | filter_value5    | value5                      | filter_value6                                  | operator2                | value6 | operator3                   | filter_value7                  | operator4    | operator5    | date1      | date2      | paginationValue |
      | Picklists | GLH           | is        | London North              | Ordering Entity | Barts Health NHS Trust                              | Referral ID   | r1234       | Patient NGIS ID | is exactly | p87902736451                           | GEL1008 Plate ID | LP1110102-DNA               | GEL1008 Normalised Biorepository Sample Volume | equals                   | 90     | is greater than or equal to | GEL1008 Plate Date of Dispatch | equals       | before or on | 30-03-2020 | 25-03-2020 | 25              |
      | Picklists | GLH           | is one of | London North,London South | Ordering Entity | 2gether NHS Foundation Trust,Barts Health NHS Trust | Referral ID   | r1234,r5678 | Patient NGIS ID | is one of  | p24295001324,p44616417782,p16259578271 | GEL1008 Plate ID | LP1211302-DNA,LP1110102-DNA | GEL1008 Normalised Biorepository Sample Volume | is less than or equal to | 100    | is greater than or equal to | GEL1008 Plate Date of Dispatch | before or on | on or after  | 30-03-2020 | 20-03-2020 | 50              |