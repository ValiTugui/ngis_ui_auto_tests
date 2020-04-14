@MIPORTAL

Feature: MIPORTAL:  Plater Samples (E2EUI-2706,2231,2331)

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user sees the values in the search value "plater_samples-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage       | value | operator  |
      | Plater Samples | GLH   | is        |
      | Plater Samples | GLH   | is one of |

  @NTS-4978
   ## @E2EUI-2706
  Scenario Outline:NTS-4978:E2EUI-2706: MIS - Datetime filters for "before or on" dates do not bring back all records for the specified date
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects a value "<value>" from the "plater_samples-search-col" column drop-down
    Then the user sees the values in the search value "plater_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1004 Clinic Sample Type            |
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the plater-samples date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage       | section        | NoOfSearchField | value                            | operator     | date       |
      | Plater Samples | plater_samples | 3               | gel1005 Sample Received Datetime | before or on | 21-02-2020 |

  @NTS-5021
   ## @E2EUI-2231
  Scenario Outline:NTS-5021:E2EUI-2231: MIS: Fields not populating in Plater Samples
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "plater_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the plater-samples date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "gel1005__laboratory_sample_id" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage       | value                            | operator     | date       | ColumnHeader                 |
      | Plater Samples | gel1005 Sample Received Datetime | before or on | 21-02-2020 | gel1005 Laboratory Sample ID |

  @NTS-5030
    #@E2EUI-2331
  Scenario Outline:NTS-5030:E2EUI-2331: Plater Sample Report UI improvement
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "plater_samples-search-col" column drop-down
    Then the user sees the values in the search value "plater_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1004 Clinic Sample Type            |
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    And the user selects a search operator "<operator>" from the "plater_samples-search-operator" operator drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    And search results are displayed in table format with display options button
    And the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage       | value | operator | ColumnHeader                                                      |
      | Plater Samples | GLH   | is       | gel1001 Patient NGIS ID,gel1004 GMC Rack ID,gel1004 GMC Rack Well |
