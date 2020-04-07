@MIPORTAL

Feature: This is mi-portal PickLists

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-4865
  Scenario Outline: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user sees the values in the search value "picklists-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage  | value | operator  |
      | Picklists | GLH   | is        |
      | Picklists | GLH   | is one of |

  @NTS-5037
   ## @E2EUI-2437
  Scenario Outline:NTS-5037: A user should be able to see in the Picklists report, the field 'gel1008_plate_warning_msgs'
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<value>" from the "picklists-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "gel1008__plate__warning_msgs" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList     |
      | gel1008__plate__warning_msgs |
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage  | filter | operator  | value                     | ColumnHeader               |
      | Picklists | GLH    | is        | London North              | gel1008 Plate Warning Msgs |
      | Picklists | GLH    | is one of | London North,London South | gel1008 Plate Warning Msgs |
#### This is a failure, as per the current UI the "gel1008 Plate Warning Msgs" column does not appear in the results table

  @NTS-5055
    #@E2EUI-2410
  Scenario Outline: NTS-5055:Empty object shown where no Plate Warnings exist
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "picklists" page
    And the user selects a value "<value>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "picklists-search-value" column drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "gel1008__plate__warning_msgs" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ## We Are checking for Data and blank fields Not for {}
    Then the table column "<ColumnHeader>" is displayed with data
    Then the selected search option is reset after test
#### This is a failure, as per the current UI the "gel1008 Plate Warning Msgs" column does not appear in the results table
    Examples:
      | mi_stage  | NoOfSearchField | value | operator | ColumnHeader               |dropdown     |
      | Picklists | 3               | GLH   | is       | gel1008 Plate Warning Msgs |London North |

  @NTS-4920
    #@E2EUI-2223 @E2EUI-2232
  Scenario Outline: Data is not displayed gel1008_filename and gel1008_status
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "picklists-search-value" column drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in the search results
    When the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders                       |
      | gel1008 Plate Batch Import Filename |
      | gel1008 Plate Batch Import Status   |
      | gel1008 Plate ID                    |
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage  | value | operator | dropdown     | ColumnHeader                                                                           |
      | Picklists | GLH   | is       | London North | gel1008 Plate Batch Import Filename,gel1008 Plate Batch Import Status,gel1008 Plate ID |

  @NTS-5058
    #@E2EUI-2710
  Scenario Outline:NTS-5058 : A user is looking for explanatory message from the API
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the values in the search value "picklists-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader               |
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | gel1008 Plate ID                               |
      | gel1008 Normalised Biorepository Sample Volume |
      | gel1008 Plate Date of Dispatch                 |
    Then the user click on the reset button
    And the user selects a value "<filter>" from the "picklists-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "picklists-search-operator" operator drop-down
    And the user fills in a "<value>" in the "picklists-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees an error message "Select a valid choice. That choice is not one of the available choices." on the page

    Examples:
      | mi_stage  | filter           | operator  | value     |
      | Picklists | gel1008 Plate ID | is one of | joy-22457 |

