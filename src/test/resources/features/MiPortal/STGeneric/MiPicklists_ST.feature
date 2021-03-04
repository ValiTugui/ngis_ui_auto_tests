@MIPORTAL
@MIPORTAL_ST
@Picklists
Feature: MIPORTAL ST - PickLists 1

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of Picklists search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the pick lists search column drop-down menu
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | GEL1008 Plate ID                               |
      | GEL1008 Normalised Biorepository Sample Volume |
      | GEL1008 Plate Date of Dispatch                 |
      | LSID            |

    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user sees the below values in the pick lists search value drop-down menu
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    And the user selects is one of as the pick lists search operator dropdown
    And the user sees the below values in the pick lists search value drop-down menu
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    And the user selects Ordering Entity as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      | is        |
      | is one of |

    And the user selects Referral ID  as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      | is exactly       |
      | is one of |

    And the user selects Patient NGIS ID as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      | is exactly       |
      | is one of |

    And the user selects GEL1008 Plate ID as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      | is exactly       |
      | is one of |

    And the user selects GEL1008 Normalised Biorepository Sample Volume as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
    |equals|
    |is less than or equal to|
    |is greater than or equal to|

    And the user selects GEL1008 Plate Date of Dispatch as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      |equals|
      |before or on|
      |on or after|

    And the user selects LSID as the pick lists search column dropdown
    And the user sees the below values in the pick lists search operator dropdown menu
      | is exactly       |
      | is one of |

    Then the selected search option is reset after test

    Examples:
      | mi_stage  |
      | Picklists |


    # Below scenario is out of scope due to  https://jira.extge.co.uk/browse/NMIS-1222
#  @NTS-5058
#    #@E2EUI-2710
#  Scenario: NTS-5058:E2EUI-2710: A user is looking for explanatory message from the API
#    When the user selects gel1008 Plate ID as the pick lists search column dropdown
#    And the user selects is one of as the pick lists search operator dropdown
#    And the user selects joy-22457 as the picklists search input value
#    And the user clicks on Add criteria button
#    Then file submission search criteria badge information is displayed below drop-down buttons
#    When the user click on the Search button
#    Then the user sees an error message "Select a valid choice. That choice is not one of the available choices." on the page

@NTS-6703 @MI-LOGOUT
  Scenario Outline:NTS-6703:Remove extraneous field gel1008__plate__warning_msgs from Picklists Report and verify the fields present in the Picklists section
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects GLH as the pick lists search column dropdown
    And the user selects is as the pick lists search operator dropdown
    And the user selects GLHName as the pick lists search value dropdown
    And the user clicks on Add criteria button
    And the user click on the Search button
    Then the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV
    And the user clicks on the Display Options button
    When the user clicks "Show all" button on the modal-content page
  And the user sees the displayed fields-columns under "Show" section
    | HeaderColumnOrderingList |
    |GEL1008 Laboratory Sample ID|
    |GEL1008 Participant ID      |
    |GEL1008 Normalised Biorepository Concentration|
    |GEL1008 Well ID                               |
    |GEL1008 Plate Consignment Number              |
    |GEL1008 Normalised Biorepository Sample Volume|
    |GEL1008 Plate Date of Dispatch                |
    |GEL1008 Plate Batch Import Filename           |
    |GEL1008 Created                               |
    |GEL1008 Plate Batch Import Status             |
    |GEL1008 Plate ID                              |

    And the user sees the fields are not displayed under the "Show" section
      | HeaderColumnOrderingList   |
      | gel1008 Plate Warning Msgs |
    Then the user closes the modal content by clicking on the reset-button

    Examples:
      | mi_stage  |
      | Picklists |