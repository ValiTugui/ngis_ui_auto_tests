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

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1373 @E2EUI-836 @E2EUI-1269
  Scenario Outline: NTS-3386 : Test with a trio (mother & father)
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree - checking for Proband
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able see the pedigree diagram loaded for the given members
      | MemberDetails               |
      | NHSNumber=NA:DOB=25-10-2005 |
    ##Below step added this as sometimes direct clicking on Requesting Organisation from Pedigree is not happening due to some overlay
    When the user scroll to the top of landing page
    ##Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Information
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Family Member
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                              |
      | NHSNumber=NA:DOB=17-07-1980:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema   |
      | NHSNumber=NA:DOB=17-07-1978:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Unaffected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able see the pedigree diagram loaded for the given members
      | MemberDetails               |
      | NHSNumber=NA:DOB=17-07-1980 |
      | NHSNumber=NA:DOB=17-07-1978 |

    Examples:
      | Requesting organisation | ordering_entity_name | NoOfParticipants | ResponsibleClinicianDetails               | ClinicalQuestionDetails                   | FamilyMembers  | Pedigree |
      | Requesting organisation | Maidstone            | 3                | LastName=Smith:Department=Victoria Street | DiseaseStatus=Unaffected:AgeOfOnset=03,02 | Family members | Pedigree |

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1630 @E2EUI-1051
  Scenario Outline: NTS-3464:User is making a referral and has arrived in the Pedigree section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2000:Gender=Female |
    When the user is navigated to a page with title Check your patient's details
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


