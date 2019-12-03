package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FrameworkSupportStepDefs extends Pages {
    public FrameworkSupportStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^a web browser is at the \"([^\"]*)\" page with confirmation \"([^\"]*)\"$")
    public void navigationSupport(String urlToNavigate , String confirmPageAfterNavigate) {
        System.out.println("new universal getter" +  AppConfig.getPropertyValueFromPropertyFile("MYNewName"));
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(urlToNavigate), confirmPageAfterNavigate);

        
    }
}
