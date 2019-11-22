package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static co.uk.gel.lib.Actions.getText;

public class SamplesSteps extends Pages {

    public SamplesSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the Manage Samples page displays the page title and sub-titles text body")
    public void theManageSamplesPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        Debugger.println("Expected Page Title: " + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        if (list.get(0).get("subTitleHeader1").contains("Add")) {
            // Subtitle before adding a sample on Manage Sample page
            Debugger.println("Expected Subtitle1 : " + list.get(0).get("subTitleHeader1") + " Actual Subtitle1: " + getText(samplesPage.manageSamplesSubTitles.get(0)));
            Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.manageSamplesSubTitles.get(0)));

            Debugger.println("Expected Subtitle2: " + list.get(0).get("subTitleHeader2") + " Actual Subtitle2: " + getText(samplesPage.manageSamplesSubTitles.get(1)));
            Assert.assertEquals(list.get(0).get("subTitleHeader2"), getText(samplesPage.manageSamplesSubTitles.get(1)));
        } else if (list.get(0).get("subTitleHeader1").contains("Change")) {
            // // Subtitle after adding a sample on Manage Sample page
            Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.manageSamplesSubTitles.get(0)));
            Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.manageSamplesSubTitles.get(0)));
        }
    }

    @And("the Samples page displays the page title and sub-titles text body")
    public void theSamplesPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        Debugger.println("Expected Page Title:" + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.addASampleOrEditASubTitle));
        Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.addASampleOrEditASubTitle));
    }

    @And("the user clicks the Add sample button")
    public void theUserClicksTheAddSampleButton() {
        samplesPage.clickAddSampleButton();
    }

    @And("the user answers the questions on Add a Sample page by selecting the sample type {string}, sample state and filling SampleID")
    public void theUserAnswersTheQuestionsOnAddASamplePageBySelectingTheSampleTypeSampleStateAndFillingSampleID(String sampleType) {
        samplesPage.selectSampleType(sampleType);
        samplesPage.selectSampleState();
        samplesPage.fillInSampleID();
    }

    @And("the Add Samples details page displays the page title and sub-titles text body")
    public void theAddSamplesDetailsPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        Debugger.println("Expected Page Title:" + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.addSampleDetailsSubTitle));
        Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.addSampleDetailsSubTitle));
    }


    @When("the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search{string}")
    public void theUserAnswersTheSamplesDynamicQuestionsOnAddASampleDetailsPageBySelectingSampleSearch(String sampleTopoMorphyGraphy) {
        samplesPage.answerSampleTopography(sampleTopoMorphyGraphy);
        samplesPage.answerSampleMorphology(sampleTopoMorphyGraphy);
        samplesPage.fillInPercentageOfMalignantNuclei();
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();

    }

    @And("the user adds a tumour sample by providing sample type {string}")
    public void theUserAddsATumourSampleByProvidingSampleType(String sampleType) {
        samplesPage.selectSampleType(sampleType);
        samplesPage.selectSampleState();
        samplesPage.fillInSampleID();
        referralPage.clickSaveAndContinueButton();
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
