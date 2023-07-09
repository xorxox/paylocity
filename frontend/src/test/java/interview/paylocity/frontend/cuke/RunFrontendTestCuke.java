package interview.paylocity.frontend.cuke;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/interview/paylocity/frontend/features/PreviewBenefitCost.feature",
        plugin = {"pretty", "html:target/cucumber/cucumberTests"},
        extraGlue = "interview.paylocity.frontend")
public class RunFrontendTestCuke {
}
