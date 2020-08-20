@MIPORTAL
@MIPORTAL_SIT

Feature: MIPORTAL SIT - Implement hierarchical search terms for GLH/Ordering entity.

  @NTS-5721
   ## @E2EUI-2886
  Scenario Outline: NTS-5721:E2EUI-2886: As a user, I want to see the ordering entities for the respective GLH are matching to document in Order Tracking & Plater Samples sections.

    Given a web browser is at the mi-portal home page
      | MI_PORTAL_URL | ngis.io | GEL_NORMAL_USER |
    And the user should be able to see sample processing menu is displayed
    When the user navigates to the mi-portal "<mi_stage>" stage
    And the user sees a search box container section for "<mi_stage>" page
    When the user selects GLH as the order tracking search column dropdown
    And the user selects <operator> as the order tracking search operator dropdown
    And the user selects <glhName> as the order tracking search value dropdown
    And the user clicks on Add criteria button
    Then file submission search criteria badge information is displayed below drop-down buttons
    Then the user selects Ordering Entity as the order tracking search column dropdown
    And User should be able to see data under given "<glhName>" and matches excel data "<FileName>" with the Ordering Entity options

    Examples:
      | mi_stage       | operator | glhName                       | FileName               |
      | Order Tracking | is       | East Mids and East of England | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | London North                  | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | London South                  | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | North West                    | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | South West                    | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | Wessex & West Midlands        | gld-ods-mapping-3.xlsx |
      | Order Tracking | is       | Yorkshire & North East        | gld-ods-mapping-3.xlsx |
