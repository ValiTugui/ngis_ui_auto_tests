package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static co.uk.gel.lib.Action.getText;

public class SamplesSteps extends Pages {

    public SamplesSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the Manage Samples page displays the page title and sub-titles text body")
    public void theManageSamplesPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        //Debugger.println("Expected Page Title: " + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        if (list.get(0).get("subTitleHeader1").contains("Add")) {
            // Subtitle before adding a sample on Manage Sample page
            //Debugger.println("Expected Subtitle1 : " + list.get(0).get("subTitleHeader1") + " Actual Subtitle1: " + getText(samplesPage.manageSamplesSubTitles.get(0)));
            Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.manageSamplesSubTitles.get(0)));

           // Debugger.println("Expected Subtitle2: " + list.get(0).get("subTitleHeader2") + " Actual Subtitle2: " + getText(samplesPage.manageSamplesSubTitles.get(1)));
            Assert.assertEquals(list.get(0).get("subTitleHeader2"), getText(samplesPage.manageSamplesSubTitles.get(1)));
        } else if (list.get(0).get("subTitleHeader1").contains("Change")) {
            // // Subtitle after adding a sample on Manage Sample page
            //Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.manageSamplesSubTitles.get(0)));
            Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.manageSamplesSubTitles.get(0)));
        }
    }

    @And("the Samples page displays the page title and sub-titles text body")
    public void theSamplesPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        //Debugger.println("Expected Page Title:" + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        //Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.addASampleOrEditASubTitle));
        Assert.assertEquals(list.get(0).get("subTitleHeader1"), getText(samplesPage.addASampleOrEditASubTitle));
    }

    @And("the user clicks the Add sample button")
    public void theUserClicksTheAddSampleButton() {
        boolean testResult = false;
        testResult = samplesPage.clickAddSampleButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_AddSample.jpg");
            Assert.fail("Could not click on Add sample button");
        }
    }

    @And("the user answers the questions on Add a Sample page by selecting the sample type {string}, sample state and filling SampleID")
    public void theUserAnswersTheQuestionsOnAddASamplePageBySelectingTheSampleTypeSampleStateAndFillingSampleID(String sampleType) {
        boolean testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
        samplesPage.selectSampleState();
        testResult = samplesPage.fillInSampleID();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleID.jpg");
            Assert.fail("Could not fillInSampleID");
        }
        PatientDetailsPage.newPatient.setSampleType(sampleType);
    }

    @When("the user answers the questions on Add a Sample page by selecting the sample type {string}, sample state {string} and filling SampleID")
    public void theUserAnswersTheQuestionsOnAddASamplePageBySelectingTheSampleTypeSampleStateAndFillingSampleID(String sampleType, String sampleState) {
        boolean testResult = false;
        testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
        testResult = samplesPage.selectSpecificSampleState(sampleState);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleState.jpg");
            Assert.fail("Could not Select Sample State:"+sampleState);
        }
        testResult = samplesPage.fillInSampleID();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleID.jpg");
            Assert.fail("Could not fillInSampleID");
        }
    }
    //Below step added to retry filling the sample details, if there is any error occurred before.
    @And("the user sees no error message on tumour page by selecting the sample type {string}, sample state {string} and filling SampleID")
    public void thereIsNoErrorMessaegsDisplayedOnTheTumourPage(String sampleType, String sampleState) {
        boolean testResult = false;

        testResult = samplesPage.isErrorPresent();
        if(!testResult){
            return;
        }

        testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
        testResult = samplesPage.selectSpecificSampleState(sampleState);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleState.jpg");
            Assert.fail("Could not Select Sample State:"+sampleState);
        }
        testResult = samplesPage.fillInSampleID();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleID.jpg");
            Assert.fail("Could not fillInSampleID");
        }

        testResult = referralPage.clickSaveAndContinueButton();
        Assert.assertTrue(testResult);
    }

    @And("the Add Samples details page displays the page title and sub-titles text body")
    public void theAddSamplesDetailsPageDisplaysThePageTitleAndSubTitlesTextBody(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        //Debugger.println("Expected Page Title:" + list.get(0).get("pageTitleHeader") + "Actual Page Title: " + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        //Debugger.println("Expected Subtitle1 :" + list.get(0).get("subTitleHeader1") + " Actual Subtitle1:" + getText(samplesPage.addSampleDetailsSubTitle));
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

    @When("the user answers the Samples dynamic questions with tumour content less than 30 percentage {string}")
    public void theUserAnswersTheSamplesDynamicQuestionsByProvidingTumourContentLessThan30Percentage(String sampleTopoMorphyGraphy) {
        samplesPage.answerSampleTopography(sampleTopoMorphyGraphy);
        samplesPage.answerSampleMorphology(sampleTopoMorphyGraphy);
        samplesPage.fillInPercentageOfMalignantNucleiBelow30();
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
    }

    @When("the user answers the Samples dynamic questions by providing tumour content more than 30 percentage {string}")
    public void theUserAnswersTheSamplesDynamicQuestionsByProvidingTumourContentMoreThan30Percentage(String sampleTopoMorphyGraphy) {
        samplesPage.answerSampleTopography(sampleTopoMorphyGraphy);
        samplesPage.answerSampleMorphology(sampleTopoMorphyGraphy);
        samplesPage.fillInPercentageOfMalignantNucleiAbove30();
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
    }

    @And("the user adds a tumour sample by providing sample type {string}")
    public void theUserAddsATumourSampleByProvidingSampleType(String sampleType) {
        boolean testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
        samplesPage.selectSampleState();
        testResult = samplesPage.fillInSampleID();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleID.jpg");
            Assert.fail("Could not fillInSampleID");
        }
        referralPage.clickSaveAndContinueButton();
    }

    @And("the Sample page has the label text is shown as {string}")
    public void theSamplePageHasTheLabelTextIsShownAs(String expectedDynamicQuestionsLabel) {
        Assert.assertEquals(expectedDynamicQuestionsLabel, samplesPage.getDynamicQuestionsLabelText());
    }

    @And("the user answers the sample dynamic questions by providing topography {string} morphology {string}")
    public void theUserAnswersTheSampleDynamicQuestionsByProvidingTopographyMorphology(String topographyValue, String morphologyValue) {
        boolean testResult = false;
        testResult = samplesPage.answerSampleTopography(topographyValue);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Topography.jpg");
            Assert.fail("Could not answer Sample topography."+topographyValue);
        }
        testResult = samplesPage.answerSampleMorphology(morphologyValue);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Morphology.jpg");
            Assert.fail("Could not answer Sample Morphology."+morphologyValue);
        }
        samplesPage.fillInPercentageOfMalignantNuclei();
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
        testResult = referralPage.clickSaveAndContinueButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SaveAndContinue.jpg");
            Assert.fail("Could not Save And continue.");
        }
        testResult = samplesPage.newSampleIsDisplayedInLandingPage();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleNotSipsplayed.jpg");
            Assert.fail("New sample not displayed in landing page.");
        }
    }

    @Then("the new sample is displayed in the landing page")
    public void theNewSampleIsDisplayedInTheLandingPage() {
        int numberOfSamples = samplesPage.numberOfNewSamplesDisplayedInLandingPage();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Organization"," ")+"_Added");
        }
        Assert.assertTrue("Numbers of samples displayed should 1 or great than 1", numberOfSamples > 0);
    }

    @When("the user answers the Samples dynamic questions for non-tumour sample on Add a Sample Details page")
    public void theUserAnswersTheSamplesDynamicQuestionsForNonTumourSampleOnAddASampleDetailsPage() {
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
    }

    @And("the user answers all sample questions on Add a Sample page without the {string}")
    public void theUserAnswersAllSampleQuestionsOnAddASamplePageWithoutThe(String sampleField) {

        switch (sampleField) {
            case "sampleType": {
                samplesPage.selectSampleState();
                samplesPage.fillInSampleID();
                break;
            }
            case "sampleState": {
                boolean testResult = samplesPage.selectSampleType("Normal or germline sample");
                if(!testResult){
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
                    Assert.fail("Could not Select Sample Type:");
                }
                samplesPage.fillInSampleID();
                break;
            }
            case "sampleID": {
                samplesPage.selectSampleType("Normal or germline sample");
                samplesPage.selectSampleState();
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid text field name");
        }
    }

    @Then("the error messages for the sample mandatory fields on Add a Sample page are displayed")
    public void theErrorMessagesForTheSampleMandatoryFieldsOnAddASamplePageAreDisplayed(DataTable dataTable) {
        List<List<String>> expectedLabelsAndErrorMessagesList = dataTable.asLists(String.class);
        List<String> actualFieldErrorMessages = referralPage.getTheListOfFieldsErrorMessagesOnCurrentPage();
        List<String> actualFieldsErrorLabels = samplesPage.getTheListOfFieldsErrorLabelsOnAddASamplePage();

        for (int i = 1; i < expectedLabelsAndErrorMessagesList.size(); i++) { //i starts from 1 because i=0 represents the header
            //Debugger.println("Expected labelHeader " + expectedLabelsAndErrorMessagesList.get(i).get(0));
            //Debugger.println("Actual labelHeader " + actualFieldErrorMessages.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndErrorMessagesList.get(i).get(0), actualFieldsErrorLabels.get(i - 1));

            //Debugger.println("Expected ErrorMessage Header " + expectedLabelsAndErrorMessagesList.get(i).get(1));
           // Debugger.println("Actual ErrorMessage Header " + actualFieldErrorMessages.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndErrorMessagesList.get(i).get(1), actualFieldErrorMessages.get(i - 1));
        }
    }

    @And("the following drop-down values are displayed for Sample types on Add a sample page")
    public void theFollowingDropDownValuesAreDisplayedForSampleTypesOnAddASamplePage(DataTable dataTable) {
        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> expectedSampleTypesList = new ArrayList<>();
        List<String> actualSampleTypesList = samplesPage.getSampleTypesOptions();

        for (int i = 0; i < expectedList.size(); i++) {
            expectedSampleTypesList.add(expectedList.get(i).get("sampleTypesHeader"));
            Debugger.println("Expected samples options: " + i + " : " + expectedSampleTypesList.get(i));
        }
        /* Collections.sort method to sorting the elements of ArrayList in ascending order. */
        Collections.sort(expectedSampleTypesList);
        Collections.sort(actualSampleTypesList);
        for (int i = 0; i < expectedList.size(); i++) {
            //Debugger.println("Expected Sample type values after sorting in ascending order: " + i + " : " + expectedSampleTypesList.get(i));
           // Debugger.println("Actual Sample type values after sorting in ascending order" + i + " : " + actualSampleTypesList.get(i) + "\n");
            Assert.assertEquals(expectedSampleTypesList.get(i), actualSampleTypesList.get(i));
        }
    }

    @And("the Add a Sample page displays the appropriate field elements - sample type, sample state and sampleID")
    public void theAddASamplePageDisplaysTheAppropriateFieldElementsSampleTypeSampleStateAndSampleID() {
        boolean eachElementIsLoaded = false;
        eachElementIsLoaded = samplesPage.verifyTheElementsOnAddASamplePage();
        Assert.assertTrue(eachElementIsLoaded);
    }

    @And("the labels and help hint texts are displayed on Add a Sample page")
    public void theLabelsAndHelpHintTextsAreDisplayedOnAddASamplePage(DataTable dataTable) {

        List<List<String>> expectedLabelsAndHintTextsListMap = dataTable.asLists(String.class);
        List<String> actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List<String> actualHelpHintTexts = referralPage.getTheListOfHelpHintTextsOnCurrentPage();

        /* Add "None" element to the first and second index of actualHelpHintTexts, as Sample type and sample state have no help hint text */
        actualHelpHintTexts.add(0, "None");
        actualHelpHintTexts.add(1, "None");

        for (int i = 1; i < expectedLabelsAndHintTextsListMap.size(); i++) { //i starts from 1 because i=0 represents the header
             Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(0), actualFieldsLabels.get(i - 1));
             Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(1), actualHelpHintTexts.get(i - 1));
        }
    }

    @And("a search icon is displayed inside the Sample state drop down field")
    public void aSearchIconIsDisplayedInsideTheSampleStateDropDownField() {
        boolean testResult = false;
        testResult = samplesPage.verifySearchIconInsideSampleStateField();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleIcon.jpg");
            Assert.fail("verifySearchIconInsideSampleStateField failed");
        }
    }

    @And("fields and drops-downs are shown as mandatory with astericks star symbol")
    public void fieldsAndDropsDownsAreShownAsMandatoryWithAstericksStarSymbol() {

        List<String> actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();

        for (int i = 0; i < actualFieldsLabels.size(); i++) { //i starts from 1 because i=0 represents the header;
            Assert.assertTrue(actualFieldsLabels.get(i).contains("✱"));
        }
    }

    @And("place-holder text is displayed for Sample type, Sample State and SampleID on Add a Sample page")
    public void placeHolderTextIsDisplayedForSampleTypeSampleStateAndSampleIDOnAddASamplePage(DataTable dataTable) {

        List<List<String>> expectedLabelsAndPlaceHolderList = dataTable.asLists(String.class);
        List<String> actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List<String> actualPlaceHolderTexts = samplesPage.getTheFieldPlaceHolderTextOnAddASamplePage();

        for (int i = 1; i < expectedLabelsAndPlaceHolderList.size(); i++) { //i starts from 1 because i=0 represents the header
             Assert.assertEquals(expectedLabelsAndPlaceHolderList.get(i).get(0), actualFieldsLabels.get(i - 1));
             Assert.assertEquals(expectedLabelsAndPlaceHolderList.get(i).get(1), actualPlaceHolderTexts.get(i - 1));
        }
    }

    @And("the sub-page title {string} is displayed on Add a Sample Page")
    public void theSubPageTitleIsDisplayedOnAddASamplePage(String subPageTitle) {
        boolean testResult = false;
        testResult = samplesPage.verifyTheSubPageTitle(subPageTitle);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Subtitle.jpg");
            Assert.fail("Page subtitle not present:"+subPageTitle);
        }
    }


    @And("the expected sub-set of sample-state values are displayed in the Sample state drop-down")
    public void theExpectedSubSetOfSampleStateValuesAreDisplayedInTheSampleStateDropDown(DataTable dataTable) {

        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> expectedSampleStateList = new ArrayList<>();
        List<String> actualSampleStateDropDownValues = samplesPage.getTheSampleStateDropDownValues();

        for (int i = 0; i < expectedList.size(); i++) {
            expectedSampleStateList.add(expectedList.get(i).get("sampleStateHeader"));
            if(!actualSampleStateDropDownValues.contains(expectedSampleStateList.get(i))) {
                Debugger.println("Expected sample states option: " + expectedSampleStateList.get(i)+" Not present in :"+actualSampleStateDropDownValues);
                Assert.assertTrue(false);
            }
        }
    }

    @When("the user selects the existing sample from the landing page by clicking on the chevron right arrow icon")
    public void theUserSelectsTheExistingSampleFromTheLandingPageByClickingOnTheChevronRightArrowIcon() {
        boolean testResult = false;
        testResult = samplesPage.selectSampleFromLandingPage();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EditSample.jpg");
            Assert.fail("Could not Edit Sample from Landing page :");
        }
    }

    @And("the user edits the fields on Edit a Sample page by selecting the sample type {string}, sample state {string} and SampleID")
    public void theUserEditsTheFieldsOnEditASamplePageBySelectingTheSampleTypeSampleStateAndSampleID(String sampleType, String sampleState) {
        boolean testResult = false;
        testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
        testResult = samplesPage.selectSpecificSampleState(sampleState);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleState.jpg");
            Assert.fail("Could not Select Sample State:"+sampleState);
        }
        testResult = samplesPage.fillInSampleID();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleID.jpg");
            Assert.fail("Could not fillInSampleID");
        }
    }

    @Then("the new edited sample details are displayed in the edit sample page")
    public void theNewEditedSampleDetailsAreDisplayedInTheEditSamplePage() {
        List<String> expectectedSampleDetails = samplesPage.getTheExpectedSampleDetails();
        List<String> actualSampleDetails = samplesPage.getTheSampleDetailsOnEditASamplePage();

        for (int i = 0; i < expectectedSampleDetails.size(); i++) {
            //Debugger.println("Expected sampleDetail: " + expectectedSampleDetails.get(i) + ":" + i + ":" + "Actual sampleDetail " + actualSampleDetails.get(i));
            Assert.assertEquals(expectectedSampleDetails.get(i), actualSampleDetails.get(i));
        }
    }

    @And("the user adds a sample as Child sample by selecting a sample row as a Parent Sample on Add a Sample page")
    public void theUserAddsASampleAsChildSampleBySelectingASampleRowAsAParentSampleOnAddASamplePage() {
        samplesPage.selectASampleAsParentSample();
    }

    @And("on the Manage samples page, the sample table list shows the column header names")
    public void onTheManageSamplesPageTheSampleTableListShowsTheColumnHeaderNames(DataTable dataTable) {
        List<List<String>> list = dataTable.asLists(String.class);
        List<String> headerRow = list.get(1);  //i starts from 1 because i=0 represents the header
        List<String> expectedHeaders = new ArrayList<>();
        List actualHeaders = referralPage.getTableColumnHeaders();

        for (int i = 0; i < headerRow.size(); i++) {
            expectedHeaders.add(headerRow.get(i));
            Assert.assertEquals(expectedHeaders.get(i), actualHeaders.get(i));
        }
        //Debugger.println("Expected Table columns: " + expectedHeaders);
        //Debugger.println("Actual Table columns: " + actualHeaders.toString());
        Assert.assertEquals(expectedHeaders, actualHeaders);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Samples");
        }
    }

    @And("on the Manage Samples page, the new sample details are displayed in the sample table list")
    public void onTheManageSamplesPageTheNewSampleDetailsAreDisplayedInTheSampleTableList() {

        List<String> expectedSampleTestData = samplesPage.getTheExpectedSampleDetails();
        List<String> actualSampleTestData = samplesPage.getTheSampleDetailsOnTableList();

        //Debugger.println(("Actual Sample Test Data from list: " + actualSampleTestData));
       // Debugger.println(" Expected SampleTestData for list: " + expectedSampleTestData);

        for (int i = 0; i < expectedSampleTestData.size(); i++) {
            Assert.assertTrue(actualSampleTestData.contains(expectedSampleTestData.get(i)));
        }
        /* Assert if there's Tumour Description */
        /* If non-sample type is selected, Tumour Description value will be null in Sample table list, NULL value will be asserted */
        /* if Sample type selected is of Sample-tumour type, Assert the value in the column "Tumour Description" in SampleTable list */
        String expectedTumourDescription = tumoursPage.getTheCurrentTumourDescription();
        boolean testResult = false;
        testResult = samplesPage.verifyTumourDescriptionIsOnlyDisplayForSampleTumourType(actualSampleTestData, expectedTumourDescription);
        //Debugger.println("test-result for tumourDescription " + testResult);
        Assert.assertTrue(testResult);
    }

    @And("on the Manage Samples page, the child sample's details are properly displayed in the sample table list")
    public void onTheManageSamplesPageTheChildSampleSDetailsAreProperlyDisplayedInTheSampleTableList() {

        List<String> expectedChildSampleTestData = samplesPage.getTheExpectedSampleDetails();
        /* index 0 is for parent and index 1 is for child */
        List<String> actualParentSampleTestData = samplesPage.getTheParentAndChildSampleDetailsOnTableList(0);
        List<String> actualChildSampleTestData = samplesPage.getTheParentAndChildSampleDetailsOnTableList(1);
        Debugger.println("Actual Parent Sample Test Data: " + actualParentSampleTestData);
        Debugger.println("Actual Child Sample Test Data: " + actualChildSampleTestData);
        Debugger.println("Expected Child Sample Test Data: " + expectedChildSampleTestData);

        for (int i = 0; i < expectedChildSampleTestData.size(); i++) {
            Assert.assertTrue(actualChildSampleTestData.contains(expectedChildSampleTestData.get(i)));
        }

        /* Assert if there's Tumour Description */
        /* If non-sample type is selected, Tumour Description value will be null in Sample table list, NULL value will be asserted */
        /* if Sample type selected is of Sample-tumour type, Assert the value in the column "Tumour Description" in SampleTable list */
        String expectedTumourDescription = tumoursPage.getTheCurrentTumourDescription();
        boolean testResult = false;
        testResult = samplesPage.verifyTumourDescriptionIsOnlyDisplayForSampleTumourType(actualChildSampleTestData, expectedTumourDescription);
        Assert.assertTrue(testResult);

        /* Assert Local-Lab ID of Parent match Parent-ID of Child */
        Debugger.println("Lab-Id of Parent : " + actualParentSampleTestData.get(2));
        Debugger.println("Parent-ID of Child : " + actualChildSampleTestData.get(3));
        Assert.assertEquals(actualParentSampleTestData.get(2), actualChildSampleTestData.get(3));
    }

    @When("the user selects a tumour sample type {string} from the system questions page on Add a Sample page")
    public void theUserSelectsATumourSampleTypeFromTheSystemQuestionsPageOnAddASamplePage(String sampleType) {
        boolean testResult = samplesPage.selectSampleType(sampleType);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:"+sampleType);
        }
    }

    @Then("the Add tumour error message is displayed below Sample type field {string}")
    public void theAddTumourErrorMessageIsDisplayedBelowSampleTypeField(String expectedTumourErrorMessage) {

        String actualErrorMessage = samplesPage.getTheDisplayedAddTumourErrorMessage();
        //Debugger.println("Expected error message :" + expectedTumourErrorMessage);
        //Debugger.println("Actual error message :" + actualErrorMessage);
        Assert.assertEquals(expectedTumourErrorMessage, actualErrorMessage);
    }

    @When("the user clicks the Add a tumour link from the error message")
    public void theUserClicksTheAddATumourLinkFromTheErrorMessage() {
        samplesPage.clickAddTumourLinkFromErrorMessage();
    }


    @And("the user sees a hyper-text link message below the linked tumour details {string} on Add a Sample page")
    public void theUserSeesAHyperTextLinkMessageBelowTheLinkedTumourDetailsOnAddASamplePage(String expectedTumourTextLink) {

        String actualTumourTextLink = samplesPage.getTheDisplayedTumourTextLinkOnAddASamplePage();
        //Debugger.println("Expected Tumour text link : " + expectedTumourTextLink);
        //Debugger.println("Actual Tumour text link : " + expectedTumourTextLink);
        Assert.assertEquals(expectedTumourTextLink, actualTumourTextLink);
    }

    @When("the user clicks the Not the right tumour link below the linked tumour details on Add a Sample page")
    public void theUserClicksTheNotTheRightTumourLinkBelowTheLinkedTumourDetailsOnAddASamplePage() {
        samplesPage.clickTheNotTheRightTumourLink();
    }

    @Then("the user sees a text below the the Sample-ID on Add a Sample page {string}")
    public void theUserSeesATextBelowTheTheSampleIDOnAddASamplePage(String expectedLinkedSampleText) {

        String actualLinkedSampleText = samplesPage.getTheDisplayedSampleTextLinkOnAddASamplePage();
        //Debugger.println("Expected Sample text link : " + expectedLinkedSampleText);
        //Debugger.println("Actual Sample text link : " + actualLinkedSampleText);
        Assert.assertEquals(expectedLinkedSampleText, actualLinkedSampleText);
    }

    @And("the Add a Sample Details displays the appropriate field elements for Sample non-Tumour type - sample collection date and sample comments")
    public void theAddASampleDetailsDisplaysTheAppropriateFieldElementsForSampleNonTumourTypeSampleCollectionDateAndSampleComments() {
        boolean testResult = false;
        testResult = samplesPage.verifyTheElementsOnAddSampleDetailsForSampleNonTumourType();
        Assert.assertTrue(testResult);
    }


    @And("the Add a Sample Details displays the appropriate field elements for Sample Tumour type - Sample topography, morphology, Tumour content, number of slides, collection date and sample comments")
    public void theAddASampleDetailsDisplaysTheAppropriateFieldElementsForSampleTumourTypeSampleTopographyMorphologyTumourContentNumberOfSlidesCollectionDateAndSampleComments() {
        boolean testResult = false;
        testResult = samplesPage.verifyTheElementsOnAddSampleDetailsForSampleTumourType();
        Assert.assertTrue(testResult);
    }


    @And("asterisk {string} star symbol is shown as mandatory next to the Tumour content - percentage of malignant field label for only Solid tumour sample")
    public void asteriskStarSymbolIsShownAsMandatoryNextToTheTumourContentPercentageOfMalignantFieldLabelForOnlySolidTumourSample(String expectedTumourContentFieldLabel) {

        String actualTumourContentPercentage = samplesPage.getTheLabelForTumourContentPercentageField();
        //Debugger.println("Actual Tumour Content Field Label: " + actualTumourContentPercentage);
        //Debugger.println("Expected Tumour Content Field Label: " + expectedTumourContentFieldLabel);
        Assert.assertEquals(expectedTumourContentFieldLabel, actualTumourContentPercentage);
    }

    @When("the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search{string} and leaves Tumour content percentage field blank")
    public void theUserAnswersTheSamplesDynamicQuestionsOnAddASampleDetailsPageBySelectingSampleSearchAndLeavesTumourContentPercentageFieldBlank(String sampleTopoMorphyGraphy) {

        samplesPage.answerSampleTopography(sampleTopoMorphyGraphy);
        samplesPage.answerSampleMorphology(sampleTopoMorphyGraphy);
        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
    }

    @But("the {string} stage is marked {string}")
    public void theStageIsMarked(String stage, String stageStatus) {

        switch (stageStatus) {
            case "MandatoryToDo": {
                //Debugger.println(stage + " status stage for Solid tumour Sample is : " + stageStatus);
                boolean testResult = referralPage.stageIsMandatoryToDo(stage);
                if(!testResult){
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"StageNotMandatory.jpg");
                    Assert.fail("Stage "+stage+" not marked as mandatory to do.");
                }
                break;
            }
            case "NotMandatoryToDo": {
                //verifying asterisk and tick mark are not present.
                if (referralPage.stageIsMandatoryToDo(stage) || referralPage.stageIsCompleted(stage)) {
                    Assert.fail("The Panels stage is still mandatory to do or is completed.");
                }
                break;
            }
            case "Completed": {
               // Debugger.println(stage + " status stage for Liquid tumour Sample is : " + stageStatus);
                Assert.assertTrue(referralPage.stageIsCompleted(stage));
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid Stage Status");
        }
    }

    @And("the Sample Collection date field is displayed with label {string}")
    public void theSampleCollectionDateFieldIsDisplayedWithLabel(String expectedSampleCollectionDateLabel) {

        boolean flag = Wait.isElementDisplayed(driver, samplesPage.sampleCollectionDay, 10);
        Assert.assertTrue(flag);
        //String actualSampleCollectionDateLabel = Actions.getText(samplesPage.sampleCollectionDateFieldLabel);
       // Debugger.println("Actual sampleCollection label: " + actualSampleCollectionDateLabel);
      //  Debugger.println("Expected sampleCollection label: " + expectedSampleCollectionDateLabel);
        //Assert.assertEquals(expectedSampleCollectionDateLabel,actualSampleCollectionDateLabel);
    }

    @And("the user is able to enter date in the Sample Collection date field")
    public void theUserIsAbleToEnterDateInTheSampleCollectionDateField() {
          samplesPage.selectSampleCollectionDate();
          //String actualSampleCollectionDate = Actions.getValue(samplesPage.sampleCollectionDay);
         // Debugger.println("Actual sampleCollection Date:" + actualSampleCollectionDate);
          //Assert.assertTrue(!Objects.requireNonNull(getValue(samplesPage.sampleCollectionDateField)).isEmpty()); //Collection field date is not empty
    }

    @And("the user see a tick mark next to the selected parent Sample")
    public void theUserSeeATickMarkNextToTheSelectedParentSample() {
        Assert.assertTrue(samplesPage.ensureTickMarkIsDisplayedNextToSampleType());
    }

    @When("the user edits the added sample")
    public void theUserEditsTheThAddedSample() {
        boolean testResult = false;
        testResult = samplesPage.editSpecificSample();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EditSample.jpg");
            Assert.fail("Could not Edit Sample:");
        }
    }
    @Then("the user sees the Add sample button to add additional samples")
    public void theUserSeesTheAddSampleButtonToAddAdditionalSampleOnPage() {
        boolean testResult = false;
        testResult = samplesPage.verifyAddSampleButtonIsDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Add Sample.jpg");
            Assert.fail("Add Sample Button not displayed");
        }
    }

    @When("the user edits the added sample (.*)")
    public void theUserEditsTheThAddedSample(String sampleNo) {
        boolean testResult = false;
        testResult = samplesPage.editSpecificSample(sampleNo);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EditSample.jpg");
            Assert.fail("Could not edit specific sample :");
        }
    }
}
