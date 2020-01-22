package co.uk.gel.proj.runner;

import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
         plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"},
         glue = {"co.uk.gel.proj.steps"},
         features = {"src/test/resources/features"},
       //  tags = {"@secuesfbhjfekjadirtyscans"}
        tags = {"@secuirtyscans"}
        // tags = {"@NTS-3389"}
        )
    public class RunnerTest {
        @BeforeClass
        public static void setup() {
            Debugger.println("\n******* RUN STARTS " + new java.util.Date() + " *******************************");
        }


    }//end