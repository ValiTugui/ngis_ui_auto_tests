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

import java.text.ParseException;
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


    @And("the user enters a date {string} in the file-submission date field")
    public void theUserEntersADateInTheFileSubmissionDateField(String date) {
        miPortalFileSubmissionPage.fillInTheFileSubmissionDate(date);
    }


    @Then("file submission search criteria badge information is displayed below drop-down buttons")
    public void fileSubmissionSearchCriteriaBadgeInformationIsDisplayedBelowDropDownButtons() {
        boolean testResult = false;
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


    @And("the column\\(s) field {string} in the search result table displayed the only filtered {string}")
    public void theColumnSFieldInTheSearchResultTableDisplayedTheOnlyFiltered(String columnField, String columnFieldValue) {

        switch (columnField) {
            case "Created": {
                theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(columnField, columnFieldValue);
                break;
            }
            case "Submitted By Code": {
                theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(columnField, columnFieldValue);
                break;
            }
            case "Submitted By": {
                theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(columnField, columnFieldValue);
                break;
            }
            case "Status": {
                theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(columnField, columnFieldValue);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid ColumnHeader");
        }
    }

    public void theSpecifiedColumnHeaderDisplaysTheFilteredColumnFieldValues(String columnHeader, String columnFieldValue) {

        if (columnHeader.equalsIgnoreCase("Created")) {
            String badge = miPortalFileSubmissionPage.badgeFilterSearchCriteria.getText();
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

        List<Map<String, String>> expectedListOfColumnHeaders = dataTable.asMaps(String.class, String.class);
        List actualListOfColumnHeaders = miPortalFileSubmissionPage.getAllHeadersInSearchResultTable();

        for (int i = 0; i < expectedListOfColumnHeaders.size(); i++) {
            Debugger.println("Expected " + expectedListOfColumnHeaders.get(i).get("columnHeaders"));
            Debugger.println("Actual list of headers : " + actualListOfColumnHeaders);
            Assert.assertFalse(actualListOfColumnHeaders.contains(expectedListOfColumnHeaders.get(i).get("columnHeaders")));
        }
    }

    @And("the Save and Close button under Show All and Hide All button becomes disabled")
    public void theSaveAndCloseButtonUnderShowAllAndHideAllButtonBecomesDisabled() {
        Assert.assertTrue("Save and Close button is not disabled", !(miPortalHomePage.saveAndCloseButtonIsDisabled()));
    }

}