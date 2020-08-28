package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.CSVFileReader;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.MIPortalTestData;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MiPortalGlhSamplesSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalGlhSamplesSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user enters a sample consignment Number {string} in the search box field")
    public void theUserEntersASampleConsignmentNumberInTheSearchBoxField(String number) {
        boolean testResult = false;
        testResult = miGlhSamplesPage.fillInTheSampleConsignmentNumber(number);
        Assert.assertTrue(testResult);

    }

    @When("the user clicks on the Download CSV button to download the CSV file as {string}.csv in GLH samples page")
    public void theUserClicksOnTheDownloadCSVButtonToDownloadTheCSVFileAsCsvInGLHSamplesPage(String fileName) {
        String dateToday = miGlhSamplesPage.getGlhFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
        fileName = fileName + "-" + dateToday + ".csv";
        Debugger.println("Actual-filename : " + fileName);
        miPortalHomePage.downloadMiCSVFile(fileName);
    }
    @And("the user enters (.*) in the glh search value box")
    public void theUserFillsInAInTheSearchBox(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("ConsignmentNumber")){
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            searchValue = mipData.getConsignment_number();
        }else if(searchValue.equalsIgnoreCase("Referral ID")) {
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
        testResult = miGlhSamplesPage.fillValueInGLHSamplesSearchInputField(searchValue);
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the glh search value dropdown")
    public void theUserSelectSpecifiedSearchValue(String searchValue) {
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
            for(int i=0; i<values.length; i++){
                testResult = miGlhSamplesPage.selectGlhDropDownSearchValue(values[i]);
                Assert.assertTrue(testResult);
            }
            return;
        }
        testResult = miGlhSamplesPage.selectGlhDropDownSearchValue(searchValue);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the glh search operator dropdown")
    public void theUserSelectSpecifiedSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miGlhSamplesPage.selectGlhDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the glh search column dropdown")
    public void theUserSelectSpecifiedSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miGlhSamplesPage.selectGlhDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the glh samples search value drop-down menu")
    public void theUserSeesBelowValuesInTheGlhSamplesSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miGlhSamplesPage.selectGlhDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

    @And("the user sees the below values in the glh samples search column drop-down menu")
    public void theUserSeesBelowValuesInTheGlhSamplesSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miGlhSamplesPage.selectGlhDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            if(!testResult){
                Assert.fail(expectedDropDownValues.get(i).get(0)+" not present in GLH Samples Search Column Drop Down.");
            }
        }
    }
    @Then("the glh search result table column (.*) is displayed with data (.*)")
    public void theTableColumnIsDisplayedWithData(String columnName,String columnValue) {
        boolean testResult = false;
        testResult = miGlhSamplesPage.verifyColumnValueInGlhSamplesSearchResultTable(columnName,columnValue);
        Assert.assertTrue(testResult);
    }
    @And("the user should be able to download the filtered glh samples")
    public void theUserShouldBeAbleToDownloadGLHSamplesFiltered() {
        boolean testResult = false;
        testResult = miPortalHomePage.downloadMiCSVFile("glh_samples_filtered");
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("glh_samples_filtered","");
        Assert.assertTrue(testResult);
    }

    @When("the user double clicks on any data table row and a pop up box is displayed with the row values")
    public void theUserDoubleClicksOnAnyDataTableRow() {
        boolean testResult = false;
        testResult = miGlhSamplesPage.doubleClickDataRow();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on the pop up close icon")
    public void theUserClicksOnThePopUpCloseIcon() {
        boolean testResult = false;
        testResult = miGlhSamplesPage.closePopUpBox();
        Assert.assertTrue(testResult);
    }

}