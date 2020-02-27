@regression
@pedigree
@pedigree_uiCustomization
Feature: Pedigree - UI Customizations

  @NTS-3384 @E2EUI-1226 @E2EUI-1948 @E2EUI-1070 @E2EUI-1007 @E2EUI-1080 @E2EUI-1187 @E2EUI-1571 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3384: UI Customizations: NGIS Patient -  Clinical Tab
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
      | FieldName           | FieldStatus  |
      | Age at Onset (Y)    | Non-Editable |
      | Age at Onset (m)    | Non-Editable |
      | Disease status      | Non-Editable |
      | Diagnosis Certainty | Non-Editable |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1970 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3388 @E2EUI-1073 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3388: UI Customizations: NGIS Patient -  Tumours Tab
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
      | Number of Colorectal Polyps Adenomas | AutoComplete |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1972 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3464 @E2EUI-946 @E2EUI-1425 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3464: UI Customizations: NGIS Patient -  Phenotype Tab
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
      | Phenotype   | AutoComplete |
    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1971 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3464 @E2EUI-1754 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3464 : Clinical Indication is displayed properly on Pedigree tool.
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
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Clinical
    Then the below field values should be displayed properly on Clinical Tab
      | FieldName                |
      | Clinical Indication Name |
    Examples:
      | ClinicalStage      | FamilyMemberDetails         | ClinicalQuestionDetails                 | Pedigree |
      | Clinical questions | NHSNumber=NA:DOB=25-10-2008 | DiseaseStatus=Affected:AgeOfOnset=03,02 | Pedigree |

