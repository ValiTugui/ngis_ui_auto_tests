package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.DataDogPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
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
        boolean testResult = false;
        List<List<String>> fields = values.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = dataDogPage.enterTheSearchValuesInTheSearchBox(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user can see the host details in the result table")
    public void theUserCanSeeTheHostDetailsInTheResultTable() {
        boolean testResult = false;
        testResult = dataDogPage.verifyTheHostHeaderInTheTable();
        Assert.assertTrue(testResult);
    }

    @Given("a web browser is at the datadog home page")
    public void aWebBrowserIsAtTheDatadogHomePage (List<String> loginDetailsInput) {
        boolean testResult = false;
        String baseURL = loginDetailsInput.get(0);
        String pageToNavigate = loginDetailsInput.get(1);
        String userType = loginDetailsInput.get(2);
        //Get the URL from Properties file
        String requiredPage = AppConfig.getPropertyValueFromPropertyFile(baseURL);
        Debugger.println("Opening the URL: " + requiredPage + "\n and Page:" + pageToNavigate);
        driver.get(requiredPage);
        Wait.seconds(10);//Wait for 10 Seconds for the page to load
        String navigatedURL = driver.getCurrentUrl();
        Debugger.println("Current URL before LOGIN is :" + navigatedURL);
        if (navigatedURL.contains("app.datadoghq")) {
            if (userType != null) {
                dataDogPage.loginToDataDogApplication(driver, userType);
            } else {
                Assert.fail("There is No User details provided to login.");
            }
        } else if (navigatedURL.contains(pageToNavigate)) {
            Debugger.println("Already logged in, current URL after LOGIN :" + navigatedURL);
        }
        navigatedURL = driver.getCurrentUrl();
        Debugger.println("Current URL after LOGIN :" + navigatedURL);
        if (!navigatedURL.contains(pageToNavigate)) {
            Debugger.println("The open page after login is not correct..." + navigatedURL + "Reloading page..." + requiredPage);
            driver.get(requiredPage);
            Wait.seconds(5); //Wait for page load
        }
        testResult = dataDogPage.openAndVerifyDataDogPage();
        Assert.assertTrue(testResult);
    }


    @Then("the user select the services as {string} only")
    public void theUserSelectTheServicesAsOnly(String serviceName) {
        boolean testResult = false;
        testResult = dataDogPage.selectTheOptionInServicesSection(serviceName);
        Assert.assertTrue(testResult);
    }

    @Then("the user select the checkbox {string} in the services")
    public void theUserSelectTheCheckboxInTheServices(String Name) {
        boolean testResult = false;
        testResult = dataDogPage.selectTheServiceCheckBox(Name);
        Assert.assertTrue(testResult);
    }
}
