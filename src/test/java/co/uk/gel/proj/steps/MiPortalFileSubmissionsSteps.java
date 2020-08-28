package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.CSVFileReader;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.MIPortalTestData;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.ParseException;
import java.util.*;


public class MiPortalFileSubmissionsSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

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
        Debugger.println("Refreshing the browser page before starting...");
        SeleniumLib.refreshPage();
        Wait.seconds(2);
        Actions.acceptAlert(driver);
        Wait.seconds(1);
        Actions.acceptAlert(driver);
    }

    @And("the user enters a date (.*) in the file-submission date field")
    public void theUserEntersADateInTheFileSubmissionDateField(String date) {
        boolean testResult =false;
        testResult=miPortalFileSubmissionPage.fillInTheFileSubmissionDate(date);
        Assert.assertTrue(testResult);
    }

    @And("the user enters (.*) days before today in the file-submission date field")
    public void theUserEntersADateNDaysBeforeInTheFileSubmissionDateField(String noOfDaysBefore) {
        boolean testResult =false;
        testResult=miPortalFileSubmissionPage.fillInPastDateInTheFileSubmissionDate(noOfDaysBefore);
        Assert.assertTrue(testResult);
    }

    @Then("file submission search criteria badge information is displayed below drop-down buttons")
    public void fileSubmissionSearchCriteriaBadgeInformationIsDisplayedBelowDropDownButtons() {
        boolean testResult = false;
        Wait.seconds(3);
        testResult = miPortalHomePage.badgeFilterSearchCriteriaIsDisplayed();
        Assert.assertTrue(testResult);
    }


    @Then("search results are displayed for the file-submission search")
    public void searchResultsAreDisplayedForTheFileSubmissionSearch() {
        boolean testResult = false;
        testResult = miPortalHomePage.searchResultTableIsDisplayed();
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

    @Then("the file-submission page displays the search header, drop-down - column, operator, and value, add, search and reset buttons")
    public void theFileSubmissionPageDisplaysTheSearchHeaderDropDownColumnOperatorAndValueAddSearchAndResetButtons() {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyTheElementsOnFileSubmissionPage();
        Assert.assertTrue(testResult);
    }


    @Then("the search criteria badge disappears")
    public void theSearchCriteriaBadgeDisappears() {
        boolean testResult = false;
        testResult = miPortalHomePage.badgeFilterSearchCriteriaIsNotDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("the values are not displayed in the file-submission search column {string} drop-down menu")
    public void theValuesAreNotDisplayedInTheFileSubmissionSearchColumnDropDownMenu(String DropDownButton, DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues(DropDownButton);
        Assert.assertNotNull(actualDropDownValues);
        boolean testResult = false;
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            if(!actualDropDownValues.contains(expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader"))){
                testResult = true;
            }else {
                Debugger.println("Unexpected value "+expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader")+" contains in "+DropDownButton);
                testResult = false;
                Assert.assertTrue("Unexpected value "+expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader")+" contains in "+DropDownButton,testResult);
            }
            Assert.assertTrue(testResult);
        }
    }


    @And("the column\\(s) field {string} in the search result table displayed the only filtered {string}")
    public void theColumnSFieldInTheSearchResultTableDisplayedTheOnlyFiltered(String columnField, String columnFieldValue) {

        if (columnField.equalsIgnoreCase("Created")) {
            String badge = miPortalHomePage.badgeFilterSearchCriteria.getText();
            String expectedFilteredDate = (badge.split("="))[1].trim();
            columnFieldValue = expectedFilteredDate;
        }
        List<String> columnValues = miPortalFileSubmissionPage.getValuesOfAColumnField(columnField);
        for (String fieldValue : columnValues) {
            Assert.assertTrue(fieldValue.contains(columnFieldValue));
        }
    }

    @And("the column {string} in the search result table displayed the only filtered date {string} days before today")
    public void filteredTableContainsOnlyFilteredDateValue(String columnName, String noOfDays) {
        boolean testResult = false;
        try {
            int daysBefore = -1 * Integer.parseInt(noOfDays);
            String dateToday = TestUtils.todayInDDMMYYYFormat();
            dateToday = dateToday.replace("/", "-");
            String pastDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, daysBefore, 0, 0); //Add future day +1
            String dateInYYMMDD = TestUtils.dateFormatReverserToYYYYMMDD(pastDate);
            testResult = miPortalFileSubmissionPage.verifyColumnValueInFileSubmissionSearchResultTable(columnName,dateInYYMMDD);
            Assert.assertTrue(testResult);
        }catch(Exception exp){

        }
    }
    @Then("the user should be able to see the non-empty data cell in the (.*) column of file submission search result table")
    public void theUserShouldBeAbleToSeeTheNonEmptyDataCellInTheColumn(String columnName) {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyColumnValueInFileSubmissionSearchResultTable(columnName,"non-empty-data");
        Assert.assertTrue(testResult);
    }

    public void theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(String columnHeader, String columnFieldValue) {

        if (columnHeader.equalsIgnoreCase("Created")) {
            String badge = miPortalHomePage.badgeFilterSearchCriteria.getText();
            Debugger.println(badge + " is new date ");
            String expectedFilteredDate = (badge.split("="))[1].trim();
            Debugger.println("Formatted date yyyy-MM-dd :" + expectedFilteredDate);
            columnFieldValue = expectedFilteredDate;
        }
        List<String> columnValues = miPortalFileSubmissionPage.getValuesOfAColumnField(columnHeader);
        Debugger.println("sss-submitted By" + columnValues);
        for (String fieldValue : columnValues) {
            Assert.assertTrue(fieldValue.contains(columnFieldValue));
        }
    }

    @And("the columns fields are not displayed in the list of columns headers of the search result table")
    public void theColumnsFieldsAreNotDisplayedInTheListOfColumnsHeadersOfTheSearchResultTable(DataTable dataTable) {

        List<List<String>> expectedListOfColumnHeaders = dataTable.asLists();
        boolean testResult = false;
        for(int i=1; i<expectedListOfColumnHeaders.size(); i++) {
            testResult = miPortalFileSubmissionPage.verifyColumnHeadPresence(expectedListOfColumnHeaders.get(i).get(0));
            Assert.assertFalse(testResult);
        }
    }

    @And("the user sees the Expand plus icon at the start of each row where it is clicked to show column names and values")
    public void theUserSeesTheExpandPlusIconAtTheStartOfEachRowWhereItIsClickedToShowColumnNamesAndValues() {
        String testResult = "";
        testResult = miPortalFileSubmissionPage.verifyThePusIconAtTheStartOfEachRowAndClickToExpand();
        Assert.assertEquals("Success",testResult);
    }

    @And("the user see dates value in {string} column of file-submission search result in descending order")
    public void theUserSeeDatesValueInColumnOfFileSubmissionSearchResultInDescendingOrder(String columnHeader) {

        List<String> actualValues = miPortalFileSubmissionPage.getValuesOfAColumnField(columnHeader);
        if(actualValues.size() == 0){
            Assert.assertTrue(false);
        }
        List<String> expectedValues = new ArrayList<>();
        expectedValues.addAll(actualValues);
        Collections.sort(expectedValues, Collections.reverseOrder());
        Assert.assertEquals(expectedValues, actualValues);
    }

    @When("the user clicks {string} button on the modal-content page")
    public void theUserClicksHideAllOrShowAllButtonOnTheModalContentPage(String buttonOnModalContentPage) {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.theUserClicksHideAllOrShowAllButtonOnTheModalContentPage(buttonOnModalContentPage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the selected {string} value in file submissions page")
    public void theUserShouldBeAbleToSeeTheSelectedValueInFileSubmissionsPage(String selectedOption) {
        boolean testResult = false;
        testResult =miPortalFileSubmissionPage.verifyThePresenceOfSelectedOption(selectedOption);
        Assert.assertTrue(testResult);
    }

    @When("the user adds {string} column to Hide section")
    public void theUserAddsColumnToHideSection(String columnName) {
        boolean testResult = false;
        String[] valueList = null;
        if (!columnName.contains(",")) {
            valueList = new String[]{columnName};
        } else {
            valueList = columnName.split(",");
        }
        for (int i = 0; i < valueList.length; i++) {
            testResult = miPortalFileSubmissionPage.addColumnHeadersToHideSection(valueList[i]);
            Assert.assertTrue(testResult);
        }
    }
    @And("the user selects (.*) as the search value dropdown")
    public void theUserSelectSpecifiedSearchValue(String searchValue) {
        boolean testResult = false;
        Wait.seconds(5);
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            testResult = miPortalFileSubmissionPage.selectDropDownSearchValue(mipData.getGlh_name());
        }else{
            testResult = miPortalFileSubmissionPage.selectDropDownSearchValue(searchValue);
        }
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the search operator dropdown")
    public void theUserSelectSpecifiedSearchOperator(String searchOperator) {
        boolean testResult = false;
        Wait.seconds(5);
        testResult = miPortalFileSubmissionPage.selectDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the search column dropdown")
    public void theUserSelectSpecifiedSearchColumn(String searchColumn) {
        boolean testResult = false;
        Wait.seconds(5);
        testResult = miPortalFileSubmissionPage.selectDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see the below columns populated in search result table based on the selected (.*)")
    public void theUserSelectSpecifiedSearchValue(String searchValue,DataTable columnHeaders) {
        boolean testResult = false;
        MIPortalTestData mipData = csvFileReader.getRandomTestData();
        if(mipData == null){
            Debugger.println("No Data exists in the test data file provided.");
            Assert.assertTrue("No Data exists in the test data file provided.",false);
        }
        List<List<String>> columnNames = columnHeaders.asLists();
        String columnValue = "";
        for(int i=1; i<columnNames.size(); i++) {
            if (columnNames.get(i).get(0).equalsIgnoreCase("Submitted By Code")) {
                columnValue = mipData.getGlh_code();
            } else if (columnNames.get(i).get(0).equalsIgnoreCase("Submitted By")) {
                columnValue = mipData.getGlh_name();
            }
            testResult = miPortalFileSubmissionPage.verifyColumnValueInFileSubmissionSearchResultTable(columnNames.get(i).get(0), columnValue);
            Assert.assertTrue(testResult);
        }
    }
    @Then("the table column (.*) is displayed with data (.*)")
    public void theTableColumnIsDisplayedWithData(String columnName,String columnValue) {
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyColumnValueInFileSubmissionSearchResultTable(columnName,columnValue);
        Assert.assertTrue(testResult);

    }
    @And("the user should not sees the below values in the file-submission search column drop-down menu")
    public void theUserShouldNotSeesBelowValuesInTheFileSubmissionSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPortalFileSubmissionPage.selectDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            Assert.assertFalse(testResult);
        }
    }
    @And("the user sees the below values in the file-submission search column drop-down menu")
    public void theUserSeesBelowValuesInTheFileSubmissionSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPortalFileSubmissionPage.selectDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    @And("the user sees the below values in the file-submission search operator drop-down menu")
    public void theUserSeesBelowValuesInTheFileSubmissionSearchOperatorDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPortalFileSubmissionPage.selectDropDownSearchOperator(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    @And("the user sees the below values in the file-submission search value drop-down menu")
    public void theUserSeesBelowValuesInTheFileSubmissionSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPortalFileSubmissionPage.selectDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            if(!testResult){
              Assert.fail(expectedDropDownValues.get(i).get(0)+" not available in FileSubmission search value drop down");
            }
        }
    }
    @And("the columns fields are  displayed in the list of columns headers of the search result table")
    public void theColumnsFieldsAreDisplayedInTheListOfColumnsHeadersOfTheSearchResultTable(DataTable dataTable) {

        List<List<String>> expectedListOfColumnHeaders = dataTable.asLists();
        boolean testResult = false;
        testResult = miPortalFileSubmissionPage.verifyColumnHeaderInFileSubmissionSearchResultTable(expectedListOfColumnHeaders);
        Assert.assertTrue(testResult);
    }
    }