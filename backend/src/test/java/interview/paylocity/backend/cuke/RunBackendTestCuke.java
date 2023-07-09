package interview.paylocity.backend.cuke;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/interview/paylocity/backend/features/CreateModifyDeleteUser.feature",
        plugin = {"pretty", "html:target/cucumber/cucumberTests"},
        extraGlue = "interview.paylocity.backend")
public class RunBackendTestCuke {
}
