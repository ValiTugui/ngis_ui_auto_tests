package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.CSVFileReader;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.MIPortalTestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;

import java.util.List;

public class MiPortalSequencerSamplesSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalSequencerSamplesSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user enters a date {string} in the sequencer samples date field")
    public void theUserEntersADateInTheSequencerSamplesDateField(String date) {
        boolean testResult=false;
        testResult=miPlaterSamplesPage.fillInThePlaterSamplesDate(date);
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the sequencer samples search value dropdown")
    public void theUserSelectSpecifiedSequencerSamplesSearchValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchValue(mipData.getGlh_name());
        }else{
            testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchValue(searchValue);
        }
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the sequencer samples search operator dropdown")
    public void theUserSelectSpecifiedSequencerSamplesSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the sequencer samples search column dropdown")
    public void theUserSelectSpecifiedSequencerSamplesSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the sequencer samples search value drop-down menu")
    public void theUserSeesBelowValuesInTheSequencerSamplesSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            if(!testResult){
                Assert.assertTrue(expectedDropDownValues.get(i).get(0)+"not present in Sequencer samples.",testResult);
            }
            Wait.seconds(2);
        }
    }

    @And("the user sees the below values in the sequencer samples search column drop-down menu")
    public void theUserSeesBelowValuesInTheSequencerSamplesSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        List actualDropDownValues = miSequencerSamplesPage.searchColumnDropDownMenu();
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
    @And("the user verify the date format displayed in sequencer samples result table under column (.*)")
    public void theUserVerifyTheDateFormatDisplayed(String columnName) {
        boolean testResult = false;
        testResult = miSequencerSamplesPage.verifyDateDisplayFormatOnColumn(columnName,"dd/mm/yyyy");
        Assert.assertTrue(testResult);
    }

    @And("the user sees the fields are not displayed under the {string} section")
    public void theUserSeesTheFieldsAreNotDisplayedUnderTheSection(String expColumnHeaderStatus, DataTable dataTable) {
        Wait.seconds(2);
        List <List<String>> expectedListOfColumnHeaders = dataTable.asLists();
        boolean testResult = false;
        testResult = miSequencerSamplesPage.verifyListOfColumnsInHeaderShowOrHidden(expColumnHeaderStatus,expectedListOfColumnHeaders);
        Assert.assertFalse(testResult);
    }

    @And("the user sees the below values in the sequencer samples search operator dropdown")
    public void theUserSeesTheBelowValuesInTheSequencerSamplesSearchOperatorDropdown(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(5);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miSequencerSamplesPage.verifySequencerSamplesDropDownSearchOperator(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }
}