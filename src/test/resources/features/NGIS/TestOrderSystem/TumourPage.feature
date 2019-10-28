@tumoursPage
Feature: Tumours Page



    @COMP6_TO_TumourCreate @LOGOUT
    @tumoursPage_01 @NTS-3152 @P @E2EUI-2018 @E2EUI-1840 @v_1
  Scenario Outline:NTS-3152 Future date can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | NGIS | Cancer |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

      Examples: of future date scenario
        | stage   | Date_of_Diagnosis | error_message                    |
        | Tumours | 12-03-2150        | Please enter a date before today |


  @COMP6_TO_TumourCreate @LOGOUT
  @tumoursPage_02 @NTS-3154 @P @E2EUI-1320 @@E2EUI-894 @v_1
  Scenario Outline: NTS-3154: Add a new tumour for an existing patient
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | SPINE | Cancer |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions selecting tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm |
      | Tumours | Solid tumour: metastatic | Recurrence       | test   |


