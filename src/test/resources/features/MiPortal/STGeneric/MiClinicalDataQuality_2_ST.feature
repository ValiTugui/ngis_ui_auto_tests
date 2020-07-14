@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST - Clinical Data Quality - 2

  @MiPortalClinicalDataQuality_2
  Scenario Outline: Pagination drop-down options shown in search result table in Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <Report_Guidance> under the Clinical Data Quality Report header
    Then the user sees the below values in the GLH search column drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    And the user selects South West as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Deselect All button by default all the ordering entites should select
    And the user click on Select All button

    Examples:
      | mi_stage              | header                       | Report_Guidance |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance |



  @MiPortalClinicalDataQuality_3
  Scenario Outline: Pagination drop-down options shown in search result table in Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <Report_Guidance> under the Clinical Data Quality Report header
    Then the user sees the below values in the GLH search column drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    And the user selects North West as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules


    Examples:
      | mi_stage              | header                       | Report_Guidance |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance |


