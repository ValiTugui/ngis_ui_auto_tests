package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.CSVFileReader;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.MIPortalTestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class MiPortalPickListsSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalPickListsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user enters a date {string} in the pick lists date field")
    public void theUserEntersADateInThePickListsDateField(String date) {
        boolean testResult=false;
        testResult=miPlaterSamplesPage.fillInThePlaterSamplesDate(date);
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the pick lists search value dropdown")
    public void theUserSelectSpecifiedPickListsSearchValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            testResult = miPickListsPage.selectPickListsDropDownSearchValue(mipData.getGlh_name());
        }else{
            testResult = miPickListsPage.selectPickListsDropDownSearchValue(searchValue);
        }
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the pick lists search operator dropdown")
    public void theUserSelectSpecifiedPickListsSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miPickListsPage.selectPickListsDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the pick lists search column dropdown")
    public void theUserSelectSpecifiedPickListsSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miPickListsPage.selectPickListsDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the pick lists search value drop-down menu")
    public void theUserSeesBelowValuesInThePickListsSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPickListsPage.selectPickListsDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    @And("the user selects (.*) as the picklists search input value")
    public void theUserEnterSpecifiedPickListsSearchInputValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("Referral ID")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getReferral_id();
        }else if(searchValue.equalsIgnoreCase("Patient NGIS ID")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getPatient_ngsid();
        }

        testResult = miPickListsPage.enterPickListsTextSearchValue(searchValue);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the below values in the pick lists search column drop-down menu")
    public void theUserSeesBelowValuesInThePickListsSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        List actualDropDownValues = miPickListsPage.searchColumnDropDownMenu();
        if (expectedDropDownValues.size() != actualDropDownValues.size()) {
            Debugger.println("Mismatch in number of options, Expected: "+expectedDropDownValues.size()+" Actual: "+actualDropDownValues.size());
            Assert.assertTrue(false);
        }
        String actValue = "", expValue = "";
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            actValue = actualDropDownValues.get(i).toString();
            expValue = expectedDropDownValues.get(i).get(0);
            if (!actValue.equalsIgnoreCase(expValue)) {
                Assert.assertTrue(false);
            }
        }
    }

    @And("the columns fields are displayed in the list of columns headers of the pick lists search result table")
    public void theColumnsFieldsAreDisplayedInPickListsResultTable(DataTable dataTable) {
        List<List<String>> expectedListOfColumnHeaders = dataTable.asLists();
        boolean testResult = false;
        testResult = miPickListsPage.verifyColumnHeaderInPickListsSearchResultTable(expectedListOfColumnHeaders);
        Assert.assertTrue(testResult);
    }

    @Then("the pick lists search result table column (.*) is displayed with data (.*)")
    public void thePickListsSearchResultIsDisplayedWithData(String columnName,String columnValue) {
        boolean testResult = false;
        testResult = miPickListsPage.verifyColumnValueInPickListsSearchResultTable(columnName,columnValue);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the below values in the pick lists search operator dropdown menu")
    public void theUserSeesTheBelowValuesInThePickListsSearchOperatorDropdownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPickListsPage.verifyPicklistsDropDownSearchOperator(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    }
