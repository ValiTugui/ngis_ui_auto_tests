@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT -Glh Samples

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5036
   # @E2EUI-2336
  Scenario Outline: NTS-5036:E2EUI-2336: New data columns in the result table of GLH Samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects GLHName as the glh search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the glh search result table column gel1001 Referral ID is displayed with data non-empty-data
    And the glh search result table column gel1001 Dispatched Sample Type is displayed with data non-empty-data
    And the glh search result table column gel1001 GLH Sample Consignment Number is displayed with data non-empty-data
    And the selected search option is reset after test
    ##Note : Ensure test data file present in testdata folder
    Examples:
      | mi_stage    |
      | GLH Samples |
 ###One filter remaining to include in the example,as from E2EUI-2336 The filter "gel1001_dispatched_sample_id" is not present in the search result table.

  @NTS-5049
    #@E2EUI-1162
  Scenario Outline: NTS-5049:E2EUI-1162: MI Dashboard | Filter
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the glh search column dropdown
    And the user selects is one of as the glh search operator dropdown
    And the user selects GLHName as the glh search value dropdown
    And the user clicks on Add criteria button
    And the user click on the reset button
    And the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects GLHName as the glh search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the glh search result table column gel1001 Referral ID is displayed with data non-empty-data
    And the glh search result table column gel1001 Dispatched Sample Type is displayed with data non-empty-data
    And the glh search result table column gel1001 GLH Sample Consignment Number is displayed with data non-empty-data
    Then the selected search option is reset after test

    Examples:
      | mi_stage    |
      | GLH Samples |

  @NTS-5033
    #@E2EUI-2579
  Scenario Outline:NTS-5033:E2EUI-2579: MIS - Remove "is one of" option for Gel 1004 GLH Sample Consignment Number Filter in GLH Samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects gel1004 GLH Sample Consignment Number as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user enters ConsignmentNumber in the glh search value box
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user should be able to download the filtered glh samples
    And the selected search option is reset after test

    Examples:
      | mi_stage    |
      | GLH Samples |