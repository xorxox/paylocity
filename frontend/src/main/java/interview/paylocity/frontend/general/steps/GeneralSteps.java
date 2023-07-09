package interview.paylocity.frontend.general.steps;

import interview.paylocity.frontend.general.pages.BenefitDashboardPage;
import interview.paylocity.frontend.general.pages.LoginPage;
import interview.paylocity.frontend.settings.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;

import javax.inject.Inject;

import static interview.paylocity.frontend.settings.topology.Topology.*;

public class GeneralSteps extends BaseTest {

    // Injections

    @Inject
    private LoginPage loginPage;

    @Inject
    private BenefitDashboardPage benefitDashboardPage;

    // Before and After

    @After
    public void logOutFromBenefitDashboard() {
        benefitDashboardPage.clickOnLogOutLink();
        loginPage.waitUntilLoginPageLoad();
    }

    // Step Definitions

    @Given("As an Employer I am on the Benefits Dashboard page")
    public void asAnEmployerIAmOnTheBenefitsDashboardPage() throws Exception {
        loginPage.openLoginPage(PAYLOCITY_LOGIN_PAGE_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);
        loginPage.checkUrlAfterLogin(PAYLOCITY_BENEFIT_DASHBOARD);
    }
}
