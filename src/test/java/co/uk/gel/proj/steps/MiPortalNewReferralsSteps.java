package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.CSVFileReader;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.MIPortalTestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;

import java.util.List;

public class MiPortalNewReferralsSteps extends Pages {

    CSVFileReader csvFileReader = new CSVFileReader();

    public MiPortalNewReferralsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user enters a date {string} in the new referrals date field")
    public void theUserEntersADateInTheNewReferralsDateField(String date) {
        boolean testResult=false;
        testResult=miPlaterSamplesPage.fillInThePlaterSamplesDate(date);
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the new referrals search value dropdown")
    public void theUserSelectSpecifiedNewReferralsSearchValue(String searchValue) {
        boolean testResult = false;
        if(searchValue.equalsIgnoreCase("GLHName")) {
            MIPortalTestData mipData = csvFileReader.getRandomTestData();
            if (mipData == null) {
                Debugger.println("No Data exists in the test data file provided.");
                Assert.assertTrue("No Data exists in the test data file provided.", false);
            }
            testResult = miNewReferralsPage.selectNewReferralsDropDownSearchValue(mipData.getGlh_name());
        }else{
            testResult = miNewReferralsPage.selectNewReferralsDropDownSearchValue(searchValue);
        }
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the new referrals search operator dropdown")
    public void theUserSelectSpecifiedNewReferralsSearchOperator(String searchOperator) {
        boolean testResult = false;
        testResult = miNewReferralsPage.selectNewReferralsDropDownSearchOperator(searchOperator);
        Assert.assertTrue(testResult);
    }
    @And("the user selects (.*) as the new referrals search column dropdown")
    public void theUserSelectSpecifiedNewReferralsSearchColumn(String searchColumn) {
        boolean testResult = false;
        testResult = miNewReferralsPage.selectNewReferralsDropDownSearchColumn(searchColumn);
        Assert.assertTrue(testResult);
    }
    @And("the user sees the below values in the new referrals search value drop-down menu")
    public void theUserSeesBelowValuesInTheNewReferralsSearchValueDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miNewReferralsPage.selectNewReferralsDropDownSearchValue(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

    @And("the user sees the below values in the new referrals search column drop-down menu")
    public void theUserSeesBelowValuesInTheNewReferralsSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miSequencerSamplesPage.selectSequencerSamplesDropDownSearchColumn(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

    @And("the user sees the below values in the new referrals operator drop-down menu")
    public void theUserSeesBelowValuesInTheNewReferralsOperatorDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miNewReferralsPage.selectNewReferralsDropDownSearchOperator(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

}