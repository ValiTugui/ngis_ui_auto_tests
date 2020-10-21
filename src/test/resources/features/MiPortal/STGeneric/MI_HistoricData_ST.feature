@MIPORTAL
@MIPORTAL_ST
@SYSTEM_TEST
@DQ_Report
Feature: MIPORTAL ST - Historic Data

  @NTS-5743
#    @MI-LOGOUT
  Scenario Outline: User is able to verify historic data
    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
#    And the user verifies the "<SheetName1>" data from table against "<FileName>"
#    And the user verifies the "<SheetName2>" data from table against "<FileName>"
#    And the user verifies the "<SheetName3>" data from table against "<FileName>"
#    And the user verifies the "<SheetName4>" data from table against "<FileName>"
#    And the user verifies the "<SheetName5>" data from table against "<FileName>"
    And the user verifies the "<SheetName6>" data from table against "<FileName>"
#    And the user verifies the "<SheetName7>" data from table against "<FileName>"

    Examples:
      | FileName                          | SheetName1       | SheetName2     | SheetName3  | SheetName4     | SheetName5 | SheetName6        | SheetName7    |
      | r20805102018_Data_Validation.xlsx | File Submissions | Order Tracking | GLH Samples | Plater Samples | Picklists  | Sequencer Samples | New Referrals |