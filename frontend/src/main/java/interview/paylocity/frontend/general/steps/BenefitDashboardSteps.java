package interview.paylocity.frontend.general.steps;

import interview.paylocity.frontend.general.pages.BenefitDashboardPage;
import interview.paylocity.frontend.settings.base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BenefitDashboardSteps extends BaseTest {

    // Injection

    @Inject
    private BenefitDashboardPage benefitDashboardPage;

    // Step Definitions

    @When("I select Add Employee")
    public void iSelectAddEmployee() {
        benefitDashboardPage.openAddNewUserWindows();
    }

    @Then("I should be able to enter employee details")
    public void iShouldBeAbleToEnterEmployeeDetails() {
        generalDataHolder.sharedFirstName = randomString(5);
        generalDataHolder.sharedLastName = randomString(5);
        generalDataHolder.sharedDependants = String.valueOf(randomNumberGenerator(5));

        benefitDashboardPage.fillNewUserModalWindow(generalDataHolder.sharedFirstName, generalDataHolder.sharedLastName, generalDataHolder.sharedDependants);
    }

    @And("the employee should save")
    public void theEmployeeShouldSave() throws Exception {
        benefitDashboardPage.saveCreatedUser();
    }

    @And("I should see the employee in the table")
    public void iShouldSeeTheEmployeeInTheTable() {

        benefitDashboardPage.checkDataInBenefitTable(generalDataHolder.sharedFirstName, 1);
        benefitDashboardPage.checkDataInBenefitTable(generalDataHolder.sharedLastName, 2);
        benefitDashboardPage.checkDataInBenefitTable(generalDataHolder.sharedDependants, 3);
    }

    @And("the benefit cost calculations are correct")
    public void theBenefitCostCalculationsAreCorrect() {
        double benefitsDeductedTotal = 1000 + (Integer.parseInt(generalDataHolder.sharedDependants) * 500);
        log.info("Total amount of benefits deducted: " + benefitsDeductedTotal);
        double benefitsDeductedPerPaycheck = benefitsDeductedTotal / 26;
        log.info("Benefits deducted per pay check" + benefitsDeductedPerPaycheck);
        BigDecimal roundedNumber = BigDecimal.valueOf(benefitsDeductedPerPaycheck).setScale(2, RoundingMode.HALF_UP);
        log.info("Rounded number deducted from paycheck: " + roundedNumber);

        benefitDashboardPage.checkDataInBenefitTable(String.valueOf(roundedNumber), 6);
    }

    @When("I select the Action Edit")
    public void iSelectTheActionEdit() {
        log.info("Under development");
    }

    @Then("I can edit employee details")
    public void iCanEditEmployeeDetails() {
        log.info("Under development");
    }

    @And("the data should change in the table")
    public void theDataShouldChangeInTheTable() {
        log.info("Under development");
    }

    @When("I click the Action X")
    public void iClickTheActionX() {
        log.info("Under development");
    }

    @Then("the employee should be deleted")
    public void theEmployeeShouldBeDeleted() {
        log.info("Under development");
    }

}
