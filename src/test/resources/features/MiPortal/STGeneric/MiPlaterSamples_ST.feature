@MIPORTAL
@MIPORTAL_ST
@plater_samples
Feature:  MIPORTAL ST - Plater Samples

  @NTS-5190 @MI-LOGOUT
    #NTS-6704,4978 are covered in this scenario
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of Plater Samples search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the plater samples search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      | Patient NGIS ID                       |
      | GEL1004 Disease Area                  |
      | GEL1004 GLH Sample Consignment Number |
      | GEL1004 Laboratory ID                 |
      | GEL1005 Sample Received               |
      | GEL1005 Sample Received Datetime      |
      | LSID            |

    And the user selects GLH as the plater samples search column dropdown
    And the user selects is as the plater samples search operator dropdown
    And the user sees the below values in the plater samples search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects is one of as the plater samples search operator dropdown
    And the user sees the below values in the plater samples search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects Ordering Entity as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is        |
      | is one of |

    And the user selects Referral ID as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is exactly       |
      | is one of |

    And the user selects Patient NGIS ID as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is exactly       |
      | is one of |

    And the user selects GEL1004 Disease Area as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is        |
    And the user sees the below values in the plater samples search value drop-down menu
    |Cancer|
    |Rare Disease|

    And the user selects GEL1004 GLH Sample Consignment Number as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is exactly       |
      | is one of |

    And the user selects GEL1004 Laboratory ID as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is        |
    And the user sees the below values in the plater samples search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects GEL1005 Sample Received as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is        |
    And the user sees the below values in the plater samples search value drop-down menu
    |Yes|
    |No |

    And the user selects GEL1005 Sample Received Datetime as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      |equals|
      |before or on|
      |on or after|

    And the user selects LSID as the plater samples search column dropdown
    And the user sees the below values in the plater samples search operator drop-down menu
      | is exactly       |
      | is one of |
#Merged the scenario NTS-4978:E2EUI-2706: MIS - Datetime filters for "before or on" dates do not bring back all records for the specified date
    And the user selects gel1005 Sample Received Datetime as the plater samples search column dropdown
    And the user selects before or on as the plater samples search operator dropdown
    And the user enters 5 days before today in the plater sample date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Plater Samples |


  @NTS-5021 @MI-LOGOUT
   ## @E2EUI-2231 ##Drag and Drop  - need to recheck
  Scenario Outline: NTS-5021:E2EUI-2231: MIS: Fields not populating in Plater Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    When the user selects GEL1005 Sample Received Datetime as the plater samples search column dropdown
    And the user selects before or on as the plater samples search operator dropdown
    And the user enters 5 days before today in the plater sample date field
    And the user clicks on Add criteria button
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    #Verifying the columns/fields in the Plater samples section
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      | GEL1001 Patient NGIS ID  |
      |GEL1001 Referral ID       |
      |GEL1004 Disease Area      |
      |GEL1004 GMC Rack ID       |
      | GEL1004 GLH Sample Consignment Number|
      |GEL1004 Laboratory Sample ID          |
      | GEL1004 Laboratory Sample Volume     |
      | GEL1004 GMC Rack Well                |
      |GEL1004 Is Proband                    |
      | GEL1005 Laboratory ID                |
      |GEL1005 Sample Received               |
      |GEL1005 Sample Received Datetime      |

    And the user sees the displayed fields-columns under "Hide" section
      | HeaderColumnOrderingList |
      | GEL1004 Filename                     |
      |GEL1004 Created                       |
      |GEL1005 Filename                      |
      |GEL1005 Created                       |
      |GEL1005 Participant ID                |
      |GEL1004 Laboratory ID                 |
      |GEL1005 Datetime Report Generated     |
      |GEL1005 Laboratory Sample ID          |

    When the user drag the column header "GEL1005 Laboratory Sample ID" from the section "Hide" to "Show" section
    And the user clicks on save and close button
    And the columns fields are  displayed in the list of columns headers of the search result table
      | columnHeaders |
      |GEL1005 Laboratory Sample ID|
    And the selected search option is reset after test

    Examples:
      | mi_stage       |
      | Plater Samples |

