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

public class MiPortalPlaterSamplesSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalPlaterSamplesSteps(SeleniumDriver driver) {
        super(driver);
}

    @And("the user enters a date {string} in the plater-samples date field")
    public void theUserEntersADateInThePlaterSamplesDateField(String date) {
        boolean testResult=false;
        testResult=miPlaterSamplesPage.fillInThePlaterSamplesDate(date);
        Assert.assertTrue(testResult);
    }

    @And("the user enters (.*) days before today in the plater sample date field")
    public void theUserEntersADateNDaysBeforeInThePlaterSampleDateField(String noOfDaysBefore) {
        boolean testResult =false;
        testResult=miPlaterSamplesPage.fillInPastDateInThePlaterSampleDate(noOfDaysBefore);
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the plater samples search input value")
    public void theUserSelectSpecifiedPlaterSamplesSearchInputValue(String searchValue) {
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

        testResult = miPlaterSamplesPage.enterPlaterSampleTextSearchValue(searchValue);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the plater samples search value dropdown")
    public void theUserSelectSpecifiedPlaterSamplesSearchValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchValue(mipData.getGlh_name());
        }else{
            testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchValue(searchValue);
        }
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the plater samples search operator dropdown")
    public void theUserSelectSpecifiedPlaterSamplesSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the plater samples search column dropdown")
    public void theUserSelectSpecifiedPlaterSamplesSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the plater samples search value drop-down menu")
    public void theUserSeesBelowValuesInThePlaterSamplesSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            if(!testResult){
                Assert.assertTrue(expectedDropDownValues.get(i).get(0)+" Not present in Plater Samples.",testResult);
            }
            Wait.seconds(2);
        }
    }

    @And("the user sees the below values in the plater samples search column drop-down menu")
    public void theUserSeesBelowValuesInThePlaterSamplesSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miPlaterSamplesPage.selectPlaterSamplesDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            if(!testResult) {
                Assert.assertTrue(expectedDropDownValues.get(i).get(0) + "not present in plater samples.", testResult);
            }
            Wait.seconds(2);
        }
    }

    @Then("the plater samples search result table column (.*) is displayed with data (.*)")
    public void thePlaterSamplesSearchResultIsDisplayedWithData(String columnName,String columnValue) {
        boolean testResult = false;
        testResult = miPlaterSamplesPage.verifyColumnValueInPlaterSamplesSearchResultTable(columnName,columnValue);
        Assert.assertTrue(testResult);
    }

    @And("the user can not see the value {string} under search column dropdown")
    public void theUserCanNotSeeTheValueUnderSearchColumnDropdown(String expectedHeaderValue) {
        boolean testResult = false;
        testResult = miPlaterSamplesPage.verifyExpectedColumnNameInPlaterSamplesSearchColumnDropdown(expectedHeaderValue);
        Assert.assertTrue(testResult);
    }
}