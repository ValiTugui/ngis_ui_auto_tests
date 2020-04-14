#@regression
#@pedigree_uiCustomizationNGIS
@PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - UI Customizations - NGIS

  @NTS-3384 @LOGOUT
#    @E2EUI-1226 @E2EUI-1948 @E2EUI-1070 @E2EUI-1030 @E2EUI-1007 @E2EUI-1080 @E2EUI-1187 @E2EUI-1571 @E2EUI-1444
  Scenario Outline: NTS-3384:(E2EUI-1226,1948,1070,1030,1007,1080,1187,1571,1444): UI Customizations: NGIS Patient -  Clinical Tab
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1970:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    Then the user should see the below messages displayed in the pedigree page
      | The pedigree automatically shows test participants including:                                                            |
      | To make changes related to these participants, update the patient details and family members sections, not the pedigree. |
      | Update the pedigree to add additional family members who are not being tested as part of this referral.                  |
      | Do not include any personal identifiable information in the pedigree.                                                    |
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<MemberDetails>"
    And the user select the pedigree tab Clinical
    Then the user should see below fields on Clinical Tab with the given status
      | FieldLabel                                       | FieldStatus  |
      | Clinical Indication Name                         | Non-Editable |
      | Age at Onset (Y)                                 | Non-Editable |
      | Age at Onset (m)                                 | Non-Editable |
      | Disease status                                   | Non-Editable |
      | Please select coding system for disorder search: | Non-Editable |
      | Diagnosis Certainty                              | Non-Editable |
      | Disorder                                         | Non-Editable |
      | Documented evaluation                            | Editable     |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1970 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3388 @LOGOUT
#    @E2EUI-1073 @E2EUI-1277
  Scenario Outline: NTS-3388:(E2EUI-1073,1277): UI Customizations: NGIS Patient -  Tumours Tab
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1972:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    Then the user should see the below messages displayed in the pedigree page
      | The pedigree automatically shows test participants including:                                                            |
      | To make changes related to these participants, update the patient details and family members sections, not the pedigree. |
      | Update the pedigree to add additional family members who are not being tested as part of this referral.                  |
      | Do not include any personal identifiable information in the pedigree.                                                    |
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<MemberDetails>"
    And the user select the pedigree tab Tumours
    Then the user should see below fields on Tumours Tab with the given status
      | FieldName                            | FieldStatus  |
      | Number Of Colorectal Polyps Total    | Non-Editable |
      | Number of Colorectal Polyps Adenomas | Non-Editable |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1972 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3464 @LOGOUT
#    @E2EUI-946 @E2EUI-1425
  Scenario Outline: NTS-3464:(E2EUI-946,1425): UI Customizations: NGIS Patient -  Phenotype Tab
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1971:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    Then the user should see the below messages displayed in the pedigree page
      | The pedigree automatically shows test participants including:                                                            |
      | To make changes related to these participants, update the patient details and family members sections, not the pedigree. |
      | Update the pedigree to add additional family members who are not being tested as part of this referral.                  |
      | Do not include any personal identifiable information in the pedigree.                                                    |
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<MemberDetails>"
    And the user select the pedigree tab Phenotype
    Then the user should see below fields on Phenotype Tab with the given status
      | FieldName   | FieldStatus  |
      | HPO Present | Non-Editable |
      | Phenotype   | Non-Editable |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1971 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3464 @LOGOUT
#    @E2EUI-1754 @E2EUI-1246
  Scenario Outline: NTS-3464 :(E2EUI-1754,1246): Clinical Indication is displayed properly on Pedigree tool.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Clinical Information
    When the user navigates to the "<ClinicalStage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
     ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    ##below step for E2EUI-1246
    And the user should see clinical indication same as what is selected at the start of test order
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Clinical
    Then the below field values should be displayed properly on Clinical Tab
      | FieldName                |
      | Clinical Indication Name |
    Examples:
      | ClinicalStage      | FamilyMemberDetails         | ClinicalQuestionDetails                 | Pedigree | WarningMessage                                                                                |
      | Clinical questions | NHSNumber=NA:DOB=25-10-2008 | DiseaseStatus=Affected:AgeOfOnset=03,02 | Pedigree | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |


  @NTS-3464 @LOGOUT
#    @E2EUI-1157
  Scenario Outline: NTS-3464 :E2EUI-1157: Is participating in test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree - checking for Proband
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then user should see the Participating in Test check box is Not Selected
    And the user is able to close the popup by clicking on the close icon
    ##Below step added this as sometimes direct clicking on Requesting Organisation from Pedigree is not happening due to some overlay
    When the user scroll to the top of landing page
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then user should see the Participating in Test check box is Selected
    And the user is able to close the popup by clicking on the close icon

    Examples:
      | TestPackage  | ProbandDetails              | NoOfParticipants | Pedigree | WarningMessage                                                                                |
      | Test package | NHSNumber=NA:DOB=25-10-2005 | 1                | Pedigree | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |