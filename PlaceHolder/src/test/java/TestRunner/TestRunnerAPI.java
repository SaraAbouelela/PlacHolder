package TestRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/FeatureFile/API"}
                    , glue = {"StepDefinition"}
                    , tags = ("@TestScenario")
, plugin = {
        "pretty","html:test-output/DefaultReport/DefaultReport.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
        )

public class TestRunnerAPI extends AbstractTestNGCucumberTests
{

}
