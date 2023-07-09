package interview.paylocity.backend.settings.base;

import org.springframework.boot.test.context.SpringBootTest;

@io.cucumber.spring.CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
public class CucumberSpringConfiguration {
}
