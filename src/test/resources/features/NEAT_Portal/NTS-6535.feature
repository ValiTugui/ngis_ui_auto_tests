@NEAT
@NTS_6535

Feature: Merge / Demerge event should be displayed as Merge Badge/Stamp in TOMS for RD Participants

  @NTS_6535_Scenario1
  Scenario Outline: As a user,I want to know if a patient recorded has been merged or de-merged,So that I know it has been subject to a non-standard process and can investigate any issues with the data I see
#  Scenario 1: Merged event should display a Merged Badge for an RD referral / participant
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Likely inborn error of metabolism | SPECIAL_CHARACTERS | create a new patient record | None | GEL_SUPER_USER |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    Then the user sees the Merge status as "<current status>"
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the success notification "<success notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<latest merge status>"

    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | Rare Disease | GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<Merge status>" on the patient result card
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks on edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button on Patient details page
    And the user clicks the Start Referral button to display the referral page
    Then the user is navigated to a page with title Test Order Forms
#    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "Patient details" stage
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<validSearchDetails>"
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<Merge status>" on the patient result card
    And the user clicks the patient result card
    Then the user is navigated to a page with title Add missing family member details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<validSearchDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    Then the user should not see the merge warning notification banner
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    Examples:
      | current status | current merge status | Merge status | success notification                        | latest merge status | result_message         | tooltipMessage                                                                                                                                                                    | validSearchDetails                  | RelationshipToProband | DiseaseStatusDetails     |
      | None           | None                 | Merged       | This record's merge status has been updated | Merged              | 1 patient record found | This record has been the subject of a merge or demerge; additional information not present in the current record may be available by contacting the Genomics England service desk | NHSNumber=9449306494:DOB=17-05-2011 | Father                | DiseaseStatus=Unaffected |


  @NTS_6535_Scenario2 @NTS_6535
  Scenario Outline: As a user,I want to know if a patient recorded has been merged or de-merged,So that I know it has been subject to a non-standard process and can investigate any issues with the data I see
#  Scenario 2: Demerged event should display a Demerged Badge for an RD referral / participant
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Likely inborn error of metabolism | SPECIAL_CHARACTERS | create a new patient record | None | GEL_SUPER_USER |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    Then the user sees the Merge status as "<current status>"
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the success notification "<success notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<latest merge status>"

    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | Rare Disease | GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<Merge status>" on the patient result card
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks on edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button on Patient details page
    And the user clicks the Start Referral button to display the referral page
    Then the user is navigated to a page with title Test Order Forms
#    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "Patient details" stage
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<validSearchDetails>"
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<Merge status>" on the patient result card
    And the user clicks the patient result card
    Then the user is navigated to a page with title Add missing family member details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<validSearchDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    Then the user should not see the merge warning notification banner
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    Examples:
      | current status | current merge status | Merge status | success notification                        | latest merge status | result_message         | tooltipMessage                                                                                                                                                                    | validSearchDetails                  | RelationshipToProband | DiseaseStatusDetails     |
      | None           | None                 | Demerged     | This record's merge status has been updated | Demerged            | 1 patient record found | This record has been the subject of a merge or demerge; additional information not present in the current record may be available by contacting the Genomics England service desk | NHSNumber=9449306583:DOB=21-05-2008 | Father                | DiseaseStatus=Unaffected |


  @NTS_6535_Scenario3 @NTS_6535
  Scenario Outline: As a user,I want to know if a patient recorded has been merged or de-merged,So that I know it has been subject to a non-standard process and can investigate any issues with the data I see
#  Scenario 3: Merged and Demerged event should display a Merged Badge for an RD referral / participant
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Likely inborn error of metabolism | SPECIAL_CHARACTERS | create a new patient record | None | GEL_SUPER_USER |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    Then the user sees the Merge status as "<current status>"
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the success notification "<success notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<latest merge status>"

    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | Rare Disease | GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<merge status on card>" on the patient result card
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks on edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button on Patient details page
    And the user clicks the Start Referral button to display the referral page
#    Then the user is navigated to a page with title Test Order Forms
#    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "Patient details" stage
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<validSearchDetails>"
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should see the merge status "<merge status on card>" on the patient result card
    And the user clicks the patient result card
    Then the user is navigated to a page with title Add missing family member details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the message displayed on the notification banner is "<tooltipMessage>"
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<validSearchDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the message displayed on the notification banner is "<tooltipMessage>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    Then the user should not see the merge warning notification banner
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see the tooltip on the Merge status badge having text "<tooltipMessage>"
    Examples:
      | current status | current merge status | Merge status        | success notification                        | latest merge status | result_message         | tooltipMessage                                                                                                                                                                    | validSearchDetails                  | RelationshipToProband | DiseaseStatusDetails     | merge status on card |
      | None           | None                 | Merged and Demerged | This record's merge status has been updated | Merged and Demerged | 1 patient record found | This record has been the subject of a merge or demerge; additional information not present in the current record may be available by contacting the Genomics England service desk | NHSNumber=9449306680:DOB=14-06-2011 | Father                | DiseaseStatus=Unaffected | Merged               |


  @NTS_6535_Scenario4
  Scenario Outline: None merge status event should no longer display a Merged Badge for an RD referral / participant
  As a user I do not want to see Merged badges / stamps where a participant has an Original merge status in NEAT.

    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Likely inborn error of metabolism | SPECIAL_CHARACTERS | create a new patient record | None | GEL_SUPER_USER |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    Then the user sees the Merge status as "<current status>"
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the success notification "<success notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<latest merge status>"

    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | Rare Disease | GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should not see the merge status on the patient result card
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    Then the user should not see the merge warning notification banner
    And the user clicks on edit patient details
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button on Patient details page
    And the user clicks the Start Referral button to display the referral page
    Then the user is navigated to a page with title Test Order Forms
#    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "Patient details" stage
    Then the user should not see the merge warning notification banner
    When the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<validSearchDetails>"
    Then The patient record is displayed with a heading of "<result_message>"
    Then the user should not see the merge status on the patient result card
    And the user clicks the patient result card
    Then the user is navigated to a page with title Add missing family member details
    Then the user should not see the merge warning notification banner
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    Then the user should not see the merge warning notification banner
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<validSearchDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    Then the user should not see the merge warning notification banner
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    Then the user should not see the merge warning notification banner
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | current status | current merge status | Merge status | success notification                        | latest merge status | result_message         | validSearchDetails                  | RelationshipToProband | DiseaseStatusDetails     |
      | None           | None                 | None         | This record's merge status has been updated | None                | 1 patient record found | NHSNumber=9449306567:DOB=28-05-2011 | Father                | DiseaseStatus=Unaffected |
