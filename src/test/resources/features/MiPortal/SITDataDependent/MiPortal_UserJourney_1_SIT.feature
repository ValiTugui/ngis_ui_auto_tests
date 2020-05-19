@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - User Journey - Order Tracking and GLH Samples.

  @NTS-5061
   ## @E2EUI-855
  Scenario Outline:NTS-5061:E2EUI-855: Mi dashboard Order Tracking section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<order_tracking>" stage
    And the user sees a search box container section for "<order_tracking>" page
    And the user sees the below values in the order tracking search column drop-down menu
      | GLH                           |
      | Ordering Entity               |
      | Referral ID                   |
      | Patient NGIS ID               |
      | Test Type       |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    When the user selects Ordering Entity as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects Ordering Entity as the order tracking search value dropdown
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    When the user selects Referral ID as the order tracking search column dropdown
    And the user selects is exactly as the order tracking search operator dropdown
    And the user selects Referral ID as the order tracking search input value
    Then the selected search option is reset after test
#    And the user clicks on Add criteria button
#    And file submission search criteria badge information is displayed below drop-down buttons
#    And the user click on the Search button
#    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
#    And the selected search option is reset after test
#    Then the user sees the message "No results found for these search terms." below the search container
#    And the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    When the user selects Patient NGIS ID as the order tracking search column dropdown
    And the user selects is exactly as the order tracking search operator dropdown
    And the user selects Patient NGIS ID as the order tracking search input value
#    And the user clicks on Add criteria button
#    And file submission search criteria badge information is displayed below drop-down buttons
#    And the user click on the Search button
#    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test
    ###For 5th filter-Clinical Indication Test Type
    When the user selects Test Type as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects Test Type as the order tracking search value dropdown
    And the selected search option is reset after test
    ### Results Table
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ### Results Table Page
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
    And the user should be able to download the filtered order tracking
    ### Display Options Button
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user sees the buttons - Reset, Save and close under Display Options
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    And the user drag the column header "GEL1008 Well ID" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | GEL1008 Well ID          |
    And the user save the changes on modal content by clicking Save and Close button
    Then the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders   |
      | GEL1008 Well ID |
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user clicks on the button "Hide all"
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user clicks on the button "Show all"
    Then the user save the changes on modal content by clicking Save and Close button
    ### Compact grid check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Compact grid" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<order_tracking>" page
    And the user verify the Compact Size of Screen
    Then the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    ### Truncate columns check
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Truncate columns" check box on the modal content page
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees a search box container section for "<order_tracking>" page
    Then the selected search option is reset after test

    Examples:
      | order_tracking | paginationValue |
      | Order Tracking | 25              |

  @NTS-5064
    #@E2EUI-1292 E2EUI-2579
  Scenario Outline:NTS-5064:E2EUI-1292,E2EUI-2579: MI Dashboard | GLH Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the glh samples search column drop-down menu
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Patient NGIS ID                  |
      | Batch Import Filename            |
      | Dispatched Sample Type                |
      | GEL1004 GLH Sample Consignment Number |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    And the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects GLHName as the glh search value dropdown
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    And the user selects Ordering Entity as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects OrderingEntity as the glh search value dropdown
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    And the user selects Referral ID as the glh search column dropdown
    And the user selects is exactly as the glh search operator dropdown
    And the user enters Referral ID in the glh search value box
    And the user clicks on Add criteria button
    Then the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    And the user selects Patient NGIS ID as the glh search column dropdown
    And the user selects is exactly as the glh search operator dropdown
    And the user enters Patient NGIS ID in the glh search value box
    And the user clicks on Add criteria button
    Then the selected search option is reset after test
     ###For 5th filter-Batch Import Filename
    And the user selects Batch Import Filename as the glh search column dropdown
    And the user selects matches as the glh search operator dropdown
    And the user enters <FilenameValue> in the glh search value box
    And the user clicks on Add criteria button
    Then the selected search option is reset after test
     ###For 6th filter-Dispatched Sample Type
    And the user selects Dispatched Sample Type as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects Liquid tumour sample as the glh search value dropdown
    And the user clicks on Add criteria button
    Then the selected search option is reset after test
     ###For 7th filter-GEL1004 GLH Sample Consignment Number
    And the user selects GEL1004 GLH Sample Consignment Number as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user enters ConsignmentNumber in the glh search value box
    Then the selected search option is reset after test
      ### Results Table
    And the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects GLHName as the glh search value dropdown
    Then the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Download CSV button to download the CSV file as "glh_samples_filtered".csv
    When the user clicks on the Display Options button
    And the user sees the buttons - Reset, Save and close under Display Options
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    Then the user sees a modal-content page
    And the user sees the checkboxes with the label names Compact grid and Truncate columns
    And the user click on the "Compact grid" check box on the modal content page
    And the user click on the "Truncate columns" check box on the modal content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList                       |
      | GEL1001 Created                               |
      | GEL1001 Primary Sample ID Received GLH        |
      | GEL1001 Dispatched Sample ID GLH Lims         |
      | GEL1001 Dispatched Sample State               |
      | GEL1001 Laboratory Remaining Volume Banked ul |
      | GEL1001 GLH OD 260 280                        |
      | GEL1001 GLH Din Value                         |
      | GEL1001 GLH Percentage DNA                    |
      | GEL1001 DNA Extraction Protocol               |
      | GEL1001 Prolonged Sample Storage              |
      | GEL1001 Retrospective Sample                  |
      | GEL1001 Approved By                           |
      | GEL1001 Clinical Indication Test Type ID      |
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList               |
      | GEL1001 Filename                      |
      | GEL1001 Patient NHS Number            |
      | GEL1001 Patient NHS Number            |
      | GEL1001 Ordering Entity ID            |
      | GEL1001 Primary Sample Received Date  |
      | GEL1001 Primary Sample ID GLH Lims    |
      | GEL1001 Dispatched Sample Lsid        |
      | GEL1001 Dispatched Sample Volume ul   |
      | GEL1001 GLH Concentration ng ul       |
      | GEL1001 GLH QC Status                 |
      | GEL1001 GLH Sample Dispatch Date      |
      | GEL1001 GMC Rack ID                   |
      | GEL1001 GMC Rack Well                 |
      | GEL1001 Referral ID                   |
      | GEL1001 GLH Laboratory ID             |
      | GEL1001 Dispatched Sample Type        |
      | GEL1001 GLH Sample Consignment Number |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    Then the selected search option is reset after test

    Examples:
      | mi_stage    | paginationValue | FilenameValue           |
      | GLH Samples | 25              | ngis_glh_to_gel_sample_ |