@MIPORTAL

Feature: MIPORTAL: Homepage (E2EUI-2328,2772,2329,2335,2704,1433)

#  @NTS_todo
#  Scenario Outline: mi-portal page
#    Given a web browser is at the mi-portal home page
#      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
#    When the user navigates to the mi-portal "<mi_stage>" stage
##    And the mi-portal "<mi_stage>" stage is selected
##    And the user sees a search box header section for "<mi_stage>" page
#    When the user navigates to the mi-portal "<mi_stage>" stage
#    And the user sees a search box container section for "<mi_stage>" page
#
#    Examples:
#      | mi_stage         | value   | operator     | date       | filename                                            | Status  | ErrorMessage | WarningMessage |
#      | file_submissions | Created | before or on | 09-03-2020 | ngis_glh_to_gel_sample_sent_now_20200309_200002.csv | invalid |              |                |

  @NTS-5180
    #@E2EUI-2328
  Scenario Outline: NTS-5180:E2EUI-2328: Feedback Link removed from Mi Portal
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage>" stage
    Then the user validates there is no feedback link on the page

    Examples:
      | mi_stage          |
      | File Submissions  |
      | Order Tracking    |
      | GLH Samples       |
      | Plater Samples    |
      | Picklists         |
      | Sequencer Samples |
      | New Referrals     |

  @NTS-4985
   ## @E2EUI-2772 @E2EUI-2329
  Scenario Outline: NTS-4985:E2EUI-2772,2329:Standardized MI portal name
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage1>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage2>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage3>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage4>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage5>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage6>" stage
    Then the user sees the "MI Portal" logo and environment name at their position
    When the user navigates to the mi-portal "<mi_stage7>" stage
    Then the user sees the "MI Portal" logo and environment name at their position

    Examples:
      | mi_stage1        | mi_stage2      | mi_stage3   | mi_stage4      | mi_stage5 | mi_stage6         | mi_stage7     |
      | File Submissions | Order Tracking | GLH Samples | Plater Samples | Picklists | Sequencer Samples | New Referrals |

  @NTS-5038
   # @E2EUI-2355
  Scenario Outline:NTS-5038:E2EUI-2355: Fields Referral ID and Patient NGIS ID appear below the GLH and Ordering Entity filters followed by report specific filters across all reports
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage1>" stage
    And the user sees a search box container section for "<mi_stage1>" page
    And the user sees the values in the search value "order_tracking-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader |
      | GLH                              |
      | Ordering Entity                  |
      | Referral ID                      |
      | Patient NGIS ID                  |
      | Clinical Indication Test Type    |
    When the user navigates to the mi-portal "<mi_stage2>" stage
    And the user sees a search box container section for "<mi_stage2>" page
    And the user sees the values in the search value "glh_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | Batch Import Filename                 |
      | Dispatched Sample Type                |
      | gel1004 GLH Sample Consignment Number |
    When the user navigates to the mi-portal "<mi_stage3>" stage
    And the user sees a search box container section for "<mi_stage3>" page
    And the user sees the values in the search value "plater_samples-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader      |
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | gel1004 Clinic Sample Type            |
      | gel1004 Disease Area                  |
      | gel1004 GLH Sample Consignment Number |
      | gel1004 Laboratory ID                 |
      | gel1005 Sample Received Datetime      |
    When the user navigates to the mi-portal "<mi_stage4>" stage
    And the user sees a search box container section for "<mi_stage4>" page
    And the user sees the values in the search value "picklists-search-col" drop-down menu
      | fileSubmissionsSearchValueHeader               |
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | gel1008 Plate ID                               |
      | gel1008 Normalised Biorepository Sample Volume |
      | gel1008 Plate Date of Dispatch                 |
    When the user navigates to the mi-portal "<mi_stage5>" stage
    And the user sees a search box container section for "<mi_stage5>" page
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

    Examples:
      | mi_stage1      | mi_stage2   | mi_stage3      | mi_stage4 | mi_stage5         |
      | Order Tracking | GLH Samples | Plater Samples | Picklists | Sequencer Samples |

  @NTS-4975
  #@E2EUI-2704
  Scenario:NTS-4975:E2EUI-2704: Implement sql-performance recommendations for miportalsampleview
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

  @NTS-4460
    #@E2EUI-1433
  Scenario Outline:NTS-4460:E2EUI-1433: MI Dashboard | Search LSIDs
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
    And  the user inputs the "<LSIDRefNo>" reference number
    And the user clicks on Find LSID
    #after clicking on find Button ,the data is displayed in image format,not able to identify any validation

    Examples:
      | mi_stage                        | LSIDRefNo  |
      | samplemsgstate_sampleprocessing | 1888152722 |

  @NTS-5059
  Scenario Outline:NTS-5059 : Fix CSV Download fails to truncate cells
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
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects a value "<value>" from the "file_submissions-search-col" column drop-down
    And the user selects a search operator "<operator>" from the "file_submissions-search-operator" operator drop-down
    And the user enters a date "<date>" in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    And search results are displayed in table format with display options button
    When the user clicks on the Download CSV button to download the CSV file as "fileSubmission".csv

    ### Incomplete : file content matching in UI and downloaded CSV file are yet to be done.

    Examples:
      | mi_stage         | value   | operator | date       |
      | File Submissions | Created | equals   | 02-04-2020 |
