package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MiPortalOrderTrackingSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalOrderTrackingSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user selects (.*) as the order tracking search value dropdown")
    public void theUserSelectSpecifiedOrderTrackingSearchValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getGlh_name();
        }else if(searchValue.equalsIgnoreCase("OrderingEntity")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getOrdering_entity();
        }else if(searchValue.equalsIgnoreCase("Test Type")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getTest_type();
        }else if(searchValue.indexOf(",") != -1){
            String[] values = searchValue.split(",");
            Wait.seconds(3);
            for (int i = 0; i < values.length; i++) {
                testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchValue(values[i]);
                if (!testResult) {
                    Debugger.println("URL:" + driver.getCurrentUrl());
                    Assert.fail(values[i] + " not found in Order Tracking Search Value DropDown.");
                }
                Wait.seconds(3);
            }
            return;
        }
        testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchValue(searchValue);
        Assert.assertTrue(testResult);

    }
    @And("the user selects (.*) as the order tracking search operator dropdown")
    public void theUserSelectSpecifiedOrderTrackingSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the order tracking search column dropdown")
    public void theUserSelectSpecifiedOrderTrackingSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the order tracking search value drop-down menu")
    public void theUserSeesBelowValuesInTheOrderTrackingSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    @And("the user selects (.*) as the order tracking search input value")
    public void theUserSelectSpecifiedOrderTrackingSearchInputValue(String searchValue) {
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

        testResult = miOrderTrackingPage.enterOrderTrackingTextSearchValue(searchValue);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the order tracking search column drop-down menu")
    public void theUserSeesBelowValuesInTheOrderTrackingSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miOrderTrackingPage.selectOrderTrackingDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
    @Then("the order tracking search result table column (.*) is displayed with data (.*)")
    public void theOrderTrackingSearchResultIsDisplayedWithData(String columnName, String columnValue) {
        boolean testResult = false;
        testResult = miOrderTrackingPage.verifyColumnValueInOrderTrackingSearchResultTable(columnName, columnValue);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the different values between columns (.*) and (.*)")
    public void theUserShouldBeAbleToSeeDifferentValuesBtweenColumns(String columnName1, String columnName2) {
        boolean testresult = false;
        testresult = miOrderTrackingPage.verifyOrderTrackingResultColumnValuesDifference(columnName1, columnName2);
        Assert.assertTrue(testresult);
    }
    @And("the user should be able to download the filtered order tracking")
    public void theUserShouldBeAbleToDownloadOrderTrackingFiltered() {
        boolean testResult = false;
        testResult = miPortalHomePage.downloadMiCSVFile("order_tracking_filtered");
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("order_tracking_filtered","");
        Assert.assertTrue(testResult);
    }

    @And("User should be able to see data under (.*) column filled based on selected options in third filter box is displayed with (.*)")
    public void userShouldBeAbleToSeeDataUnderAndColumnFilledBasedOnSelectedOptionInThirdFilterBox(String columnName, String columnValue) {
        boolean testResult = false;
        testResult = miOrderTrackingPage.verifyColumnValueInOrderTrackingSearchResultTable(columnName, columnValue);
        Assert.assertTrue(testResult);
    }

    @And("User should be able to see data under given {string} and matches excel data {string} with the Ordering Entity options")
    public void userShouldBeAbleToSeeDataUnderGivenAndMatchesExcelDataWithTheOrderingEntityOptions(String glhName, String fileName) {
        boolean testResult = false;
        testResult = miOrderTrackingPage.verifyColumnDropdownInOrderTrackingSearchOptions(glhName, fileName);
        Assert.assertTrue(testResult);
    }
}
