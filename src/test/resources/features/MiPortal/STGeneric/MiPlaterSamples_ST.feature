@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature:  MIPORTAL ST - Plater Samples

  @NTS-5190 @MI-LOGOUT
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user sees the below values in the plater samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    And the user selects is one of as the plater samples search operator dropdown
    And the user sees the below values in the plater samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Plater Samples |


  @NTS-4978 @MI-LOGOUT
   ## @E2EUI-2706
  Scenario Outline:NTS-4978:E2EUI-2706: MIS - Datetime filters for "before or on" dates do not bring back all records for the specified date
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    When the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    Then the user sees the below values in the plater samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
#      | GEL1004 Clinic Sample Type            |
       ## As part of SALLY release the field/filter "gel1004 Clinic Sample Type" has been removed
      | GEL1004 Disease Area                  |
      | GEL1004 GLH Sample Consignment Number |
      | GEL1004 Laboratory ID                 |
      | GEl1005 Sample Received Datetime      |
    And the user selects gel1005 Sample Received Datetime as the plater samples search column dropdown
    And the user selects before or on as the plater samples search operator dropdown
    And the user enters 5 days before today in the plater sample date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | section        | NoOfSearchField | mi_stage       |
      | plater_samples | 3               | Plater Samples |

  @NTS-5021 @MI-LOGOUT
   ## @E2EUI-2231 ##Drag and Drop  - need to recheck
  Scenario Outline: NTS-5021:E2EUI-2231: MIS: Fields not populating in Plater Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    When the user selects GEL1005 Sample Received Datetime as the plater samples search column dropdown
    And the user selects before or on as the plater samples search operator dropdown
    And the user enters 5 days before today in the plater sample date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user drag the column header "GEL1005 Laboratory Sample ID" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    When the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Plater Samples |


@NTS-6704 @MI-LOGOUT
  Scenario Outline:NTS-6704:Remove GEL1004 Clinical Sample Type from Plater Samples Report
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user can not see the value "gel1004 Clinic Sample Type" under search column dropdown
    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects GLHName as the plater samples search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user sees the fields are not displayed under the "Show" section
      | HeaderColumnOrderingList   |
      | gel1004 Clinic Sample Type |
    And the user closes the modal content by clicking on the reset-button
    Examples:
      | mi_stage       |
      | Plater Samples |