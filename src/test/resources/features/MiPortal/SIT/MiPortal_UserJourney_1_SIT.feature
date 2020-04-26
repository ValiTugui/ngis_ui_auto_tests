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
      | Clinical Indication Test Type |
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
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test
#    Then the user sees the message "No results found for these search terms." below the search container
#    And the selected search option is reset after test
    ###For 4th filter-Patient NGIS ID
    When the user selects Patient NGIS ID as the order tracking search column dropdown
    And the user selects is exactly as the order tracking search operator dropdown
    And the user selects Patient NGIS ID as the order tracking search input value
    And the user clicks on Add criteria button
    And file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test
    ###For 5th filter-Clinical Indication Test Type
    When the user selects Test Type as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects Test Type as the order tracking search value dropdown
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
#    When the user clicks on the Download CSV button to download the CSV file as "file_submissions_filtered".csv in GLH samples page
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
      | mi_stage    |paginationValue |
      | GLH Samples |25              |