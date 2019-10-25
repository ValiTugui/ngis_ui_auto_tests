@tumoursPage
Feature: Tumours Page


  @LOGOUT_BEFORE_TEST @v_1
    @COMP6_TO_TumourCreate
    @tumoursPage_01 @NTS-3152 @P @E2EUI-2018
  Scenario Outline:NTS-3152 Future date can't be entered in the Date of diagnosis field from the Add a tumour page
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | NGIS | Cancer |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples: of future date scenario
      | stage   | Date_of_Diagnosis | error_message                    |
      | Tumours | 12-03-2150        | Please enter a date before today |

