@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - Order_Tracking

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5060
    #@E2EUI-1851 @Scenario2
  Scenario Outline: NTS-5060:E2EUI-1851:Scenario2- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the user should be able to see the non-empty data cell in the gel1001 GLH Sample Dispatch Date column of file submission search result table
    And the user should be able to see the non-empty data cell in the gel1001 Dispatched Sample State column of file submission search result table
    And the selected search option is reset after test
    ##Note:Ensure test data present in testdata folder
    Examples:
      | mi_stage       | section        | NoOfSearchField |
      | Order Tracking | order_tracking | 3               |

  @NTS-5046
   # @E2EUI-2337 #Drag and Drop
  Scenario Outline:NTS-5046:E2EUI-2337: A user should be able to see the column header "gel1001__clinical_indication_test_type_id" in the results section of Order Tracking when filter Clinical Indication Test Type is selected.
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects Test Type as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects <value> as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "GEL1001 Clinical Indication Test Type ID" from the section "Hide" to "Show" section
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList                  |
      | GEL1001 Clinical Indication Test Type ID |
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the order tracking search result table column GEL1001 Clinical Indication Test Type ID is displayed with data non-empty-data
    And the selected search option is reset after test

    Examples:
      | mi_stage       | operator  | value                                                                                              |
      | Order Tracking | is        | Epilepsy - early onset or syndromic WGS                  |
      | Order Tracking | is one of | Craniosynostosis WGS,Epilepsy - early onset or syndromic WGS |

  @NTS-5029
    #@E2EUI-2438 ##DRag and Drop
  Scenario Outline: NTS-5029:E2EUI-2438:Drag and drop clinical_indication code on Sample View
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "order_tracking" page
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "Test Code" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the order tracking search result table column Test Code is displayed with data non-empty-data

    Examples:
      | mi_stage       | NoOfSearchField |
      | Order Tracking | 3               |

  @NTS-5056
    #@E2EUI-2234
  Scenario Outline: NTS-5056:E2EUI-2234:The plate id and well id fields in Order tracking should be different for each files
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the order tracking search result table column GEL1008 Plate ID is displayed with data non-empty-data
    And the order tracking search result table column GEL1008 Well ID is displayed with data non-empty-data
    Then the user should be able to see the different values between columns GEL1008 Plate ID and GEL1008 Well ID
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Order Tracking |

  @NTS-5028
    #@E2EUI-1929
  Scenario Outline: NTS-5028:E2EUI-1929: Status field should show referral status in Order Tracking
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects GLHName as the order tracking search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    And the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the order tracking search result table column Status is displayed with data non-empty-data
    Then the selected search option is reset after test

    Examples:
      | mi_stage       | operator  |
      | Order Tracking | is        |


  @NTS-5647 @MI-LOGOUT
    #@E2EUI-2900
  Scenario Outline: Verifying the data under the Test Name and Clinical Indication Name columns In Mi portal Order tracking section when select Test type as search operator
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    When the user selects Test Type as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects <Test_type_name> as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    And the user click on the Search button
    And the order tracking search result table column <column_1> is displayed with data <data>
    And the order tracking search result table column <column_2> is displayed with data <data>
    And the selected search option is reset after test

    Examples:
      | mi_stage       | operator | column_1  | column_2                 | data           | Test_type_name                                                  |
      | Order Tracking | isoneof  | Test Name | Clinical Indication Name | non-empty-data | Cerebral malformations WGS, Hereditary ataxia - adult onset WGS |
      | Order Tracking | is       | Test Name | Clinical Indication Name | non-empty-data | Hereditary ataxia - adult onset WGS                             |


