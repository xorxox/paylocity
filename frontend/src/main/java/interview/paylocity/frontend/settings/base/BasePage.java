package interview.paylocity.frontend.settings.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@PageObject
@ContextConfiguration(classes = BasePage.class)
@ComponentScan(value = {"interview.paylocity.frontend.general", "interview.paylocity.frontend.settings", "interview.paylocity.*"})
public class BasePage {

    // Initializations

    protected static final Logger log = LogManager.getLogger(BasePage.class);

    private final Duration WAIT_TIME_DURATION = Duration.ofSeconds(6);

    // Injections

    @Inject
    protected WebDriver driver;

    // Driver Methods

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Wait Until Methods

    protected void waitUntilWebElementExist(WebElement webElement) {
        await()
                .atMost(Duration.ofSeconds(30))
                .pollDelay(Duration.ofMillis(500))
                .pollInterval(Duration.ofMillis(500))
                .until(() -> verifyElementExistBoolean(webElement));
    }

    protected void waitForElementToDisappear(WebElement element) throws Exception {
        waitTillObjectDisappears(element, WAIT_TIME_DURATION);
        Thread.sleep(10);
    }

    // Verify Elements Methods

    protected void verifyElementExist(WebElement element) {
        verifyExisting(element, WAIT_TIME_DURATION);
        verifyVisibility(element, WAIT_TIME_DURATION);
    }

    protected boolean verifyElementExistBoolean(WebElement element) {
        try {
            verifyExisting(element, WAIT_TIME_DURATION);
            verifyVisibility(element, WAIT_TIME_DURATION);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void checkIfElementExist(WebElement element) {
        verifyExisting(element, WAIT_TIME_DURATION);
        verifyVisibility(element, WAIT_TIME_DURATION);
    }

    // Operate With Element Methods

    protected void clickOnElement(WebElement element) {
        clickOnElement(element, WAIT_TIME_DURATION);
    }

    protected void navigateToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    // Operate Text Methods

    protected void verifyElementValuePositionInTheList(List<WebElement> listOfElements, String value, int position) {
        for (WebElement line : listOfElements) {
            List<WebElement> dataOnEachLine = line.findElements(By.xpath("td"));
            for (WebElement element : dataOnEachLine) {
                verifyElementExist(element);
                if (element.getText().contains(value) && dataOnEachLine.indexOf(element) == position) {
                    log.info("Searched Element value: " + value + " in the listOfElements is in the right position: " + position);
                    return;
                }
            }
        }

        String errorInfo = "Element value: " + value + " in the listOfElements is not in the: " + position + " position";
        log.error(errorInfo);
        fail(errorInfo);
    }

    // Fill Element methods

    protected void fillElement(WebElement element, String input) {
        fillElement(element, input, WAIT_TIME_DURATION);
    }

    // Auxiliary Methods

    private void clickOnElement(WebElement element, Duration waitTime) {
        verifyExisting(element, waitTime);
        verifyVisibility(element, waitTime);
        verifyClickable(element, waitTime);

        element.click();
    }

    private void verifyExisting(WebElement element, Duration waitTime) {
        WebDriverWait waitExist = new WebDriverWait(driver, waitTime);
        waitExist.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
    }

    private void verifyVisibility(WebElement element, Duration waitTime) {
        WebDriverWait waitVisible = new WebDriverWait(driver, waitTime);
        waitVisible.until(visibilityOf(element));
    }

    private void verifyClickable(WebElement element, Duration waitTime) {
        WebDriverWait waitClickable = new WebDriverWait(driver, waitTime);
        waitClickable.until(elementToBeClickable(element));
    }

    private void fillElement(WebElement element, String input, Duration waitTime) {
        verifyExisting(element, waitTime);
        verifyVisibility(element, waitTime);

        sendKeysElement(element, input);
    }

    private void sendKeysElement(WebElement element, String SendKeys) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        element.sendKeys(SendKeys);
    }

    private void waitTillObjectDisappears(WebElement element, Duration timeout) {
        WebDriverWait waitDisappears = new WebDriverWait(driver, timeout);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        waitDisappears.until(invisibilityOf(element));
        driver.manage().timeouts().implicitlyWait(WAIT_TIME_DURATION.dividedBy(3));
    }

}
