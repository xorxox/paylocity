package interview.paylocity.frontend.general.pages;

import interview.paylocity.frontend.settings.base.BasePage;
import interview.paylocity.frontend.settings.base.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageObject
public class BenefitDashboardPage extends BasePage {

    // Web Elements

    @FindBy(xpath = "//button[@id='add']")
    private WebElement addNewUserButton;

    @FindBy(xpath = "//div[@id='employeeModal' and not(contains(@style, 'display: none;'))]//input[@id='firstName']")
    private WebElement createNewUserModalWindowFirstNameInputField;

    @FindBy(xpath = "//div[@id='employeeModal' and not(contains(@style, 'display: none;'))]//input[@id='lastName']")
    private WebElement createNewUserModalWindowLastNameInputField;

    @FindBy(xpath = "//div[@id='employeeModal' and not(contains(@style, 'display: none;'))]//input[@id='dependants']")
    private WebElement createNewUserModalWindowDependantsInputField;

    @FindBy(xpath = "//div[@id='employeeModal' and not(contains(@style, 'display: none;'))]//button[@id='addEmployee']")
    private WebElement createNewUserModalWindowAddButton;

    @FindBy(xpath = "//table[@id='employeesTable']/thead/tr/th[1]")
    private WebElement tableHeaderIdColumn;

    @FindBy(xpath = "//table[@id='employeesTable']/tbody/tr")
    private List<WebElement> tableDataList;

    @FindBy(xpath = "//header//a[contains(@href, '/Prod/Account/LogOut')]")
    private WebElement logOutLink;

    // Page Objects

    public void openAddNewUserWindows() {
        clickOnElement(addNewUserButton);
        waitUntilWebElementExist(createNewUserModalWindowFirstNameInputField);
    }

    public void fillNewUserModalWindow(String firstName, String lastName, String dependants) {
        fillElement(createNewUserModalWindowFirstNameInputField, firstName);
        fillElement(createNewUserModalWindowLastNameInputField, lastName);
        fillElement(createNewUserModalWindowDependantsInputField, dependants);
    }

    public void saveCreatedUser() throws Exception {
        clickOnElement(createNewUserModalWindowAddButton);
        waitForElementToDisappear(createNewUserModalWindowAddButton);
        waitUntilWebElementExist(tableHeaderIdColumn);
    }

    public void checkDataInBenefitTable(String tableDataValue, int position) {
        verifyElementValuePositionInTheList(tableDataList, tableDataValue, position);
    }

    public void clickOnLogOutLink() {
        waitUntilWebElementExist(logOutLink);
        clickOnElement(logOutLink);
    }
}
