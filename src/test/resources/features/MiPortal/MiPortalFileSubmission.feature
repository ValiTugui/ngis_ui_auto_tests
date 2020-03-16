@MIPORTAL
@SYSTEM_TEST

Feature: This is mi-portal fileSubmission

  @mi-portal1 @NTS_todo
  Scenario Outline: mi-portal page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects a value "<value>" from the file-submission search column drop-down
    And the user selects a search operator "<operator>" from the file-submission search operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the user is able to see the field values - Filenames "<filename>", Status "<Status>", ErrorMessage "<ErrorMessage>" and WarningMessage "<WarningMessage>"

    Examples:
      | mi_stage | value   | operator | date  | filename                                            | Status | ErrorMessage | WarningMessage |
      |          | Created | equals   | today | ngis_gel_to_bio_sample_sent_wwm_20200226_103815.csv | valid  |              |                |




