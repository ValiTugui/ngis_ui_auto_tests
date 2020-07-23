@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST
@DQ_Report
Feature: MIPORTAL ST - Clinical Data Quality - 2

  @MiPortalClinicalDataQuality_2
  Scenario Outline: Select All and Deselect All button validation in Ordering Entity dropdown
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <Report_Guidance> under the Clinical Data Quality Report header
    And the user selects Wessex & West Midlands as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Deselect All button by default all the ordering entities should select
    And the user click on Select All button

    Examples:
      | mi_stage              | header                       | Report_Guidance |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance |

  @MiPortalClinicalDataQuality_3
  Scenario Outline: Select All and Deselect All buttons validation in Ordering Entity dropdown
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects East Mids and East of England as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Deselect All button by default all the ordering entities should select
    Then the user able to see all the ordering entities should deselect
    And the user click on Select All button
    Then the user able to see all the ordering entities should select
    Examples:
      | mi_stage              | header                       |
      | Clinical Data Quality | Clinical Data Quality Report |

  @MiPortalClinicalDataQuality_4
  Scenario Outline: Elements shown in search result table in Clinical Data Quality page with data
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects North West as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules
    Examples:
      | mi_stage              | header                       |
      | Clinical Data Quality | Clinical Data Quality Report |

  @MiPortalClinicalDataQuality_5
  Scenario Outline: Validate the values displayed in table column
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io |  |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects "<glh_name>" as the Clinical Dq Filter Glh drop-down menu
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules
    And the user selects "<tab_name>" tab
    And the user sees the column "<column_1>" is displayed with data "<data_1>"
    And the user sees the column "<column_2>" is displayed with data "<data_2>"

    Examples:
      | mi_stage              | header                       | glh_name     | tab_name                  | column_1                        | data_1         | column_2                | data_2         |
      | Clinical Data Quality | Clinical Data Quality Report | London North | Summary                   | Rule ID                         | non-empty-data | Rule Description        | non-empty-data |
      | Clinical Data Quality | Clinical Data Quality Report | London North | Full Output               | Organisation                    | non-empty-data | Programme               | non-empty-data |
      | Clinical Data Quality | Clinical Data Quality Report | London North | Streamline Output         | Patient's first name            | non-empty-data | Failed rule ID          | non-empty-data |
      | Clinical Data Quality | Clinical Data Quality Report | London North | Genomic Identity Output   | Patient's gender                | non-empty-data | Failure description     | non-empty-data |
      | Clinical Data Quality | Clinical Data Quality Report | London North | Appendix - all rules      | Full output/Streamline Output   | non-empty-data | Failed rule description | non-empty-data |