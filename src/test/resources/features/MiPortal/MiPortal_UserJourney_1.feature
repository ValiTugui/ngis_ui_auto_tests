@MIPORTAL

Feature: MI Portal - This is mi-portal User Journey for Order Tracking and GLH Samples.

  @NTS-5061
   ## @E2EUI-855
  Scenario Outline:NTS-5061 :Mi dashboard Order Tracking section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "order_tracking-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Patient NGIS ID                  |
      | Clinical Indication Test Type    |
    Then the selected search option is reset after test
    ###For 1st filter-GLH
    When the user selects a value "<filter_value1>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "order_tracking-search-value" value drop-down
    And the user sees the selected "<value1>" in the "order_tracking-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 2nd filter-Ordering Entity
    When the user selects a value "<filter_value2>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value2>" from the "order_tracking-search-value" value drop-down
    And the user sees the selected "<value2>" in the "order_tracking-search-value" value drop-down
    Then the selected search option is reset after test
    ###For 3rd filter-Referral ID
    When the user selects a value "<filter_value3>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "order_tracking-search-operator" operator drop-down
    And the user fills in a "<value3>" in the "order_tracking-search-value" search box
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the user sees the message "No results found for these search terms." below the search container
    And the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    When the user selects a value "<filter_value4>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "order_tracking-search-operator" operator drop-down
    And the user fills in a "<value4>" in the "order_tracking-search-value" search box
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test
    ###For 5th filter-Clinical Indication Test Type
    When the user selects a value "<filter_value5>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value5>" from the "order_tracking-search-value" value drop-down
    And the user sees the selected "<value5>" in the "order_tracking-search-value" value drop-down
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
    Then the user clicks on the Download CSV button to download the CSV file as "order_tracking_filtered".csv
    ### Display Options Button
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user sees the buttons - Reset, Save and close under Display Options
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page
    And the user drag the column header "gel1008__well_id" from the section "Show" to "Hide" section
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | gel1008__well_id         |
    And the user save the changes on modal content by clicking Save and Close button
    Then the columns fields are not displayed in the list of columns headers of the search result table
      | columnHeaders   |
      | gel1008 Well ID |
    When the user clicks on the Display Options button
    And the user sees a modal-content page
    And the user clicks on the button "Hide all"
    And the Save and Close button under Show All and Hide All button becomes disabled
    And the user clicks on the button "Show all"
    Then the user save the changes on modal content by clicking Save and Close button
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
      | mi_stage       | filter_value1 | operator  | value1                    | filter_value2   | value2                                              | filter_value3 | value3      | filter_value4   | operator1  | value4                    | filter_value5                 | value5                                                                                                                       | paginationValue |
      | Order Tracking | GLH           | is        | London North              | Ordering Entity | Barts Health NHS Trust                              | Referral ID   | r1234       | Patient NGIS ID | is exactly | p24295001324              | Clinical Indication Test Type | Epilepsy - early onset or syndromic WGS: Early onset or syndromic epilepsy                                                   | 25              |
      | Order Tracking | GLH           | is one of | London North,London South | Ordering Entity | 2gether NHS Foundation Trust,Barts Health NHS Trust | Referral ID   | r1234,r5678 | Patient NGIS ID | is one of  | p24295001324,p44616417782 | Clinical Indication Test Type | Cerebral malformations WGS: Cerebral malformation,Epilepsy - early onset or syndromic WGS: Early onset or syndromic epilepsy | 50              |

  @NTS-5064
    #@E2EUI-1292
  Scenario Outline:NTS-5064 : MI Dashboard | GLH Samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Patient NGIS ID                  |
      | Batch Import Filename            |
    Then the selected search option is reset after test
    ##For 1st filter-GLH and 2nd filter-Ordering Entity
    And the user selects a value "<filter_value1>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "glh_samples-search-value" value drop-down
    And the user sees the selected "<value1>" in the "glh_samples-search-value" value drop-down
    Then the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    When the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the table column "<ColumnHeader>" is displayed with data
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
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user click on the "Truncate columns" check box on the modal content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList                       |
      | gel1001__created                               |
      | gel1001__primary_sample_id_received_glh        |
      | gel1001__dispatched_sample_id_glh_lims         |
      | gel1001__dispatched_sample_state               |
      | gel1001__laboratory_remaining_volume_banked_ul |
      | gel1001__glh_od_260_280                        |
      | gel1001__glh_din_value                         |
      | gel1001__glh_percentage_dna                    |
      | gel1001__dna_extraction_protocol               |
      | gel1001__prolonged_sample_storage              |
      | gel1001__retrospective_sample                  |
      | gel1001__approved_by                           |
      | gel1001__clinical_indication_test_type_id      |
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList               |
      | gel1001__filename                      |
      | gel1001__patient_nhs_number            |
      | gel1001__patient_ngis_id               |
      | gel1001__ordering_entity_id            |
      | gel1001__primary_sample_received_date  |
      | gel1001__primary_sample_id_glh_lims    |
      | gel1001__dispatched_sample_lsid        |
      | gel1001__dispatched_sample_volume_ul   |
      | gel1001__glh_concentration_ng_ul       |
      | gel1001__glh_qc_status                 |
      | gel1001__glh_sample_dispatch_date      |
      | gel1001__gmc_rack_id                   |
      | gel1001__gmc_rack_well                 |
      | gel1001__referral_id                   |
      | gel1001__glh_laboratory_id             |
      | gel1001__dispatched_sample_type        |
      | gel1001__glh_sample_consignment_number |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    Then the selected search option is reset after test

    ##For 3rd filter-Referral ID and 4th filter-Patient NGIS ID
    And the user selects a value "<filter_value2>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "glh_samples-search-operator" operator drop-down
    And the user fills in a "<value2>" in the "glh_samples-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
#    Then the table column "<ColumnHeader>" is displayed with data
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
    And the user sees the checkboxes with the label names "Compact grid" and "Truncate columns"
    And the user click on the "Compact grid" check box on the modal content page
    And the user click on the "Truncate columns" check box on the modal content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList                       |
      | gel1001__created                               |
      | gel1001__primary_sample_id_received_glh        |
      | gel1001__dispatched_sample_id_glh_lims         |
      | gel1001__dispatched_sample_state               |
      | gel1001__laboratory_remaining_volume_banked_ul |
      | gel1001__glh_od_260_280                        |
      | gel1001__glh_din_value                         |
      | gel1001__glh_percentage_dna                    |
      | gel1001__dna_extraction_protocol               |
      | gel1001__prolonged_sample_storage              |
      | gel1001__retrospective_sample                  |
      | gel1001__approved_by                           |
      | gel1001__clinical_indication_test_type_id      |
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList               |
      | gel1001__filename                      |
      | gel1001__patient_nhs_number            |
      | gel1001__patient_ngis_id               |
      | gel1001__ordering_entity_id            |
      | gel1001__primary_sample_received_date  |
      | gel1001__primary_sample_id_glh_lims    |
      | gel1001__dispatched_sample_lsid        |
      | gel1001__dispatched_sample_volume_ul   |
      | gel1001__glh_concentration_ng_ul       |
      | gel1001__glh_qc_status                 |
      | gel1001__glh_sample_dispatch_date      |
      | gel1001__gmc_rack_id                   |
      | gel1001__gmc_rack_well                 |
      | gel1001__referral_id                   |
      | gel1001__glh_laboratory_id             |
      | gel1001__dispatched_sample_type        |
      | gel1001__glh_sample_consignment_number |
    And the user save the changes on modal content by clicking Save and Close button
    And the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values
    Then the selected search option is reset after test

    Examples:
      | mi_stage    | filter_value1   | operator  | value1                                              | filter_value2   | operator1  | value2                    | paginationValue | ColumnHeader |
      | GLH Samples | GLH             | is        | London North                                        | Referral ID     | is exactly | r20233302920              | 25              | GMC,NGIS     |
      | GLH Samples | GLH             | is one of | London North,London South                           | Referral ID     | is one of  | r20233302920,r20963473082 | 50              | GMC,NGIS     |
      | GLH Samples | Ordering Entity | is        | Barts Health NHS Trust                              | Patient NGIS ID | is exactly | p08395405325              | 25              | GMC,NGIS     |
      | GLH Samples | Ordering Entity | is one of | 2gether NHS Foundation Trust,Barts Health NHS Trust | Patient NGIS ID | is one of  | p08395405325,p67752570099 | 50              | GMC,NGIS     |
