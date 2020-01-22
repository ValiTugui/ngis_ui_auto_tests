package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class ClinicalQuestionsSteps extends Pages {
    public ClinicalQuestionsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("there is {string} existing HPO term")
    public void thereIsExistingHPOTerm(String numberOfHPOTerms) {
        Assert.assertTrue(clinicalQuestionsPage.verifyTheCountOfHPOTerms(Integer.parseInt(numberOfHPOTerms)));
    }

    @When("the user adds a new HPO phenotype term {string}")
    public void theUserAddsANewHPOPhenotypeTerm(String newHPOTerm) {
        int actualNumberOfHPOTerms = clinicalQuestionsPage.searchAndSelectRandomHPOPhenotype(newHPOTerm);
    }

    @Then("the new HPO term {string} appears at the top of the list of the HPO terms")
    public void theNewHPOTermAppearsAtTheTopOfTheListOfTheHPOTerms(String expectedHPOTerm) {
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificHPOTermDisplayedInTheFirstRow(expectedHPOTerm));
    }

    @And("the Clinical Questions page is displayed with at least {string} HPO terms in the HPO Phenotype section")
    public void theClinicalQuestionsPageIsDisplayedWithAtLeastHPOTermsInTheHPOPhenotypeSection(String expectedNumberOfHPOTerms) {
        Assert.assertTrue(clinicalQuestionsPage.verifyTheCountOfHPOTerms(Integer.parseInt(expectedNumberOfHPOTerms)));
    }

    @And("the user selects a value {string} from the Rare disease diagnosis")
    public void theUserSelectsAValueFromTheRareDiseaseDiagnosis(String expectedDiagnosis) {
        String actualValue = clinicalQuestionsPage.searchAndSelectSpecificDiagnosis(expectedDiagnosis);
        Assert.assertNotNull(actualValue);
        Assert.assertTrue(actualValue.equalsIgnoreCase(expectedDiagnosis));
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
            Debugger.println("Expected Disease Status value : " + diseaseStatus);
            Debugger.println("Actual   Disease Status value : " + actualDiseaseStatus);
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
        clinicalQuestionsPage.fillInYearsOfOnset(year);
        clinicalQuestionsPage.fillInMonthsOfOnset(month);
   }

    @When("the user provided the values {string} for Age of onset fields")
    public void theUserProvidedTheValuesForAgeOfOnsetFields(String months) {
        clinicalQuestionsPage.fillInMonthsOfOnset(months);
    }

    @When("the user provided the year values {string} for Age of onset fields")
    public void theUserProvidedTheYearValuesForAgeOfOnsetFields(String year) {
        clinicalQuestionsPage.fillInYearsOfOnset(year);
    }

    @And("the user sees an error {string} message on the page")
    public void theUserSeesAnErrorMessageOnThePage(String expectedErrorMessage) {
        String actualErrorMessage = clinicalQuestionsPage.getErrorMessageText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @And("the user does not see an error message on the page")
    public void theUserDoesNotSeeAnErrorMessageOnThePage() {
            Assert.assertFalse(clinicalQuestionsPage.checkNoErrorMessageIsDisplayed());
    }
    @And("the user fills the ClinicalQuestionsPage with the {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        Assert.assertTrue(clinicalQuestionsPage.fillDiseaseStatusAgeOfOnsetAndHPOTerm(searchDetails));
    }

    @And("the HPO phenotype drop-down is allowed to have values up to {string}")
    public void theHPOPhenotypeDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(clinicalQuestionsPage.verifyMaxAllowedValuesHPOField(Integer.parseInt(allowedValuesCount)));
    }

    @And("the OMIM and Oprhanet drop-down is allowed to have values up to {string}")
    public void theOMIMAndOprhanetDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(clinicalQuestionsPage.verifyMaxAllowedValuesOMIMField(Integer.parseInt(allowedValuesCount)));
    }


    @And("the user fills the ClinicalQuestionsPage with the {string} except to the Rare disease diagnosis field")
    public void theUserFillsTheClinicalQuestionsPageWithTheExceptToTheRareDiseaseDiagnosisField(String searchDetails) {
        theUserSearchTheFamilyMemberWithTheSpecifiedDetails(searchDetails);
    }

    @When("the user clears the value that is set on on the close icon  placed in the Disease status field by clicking the close icon")
    public void theUserClearsTheValueThatIsSetOnOnTheCloseIconPlacedInTheDiseaseStatusFieldByClickingTheCloseIcon() {
        clinicalQuestionsPage.clickCloseIcon();
    }

    @Then("the Disease status field is not set with the disease status value {string}")
    public void theDiseaseStatusFieldIsNotSetWithTheDiseaseStatusValue(String searchTerms) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchTerms);
        String previouslyAssignedValueOfDiseaseStatus = paramNameValue.get("DiseaseStatus");
        String expectedDefaultValueOfDiseaseStatus = "Select...";
        String actualValueOfDiseaseStatus = clinicalQuestionsPage.getDefaultValueOfDiseaseStatus();
        Debugger.println("ACTUAL value : " + actualValueOfDiseaseStatus);
        Assert.assertFalse(actualValueOfDiseaseStatus.equals(previouslyAssignedValueOfDiseaseStatus));
        Assert.assertTrue(actualValueOfDiseaseStatus.equals(expectedDefaultValueOfDiseaseStatus));
    }

    @When("the user clicks the delete icon which is displayed across the {string}")
    public void theUserClicksTheDeleteIconWhichIsDisplayedAcrossThe(String hPOTermToBeRemoved) {
     Assert.assertTrue(clinicalQuestionsPage.deleteHPOTerm(hPOTermToBeRemoved));
    }

    @When("the user fills the clinical questions with the {string} except to the Rare disease diagnosis field for the family member")
    public void theUserFillsTheClinicalQuestionsWithTheExceptToTheRareDiseaseDiagnosisFieldForTheFamilyMember(String searchTerms) {
        theUserFillsTheClinicalQuestionsPageWithTheExceptToTheRareDiseaseDiagnosisField(searchTerms);
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

    @And("the user fills the ClinicalQuestionsPage with the {string} except to the disease status field")
    public void theUserFillsTheClinicalQuestionsPageWithTheExceptToTheDiseaseStatusField(String searchTerms) {
        theUserFillsTheClinicalQuestionsPageWithTheExceptToTheRareDiseaseDiagnosisField(searchTerms);
    }

    @And("the user sees the data such as {string} {string} {string} {string} phenotypic and karyotypic sex are saved")
    public void theUserSeesTheDataSuchAsPhenotypicAndKaryotypicSexAreSaved(String expectedDiseaseStatus, String expectedHPOTerm, String searchTerms, String expectedRareDiseaseValue) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchTerms);
        String[] expectedAgeOnSets = paramNameValue.get("AgeOfOnset").split(",");
        String expectedHPOTermInFirstRow = paramNameValue.get("HpoPhenoType");
        Debugger.println("expected age on sets years : " + expectedAgeOnSets[0]);
        Debugger.println("expected age on sets months: " + expectedAgeOnSets[1]);
        // verify Age On Set
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetYearsValue(expectedAgeOnSets[0]));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificAgeOnSetMonthValue(expectedAgeOnSets[1]));
        // verify Disease Status
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificDiseaseStatusValue(expectedDiseaseStatus));
        // verify HPO term
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificHPOTermDisplayed(expectedHPOTermInFirstRow));
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificHPOTermDisplayed(expectedHPOTerm));
        //verify Rare Disease Diagnoses
        Assert.assertTrue(clinicalQuestionsPage.verifySpecificRareDiseaseValue(expectedRareDiseaseValue));
        //verify Phenotypic and karyotypic sex
        Assert.assertNotNull(clinicalQuestionsPage.getPhenotypicSexDropdownValue());
        Assert.assertNotNull(clinicalQuestionsPage.getKaryotypicSexDropdownValue());

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
        String actualValue = clinicalQuestionsPage.selectRareDiseaseDiagnosisType(diagnosisTypeValue);
        Assert.assertTrue(diagnosisTypeValue.equalsIgnoreCase(actualValue));

        String actualStatusValue = clinicalQuestionsPage.selectRareDiseaseStatus(statusValue);
        Assert.assertTrue(statusValue.equalsIgnoreCase(actualStatusValue));
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
}
