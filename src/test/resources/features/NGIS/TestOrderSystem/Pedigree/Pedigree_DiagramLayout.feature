@regression
@pedigree
@pedigree_diagramLayout
Feature: Pedigree - Diagram Layout

  @NTS-3304 @E2EUI-934 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3304 : Pedigree Diagram layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
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
    And the user is able to close the popup by clicking on the close icon

    Examples:
      | FamilyMemberDetails         | Pedigree | WarningMessage                                                                                |
      | NHSNumber=NA:DOB=25-10-2008 | Pedigree | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3304 @E2EUI-1457 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3304 : Pedigree Diagram layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
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

