@MIPORTAL
@MIPORTAL_ST
@glh_samples
Feature: MIPORTAL ST -  Glh Samples

  @NTS-5190  @MI-LOGOUT
    # NTS-5178 is covered here in this scenario
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of GLH Samples search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the user sees the below values in the glh samples search column drop-down menu
      | GLH                           |
      | Ordering Entity               |
      | Referral ID                   |
      | Patient NGIS ID               |
      | Batch Import Filename         |
      | Dispatched Sample Type        |
      | GLH Sample Consignment Number |
      | LSID                          |

    When the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    Then the user sees the below values in the glh samples search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    When the user selects is one of as the glh search operator dropdown
    Then the user sees the below values in the glh samples search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    When the user selects Ordering Entity as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is        |
      | is one of |

    When the user selects Referral ID as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is exactly       |
      | is one of |

    When the user selects Patient NGIS ID  as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is exactly       |
      | is one of |

    When the user selects Batch Import Filename  as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | matches |

    When the user selects Dispatched Sample Type  as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is        |
    And the user selects is as the glh search operator dropdown
    Then the user sees the below values in the glh samples search value drop-down menu
      |Liquid tumour sample|
      | Normal or Germline sample               |
      |Solid tumour sample              |

    When the user selects GLH Sample Consignment Number  as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is exactly       |
      | is one of |

    When the user selects LSID as the glh search column dropdown
    Then the user sees the below values in the glh samples search operator dropdown
      | is exactly       |
      | is one of |

    Examples:
      | mi_stage    |
      | GLH Samples |

  #  Covered in NTS-5190
  @NTS-5036  @MI-LOGOUT
#   # @E2EUI-2486
  Scenario Outline: NTS-5036:E2EUI-2486: The GLH Samples section is having a new Filename filter and verify the columns present in the GLH Samples
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Batch Import Filename as the glh search column dropdown
    And the user selects matches as the glh search operator dropdown
    And the user enters gel in the glh search value box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks on the button "Show all"
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      |GEL1001 Filename          |
      | GEL1001 Patient NHS Number|
      | GEL1001 Patient NGIS ID   |
      |  GEL1001 Ordering Entity ID|
      | GEL1001 Primary Sample Received Date|
      | GEL1001 Primary Sample ID GLH Lims  |
      | GEL1001 Dispatched Sample Lsid      |
      | GEL1001 Dispatched Sample Volume ul |
      |GEL1001 GLH Concentration ng ul      |
      | GEL1001 GLH QC Status               |
      |GEL1001 GLH Sample Dispatch Date     |
      | GEL1001 GMC Rack ID                 |
      | GEL1001 GMC Rack Well               |
      |GEL1001 Referral ID                  |
      |GEL1001 GLH Laboratory ID            |
      |GEL1001 Dispatched Sample Type       |
      | GEL1001 GLH Sample Consignment Number|
      | GEL1001 Created                      |
      |  GEL1001 Primary Sample ID Received GLH|
      | GEL1001 Dispatched Sample ID GLH Lims  |
      |GEL1001 Dispatched Sample State         |
      | GEL1001 Laboratory Remaining Volume Banked ul|
      |GEL1001 GLH OD 260 280                        |
      | GEL1001 GLH Din Value                        |
      | GEL1001 GLH Percentage DNA                   |
      |GEL1001 DNA Extraction Protocol               |
      | GEL1001 Prolonged Sample Storage             |
      |  GEL1001 Retrospective Sample                |
      |  GEL1001 Approved By                         |
      | GEL1001 Clinical Indication Test Type ID     |

    And the user closes the modal content by clicking on the reset-button

    Examples:
      | mi_stage    |
      | GLH Samples |

  @NTS-6446 @MI-LOGOUT
  Scenario Outline: A pop-up containing all the rows values on double-clicking on a particular table row should be displayed
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects GLH as the glh search column dropdown
    And the user selects is as the glh search operator dropdown
    And the user selects South East Genomic Laboratory Hub as the search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV

    And the glh search result table column GEL1001 Filename is displayed with data gel
    Then the user double clicks on any data table row and a pop up box is displayed with the row values
    And the user clicks on the pop up close icon

    Examples:
      | mi_stage    |
      | GLH Samples |