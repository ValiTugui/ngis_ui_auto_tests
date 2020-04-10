@MIPORTAL

Feature: MIPORTAL:  Sequencer Samples (E2EUI-2321,2256,2233,2332)

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-4865
  Scenario Outline:NTS-4865: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user sees the values in the search value "sequencer_samples-search-value" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage          | value | operator  |
      | Sequencer Samples | GLH   | is        |
      | Sequencer Samples | GLH   | is one of |

  @NTS-5051
   # @E2EUI-2321
  Scenario Outline:NTS-5051: Add "glh" and "ordering_entity" as fields which are returned from the miportalplateview API and are also fields which can be used as filters - Sequencer samples
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    Then the user sees the values in the search value "sequencer_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |

    Examples:
      | mi_stage          | value |
      | Sequencer Samples | GLH   |

  @NTS-4917
    #@E2EUI-2256
  Scenario Outline:NTS-4917: Plate Date Of Dispatch field should be displayed as in 'dd/mm/yyyy' format
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed for the file-submission search
    And the user verify the date format displayed
    And the selected search option is reset after test

    Examples:
      | mi_stage          | value                          | operator     | date       |
      | Sequencer Samples | gel1009 Plate Date of Dispatch | before or on | 09-03-2020 |

  @NTS-4919
    #@E2EUI-2233
  Scenario Outline:NTS-4919: Fields not poulating in Sequencer samples
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the user selects a value "<dropdown>" from the "sequencer_samples-search-col" column drop-down
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then search results are displayed in the search results
    When the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
    And the user save the changes on modal content by clicking Save and Close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders    |
      | gel1010 Filename |
    Then the table column "<ColumnHeader>" is displayed with data
    And the selected search option is reset after test

    Examples:
      | mi_stage          | value                      | operator | dropdown | ColumnHeader     |
      | Sequencer Samples | gel1010 Illumina QC Status | is       | Pass     | gel1010 Filename |

   @NTS-5032
     #@E2EUI-2332
  Scenario Outline:NTS-5032:E2EUI-2332 Sequencer Sample Report UI improvement
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "sequencer_samples-search-col" column drop-down
    And the user sees the values in the search value "sequencer_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1009 Plate Barcode                 |
      | gel1010 Illumina QC Status            |
      | gel1010 Illumina Sample Concentration |
      | gel1009 Patient ID                    |
      | gel1009 Plate Date of Dispatch        |
    And the user selects a search operator "<operator>" from the "sequencer_samples-search-operator" operator drop-down
    And the selected search option is reset after test

    Examples:
      | mi_stage          | value       | operator   |
      | Sequencer Samples | Referral ID | is exactly |
