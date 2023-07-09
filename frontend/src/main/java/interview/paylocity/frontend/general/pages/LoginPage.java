package interview.paylocity.frontend.general.pages;

import interview.paylocity.frontend.settings.base.BasePage;
import interview.paylocity.frontend.settings.base.PageObject;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject
public class LoginPage extends BasePage {

    // Web Elements

    @FindBy(xpath = "//input[@id='Username']")
    private WebElement usernameInputField;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button")
    private WebElement loginButton;

    // Page Object

    public void openLoginPage(String url) throws Exception {
        long start = System.currentTimeMillis();
        long end = start + 300 * 1000;

        while (!(getCurrentUrl()).contains(url)) {
            driver.get(url);
            Thread.sleep(1000);
            if (System.currentTimeMillis() > end) {
                throw new InitializationError("It was not possible to open desired URL in time.");
            }
        }
    }

    public void login(String username, String password) {
        checkIfElementExist(usernameInputField);
        fillElement(usernameInputField, username);
        fillElement(passwordInputField, password);
        clickOnElement(loginButton);
    }

    public void checkUrlAfterLogin(String url) throws Exception {
        long start = System.currentTimeMillis();
        long end = start + 10 * 1000;

        while (!(getCurrentUrl()).contains(url)) {
            log.info("Get current Url: " + getCurrentUrl());
            log.info("Url: " + url);
            Thread.sleep(1000);
            if (System.currentTimeMillis() > end) {
                throw new InitializationError("It was not possible to load desired URL in time.");
            }
        }
    }

    public void waitUntilLoginPageLoad() {
        waitUntilWebElementExist(usernameInputField);
    }
}
