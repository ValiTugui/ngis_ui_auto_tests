package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.*;


public class MiPortalFileSubmissionsSteps extends Pages {

    public MiPortalFileSubmissionsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the mi-portal home page")
    public void aWebBrowserIsAtTheMiPortalHomePage(List<String> attributeOfUrl) {

        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        String userType = attributeOfUrl.get(2);
        if (userType != null) {
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage, userType);
        }
        Wait.seconds(1);

    }

    @When("the user navigates to the mi-portal {string} stage")
    public void theUserNavigatesToTheMiPortalStage(String miPage) {
       // Actions.clickElement(driver, miPortalFileSubmissionPage.fileSubmissionLnk);
        miPortalHomePage.navigateToMiPage(miPage);
    }


    @And("the user selects a value {string} from the {string} column drop-down")
    public void theUserSelectsAValueFromTheColumnDropDown(String value, String dropDownButton) {
        miPortalHomePage.selectSearchValueDropDown(value,dropDownButton);
    }


    @And("the user selects a search operator {string} from the {string} operator drop-down")
    public void theUserSelectsASearchOperatorFromTheOperatorDropDown(String searchOperator,String dropDownButton) {
        miPortalHomePage.selectSearchValueDropDown(searchOperator, dropDownButton);
    }

    @And("the user enters a date {string} in the file-submission date field")
    public void theUserEntersADateInTheFileSubmissionDateField(String date) {
        miPortalFileSubmissionPage.fillInTheFileSubmissionDate(date);
    }

    @And("the user clicks on Add criteria button")
    public void theUserClicksOnAddCriteriaButton() {
        miPortalHomePage.clickAddButton();
       Wait.seconds(1);
    }

    @Then("file submission search criteria badge information is displayed below drop-down buttons")
    public void fileSubmissionSearchCriteriaBadgeInformationIsDisplayedBelowDropDownButtons() {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.badgeFilterSearchCriteriaIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @When("the user click on the Search button")
    public void theUserClickOnTheSearchButton() {
        miPortalHomePage.clickSearchButton();
        Wait.seconds(2);
    }

    @Then("search results are displayed for the file-submission search")
    public void searchResultsAreDisplayedForTheFileSubmissionSearch() {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.searchResultTableIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user is able to see the field values - Filenames {string}, Status {string}, ErrorMessage {string} and WarningMessage {string}")
    public void theUserIsAbleToSeeTheFieldValuesFilenamesStatusErrorMessageAndWarningMessage(String expectedFileName, String expectedStatus, String expectedErrorMessage, String expectedWarningMessage) {
        Map<String, String> Test = new HashMap<>();
        Test = miPortalFileSubmissionPage.getValuesOfSearchedResult(expectedFileName);
        Debugger.println("Test " + Test);
        expectedErrorMessage = "{\"non_field_errors\":[\"Sample received datetime must be set if the sample has been received\"]}, {\"non_field_errors\":[\"Sample received datetime must be set if the sample has been received\"]}";
        Assert.assertEquals(expectedFileName, Test.get("Filename"));
        Assert.assertEquals(expectedStatus, Test.get("Status"));
        Assert.assertEquals(expectedWarningMessage, Test.get("Warning Msgs"));
    }

    @And("the user is able to see one or more of the Filenames {string}, Status {string}, ErrorMessage {string} and WarningMessage {string}")
    public void theUserIsAbleToSeeOneOrMoreOfTheFilenamesStatusErrorMessageAndWarningMessage(String expectedFileName, String arg1, String arg2, String arg3) {

        List<Map<String, String>> Test = new ArrayList<>();
        Test = miPortalFileSubmissionPage.getValuesOfCsvFileNamesSearchedResult(expectedFileName);
        Debugger.println("TestCSV - number of rows: " + Test.size() + "\n");

        for (int i = 0; i < Test.size(); i++) {
            Debugger.println(Test.get(i) + "\n");
        }
    }

    @And("the user sees a search box container section for {string} page")
    public void theUserSeesASearchBoxContainerSectionForPage(String arg0) {
        boolean testResult = false;
        testResult = miPortalHomePage.searchBoxContainerIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the values in the file-submission search column {string} drop-down menu")
    public void theUserSeesTheValuesInTheFileSubmissionSearchColumnDropDownMenu(String dropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(dropDownButton);
        Assert.assertNotNull(actualDropDownValues);

        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader"), actualDropDownValues.get(i));
        }
    }

    @And("the user sees the values in the search operator {string} drop-down menu")
    public void theUserSeesTheValuesInTheSearchOperatorDropDownMenu(String dropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(dropDownButton);
        Assert.assertNotNull(actualDropDownValues);

        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchOperatorHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchOperatorHeader"), actualDropDownValues.get(i));
        }
    }

    @And("the user sees the values in the search value {string} drop-down menu")
    public void theUserSeesTheValuesInTheSearchValueDropDownMenu(String dropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(dropDownButton);
        Assert.assertNotNull(actualDropDownValues);

        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchValueHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchValueHeader"), actualDropDownValues.get(i));
        }

    }


    @Then("the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons")
    public void theFileSubmissionPageDisplaysTheSearchHeaderDropDownColumnOperatorAndValueAddSearchAndResetButtons() {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyTheElementsOnFileSubmissionPage();
        Assert.assertTrue(testResult);
    }

    @When("the user click on the reset button")
    public void theUserClickOnTheResetButton() {
        miPortalHomePage.clickResetButton();
    }


    @Then("the search criteria badge disappears")
    public void theSearchCriteriaBadgeDisappears() {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.badgeFilterSearchCriteriaIsNotDisplayed();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the message {string} below the search container")
    public void theUserSeesTheMessageBelowTheSearchContainer(String noResultFoundMessage) {
        boolean testResult = false;

        testResult = miPortalHomePage.verifyNoSearchResultMessage(noResultFoundMessage);
        Debugger.println("test-result flag for verifying no result found is: " + testResult);
        Assert.assertTrue(testResult);
    }


    @And("the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV")
    public void theSearchResultsSectionDisplaysTheElementsSearchResultsTextDisplayOptionsEntryOptionsResultRowHeaderAndDownLoadCSV() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheElementsInTheSearchResultSection();
        Assert.assertTrue(testResult);
    }


    @When("the user clicks on the Download CSV button to download the CSV file as {string}.csv")
    public void theUserClicksOnTheDownloadCSVButtonToDownloadTheCSVFileAsCsv(String fileName) {
        String dateToday = miPortalFileSubmissionPage.getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
        fileName = fileName + "-" + dateToday + ".csv";
        Debugger.println("Actual-filename : " + fileName);
        miPortalHomePage.downloadMiCSVFile(fileName);
    }

    @When("the user clicks on the Display Options button")
    public void theUserClicksOnTheDisplayOptionsButton() {
        miPortalHomePage.clickSearchResultDisplayOptionsButton();
    }

    @Then("the user sees a modal-content page")
    public void theUserSeesAModalContentPage(){
        boolean testResult = false;
        testResult = miPortalHomePage.modalContentIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the checkboxes with the label names {string} and {string}")
    public void theUserSeesTheCheckboxesWithTheLabelNamesAnd(String expectedCgCheckBoxLabel, String expectedTcCheckBoxLabel) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheCheckBoxesDisplayedOnModalContent();
        Assert.assertTrue(testResult);

        String actualCgCheckBoxLabel = Actions.getText(miPortalHomePage.compactGridCheckBoxLabel);
        Debugger.println("Expected CompactCheckBox: " + expectedCgCheckBoxLabel + ":" + " Actual CompactCheckBox: " + actualCgCheckBoxLabel );
        Assert.assertEquals(expectedCgCheckBoxLabel,actualCgCheckBoxLabel);

        String actualTcCheckBoxLabel = Actions.getText(miPortalHomePage.truncateColumnsCheckBoxLabel);
        Debugger.println("Expected TruncatedCheckBox: " + expectedTcCheckBoxLabel + ":" + " Actual TruncatedCheckBox: " + actualTcCheckBoxLabel );
        Assert.assertEquals(expectedTcCheckBoxLabel,actualTcCheckBoxLabel);
    }

    @And("the user closes the modal content by clicking on the reset-button")
    public void theUserClosesTheModalContentByClickingOnTheResetButton() {
        miPortalHomePage.clickResetButtonOnModalContent();
    }

    @And("the selected search option is reset after test")
    public void theSelectedSearchOptionIsResetAfterTest() {
        miPortalHomePage.clickResetButton();
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.badgeFilterSearchCriteriaIsNotDisplayed();
        Assert.assertTrue(testResult);

    }

    @And("the values are not displayed in the file-submission search column {string} drop-down menu")
    public void theValuesAreNotDisplayedInTheFileSubmissionSearchColumnDropDownMenu(String DropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String>expectedDropDownValuesList = new ArrayList<>();
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(DropDownButton);
        Assert.assertNotNull(actualDropDownValues);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            expectedDropDownValuesList.add(expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader"));
            Debugger.println("values from dataTable: " + i + " : " + expectedDropDownValuesList.get(i));
        }
        Debugger.println("Expected values:" + expectedDropDownValuesList + " are NOT equals to actual " + actualDropDownValues);
        Assert.assertNotEquals(expectedDropDownValuesList, actualDropDownValues);
    }


    @And("the user sees a section {string} split into two parts {string} and {string}")
    public void theUserSeesASectionColumnOrderingSplitIntoTwoPartsShowAndHide(String expColumnOrdering, String expShow, String expHide) {
        List<String>expectedColumnShowHideLabel = new ArrayList<>();
        List actualColumnShowHideLabel = miPortalHomePage.getColumnOrderShowHideLabelsOnDisplayedModal();
        expectedColumnShowHideLabel.add(expColumnOrdering);
        expectedColumnShowHideLabel.add(expShow);
        expectedColumnShowHideLabel.add(expHide);
        for (int i = 0; i < expectedColumnShowHideLabel.size(); i++) {
            Debugger.println("Expected " + expectedColumnShowHideLabel.get(i) + " : Actual : " + actualColumnShowHideLabel.get(i));
            Assert.assertEquals(expectedColumnShowHideLabel.get(i), actualColumnShowHideLabel.get(i));
        }
    }

    @And("the user sees the displayed fields-columns under {string} section")
    public void theUserSeesTheDisplayedFieldsColumnsUnderSection(String expColumnHeaderStatus, DataTable dataTable) {
        List<Map<String, String>> expectedListOfColumnHeaders = dataTable.asMaps(String.class, String.class);
        List actualListOfColumnHeaders = null;

        String actualColumnHeaderStatus = miPortalHomePage.getHeaderShowOrHideLabel(expColumnHeaderStatus);
        Debugger.println("Values " + actualColumnHeaderStatus);
        Assert.assertEquals(expColumnHeaderStatus,actualColumnHeaderStatus);

        if (expColumnHeaderStatus.equalsIgnoreCase("Show")) {
            actualListOfColumnHeaders = miPortalHomePage.getListOfColumnsInHeaderShowOrHidden("visible");
        } else if (expColumnHeaderStatus.equalsIgnoreCase("Hide")){
            actualListOfColumnHeaders = miPortalHomePage.getListOfColumnsInHeaderShowOrHidden("hidden");
        }

        for (int i = 0; i < expectedListOfColumnHeaders.size(); i++) {
            Debugger.println("Expected: " + expectedListOfColumnHeaders.get(i).get("HeaderColumnOrderingList") + " : " + "Actual: " + actualListOfColumnHeaders.get(i));
            Assert.assertEquals(expectedListOfColumnHeaders.get(i).get("HeaderColumnOrderingList"), actualListOfColumnHeaders.get(i));
        }
    }

}