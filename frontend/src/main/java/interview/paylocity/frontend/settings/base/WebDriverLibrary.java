package interview.paylocity.frontend.settings.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class WebDriverLibrary {

    @Bean(destroyMethod = "quit")
    public WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--disable-web-security");
        // Comment out following four lines to display browser UI
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));

        return driver;
    }
}
