package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class KibanaStepDefs extends Pages {

    public KibanaStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("a web browser is at the kibana app search page")
    public void aWebBrowserIsAtTheKibanaAppSearchPage(List<String> attributeOfUrl) {
        String baseURLKibana = attributeOfUrl.get(0);
        Debugger.println("KibanaPage is"+baseURLKibana);
        String testResult = "";
        testResult = kibanaPage.loadKibanaPage(baseURLKibana);
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    @When("the user search for referral id in search box {string} and search for duration {string}")
    public void theUserSearchForReferralIdInSearchBox(String refID,String commonLink) {
        String testResult = "";
        testResult = kibanaPage.searchReferralId(refID,commonLink);
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    @Then("the user clicks on {string} button")
    public void theUserClicksOnButton(String button) {
        boolean testResult = false;
        testResult = kibanaPage.clickOnButton(button);
        Assert.assertTrue(testResult);
    }

    @Then("the user select the filter fields {string}")
    public void theUserSelectTheFilterFields(String filterFields) {
        String testResult = "";
        if(filterFields.indexOf(",")==-1) {
            testResult = kibanaPage.selectFilter(filterFields);
            if(!testResult.equalsIgnoreCase("Success")) {
                Assert.fail(testResult);
            }
        }else{
            String[] filters = filterFields.split(",");
            for(int i=0; i<filters.length; i++){
                testResult = kibanaPage.selectFilter(filters[i]);
                if(!testResult.equalsIgnoreCase("Success")) {
                    Assert.fail(testResult);
                    break;
                }
            }
        }
    }
    @Then("the user should see the search results loaded with the filter fields {string}")
    public void theUserShouldSeeTheSearchResultsWithTheFilterFields(String filterFields) {
        String testResult = "";
        testResult = kibanaPage.verifySearchResults(filterFields);
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    @When("the user saves the report")
    public void theUserSavesTheReport() {
        String testResult = "";
        testResult = kibanaPage.saveReport();
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    @And("the user share the report as CSV")
    public void theUserShareTheReportAsCSV() {
        String testResult = "";
        testResult = kibanaPage.shareCSVReport();
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    @Then("the user should be able to generate the CSV report")
    public void theUserShouldBeAbleToGenerateTheCSVReport() {
        String testResult = "";
        testResult = kibanaPage.generateCSVReport();
        if(!testResult.equalsIgnoreCase("Success")) {
            Assert.fail(testResult);
        }
    }

    //appHome.testMethod step removed as it is not part of current applciation
}//end
