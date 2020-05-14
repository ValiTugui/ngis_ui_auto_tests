@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - PickLists

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5184
    #@E2EUI-2223 @E2EUI-2232
  Scenario Outline:NTS-5184:E2EUI-2223:E2EUI-2232: Data is not displayed gel1008_filename and gel1008_status
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user selects GLHName as the pick lists search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are displayed in the list of columns headers of the pick lists search result table
      | gel1008 Plate Batch Import Filename |
      | gel1008 Plate Batch Import Status   |
      | gel1008 Plate ID                    |
    And the pick lists search result table column gel1008 Plate Batch Import Filename is displayed with data non-empty-data
    And the pick lists search result table column gel1008 Plate Batch Import Status is displayed with data non-empty-data
    And the pick lists search result table column gel1008 Plate ID is displayed with data non-empty-data
    And the selected search option is reset after test

    Examples:
      | mi_stage  |
      | Picklists |

  @NTS-5037
   ## @E2EUI-2437
  Scenario Outline:NTS-5037:E2EUI-2437: A user should be able to see in the Picklists report, the field 'gel1008_plate_warning_msgs'
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user selects GLHName as the pick lists search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "GEL1008 Plate Warning Msgs" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList     |
      | GEL1008 Plate Warning Msgs |
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the pick lists search result table column GEL1008 Plate Warning Msgs is displayed with data non-empty-data
    And the selected search option is reset after test

    Examples:
      | mi_stage  |
      | Picklists |
      #### This is a failure, as per the current UI the "gel1008 Plate Warning Msgs" column does not appear in the results table

  @NTS-5055
    #@E2EUI-2410
  Scenario Outline:NTS-5055:E2EUI-2410:Empty object shown where no Plate Warnings exist
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "picklists" page
    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user selects GLHName as the pick lists search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "GEL1008 Plate Warning Msgs" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    ## We Are checking for Data and blank fields Not for {}
    And the pick lists search result table column GEL1008 Plate Warning Msgs is displayed with data non-empty-data
    Then the selected search option is reset after test
#### This is a failure, as per the current UI the "gel1008 Plate Warning Msgs" column does not appear in the results table
    Examples:
      | mi_stage  | NoOfSearchField |
      | Picklists | 3               |
