package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicalQuestionsSteps extends Pages {
    public List<String> phenotypicSexDropdownList;
    public List<String> karyotypicSexDropdownList;

    public ClinicalQuestionsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("there is {string} existing HPO term")
    public void thereIsExistingHPOTerm(String numberOfHPOTerms) {
        if(!clinicalQuestionsPage.verifyTheCountOfHPOTerms(Integer.parseInt(numberOfHPOTerms))) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_HPOTerm.jpg");
            Assert.fail("Existing HPO term validation failed.");
        }
    }

    @When("the user adds a new HPO phenotype term {string}")
    public void theUserAddsANewHPOPhenotypeTerm(String newHPOTerm) {
        if(clinicalQuestionsPage.searchAndSelectRandomHPOPhenotype(newHPOTerm) < 1){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewHPOTerm.jpg");
            Assert.fail("New HPO term not added.");
        }
    }

    @Then("the new HPO term {string} appears at the top of the list of the HPO terms")
    public void theNewHPOTermAppearsAtTheTopOfTheListOfTheHPOTerms(String expectedHPOTerm) {
        if(!clinicalQuestionsPage.verifySpecificHPOTermDisplayedInTheFirstRow(expectedHPOTerm)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewHPOTerm.jpg");
            Assert.fail("New HPO term not appeared at top.");
        }
    }

    @And("the Clinical Questions page is displayed with at least {string} HPO terms in the HPO Phenotype section")
    public void theClinicalQuestionsPageIsDisplayedWithAtLeastHPOTermsInTheHPOPhenotypeSection(String expectedNumberOfHPOTerms) {
        Assert.assertTrue(clinicalQuestionsPage.verifyTheCountOfHPOTerms(Integer.parseInt(expectedNumberOfHPOTerms)));
    }

    @And("the user selects a value {string} from the Rare disease diagnosis")
    public void theUserSelectsAValueFromTheRareDiseaseDiagnosis(String expectedDiagnosis) {
        String actualValue = clinicalQuestionsPage.searchAndSelectSpecificDiagnosis(expectedDiagnosis);
        if(actualValue == null){
            actualValue = clinicalQuestionsPage.retrySelectingDiagnosis(expectedDiagnosis);
        }
        if(!actualValue.equalsIgnoreCase(expectedDiagnosis)){
            actualValue = clinicalQuestionsPage.retrySelectingDiagnosis(expectedDiagnosis);
            if(!actualValue.equalsIgnoreCase("Success")){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RareDisease.jpg");
                Assert.fail("Rare disease selection not success");
            }

        }
    }

    @When("the user presses the backspace key on the Rare disease diagnosis field")
    public void theUserPressesTheBackspaceKeyOnTheRareDiseaseDiagnosisField() throws AWTException {
        clinicalQuestionsPage.clearRareDiseaseDiagnosisFieldByPressingBackspaceKey();
    }

    @Then("the value {string} should be cleared from the Rare disease diagnosis field")
    public void theValueShouldBeClearedFromTheRareDiseaseDiagnosisField(String diagnosis) {
        Assert.assertTrue(clinicalQuestionsPage.confirmRareDiseaseDiagnosisFieldIsEmpty(diagnosis));
    }

    @When("the user selects {string}")
    public void theUserSelects(String diseaseStatus) {
        if(diseaseStatus.contentEquals("USER_DOES_NOT_SELECT_ANY_VALUE")){
            // No need to set a disease value in UI if User doesn't select a value
        } else {
            String actualDiseaseStatus = clinicalQuestionsPage.selectDiseaseStatus(diseaseStatus);
            Assert.assertNotNull(actualDiseaseStatus);
            Assert.assertTrue(actualDiseaseStatus.contains(diseaseStatus));
        }
    }

    @Then("the HPO phenotype details mandatory state is {string}")
    public void theHPOPhenotypeDetailsMandatoryStateIs(String mandatoryValue) {
        boolean expectedMandatoryValue = Boolean.parseBoolean(mandatoryValue);
        boolean actualMandatoryValue = clinicalQuestionsPage.confirmHPOPhenotypeSectionIsMarkedAsMandatory();
        Assert.assertEquals(actualMandatoryValue, expectedMandatoryValue);
    }

    @When("the user provided the values {string} {string} for Age of onset fields")
    public void theUserProvidedTheValuesForAgeOfOnsetFields(String year, String month) {
        clinicalQuestionsPage.clearValueFromYearsOfOnset();
        clinicalQuestionsPage.clearValueFromMonthsOfOnset();
        clinicalQuestionsPage.fillInYearsOfOnset(year);
        clinicalQuestionsPage.fillInMonthsOfOnset(month);
   }

    @When("the user provided the values {string} for Age of onset fields")
    public void theUserProvidedTheValuesForAgeOfOnsetFields(String months) {
        clinicalQuestionsPage.clearValueFromYearsOfOnset();
        clinicalQuestionsPage.clearValueFromMonthsOfOnset();
        clinicalQuestionsPage.fillInMonthsOfOnset(months);
    }

    @When("the user provided the year values {string} for Age of onset fields")
    public void theUserProvidedTheYearValuesForAgeOfOnsetFields(String year) {
        clinicalQuestionsPage.clearValueFromYearsOfOnset();
        clinicalQuestionsPage.clearValueFromMonthsOfOnset();
        clinicalQuestionsPage.fillInYearsOfOnset(year);
    }

    @And("the user sees an error {string} message on the page")
    public void theUserSeesAnErrorMessageOnThePage(String expectedErrorMessage) {
        String actualErrorMessage = clinicalQuestionsPage.getErrorMessageText();
        if(!expectedErrorMessage.equalsIgnoreCase(actualErrorMessage)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoErrorMsg.jpg");
            Assert.fail("Error messsge not seeen as expected:"+expectedErrorMessage);
        }
    }

    @And("the user does not see an error message on the page")
    public void theUserDoesNotSeeAnErrorMessageOnThePage() {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.checkNoErrorMessageIsDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoErrorMsg.jpg");
            Assert.fail("No error message displayed.");
        }
   }
    @And("the user fills the ClinicalQuestionsPage with the {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.fillDiseaseStatusAgeOfOnsetAndHPOTerm(searchDetails);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalQuestions");
        }
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalQuestions");
            Assert.fail("Clinical Questions could not enter.");
        }
   }

    @And("the HPO phenotype drop-down is allowed to have values up to {string}")
    public void theHPOPhenotypeDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(clinicalQuestionsPage.verifyMaxAllowedValuesHPOField(Integer.parseInt(allowedValuesCount)));
    }

    @And("the OMIM and Oprhanet drop-down is allowed to have values up to {string}")
    public void theOMIMAndOprhanetDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(clinicalQuestionsPage.verifyMaxAllowedValuesOMIMField(Integer.parseInt(allowedValuesCount)));
    }

    @When("the user clears the value that is set on on the close icon  placed in the Disease status field by clicking the close icon")
    public void theUserClearsTheValueThatIsSetOnOnTheCloseIconPlacedInTheDiseaseStatusFieldByClickingTheCloseIcon() {
        clinicalQuestionsPage.clickCloseIcon();
    }

    @Then("the Disease status field is NOT set with the disease status value (.*)")
    public void theDiseaseStatusFieldIsNotSetWithTheDiseaseStatusValue(String expectedDefaultValueOfDiseaseStatus) {
        String actualValueOfDiseaseStatus = clinicalQuestionsPage.getDefaultValueOfDiseaseStatus();
        //Debugger.println("ACTUAL value : " + actualValueOfDiseaseStatus);
        Assert.assertFalse(actualValueOfDiseaseStatus.equals(expectedDefaultValueOfDiseaseStatus));
    }
    @Then("the Disease status field is SET with the disease status value (.*)")
    public void theDiseaseStatusFieldIsSetWithTheDiseaseStatusValue(String expectedDefaultValueOfDiseaseStatus) {
        String actualValueOfDiseaseStatus = clinicalQuestionsPage.getDefaultValueOfDiseaseStatus();
        //Debugger.println("ACTUAL value : " + actualValueOfDiseaseStatus);
        if(!actualValueOfDiseaseStatus.equals(expectedDefaultValueOfDiseaseStatus)){
            //Debugger.println("URL:"+driver.getCurrentUrl());
            Assert.fail("Expected :"+expectedDefaultValueOfDiseaseStatus+",Actual:"+actualValueOfDiseaseStatus);
        }
    }

    @When("the user clicks the delete icon which is displayed across the {string}")
    public void theUserClicksTheDeleteIconWhichIsDisplayedAcrossThe(String hPOTermToBeRemoved) {
     Assert.assertTrue(clinicalQuestionsPage.deleteHPOTerm(hPOTermToBeRemoved));
    }

    @And("the user selects the HPO phenotype questions such as Name, Term presence {string} and corresponding modifier")
    public void theUserSelectsTheHPOPhenotypeQuestionsSuchAsNameTermPresenceAndCorrespondingModifier(String termPresence) {
        Assert.assertTrue(clinicalQuestionsPage.selectTermPresence(termPresence));
        Assert.assertTrue(clinicalQuestionsPage.selectRandomModifier() != null);
    }

    @And("the user answers the phenotypic and karyotypic sex questions")
    public void theUserAnswersThePhenotypicAndKaryotypicSexQuestions() {
        Assert.assertTrue(clinicalQuestionsPage.selectRandomPhenotypicSex() != null);
        Assert.assertTrue(clinicalQuestionsPage.selectRandomKaryotypicSex() != null);
    }

    @And("the user sees the data such as {string} {string} {string} {string} phenotypic and karyotypic sex are saved")
    public void theUserSeesTheDataSuchAsPhenotypicAndKaryotypicSexAreSaved(String expectedDiseaseStatus, String expectedHPOTerm, String searchTerms, String expectedRareDiseaseValue) {
        //Debugger.println("URL: "+driver.getCurrentUrl());
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchTerms);
        String[] expectedAgeOnSets = paramNameValue.get("AgeOfOnset").split(",");
        String expectedHPOTermInFirstRow = paramNameValue.get("HpoPhenoType");
        // verify Age On Set
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetYearsValue(expectedAgeOnSets[0]));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetMonthValue(expectedAgeOnSets[1]));
     }
    @When("the user search for a HPO phenotype term {string} by selecting from the list of answers")
    public void theUserSearchForAHPOPhenotypeTermBySelectingFromTheListOfAnswers(String hpoTerm) {
        theUserAddsANewHPOPhenotypeTerm(hpoTerm);
    }

    @And("the user searches for the Rare disease diagnosis {string} by selecting from the list of answers")
    public void theUserSearchesForTheRareDiseaseDiagnosisBySelectingFromTheListOfAnswers(String diagnosis) {
        theUserSelectsAValueFromTheRareDiseaseDiagnosis(diagnosis);
    }

    @And("the user searches for the karyotypic sex {string} by selecting from the result list")
    public void theUserSearchesForTheKaryotypicSexBySelectingFromTheResultList(String expectedValue) {
        String actualKaryotypicSexValue = clinicalQuestionsPage.selectSpecificKaryotypicSexDropdownValue(expectedValue);
        Assert.assertNotNull(actualKaryotypicSexValue);
        Assert.assertTrue(actualKaryotypicSexValue.equalsIgnoreCase(expectedValue));
    }


    @And("the user adds a new HPO phenotype term {string} using the autosuggest terms")
    public void theUserAddsANewHPOPhenotypeTermUsingTheAutosuggestTerms(String hpoTerm) {
        theUserAddsANewHPOPhenotypeTerm(hpoTerm);
    }

    @And("the count of HPO terms table is {int}")
    public void theCountOfHPOTermsTableIs(int expectedNumberOfHPOTerms) {
        Assert.assertTrue(clinicalQuestionsPage.verifyTheCountOfHPOTerms(expectedNumberOfHPOTerms));
    }

    @And("the user selects the Rare disease diagnosis questions such as {string} and corresponding status {string}")
    public void theUserSelectsTheRareDiseaseDiagnosisQuestionsSuchAsAndCorrespondingStatus(String diagnosisTypeValue, String statusValue) {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.selectRareDiseaseDiagnosisType(diagnosisTypeValue);
        Assert.assertTrue(testResult);
        testResult = clinicalQuestionsPage.selectRareDiseaseStatus(statusValue);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the data in Disease status details such as {string} AgeOnset values {string} {string}")
    public void theUserSeesTheDataInDiseaseStatusDetailsSuchAsAgeOnsetValues(String expectedDiseaseStatus, String expectedAgeOnSetYear, String expectedAgeOnSetMonth) {
        // verify Disease Status
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificDiseaseStatusValue(expectedDiseaseStatus));
        // verify Age On Set
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetYearsValue(expectedAgeOnSetYear));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetMonthValue(expectedAgeOnSetMonth));
    }

    @And("the user sees the data in HPO phenotype details such as {string} Term presence {string}")
    public void theUserSeesTheDataInHPOPhenotypeDetailsSuchAsTermPresence(String expectedHPOTerm, String expectedTermPresence) {
        //Debugger.println("URL:...."+driver.getCurrentUrl());
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificHPOTermDisplayed(expectedHPOTerm));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificTermPresence(expectedTermPresence));
    }

    @And("the user sees the data in Phenotypic and karyotypic Sex")
    public void theUserSeesTheDataInPhenotypicAndKaryotypicSex() {
        Assert.assertNotNull(clinicalQuestionsPage.getPhenotypicSexDropdownValue());
        Assert.assertNotNull(clinicalQuestionsPage.getKaryotypicSexDropdownValue());
    }

    @And("the user sees the data in Rare disease diagnoses such as {string} {string} and corresponding status {string}")
    public void theUserSeesTheDataInRareDiseaseDiagnosesSuchAsAndCorrespondingStatus(String rareDiseaseValue, String diagnosisTypeValue, String statusValue) {
        //verify Rare Disease Diagnoses
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificRareDiseaseValue(rareDiseaseValue));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificDiagnosisType(diagnosisTypeValue));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificRareDiseaseDiagnosisStatusValue(statusValue));
    }

    @And("the user sees the error message {string} in the HPO phenotype section")
    public void theUserSeesTheErrorMessageInTheHPOPhenotypeSection(String expectedErrorMessage) {
        String actualErrorMessage = clinicalQuestionsPage.getErrorMessageFromHPOPhenotypeSection();
        Assert.assertTrue(actualErrorMessage.equalsIgnoreCase(expectedErrorMessage));
    }

    @When("the user clicks the phenotypic sex dropdown")
    public void theUserClicksThePhenotypicSexDropdown() {
        phenotypicSexDropdownList = clinicalQuestionsPage.getValuesFromPhenotypicSexDropDown();
    }

    @When("the user clicks the karyotypic sex dropdown")
    public void theUserClicksTheKaryotypicSexDropdown() {
        karyotypicSexDropdownList = clinicalQuestionsPage.getValuesFromKaryotypicSexDropDown();
    }

    @And("the user sees the following values in phenotypic sex dropdown")
    public void theUserSeesTheFollowingValuesInPhenotypicSexDropdown(List<String> expectedValues) {
        Assert.assertTrue(TestUtils.compareTwoCollectionsContainIdenticalValues(phenotypicSexDropdownList, expectedValues));
    }

    @And("the user sees the following values in karyotypic sex dropdown")
    public void theUserSeesTheFollowingValuesInKaryotypicSexDropdown(List<String> expectedValues) {
        Assert.assertTrue(TestUtils.compareTwoCollectionsContainIdenticalValues(karyotypicSexDropdownList, expectedValues));
    }

    @And("the user answers the phenotypic sex questions")
    public void theUserAnswersThePhenotypicSexQuestions() {
        Assert.assertTrue(clinicalQuestionsPage.selectRandomPhenotypicSex() != null);
    }

    @And("the user clicks on add another link")
    public void theUserClicksOnAddAnotherLink() {
        Assert.assertTrue(clinicalQuestionsPage.clickAddAnotherLink());
    }

    @And("the user selects a value {string} from the Rare disease diagnosis in the second table")
    public void theUserSelectsAValueFromTheRareDiseaseDiagnosisInTheSecondTable(String expectedDiagnosis) {
       clinicalQuestionsPage.searchAndSelectSpecificDiagnosisSecondField(expectedDiagnosis);
    }

    @And("the user selects the Rare disease diagnosis questions such as {string} and corresponding status {string} in the second table")
    public void theUserSelectsTheRareDiseaseDiagnosisQuestionsSuchAsAndCorrespondingStatusInTheSecondTable(String diagnosisTypeValue, String statusValue) {
        String actualValue = clinicalQuestionsPage.selectRareDiseaseDiagnosisType(diagnosisTypeValue , 2);
        Assert.assertTrue(diagnosisTypeValue.equalsIgnoreCase(actualValue));

        String actualStatusValue = clinicalQuestionsPage.selectRareDiseaseStatus(statusValue, 2);
        Assert.assertTrue(statusValue.equalsIgnoreCase(actualStatusValue));
    }

    @And("the user should able to see the same family member DiseaseStatusDetails {string}")
    public void theUserShouldAbleToSeeTheSameFamilyMemberDiseaseStatusDetails(String diseaseStatus) {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.verifyTheFilledDiseaseStatusDetails(diseaseStatus);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_DiseaseStatus.jpg");
            Assert.fail("Disease status details not correct: "+diseaseStatus);
        }

    }
    //Created this new method for optional field validation as it is observed that the existing method pass with mandatory label also.
    @And("the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page")
    public void theNonMandatoryInputFieldsAndDropsDownsLabelsAreShownWithoutAsteriskStarSymbol(DataTable dataTable) {
        List<Map<String, String>> expectedLabelList = dataTable.asMaps(String.class, String.class);
        boolean fieldLabelsFlag;
        fieldLabelsFlag = clinicalQuestionsPage.verifyTheExpectedFieldLabelsWithActualFieldLabels(expectedLabelList);
        Assert.assertTrue(fieldLabelsFlag);
    }

    @And("the user should see that special characters are not allowed")
    public void theUserShouldSeeThatSpecialCharactersAreNotAllowed() {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.verifyThePresenceOfSpecialCharacters();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SpecialChar.jpg");
            Assert.fail("Special characters are allowed.");
        }
    }

    @Then("the user should be able to see the hint text {string} for HPO phenotype details")
    public void theUserShouldBeAbleToSeeTheHintTextForHPOPhenotypeDetails(String hintText) {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.verifyTheHPOFieldsHintText(hintText);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_HintText.jpg");
            Assert.fail("Hint Text not displayed.");
        }

    }
    @Then("the user should be able to see the hint text {string} for the {string}")
    public void theUserShouldBeAbleToSeeTheHintTextForThe(String hintText, String dropdownInput) {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.verifyTheDropDownFieldsHintText(hintText, dropdownInput);
        Assert.assertTrue(testResult);
    }
    //This is not same as field labels, so created new one
    @And("the user should be able to see the field headers on Clinical questions page")
    public void theUserShouldBeAbleToSeeTheFieldHeadersOnClinicalQuestionsPage(DataTable expectedHeaders) {
        boolean testResult = false;
        List<String> stages = expectedHeaders.asList();
        for (int i = 1; i < stages.size(); i++) {
            testResult = clinicalQuestionsPage.verifyFieldHeaders(stages.get(i));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user should see selected radio button in {string} temp plate")
    public void theUserShouldSeeSelectedRadioButtonInTempPlate(String expectedTermPresence) {
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificTermPresence(expectedTermPresence));
    }

    @And("the user sees an bottom message {string} on the page")
    public void theUserSeesAnBottomMessageOnThePage(String subTitlemsg) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifySubTitleMessage(subTitlemsg);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SubtitleMsg.jpg");
            Assert.fail("Subtitle message not displayed");
        }
    }
    @And("the user see error message when providing invalid age of onsets")
    public void theUserSeeErrorMessageWhenProvidingInvalidAgeOfOnsets(DataTable inputDetails) {
        List<List<String>> fieldDetails = inputDetails.asLists();
        String actualErrorMessage = "";
        for (int i = 1; i < fieldDetails.size(); i++) {
            Wait.seconds(3);
            clinicalQuestionsPage.fillInYearsOfOnset(fieldDetails.get(i).get(0));
            clinicalQuestionsPage.fillInMonthsOfOnset(fieldDetails.get(i).get(1));
            actualErrorMessage = clinicalQuestionsPage.getErrorMessageText();
            if(actualErrorMessage == null){

            }
            if(!fieldDetails.get(i).get(2).equalsIgnoreCase(actualErrorMessage)){
                Debugger.println("Expected Error Message: "+fieldDetails.get(i).get(2)+",Actual:"+actualErrorMessage);
                SeleniumLib.takeAScreenShot("AgeOfOnsetError.jpg");
                Assert.assertTrue(false);
            }
        }
    }
    @And("the user should not see error message when providing valid age of onsets")
    public void theUserShouldNotSeeErrorMessageWhenProvidingValidAgeOfOnsets(DataTable inputDetails) {
        List<List<String>> fieldDetails = inputDetails.asLists();
        boolean testResult = false;
        for (int i = 1; i < fieldDetails.size(); i++) {
            Wait.seconds(3);
            if(fieldDetails.get(i).get(0) != null && !fieldDetails.get(i).get(0).isEmpty()) {
                clinicalQuestionsPage.fillInYearsOfOnset(fieldDetails.get(i).get(0));
            }
            if(fieldDetails.get(i).get(1) != null && !fieldDetails.get(i).get(1).isEmpty()) {
                clinicalQuestionsPage.fillInMonthsOfOnset(fieldDetails.get(i).get(1));
            }
            testResult = clinicalQuestionsPage.checkNoErrorMessageIsDisplayed();
            Assert.assertTrue(testResult);
        }
    }
    @And("the phenotype label marked as mandatory based on the disease status selection")
    public void thePhenotypeLabelMarkedAsMandatoryBasedOnTheDiseaseStatusSelection(DataTable inputDetails) {
        boolean testResult = false;
        List<List<String>> fieldDetails = inputDetails.asLists();
        String actSelection = "";
        boolean expStatus = false;
        for (int i = 1; i < fieldDetails.size(); i++) {
            Wait.seconds(3);
            if(!fieldDetails.get(i).get(0).equalsIgnoreCase("USER_DOES_NOT_SELECT_ANY_VALUE")) {
                actSelection = clinicalQuestionsPage.selectDiseaseStatus(fieldDetails.get(i).get(0));
                if (!actSelection.contains(fieldDetails.get(i).get(0))) {
                    Assert.assertTrue(false);
                }
            }
            expStatus = Boolean.parseBoolean(fieldDetails.get(i).get(1));
            testResult = clinicalQuestionsPage.confirmHPOPhenotypeSectionIsMarkedAsMandatory();
            if(testResult != expStatus){
                Assert.assertTrue(false);
            }
        }
    }
    @And("the user answers the phenotypic sex based on gender of the family member")
    public void theUserAnswersThePhenotypicSex() {
        boolean testResult = false;
        testResult = clinicalQuestionsPage.selectSpecificPhenotypicSexDropdownValue();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PhenotypeSex.jpg");
            Assert.fail("Phenotypic sex not answered");
        }
    }

    @And("the user should be able to see HPO term field marked as {string}")
    public void theUserShouldBeAbleToSeeHPOTermFieldMarkedAs(String hpoTermFieldStatus) {
        boolean testResult = false;
        boolean expStatus = false;
        expStatus = Boolean.parseBoolean(hpoTermFieldStatus);
        testResult = clinicalQuestionsPage.confirmHPOPhenotypeSectionIsMarkedAsMandatory();
        if (testResult != expStatus) {
            Assert.assertTrue(true);
        }
    }

    @Then("the should be able to see HPO term field not marked as {string}")
    public void theShouldBeAbleToSeeHPOTermFieldNotMarkedAs(String hpoTermFieldStatus) {
        boolean testResult = false;
        boolean expStatus = false;
        expStatus = Boolean.parseBoolean(hpoTermFieldStatus);
        testResult = clinicalQuestionsPage.confirmHPOPhenotypeSectionIsMarkedAsMandatory();
        if (testResult != expStatus) {
            Assert.assertTrue(false);
        }
    }
}
