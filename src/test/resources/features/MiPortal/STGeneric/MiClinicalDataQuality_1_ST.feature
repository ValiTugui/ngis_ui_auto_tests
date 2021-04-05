@DQ_Report_System_Testing

  ##This feature has been moved to @DQ_Report_System_Testing tag as this will be maintained by DDF Squad
Feature: MIPORTAL ST - Clinical Data Quality - 1

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: User is able to navigate to Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    #This latest change is applied for Nana release only in e2elatest
    Given the user is able to see data quality menu is displayed
#    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <report_guidance> under the Clinical Data Quality Report header
    And the user sees the below values in the GLH search column drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu

    Examples:
      | mi_stage              | header                       | report_guidance | glh_name     |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance | London North |