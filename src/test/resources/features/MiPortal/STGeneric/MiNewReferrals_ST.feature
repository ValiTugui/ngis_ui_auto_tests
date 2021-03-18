@MIPORTAL
@MIPORTAL_ST
@New_referrals
Feature:  MIPORTAL ST -  New Referrals ST

  @NTS-5190 @MI-LOGOUT
    # NTS-5066 is covered here
  Scenario Outline:NTS-5190:E2EUI-2770:When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the new referrals search column drop-down menu
      | GLH                                   |
      | Ordering Entity                       |
      | Referral ID                           |
      |Has GEL1001                            |
      |Priority                               |
      |Sample Processing GLH                  |
      |Test Type                              |
    And the user selects GLH as the new referrals search column dropdown
    And the user selects is as the new referrals search operator dropdown
    #  defect https://jira.extge.co.uk/browse/NMIS-782
    And the user sees the below values in the new referrals search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects is one of as the new referrals search operator dropdown
    #  defect https://jira.extge.co.uk/browse/NMIS-782
    And the user sees the below values in the new referrals search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects Ordering Entity as the new referrals search column dropdown
    And the user sees the below values in the new referrals operator drop-down menu
      | is        |
      | is one of |

    And the user selects Has GEL1001 as the new referrals search column dropdown
    And the user sees the below values in the new referrals operator drop-down menu
      | is        |
    And the user sees the below values in the new referrals search value drop-down menu
    |TRUE|
    |FALSE|

    And the user selects Priority as the new referrals search column dropdown
    And the user sees the below values in the new referrals operator drop-down menu
      | is        |
      | is one of |

    And the user sees the below values in the new referrals search value drop-down menu
    |Routine|
    |Urgent |

    And the user selects Sample Processing GLH as the new referrals search column dropdown
    And the user sees the below values in the new referrals operator drop-down menu
      | is        |
      | is one of |
    And the user sees the below values in the new referrals search value drop-down menu
      | Central and South Genomic Laboratory Hub        |
      | East Genomic Laboratory Hub                     |
      | North East and Yorkshire Genomic Laboratory Hub |
      | North Thames Genomic Laboratory Hub             |
      | North West Genomic Laboratory Hub               |
      | South East Genomic Laboratory Hub               |
      | South West Genomic Laboratory Hub               |

    And the user selects Test Type as the new referrals search column dropdown
    And the user sees the below values in the new referrals operator drop-down menu
      | is        |
      | is one of |
# Verifying the columns/fields present in the New Referrals report
    When the user selects Test Type as the new referrals search column dropdown
    And the user selects is as the new referrals search operator dropdown
    And the user selects Cystic renal disease WGS as the new referrals search value dropdown
    And the user clicks on Add criteria button
    When the user click on the Search button
    And the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    When the user clicks on the Display Options button
    Then the user sees a modal-content page
    And the user sees a section 'Column ordering' split into two parts 'Show' and 'Hide'
    When the user clicks on the button "Show all"
    And the user sees the displayed fields-columns under "Show" section
      | HeaderColumnOrderingList |
      |Referral ID               |
      |Referral Creation Date    |
      |Referral Last Submitted   |
      |Referral Status           |
      |Ordering Entity Name      |
      |Clinical Indication Code  |
      |Sample Processing Lab     |
      |Priority                  |
      |Ordering Entity Code      |
      |Order Entity GLH          |
      |Clinical Indication       |
      |CI Test Type Code         |
      |Test Type Name            |
      |Sample Processing Lab Code|
      |Sample Processing GLH     |
      |Has GEL1001               |

    And the user save the changes on modal content by clicking Save and Close button
    And the selected search option is reset after test

    Examples:
      | mi_stage      |
      | New Referrals |
