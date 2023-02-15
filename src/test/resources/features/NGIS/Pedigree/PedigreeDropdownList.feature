#@SYSTEM_TEST
#@07-PEDIGREE
Feature: Pedigree dropdown list

  #@HTO-992
  Scenario: Update pedigree dropdown list to have immediate family members at/near top
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Female |
    Then the user is navigated to a page with title Add a requesting organisation
    ##Test Package - No of participants -2
    When the user navigates to the "Test package" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "2"
    And the user clicks the Save and Continue button
    And the "Test package" stage is marked as Completed
    ##Family Members - searching for an existing referral
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on Add family member button
    And the user searches for an existing family member using "10-01-1950" for DOB, "John" for first name, "Jones" for last name and "Male" for gender
    And the user clicks on the patient card
    Then the relationship with the patient list order should be as follow
      | Mother                              |
      | Father                              |
      | Full Sibling                        |
      | Daughter                            |
      | Son                                 |
      | Twins Unknown                       |
      | Twins Monozygous                    |
      | Twins Dizygous                      |
      | Maternal Half Sibling               |
      | Paternal Half Sibling               |
      | Other                               |
      | Maternal Grandparent                |
      | Paternal Grandparent                |
      | Paternal Grandfather's Parent       |
      | Paternal Grandmother's Parent       |
      | Maternal Aunt                       |
      | Maternal Uncle                      |
      | Paternal Aunt                       |
      | Paternal Uncle                      |
      | Maternal Cousin through Aunt        |
      | Maternal Cousin through Uncle       |
      | Paternal Cousin through Aunt        |
      | Paternal Cousin through Uncle       |
      | Maternal First Cousin once removed  |
      | Paternal First Cousin once removed  |
      | Maternal Second Cousin              |
      | Paternal Second Cousin              |
      | Maternal Second Cousin once removed |
      | Paternal Second Cousin once removed |
      | Maternal Third Cousin               |
      | Paternal Third Cousin               |
      | Double First Cousin                 |
