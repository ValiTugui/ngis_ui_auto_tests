@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - Sanity

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5065
    #@E2EUI-1985
  Scenario Outline: NTS-5065:E2EUI-1985:Sanity for Mi Portal
    When the user should be able to see sample processing menu is displayed
    ##Header And Naviagtion Options
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
    ##File Submissions Section
    When the user navigates to the mi-portal "<mi_stage1>" stage
    And the user sees a search box container section for "<mi_stage1>" page
    ##Filters
    And the user selects a value "<value1>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<value>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    When search results are displayed in table format with display options button
    And the selected search option is reset after test
    ##Order Tracking Section
    When the user navigates to the mi-portal "<mi_stage2>" stage
    And the user sees a search box container section for "<mi_stage2>" page
    ##Filters
    And the user selects a value "<value2>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    When search results are displayed in table format with display options button
    ##Displaying Columns Options
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user clicks on save and close button
    And the selected search option is reset after test
    ##GLH Samples Section
    When the user navigates to the mi-portal "<mi_stage3>" stage
    And the user selects a value "<value3>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator1>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value4>" from the "glh_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    And the user click on the reset button
    And the user selects a value "<value2>" from the "glh_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<value5>" from the "glh_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
#    Then search results are displayed for the file-submission search
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test
    ##Plater Samples Section
    And the user navigates to the mi-portal "<mi_stage4>" stage
    Then the user sees a search box container section for "<mi_stage4>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<value6>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator2>" from the "plater_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the plater-samples date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ##Pagination
#    When search results are displayed in table format with display options button
#    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    Then the selected search option is reset after test
    ##Picklist Section
    When the user navigates to the mi-portal "<mi_stage5>" stage
    And the user sees a search box container section for "<mi_stage5>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "picklists" page
    And the user selects a value "<value2>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "picklists-search-value" column drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "gel1008__created" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
#    And the table column "<ColumnHeader>" is displayed with data
    Then the selected search option is reset after test
    ##Sequencer Samples Section
    When the user navigates to the mi-portal "<mi_stage6>" stage
    And the user sees a search box container section for "<mi_stage6>" page
    And the user selects a value "<value2>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<value7>" from the "sequencer_samples-search-value" value drop-down
    And the user sees the buttons - Add, Search, Reset buttons under the Search boxes
    And the user clicks on Add criteria button
    When the user click on the Search button
#    Then search results are displayed in the search results
    Then search results are displayed in table format with display options button
    ###The results are displayed
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ### CSV File download
    When the user clicks on the Download CSV button to download the CSV file as "sequencer_samples_filtered".csv
    Then the selected search option is reset after test
    ##New Referral Section
    And the user navigates to the mi-portal "<mi_stage7>" stage
    Then the user sees a search box container section for "<mi_stage7>" page
    And the user selects a value "<value2>" from the "new_referrals-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<value5>" from the "new_referrals-search-value" value drop-down
    And the user sees the selected "<value5>" in the "new_referrals-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    Then the selected search option is reset after test
    ##Search LSID Section
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user inputs the "<LSID>" reference number
    And the user clicks on Find LSID
    ###after clicking on find Button ,the data is displayed in image formats and not able to identify any validation
    Examples:
      | mi_stage     | mi_stage1        | value1 | value | mi_stage2      | mi_stage3   | mi_stage4      | mi_stage5 | mi_stage6         | mi_stage7     | value2 | dropdown     | value4                      | NoOfSearchField | section        | value3          | value5       | value6                           | value7                        | operator1 | operator2    | date       | paginationValue | operator | ColumnHeader    | LSID       |
      | Search LSIDs | File Submissions | Status | Valid | Order Tracking | GLH Samples | Plater Samples | Picklists | Sequencer Samples | New Referrals | GLH    | London North | Bolton NHS Foundation Trust | 3               | plater_samples | Ordering Entity | London North | gel1005 Sample Received Datetime | East Mids and East of England | is one of | before or on | 21-02-2020 | 25              | is       | gel1008 Created | 1371612610 |

  @NTS-5053
    #@E2EUI-2705 @Scenario1
  Scenario Outline: NTS-5053:E2EUI-2705:File Submissions- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "file_submissions-search-col" column drop-down
    And the user sees the values in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | Created                           |
      | Status                            |
      | Submitted By                      |
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "file_submissions-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage         | section          | column | NoOfSearchField | operator | dropdownValue       |
      | File Submissions | file_submissions | Status | 3               | is       | Valid with Warnings |

  @NTS-5053
    #@E2EUI-2705 @Scenario2
  Scenario Outline: NTS-5053:E2EUI-2705:Order Tracking- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "order_tracking-search-col" column drop-down
    And the user sees the values in the file-submission search column "order_tracking-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |
      | Referral ID                       |
      | Patient NGIS ID                   |
      | Clinical Indication Test Type     |
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage       | section        | column | NoOfSearchField | operator | dropdownValue                 |
      | Order Tracking | order_tracking | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5053
    #@E2EUI-2705 @Scenario3
  Scenario Outline: NTS-5053:E2EUI-2705:GLH Samples- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "glh_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | Batch Import Filename                 |
      | Dispatched Sample Type                |
      | gel1004 GLH Sample Consignment Number |
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "glh_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage    | section     | column | NoOfSearchField | operator | dropdownValue                 |
      | GLH Samples | glh_samples | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5053
    #@E2EUI-2705 @Scenario4
  Scenario Outline: NTS-5053:E2EUI-2705:Plater Samples- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "plater_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "plater_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
