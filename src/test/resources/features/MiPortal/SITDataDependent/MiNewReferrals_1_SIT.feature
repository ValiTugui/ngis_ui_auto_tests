@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - New Referrals 1

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5060
     #@E2EUI-1851 @Scenario1
  Scenario Outline: NTS-5060:E2EUI-1851:Scenario1- Prevent user from hiding all columns in Display Options
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a search box container section for "<mi_stage>" page
    And the user should be able to see "<NoOfSearchField>" search boxes in the "<section>" page
    And the user selects GLH as the new referrals search column dropdown
    And the user selects is as the new referrals search operator dropdown
    And the user selects GLHName as the search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    When the user clicks "Show all" button on the modal-content page
    And the user clicks on save and close button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    Then the user should be able to see the non-empty data cell in the Referral Creation Date column of file submission search result table
    And the user should be able to see the non-empty data cell in the Referral Status column of file submission search result table
    And the selected search option is reset after test
    ##Note: Ensure test data file present in testdata folder
    Examples:
      | mi_stage      | section       | NoOfSearchField |
      | New Referrals | new_referrals | 3               |