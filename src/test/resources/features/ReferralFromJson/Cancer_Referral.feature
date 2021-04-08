@ReferralFromJson
Feature: Create Referrals by reading details from Json file

  @CancerReferralFromJson @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    ##Note that the json file is expected to be present in testdata folder
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully

    Examples:
      | JSONFileName           |
      | Cancer_Referral_1.json |