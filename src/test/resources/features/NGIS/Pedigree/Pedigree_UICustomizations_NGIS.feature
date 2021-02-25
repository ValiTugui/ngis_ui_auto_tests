#@pedigree_uiCustomizationNGIS
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - UI Customizations - NGIS

  @NTS-3388 @E2EUI-1073 @E2EUI-1277
  @NTS-3464  @E2EUI-946 @E2EUI-1425 @E2EUI-1754 @E2EUI-1246
   @NTS-3384 @Z-LOGOUT
#    @E2EUI-1226 @E2EUI-1948 @E2EUI-1070 @E2EUI-1030 @E2EUI-1007 @E2EUI-1080 @E2EUI-1187 @E2EUI-1571 @E2EUI-1444
  Scenario Outline: NTS-3384:(E2EUI-1226,1948,1070,1030,1007,1080,1187,1571,1444): UI Customizations: NGIS Patient -  Clinical Tab
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-1970:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    Then the user should see the below messages displayed in the pedigree page
#      | The pedigree automatically shows test participants including:                                                            |
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
  ## Added @NTS-3388
  And the user select the pedigree tab Tumours
  Then the user should see below fields on Tumours Tab with the given status
    | FieldName                            | FieldStatus  |
    | Number Of Colorectal Polyps Total    | Non-Editable |
    | Number of Colorectal Polyps Adenomas | Non-Editable |
  ##Added @NTS-3464
  And the user select the pedigree tab Phenotype
  Then the user should see below fields on Phenotype Tab with the given status
    | FieldName   | FieldStatus  |
    | HPO Present | Non-Editable |
    | Phenotype   | Non-Editable |
  And the user select the pedigree tab Clinical
  Then the below field values should be displayed properly on Clinical Tab
    | FieldName                |
    | Clinical Indication Name |

    Examples:
      | PedigreeStage | MemberDetails               | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1970 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |


  @NTS-3464 @Z-LOGOUT
#    @E2EUI-1157
  Scenario Outline: NTS-3464 :E2EUI-1157: Is participating in test
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
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

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1194
  Scenario Outline: NTS-3386 : Order the display of HPO Terms in Pedigree
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2006:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
     ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ##Family Member
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1979:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    And the user added additional phenotypes "<Phenotypes>" to the family member
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Phenotype
    Then the user should see the hpo phenotypes "<Phenotypes>" displayed

    Examples:
      | TestPackage  | Two | FamilyMembers  | Pedigree | Phenotypes                          | FamilyMemberDetails         |
      | Test package | 2   | Family members | Pedigree | Congestive heart failure,Lymphedema | NHSNumber=NA:DOB=17-07-1979 |