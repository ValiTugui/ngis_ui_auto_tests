@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature:  MIPORTAL ST -  Home Page

  @NTS-5180
    #@E2EUI-2328
  Scenario Outline: NTS-5180:E2EUI-2328: Feedback Link removed from Mi Portal
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage1>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage2>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage3>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage4>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage5>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage6>" stage
    Then the user validates there is no feedback link on the page
    When the user navigates to the mi-portal "<mi_stage7>" stage
    Then the user validates there is no feedback link on the page

    Examples:
      | mi_stage1        | mi_stage2      | mi_stage3   | mi_stage4      | mi_stage5 | mi_stage6         | mi_stage7     |
      | File Submissions | Order Tracking | GLH Samples | Plater Samples | Picklists | Sequencer Samples | New Referrals |

  @NTS-4985
   ## @E2EUI-2772 @E2EUI-2329
  Scenario Outline: NTS-4985:E2EUI-2772,2329:Standardized MI portal name
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

  @NTS-5038 @MI-LOGOUT
   # @E2EUI-2355
  Scenario Outline:NTS-5038:E2EUI-2355: Fields Referral ID and Patient NGIS ID appear below the GLH and Ordering Entity filters followed by report specific filters across all reports
    When the user navigates to the mi-portal "<order_tracking>" stage
    And the user sees a search box container section for "<order_tracking>" page
    And the user sees the below values in the order tracking search column drop-down menu
      | GLH             |
      | Ordering Entity |
      | Referral ID     |
      | Patient NGIS ID |
      | Test Type       |
    When the user navigates to the mi-portal "<glh_samples>" stage
    And the user sees a search box container section for "<glh_samples>" page
    And the user sees the below values in the glh samples search column drop-down menu
      | GLH                           |
      | Ordering Entity               |
      | Referral ID                   |
      | Patient NGIS ID               |
      | Batch Import Filename         |
      | Dispatched Sample Type        |
      | GLH Sample Consignment Number |
    When the user navigates to the mi-portal "<plater_samples>" stage
    And the user sees a search box container section for "<plater_samples>" page
    And the user sees the below values in the plater samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | GEL1004 Clinic Sample Type            |
      | GEL1004 Disease Area                  |
      | GEL1004 GLH Sample Consignment Number |
      | GEL1004 Laboratory ID                 |
      | GEL1005 Sample Received Datetime      |
    When the user navigates to the mi-portal "<pick_lists>" stage
    And the user sees a search box container section for "<pick_lists>" page
    And the user sees the below values in the pick lists search column drop-down menu
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | GEL1008 Plate ID                               |
      | GEL1008 Normalised Biorepository Sample Volume |
      | GEL1008 Plate Date of Dispatch                 |
    When the user navigates to the mi-portal "<sequencer_samples>" stage
    And the user sees a search box container section for "<sequencer_samples>" page
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

    Examples:
      | order_tracking | glh_samples | plater_samples | pick_lists | sequencer_samples |
      | Order Tracking | GLH Samples | Plater Samples | Picklists  | Sequencer Samples |

  @NTS-4975
  #@E2EUI-2704
  Scenario:NTS-4975:E2EUI-2704: Implement sql-performance recommendations for miportalsampleview
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user should be able to see sample processing menu is displayed
    Then the user should be able to see the below header sections in Sample Processing
      | HeaderSection         |
      | File Submissions      |
      | Order Tracking        |
      | GLH Samples           |
      | Plater Samples        |
      | Picklists             |
      | Sequencer Samples     |
      | New Referrals         |
      | Search LSIDs          |
    When the user should be able to see data quality menu is displayed
    Then the user should be able to see the below header sections in Data Quality
      | HeaderSection         |
      | Clinical Data Quality |