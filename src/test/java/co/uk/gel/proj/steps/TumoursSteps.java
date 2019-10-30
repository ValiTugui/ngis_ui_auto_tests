package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NgisPatientTwo;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.TumoursPage;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class TumoursSteps extends Pages {

    public TumoursSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the user enters {string} in the date of diagnosis field")
    public void theUserEntersInTheDateOfDiagnosisField(String dateOfDiagnosis) {

        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
        tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
    }

    @Then("the message will be displayed as {string} in {string} color for the date of diagnosis field")
    public void theMessageWillBeDisplayedAsInColorForTheDateOfDiagnosisField(String errorMessage, String fontColor) {
        patientSearchPage.checkTheErrorMessagesInDOB(errorMessage, fontColor);
    }

    @And("the user answers all tumour system questions by selecting tumour type {string} and leaves date of diagnosis field blank")
    public void theUserAnswersAllTumourSystemQuestionsBySelectingTumourTypeAndLeavesDateOfDiagnosisFieldBlank(String tumourType) {
        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.selectTumourType(tumourType);
        tumoursPage.fillInSpecimenID();

    }

    @And("the user answers the tumour system questions selecting tumour type {string}")
    public void theUserAnswersTheTumourSystemQuestionsSelectingTumourType(String tumourType) {
        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.selectTumourType(tumourType);
        tumoursPage.fillInSpecimenID();
    }

    @And("the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation {string}")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourCoreDataBySelectingTheTumourPresentation(String tumourPresentation) {
        tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(tumourPresentation);
    }


    @And("the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched {string} result drop list")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourDiagnosisBySelectingASnomedCTFromTheSearchedResultDropList(String snomedTest) {
        tumoursPage.answerTumourDiagnosisQuestions(snomedTest);
    }

    @Then("the new tumour is displayed in the landing page")
    public void theNewTumourIsDisplayedInTheLandingPage() {
        Assert.assertTrue(tumoursPage.newTumourIsDisplayedInLandingPage(1));
    }

    @And("the new tumour is not highlighted")
    public void theNewTumourIsNotHighlighted() {
        tumoursPage.tumourIsNotHighlighted();
        tumoursPage.warningMessageIsNotDisplayed();
    }


    @Then("the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc")
    public void theTumoursStageDisplaysAddATumourPageWithAppropriateFieldsDescriptionDateOfDiagnosisEtc() {

        boolean eachElementIsLoaded;
        eachElementIsLoaded = tumoursPage.verifyTheElementsOnAddTumoursPageAreDisplayed();
        Assert.assertTrue(eachElementIsLoaded);

    }

    @And("an information {string} is displayed that a test cannot start without a tumour")
    public void anInformationIsDisplayedThatATestCannotStartWithoutATumour(String tumourInformation) {
        Debugger.println("Tumour subtitle : " + tumoursPage.TumourSubTitle.getText());
        Assert.assertTrue(tumoursPage.TumourSubTitle.getText().contains(tumourInformation));

    }
}