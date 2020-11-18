@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST
@DQ_Report
Feature: MIPORTAL ST - Clinical Data Quality - 3

  @NTS-6097 @MI-LOGOUT
  Scenario Outline:NTS-6097:Scenario-1: User is able to navigate to report guidance link
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <report_guidance> under the Clinical Data Quality Report header
    And the user clicks the "<report_guidance>" link to open the document in a new tab having "huddle" url

    Examples:
      | mi_stage              | header                       | report_guidance |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance |

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: Pagination drop-down options shown in search result table
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules
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

    Examples:
      | mi_stage              | header                       |  glh_name               |
      | Clinical Data Quality | Clinical Data Quality Report |  Yorkshire & North East |


    @NTS-6097 @MI-LOGOUT
  Scenario Outline:NTS-6097:Scenario-2:User is able to verify the data present in each tabs for every GLH
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    And the user sees the column headers of each of the tables with data present in the tabs
      | Summary                 | Number of failures,Organisation,Rule ID,Programme,Level,Rule description,Resolution guidance,Includes data from csv,Delays the test order                                                                                                                                                                                                                        | Yes |
      | Full Output             | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Failed rule description,Resolution guidance,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC)              | Yes |
      | Streamline Output       | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Failed rule description,Resolution guidance,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC)              | Yes |
      | Genomic Identity Output | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Rule description,Resolution guidance,Failure description,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC) | No  |
      | Appendix - all rules    | Rule ID,Programme,Level,Full output/Streamline Output,Includes data from csv,Delays the test order,Failed rule description,Resolution guidance                                                                                                                                                                                                                   | Yes |

    And the user clicks the "<linkName>" link in the "Full Output" tab which opens "ngis" url
    And the user clicks the "<linkName>" link in the "Streamline Output" tab which opens "ngis" url
    And the user should be able to download the filtered DQ report
    Examples:
      | mi_stage              | header                       | glh_name                      | linkName        |
      | Clinical Data Quality | Clinical Data Quality Report | East Mids and East of England | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | London North                  | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | London South                  | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | North West                    | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | Wessex & West Midlands        | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | Yorkshire & North East        | Test order link |


    @NTS-6097 @MI-LOGOUT
  Scenario Outline:NTS-6097:Scenario-2:User is able to verify the data present in each tabs for the GLH 'South West'
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    And the user sees the column headers of each of the tables with data present in the tabs
      | Summary                 | Number of failures,Organisation,Rule ID,Programme,Level,Rule description,Resolution guidance,Includes data from csv,Delays the test order                                                                                                                                                                                                                        | Yes |
      | Full Output             | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Failed rule description,Resolution guidance,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC)              | Yes |
      | Streamline Output       | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Failed rule description,Resolution guidance,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC)              | Yes |
      | Genomic Identity Output | Organisation,Test order link,Referral ID,Patient's NHS Number,Patient's date of birth,Patient's first name,Patient's surname,Patient's gender,Failed rule ID,Programme,Level,Rule description,Resolution guidance,Failure description,Includes data from csv,Delays the test order,Failed rule datetime,Test order last amended by,Test order last amended (UTC) | Yes  |
      | Appendix - all rules    | Rule ID,Programme,Level,Full output/Streamline Output,Includes data from csv,Delays the test order,Failed rule description,Resolution guidance                                                                                                                                                                                                                   | Yes |

    And the user clicks the "<linkName>" link in the "Full Output" tab which opens "ngis" url
    And the user clicks the "<linkName>" link in the "Streamline Output" tab which opens "ngis" url
    And the user clicks the "<linkName>" link in the "Genomic Identity Output" tab which opens "ngis" url
    And the user should be able to download the filtered DQ report
    Examples:
      | mi_stage              | header                       | glh_name                      | linkName        |
      | Clinical Data Quality | Clinical Data Quality Report | South West                    | Test order link |