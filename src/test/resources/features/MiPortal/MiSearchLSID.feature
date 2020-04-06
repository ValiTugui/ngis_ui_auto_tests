@MIPORTAL

Feature: This is mi-portal MiSearchLSID


  @NTS_todo
  Scenario Outline: mi-portal page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the mi-portal "<mi_stage>" stage is selected
#    And the user sees a search box header section for "<mi_stage>" page


    Examples:
      | mi_stage                        | value   | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
      | samplemsgstate_sampleprocessing | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |



