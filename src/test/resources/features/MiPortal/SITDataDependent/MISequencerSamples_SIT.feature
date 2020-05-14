@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL: Sequencer Samples SIT (E2EUI-2256,2233)

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |


  @NTS-5187
    #@E2EUI-2233
  Scenario Outline:NTS-5187:E2EUI-2233: Fields not poulating in Sequencer samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "sequencer_samples-search-value" column drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
#    Then search results are displayed in the search results
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders    |
      | gel1010 Filename |
#    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage          | value                      | operator | dropdown |
      | Sequencer Samples | GEL1010 Illumina QC Status | is       | Pass     |
