@MIPORTAL
@MIPORTAL_ST
@file_submission
Feature: MIPORTAL ST - File Submission 2

  @NTS-3390 @MI-LOGOUT
    #@E2EUI-1283 NTS-5054:E2EUI-2471
  Scenario Outline: NTS-3390: Verify the Pagination drop-down options shown in search result table and Sort file submissions results by created date descending
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    When the user selects Created as the search column dropdown
    And the user selects before or on as the search operator dropdown
    And the user enters 5 days before today in the file-submission date field
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then search results are displayed in table format with display options button
    #Covered the verification of values in "Created" column of search result is in descending order
    And the user see dates value in "Created" column of file-submission search result in descending order
    And the user sees the search results pagination entry drop-down with default selection of "10"
    And the user sees all the drop-down values in the search results pagination entry selection
      | paginationDropDownValues |
      | 10                       |
      | 25                       |
      | 50                       |
      | 100                      |
    And the search result table should display based on the pagination value selected
      | Pagination | Expected Rows |
      | 50         | 50            |
      | 25         | 25            |
      | 10         | 10            |
      | 100        | 100           |
    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |

  @NTS-3390 @MI-LOGOUT
    #@E2EUI-1283,2513
  Scenario Outline: NTS-3390:E2EUI-1283,2513:verify the defaults elements on File Submission search page
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    Then the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons
    Then the user sees the below values in the file-submission search column drop-down menu
      | Created      |
      | Status       |
      | Submitted By |
      | Filename      |
      | File Type     |

    When the user selects Created as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | equals       |
      | before or on |
      | on or after  |

    When the user selects Status as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |

    When the user selects Status as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |

    When the user selects Status as the search column dropdown
    And the user selects is one of as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | Duplicate           |
      | In Progress         |
      | Invalid             |
      | Valid               |
      | Valid with Warnings |

    When the user selects Submitted By as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |

    When the user selects Submitted By as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | East Genomic Laboratory Hub                     |
      | North Thames Genomic Laboratory Hub             |
      | South East Genomic Laboratory Hub               |
      | North West Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |
      | Central and South Genomic Laboratory Hub        |
      | North East and Yorkshire Genomic Laboratory Hub |
      | ILMN                                            |
      | UKB                                             |

    When the user selects is one of as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | East Genomic Laboratory Hub                     |
      | North Thames Genomic Laboratory Hub             |
      | South East Genomic Laboratory Hub               |
      | North West Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |
      | Central and South Genomic Laboratory Hub        |
      | North East and Yorkshire Genomic Laboratory Hub |
      | ILMN                                            |
      | UKB                                             |

    When the user selects Filename as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | matches        |

    When the user selects File Type as the search column dropdown
    Then the user sees the below values in the file-submission search operator drop-down menu
      | is        |
      | is one of |

    When the user selects File Type as the search column dropdown
    And the user selects is as the search operator dropdown
    Then the user sees the below values in the file-submission search value drop-down menu
      | GEL1001 |
      | GEL1004 |
      | GEL1005 |
      | GEL1006 |
      | GEL1007 |
      | GEL1008  |
      | GEL1009  |
      | GEL1010  |

    And the selected search option is reset after test

    Examples:
      | mi_stage         |
      | File Submissions |