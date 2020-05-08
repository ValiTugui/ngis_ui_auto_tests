#@regression
#@pedigree_diagramLayout
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - Diagram Layout
  @NTS-3304 @Z-LOGOUT
#    @E2EUI-934 @E2EUI-1046
  Scenario Outline: NTS-3304:(E2EUI-934,1046) Pedigree Diagram layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
     ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    And the user should be able to see following menu items in the diagram menus
      | MenuItem    |
      | Undo        |
      | Redo        |
      | Reset       |
      | Print       |
      | Save        |
      | Export      |
      | RequestID   |
      | NGISVersion |
    And the user should be able to see following controls to view the diagram
      | ViewControl |
      | MoveRight   |
      | MoveDown    |
      | MoveLeft    |
      | MoveUp      |
      | MoveHome    |
      | ZoomIn      |
      | ZoomOut     |
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    Then the user should be able to see the below tabs in the popup window
      | TabName   |
      | Clinical  |
      | Personal  |
      | Phenotype |
      | Tumours   |
    ##E2EUI-1046
    And the verify the Pedigree diagram is loaded with java script instead of iframe
    And the user is able to close the popup by clicking on the close icon

    Examples:
      | FamilyMemberDetails         | Pedigree | WarningMessage                                                                                |
      | NHSNumber=NA:DOB=25-10-2008 | Pedigree | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3304 @Z-LOGOUT
#    @E2EUI-1457
  Scenario Outline: NTS-3304 :E2EUI-1457: Pedigree Diagram layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2009:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
     ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    And the user should see the Undo button status as disabled
    And the user should see the Redo button status as disabled
    When the user click on Reset menu button
    Then the user should see the Undo button status as enabled
    When the user click on Undo menu button
    Then the user should see the Redo button status as enabled

    Examples:
      | Pedigree | WarningMessage                                                                                |
      | Pedigree | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-33861 @Z-LOGOUT
#    @E2EUI-1630 @E2EUI-1051
  Scenario Outline: NTS-3464:User is making a referral and has arrived in the Pedigree section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2000:Gender=Female |
    When the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see Save button on Pedigree Page
    ##Requesting Organisation
    When the user scroll to the top of landing page
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Manchester" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
     ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see SaveAndContinue button on Pedigree Page
    And the user clicks on Save and Continue on Pedigree Page
    And the "<Pedigree>" stage is marked as Completed
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | Requesting organisation | Pedigree | NoOfParticipants |
      | Requesting organisation | Pedigree | 1                |