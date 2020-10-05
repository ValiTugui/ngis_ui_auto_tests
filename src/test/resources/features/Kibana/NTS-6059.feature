@SYSTEM_TEST

Feature: Verify user history details for a referral using Kibana Logs

  @NTS-6059
  Scenario Outline: NTS-6059:Verify user history details for a referral using Kibana Logs.
    Given a web browser is at the kibana app search page
      | KIBANA_URL |
    Then the user search for referral id in search box "<refID>"
    Then the user clicks on previous time window and selects the date link
      | This Week |
#    Then the user clicks on "<ButtonName>" button


    Examples:
      | ButtonName | refID        |
      | Refresh    | r20934394794 |
