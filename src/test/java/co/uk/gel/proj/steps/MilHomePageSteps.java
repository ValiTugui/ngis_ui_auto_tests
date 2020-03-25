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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MilHomePageSteps extends Pages {

    public MilHomePageSteps(SeleniumDriver driver) {
        super(driver);
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

    @And("the user clicks on Add criteria button")
    public void theUserClicksOnAddCriteriaButton() {
        miPortalHomePage.clickAddButton();
        Wait.seconds(1);
    }

    @When("the user click on the Search button")
    public void theUserClickOnTheSearchButton() {
        miPortalHomePage.clickSearchButton();
        Wait.seconds(2);
    }

    @When("the user click on the reset button")
    public void theUserClickOnTheResetButton() {
        miPortalHomePage.clickResetButton();
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
        testResult = miPortalHomePage.badgeFilterSearchCriteriaIsNotDisplayed();
        Assert.assertTrue(testResult);
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
        Wait.seconds(2);
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

    @And("the user selects a value {string} from the {string} value drop-down")
    public void theUserSelectsAValueFromTheValueDropDown(String value, String dropDownButton) {
        miPortalHomePage.selectSearchValueDropDown(value,dropDownButton);
    }


    @And("the user sees the search results pagination entry drop-down with default selection of {string}")
    public void theUserSeesTheSearchResultsPaginationEntryDropDownWithDefaultSelectionOf(String expectedEntryPagination) {
        String actualEntryPagination = miPortalHomePage.getTheSelectedPaginationEntryValue();
        Debugger.println("ExpectedEntry: " + expectedEntryPagination + " : ActualEntry :" + actualEntryPagination);
        Assert.assertTrue(actualEntryPagination.contains(expectedEntryPagination));
    }

    @And("the user sees all the drop-down values in the search results pagination entry selection")
    public void theUserSeesAllTheDropDownValuesInTheSearchResultsPaginationEntrySelection(DataTable dataTable) {
        List<Map<String, String>> expectedPaginationDropDownValues = dataTable.asMaps(String.class, String.class);
        List actualPaginationDropDownValues = miPortalHomePage.getAllThePaginationEntryDropDownValues();

        for (int i = 0; i < expectedPaginationDropDownValues.size(); i++) {
            Debugger.println("Expected " + expectedPaginationDropDownValues.get(i).get("paginationDropDownValues") + " : Actual : " + actualPaginationDropDownValues.get(i));
            Assert.assertEquals(expectedPaginationDropDownValues.get(i).get("paginationDropDownValues"), actualPaginationDropDownValues.get(i));
        }
    }

    @When("the user selects a pagination drop-down value {string}")
    public void theUserSelectsAPaginationDropDownValue(String paginationValue) {
        miPortalHomePage.selectValueInPagination(paginationValue);
    }

    @And("the user sees the search result table updated with {string} results")
    public void theUserSeesTheSearchResultTableUpdatedWithResults(String paginationValue) {
        int value = Integer.parseInt(paginationValue);
        boolean testResult = false;
        testResult = miPortalHomePage.getTheTotalNumberOfSearchResult(value);
        Debugger.println("test + " + testResult);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page")
    public void theUserSeesTheButtonsShowAllAndHideAllButtonsUnderColumnOrderingSectionOnModalContentPage() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheButtonsShowAllAndHideAllAreDisplayedOnModalContent();
        Debugger.println("test + " + testResult);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on the button {string}")
    public void theUserClicksOnTheButton(String showOrHideButton) {
        miPortalHomePage.clickShowAllOrHideAllButton(showOrHideButton);
    }
}