package co.uk.gel.proj.steps;

import co.uk.gel.config.BrowserFactory;
import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.lib.SeleniumLib;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.lang.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestHooks  extends Pages {

    //SeleniumLib seleniumLib;
    public static String currentTagName = "";
    public static String currentTags = "";
    public static String currentFeature = "";
    public static String temptagname = "";
    public static boolean new_scenario_feature = false;

    public TestHooks(SeleniumDriver driver){
        super(driver);
        //seleniumLib = new SeleniumLib(driver);
    }

    @Before
    public void begininingOfTest(Scenario scenario){
        currentTagName = scenario.getSourceTagNames().toString();
        currentTags = scenario.getSourceTagNames().toString();
        currentFeature = scenario.getId().split(";")[0];
        if(temptagname.isEmpty() || !(temptagname.equalsIgnoreCase(currentTagName))){
            Debugger.println("\n"+scenario.getSourceTagNames()+" STARTED");
            temptagname = currentTagName;
            new_scenario_feature = true;
            Debugger.println("FEATURE: " + currentFeature.replaceAll("-", " "));
            Debugger.println("SCENARIO: "+ scenario.getName());
        }else{
            new_scenario_feature = false;
        }

    }




    @After(order=0)
    public void tearDown(Scenario scenario){
        String scenarioStatus =  scenario.getStatus();
        if(!scenarioStatus.equalsIgnoreCase("PASSED")){
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        Debugger.println("STATUS: " + scenarioStatus.toUpperCase());

        System.out.println("deleted cookies");
        driver.findElement(By.xpath("//a[text()='Log out']")).click(); // Logging out to restart new session
        driver.manage().deleteAllCookies();
//        driver.quit();//28 /09/2019  -To delete cookies to terminate session /28 /09/2019  -To delete cookies to terminate session
       // driver=null;

    }
    @After(order=1)
    public void afterScenario(){
        //login_page.logoutFromMI();
    }
}//end class
