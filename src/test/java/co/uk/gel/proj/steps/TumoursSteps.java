package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static co.uk.gel.lib.Action.getText;

public class TumoursSteps extends Pages {
    SeleniumLib seleniumLib = new SeleniumLib(driver);

    public TumoursSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the user enters {string} in the date of diagnosis field")
    public void theUserEntersInTheDateOfDiagnosisField(String criteriaForDateDiagnosis) throws Exception {

        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String actualFullDOB = referralPage.referralHeaderBorn.getText();
        String expectedDateOfBirth = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
        Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
        Assert.assertTrue(actualFullDOB.contains(expectedDateOfBirth));

        expectedDateOfBirth = newPatient.getDay() + "-" + newPatient.getMonth() + "-" + newPatient.getYear();
        String dateOfDiagnosis;

        switch (criteriaForDateDiagnosis) {
            case "Month_is_more_than_9_months_before_date_of_birth": {
                dateOfDiagnosis = TestUtils.getDateNineMonthsOrMoreBeforeDoB(expectedDateOfBirth, 0, -10, 0);
                String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                Action.retryClickAndIgnoreElementInterception(driver, tumoursPage.tumourTypeLabel);
                //click on tumourTypeLabel label to move cursor away from dateYear field
                break;
            }
            case "Date_is_more_than_9_months_before_date_of_birth": {
                dateOfDiagnosis = TestUtils.getDateNineMonthsOrMoreBeforeDoB(expectedDateOfBirth, -2, -9, 0);
                String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                Action.retryClickAndIgnoreElementInterception(driver, tumoursPage.tumourTypeLabel);
                break;
            }
            case "Year_is_more_than_9_months_before_date_of_birth": {
                dateOfDiagnosis = TestUtils.getDateNineMonthsOrMoreBeforeDoB(expectedDateOfBirth, 0, 0, -1);
                String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                Action.retryClickAndIgnoreElementInterception(driver, tumoursPage.tumourTypeLabel);
                break;
            }
            default:
                String[] value = criteriaForDateDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                Action.retryClickAndIgnoreElementInterception(driver, tumoursPage.tumourTypeLabel);
                break;
            // throw new IllegalArgumentException("Invalid text field name");
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
            String stepResult = "";
            boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
                Assert.fail("Could not navigate to Tumour Page.");
            }
            List<List<String>> diagnosisDates = inputDetails.asLists();
            String dateOfDiagnosis = "";
            for (int i = 1; i < diagnosisDates.size(); i++) {
                dateOfDiagnosis = diagnosisDates.get(i).get(0);
                // Debugger.println("DateOfDiagnosis: "+dateOfDiagnosis);
                if (dateOfDiagnosis.equalsIgnoreCase("14-0-1899")) {
                    String[] value = dateOfDiagnosis.split("-");
                    stepResult = tumoursPage.fillInDateOfDiagnosisInDifferentOrder(value[0], value[1], value[2]);
                    if(!stepResult.equalsIgnoreCase("Success")){
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
                        Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
                    }
                } else {
                    String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
                    testResult = tumoursPage.fillInDateOfDiagnosis(value[0], value[1], value[2]);
                    if (!testResult) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
                        Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
                    }
                }
                Wait.seconds(2);
                patientSearchPage.checkTheErrorMessagesInDOB(diagnosisDates.get(i).get(1), diagnosisDates.get(i).get(2));
                Wait.seconds(2);
                tumoursPage.clearDateOfDiagnosisFields();
                Wait.seconds(2);
            }
        } catch (Exception exp) {
            Debugger.println("Exception in validating theDateOfDiagnosisFieldGivesProperErrorMessages: " + exp);
        }
    }

    @And("the user answers all tumour system questions fields, select tumour type {string} and leaves date of diagnosis field blank")
    public void theUserAnswersAllTumourSystemQuestionsFieldsSelectTumourTypeAndLeavesDateOfDiagnosisFieldBlank(String tumourType) {
        boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
        String result = tumoursPage.fillInTumourDescription();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
            Assert.fail("Could not fill tumour description.");
        }
        result = tumoursPage.selectTumourType(tumourType);
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
            Assert.fail("Could not fill tumour type.");
        }
        result = tumoursPage.fillInSpecimenID();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourSpecimen.jpg");
            Assert.fail("Could not fill tumour Specimen.");
        }

    }

    @And("the user answers the tumour system questions fields and select a tumour type {string}")
    public void theUserAnswersTheTumourSystemQuestionsFieldsAndSelectATumourType(String tumourType) {
        boolean testResult = false;
        try {
            testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
                Assert.fail("Could not navigate to Tumour Page.");
            }
            if (tumoursPage.fillInTumourDescription() == null) {
                Assert.assertTrue(false);
            }
            testResult = tumoursPage.fillInDateOfDiagnosis();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
                Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
            }
            String tumour = tumoursPage.selectTumourType(tumourType);
            if (tumour == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
                Assert.fail("Could not fill tumour type.");
            }
            PatientDetailsPage.newPatient.setTumourType(tumour);
            if (tumoursPage.fillInSpecimenID() == null) {
                Assert.assertTrue(false);
            }
            Wait.seconds(5);//Observed timeout in next step, so introducing a wait fo 5 seconds.
        } catch (Exception exp) {
            Debugger.println("Exception from Answering Tumour Question Field: " + exp);
        }
    }

    @And("the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation {string}")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourCoreDataBySelectingTheTumourPresentation(String tumourPresentation) {
        boolean testResult = false;
        testResult = tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(tumourPresentation);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourFirstPresentation.jpg");
            Assert.fail("Could not select tumour first presentation");
        }
    }


    @And("the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched {string} result drop list")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourDiagnosisBySelectingASnomedCTFromTheSearchedResultDropList(String snomedTest) {
        boolean testResult = false;
        testResult = tumoursPage.answerTumourDiagnosisQuestions(snomedTest);
        Assert.assertTrue(testResult);
    }


    @And("the user answers the tumour dynamic questions {string} for Tumour Diagnosis by selecting a SnomedCT from the searched {string} result drop list")
    public void theUserAnswersTheTumourDynamicQuestionsForTumourDiagnosisBySelectingASnomedCTFromTheSearchedResultDropList(String tumourType, String snomedTest) {
        tumoursPage.answerTumourDiagnosisQuestionsBasedOnTumourType(tumourType, snomedTest);
    }

    @Then("the new tumour is displayed in the landing page")
    public void theNewTumourIsDisplayedInTheLandingPage() {
        boolean testResult = false;
        testResult = tumoursPage.newTumourIsDisplayedInLandingPage(1);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NewTumour.jpg");
            Assert.fail("New Tumour is not displayed.");
        }
    }

    @Then("the new tumour is displayed in the landing page for the existing patient with tumour list")
    public void theNewTumourIsDisplayedInTheLandingPageForTheExistingPatientWithTumourList() {
        int numberOfTumours = tumoursPage.getTheNumbersOfTumoursDisplayedInLandingPage();
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_Tumour");
        }
        if(numberOfTumours < 1) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NumTumours.jpg");
            Assert.fail("Numbers of Tumours displayed should 1 or great than 1");
        }
    }

    @And("the new tumour is not highlighted")
    public void theNewTumourIsNotHighlighted() {
        boolean testResult = false;
        testResult = tumoursPage.tumourIsNotHighlighted();
        if(!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourHighlight.jpg");
            Assert.fail("Tumors are not highlighted");
        }

        testResult = tumoursPage.warningMessageIsNotDisplayed();
        if(!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourWarning.jpg");
            Assert.fail("Warning messages displayed, but not expected");
        }
    }


    @Then("the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc")
    public void theTumoursStageDisplaysAddATumourPageWithAppropriateFieldsDescriptionDateOfDiagnosisEtc() {
        boolean eachElementIsLoaded;
        eachElementIsLoaded = tumoursPage.verifyTheElementsOnAddTumoursPageAreDisplayed();
        if (!eachElementIsLoaded) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ElementsOnAddTumoursPage.jpg");
            Assert.fail("ElementsOnAddTumoursPage not displayed as expected");
        }

    }

    @And("an information {string} is displayed that a test cannot start without a tumour")
    public void anInformationIsDisplayedThatATestCannotStartWithoutATumour(String tumourInformation) {
        boolean testResult = false;
        String[] expectedTumourSubTitle = tumourInformation.split("-");
        for (int i = 0; i < expectedTumourSubTitle.length; i++) {
            testResult = tumoursPage.verifyTumourSubTitle(expectedTumourSubTitle[i]);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumoursSubtitle.jpg");
                Assert.fail("Tumour subtitle not present:"+expectedTumourSubTitle[i]);
            }
        }
    }

    @And("the web browser is still at the same {string} page")
    public void theWebBrowserIsStillAtTheSamePage(String partCurrentUrl) {
        String fullCurrentURL = driver.getCurrentUrl();
        partCurrentUrl = partCurrentUrl.toLowerCase();
        if (!fullCurrentURL.contains(partCurrentUrl)) {
            Debugger.println("FullURL:" + fullCurrentURL + "\nPart:" + partCurrentUrl);
            Assert.assertTrue(false);
        }

    }


    @And("the user answers all tumour system questions without selecting any tumour type")
    public void theUserAnswersAllTumourSystemQuestionsWithoutSelectingAnyTumourType() {
        boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
        String result = tumoursPage.fillInTumourDescription();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
            Assert.fail("Could not fill tumour description.");
        }
        testResult = tumoursPage.fillInDateOfDiagnosis();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
            Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
        }
        result = tumoursPage.fillInSpecimenID();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourSpecimen.jpg");
            Assert.fail("Could not fill tumour Specimen.");
        }
    }

    @And("the tumours stage is at Add a Tumour page")
    public void theTumoursStageIsAtAddATumourPage() {
        boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
    }


    @Then("the error messages for the tumour mandatory fields are displayed")
    public void theErrorMessagesForTheTumourMandatoryFieldsAreDisplayed(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < list.size(); i++) {
            //Debugger.println("Expected: " + list.get(i).get("errorMessageHeader") + " : " + "Actual: " + tumoursPage.errorMessages.get(i).getText());
            Assert.assertEquals(list.get(i).get("errorMessageHeader"), getText(tumoursPage.errorMessages.get(i)));
        }
    }

    @And("the tumour stage is on select or edit a tumour page showing")
    public void theTumourStageIsOnSelectOrEditATumourPageShowing(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        //Debugger.println("Expected :" + list.get(0).get("pageTitleHeader") + " Actual:" + referralPage.getTheCurrentPageTitle());
        Assert.assertEquals(list.get(0).get("pageTitleHeader"), referralPage.getTheCurrentPageTitle());

        if (list.get(0).get("notificationTextHeader").equalsIgnoreCase("None")) { //assert that notification success is not displayed
            // Boolean flagStatus =  tumoursPage.checkNotificationElementIsNotPresent();
            boolean flagStatus = seleniumLib.isElementPresent(tumoursPage.successNotification);
            //Debugger.println("Success notification Element is displayed but it's not meant to be displayed " + flagStatus);
            Assert.assertFalse("Success notification Element is not displayed", flagStatus);

        } else {
            //Debugger.println("Expected :" + list.get(0).get("notificationTextHeader") + " Actual:" + getText(tumoursPage.successNotification));
            Assert.assertEquals(list.get(0).get("notificationTextHeader"), getText(tumoursPage.successNotification));
        }

        //Debugger.println("Expected :" + list.get(0).get("textInformationHeader") + " Actual:" + getText(tumoursPage.tumourInformationText));
        Assert.assertEquals(list.get(0).get("textInformationHeader"), getText(tumoursPage.tumourInformationText));

        //Debugger.println("Expected :" + list.get(0).get("linkToAddANewTumourHeader") + " Actual:" + getText(tumoursPage.addAnotherTumourLink));
        Assert.assertEquals(list.get(0).get("linkToAddANewTumourHeader"), getText(tumoursPage.addAnotherTumourLink));

        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));
        int actualListOfTumours = tumoursPage.listOfTumoursInTheTable.size();
        //Debugger.println("Expected: " + list.get(0).get("NumberOfTumoursAdded") + " Actual: " + tumoursPage.listOfTumoursInTheTable.size());
        Assert.assertEquals(expectedListOfTumours, actualListOfTumours);
    }


    @And("information text are displayed on the select or edit a tumour page")
    public void informationTextAreDisplayedOnTheTheSelectOrEditATumourPage(DataTable dataTable) {
        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> actualInformationText = tumoursPage.getInformationTextOnEditTumourPage();
        String expectedText = "";
        boolean isPresent;
        for (int i = 0; i < expectedList.size(); i++) {
            expectedText = expectedList.get(i).get("informationTextHeader");
            isPresent = false;
            for (int j = 0; j < actualInformationText.size(); j++) {
                if (actualInformationText.get(j).contains(expectedText)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                SeleniumLib.takeAScreenShot("TumourPageValue.jpg");
                Debugger.println("URL:" + driver.getCurrentUrl());
                Assert.fail("Expected value:" + expectedText + " not present in Tumour Page.");
            }
        }
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
        //Debugger.println("Expected Table columns: " + expectedHeaders);
        actualHeaders = tumoursPage.getTumourTableHeaders();
        // Debugger.println("Actual Table columns: " + actualHeaders.toString());
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

    @And("the new tumour is added as a list, with a checked radio button and a chevron right arrow icon")
    public void theNewTumourIsAddedAsAListWithACheckedRadioButtonAndAChevronRightArrowIcon() {

        Assert.assertTrue(tumoursPage.editTumourArrow.isDisplayed());
        Assert.assertTrue(tumoursPage.checkedRadioButton.isDisplayed());
    }

    @When("the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon")
    public void theUserSelectsTheExistingTumourFromTheLandingPageByClickingOnTheChevronRightArrowIcon() {
        boolean testResult = false;
        testResult = tumoursPage.clickEditTumourArrow();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_EditTumour.jpg");
            Assert.fail("Could not select Edit Tumour Arrow :");
        }
    }

    @And("the user edits the tumour system questions fields and select a new tumour type {string}")
    public void theUserEditsTheTumourSystemQuestionsFieldsAndSelectANewTumourType(String tumourType) {
        boolean testResult = false;
        testResult = tumoursPage.editTumourDescription();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_EditTumourDesc.jpg");
            Assert.fail("Could not select Edit Tumour Description :");
        }
        testResult = tumoursPage.editDateOfDiagnosis();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_EditTumourDiagnose.jpg");
            Assert.fail("Could not Edit Diagnosis :");
        }
        if (tumoursPage.selectTumourType(tumourType) == null) {
            Assert.assertTrue(false);
        }
        testResult = tumoursPage.editSpecimenID();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_EditTumourSpecimen.jpg");
            Assert.fail("Could not Edit SpecimenID :");
        }
    }

    @And("on the select or edit a tumour page, the new tumour details are displayed in the tumour table list")
    public void onTheSelectOrEditATumourPageTheNewTumourDetailsAreDisplayedInTheTumourTableList() {
        List<String> expectedTumourTestData;
        List<String> actualTumourTestData;
        expectedTumourTestData = tumoursPage.getExpectedTumourTestDataForAddATumourPage();
        actualTumourTestData = tumoursPage.getTheTumourDetailsOnTableList();
        Assert.assertEquals(expectedTumourTestData, actualTumourTestData);
    }

    @And("the {string} page is displayed")
    public void thePageIsDisplayed(String expectedPageTitle) {
        boolean testResult = false;
        testResult = referralPage.verifyThePageTitlePresence(expectedPageTitle);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
        }
        Assert.assertTrue(testResult);
    }

    @And("the new tumour details are displayed in the Edit a Tumour page")
    public void theNewTumourDetailsAreDisplayedInTheEditATumourPage() {
        List<String> expectedTumourTestData;
        List<String> actualTumourTestData;
        expectedTumourTestData = tumoursPage.getTheTumourDetailsOnEditATumourPage();
        actualTumourTestData = tumoursPage.getTheExpectedTumourDetailsForAddATumourPage();
        Assert.assertEquals(expectedTumourTestData, actualTumourTestData);
    }

    @And("the labels and help hint texts are displayed")
    public void theLabelsAndHelpHintTextsAreDisplayed(DataTable dataTable) {
        List<List<String>> expectedLabelsAndHintTextsListMap = dataTable.asLists(String.class);
        List<String> actualHelpHintTexts = referralPage.getTheListOfHelpHintTextsOnCurrentPage();
        List<String> actualFieldsLabels = tumoursPage.getTheTumourFieldsLabelsOnAddATumourPage();
        /* Add "None" element to the fourth index of actualHelpHintTexts, as Tumour type has no help hint text */
        actualHelpHintTexts.add(3, "None");
        for (int i = 1; i < expectedLabelsAndHintTextsListMap.size(); i++) { //i starts from 1 because i=0 represents the header
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(0), actualFieldsLabels.get(i - 1));
            Assert.assertEquals(expectedLabelsAndHintTextsListMap.get(i).get(1), actualHelpHintTexts.get(i - 1));
            Wait.seconds(2);
        }
    }

    @And("the user answers the tumour system specific question fields - Description, Select a tumour type {string} and Pathology Sample ID")
    public void theUserAnswersTheTumourSystemSpecificQuestionFieldsDescriptionSelectATumourTypeAndPathologySampleID(String tumourType) {

        boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
        String result = tumoursPage.fillInTumourDescription();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
            Assert.fail("Could not fill tumour description.");
        }
        result = tumoursPage.selectTumourType(tumourType);
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
            Assert.fail("Could not fill tumour type.");
        }
        result = tumoursPage.fillInSpecimenID();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourSpecimen.jpg");
            Assert.fail("Could not fill tumour Specimen.");
        }
    }

    @And("the user enters {string} in the Pathology Sample ID field")
    public void theUserEntersInThePathologySampleIDField(String pathologySampleId) {
        Action.fillInValue(tumoursPage.pathologyReportId, pathologySampleId);
    }


    @And("the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type {string}")
    public void theUserAnswersTheTumourSystemSpecificQuestionFieldsDescriptionDateOfDiagnosisAmdSelectATumourType(String tumourType) {

        boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
        String result = tumoursPage.fillInTumourDescription();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
            Assert.fail("Could not fill tumour description.");
        }
        testResult = tumoursPage.fillInDateOfDiagnosis();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
            Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
        }
        result = tumoursPage.selectTumourType(tumourType);
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
            Assert.fail("Could not fill tumour type.");
        }
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

        boolean testResult = false;
        testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ErrorMsg.jpg");
            Assert.fail("Error Message not displayed:" + errorMessage);
        }
    }

    @When("user clicks add a new tumour link")
    public void userClicksAddANewTumourLink() {
        boolean testResult = tumoursPage.clickOnTheAddANewTumourTextLink();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NewTumour.jpg");
            Assert.fail("Could not click on Add new tumour link");
        }
    }


    @And("the user adds a new tumour")
    public void theUserAddsANewTumour(DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        int expectedListOfTumours = Integer.parseInt(list.get(0).get("NumberOfTumoursAdded"));
        String result = tumoursPage.fillInTumourDescription();
        if (result == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
            Assert.fail("Could not fill tumour description.");
        }
        if (!tumoursPage.fillInDateOfDiagnosis()) {
            Assert.assertTrue(false);
        }
        if (tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader")) == null) {
            Assert.assertTrue(false);

        }
        if (tumoursPage.fillInSpecimenID() == null) {
            Assert.assertTrue(false);
        }
        if (!referralPage.clickSaveAndContinueButton()) {
            Assert.assertTrue(false);
        }
        boolean testResult = tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourFirstPresentation.jpg");
            Assert.fail("Could not select tumour first presentation");
        }
        testResult = tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDiagnoseQuestion.jpg");
            Assert.fail("Could not answer tumour Diagnosis Questions");
        }
        if (!referralPage.clickSaveAndContinueButton()) {
            Assert.assertTrue(false);
        }
        testResult = tumoursPage.newTumourIsDisplayedInLandingPage(expectedListOfTumours);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NewTumour.jpg");
            Assert.fail("Tumours are not displayed.");
        }

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
//
//        Debugger.println("Expected TumourTest Details-list            : " + expectedTumourTestData);
//        Debugger.println("Actual TumourTest Details List on SamplePage: " + actualTumourTestDetailsOnAddSamplePage);

        for (int i = 0; i < expectedTumourTestData.size(); i++) {
//            Debugger.println("Expected TumourTestData: " + i + " : " + expectedTumourTestData.get(i));
//            Debugger.println("Actual TumourTest Details on Sample Page: " + i + " : " + actualTumourTestDetailsOnAddSamplePage.get(i) + "\n");
            Assert.assertEquals(expectedTumourTestData.get(i), actualTumourTestDetailsOnAddSamplePage.get(i));
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

        for (int i = 0; i < expectedListOfTumours; i++) {
            boolean testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
                Assert.fail("Could not navigate to Tumour Page.");
            }
            String result = tumoursPage.fillInTumourDescription();
            if (result == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDesc.jpg");
                Assert.fail("Could not fill tumour description.");
            }
            testResult = tumoursPage.fillInDateOfDiagnosis();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
                Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
            }
            tumoursPage.selectTumourType(list.get(0).get("TumourTypeHeader"));
            result = tumoursPage.fillInSpecimenID();
            if (result == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourSpecimen.jpg");
                Assert.fail("Could not fill tumour Specimen.");
            }
            referralPage.clickSaveAndContinueButton();
            testResult = tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(list.get(0).get("PresentationTypeHeader"));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourFirstPresentation.jpg");
                Assert.fail("Could not select tumour first presentation");
            }
            testResult = tumoursPage.answerTumourDiagnosisQuestions(list.get(0).get("SnomedCTSearchHeader"));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDiagnoseQuestion.jpg");
                Assert.fail("Could not answer tumour Diagnosis Questions");
            }
            referralPage.clickSaveAndContinueButton();
        }
        boolean testResult = tumoursPage.newTumourIsDisplayedInLandingPage(expectedListOfTumours);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NewTumour.jpg");
            Assert.fail("Tumours are not displayed.");
        }
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
        int randomInt = randomGenerator.nextInt(numberOfUnCheckedTumourList);
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

    @Then("the user selects the existing tumour from the tumour landing page")
    public void theUserSelectsTheExistingTumourFromTheTumourLandingPage() {
        boolean testResult = false;
        testResult = tumoursPage.clickOnTheExistingTumourBox();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourForm.jpg");
            Assert.fail("Could not select existing Tumour form");
        }
    }

    @Then("the user should not see any error message after selecting the tumour")
    public void theUserShouldNotSeeAnyErrorMessageAfterSelectingTheTumour() {
        boolean testResult = false;
        testResult = tumoursPage.tumourSelectedWithoutAnyMessage();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ErrorMsg.jpg");
            Assert.fail("Error Message displayed, not expected");
        }
    }

    @And("the user should be able to see that the {string} is not present")
    public void theUserShouldBeAbleToSeeThatTheIsNotPresent(String expText) {
        boolean testResult = false;
        testResult = tumoursPage.verifyLabelTextInTumourIsNotPresent(expText);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_LabelText.jpg");
            Assert.fail("Label text verification failed.."+expText);
        }
    }

    @And("the user fills the Topography of {string} SnomedCT and Topography of this {string} SnomedCT with result drop list")
    public void theUserFillsTheTopographyOfSnomedCTAndTopographyOfThisSnomedCTWithResultDropList(String primaryTumour, String tumour) {
        boolean testResult = false;
        testResult = tumoursPage.fillTumourDiagnosisTable(primaryTumour, tumour);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_Topography.jpg");
            Assert.fail("Could not fill Topography");
        }
    }

    @And("the user clicks on the Tumour Diagnosis add another link")
    public void theUserClicksOnTheTumourDiagnosisAddAnotherLink() {
        boolean testResult = false;
        testResult = tumoursPage.clicksOnAddAnotherLinkForTumourDiagnosis();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDiagnosis.jpg");
            Assert.fail("Could not click on Add another link for Tumour Diagnosis");
        }
    }

    @And("the user fills the Working diagnosis {string} SnomedCT with result drop list")
    public void theUserFillsTheWorkingDiagnosisSnomedCTWithResultDropList(String expValue) {
        boolean testResult = false;
        testResult = tumoursPage.fillWorkingDiagnosis(expValue);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourWorkDiagnose.jpg");
            Assert.fail("Could not fill working diagnosis:"+expValue);
        }
    }

    @And("the user clicks on the Working diagnosis morphology add another link")
    public void theUserClicksOnTheWorkingDiagnosisMorphologyAddAnotherLink() {
        boolean testResult = false;
        testResult = tumoursPage.clicksOnAddAnotherLinkForWorkingDiagnosisMorphology();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_Morphology.jpg");
            Assert.fail("Could not click on the Working diagnosis morphology add another link");
        }
    }

    @And("the user stores the tumour details")
    public void theUserStoresTheTumourDetails() {
        tumoursPage.verifyDescriptionAndReportId();
    }

    @When("the user updates the {string} stage with {string}")
    public void theUserUpdatesTheStageWith(String stageName, String updateDetails) {
        if (stageName.equalsIgnoreCase("Tumours")) {
            String tumour = tumoursPage.selectTumourType(updateDetails);
            if (tumour == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
                Assert.fail("Could not fill tumour type.");
            }
        }
    }
}//end
