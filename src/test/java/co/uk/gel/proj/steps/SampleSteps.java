package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SampleSteps extends Pages {
    public SampleSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user clicks the Add sample button")
    public void theUserClicksTheAddSampleButton() {
        samplesPage.clickAddSampleButton();
    }

    @And("the user adds a tumour sample by providing sample type {string}")
    public void theUserAddsATumourSampleByProvidingSampleType(String sampleType) {
        samplesPage.selectSampleType(sampleType);
        samplesPage.selectSampleState();
        samplesPage.fillInSampleID();
        referralPage.clickSaveAndContinueButton();
    }

    @And("the Sample page title is shown as {string}")
    public void theSamplePageTitleIsShownAs(String expectedPageTitle) {
        Assert.assertEquals(expectedPageTitle, referralPage.getTheCurrentPageTitle());
    }

    @And("the Sample page has the label text is shown as {string}")
    public void theSamplePageHasTheLabelTextIsShownAs(String expectedDynamicQuestionsLabel) {
        Assert.assertEquals(expectedDynamicQuestionsLabel, samplesPage.getDynamicQuestionsLabelText());
    }

    @And("the user answers the sample dynamic questions by providing topography {string} morphology {string}")
    public void theUserAnswersTheSampleDynamicQuestionsByProvidingTopographyMorphology(String topographyValue, String morphologyValue) {
        samplesPage.answerSampleTopography(topographyValue);
        samplesPage.answerSampleMorphology(morphologyValue);
        samplesPage.fillInPercentageOfMalignantNuclei();
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
        referralPage.clickSaveAndContinueButton();
        Assert.assertTrue(samplesPage.newSampleIsDisplayedInLandingPage());
    }
}
