package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
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

    @When("the user answers the questions on Add a Sample page by selecting the sample type {string}, sample state {string} and filling SampleID")
    public void theUserAnswersTheQuestionsOnAddASamplePageBySelectingTheSampleTypeSampleStateAndFillingSampleID(String sampleType, String sampleState) {
        samplesPage.selectSampleType(sampleType);
        samplesPage.selectSampleState(sampleState);
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

    @Then("the new sample is displayed in the landing page")
    public void theNewSampleIsDisplayedInTheLandingPage() {
        int numberOfSamples = samplesPage.numberOfNewSamplesDisplayedInLandingPage();
        Debugger.println("Number of sample(s) :" + numberOfSamples);
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
                samplesPage.selectSampleType("Omics sample");
                samplesPage.fillInSampleID();
                break;
            }
            case "sampleID": {
                samplesPage.selectSampleType("Omics sample");
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
            Debugger.println("Expected labelHeader " + expectedLabelsAndErrorMessagesList.get(i).get(0));
            Debugger.println("Actual labelHeader " + actualFieldErrorMessages.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndErrorMessagesList.get(i).get(0), actualFieldsErrorLabels.get(i - 1));

            Debugger.println("Expected ErrorMessage Header " + expectedLabelsAndErrorMessagesList.get(i).get(1));
            Debugger.println("Actual ErrorMessage Header " + actualFieldErrorMessages.get(i - 1) + "\n");
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
            Debugger.println("Expected Sample type values after sorting in ascending order: " + i + " : " + expectedSampleTypesList.get(i));
            Debugger.println("Actual Sample type values after sorting in ascending order" + i + " : " + actualSampleTypesList.get(i) + "\n");
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
            Debugger.println("Expected labelHeader " + expectedLabelsAndHintTextsListMap.get(i).get(0));
            Debugger.println("Actual labelHeader " + actualFieldsLabels.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(0), actualFieldsLabels.get(i - 1));

            Debugger.println("Expected HintTextHeader " + expectedLabelsAndHintTextsListMap.get(i).get(1));
            Debugger.println("Actual HintTextHeader " + actualHelpHintTexts.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(1), actualHelpHintTexts.get(i - 1));
        }
    }

    @And("a search icon is displayed inside the Sample state drop down field")
    public void aSearchIconIsDisplayedInsideTheSampleStateDropDownField() {

        boolean searchIconShown = false;
        searchIconShown = samplesPage.verifySearchIconInsideSampleStateField();
        Debugger.println("Return boolean for element present: " + searchIconShown);
        Assert.assertTrue(searchIconShown);
    }

    @And("fields and drops-downs are shown as mandatory with astericks star symbol")
    public void fieldsAndDropsDownsAreShownAsMandatoryWithAstericksStarSymbol() {

        List<String> actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();

        for (int i = 0; i < actualFieldsLabels.size(); i++) { //i starts from 1 because i=0 represents the header;
            Debugger.println("Actual fields labels on Add a Sample page :" + actualFieldsLabels.get(i) + "\n");
            Assert.assertTrue(actualFieldsLabels.get(i).contains("✱"));
        }
    }

    @And("place-holder text is displayed for Sample type, Sample State and SampleID on Add a Sample page")
    public void placeHolderTextIsDisplayedForSampleTypeSampleStateAndSampleIDOnAddASamplePage(DataTable dataTable) {

        List<List<String>> expectedLabelsAndPlaceHolderList = dataTable.asLists(String.class);
        List<String> actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List<String> actualPlaceHolderTexts = samplesPage.getTheFieldPlaceHolderTextOnAddASamplePage();

        for (int i = 1; i < expectedLabelsAndPlaceHolderList.size(); i++) { //i starts from 1 because i=0 represents the header
            Debugger.println("Expected labelHeader " + expectedLabelsAndPlaceHolderList.get(i).get(0));
            Debugger.println("Actual labelHeader " + actualFieldsLabels.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndPlaceHolderList.get(i).get(0), actualFieldsLabels.get(i - 1));

            Debugger.println("Expected PlaceHolderText " + expectedLabelsAndPlaceHolderList.get(i).get(1));
            Debugger.println("Actual PlaceHolderText " + actualPlaceHolderTexts.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndPlaceHolderList.get(i).get(1), actualPlaceHolderTexts.get(i - 1));
        }
    }

    @And("the sub-page title {string} is displayed on Add a Sample Page")
    public void theSubPageTitleIsDisplayedOnAddASamplePage(String subPageTitle) {
        samplesPage.verifyTheSubPageTitle(subPageTitle);
    }


    @And("the expected sub-set of sample-state values are displayed in the Sample state drop-down")
    public void theExpectedSubSetOfSampleStateValuesAreDisplayedInTheSampleStateDropDown(DataTable dataTable) {

        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> expectedSampleStateList = new ArrayList<>();
        List<String> actualSampleStateDropDownValues = samplesPage.getTheSampleStateDropDownValues();

        for (int i = 0; i < expectedList.size(); i++) {
            expectedSampleStateList.add(expectedList.get(i).get("sampleStateHeader"));
            Debugger.println("Expected sample states options: " + i + " : " + expectedSampleStateList.get(i));
            Assert.assertTrue(actualSampleStateDropDownValues.contains(expectedSampleStateList.get(i)));
        }
    }

    @When("the user selects the existing sample from the landing page by clicking on the chevron right arrow icon")
    public void theUserSelectsTheExistingSampleFromTheLandingPageByClickingOnTheChevronRightArrowIcon() {
        samplesPage.selectSampleFromLandingPage();
    }

    @And("the user edits the fields on Edit a Sample page by selecting the sample type {string}, sample state {string} and SampleID")
    public void theUserEditsTheFieldsOnEditASamplePageBySelectingTheSampleTypeSampleStateAndSampleID(String sampleType, String sampleState) {
        samplesPage.selectSampleType(sampleType);
        samplesPage.selectSampleState(sampleState);
        samplesPage.fillInSampleID();
    }

    @Then("the new edited sample details are displayed in the edit sample page")
    public void theNewEditedSampleDetailsAreDisplayedInTheEditSamplePage() {
        List<String> expectectedSampleDetails = samplesPage.getTheExpectedSampleDetails();
        List<String> actualSampleDetails = samplesPage.getTheSampleDetailsOnEditASamplePage();

        for (int i = 0; i < expectectedSampleDetails.size(); i++) {
            Debugger.println("Expected sampleDetail: " + expectectedSampleDetails.get(i) + ":" + i + ":" + "Actual sampleDetail " + actualSampleDetails.get(i));
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
            Debugger.println("Expected Sample Table columns: " + expectedHeaders.get(i));
            Debugger.println("Actual Sample Table columns: " + actualHeaders.get(i));
            Assert.assertEquals(expectedHeaders.get(i), actualHeaders.get(i));
        }
        Debugger.println("Expected Table columns: " + expectedHeaders);
        Debugger.println("Actual Table columns: " + actualHeaders.toString());
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

    @And("on the Manage Samples page, the new sample details are displayed in the sample table list")
    public void onTheManageSamplesPageTheNewSampleDetailsAreDisplayedInTheSampleTableList() {

        List<String> expectedSampleTestData = samplesPage.getTheExpectedSampleDetails();
        List<String> actualSampleTestData = samplesPage.getTheSampleDetailsOnTableList();

        Debugger.println(("Actual Sample Test Data from list: " + actualSampleTestData));
        Debugger.println(" Expected SampleTestData for list: " + expectedSampleTestData);

        for (int i = 0; i < expectedSampleTestData.size(); i++) {
            Assert.assertTrue(actualSampleTestData.contains(expectedSampleTestData.get(i)));
        }
        /* Assert if there's Tumour Description */
        /* If non-sample type is selected, Tumour Description value will be null in Sample table list, NULL value will be asserted */
        /* if Sample type selected is of Sample-tumour type, Assert the value in the column "Tumour Description" in SampleTable list */
        String expectedTumourDescription = tumoursPage.getTheCurrentTumourDescription();
        boolean testResult = false;
        testResult = samplesPage.verifyTumourDescriptionIsOnlyDisplayForSampleTumourType(actualSampleTestData, expectedTumourDescription);
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
        samplesPage.selectSampleType(sampleType);
    }

    @Then("the Add tumour error message is displayed below Sample type field {string}")
    public void theAddTumourErrorMessageIsDisplayedBelowSampleTypeField(String expectedTumourErrorMessage) {

        String actualErrorMessage = samplesPage.getTheDisplayedAddTumourErrorMessage();
        Debugger.println("Expected error message :" + expectedTumourErrorMessage);
        Debugger.println("Actual error message :" + actualErrorMessage);
        Assert.assertEquals(expectedTumourErrorMessage, actualErrorMessage);
    }

    @When("the user clicks the Add a tumour link from the error message")
    public void theUserClicksTheAddATumourLinkFromTheErrorMessage() {
        samplesPage.clickAddTumourLinkFromErrorMessage();
    }

}
