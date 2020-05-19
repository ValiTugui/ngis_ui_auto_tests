@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST

Feature: MIPORTAL ST - PickLists 1

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
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
    Then the selected search option is reset after test

    Examples:
      | mi_stage  |
      | Picklists |

  @NTS-5058
    #@E2EUI-2710
  Scenario: NTS-5058:E2EUI-2710: A user is looking for explanatory message from the API
    When the user selects gel1008 Plate ID as the pick lists search column dropdown
    And the user selects is one of as the pick lists search operator dropdown
    And the user selects joy-22457 as the picklists search input value
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees an error message "Select a valid choice. That choice is not one of the available choices." on the page