#      | gel1004 Clinic Sample Type            |
       ## As part of SALLY release the field/filter "gel1004 Clinic Sample Type" has been removed
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "plater_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage       | section        | column | NoOfSearchField | operator | dropdownValue                 |
      | Plater Samples | plater_samples | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5053
    #@E2EUI-2705 @Scenario5
  Scenario Outline: NTS-5053:E2EUI-2705:Picklists- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "picklists-search-col" column drop-down
    And the user sees the values in the file-submission search column "picklists-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader              |
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | gel1008 Plate ID                               |
      | gel1008 Normalised Biorepository Sample Volume |
      | gel1008 Plate Date of Dispatch                 |
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "picklists-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage  | section   | column | NoOfSearchField | operator | dropdownValue                 |
      | Picklists | picklists | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5053
    #@E2EUI-2705 @Scenario5
  Scenario Outline: NTS-5053:E2EUI-2705:Sequencer Samples- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "sequencer_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "sequencer_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1009 Plate Barcode                 |
      | gel1010 Illumina QC Status            |
      | gel1010 Illumina Sample Concentration |
      | gel1009 Patient ID                    |
      | gel1009 Plate Date of Dispatch        |
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "sequencer_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage          | section           | column | NoOfSearchField | operator | dropdownValue                 |
      | Sequencer Samples | sequencer_samples | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5053
    #@E2EUI-2705 @Scenario6
  Scenario Outline: NTS-5053:E2EUI-2705:New Referrals- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "new_referrals-search-col" column drop-down
    And the user sees the values in the file-submission search column "new_referrals-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |
      | Referral ID                       |
      | Has Gel1001                       |
      | Priority                          |
      | Sample Processing GLH             |
      | Test Type Name                    |
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "new_referrals-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks "Hide all" button on the modal-content page
    And the user should be able to see the empty "Show" label and the data with "Hide" label
    Then the user should see Save and Close button as disabled
    And the user closes the modal content by clicking on the reset-button
    And the selected search option is reset after test

    Examples:
      | mi_stage      | section       | column | NoOfSearchField | operator | dropdownValue                 |
      | New Referrals | new_referrals | GLH    | 3               | is       | East Mids and East of England |

  @NTS-5057
    #@E2EUI-2700 @Scenario1
  Scenario Outline: NTS-5057:E2EUI-2700: File Submissions :The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user sees the values in the file-submission search column "file_submissions-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | Created                           |
      | Status                            |
      | Submitted By                      |
    Then the user click on the reset button
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage         | value   | operator | date       | paginationValue | section          | NoOfSearchField |
      | File Submissions | Created | equals   | 02-04-2020 | 50              | file_submissions | 3               |

  @NTS-5057
    #@E2EUI-2700 @Scenario2
  Scenario Outline: NTS-5057:E2EUI-2700 :Order Tracking- The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "order_tracking-search-col" column drop-down
    And the user sees the values in the file-submission search column "order_tracking-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |
      | Referral ID                       |
      | Patient NGIS ID                   |
      | Clinical Indication Test Type     |
    Then the user click on the reset button
    And the user selects a value "Ordering Entity" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "is" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "North Bristol NHS Trust" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage       | section        | column | NoOfSearchField | paginationValue |
      | Order Tracking | order_tracking | GLH    | 3               | 50              |

  @NTS-5057
    #@E2EUI-2700 @Scenario3
  Scenario Outline: NTS-5057:E2EUI-2700 :GLH Samples- The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "glh_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | Batch Import Filename                 |
      | Dispatched Sample Type                |
      | gel1004 GLH Sample Consignment Number |
    And the user selects a search operator "<operator>" from the "glh_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "glh_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage    | section     | column | NoOfSearchField | operator | dropdownValue                 | paginationValue |
      | GLH Samples | glh_samples | GLH    | 3               | is       | East Mids and East of England | 50              |

  @NTS-5057
    #@E2EUI-2700 @Scenario4
  Scenario Outline: NTS-5057:E2EUI-2700 :Plater Samples- The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "plater_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "plater_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
