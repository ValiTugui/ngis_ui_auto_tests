@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST
@DQ_Report
Feature: MIPORTAL ST - Clinical Data Quality - 1


  @MiPortalClinicalDataQuality_1
  Scenario Outline: User is able to navigate to Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <report_guidance> under the Clinical Data Quality Report header
    Then the user sees the below values in the GLH search column drop-down menu
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


