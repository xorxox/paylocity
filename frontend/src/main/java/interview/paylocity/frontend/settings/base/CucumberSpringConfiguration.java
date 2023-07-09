package interview.paylocity.frontend.settings.base;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest(classes = BasePage.class)
public class CucumberSpringConfiguration {
}
