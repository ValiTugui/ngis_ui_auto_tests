@Sample_Failures_System_Testing
@MIPORTAL_ST
@SYSTEM_TEST
Feature: MIPORTAL ST - Sample Failures - 1


  @NTS-6850 @MI-LOGOUT
  Scenario Outline: User is able to navigate to Sample Failures page and verify the Report guidance link and the GLH drop-down values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see sample failures report menu is displayed
    And the user navigates to the "<mi_stage>" stage in mi-portal
    Then the user sees a header as Sample Failures Report on "<header>" stage
    And the user sees a link <report_guidance> under the Sample Failures Report header
    And the user clicks the "<report_guidance>" link to open the document in a new tab having "extge" in the url
    And the user sees the below values in the GLH search column drop-down menu on Sample Failures section
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage        | header                 | report_guidance |
      | Sample Failures | Sample Failures Report | Report Guidance |


  @NTS-6850 @MI-LOGOUT
  Scenario Outline: Select All and Deselect All button functionality validation in GLH dropdown in Sample failures page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user should be able to see sample failures report menu is displayed
    And the user navigates to the "<mi_stage>" stage in mi-portal
    Then the user sees a header as Sample Failures Report on "<header>" stage
    And the user selects on Sample Failures GLH drop-down
    And the user click on Select All button for Sample Failures GLH drop-down
    And the user should see all the values should select
    And the user click on Deselect All button for Sample Failures GLH drop-down
    And the user should see all the values should Deselect
    And the user click on Select All button for Sample Failures GLH drop-down
    And the user click on Apply Filters button on Sample Failures Page
    Then the filter results displays the elememts - PaginationEntry, Last Updated, Showing entries, Result Row Header and Download Data

    Examples:
      | mi_stage        | header                 |
      | Sample Failures | Sample Failures Report |


  @NTS-6850 @MI-LOGOUT
  Scenario Outline: Select All and Deselect All button functionality and check box functionality validation in Sample failures page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user should be able to see sample failures report menu is displayed
    And the user navigates to the "<mi_stage>" stage in mi-portal
    Then the user sees a header as Sample Failures Report on "<header>" stage
    And the user selects <glh_name> as the Sample Failures GLH drop-down menu
    And the user selects on Sample Failures Programme drop-down
    And the user click on Select All button from Sample failures Programme drop-down menu
    And the user should see all the values should select
    And the user click on Deselect All button from Sample failures Programme drop-down menu
    And the user should see all the values should Deselect
    And the user selects <Programme_name> as the Sample failures Programme drop-down menu
    And the user selects on Sample Failures Failure Type drop-down
    And the user click on Select All button from Sample failures Failure Type drop-down menu
    And the user should see all the values should select
    And the user click on Deselect All button from Sample failures Failure Type drop-down menu
    And the user should see all the values should Deselect
    And the user selects <Failure_Type> as the sample failures Failure Type drop-down menu
    And the user click on Hide replaced samples check box to deselect by default it is selected
    And the user click on Hide replaced samples check box to select
    And the user click on Over 14 days old only check box to select

    Examples:
      | mi_stage        | header                 | glh_name               | Programme_name | Failure_Type          |
      | Sample Failures | Sample Failures Report | Yorkshire & North East | Rare diseases  | JIRA Complex ID Fails |


  @NTS-6850 @MI-LOGOUT
  Scenario Outline: Validating the data table populated after clicking on apply filters in Sample failures page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user should be able to see sample failures report menu is displayed
    And the user navigates to the "<mi_stage>" stage in mi-portal
    Then the user sees a header as Sample Failures Report on "<header>" stage
    And the user selects on Sample Failures GLH drop-down
    And the user click on Select All button for Sample Failures GLH drop-down
    And the user selects on Sample Failures Programme drop-down
    And the user click on Select All button from Sample failures Programme drop-down menu
    And the user selects on Sample Failures Failure Type drop-down
    And the user click on Select All button from Sample failures Failure Type drop-down menu
    And the user click on Over 14 days old only check box to select
    And the user click on Apply Filters button on Sample Failures Page
    Then the filter results displays the elememts - PaginationEntry, Last Updated, Showing entries, Result Row Header and Download Data

    Examples:
      | mi_stage        | header                 |
      | Sample Failures | Sample Failures Report |

  @NTS-6850 @MI-LOGOUT
  Scenario Outline: Validating the pagination drop-down and Data table columns names in Sample failures page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user should be able to see sample failures report menu is displayed
    And the user navigates to the "<mi_stage>" stage in mi-portal
    Then the user sees a header as Sample Failures Report on "<header>" stage
    And the user selects on Sample Failures GLH drop-down
    And the user click on Select All button for Sample Failures GLH drop-down
    And the user click on Over 14 days old only check box to select
    And the user click on Apply Filters button on Sample Failures Page
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    And the search result table should display based on the pagination value selected
      | Pagination | Expected Rows |
      | 50         | 50            |
      | 25         | 25            |
      | 10         | 10            |
      | 100        | 100           |
    And the user sees all the table header values in the result table as follows
      | Sample Well                        |
      | Dispatched Sample LSID             |
      | Patient NGIS ID                    |
      | Referral NGIS ID                   |
      | Sample Failure Type                |
      | Description                        |
      | Replacement Dispatched Sample LSID |
      | Arrival At Plating GLH             |
      | Fail Date                          |
      | GLH                                |
      | Programme                          |
    And the user should be able to download the filtered Sample failures report

    Examples:
      | mi_stage        | header                 |
      | Sample Failures | Sample Failures Report |
