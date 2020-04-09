package co.uk.gel.proj.runner;

import co.uk.gel.proj.util.Debugger;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
         plugin = {"pretty", "html:target/cucumber","json:target/cucumber.json"},
         glue = {"co.uk.gel.proj.steps"},
         features = {"src/test/resources/features"},
         tags = {"@NTS-3109"}
        )
    public class RunnerTest {
        @BeforeClass
        public static void setup() {
            Debugger.println("\n******* RUN STARTS " + new java.util.Date() + " *******************************");
        }
    }//end