package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

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
        if (dateOfDiagnosis.equalsIgnoreCase("14-0-1899")){
            String[] value = dateOfDiagnosis.split("-");
            tumoursPage.fillInDateOfDiagnosisInDifferentOrder(value[0], value[1], value[2]);
        }else {
            String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
            tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
            Actions.retryClickAndIgnoreElementInterception(driver,tumoursPage.tumourTypeLabel);
            //click on tumourTypeLabel label to move cursor away from dateYear field
        }
    }

    @Then("the message will be displayed as {string} in {string} color for the date of diagnosis field")
    public void theMessageWillBeDisplayedAsInColorForTheDateOfDiagnosisField(String errorMessage, String fontColor) {
        patientSearchPage.checkTheErrorMessagesInDOB(errorMessage, fontColor);
        tumoursPage.clearDateOfDiagnosisFields();
        Wait.forElementToBeClickable(driver, referralPage.logoutButton);
    }
    @Then("the DateOfDiagnosis field displays given messages in specific color for the wrong values")
    public void theDateOfDiagnosisFieldGivesProperErrorMessages(DataTable inputDetails) {
        try {
            boolean testResult = false;
            tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            List<List<String>> diagnosisDates = inputDetails.asLists();
            String dateOfDiagnosis = "";
            for(int i=1; i<diagnosisDates.size(); i++){
                dateOfDiagnosis = diagnosisDates.get(i).get(0);
                Debugger.println("DateOfDiagnosis: "+dateOfDiagnosis);
                if (dateOfDiagnosis.equalsIgnoreCase("14-0-1899")){
                    String[] value = dateOfDiagnosis.split("-");
                    tumoursPage.fillInDateOfDiagnosisInDifferentOrder(value[0], value[1], value[2]);
                }else {
                    String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                    tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                    //Actions.retryClickAndIgnoreElementInterception(driver, tumoursPage.tumourTypeLabel);
                }
                patientSearchPage.checkTheErrorMessagesInDOB(diagnosisDates.get(i).get(1),diagnosisDates.get(i).get(2));
                tumoursPage.clearDateOfDiagnosisFields();
                Wait.seconds(2);
                Debugger.println("PASS");
                //click on tumourTypeLabel label to move cursor away from dateYear field
            }
        }catch(Exception exp){
            Debugger.println("Exception in validating theDateOfDiagnosisFieldGivesProperErrorMessages: "+exp);
        }
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
        try {
            tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            tumoursPage.fillInTumourDescription();
            tumoursPage.fillInDateOfDiagnosis();
            String tumour = tumoursPage.selectTumourType(tumourType);
            PatientDetailsPage.newPatient.setTumourType(tumour);
            tumoursPage.fillInSpecimenID();
            Wait.seconds(5);//Observed timeout in next step, so introducing a wait fo 5 seconds.
        }catch(Exception exp){
            Debugger.println("Exception from Answering Tumour Question Field: "+exp);
        }
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
        if (expectedPageTitle.equals("Add sample details")) {
            try {
                Wait.seconds(2);
                Wait.forElementToBeDisplayed(driver, samplesPage.addSampleDetailsSubHeading);
                Debugger.println("Sub heading - Sample Details found :");
            } catch (TimeoutException exp) {
                SeleniumLib.takeAScreenShot("AddSampleDetailsPage.jpg");
                Debugger.println("Timeout loading sub-heading sample details " + exp);
            }
        }
        boolean testResult = false;
        testResult = referralPage.verifyThePageTitlePresence(expectedPageTitle);
        Debugger.println("test-result flag for verifying page title is: " + testResult);
        Assert.assertTrue(testResult);
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
        testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
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

        tumoursPage.fillInTumourDescription();
        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader"));
        tumoursPage.fillInSpecimenID();
        referralPage.clickSaveAndContinueButton();
        tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
        tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
        referralPage.clickSaveAndContinueButton();
        tumoursPage.newTumourIsDisplayedInLandingPage(expectedListOfTumours);

    }

    @And("the Tumour page has the label text displayed as {string}")
    public void theTumourPageHasTheLabelTextDisplayedAs(String expectedLabelName) {
        String actualSnomedCTText = tumoursPage.getDynamicQuestionsSnomedCTLabelText();
        Assert.assertEquals(expectedLabelName, actualSnomedCTText);
    }


    @And("the tumour details are displayed in the Add a sample page on selecting a tumour sample type")
    public void theTumourDetailsAreDisplayedInTheAddASamplePageOnSelectingATumourSampleType() {

        List<String> expectedTumourTestData;
        expectedTumourTestData = tumoursPage.getExpectedTumourTestDataForAddATumourPage();
        List<String> actualTumourTestDetailsOnAddSamplePage;
        actualTumourTestDetailsOnAddSamplePage = samplesPage.getTheTumourDetailsValuesFromAddSamplePage();

        Debugger.println("Expected TumourTest Details-list            : " + expectedTumourTestData);
        Debugger.println("Actual TumourTest Details List on SamplePage: " + actualTumourTestDetailsOnAddSamplePage);

        for (int i = 0; i < expectedTumourTestData.size(); i++) {
            Debugger.println("Expected TumourTestData: " + i + " : " + expectedTumourTestData.get(i));
            Debugger.println("Actual TumourTest Details on Sample Page: " + i + " : " + actualTumourTestDetailsOnAddSamplePage.get(i) + "\n");
            Assert.assertEquals( expectedTumourTestData.get(i), actualTumourTestDetailsOnAddSamplePage.get(i));
        }

    }

    @And("the Tumour description value is reset after test")
    public void theTumourDescriptionValueIsResetAfterTest() {
        Assert.assertNull(tumoursPage.resetTheCurrentTumourDescription());
    }


    @And("the user adds new tumours")
    public void theUserAddsNewTumours(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));

        for (int i = 0; i <  expectedListOfTumours; i ++ ) {
            tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            tumoursPage.fillInTumourDescription();
            tumoursPage.fillInDateOfDiagnosis();
            tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader"));
            tumoursPage.fillInSpecimenID();
            referralPage.clickSaveAndContinueButton();
            tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
            tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
            referralPage.clickSaveAndContinueButton();
        }
        tumoursPage.newTumourIsDisplayedInLandingPage(expectedListOfTumours);
        tumoursPage.setTheTotalNumberOfUncheckedTumourList();
        Debugger.println("Total Number of unchecked1 " + tumoursPage.getTheTotalNumberOfUncheckedTumourList());
    }

    @And("the user select a different tumour list")
    public void theUserSelectADifferentTumourList() {
        Wait.seconds(3);
        Random randomGenerator = new Random();
        Debugger.println("Total Number of unchecked2: " + tumoursPage.getTheTotalNumberOfUncheckedTumourList());
        int numberOfUnCheckedTumourList = tumoursPage.getTheTotalNumberOfUncheckedTumourList();
        Debugger.println("Total number " + numberOfUnCheckedTumourList);
        int randomInt = randomGenerator.nextInt(numberOfUnCheckedTumourList) ;
        Debugger.println("random-Int " + randomInt);
        tumoursPage.listOfUnselectedTumourList.get(randomInt).click();
    }

    @And("the different tumour selected is shown with a checked radio button")
    public void theDifferentTumourSelectedIsShownWithACheckedRadioButton() {
        Assert.assertTrue(tumoursPage.checkedRadioButton.isDisplayed());
    }

    @When("the user attempts to fill in the Tumour Description {string} with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInTheTumourDescriptionWithDataThatExceedTheMaximumDataAllowed(String tumourDescription, int maximumCharactersAllowed) {
        if (tumourDescription.length() > maximumCharactersAllowed) {
            tumoursPage.descriptiveName.sendKeys(tumourDescription);
            Assert.assertTrue(true);
        }
    }

    @And("the user see a tick mark next to the added tumour")
    public void theUserSeeATickMarkNextToTheAddedTumour() {
        Assert.assertTrue(tumoursPage.ensureTickMarkIsDisplayedNextToSampleType());
    }

    @And("the user adds a new tumour with the following details")
    public void theUserAddsANewTumourWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));

        tumoursPage.fillInTumourDescription();
        tumoursPage.fillInDateOfDiagnosis();
        tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader"));
        tumoursPage.fillInSpecimenID();
        referralPage.clickSaveAndContinueButton();
        tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
        tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
        referralPage.clickSaveAndContinueButton();
    }
}
