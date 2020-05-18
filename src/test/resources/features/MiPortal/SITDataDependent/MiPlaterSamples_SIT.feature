@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - Plater Samples


  @NTS-5030
    #@E2EUI-2331
  Scenario Outline:NTS-5030:E2EUI-2331: Plater Sample Report UI improvement
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the plater samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1004 Clinic Sample Type            |
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user selects GLHName as the plater samples search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And search results are displayed in table format with display options button
    Then the plater samples search result table column gel1001 Patient NGIS ID is displayed with data non-empty-data
    And the plater samples search result table column gel1004 GMC Rack ID is displayed with data non-empty-data
    And the plater samples search result table column gel1004 GMC Rack Well is displayed with data non-empty-data
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Plater Samples |