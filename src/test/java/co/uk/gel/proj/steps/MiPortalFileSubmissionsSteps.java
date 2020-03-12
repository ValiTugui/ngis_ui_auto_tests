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

    @And("I perform a search on the mi")
    public void iPerformASearchOnTheMi() {

        Actions.clickElement(driver, miPortalFileSubmissionPage.fileSubmissionLnk);
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.FileSubmissionSearchDropDownButton, "Created");
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.FileSubmissionSearchOperatorDropDownButton, "equals");

        //Actions.clearField(patientDetailsPage.getFileSubmissionDate);
        //patientDetailsPage.getFileSubmissionDate.sendKeys("12-02-2020");

        Actions.clickElement(driver,miPortalFileSubmissionPage.addButton);
        Actions.clickElement(driver,miPortalFileSubmissionPage.searchButton);
        Wait.seconds(10);

        // String value = miPortalFileSubmissionPage.getAValueOfSearchedResult("ngis_glh_to_gel_sample_sent_wwm_20200212_161046.csv", 10);
//        String value = miPortalFileSubmissionPage.getAValueOfSearchedResult("LP2723109-DNA_ngis_illumina_to_gel_intake_qc_20200212_170639.csv", 10);
//        Debugger.println("Values " + value);
//        Map<String, String>Test = new HashMap<>();
//         Test =  miPortalFileSubmissionPage.getValuesOfSearchedResult("ngis_glh_to_gel_sample_sent_wwm_20200212_161046.csv");
//        Test =  miPortalFileSubmissionPage.getValuesOfSearchedResult("LP2723109-DNA_ngis_illumina_to_gel_intake_qc_20200212_170639.csv");
//        Debugger.println("Test " + Test);


    }

    @When("the user navigates to the mi-portal {string} stage")
    public void theUserNavigatesToTheMiPortalStage(String miPage) {
       // Actions.clickElement(driver, miPortalFileSubmissionPage.fileSubmissionLnk);
        miPortalHomePage.navigateToMiPage(miPage);
    }


    @And("the user selects a value {string} from the file-submission search column drop-down")
    public void theUserSelectsAValueFromTheFileSubmissionSearchColumnDropDown(String value) {
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.FileSubmissionSearchDropDownButton, value);
    }


    @And("the user selects a search operator {string} from the file-submission search operator drop-down")
    public void theUserSelectsASearchOperatorFromTheFileSubmissionSearchOperatorDropDown(String searchOperator) {
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.FileSubmissionSearchOperatorDropDownButton, searchOperator);
    }

    @And("the user enters a date {string} in the file-submission date field")
    public void theUserEntersADateInTheFileSubmissionDateField(String date) {

        Wait.seconds(2);
        miPortalFileSubmissionPage.getFileSubmissionDate.click();
        miPortalFileSubmissionPage.getFileSubmissionDate.clear();
        Wait.seconds(3);
        miPortalFileSubmissionPage.getFileSubmissionDate.sendKeys(date);
    }

    @And("the user clicks on Add criteria button")
    public void theUserClicksOnAddCriteriaButton() {
        Actions.clickElement(driver,miPortalFileSubmissionPage.addButton);
    }

    @Then("file submission search criteria badge information is displayed below drop-down buttons")
    public void fileSubmissionSearchCriteriaBadgeInformationIsDisplayedBelowDropDownButtons() {
    }

    @When("the user click on the Search button")
    public void theUserClickOnTheSearchButton() {
        Actions.clickElement(driver,miPortalFileSubmissionPage.searchButton);
        Wait.seconds(5);
    }

    @Then("search results are displayed for the file-submission search")
    public void searchResultsAreDisplayedForTheFileSubmissionSearch() {
        //ToDo
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

    @And("the user sees the values in the file-submission search column drop-down menu")
    public void theUserSeesTheValuesInTheFileSubmissionSearchColumnDropDownMenu(DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        miPortalFileSubmissionPage.FileSubmissionSearchDropDownButton.click();
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues();

        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchColumnHeader"), actualDropDownValues.get(i));
        }
    }

    @And("the user sees the values in the search operator drop-down menu")
    public void theUserSeesTheValuesInTheSearchOperatorDropDownMenu(DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
        miPortalFileSubmissionPage.FileSubmissionSearchOperatorDropDownButton.click();
        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues();

        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchOperatorHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchOperatorHeader"), actualDropDownValues.get(i));
        }
    }

    @And("the user sees the values in the search value drop-down menu")
    public void theUserSeesTheValuesInTheSearchValueDropDownMenu(DataTable dataTable) {
        List<Map<String, String>> expectedDropDownValues = dataTable.asMaps(String.class, String.class);
       // Wait.seconds(2);
        miPortalFileSubmissionPage.FileSubmissionSearchValueDropDownButton.click();

        List<String> actualDropDownValues = miPortalHomePage.getDropDownValues();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            Debugger.println("Expected: " + expectedDropDownValues.get(i).get("fileSubmissionsSearchValueHeader") + " : " + "Actual: " + actualDropDownValues.get(i));
            Assert.assertEquals(expectedDropDownValues.get(i).get("fileSubmissionsSearchValueHeader"), actualDropDownValues.get(i));
        }

    }
}