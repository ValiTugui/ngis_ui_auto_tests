package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.ParseException;
import java.util.*;

import static co.uk.gel.lib.Actions.getText;

public class TumoursSteps extends Pages {
    SeleniumLib seleniumLib = new SeleniumLib(driver);

    public TumoursSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the user enters {string} in the date of diagnosis field")
    public void theUserEntersInTheDateOfDiagnosisField(String dateOfDiagnosis) {

        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
        tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
        tumoursPage.tumourTypeLabel.click(); //click on descriptiveName label to move cursor away from dateYear field
    }

    @Then("the message will be displayed as {string} in {string} color for the date of diagnosis field")
    public void theMessageWillBeDisplayedAsInColorForTheDateOfDiagnosisField(String errorMessage, String fontColor) {
        patientSearchPage.checkTheErrorMessagesInDOB(errorMessage, fontColor);
        tumoursPage.clearDateOfDiagnosisFields();
    }

    @And("the user answers all tumour system questions fields, select tumour type {string} and leaves date of diagnosis field blank")
    public void theUserAnswersAllTumourSystemQuestionsFieldsSelectTumourTypeAndLeavesDateOfDiagnosisFieldBlank(String tumourType) {
        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.selectTumourType(tumourType);
        tumoursPage.fillInSpecimenID();

    }

    @And("the user answers the tumour system questions fields and select a tumour type {string}")
    public void theUserAnswersTheTumourSystemQuestionsFieldsAndSelectATumourType(String tumourType) {
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


    @And("the user answers the tumour dynamic questions {string} for Tumour Diagnosis by selecting a SnomedCT from the searched {string} result drop list")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourDiagnosisBySelectingASnomedCTFromTheSearchedResultDropList(String tumourType, String snomedTest) {
        tumoursPage.answerTumourDiagnosisQuestionsBasedOnTumourType(tumourType, snomedTest);
    }

    @Then("the new tumour is displayed in the landing page")
    public void theNewTumourIsDisplayedInTheLandingPage() {
        Assert.assertTrue(tumoursPage.newTumourIsDisplayedInLandingPage(1));
    }


    @Then("the new tumour is displayed in the landing page for the existing patient with tumour list")
    public void theNewTumourIsDisplayedInTheLandingPageForTheExistingPatientWithTumourList() {

        int numberOfTumours = tumoursPage.getTheNumbersOfTumoursDisplayedInLandingPage();
        Debugger.println("Number of Tumour(s) :" + numberOfTumours);
        Assert.assertTrue("Numbers of Tumours displayed should 1 or great than 1", numberOfTumours > 0);
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
        Wait.forElementToBeDisplayed(driver, tumoursPage.TumourSubTitle);
        String actualTumourSubTitle = tumoursPage.TumourSubTitle.getText();
        Debugger.println("Actual Tumour subtitle : " + actualTumourSubTitle);
        String[] expectedTumourSubTitle = tumourInformation.split("-");
        for (int i = 0; i < expectedTumourSubTitle.length; i++) {
            Assert.assertTrue(actualTumourSubTitle.contains(expectedTumourSubTitle[i]));
            Debugger.println("Expected SubTitle: " + i + ": " + expectedTumourSubTitle[i]);
        }

    }


    @And("the web browser is still at the same {string} page")
    public void theWebBrowserIsStillAtTheSamePage(String partCurrentUrl) {
        String fullCurrentURL = driver.getCurrentUrl();
        partCurrentUrl = partCurrentUrl.toLowerCase();
        Assert.assertTrue(fullCurrentURL.contains(partCurrentUrl));

    }


    @And("the user answers all tumour system questions without selecting any tumour type")
    public void theUserAnswersAllTumourSystemQuestionsWithoutSelectingAnyTumourType() {
        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.fillInSpecimenID();
    }

    @And("the tumours stage is at Add a Tumour page")
    public void theTumoursStageIsAtAddATumourPage() {
        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
    }


    @Then("the error messages for the tumour mandatory fields are displayed")
    public void theErrorMessagesForTheTumourMandatoryFieldsAreDisplayed(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < list.size(); i++) {
            Debugger.println("Expected: " + list.get(i).get("errorMessageHeader") + " : " + "Actual: " + tumoursPage.errorMessages.get(i).getText());
            Assert.assertEquals(list.get(i).get("errorMessageHeader"), getText(tumoursPage.errorMessages.get(i)));
        }
    }

