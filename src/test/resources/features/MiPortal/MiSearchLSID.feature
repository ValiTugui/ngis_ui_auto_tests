@MIPORTAL

Feature: MIPORTAL:  MiSearchLSID (E2EUI-1433)


#  @NTS_todo
#  Scenario Outline: mi-portal page
#    Given a web browser is at the mi-portal home page
#      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
#    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
#    And the user sees a search box header section for "<mi_stage>" page


#    Examples:
#      | mi_stage                        | value   | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
#      | samplemsgstate_sampleprocessing | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |


  @NTS-5179
    #@E2EUI-1433
  Scenario Outline:NTS-5179:E2EUI-1433: MI Dashboard | Search LSIDs
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see sample processing menu is displayed
    Then the user should be able to see the below header sections in Sample Processing
      | HeaderSection     |
      | File Submissions  |
      | Order Tracking    |
      | GLH Samples       |
      | Plater Samples    |
      | Picklists         |
      | Sequencer Samples |
      | New Referrals     |
      | Search LSIDs      |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And  the user inputs the "<LSID>" reference number
    And the user clicks on Find LSID
    #after clicking on find Button ,the data is displayed in image format,not able to identify any validation

    Examples:
      | mi_stage     | LSID       |
      | Search LSIDs | 1371612610 |

