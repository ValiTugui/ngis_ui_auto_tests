@MIPORTAL
@SYSTEM_TEST

Feature:  MIPORTAL ST -  Sequencer Samples

  Background:
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <value> as the sequencer samples search column dropdown
    And the user selects <operator> as the sequencer samples search operator dropdown
    And the user sees the below values in the sequencer samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

    Examples:
      | mi_stage          | value | operator  |
      | Sequencer Samples | GLH   | is        |
      | Sequencer Samples | GLH   | is one of |

  @NTS-5051
   # @E2EUI-2321
  Scenario Outline:NTS-5051:E2EUI-2321: Add "glh" and "ordering_entity" as fields which are returned from the miportalplateview API and are also fields which can be used as filters - Sequencer samples
    When the user should be able to see sample processing menu is displayed
    And the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search column drop-down menu
      | GLH             |
      | Ordering Entity |

    Examples:
      | mi_stage          |
      | Sequencer Samples |


  @NTS-5032
     #@E2EUI-2332
  Scenario Outline:NTS-5032:E2EUI-2332: Sequencer Sample Report UI improvement
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the sequencer samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | GEL1009 Plate Barcode                 |
      | GEL1010 Illumina QC Status            |
      | GEL1010 Illumina Sample Concentration |
      | GEL1009 Patient ID                    |
      | GEL1009 Plate Date of Dispatch        |
    And the user selects Referral ID as the sequencer samples search column dropdown
    Then the user selects is exactly as the sequencer samples search operator dropdown
    And the selected search option is reset after test

    Examples:
      | mi_stage          |
      | Sequencer Samples |

  @NTS-5186 @failed
    #@E2EUI-2256
  Scenario Outline:NTS-5186:E2EUI-2256: Plate Date Of Dispatch field should be displayed as in 'dd/mm/yyyy' format
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GEL1009 Plate Date of Dispatch as the sequencer samples search column dropdown
    And the user selects before or on as the sequencer samples search operator dropdown
    And the user enters a date "<noOfDays>" days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user verify the date format displayed in sequencer samples result table under column GEL1009 Plate Date of Dispatch
    And the selected search option is reset after test

    Examples:
      | mi_stage          | noOfDays |
      | Sequencer Samples | 10       |