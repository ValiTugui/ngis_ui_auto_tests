@ReferralFromJson
Feature: Create Referrals by reading details from Json file

  @RDReferralFromJson  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    ##Note that the json file is expected to be present in testdata folder
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully

    Examples:
      | JSONFileName       |
#      | RD_Referral_2.json |
      | r20210100124_10000_referral_DDF_modified.json |
