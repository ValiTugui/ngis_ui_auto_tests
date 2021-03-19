@MIPORTAL
@MIPORTAL_ST
@Order_tracking
Feature: MIPORTAL ST - Order_Tracking

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of Order Tracking search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the order tracking search column drop-down menu
      | GLH             |
      | Ordering Entity |
      | Referral ID     |
      | Patient NGIS ID |
      | Test Type       |
      | LSID            |
    And the user selects GLH as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects is one of as the order tracking search operator dropdown
    And the user sees the below values in the order tracking search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    When the user selects Ordering Entity as the order tracking search column dropdown
    Then the user sees the below values in the order tracking search operator dropdown
      | is        |
      | is one of |

    When the user selects Referral ID as the order tracking search column dropdown
    Then the user sees the below values in the order tracking search operator dropdown
      | is exactly       |
      | is one of |

    When the user selects Patient NGIS ID as the order tracking search column dropdown
    Then the user sees the below values in the order tracking search operator dropdown
      | is exactly       |
      | is one of |

    When the user selects Test Type as the order tracking search column dropdown
    Then the user sees the below values in the order tracking search operator dropdown
      | is        |
      | is one of |

    When the user selects LSID as the order tracking search column dropdown
    Then the user sees the below values in the order tracking search operator dropdown
      | is exactly       |
      | is one of |

#Merging the scenario NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    When the user selects Test Type as the order tracking search column dropdown
    And the user selects is one of as the order tracking search operator dropdown
    And the user selects Cerebral malformations WGS,Hereditary ataxia - adult onset WGS as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Order Tracking |

  @NTS-5052 @MI-LOGOUT
    #@E2EUI-2398
  Scenario Outline: NTS-5052:E2EUI-2398: clinical_indication_test_type_id parameter on miportal Sample view support "in" operator.
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user selects Test Type as the order tracking search column dropdown
    And the user selects is as the order tracking search operator dropdown
    And the user selects Cystic renal disease WGS as the order tracking search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks on the button "Show all"
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      |GEL1001 GLH Laboratory ID |
      |GEL1001 Patient NGIS ID   |
      |GEL1001 Referral ID       |
      | GEL1001 Primary Sample Received Date|
      |GEL1001 Dispatched Sample Lsid       |
      |GEL1001 GLH Sample Dispatch Date     |
      | GEL1005 Sample Received Datetime    |
      |GEL1008 Plate ID                     |
      |GEL1008 Well ID                      |
      |GEL1008 Plate Date of Dispatch       |
      |GEL1010 Illumina QC Status           |
      |GEL1001 GLH QC Status                |
      |GEL1006 Biorepository QC Status      |
      |Test Name                            |
      |Clinical Indication Name             |
      |Date Request Submitted               |
      |GEL1001 Ordering Entity ID           |
      |GEL1001 Ordering Entity              |
      |GEL1001 Clinical Indication Test Type ID|
      | GEL1001 Dispatched Sample ID GLH Lims  |
      | GEL1001 Primary Sample ID Received GLH |
      |GEL1001 Dispatched Sample State         |
      | GEL1001 Approved By                    |
      |Test Code                               |
      | Priority Flag                          |
      | Status                                 |
    And the user save the changes on modal content by clicking Save and Close button
    Then the selected search option is reset after test


    Examples:
      | mi_stage       |
      | Order Tracking |