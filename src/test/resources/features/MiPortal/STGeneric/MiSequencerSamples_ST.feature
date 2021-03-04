@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST
@Sequencer_samples
Feature:  MIPORTAL ST -  Sequencer Samples

  @NTS-5190 @MI-LOGOUT
    # NTS-5051 and NTS-5032 is covered here
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of Sequencer Samples search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
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
      | LSID            |
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

    And the user selects Ordering Entity as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is        |
      | is one of |

    And the user selects Referral ID as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is exactly       |
      | is one of |

    And the user selects Patient NGIS ID as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is exactly       |
      | is one of |

    And the user selects GEL1009 Plate Barcode as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is exactly       |
      | is one of |

    And the user selects GEL1010 Illumina QC Status as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is        |
    And the user sees the below values in the sequencer samples search value drop-down menu
    |Pass|
    |Fail|

    And the user selects GEL1010 Illumina Sample Concentration as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      |equals|
      |is less than or equal to|
      |is greater than or equal to|

    And the user selects GEL1009 Patient ID as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is exactly       |
      | is one of |

    And the user selects GEL1009 Plate Date of Dispatch as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      |equals|
      |before or on|
      |on or after|

    And the user selects LSID as the sequencer samples search column dropdown
    And the user sees the below values in the sequencer samples search operator dropdown
      | is exactly       |
      | is one of |


    And the selected search option is reset after test

    Examples:
      | mi_stage          |
      | Sequencer Samples |


  @NTS-5186 @MI-LOGOUT
    #@E2EUI-2256
  Scenario Outline: NTS-5186:E2EUI-2256: Plate Date Of Dispatch field should be displayed as in 'dd/mm/yyyy' format and verify the columns/fields present in Sequencer Samples report
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
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks on the button "Show all"
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      |GEL1009 Patient ID        |
      |GEL1009 Group ID          |
      |GEL1009 Sample ID         |
      |GEL1009 Plate Barcode     |
      |GEL1009 Well              |
      |GEL1009 Sample Well       |
      |GEL1009 Volume ul         |
      |GEL1009 Concentration ng ul|
      |GEL1009 Tissue Source      |
      | GEL1009 Is Longitudinal|
      |GEL1009 Matched Sample ID|
      |GEL1009 Matched Sample Type|
      |GEL1009 Sample Type|
      |GEL1009 Plate Date of Dispatch|
      |GEL1009 Plate Consignment Number|
      |GEL1009 Source|
      |GEL1010 Sample Well|
      |GEL1010 Illumina QC Status|
      |GEL1010 Illumina Sample Concentration|
      |GEL1010 DNA Amount|
      |GEL1009 Species   |
      |GEL1009 Gender    |
      |GEL1009 OD 260 280|
      |GEL1009 Extraction Method|
      |GEL1009 Ethnicity        |
      |GEL1009 Parent 1 ID      |
      |GEL1009 Parent 2 ID|
      |GEL1009 Replicate ID|
      |GEL1009 Cancer Sample Y N|
      |GEL1009 Comment          |
      |GEL1009 Coverage         |
      |GEL1009 Due Date         |
      |GEL1009 Analysis         |
      |GEL1009 SO               |
      |GEL1009 Sample Prep Workflow|
      |GEL1009 Instrument Type     |
      |GEL1009 Delta CQ            |
      |GEL1009 Priority            |
      |GEL1009 Type of Case        |
      |GEL1009 Masked PID          |
      |GEL1009 Program             |
      |GEL1009 Delivery Type       |
      |GEL1009 Molecule            |
      |GEL1009 Tissue Prep         |
      |GEL1009 Low DNA Bool        |
      |GEL1009 Filename            |
      |GEL1009 Created             |
      |GEL1010 Illumina Sequence Gender|
      |GEL1010 Illumina Delta CQ       |
      |GEL1010 Filename                |
      |GEL1010 Created                 |
      |GEL1010 Status                  |

    And the user save the changes on modal content by clicking Save and Close button
    And the selected search option is reset after test

    Examples:
      | mi_stage          |
      | Sequencer Samples |

