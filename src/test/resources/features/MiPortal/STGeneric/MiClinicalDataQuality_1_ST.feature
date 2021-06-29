@DQ_Report_System_Testing

  @DQ_Report
  ##This feature has been moved to @DQ_Report_System_Testing tag as this will be maintained by DDF Squad
Feature: MIPORTAL ST - Clinical Data Quality - 1

  @NTS-6097 @MI-LOGOUT
  Scenario Outline: Select All and Deselect All button functionality validation in Ordering Entity dropdown

    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
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
      | mi_stage              | header                       | glh_name     |
      | Clinical Data Quality | Clinical Data Quality Report | East Genomic Laboratory Hub |


  @NTS-6861 @MI-LOGOUT
  Scenario Outline: The last updated date is in the correct format on the Clinical Data Quality page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    #This latest change is applied for Nana release only in e2e latest
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the last updated date is displayed in the "d/MM/yyyy HH:mm" format
   # And the last updated date matches date in the DQR schema

    Examples:
      | mi_stage              | header                       | glh_name                          |
      | Clinical Data Quality | Clinical Data Quality Report | South East Genomic Laboratory Hub |


  @NTS-6097 @MI-LOGOUT
  Scenario Outline:NTS-6097:Scenario-1: User is able to navigate to report guidance link and verify the pagination drop down options from the table
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see data quality menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    Then the user sees a header as Clinical Data Quality Report on "<header>" stage
    And the user sees a link <report_guidance> under the Clinical Data Quality Report header
    And the user clicks the "<report_guidance>" link to open the document in a new tab having "nhs" url
    And the user selects <glh_name> as the Clinical Dq Filter Glh drop-down menu
    And the user selects on ordering entity drop-down
    And the user click on Select All button
    And the user click on Apply Filters button
    Then the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules
    #The Scenario- Pagination drop-down options shown in search result table has been merged with this scenario
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
      | mi_stage              | header                       | report_guidance | glh_name                                        |
      | Clinical Data Quality | Clinical Data Quality Report | Report Guidance | North East and Yorkshire Genomic Laboratory Hub |

  @NTS-6097 @MI-LOGOUT
  Scenario Outline:NTS-6097:Scenario-3:User is able to verify the data present in each tabs for every GLH and verify the GLH drop down values
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
    Then the user should be able to download the filtered DQ report

    Examples:
      | mi_stage              | header                       | glh_name                                        | linkName        |
      | Clinical Data Quality | Clinical Data Quality Report | Central and South Genomic Laboratory Hub        | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | East Genomic Laboratory Hub                     | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | North East and Yorkshire Genomic Laboratory Hub | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | North Thames Genomic Laboratory Hub             | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | North West Genomic Laboratory Hub               | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | South East Genomic Laboratory Hub               | Test order link |
      | Clinical Data Quality | Clinical Data Quality Report | South West Genomic Laboratory Hub               | Test order link |

