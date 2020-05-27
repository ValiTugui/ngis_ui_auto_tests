package co.uk.gel.proj.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"@target/generated-test-sources/rerun.txt"},
        plugin = {"pretty", "json:target/cucumber-parallel/rerun/rerun.json"},
        monochrome = false,
        tags = {"@test"},
        glue = {"co.uk.gel.proj.steps"})

public class FailureScenariosRunner {
}
