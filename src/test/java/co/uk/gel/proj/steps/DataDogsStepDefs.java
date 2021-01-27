package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.DataDogPage;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class DataDogsStepDefs extends Pages {

    public DataDogsStepDefs(SeleniumDriver driver) {
        super(driver);
    }
    DataDogPage dataDogPage = new DataDogPage(driver);

    @When("the user selects the menu section (.*)")
    public void theUserSelectsTheMenuSection(String menu) {
        boolean testResult = false;
        testResult = dataDogPage.moveToMenuSection(menu);
        Assert.assertTrue(testResult);
    }
    @When("the user moves to core section (.*)")
    public void theUserMovesToCoreSection(String menu) {
        boolean testResult = false;
        testResult = dataDogPage.moveToCoreSection(menu);
        Assert.assertTrue(testResult);
    }

    @And("the user provide the filters values in search box for e2e-latest")
    public void theUserProvideTheFiltersValuesInSearchBoxForEELatest(DataTable values) {
        String testResult = "";
        List<List<String>> fields = values.asLists();
        for (int i = 0; i < fields.size(); i++) {
            testResult = dataDogPage.enterTheSearchValuesInTheSearchBox(fields.get(i).get(0));
            if (!testResult.equalsIgnoreCase("Success")) {
                Assert.fail(testResult);
            }
        }
      }

    @Then("the user can see the host details in the result table")
    public void theUserCanSeeTheHostDetailsInTheResultTable() {
        String testResult = "";
        testResult = dataDogPage.verifySearchResult();
        if(!testResult.equalsIgnoreCase("Success")){
            Assert.fail(testResult);
        }
    }

    @Given("the user logins to the data dog home page")
    public void aWebBrowserIsAtTheDatadogHomePage () {
        String baseURL = "DATADOG_URL";
        String userType = "DATADOG_USER";
        //Get the URL from Properties file
        String requiredPage = AppConfig.getPropertyValueFromPropertyFile(baseURL);
        driver.get(requiredPage);
        String testResult = dataDogPage.loginToDataDogApplication(driver);
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }


    @Then("the user select the services as {string} only")
    public void theUserSelectTheServicesAsOnly(String serviceName) {
        boolean testResult = false;
        testResult = dataDogPage.selectTheOptionInServicesSection(serviceName);
        Assert.assertTrue(testResult);
    }

    @And("the user search and select the service filters (.*)")
    public void theUserSearchAndSelectTheServiceFilters(String filters) {
        String testResult = "";
        testResult = dataDogPage.searchAndSelectFilters(filters);
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
        Wait.seconds(15);
    }
}//end