#      | gel1004 Clinic Sample Type            |
       ## As part of SALLY release the field/filter "gel1004 Clinic Sample Type" has been removed
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "plater_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage       | section        | column | NoOfSearchField | operator | dropdownValue                 | paginationValue |
      | Plater Samples | plater_samples | GLH    | 3               | is       | East Mids and East of England | 50              |

  @NTS-5057
    #@E2EUI-2700 @Scenario5
  Scenario Outline: NTS-5057:E2EUI-2700 : Picklists- The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "picklists-search-col" column drop-down
    And the user sees the values in the file-submission search column "picklists-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader              |
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | gel1008 Plate ID                               |
      | gel1008 Normalised Biorepository Sample Volume |
      | gel1008 Plate Date of Dispatch                 |
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "picklists-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage  | section   | column | NoOfSearchField | operator | dropdownValue                 | paginationValue |
      | Picklists | picklists | GLH    | 3               | is       | East Mids and East of England | 50              |

  @NTS-5057
    #@E2EUI-2700 @Scenario6
  Scenario Outline: NTS-5057:E2EUI-2700 :Sequencer Samples- The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "sequencer_samples-search-col" column drop-down
    And the user sees the values in the file-submission search column "sequencer_samples-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader     |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1009 Plate Barcode                 |
      | gel1010 Illumina QC Status            |
      | gel1010 Illumina Sample Concentration |
      | gel1009 Patient ID                    |
      | gel1009 Plate Date of Dispatch        |
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "sequencer_samples-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage          | section           | column | NoOfSearchField | operator | dropdownValue                 | paginationValue |
      | Sequencer Samples | sequencer_samples | GLH    | 3               | is       | East Mids and East of England | 50              |

  @NTS-5057
    #@E2EUI-2700 @Scenario7
  Scenario Outline: NTS-5057:E2EUI-2700 :New Referrals-The page size will not reset to default after using Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<column>" from the "new_referrals-search-col" column drop-down
    And the user sees the values in the file-submission search column "new_referrals-search-col" drop-down menu
      | fileSubmissionsSearchColumnHeader |
      | GLH                               |
      | Ordering Entity                   |
      | Referral ID                       |
      | Has Gel1001                       |
      | Priority                          |
      | Sample Processing GLH             |
      | Test Type Name                    |
    And the user selects a search operator "<operator>" from the "new_referrals-search-operator" operator drop-down
    And the user selects a value "<dropdownValue>" from the "new_referrals-search-value" value drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    When the user selects a pagination drop-down value "<paginationValue>"
    And the user sees the search result table updated with "<paginationValue>" results
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    Then the user sees the search results pagination entry drop-down with unchanged selection of "50"

    Examples:
      | mi_stage      | section       | column | NoOfSearchField | operator | dropdownValue                 | paginationValue |
      | New Referrals | new_referrals | GLH    | 3               | is       | East Mids and East of England | 50              |

