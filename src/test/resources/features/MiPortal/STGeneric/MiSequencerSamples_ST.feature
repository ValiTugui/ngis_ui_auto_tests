@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature:  MIPORTAL ST -  Sequencer Samples

   @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the sequencer samples search column dropdown
    And the user selects is as the sequencer samples search operator dropdown
    And the user sees the below values in the sequencer samples search value drop-down menu
      | East Mids and East of England |
      | London North                  |
      | London South                  |
      | North West                    |
      | South West                    |
      | Wessex & West Midlands        |
      | Yorkshire & North East        |

     And the user selects is one of as the sequencer samples search operator dropdown
     And the user sees the below values in the sequencer samples search value drop-down menu
       | East Mids and East of England |
       | London North                  |
       | London South                  |
       | North West                    |
       | South West                    |
       | Wessex & West Midlands        |
       | Yorkshire & North East        |
     And the selected search option is reset after test

    Examples:
      | mi_stage          |
      | Sequencer Samples |

  @NTS-5051
   # @E2EUI-2321
  Scenario: NTS-5051:E2EUI-2321: Add "glh" and "ordering_entity" as fields which are returned from the miportalplateview API and are also fields which can be used as filters - Sequencer samples
    When the user selects GLH as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search column drop-down menu
      | GLH             |
      | Ordering Entity |


  @NTS-5032 @MI-LOGOUT
     #@E2EUI-2332
  Scenario: NTS-5032:E2EUI-2332: Sequencer Sample Report UI improvement
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

  @NTS-5186 @MI-LOGOUT
    #@E2EUI-2256
  Scenario Outline: NTS-5186:E2EUI-2256: Plate Date Of Dispatch field should be displayed as in 'dd/mm/yyyy' format
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects GEL1009 Plate Date of Dispatch as the sequencer samples search column dropdown
    And the user selects before or on as the sequencer samples search operator dropdown
    And the user enters 10 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    And the user verify the date format displayed in sequencer samples result table under column GEL1009 Plate Date of Dispatch
    And the selected search option is reset after test

    Examples:
      | mi_stage          |
      | Sequencer Samples |

  @NTS-5669 @MI-LOGOUT
  Scenario Outline: Remove unused fields in Sequencer Samples Report
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects GEL1009 Plate Date of Dispatch as the sequencer samples search column dropdown
    And the user selects before or on as the sequencer samples search operator dropdown
    And the user enters 10 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks on the button "Show all"
    And the user sees the fields are not displayed under the "Show" section
      | HeaderColumnOrderingList |
      | gel1008_gel1009_id      |
      | gel1008_gel1010_error_msgs |
    And the user closes the modal content by clicking on the reset-button
    Examples:
      | mi_stage |
      |Sequencer Samples|

