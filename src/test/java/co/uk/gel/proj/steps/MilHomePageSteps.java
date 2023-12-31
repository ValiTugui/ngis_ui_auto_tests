package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class MilHomePageSteps extends Pages {

    public MilHomePageSteps(SeleniumDriver driver) {
        super(driver);
    }


    @When("the user navigates to the mi-portal {string} stage")
    public void theUserNavigatesToTheMiPortalStage(String miPage) {
        boolean testResult = false;
        testResult = miPortalHomePage.navigateToMiPage(miPage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIPageNavigation.jpg");
            Assert.fail("Could not navigate to MI Page");
        }
    }


    @And("the user selects a value {string} from the {string} column drop-down")
    public void theUserSelectsAValueFromTheColumnDropDown(String value, String dropDownButton) {
        boolean testResult = false;
        testResult = miPortalHomePage.selectSearchValueDropDown(value, dropDownButton);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIDropDown.jpg");
            Assert.fail("Could select the drop down value");
        }
    }


    @And("the user selects a search operator {string} from the {string} operator drop-down")
    public void theUserSelectsASearchOperatorFromTheOperatorDropDown(String searchOperator,String dropDownButton) {
        boolean testResult = false;
        testResult = miPortalHomePage.selectSearchValueDropDown(searchOperator, dropDownButton);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on Add criteria button")
    public void theUserClicksOnAddCriteriaButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickAddButton();
        Assert.assertTrue(testResult);
    }

    @When("the user click on the Search button")
    public void theUserClickOnTheSearchButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickSearchButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SearchButton.jpg");
            Assert.fail("Search button could not found/click");
        }
    }

    @When("the user click on the reset button")
    public void theUserClickOnTheResetButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickResetButton();
        Assert.assertTrue(testResult);
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
        String expValue = "";
        if((expectedDropDownValues.size()) != actualDropDownValues.size()){
            Debugger.println("Expected no of drop down values in "+dropDownButton+" is "+(expectedDropDownValues.size())+",but actual:"+actualDropDownValues.size());
            Assert.assertTrue(false);
        }
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            expValue = expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader");
            if(!expValue.equalsIgnoreCase(actualDropDownValues.get(i))){
                Debugger.println("Expected drop down value :"+expValue+" not present in "+dropDownButton);
                Assert.assertTrue(false);
            }
        }
    }

    @And("the user sees the values in the search operator {string} drop-down menu")
    public void theUserSeesTheValuesInTheSearchOperatorDropDownMenu(String dropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(dropDownButton);
        Assert.assertNotNull(actualDropDownValues);
        String expValue = "";
        if((expectedDropDownValues.size()) != actualDropDownValues.size()){
            Debugger.println("Expected no of drop down values in "+dropDownButton+" is "+(expectedDropDownValues.size())+",but actual:"+actualDropDownValues.size());
            Assert.assertTrue(false);
        }
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            expValue = expectedDropDownValues.get(i).get("fileSubmissionsSearchOperatorHeader");
            if(!expValue.equalsIgnoreCase(actualDropDownValues.get(i))){
                Debugger.println("Expected drop down value :"+expValue+" not present in "+dropDownButton);
                Assert.assertTrue(false);
            }
         }
    }

    @Then("the user sees the values in the search value {string} drop-down menu")
    public void theUserSeesTheValuesInTheSearchValueDropDownMenu(String dropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(dropDownButton);
        Assert.assertNotNull(actualDropDownValues);
        boolean testResult = false;
        String expValue = "";
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            expValue = expectedDropDownValues.get(i).get("fileSubmissionsSearchValueHeader");
            if (actualDropDownValues.contains(expValue)){
               testResult = true;
            }else{
                Debugger.println("Expected: " + expValue + " : " + "Actual: " + actualDropDownValues.get(i));
                SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
                testResult = false;
            }
            Assert.assertTrue(testResult);
        }
    }

    @Then("the user sees the message (.*) below the search container")
    public void theUserSeesTheMessageBelowTheSearchContainer(String noResultFoundMessage) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyNoSearchResultMessage(noResultFoundMessage);
        Assert.assertTrue(testResult);
    }

    @And("the search results section displays the elements - Search Results Text, Display Options, Entry Options, Result Row Header and DownLoad CSV")
    public void theSearchResultsSectionDisplaysTheElementsSearchResultsTextDisplayOptionsEntryOptionsResultRowHeaderAndDownLoadCSV() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheElementsInTheSearchResultSection();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SearchResultDisplay.jpg");
            Assert.fail("Search result section not displayed the required details.");
        }
    }

    @When("the user clicks on the Download CSV button to download the CSV file as {string}.csv")
    public void theUserClicksOnTheDownloadCSVButtonToDownloadTheCSVFileAsCsv(String fileName) {
        //String dateToday = miPortalFileSubmissionPage.getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
        //Modified to take the date based on todays date to make the step reusable in all the section pages
        boolean testResult = false;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date();
        String dateFormate = df.format(d);
        fileName = fileName + "-" + dateFormate + ".csv";
        Debugger.println("Actual-filename : " + fileName);
        testResult = miPortalHomePage.downloadMiCSVFile(fileName);
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on the Display Options button")
    public void theUserClicksOnTheDisplayOptionsButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickSearchResultDisplayOptionsButton();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees a modal-content page")
    public void theUserSeesAModalContentPage(){
        boolean testResult = false;
        testResult = miPortalHomePage.modalContentIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the checkboxes with the label names Compact grid and Truncate columns")
    public void theUserSeesTheCheckboxesWithTheLabelNamesAnd() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheCheckBoxesUnderSaveSpaceSection();
        Assert.assertTrue(testResult);
    }

    @And("the user closes the modal content by clicking on the reset-button")
    public void theUserClosesTheModalContentByClickingOnTheResetButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickResetButtonOnModalContent();
        Assert.assertTrue(testResult);
    }

    @And("the selected search option is reset after test")
    public void theSelectedSearchOptionIsResetAfterTest() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickResetButton();
        Assert.assertTrue(testResult);
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
        List <List<String>> expectedListOfColumnHeaders = dataTable.asLists();
        boolean testResult = false;
        testResult = miPortalHomePage.verifyListOfColumnsInHeaderShowOrHidden(expColumnHeaderStatus,expectedListOfColumnHeaders);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the displayed fields-columns under {string} section as empty")
    public void theUserSeesTheDisplayedFieldsColumnsUnderSectionAsEmpty(String expColumnHeaderStatus) {
        List actualListOfColumnHeaders = null;
        if (expColumnHeaderStatus.equalsIgnoreCase("Show")) {
            actualListOfColumnHeaders = miPortalHomePage.getListOfColumnsInHeaderShowOrHidden("Show");
        } else if (expColumnHeaderStatus.equalsIgnoreCase("Hide")){
            actualListOfColumnHeaders = miPortalHomePage.getListOfColumnsInHeaderShowOrHidden("Hide");
        }
        if(actualListOfColumnHeaders == null){
            Assert.assertTrue(false);
        }
        if(actualListOfColumnHeaders.size() > 0){
            Assert.assertTrue(false);
        }
    }

    @And("the user selects a value {string} from the {string} value drop-down")
    public void theUserSelectsAValueFromTheValueDropDown(String value, String dropDownButton) {
        boolean testResult = false;
        testResult = miPortalHomePage.selectSearchValueDropDown(value, dropDownButton);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the search results pagination entry drop-down with default selection of {string}")
    public void theUserSeesTheSearchResultsPaginationEntryDropDownWithDefaultSelectionOf(String expectedEntryPagination) {
        String actualEntryPagination = miPortalHomePage.getTheSelectedPaginationEntryValue();
        Debugger.println("ExpectedEntry: " + expectedEntryPagination + " : ActualEntry :" + actualEntryPagination);
        //Assert.assertTrue(actualEntryPagination.contains(expectedEntryPagination));
        Assert.assertEquals(expectedEntryPagination, actualEntryPagination);
    }

    @And("the user sees all the drop-down values in the search results pagination entry selection")
    public void theUserSeesAllTheDropDownValuesInTheSearchResultsPaginationEntrySelection(DataTable dataTable) {
        List<Map<String, String>> expectedPaginationDropDownValues = dataTable.asMaps(String.class, String.class);
        Wait.seconds(10);
        List actualPaginationDropDownValues = miPortalHomePage.getAllThePaginationEntryDropDownValues();
        if(expectedPaginationDropDownValues.size() != actualPaginationDropDownValues.size()){
            Assert.assertTrue(false);
        }
        String actValue="",expValue="";
        for (int i = 0; i < expectedPaginationDropDownValues.size(); i++) {
            actValue = actualPaginationDropDownValues.get(i).toString();
            expValue = expectedPaginationDropDownValues.get(i).get("paginationDropDownValues").toString();
            if(!actValue.equalsIgnoreCase(expValue)){
                Assert.assertTrue(false);
            }
        }
    }
    @When("the search result table should display based on the pagination value selected")
    public void theSearchTableShouldDisplayBasedOnPaginationSelection(DataTable dataInputs) {
        boolean testResult = false;

        List<List<String>> paginationValues = dataInputs.asLists();
        String paginationValue = "",expectedRows="";
        Wait.seconds(5);
        for(int i=1;i<paginationValues.size(); i++){
           paginationValue = paginationValues.get(i).get(0);
           expectedRows   = paginationValues.get(i).get(1);
           testResult = miPortalHomePage.selectValueInPagination(paginationValue);
           Assert.assertTrue(testResult);
           Wait.seconds(3);//Load the result
           miPortalFileSubmissionPage.verifyTheTotalNumberOfSearchResult(Integer.parseInt(expectedRows));
            Assert.assertTrue(testResult);
            Wait.seconds(3);//For next Iteration
        }
        testResult = miPortalHomePage.selectValueInPagination(paginationValue);
        Assert.assertTrue(testResult);
    }

    @When("the user selects a pagination drop-down value {string}")
    public void theUserSelectsAPaginationDropDownValue(String paginationValue) {
        boolean testResult = false;
        testResult = miPortalHomePage.selectValueInPagination(paginationValue);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the search result table updated with {string} results")
    public void theUserSeesTheSearchResultTableUpdatedWithResults(String paginationValue) {
        int value = Integer.parseInt(paginationValue);
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyTheTotalNumberOfSearchResult(value);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the buttons - Show All and Hide All buttons under Column Ordering section on modal content page")
    public void theUserSeesTheButtonsShowAllAndHideAllButtonsUnderColumnOrderingSectionOnModalContentPage() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheButtonsShowAllAndHideAllAreDisplayedOnModalContent();
        Debugger.println("test + " + testResult);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the Re-Ordering Section of DisplayOptions sections displayed correctly")
    public void reOrderingSectionOfDisplayOptionsDisplayedCorrectly() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyColumnOrderingSectionDisplay();
        Assert.assertTrue(testResult);
    }


    @And("the user clicks on the button {string}")
    public void theUserClicksOnTheButton(String showOrHideButton) {
        boolean testResult = false;
        testResult = miPortalHomePage.clickShowAllOrHideAllButton(showOrHideButton);
        Assert.assertTrue(testResult);
    }


    @When("the user drag the column header {string} from the section {string} to {string} section")
    public void theUserDragTheColumnHeaderFromTheSectionToSection(String columnHeader, String fromSection, String toSection) {
        boolean testResult = false;
        if(toSection.equalsIgnoreCase("Hide")) {
            testResult = miPortalFileSubmissionPage.addColumnHeadersToHideSection(columnHeader);
        }else{
            testResult = miPortalFileSubmissionPage.addColumnHeadersToShowSection(columnHeader);
        }
        Assert.assertTrue(testResult);
    }

    @And("the Save and Close button under Show All and Hide All button becomes disabled")
    public void theSaveAndCloseButtonUnderShowAllAndHideAllButtonBecomesDisabled() {
        boolean testResult = false;
        testResult = miPortalHomePage.saveAndCloseButtonIsDisabled();
        Assert.assertTrue(testResult);
    }

    @And("the user save the changes on modal content by clicking Save and Close button")
    public void theUserSaveTheChangesOnModalContentByClickingSaveAndCloseButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickSaveAndCloseButtonOnModalContent();
        Assert.assertTrue(testResult);
    }

    @And("the user click on the {string} check box on the modal content page")
    public void theUserClickOnTheCheckBoxOnTheModalContentPage(String checkBox) {
        boolean testResult = false;
        testResult = miPortalHomePage.clickOnCheckBoxOptionsForSaveSpaceOnScreen(checkBox);
        Assert.assertTrue(testResult);
    }



    @And("the user fills in a {string} in the {string} search box")
    public void theUserFillsInAInTheSearchBox(String value, String searchBox) {
        boolean testResult = false;
        testResult = miPortalHomePage.fillValueInTheTextSearchBox(value, searchBox);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on save and close button")
    public void theUserClicksOnSaveAndCloseButton() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickOnSaveAndCloseButton();
        Assert.assertTrue(testResult);
    }

    @Then("the user validates there is no feedback link on the page")
    public void theUserValidatesThereIsNoFeedbackLinkOnThePage() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyPresenceOfFeedbackLink();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the {string} logo and environment name at their position")
    public void theUserSeesTheLogoAndEnvironmentNameAtTheirPosition(String logoName) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyPortalLogoAndEnvName(logoName);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the selected {string} in the {string} value drop-down")
    public void theUserSeesTheSelectedInTheValueDropDown(String value, String dropDownField) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifySelectedDropDownValue(value, dropDownField);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the buttons - Add, Search, Reset buttons under the Search boxes")
    public void theUserSeesTheButtonsAddSearchResetButtonsUnderTheSearchBoxes() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheButtonsAddSearchResetAreDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the buttons - Reset, Save and close under Display Options")
    public void theUserSeesTheButtonsResetSaveAndCloseUnderDisplayOptions() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyTheButtonsResetSaveAndCloseAreDisplayed();
        Assert.assertTrue(testResult);
    }



    @And("the user verify the date format displayed")
    public void theUserVerifyTheDateFormatDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyDateFormat();
        Assert.assertTrue(testResult);
    }

    @And("the user verify the Compact Size of Screen")
    public void theUserVerifyTheCompactSizeOfScreen() {
        boolean testresult = false;
        testresult = miPortalHomePage.verifyHorizontalScrollBar();
        Assert.assertTrue(testresult);
    }

    @When("the user should be able to see sample processing menu is displayed")
    public void theUserShouldBeAbleToSeeSampleProcessingMenuIsDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfSampleProcessingMenu();
        Assert.assertTrue(testResult);
    }

    @Given("the user is able to see data quality menu is displayed")
    @When("the user sees the data quality menu is displayed")
    @Then("the user should be able to see data quality menu is displayed")
    public void theUserShouldBeAbleToSeeDataQualityMenuIsDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfDataQualityMenu();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the below header sections in Sample Processing")
    public void theUserShouldBeAbleToSeeTheBelowHeaderSectionsInSampleProcessing(DataTable inputSections) {
        try {
            Wait.seconds(10);
            boolean testResult = false;
            List<List<String>> linkDetails = inputSections.asLists();
            int actualSize=miPortalHomePage.getNumberOfSectionHeader();
            //actualSize -2 is given since the xpath counts the total number of menus in the landing page which is 10 but as per the scenario
            //below Sample processsing menu there is only 8 sections
            if((actualSize-2) != (linkDetails.size()-1)){
                Assert.fail("Mismatch observed in the number of section headers");
            }
            for (int i = 1; i < linkDetails.size(); i++) {
                testResult = miPortalHomePage.verifyThePresenceOfSectionHeader(linkDetails.get(i).get(0));
                if (!testResult) {
                    Debugger.println("Header " + linkDetails.get(i).get(0) + " could not verify in Sample Processing Section.");
                    Assert.fail(linkDetails.get(i).get(0)+" Could not verify in Sample Processing Section");
                }
                Wait.seconds(2);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from Sample Processing Section Header " + exp);
            Assert.assertFalse("MiHomePageSteps: Exception from Sample Processing Section Header " + exp, true);
        }
    }
    @And("the user should be able to see Participant NHS Spine Data menu is displayed")
    public void theUserShouldBeAbleToSeeNHSSpineDataMenuIsDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfParticipantNHSSpineDataMenu();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the below header sections in Data Quality")
    public void theUserShouldBeAbleToSeeTheBelowHeaderSectionsInDataQuality(DataTable inputSections) {
        try {
            Wait.seconds(10);
            boolean testResult = false;
            List<List<String>> linkDetails = inputSections.asLists();
            int actualSize = miPortalHomePage.getNumberOfDataQualitySectionHeader();
            //actualSize -9 is given since the xpath counts the total number of menus in the landing page which is 10 but as per the scenario
            //below Data Quality section there is only 1 report
            if((actualSize-9) != (linkDetails.size()-1)){
                Assert.fail("Mismatch observed in the number of section headers");
            }
                for (int i = 1; i < linkDetails.size(); i++) {
                    testResult = miPortalHomePage.verifyThePresenceOfSectionHeaderUnderDataQuality(linkDetails.get(i).get(0));
                    if (!testResult) {
                        Debugger.println("Header " + linkDetails.get(i).get(0) + " could not verify in Data Quality Section.");
                        Assert.fail(linkDetails.get(i).get(0) + " Could not verify in Data Quality Section");
                    }
                    Wait.seconds(2);
                }
            } catch(Exception exp){
                Debugger.println("Exception from Data Quality Section Header " + exp);
                Assert.assertFalse("MiHomePageSteps: Exception from Data Quality Section Header " + exp, true);
            }
        }

    @And("the user should be able to see {string} search boxes in the {string} page")
    public void theUserShouldBeAbleToSeeSearchBoxesInThePage(String numberOfSearchField, String section) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfSearchBoxes(numberOfSearchField, section);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the different values between columns {string} and {string}")
    public void theUserShouldBeAbleToSeeTheDifferentValuesBetweenColumnsAnd(String firstColumn, String secondColumn) {
        boolean testresult = false;
        testresult = miPortalHomePage.verifyTheDifferentDataBetweenColumns(firstColumn, secondColumn);
        Assert.assertTrue(testresult);
    }

    @And("the user should be able to see the empty {string} label and the data with {string} label")
    public void theUserShouldBeAbleToSeeTheEmptyLabelAndTheDataWithLabel(String emptyData, String data) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfDataInShowHideLabel(emptyData, data);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see Save and Close button as (.*)")
    public void theUserShouldSeeSaveAndCloseButtonAs(String status) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifySaveAndCloseButtonStatus();
        if (status.equalsIgnoreCase("enabled")) {
            Assert.assertTrue(testResult);
        } else {
            Assert.assertFalse(testResult);
        }
    }

    @Then("search results are displayed in the search results")
    public void searchResultsAreDisplayedInTheSearchResults() {
        boolean testResult = false;
        testResult = miPortalHomePage.searchResultTableIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the user inputs the {string} reference number")
    public void theUserInputsTheReferenceNumber(String LSIDRefNo) {
        boolean testResult = false;
        testResult = miPortalHomePage.clickOnSearchAndPassLSIDRefNo(Integer.parseInt(LSIDRefNo));
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on Find LSID")
    public void theUserClicksOnFindLSID() {
        boolean testResult = false;
        testResult = miPortalHomePage.clickOnFindLSID();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the explanation in the {string} column's cell for the value {string} in the {string} column")
    public void theUserShouldBeAbleToSeeTheExplanationInTheColumnSCellForTheValueInTheColumn(String explanationHeader, String cellValue, String columnHeader) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfExplanationForTheCellData(explanationHeader, cellValue, columnHeader);
        Assert.assertTrue(testResult);
    }

    @Then("the user should not be able to see the explanation in the {string} column for the empty cell in the {string} column")
    public void theUserShouldNotBeAbleToSeeTheExplanationInTheColumnForTheEmptyCellInTheColumn(String explanationColumn, String inputColumn) {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfEmptyExplanationForTheEmptyCell(explanationColumn, inputColumn);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the non-empty data cell in the {string} column")
    public void theUserShouldBeAbleToSeeTheNonEmptyDataCellInTheColumn(String columnName) {
        boolean testResult = false;
        String[] valueList = null;
        if (!columnName.contains(",")) {
            valueList = new String[]{columnName};
        } else {
            valueList = columnName.split(",");
        }
        for (int i = 0; i < valueList.length; i++) {
            testResult = miPortalHomePage.verifyThePresenceOfNonEmptyColumn(valueList[i]);
            if (!testResult) {
                Debugger.println(valueList[i] + " column not found.");
                Assert.assertFalse("Column not found", true);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user sees the search results pagination entry drop-down with unchanged selection of {string}")
    public void theUserSeesTheSearchResultsPaginationEntryDropDownWithUnchanfedSelectionOf(String expectedEntryPagination) {
        String actualEntryPagination = miPortalHomePage.getTheSelectedPaginationEntryValue();
        Debugger.println("ExpectedEntry: " + expectedEntryPagination + " : ActualEntry :" + actualEntryPagination);
        Assert.assertTrue(actualEntryPagination.contains(expectedEntryPagination));
    }

    @Then("search results are displayed in table format with display options button")
    public void searchResultsAreDisplayedInTableFormatWithDisplayOptionsButton() {
        boolean testResult = false;
        Wait.seconds(8);
        testResult = miPortalHomePage.verifyThePresenceOfResultInTableFormatWithDisplayOptions();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees an error message {string} on the page")
    public void theUserSeesAnErrorMessageOnThePage(String errorMessage) {
        boolean testresult = false;
        testresult = miPortalHomePage.verifyErrorMessage(errorMessage);
        Assert.assertTrue(testresult);
    }

    @And("the user click on {string} section select the filters {string} and click on Add and Search buttons and verify the table loaded")
    public void theUserClickOnFileSubmissionSectionSelectTheFiltersAndClickOnAddButtonAndClickOnSearchButton(String miPage, String filterValue) {
        Assert.assertTrue(miPortalHomePage.navigateToMiPage(miPage));
        Debugger.println("Navigated to " + miPage);
        if (miPage.equals("File Submissions"))
        {
            Assert.assertTrue(miPortalFileSubmissionPage.selectDropDownSearchColumn("Status"));
            Assert.assertTrue(miPortalFileSubmissionPage.selectDropDownSearchOperator("is"));
            Assert.assertTrue(miPortalFileSubmissionPage.selectDropDownSearchValue(filterValue));
        } else if(miPage.equals("Order Tracking") ){
            Assert.assertTrue(miOrderTrackingPage.selectOrderTrackingDropDownSearchValue(filterValue));
        } else if(miPage.equals("GLH Samples") ){
            Assert.assertTrue(miGlhSamplesPage.selectGlhDropDownSearchValue(filterValue));
        }else if(miPage.equals("Plater Samples") ){
            Assert.assertTrue(miPlaterSamplesPage.selectPlaterSamplesDropDownSearchValue(filterValue));
        }else if(miPage.equals("Picklists") ){
            Assert.assertTrue(miPickListsPage.selectPickListsDropDownSearchValue(filterValue));
        }else if(miPage.equals("Sequencer Samples") ){
            Assert.assertTrue(miSequencerSamplesPage.selectSequencerSamplesDropDownSearchValue(filterValue));
        }else if(miPage.equals("New Referrals") ){
            Assert.assertTrue(miNewReferralsPage.selectNewReferralsDropDownSearchValue(filterValue));
        }
        Assert.assertTrue(miPortalHomePage.clickAddButton());
        Debugger.println("Add button is clicked");
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIPortal_"+TestUtils.removeAWord(miPage," ")+".jpg");
        }
        Assert.assertTrue(miPortalHomePage.clickSearchButton());
        Debugger.println("Search button is clicked");
        Assert.assertTrue(miPortalHomePage.verifyTheElementsInTheSearchResultSection());
        Debugger.println("The elements present in " + miPage + " search results are verified");
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIPortal_"+TestUtils.removeAWord(miPage," ")+"_Result.jpg");
        }
        Assert.assertTrue(miPortalHomePage.clickResetButton());
    }

    @When("the user clicks the MIPortal Log out button")
    public void theUserClicksTheMIPortalLogOutButton() {
        miPortalHomePage.logOutFromMIPortal();
    }


    @And("the user verifies the {string} data from MI table against {string}")
    public void theUserVerifiesTheFileSubmissionsDataFromMITableAgainst(String sheetName,String fileName) {
        boolean testResult = false;
        testResult =  miPortalHomePage.validateDataInAllReports(fileName,sheetName);
        Assert.assertTrue(testResult);
    }

    @When("the user should be able to see sample failures menu is displayed")
    public void theUserShouldBeAbleToSeeSampleFailuresMenuIsDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfSampleFailureMenu();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the below header sections in sample failures")
    public void theUserShouldBeAbleToSeeTheBelowHeaderSectionsInSampleFailures(DataTable inputSections) {
        try {
            Wait.seconds(10);
            boolean testResult = false;
            List<List<String>> linkDetails = inputSections.asLists();
            int actualSize=miPortalHomePage.getNumberOfSampleFailureSectionHeader();
            //actualSize -9 is given since the xpath counts the total number of menus in the landing page which is 10 but as per the scenario
            //below Sample Failures section there is only 1 report
            if((actualSize-9) != (linkDetails.size()-1)){
                Assert.fail("Mismatch observed in the number of section headers");
            }
            for (int i = 1; i < linkDetails.size(); i++) {
                testResult = miPortalHomePage.verifyThePresenceOfSectionHeaderUnderSampleFailures(linkDetails.get(i).get(0));
                if (!testResult) {
                    Debugger.println("Header " + linkDetails.get(i).get(0) + " could not verify in Sample Failures Section.");
                    Assert.fail(linkDetails.get(i).get(0) + " Could not verify in Sample Failures Section");
                }
                Wait.seconds(2);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from Sample Failures Section Header " + exp);
            Assert.assertFalse("MiHomePageSteps: Exception from Sample Failures Section Header " + exp, true);
        }
    }

    @When("the user should be able to see sample failures report menu is displayed")
    public void theUserShouldBeAbleToSeeSampleFailuresReportMenuIsDisplayed() {
        boolean testResult = false;
        testResult = miPortalHomePage.verifyThePresenceOfSampleFailuresMenu();
        Assert.assertTrue(testResult);
    }

    @And("the user navigates to the {string} stage in mi-portal")
    public void theUserNavigatesToTheStageInMiPortal(String miPage1) {
        boolean testResult = false;
        testResult = miPortalHomePage.navigateToSampleFailuresPage(miPage1);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIPageNavigation.jpg");
            Assert.fail("Could not navigate to MI Page");
        }
    }
}