    @And("the tumour stage is on select or edit a tumour page showing")
    public void theTumourStageIsOnSelectOrEditATumourPageShowing(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        Debugger.println("Expected :" + list.get(0).get("pageTitleHeader") + " Actual:" + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        if (list.get(0).get("notificationTextHeader").equalsIgnoreCase("None")) { //assert that notification success is not displayed
            // Boolean flagStatus =  tumoursPage.checkNotificationElementIsNotPresent();
            boolean flagStatus = seleniumLib.isElementPresent(tumoursPage.successNotification);
            Debugger.println("Success notification Element is displayed but it's not meant to be displayed " + flagStatus);
            Assert.assertFalse("Success notification Element is not displayed", flagStatus);

        } else {
            Debugger.println("Expected :" + list.get(0).get("notificationTextHeader") + " Actual:" + getText(tumoursPage.successNotification));
            Assert.assertEquals(list.get(0).get("notificationTextHeader"), getText(tumoursPage.successNotification));
        }

        Debugger.println("Expected :" + list.get(0).get("textInformationHeader") + " Actual:" + getText(tumoursPage.tumourInformationText));
        Assert.assertEquals(list.get(0).get("textInformationHeader"), getText(tumoursPage.tumourInformationText));

        Debugger.println("Expected :" + list.get(0).get("linkToAddANewTumourHeader") + " Actual:" + getText(tumoursPage.addAnotherTumourLink));
        Assert.assertEquals(list.get(0).get("linkToAddANewTumourHeader"), getText(tumoursPage.addAnotherTumourLink));

        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));
        int actualListOfTumours = tumoursPage.listOfTumoursInTheTable.size();
        Debugger.println("Expected: " + list.get(0).get("NumberOfTumoursAdded") + " Actual: " + tumoursPage.listOfTumoursInTheTable.size());
        Assert.assertEquals(expectedListOfTumours, actualListOfTumours);
    }


    @And("information text are displayed on the select or edit a tumour page")
    public void informationTextAreDisplayedOnTheTheSelectOrEditATumourPage(DataTable dataTable) {

        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> expectedInformationText = new ArrayList<>();
        List<String> actualInformationText = tumoursPage.getInformationTextOnEditTumourPage();

        for (int i = 0; i < expectedList.size(); i++) {
            expectedInformationText.add(expectedList.get(i).get("informationTextHeader"));
            Debugger.println("Expected : " + i + " : " + expectedInformationText.get(i));
        }

        for (int i = 0; i < actualInformationText.size(); i++) {
            Debugger.println("Actual : " + i + " : " + actualInformationText.get(i));
        }

        Assert.assertEquals(expectedInformationText.get(0), actualInformationText.get(0));
        Assert.assertTrue(actualInformationText.get(1).contains(expectedInformationText.get(1)));
        Assert.assertTrue(actualInformationText.get(1).contains(expectedInformationText.get(2)));
        Assert.assertTrue(actualInformationText.get(1).contains(expectedInformationText.get(3)));
        Assert.assertTrue(actualInformationText.get(2).contains(expectedInformationText.get(4)));
    }

    @And("on the select or edit a tumour page, the tumour table list shows the column names")
    public void onTheSelectOrEditATumourPageTheTumourTableListShowsTheColumnNames(DataTable dataTable) {

        List<List<String>> list = dataTable.asLists(String.class);
        List<String> headerRow = list.get(1);  //i starts from 1 because i=0 represents the header
        List<String> expectedHeaders = new ArrayList<>();
        List<String> actualHeaders = new ArrayList<>();

        for (int i = 0; i < headerRow.size(); i++) {
            expectedHeaders.add(headerRow.get(i));
        }
        Debugger.println("Expected Table columns: " + expectedHeaders);
        actualHeaders = tumoursPage.getTumourTableHeaders();
        Debugger.println("Actual Table columns: " + actualHeaders.toString());
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

    @And("the new tumour is added as a list, with a checked radio button and a chevron right arrow icon")
    public void theNewTumourIsAddedAsAListWithACheckedRadioButtonAndAChevronRightArrowIcon() {

        Assert.assertTrue(tumoursPage.editTumourArrow.isDisplayed());
        Assert.assertTrue(tumoursPage.checkedRadioButton.isDisplayed());
    }


    @And("the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon")
    public void theUserSelectsTheExistingTumourFromTheLandingPageByClickingOnTheChevronRightArrowIcon() {

        tumoursPage.clickEditTumourArrow();
    }

    @And("the user edits the tumour system questions fields and select a new tumour type {string}")
    public void theUserEditsTheTumourSystemQuestionsFieldsAndSelectANewTumourType(String tumourType) {
        tumoursPage.editTumourDescription();
        tumoursPage.editDateOfDiagnosis();
        tumoursPage.selectTumourType(tumourType);
        tumoursPage.editSpecimenID();
    }

    @And("on the select or edit a tumour page, the new tumour details are displayed in the tumour table list")
    public void onTheSelectOrEditATumourPageTheNewTumourDetailsAreDisplayedInTheTumourTableList() {

        List<String> expectedTumourTestData;
        List<String> actualTumourTestData;

        expectedTumourTestData = tumoursPage.getExpectedTumourTestDataForAddATumourPage();
        Debugger.println("Expected TumourTestData : " + expectedTumourTestData);
        actualTumourTestData = tumoursPage.getTheTumourDetailsOnTableList();
        Debugger.println("Actual TumourTestData:" + actualTumourTestData);

        Assert.assertEquals(expectedTumourTestData, actualTumourTestData);

    }

    @And("the {string} page is displayed")
    public void thePageIsDisplayed(String expectedPageTitle) {
        String actualPageTitle = referralPage.getTheCurrentPageTitle();
        Debugger.println("Actual PageTitle : " + actualPageTitle);
        Debugger.println("Expected PageTitle : " + expectedPageTitle);
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @And("the new tumour details are displayed in the Edit a Tumour page")
    public void theNewTumourDetailsAreDisplayedInTheEditATumourPage() {

        List<String> expectedTumourTestData;
        List<String> actualTumourTestData;

        expectedTumourTestData = tumoursPage.getTheTumourDetailsOnEditATumourPage();
        Debugger.println("Expected TumourTestData : " + expectedTumourTestData);
        actualTumourTestData = tumoursPage.getTheExpectedTumourDetailsForAddATumourPage();
        Debugger.println("Actual TumourTestData on Edit a Tumour Pge:" + actualTumourTestData);

        Assert.assertEquals(expectedTumourTestData, actualTumourTestData);

    }

    @And("the success notification is displayed {string}")
    public void theSuccessNotificationIsDisplayed(String notificationText) {

        String actualNotificationText = tumoursPage.successNotificationIsDisplayed();
        Debugger.println("Actual Notification text :" + actualNotificationText);

        Debugger.println("Expected Notification text :" + notificationText);

    }

    @And("the labels and help hint texts are displayed")
    public void theLabelsAndHelpHintTextsAreDisplayed(DataTable dataTable) {


        List<List<String>> expectedLabelsAndHintTextsListMap = dataTable.asLists(String.class);
        List<String> actualHelpHintTexts = referralPage.getTheListOfHelpHintTextsOnCurrentPage();
        List<String> actualFieldsLabels = tumoursPage.getTheTumourFieldsLabelsOnAddATumourPage();
   
        /* Add "None" element to the fourth index of actualHelpHintTexts, as Tumour type has no help hint text */
        actualHelpHintTexts.add(3, "None");

        for(int i=1; i < expectedLabelsAndHintTextsListMap.size(); i++) { //i starts from 1 because i=0 represents the header
            Debugger.println("Expected labelHeader " + expectedLabelsAndHintTextsListMap.get(i).get(0));
            Debugger.println("Actual labelHeader " + actualFieldsLabels.get(i-1) + "\n");
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(0), actualFieldsLabels.get(i-1));

            Debugger.println("Expected HintTextHeader " + expectedLabelsAndHintTextsListMap.get(i).get(1));
            Debugger.println("Actual HintTextHeader " + actualHelpHintTexts.get(i-1) + "\n");
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(1), actualHelpHintTexts.get(i-1));
        }


    }


    @And("the user answers the tumour system specific question fields - Description, Select a tumour type {string} and Pathology Sample ID")
    public void theUserAnswersTheTumourSystemSpecificQuestionFieldsDescriptionSelectATumourTypeAndPathologySampleID(String tumourType) {

        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.selectTumourType(tumourType);
        tumoursPage.fillInSpecimenID();
    }

    @And("the user enters {string} in the Pathology Sample ID field")
    public void theUserEntersInThePathologySampleIDField(String pathologySampleId) {
        Actions.fillInValue(tumoursPage.pathologyReportId, pathologySampleId);
    }


    @And("the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type {string}")
    public void theUserAnswersTheTumourSystemSpecificQuestionFieldsDescriptionDateOfDiagnosisAmdSelectATumourType(String tumourType) {

        tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        tumoursPage.fillInTumourDescription();
        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.selectTumourType(tumourType);
    }

    @And("the new tumour details added in the tumour list are indicated as in-complete with {string} fonts colour")
    public void theNewTumourDetailsAddedInTheTumourListAreIndicatedAsInCompleteWithFontsColour(String fontsColor) {

        String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontsColor);
        for (int i = 0; i < tumoursPage.newlyAddedTumourDetailsList.size(); i++) {
            Debugger.println("Expected fonts colour for: " + getText(tumoursPage.newlyAddedTumourDetailsList.get(i)) + " : " + expectedFontColor);
            Debugger.println("Actual fonts colour: " + tumoursPage.newlyAddedTumourDetailsList.get(i).getCssValue("color"));
            Assert.assertEquals(expectedFontColor, tumoursPage.newlyAddedTumourDetailsList.get(i).getCssValue("color"));
        }
    }

    @And("the error message {string} is displayed in {string} fonts colour in the page")
    public void theErrorMessageIsDisplayedInFontsColourInThePage(String errorMessage, String messageColor) {

        Debugger.println("Expected error message: " + errorMessage);
        Debugger.println("Actual Error message: " + getText(tumoursPage.errorMessages.get(0)));
        boolean testResult = false;
        testResult = familyMemberDetailsPage.checkTheErrorMessageForInvalidField(errorMessage, messageColor);
        Assert.assertTrue(testResult);
    }

    @When("user clicks add a new tumour link")
    public void userClicksAddANewTumourLink() {
        tumoursPage.clickOnTheAddANewTumourTextLink();
    }


    @And("the user adds a new tumour")
    public void theUserAddsANewTumour(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));

        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader"));
        tumoursPage.fillInSpecimenID();
        referralPage.clickSaveAndContinueButton();
        tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
        tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
        referralPage.clickSaveAndContinueButton();
        tumoursPage.newTumourIsDisplayedInLandingPage(expectedListOfTumours);

    }
}
