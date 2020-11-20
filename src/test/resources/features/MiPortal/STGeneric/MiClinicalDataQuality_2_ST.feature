@DQ_Report_System_Testing

  ##This feature has been moved to @DQ_Report_System_Testing tag as this will be maintained by DDF Squad
Feature: MIPORTAL ST - Clinical Data Quality - 2

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: Select All and Deselect All button functionality validation in Ordering Entity dropdown
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <report_guidance> under the Clinical Data Quality Report header
    When the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Deselect All button by default all the ordering entities should select
    Then the user click on Select All button

    Examples:
      | mi_stage              | header                       | report_guidance | glh_name   |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance | South West |

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: Validating Ordering entities deselect and select actions
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    #This latest change is applied for Nana release only in e2e latest
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Deselect All button by default all the ordering entities should select
    Then the user able to see all the ordering entities should deselect
    And the user click on Select All button
    Then the user able to see all the ordering entities should select
    Examples:
      | mi_stage              | header                       | glh_name                      |
      | Clinical Data Quality | Clinical Data Quality Report | East Mids and East of England |

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: Tabs displayed in search result table in Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    #This latest change is applied for Nana release only in e2e latest
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules
    Examples:
      | mi_stage              | header                       | glh_name               |
      | Clinical Data Quality | Clinical Data Quality Report | Yorkshire & North East |

