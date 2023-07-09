package interview.paylocity.backend.general;

import interview.paylocity.backend.general.auxiliary.EmployeeAuxiliaryMethods;
import interview.paylocity.backend.settings.base.TestConfig;
import interview.paylocity.backend.settings.topology.Topology;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeSteps extends EmployeeAuxiliaryMethods {

    // Cucumber Tables Definitions

    @Given("User have following data")
    public void userHaveFollowingData(DataTable dataTable) {
        generalDataHolder.sharedUserDataTable = dataTable.asMaps();
    }

    // Step Definitions

    @Given("Employee want to create {string} user")
    public void employeeWantToCreateUser(String userDataId) {
        String userJsonBody = prepareUserJsonBody(generalDataHolder.sharedUserDataTable, userDataId);
        assertNotNull(userJsonBody);
        TestConfig.log.info("Create user JSON body: " + userJsonBody);

        Response response = restClient.prepareRequestWithBody(Topology.TARGET_ENVIRONMENT_URL, Topology.TARGET_ENVIRONMENT_PATH, userJsonBody, Topology.TOKEN_USED).post();
        responseReturnedCorrectHTTPCode(response, 200);

        JsonPath jsonPath = response.jsonPath();
        generalDataHolder.sharedUserId = jsonPath.getString("id");
        assertNotNull(generalDataHolder.sharedUserId);
        TestConfig.log.info("New user is created, userId is " + generalDataHolder.sharedUserId);
    }

    @When("Employee want to update {string} user with {string} data")
    public void employeeWantToUpdateUserWithData(String userType, String userDataId) {
        TestConfig.log.info("Starting user update.");
        String updateUserJsonBody = prepareUpdateUserJsonBody(generalDataHolder.sharedUserDataTable, userDataId, generalDataHolder.sharedUserId);
        assertNotNull(updateUserJsonBody);
        TestConfig.log.info("Update user JSON body: " + updateUserJsonBody);

        Response response = restClient.prepareRequestWithBody(Topology.TARGET_ENVIRONMENT_URL, Topology.TARGET_ENVIRONMENT_PATH, updateUserJsonBody, Topology.TOKEN_USED).put();
        responseReturnedCorrectHTTPCode(response, 200);
    }

    @And("Verify that {string} user using {string} data are correct")
    public void verifyThatUserUsingDataAreCorrect(String userType, String userDataId) {
        TestConfig.log.info("Preparing updated data verification.");
        Response response = restClient.prepareRequestOnlyHeader(Topology.TARGET_ENVIRONMENT_URL, Topology.TARGET_ENVIRONMENT_PATH + "/" + generalDataHolder.sharedUserId, Topology.TOKEN_USED).get();
        responseReturnedCorrectHTTPCode(response, 200);

        String firstName = null, lastName = null;

        for (Map<String, String> map : generalDataHolder.sharedUserDataTable) {
            if (userDataId.equals(map.get("id"))) {
                firstName = map.get("firstName");
                lastName = map.get("lastName");
                break;
            }
        }

        if (firstName == null)
            throw new IllegalArgumentException("It was not possible to load key for userTable data table!");
        JsonPath jsonPath = response.jsonPath();
        assertEquals(firstName, jsonPath.getString("firstName"));
        assertEquals(lastName, jsonPath.getString("lastName"));
        TestConfig.log.info("All values are updated correctly.");
    }

    @Then("Employee want to delete {string} user")
    public void employeeWantToDeleteUser(String userType) {
        TestConfig.log.info("Deletion of User started.");
        Response response = restClient.prepareRequestOnlyHeader(Topology.TARGET_ENVIRONMENT_URL, Topology.TARGET_ENVIRONMENT_PATH + "/" + generalDataHolder.sharedUserId, Topology.TOKEN_USED).delete();
        responseReturnedCorrectHTTPCode(response, 200);
    }

    @And("Verify that {string} user does not exist")
    public void verifyThatUserDoesNotExist(String userType) {
        Response response = restClient.prepareRequestOnlyHeader(Topology.TARGET_ENVIRONMENT_URL, Topology.TARGET_ENVIRONMENT_PATH, Topology.TOKEN_USED).get();
        responseReturnedCorrectHTTPCode(response, 200);

        JsonPath jsonPath = response.jsonPath();
        List<String> listOfIds = jsonPath.getList("id");
        if (listOfIds.contains(generalDataHolder.sharedUserId)) {
            TestConfig.log.error("It was not possible to delete updated user!");
            fail();
        } else {
            TestConfig.log.info("User was deleted successfully!");
        }
    }

}
