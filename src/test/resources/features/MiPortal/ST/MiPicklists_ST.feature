@MIPORTAL
@MIPORTAL_ST_3

Feature: MIPORTAL ST - PickLists 1

  @NTS-5190
  Scenario Outline:NTS-5190:E2EUI-2770: When Search-column is "Submitted By" and operator is "<operator>": verify the drop-down values of file-submission search values
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user selects <value> as the pick lists search column dropdown
    And the user selects <operator> as the pick lists search operator dropdown
    And the user sees the below values in the pick lists search value drop-down menu
      | East Mids and East of England    |
      | London North                     |
      | London South                     |
      | North West                       |
      | South West                       |
      | Wessex & West Midlands           |
      | Yorkshire & North East           |

    Examples:
      | mi_stage  | value | operator  |
      | Picklists | GLH   | is        |
      | Picklists | GLH   | is one of |

  @NTS-5058
    #@E2EUI-2710
  Scenario Outline:NTS-5058:E2EUI-2710: A user is looking for explanatory message from the API
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    And the user sees the below values in the pick lists search column drop-down menu
      | GLH                                            |
      | Ordering Entity                                |
      | Referral ID                                    |
      | Patient NGIS ID                                |
      | gel1008 Plate ID                               |
      | gel1008 Normalised Biorepository Sample Volume |
      | gel1008 Plate Date of Dispatch                 |
    Then the user click on the reset button
    And the user selects gel1008 Plate ID as the pick lists search column dropdown
    And the user selects is one of as the pick lists search operator dropdown
    And the user fills in a "<value>" in the "picklists-search-value" search box
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    When the user click on the Search button
    Then the user sees an error message "Select a valid choice. That choice is not one of the available choices." on the page

    Examples:
      | mi_stage  | value     |
      | Picklists | joy-22457 |