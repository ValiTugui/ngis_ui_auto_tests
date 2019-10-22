package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.*;


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

    @Before("@LOGIN_AS_SUPERUSER")
    public void loginAsASuperUser(){
           logoutAfterTest(5);
    }


    @After(order=0)
    public void tearDown(Scenario scenario){
        Status scenarioStatus =  scenario.getStatus();
        if(!scenarioStatus.toString().equalsIgnoreCase("PASSED")){
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        Debugger.println("STATUS: " + scenarioStatus.name().toUpperCase());

        // 14/10/2019 - Log-out now commented as Test will continue running until the session times-out, and user will be prompted to log-in
        // once the session times-out
        // driver.findElement(By.xpath("//a[text()='Log out']")).click(); // Logging out to restart new session
        // driver.manage().deleteAllCookies(); //
        // driver.quit();//28 /09/2019  -To delete cookies to terminate session /28 /09/2019
        // driver=null;

    }

    @After("@LOGOUT")
    public void logOutAndTearDown() {
          logoutAfterTest(10);
    }

    @After(order = 1)
    public void afterScenario() {
        //login_page.logoutFromMI();
    }

    private void logoutAfterTest(int waitingTime) {
        Debugger.println("deleted cookies");
        driver.findElement(By.xpath("//a[text()='Log out']")).click(); // Logging out to restart new session
        driver.manage().deleteAllCookies();
        Wait.seconds(waitingTime);

    }
}//end class


