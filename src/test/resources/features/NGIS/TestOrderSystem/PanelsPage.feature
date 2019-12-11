@regression
@COMP08_P0
@Panels

Feature: Panels

  @COMP9_Panels
    @patientChoice_Page07 @E2EUI-1278 @v_1 @P0
  Scenario Outline: E2EUI-1278: Search and add panels to referral
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
    And the user should be able to see add another panel search field and search icon
    And the user should  be able to search and add the "<searchPanels>"panels
    And the user should be able to see selected panels
    Then the sees the selected panels under added panels
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Build a pedigree
    When the user navigates to the "<Panels>" stage
    Then the sees the selected panels under added panels




    Examples:
      | FamilyMembers  | Panels | searchPanels |
      | Family members | Panels | cardiac arr  |


  @COMP8_TO_PatientSearch
    @patientChoice_Page @E2EUI-1415 @v_1 @P0
  Scenario Outline: E2EUI-1415: Requesting Organisation landing page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    And the user is navigated to a page with title Check your patient
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should be able to see an intro message "<introMessage>" on requesting organisation page
    Then the user should be able to see hint text in search box on requesting organisation page
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed

    Examples:
      | Requesting organisation | introMessage                                                   | ordering_entity_name |
      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | Maidstone            |