@SYSTEM_TEST

Feature: Verify user history details for a referral using Kibana Logs

  @NTS-6059
  Scenario Outline: NTS-6059:Verify user history details for a referral using Kibana Logs.
    Given a web browser is at the kibana app search page
      | KIBANA_URL |
    When the user search for referral id in search box "<refID>" and search for duration "<searchDuration>"
    And the user select the filter fields "<filterFields>"
    And the user clicks on "<ButtonName>" button
    Then the user should see the search results loaded with the filter fields "<filterFields>"
    ##Scenario 2
    When the user saves the report
    And the user share the report as CSV
    Then the user should be able to generate the CSV report
    ##searchDuration can be like Last-15-minutes format also
    Examples:
      | ButtonName | refID        | searchDuration | filterFields                                           |
      | Refresh    | r20934394794 | This month     | jwt_claims.name,request.querystring.human_readable_id |
