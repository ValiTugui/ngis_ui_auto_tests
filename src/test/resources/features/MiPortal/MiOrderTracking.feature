@MIPORTAL

Feature: MIPORTAL: Order_Tracking (E2EUI-2337,2438,2398,2234,1929)

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user sees the values in the search value "order_tracking-search-value" drop-down menu
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
      | Order Tracking | GLH   | is        |
      | Order Tracking | GLH   | is one of |

  @NTS-5046
   # @E2EUI-2337
  Scenario Outline:NTS-5046:E2EUI-2337: A user should be able to see the column header "gel1001__clinical_indication_test_type_id" in the results section of Order Tracking when filter Clinical Indication Test Type is selected.
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value>" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "gel1001__clinical_indication_test_type_id" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList                  |
      | gel1001__clinical_indication_test_type_id |
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage       | filter                        | operator  | value                                                                                              | ColumnHeader                             |
      | Order Tracking | Clinical Indication Test Type | is        | Diabetes - neonatal onset WGS: Neonatal diabetes                                                   | gel1001 Clinical Indication Test Type ID |
      | Order Tracking | Clinical Indication Test Type | is one of | Diabetes - neonatal onset WGS: Neonatal diabetes,Cerebral malformations WGS: Cerebral malformation | gel1001 Clinical Indication Test Type ID |

  @NTS-5029
    #@E2EUI-2438
  Scenario Outline: NTS-5029:E2EUI-2438:Drag and drop clinical_indication code on Sample View
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "order_tracking" page
    And the user selects a value "<value>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "test_code" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the table column "<ColumnHeader>" is displayed with data

    Examples:
      | mi_stage       | NoOfSearchField | value | operator | ColumnHeader |
      | Order Tracking | 3               | GLH   | is       | Test Code    |

  @NTS-5052
    #@E2EUI-2398
  Scenario Outline:NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value>" from the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       | filter                        | operator  | value                                                                                            |
      | Order Tracking | Clinical Indication Test Type | is one of | Cerebral malformations WGS: Cerebral malformation,Cystic renal disease WGS: Cystic renal disease |
      | Order Tracking | Clinical Indication Test Type | is        | Arthrogryposis - broad panel WGS: Arthrogryposis                                                 |

  @NTS-5056
    #@E2EUI-2234
  Scenario Outline: NTS-5056:E2EUI-2234:The plate id and well id fields in Order tracking should be different for each files
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the table column "<ColumnHeader>" is displayed with data
    Then the user should be able to see the different values between columns "<ColumnName1>" and "<ColumnName2>"
    And the selected search option is reset after test

    Examples:
      | mi_stage       | value | operator | ColumnHeader                     | ColumnName1      | ColumnName2     |
      | Order Tracking | GLH   | is       | gel1008 Plate ID,gel1008 Well ID | gel1008 Plate ID | gel1008 Well ID |

  @NTS-5028
    #@E2EUI-1929
  Scenario Outline: NTS-5028:E2EUI-1929: Status field should show referral status in Order Tracking
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<filter_value1>" from the "order_tracking-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "order_tracking-search-operator" operator drop-down
    And the user selects a value "<value1>" from the "order_tracking-search-value" value drop-down
    And the user sees the selected "<value1>" in the "order_tracking-search-value" value drop-down
    And the user clicks on Add criteria button
    And the user click on the Search button
    And the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    Then the table column "Status" is displayed with data
    Then the selected search option is reset after test

    Examples:
      | mi_stage       | filter_value1 | operator  | value1                    |
      | Order Tracking | GLH           | is        | London North              |
      | Order Tracking | GLH           | is one of | London North,London South |
